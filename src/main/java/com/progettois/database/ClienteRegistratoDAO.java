package com.progettois.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.progettois.entity.EntityClienteRegistrato;
import com.progettois.exception.DAOException;
import com.progettois.exception.DBConnectionException;

public class ClienteRegistratoDAO {
    
    public static EntityClienteRegistrato readClienteRegistrato(String nomeUtente, String password) throws DAOException, DBConnectionException{
		
		EntityClienteRegistrato eCR = null;
		
		try {
			
			Connection conn = DBManager.getConnection();
			String query = "SELECT * FROM CLIENTEREGISTRATO WHERE NOMEUTENTE=? AND PASSWORD=?;";
			
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setString(1, nomeUtente);
				stmt.setString(2, password);
				ResultSet result = stmt.executeQuery();
				if(result.next()) {
					
					eCR = new EntityClienteRegistrato(result.getLong(1), result.getString(2), result.getString(3),result.getString(4), result.getString(5), result.getString(6), result.getInt(7), result.getLong(8), result.getString(9), result.getString(10), result.getLong(11));
					
				}
			}catch(SQLException e) {
				throw new DAOException("Errore Cliente Registrato readClienteRegistrato");
			} finally {
				DBManager.closeConnection();
			}
		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database");
		}
		
		return eCR;
		
	}

	public static void createClienteRegistrato(EntityClienteRegistrato eCR, long idCarrello) throws DAOException, DBConnectionException{
		
		
		try {
			
			Connection conn = DBManager.getConnection();
			String query = "INSERT INTO CLIENTEREGISTRATO (NOMEUTENTE, PASSWORD, NOME, COGNOME, INDIRIZZO, CAP, TELEFONO, CITTÀ, EMAIL, IDCARRELLO)  VALUES (?,?,?,?,?,?,?,?,?,?);";
			
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setString(1, eCR.getNomeUtente());
				stmt.setString(2, eCR.getPassword());
				stmt.setString(3, eCR.getNome());
				stmt.setString(4, eCR.getCognome());
				stmt.setString(5, eCR.getIndirizzo());
				stmt.setInt(6, eCR.getCap());
				stmt.setLong(7, eCR.getTelefono());
				stmt.setString(8, eCR.getCittà());
				stmt.setString(9, eCR.getEmail());
				stmt.setLong(10, idCarrello);

				stmt.executeUpdate();

			}catch(SQLException e) {
				throw new DAOException("Errore Cliente Registrato createClienteRegistrato");
			} finally {
				DBManager.closeConnection();
			}
		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database");
		}
		
		
	}
}

