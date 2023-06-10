package com.progettois.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;

import com.progettois.entity.EntityInserimentoCartaceo;
import com.progettois.exception.DAOException;
import com.progettois.exception.DBConnectionException;

public class InserimentoCartaceoDAO {

    public static Hashtable<Long, Integer> readInserimentoCartaceo (long idCarrello) throws DAOException, DBConnectionException {

        Hashtable<Long,Integer> libri = new Hashtable<Long, Integer>();
        
        try {
			
			Connection conn = DBManager.getConnection();
			String query1 = "SELECT CODICEISBN, QUANTITÀRICHIESTA FROM INSERIMENTOCARTACEO WHERE IDCARRELLO=? ;";
            Integer quantitàRichiesta = 0;
			
			try {
				PreparedStatement stmt = conn.prepareStatement(query1);
				stmt.setLong(1, idCarrello);
				
				ResultSet result = stmt.executeQuery();
				while(result.next()) {

                    quantitàRichiesta = result.getInt(2);
                    libri.put(result.getLong(1), quantitàRichiesta);				
				}
			}
            catch(SQLException e) {
                e.printStackTrace();
				throw new DAOException("Errore InserimentoCartaceo readInserimentoCartaceo");
			} finally {
				DBManager.closeConnection();
			}
		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database");
		}
		
		return libri;
		
	}

	public static void createInserimentoCartaceo(EntityInserimentoCartaceo eIC) throws DAOException, DBConnectionException{

        try {
                
            Connection conn = DBManager.getConnection();
            String query = "INSERT INTO INSERIMENTOCARTACEO VALUES (?,?,?);";
            
            try {
                PreparedStatement stmt = conn.prepareStatement(query);
                System.out.println(eIC.getIdCarrello());
                System.out.println(eIC.getCodiceISBN());
                System.out.println(eIC.getQuantitàRichiesta());
                stmt.setLong(1, eIC.getIdCarrello());
				stmt.setLong(2, eIC.getCodiceISBN());
                stmt.setInt(3, eIC.getQuantitàRichiesta());
				stmt.executeUpdate();

            }catch(SQLException e) {
                e.printStackTrace();
                throw new DAOException("Errore Inserimento Cartaceo createInserimentoCartaceo");
            } finally {
                DBManager.closeConnection();
            }
        }catch(SQLException e) {
            throw new DBConnectionException("Errore connessione database");
        }

    }

    public static void delete(long idCarrello, long codiceISBN) throws DAOException, DBConnectionException {

        try{
            Connection conn = DBManager.getConnection();
                String query1 = "DELETE FROM INSERIMENTOCARTACEO WHERE IDCARRELLO =? AND CODICEISBN=?";
                try {
                    PreparedStatement stmt = conn.prepareStatement(query1);
                    stmt.setLong(1, idCarrello);
                    stmt.setLong(2, codiceISBN);
                    
                    stmt.executeUpdate();
                }
                catch(SQLException e) {
                    throw new DAOException("Errore Inserimento Digitale readInserimentoDigitale");
                } finally {
                    DBManager.closeConnection();
                }
		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database");
		}
    }

    public static void updateQt(int qtRichiesta, long idCarrello, long codiceISBN) throws DAOException, DBConnectionException {

        try{
            Connection conn = DBManager.getConnection();
                String query1 = "UPDATE INSERIMENTOCARTACEO SET QUANTITÀRICHIESTA=? WHERE IDCARRELLO =? AND CODICEISBN=?";
                try {
                    PreparedStatement stmt = conn.prepareStatement(query1);
                    stmt.setLong(1, qtRichiesta);
                    stmt.setLong(2, idCarrello);
                    stmt.setLong(3, codiceISBN);
                    
                    stmt.executeUpdate();
                }
                catch(SQLException e) {
                    throw new DAOException("Errore Inserimento Digitale readInserimentoDigitale");
                } finally {
                    DBManager.closeConnection();
                }
		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database");
		}
    }

	

}
