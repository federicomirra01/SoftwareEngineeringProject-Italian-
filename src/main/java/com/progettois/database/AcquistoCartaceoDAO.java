package com.progettois.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.progettois.exception.DAOException;
import com.progettois.exception.DBConnectionException;

public class AcquistoCartaceoDAO {

    public static void createAcquistoCartaceo(long idOrdineCartaceo, long codiceISBN, int quantità) throws DAOException, DBConnectionException{

        try {
                
            Connection conn = DBManager.getConnection();
            String query = "INSERT INTO ACQUISTOCARTACEO VALUES (?,?,?);";
            
            try {
                PreparedStatement stmt = conn.prepareStatement(query);
                
                stmt.setLong(1, idOrdineCartaceo);
				stmt.setLong(2, codiceISBN);
                stmt.setInt(3, quantità);
				stmt.executeUpdate();

            }catch(SQLException e) {
                throw new DAOException("Errore Acquisto Cartaceo createAcquistoCartaceo");
            } finally {
                DBManager.closeConnection();
            }
        }catch(SQLException e) {
            throw new DBConnectionException("Errore connessione database");
        }

    }

    public static ArrayList<Long> readAcquistoCartaceo(long idOrdineCartaceo) throws DAOException, DBConnectionException{

        ArrayList<Long> codiciISBN = new ArrayList<Long>();
        try {
			
			Connection conn = DBManager.getConnection();
			String query1 = "SELECT CODICEISBN FROM ACQUISTOCARTACEO WHERE IDORDINECARTACEO=?";
			try {
				PreparedStatement stmt = conn.prepareStatement(query1);
				stmt.setLong(1, idOrdineCartaceo);
				
				ResultSet result = stmt.executeQuery();
				while(result.next() == true) {

                    codiciISBN.add(result.getLong(1));	
					
					
				}
			}
            catch(SQLException e) {
                e.printStackTrace();
				throw new DAOException("Errore Acquisto Cartaceo readAcquistoCartaceo");
			} finally {
				DBManager.closeConnection();
			}
		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database");
		}
		
		return codiciISBN;
		
	}
}
    

