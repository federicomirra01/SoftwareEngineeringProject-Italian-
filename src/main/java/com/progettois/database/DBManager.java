package com.progettois.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
	
	private static Connection conn = null;
	private final static String dbPath = "//localhost:3306/GestioneLibreria";
	private final static String url = "jdbc:mysql:" + dbPath;
	private final static String username = "root";
	private final static String password = "";
	
	private DBManager() {}
	
	public static Connection getConnection() throws SQLException{
		
		if(conn == null || conn.isClosed()) {
			
			conn = DriverManager.getConnection(url, username, password);
		}
		
		return conn;
	}
	
	public static void closeConnection() throws SQLException {
		
		if(conn != null) {
			
			conn.close();
			
		}
	}

}
