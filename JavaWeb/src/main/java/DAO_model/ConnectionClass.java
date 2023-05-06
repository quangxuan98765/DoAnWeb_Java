package DAO_model;

import java.sql.*;
public class ConnectionClass {
	public static String url = "jdbc:mysql://localhost:3306/laptrinhweb2";
	public static String username = "root";
	public static String password = "";
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			//e.printStackTrace();
			System.out.println("loi" + e.getMessage());
		}
		return null;
	}

}
