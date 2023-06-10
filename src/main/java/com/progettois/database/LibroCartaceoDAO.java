package com.progettois.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.progettois.abstractClass.Libro;
import com.progettois.entity.EntityLibroCartaceo;
import com.progettois.exception.DAOException;
import com.progettois.exception.DBConnectionException;

public class LibroCartaceoDAO {
	
	public static void createLibroCartaceo(EntityLibroCartaceo eLC) throws DAOException, DBConnectionException {
		
		try {
			
			Connection conn = DBManager.getConnection();
			String query = "INSERT INTO LIBROCARTACEO VALUES (?,?,?,?,?,?,?);";
			
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				
				stmt.setLong(1, eLC.getCodiceISBN());
				stmt.setString(2, eLC.getTitolo());
				stmt.setString(3, eLC.getAutori());
				stmt.setString(4, eLC.getCasaEditrice());
				stmt.setString(5, eLC.getGenere());
				stmt.setDouble(6, eLC.getPrezzo());
				stmt.setInt(7, eLC.getQtdisponibile());
				
				stmt.executeUpdate();
				
			}catch(SQLException e) {
				
				throw new DAOException("Errore memorizzazione libro cartaceo");
				
			} finally {
				
				DBManager.closeConnection();
			}
			
		}catch(SQLException e) {
			
			throw new DBConnectionException("Errore connessione database");
		}
		
		
	}
	
	public static EntityLibroCartaceo readLibroCartaceo(long codiceISBN) throws DAOException, DBConnectionException{
		
		EntityLibroCartaceo eLC = null;
		
		try {
			
			Connection conn = DBManager.getConnection();
			String query = "SELECT * FROM LIBROCARTACEO WHERE CODICEISBN=? ;";
			
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setLong(1, codiceISBN);
				
				ResultSet result = stmt.executeQuery();
				if(result.next()) {
					
					eLC = new EntityLibroCartaceo(codiceISBN, result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getInt(6), result.getInt(7));
					
				}
			}catch(SQLException e) {
				throw new DAOException("Errore LibroCartaceo readLibroCartaceo");
			} finally {
				DBManager.closeConnection();
			}
		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database");
		}
		
		return eLC;
		
	}
	
	public static void updateQtLibroCartaceo(EntityLibroCartaceo eLC) throws DAOException, DBConnectionException{
		
		try {
			Connection conn = DBManager.getConnection();
			String query = "UPDATE LIBROCARTACEO SET QUANTITÀDISPONIBILE=? WHERE CODICEISBN=?;";
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setInt(1, eLC.getQtdisponibile());
				stmt.setLong(2, eLC.getCodiceISBN());
				
				stmt.executeUpdate();
				
			}catch(SQLException e) {
				
				throw new DAOException("Errore update quantità disponibile");
				
			} finally {
				DBManager.closeConnection();
			}
		}catch(SQLException e) {
			
			throw new DBConnectionException("Errore connessione database");
			
		}
	}

	private static EntityLibroCartaceo deserializeCurrentRecord(Connection conn, ResultSet rs) throws DAOException, SQLException{
		EntityLibroCartaceo libro = new EntityLibroCartaceo(rs.getLong(1), rs.getString(2), rs.getString(3),
		rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7));

		return libro;
		
	}

	public static ArrayList<Libro> visualizzaLibriCartaceiDisponibili() throws DAOException, DBConnectionException{

		ArrayList<Libro> listaLibri = new ArrayList<Libro>();
		try{
			Connection conn = DBManager.getConnection();
			final String query = "SELECT CODICEISBN, TITOLO, AUTORI, CASAEDITRICE, GENERE, PREZZO, QUANTITÀDISPONIBILE FROM LIBROCARTACEO WHERE QUANTITÀDISPONIBILE>0";

			try{
				Statement stmt = conn.createStatement();
				ResultSet result = stmt.executeQuery(query);

				while(result.next()){

					EntityLibroCartaceo libro = deserializeCurrentRecord(conn, result);
					listaLibri.add(libro);

				}

			} catch (SQLException e){
				throw new DAOException("Errore visualizzazione lista libri");
			} finally {
				DBManager.closeConnection();
			}
		} catch (SQLException e){
			throw new DBConnectionException("Errore connessione database");
		}

		return listaLibri;
	}
}

