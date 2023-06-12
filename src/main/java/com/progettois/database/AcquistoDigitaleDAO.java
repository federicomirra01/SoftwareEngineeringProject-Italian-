package com.progettois.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.progettois.exception.DAOException;
import com.progettois.exception.DBConnectionException;

public class AcquistoDigitaleDAO {

    public static void createAcquistoDigitale(long idOrdineDigitale, long codiceISBN, String codiceScaricamento) throws DAOException, DBConnectionException{

        try {
                
            Connection conn = DBManager.getConnection();
            String query = "INSERT INTO ACQUISTODIGITALE VALUES (?,?,?);";
            
            try {
                PreparedStatement stmt = conn.prepareStatement(query);
                
                stmt.setLong(1, idOrdineDigitale);
				stmt.setLong(2, codiceISBN);
                stmt.setString(3, codiceScaricamento);
				stmt.executeUpdate();

            }catch(SQLException e) {
                throw new DAOException("Errore AcquistoDigitale createAcquistoDigitale");
            } finally {
                DBManager.closeConnection();
            }
        }catch(SQLException e) {
            throw new DBConnectionException("Errore connessione database");
        }

    }

    public static ArrayList<Long> readAcquistoDigitale(long idOrdineDigitale) throws DAOException, DBConnectionException{

        ArrayList<Long> codiciISBN = new ArrayList<Long>();
        try {
			
			Connection conn = DBManager.getConnection();
			String query1 = "SELECT CODICEISBN FROM ACQUISTODIGITALE WHERE IDORDINEDIGITALE=?";
			try {
				PreparedStatement stmt = conn.prepareStatement(query1);
				stmt.setLong(1, idOrdineDigitale);
				
				ResultSet result = stmt.executeQuery();
				while(result.next() == true) {

                    codiciISBN.add(result.getLong(1));	
					
					
				}
			}
            catch(SQLException e) {
                e.printStackTrace();
				throw new DAOException("Errore Acquisto Digitale readAcquistoDigitale");
			} finally {
				DBManager.closeConnection();
			}
		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database");
		}
		
		return codiciISBN;
		
	}
    
}