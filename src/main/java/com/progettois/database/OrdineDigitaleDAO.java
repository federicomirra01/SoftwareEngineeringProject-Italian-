package com.progettois.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.progettois.entity.EntityOrdineDigitale;
import com.progettois.exception.DAOException;
import com.progettois.exception.DBConnectionException;

public class OrdineDigitaleDAO {

    public static long createOrdineDigitale(EntityOrdineDigitale eOD) throws DAOException, DBConnectionException{
 
        long idOrdine = 0L;

        try {
                
            Connection conn = DBManager.getConnection();
            String query = "INSERT INTO ORDINEDIGITALE (DATASCARICAMENTO, STATO, IDCLIENTEREGISTRATO) VALUES (NULL, NULL, ?);";
            String query2 = "SELECT MAX(IDORDINEDIGITALE) FROM ORDINEDIGITALE";
            
            try {
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setLong(1, eOD.getIdClienteRegistrato());
				stmt.executeUpdate();

                Statement stmt2 = conn.prepareStatement(query2);
                ResultSet result = stmt2.executeQuery(query2);

                if(result.next()){
                    idOrdine = result.getLong(1);
                } else{
                    throw new DAOException("Id non trovato");
                }

            }catch(SQLException e) {
                e.printStackTrace();
                throw new DAOException("Errore Ordine Digitale createOrdineDigitale");
            } finally {
                DBManager.closeConnection();
            }
        }catch(SQLException e) {
            throw new DBConnectionException("Errore connessione database");
        }

        return idOrdine;

    }

    public static void updatePrezzo(EntityOrdineDigitale eOD) throws DAOException, DBConnectionException {

        try {
			Connection conn = DBManager.getConnection();
			String query = "UPDATE ORDINEDIGITALE SET PREZZO=? WHERE IDORDINEDIGITALE=?;";
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setDouble(1, eOD.getPrezzo());
				stmt.setLong(2, eOD.getIdOrdine());
				
				stmt.executeUpdate();
				
			}catch(SQLException e) {
				
				throw new DAOException("Errore update prezzo ordine digitale");
				
			} finally {
				DBManager.closeConnection();
			}
		}catch(SQLException e) {
			
			throw new DBConnectionException("Errore connessione database");
			
		}
    }

    public static double readOrdineDigitalePrezzo(long idOrdineDigitale) throws DAOException, DBConnectionException {
        
        double prezzo = 0;
		
		try {
			
			Connection conn = DBManager.getConnection();
			String query = "SELECT PREZZO FROM ORDINEDIGITALE WHERE IDORDINEDIGITALE=? ;";
			
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setLong(1, idOrdineDigitale);
				
				ResultSet result = stmt.executeQuery();
				if(result.next()) {
					
					prezzo = result.getDouble(1);
					
				}
			}catch(SQLException e) {
				throw new DAOException("Errore Ordine Digitale readOrdineDigitale");
			} finally {
				DBManager.closeConnection();
			}
		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database");
		}
		
		return prezzo;
    }

    public static void updateStato(EntityOrdineDigitale eOD) throws DAOException, DBConnectionException {

		try {
			Connection conn = DBManager.getConnection();
			String query = "UPDATE ORDINEDIGITALE SET STATO=? WHERE IDORDINEDIGITALE=?;";
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setString(1, eOD.getStato().toString() );
				stmt.setLong(2, eOD.getIdOrdine());
				
				stmt.executeUpdate();
				
			}catch(SQLException e) {
				
				throw new DAOException("Errore update stato ordine cartaceo");
				
			} finally {
				DBManager.closeConnection();
			}
		}catch(SQLException e) {
			
			throw new DBConnectionException("Errore connessione database");
			
		}
    }
    
}
