package com.progettois.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.progettois.entity.EntityOrdineCartaceo;
import com.progettois.exception.DAOException;
import com.progettois.exception.DBConnectionException;

public class OrdineCartaceoDAO {

    public static long createOdineCartaceo(EntityOrdineCartaceo eOC) throws DAOException, DBConnectionException{

        long idOrdine = 0L;
        try {
                
            Connection conn = DBManager.getConnection();
            String query = "INSERT INTO ORDINECARTACEO (DATACONSEGNA, STATO, IDCLIENTEREGISTRATO) VALUES (NULL, NULL, ?);";
            String query2 = "SELECT MAX(IDORDINECARTACEO) FROM ORDINECARTACEO";
            
            try {
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setLong(1, eOC.getIdClienteRegistrato());
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
                throw new DAOException("Errore Ordine Cartaceo createOrdineCartaceo");
            } finally {
                DBManager.closeConnection();
            }
        }catch(SQLException e) {
            throw new DBConnectionException("Errore connessione database");
        }

        return idOrdine;

    }

    public static void updatePrezzo(EntityOrdineCartaceo eOC) throws DAOException, DBConnectionException {
        try {
			Connection conn = DBManager.getConnection();
			String query = "UPDATE ORDINECARTACEO SET PREZZO=? WHERE IDORDINECARTACEO=?;";
			
            try {
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setDouble(1, eOC.getPrezzo());
				stmt.setLong(2, eOC.getIdOrdine());
				
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

    public static double readOrdineCartaceoPrezzo(long idOrdineCartaceo) throws DAOException, DBConnectionException {

        double prezzo = 0;
		
		try {
			
			Connection conn = DBManager.getConnection();
			String query = "SELECT PREZZO FROM ORDINECARTACEO WHERE IDORDINECARTACEO=? ;";
			
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setLong(1, idOrdineCartaceo);
				
				ResultSet result = stmt.executeQuery();
				if(result.next()) {
					
					prezzo = result.getDouble(1);
					
				}
			}catch(SQLException e) {
				throw new DAOException("Errore Ordine Cartaceo readOrdineCartaceoPrezzo");
			} finally {
				DBManager.closeConnection();
			}
		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database");
		}
		
		return prezzo;
       
    }

    public static void updateStato(EntityOrdineCartaceo eOC) throws DAOException, DBConnectionException {
		
		try {
			Connection conn = DBManager.getConnection();
			String query = "UPDATE ORDINECARTACEO SET STATO=? WHERE IDORDINECARTACEO=?;";
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setString(1, eOC.getStato().toString() );
				stmt.setLong(2, eOC.getIdOrdine());
				
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
    
