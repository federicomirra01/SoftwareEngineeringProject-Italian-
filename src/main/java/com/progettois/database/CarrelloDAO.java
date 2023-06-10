package com.progettois.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.progettois.abstractClass.Libro;
import com.progettois.entity.EntityCarrello;
import com.progettois.exception.DAOException;
import com.progettois.exception.DBConnectionException;

public class CarrelloDAO {


    public static long createCarrello() throws DAOException, DBConnectionException{

        long idCarrello = 0L;

        try {
                
            Connection conn = DBManager.getConnection();
            String query = "INSERT INTO CARRELLO (PREZZO) VALUES (?);";
            String query2 = "SELECT MAX(IDCARRELLO) FROM CARRELLO;";
            try {
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setDouble(1, 0);
                stmt.executeUpdate();

                Statement stmt2 = conn.prepareStatement(query2);

                ResultSet result = stmt2.executeQuery(query2);
                if(result.next())
                    idCarrello = result.getLong(1);


            }catch(SQLException e) {
                throw new DAOException("Errore Cliente Registrato createCarrello");
            } finally {
                DBManager.closeConnection();
            }
        }catch(SQLException e) {
            throw new DBConnectionException("Errore connessione database");
        }

        return idCarrello;

    }

    public static EntityCarrello readCarrello(long idCarrello) throws DAOException, DBConnectionException {

        EntityCarrello carrello = null;
        try {
			
			Connection conn = DBManager.getConnection();
			String query = "SELECT * FROM CARRELLO WHERE IDCARRELLO=?;";
			
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setLong(1, idCarrello);
				
				ResultSet result = stmt.executeQuery();
				if(result.next()) {
					
					carrello = new EntityCarrello(idCarrello, new ArrayList<Libro>(), result.getDouble(2));
					
				}
			}catch(SQLException e) {
				throw new DAOException("Errore LibroCartaceo readLibroCartaceo");
			} finally {
				DBManager.closeConnection();
			}
		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database");
		}

        return carrello;

        
    }

    public static void updatePrezzo(EntityCarrello carrello) throws DAOException, DBConnectionException {

        try {
			Connection conn = DBManager.getConnection();
			String query = "UPDATE CARRELLO SET PREZZO=? WHERE IDCARRELLO=?;";
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setDouble(1, carrello.getPrezzoTotale());
				stmt.setLong(2, carrello.getIdCarrello());
				
				stmt.executeUpdate();
				
			}catch(SQLException e) {
				
				throw new DAOException("Errore update prezzo carrello");
				
			} finally {
				DBManager.closeConnection();
			}
		}catch(SQLException e) {
			
			throw new DBConnectionException("Errore connessione database");
			
		}
    }
        
    
    
}
