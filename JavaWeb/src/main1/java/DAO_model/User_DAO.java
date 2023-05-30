package DAO_model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
import java.util.*;
public class User_DAO{
	
	public boolean themTaiKhoan(Object obj) {
		User_model kh = (User_model) obj;
		try {
			Connection conn = ConnectionClass.getConnection();
			PreparedStatement ps = conn.prepareStatement("INSERT INTO users (username, password, email, fullname, role, disabled) VALUES (?, ?, ?, ?, 'normal', '0')");
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
	
	public boolean isAdmin(String username) {
	    try {
	        Connection conn = ConnectionClass.getConnection();
	        PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE username = ?");
	        ps.setString(1, username);
	        ResultSet rs = ps.executeQuery();
	        boolean isAdmin = false;
	        if (rs.next()) { // Kiểm tra xem có bản ghi trong kết quả trả về hay không
	            String role = rs.getString("role");
	            if (role.equals("admin")) {
	                isAdmin = true;
	            }
	        }
	        rs.close();
	        ps.close();
	        conn.close();
	        return isAdmin;
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println(e.getMessage());
	    }
	    return false;
	}

	public boolean isDisabled(String username) {
	    try {
	        Connection conn = ConnectionClass.getConnection();
	        PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE username = ?");
	        ps.setString(1, username);
	        ResultSet rs = ps.executeQuery();
	        boolean isDisabled = false;
	        if (rs.next()) { // Kiểm tra xem có bản ghi trong kết quả trả về hay không
	            String role = rs.getString("disabled");
	            if (role.equals(0)) {
	            	isDisabled = true;
	            }
	        }
	        rs.close();
	        ps.close();
	        conn.close();
	        return isDisabled;
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println(e.getMessage());
	    }
	    return false;
	}
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
			String sql = "SELECT * FROM users ";
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
	
	public boolean ThemDC(Object obj, String username) {
		User_model kh = (User_model) obj;
		try {
			Connection conn = ConnectionClass.getConnection();
			PreparedStatement ps = conn.prepareStatement("INSERT INTO diachi (taikhoan, city, tenduong, sonha) VALUES (?, ?, ?, ?)");
			ps.setString(1, username);
			ps.setString(2, kh.getCity());
			ps.setString(3, kh.getTenduong());
			ps.setString(4, kh.getSonha());
			//ps.setString(5, "user");
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	
	public List<User_model> getDC(String username) {
	    try {
	        Connection conn = ConnectionClass.getConnection();
	        String sql = "SELECT * FROM diachi WHERE taikhoan = ?";
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setString(1, username);
	        ResultSet rs = ps.executeQuery();
	        
	        List<User_model> list = new ArrayList<User_model>();
	        while (rs.next()) {
	            User_model user = new User_model();
	            user.setIdc(rs.getInt("id"));
	            user.setCity(rs.getString("city"));
	            user.setTenduong(rs.getString("tenduong"));
	            user.setSonha(rs.getString("sonha"));
	            list.add(user);
	        }
	        
	        rs.close();
	        ps.close();
	        conn.close();
	        return list;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	public User_model getDCByid(String id) {
		try {
			Connection conn = ConnectionClass.getConnection();
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM diachi WHERE id = '" + id + "'";
			ResultSet rs = stmt.executeQuery(sql);
			User_model p = new User_model();
			if(rs.next()) {
				p.setIdc(rs.getInt("id"));
				p.setCity(rs.getString("city"));
				p.setTenduong(rs.getString("tenduong"));
				p.setSonha(rs.getString("sonha"));
			}
			stmt.close();
			conn.close();
			return p;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean SuaDC(Object obj, String id, String tk) {
		User_model kh = (User_model) obj;
		try {
			Connection conn = ConnectionClass.getConnection();
			String sql = "UPDATE diachi SET taikhoan=?, city=?, tenduong=?, sonha=? WHERE id=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tk);
			pstmt.setString(2, kh.getCity());
			pstmt.setString(3, kh.getTenduong());
			pstmt.setString(4, kh.getSonha());
			pstmt.setString(5, id);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean XoaDC(String id) {
		try {
			Connection conn = ConnectionClass.getConnection();
			Statement stmt = conn.createStatement();
			String sql = "DELETE FROM diachi WHERE id = '" + id + "'";
			stmt.executeUpdate(sql);
			stmt.close();
			conn.close();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	
	public List<User_model> getUsersByRole(String role){
		List<User_model> list = new ArrayList<User_model>();
		try {
		    Connection conn = ConnectionClass.getConnection();
		    String sql;
		    if (role == null || role.isEmpty()) {
		        sql = "SELECT `fullname`, `username`, `role`, `disabled` FROM `users`";
		    } else {
		        sql = "SELECT `fullname`, `username`, `role`, `disabled` FROM `users` WHERE `role` = ?";
		    }
		    PreparedStatement ps = conn.prepareStatement(sql);

		    if (role != null && !role.isEmpty()) {
		        ps.setString(1, role);
		    }

		    ResultSet results = ps.executeQuery();
		    while (results.next()) {
		        User_model user = new User_model();
		        user.setUsername(results.getString("username"));
		        user.setFullname(results.getString("fullname"));
		        user.setRole(results.getString("role"));
		        user.setDisabled(results.getString("disabled"));
		        list.add(user);
		    }
		    conn.close();
		} catch (Exception e) {
		    e.printStackTrace();
		}
		return list;
	}
	public boolean updateDisabled(String username, String disable_value) {
		try {
		Connection conn = ConnectionClass.getConnection();
		PreparedStatement ps = conn.prepareStatement("UPDATE `users` SET `disabled` = ? WHERE `username` = ?");
		ps.setString(1, disable_value);
		ps.setString(2, username);
		ps.executeUpdate();

	    System.out.println("SQL: " + ps);
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public boolean updateSthWhereUNEquals(String username,HashMap<String,String> Map1) {
		try {
			Connection conn = ConnectionClass.getConnection();
			String sql ="UPDATE `users` SET %s = ? WHERE `username` = ?";
			PreparedStatement[] p = new PreparedStatement[Map1.size()];; 
			int i=0;

			for (Map.Entry<String,String> entry : Map1.entrySet()) {
				int j=0;
			    String key = entry.getKey();
			    String value = entry.getValue();
				String fmtSql = String.format(sql, key);
				
				p[i] = conn.prepareStatement(fmtSql);
				p[i].setString(1, value);
				p[i].setString(2, username);
				p[i++].executeUpdate();
			}
			conn.close();
		}catch(Exception e) {
				e.printStackTrace();
				return false;
		}
		return true;	
	}
	public boolean deleteUserWithRelateData(String username) {
		try {
		Connection conn =ConnectionClass.getConnection();
		PreparedStatement[] p = new PreparedStatement[4];
		 p[0] = conn.prepareStatement("DELETE FROM `donhang` WHERE `tentaikhoan` = ?");
		 p[1] = conn.prepareStatement("DELETE FROM `cart` WHERE `taikhoan` = ?");
		 p[2] = conn.prepareStatement("DELETE FROM `diachi` WHERE `taikhoan` = ?");
		 p[3] = conn.prepareStatement("DELETE FROM `users` WHERE `username` = ?");
		for(PreparedStatement currStatement : p ) {
			currStatement.setString(1,username);
			currStatement.executeUpdate();

		    System.out.println("SQL: " + p);
		}
		 conn.close();
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
		}

	public String getHashedPassword(String username) {
        // Logic to retrieve the hashed password from the database based on the username
        // Replace this with your actual implementation
        
        // Example code using a database query
        String hashedPassword = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
        	conn =ConnectionClass.getConnection();

            // Prepare the SQL statement
            String sql = "SELECT password FROM users WHERE username = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);

            // Execute the query
            rs = stmt.executeQuery();

            // Retrieve the hashed password from the result set
            if (rs.next()) {
                hashedPassword = rs.getString("password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hashedPassword;
    }
	
}
