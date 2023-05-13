package DAO_model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
public class User_DAO{
	
	public boolean themTaiKhoan(Object obj) {
		User_model kh = (User_model) obj;
		try {
			Connection conn = ConnectionClass.getConnection();
			PreparedStatement ps = conn.prepareStatement("INSERT INTO users (username, password, email, fullname, role) VALUES (?, ?, ?, ?, 'normal')");
			ps.setString(1, kh.getUsername());
			ps.setString(2, kh.getPassword());
			ps.setString(3, kh.getEmail());
			ps.setString(4, kh.getFullname());
			//ps.setString(5, "user");
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	//public boolean updateUser(User user) do an khong yeu cau
	
	public boolean checkLogin(String username, String password) {
		try {
			Connection conn = ConnectionClass.getConnection();
			//Statement stmt = conn.createStatement(); thay vi dung statement se ko an toan
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE username = ?");
			ps.setString(1, username); //tham so thu 1
			ResultSet rs = ps.executeQuery();
			boolean exist = false;
			if(rs.next())
				exist = true;
			ps.close();
			conn.close();
			return exist;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	
	public List<User_model> getAll(){
		try {
			Connection conn = ConnectionClass.getConnection();
			String sql = "SELECT * FROM USER ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery(sql);
			List<User_model> list = new ArrayList<User_model>();
			if(rs.next()) {
				User_model user = new User_model();
				user.setUsername(rs.getString("username"));
				user.setEmail(rs.getString("email"));
				user.setFullname(rs.getString("fullname"));
				user.setRole(rs.getString("role"));
				list.add(user);
			}
			ps.close();
			conn.close();
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

}