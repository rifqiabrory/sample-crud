package com.kantin.apps;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class Config {

	private static Connection connect = null;
	
	public static Connection getConnection() {
		if(connect == null) {
			//System.out.println("Get connecting..");
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connect = DriverManager
						.getConnection("jdbc:mysql://localhost:3306/db_kantin?" + "user=root&password=root");
				//System.out.println("Successfully connected");
			} catch (SQLException e) {
				e.printStackTrace();
			}catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return connect;
	}
}
