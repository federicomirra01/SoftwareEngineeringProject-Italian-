package com.progettois.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.progettois.entity.EntityDipendente;
import com.progettois.exception.DAOException;
import com.progettois.exception.DBConnectionException;

public class DipendenteDAO {

    public static EntityDipendente readDipendente(String nomeUtente, String password) throws DAOException, DBConnectionException{
		
		EntityDipendente eD = null;
		
		try {
			
			Connection conn = DBManager.getConnection();
			String query = "SELECT * FROM DIPENDENTE WHERE NOMEUTENTE=? AND PASSWORD=?;";
			
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setString(1, nomeUtente);
				stmt.setString(2, password);
				ResultSet result = stmt.executeQuery();
				if(result.next()) {
                    
                    eD = new EntityDipendente(nomeUtente, password);
					
				}
			}catch(SQLException e) {
				throw new DAOException("Errore Dipendente readDipendente");
			} finally {
				DBManager.closeConnection();
			}
		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database");
		}
		
		return eD;
		
	}
    
}
