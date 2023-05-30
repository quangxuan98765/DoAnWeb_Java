package DAO_model;

import java.sql.*;
public class ConnectionClass {
	public static String url = "jdbc:mysql://localhost:3306/laptrinhweb2";
	public static String username = "root";
	public static String password = "";
	public static Connection getConnection() {
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection conn = DriverManager.getConnection(url, username, password);
	        return conn;
	    } catch (SQLException e) {
	        System.out.println("Không thể kết nối với cơ sở dữ liệu. Lỗi: " + e.getMessage());
	    } catch (ClassNotFoundException e) {
	        System.out.println("Không tìm thấy driver JDBC. Lỗi: " + e.getMessage());
	    }
	    return null;
	}


}
