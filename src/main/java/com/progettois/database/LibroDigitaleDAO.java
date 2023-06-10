package com.progettois.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.progettois.abstractClass.Libro;
import com.progettois.entity.EntityLibroDigitale;
import com.progettois.exception.DAOException;
import com.progettois.exception.DBConnectionException;

public class LibroDigitaleDAO {
	
	public static void createLibroDigitale(EntityLibroDigitale eLD) throws DAOException, DBConnectionException {
		
		try {
			
			Connection conn = DBManager.getConnection();
			String query = "INSERT INTO LIBRODIGITALE VALUES (?,?,?,?,?,?);";
			
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				
				stmt.setLong(1, eLD.getCodiceISBN());
				stmt.setString(2, eLD.getTitolo());
				stmt.setString(3, eLD.getAutori());
				stmt.setString(4, eLD.getCasaEditrice());
				stmt.setString(5, eLD.getGenere());
				stmt.setDouble(6, eLD.getPrezzo());
				
				stmt.executeUpdate();
				
			}catch(SQLException e) {
				
				throw new DAOException("Errore memorizzazione libro digitale");
				
			} finally {
				
				DBManager.closeConnection();
			}
			
		}catch(SQLException e) {
			
			throw new DBConnectionException("Errore connessione database");
		}
		
		
	}
	
public static EntityLibroDigitale readLibroDigitale(long codiceISBN) throws DAOException, DBConnectionException{
		
		EntityLibroDigitale eLD = null;
		
		try {
			
			Connection conn = DBManager.getConnection();
			String query = "SELECT * FROM LIBRODIGITALE WHERE CODICEISBN=? ;";
			
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setLong(1, codiceISBN);
				
				ResultSet result = stmt.executeQuery();
				if(result.next()) {
					
					eLD = new EntityLibroDigitale(codiceISBN, result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getInt(6));
					
				}
			}catch(SQLException e) {
				throw new DAOException("Errore LibroDigitale readLibroDigitale");
			} finally {
				DBManager.closeConnection();
			}
		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database");
		}
		
		return eLD;
		
	}

	private static EntityLibroDigitale deserializeCurrentRecord(Connection conn, ResultSet rs) throws DAOException, SQLException{
		EntityLibroDigitale libro = new EntityLibroDigitale(rs.getLong(1), rs.getString(2), rs.getString(3),
		rs.getString(4), rs.getString(5), rs.getInt(6));

		return libro;
		
	}

	public static ArrayList<Libro> visualizzaLibriDigitaliiDisponibili() throws DAOException, DBConnectionException{

		ArrayList<Libro> listaLibri = new ArrayList<Libro>();
		
		try{
			Connection conn = DBManager.getConnection();
			final String query = "SELECT CODICEISBN, TITOLO, AUTORI, CASAEDITRICE, GENERE, PREZZO FROM LIBRODIGITALE";

			try{
				Statement stmt = conn.createStatement();
				ResultSet result = stmt.executeQuery(query);

				while(result.next()){

					EntityLibroDigitale libro = deserializeCurrentRecord(conn, result);
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
