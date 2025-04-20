package com.ryuk.bank.tools;

import java.sql.*;

public class DataBaseLinker {
	
	private static String url= "jdbc:mysql://localhost:3306/bank_database?useSSL=false";
	private static String username= "root";
	private static String mdp= "root";
	private static Connection connex;
	
	public static Connection getConnexion() {
		try {
			if(connex == null) {
				connex= DriverManager.getConnection(url, username, mdp);
				System.out.println("connexion reussi");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connex;
	}
	
	public static void closeConnection() {
        try {
        	connex.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
}
