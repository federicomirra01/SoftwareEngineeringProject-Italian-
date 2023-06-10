package com.progettois.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.progettois.entity.EntityInserimentoDigitale;
import com.progettois.exception.DAOException;
import com.progettois.exception.DBConnectionException;

public class InserimentoDigitaleDAO {
    
    public static void createInserimentoDigitale(EntityInserimentoDigitale eID) throws DAOException, DBConnectionException{

        try {
                
            Connection conn = DBManager.getConnection();
            String query = "INSERT INTO INSERIMENTODIGITALE VALUES (?,?);";
            
            try {
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setLong(1, eID.getIdCarrello());
				stmt.setLong(2, eID.getCodiceISBN());
				stmt.executeUpdate();

            }catch(SQLException e) {
                throw new DAOException("Errore Inserimento Digitale createInserimentoDigitale");
            } finally {
                DBManager.closeConnection();
            }
        }catch(SQLException e) {
            throw new DBConnectionException("Errore connessione database");
        }

    }

    public static ArrayList<Long> readInserimentoDigitale (long idCarrello) throws DAOException, DBConnectionException {

        ArrayList<Long> libri = new ArrayList<Long>();
        
        try {
			
			Connection conn = DBManager.getConnection();
			String query1 = "SELECT CODICEISBN FROM INSERIMENTODIGITALE WHERE IDCARRELLO =?";
			try {
				PreparedStatement stmt = conn.prepareStatement(query1);
				stmt.setLong(1, idCarrello);
				
				ResultSet result = stmt.executeQuery();
				while(result.next() == true) {

                    libri.add(result.getLong(1));	
					
					
				}
			}
            catch(SQLException e) {
                e.printStackTrace();
				throw new DAOException("Errore Inserimento Digitale readInserimentoDigitale");
			} finally {
				DBManager.closeConnection();
			}
		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database");
		}
		
		return libri;
		
	}

    public static void delete(long idCarrello, long codiceISBN) throws DAOException, DBConnectionException {

        try{
            Connection conn = DBManager.getConnection();
                String query1 = "DELETE FROM INSERIMENTODIGITALE WHERE IDCARRELLO =? AND CODICEISBN=?";
                try {
                    PreparedStatement stmt = conn.prepareStatement(query1);
                    stmt.setLong(1, idCarrello);
                    stmt.setLong(2, codiceISBN);
                    
                    stmt.executeUpdate();
                }
                catch(SQLException e) {
                    e.printStackTrace();
                    throw new DAOException("Errore Inserimento Digitale readInserimentoDigitale");
                } finally {
                    DBManager.closeConnection();
                }
		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database");
		}


    }
}
