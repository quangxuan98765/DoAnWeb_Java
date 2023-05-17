package DAO_model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Cart_DAO {
	public boolean addSP(String id, String tentaikhoan) {
	    try {
	        Connection conn = ConnectionClass.getConnection();
	        String sql = "SELECT * FROM sanpham WHERE id = ?";
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setString(1, id);
	        ResultSet rs = ps.executeQuery();
	        String masp = "";
	        if (rs.next()) {
	            masp = rs.getString("MaSP");
	        }

	        int soluong = 1;

	        String sql1 = "SELECT * FROM cart WHERE MaSP = ? AND taikhoan = ?";
	        PreparedStatement ps1 = conn.prepareStatement(sql1);
	        ps1.setString(1, masp);
	        ps1.setString(2, tentaikhoan);
	        ResultSet rs1 = ps1.executeQuery();
	        if (rs1.next()) {
	            // Đã có dòng kết quả trong ResultSet, thực hiện câu UPDATE
	            String sql2 = "UPDATE cart SET soluong = soluong + 1 WHERE masp = ? AND taikhoan = ?";
	            PreparedStatement ps2 = conn.prepareStatement(sql2);
	            ps2.setString(1, masp);
	            ps2.setString(2, tentaikhoan);
	            ps2.executeUpdate();
	        } else {
	            // Không có dòng kết quả trong ResultSet, thực hiện câu INSERT
	            String sql2 = "INSERT INTO cart (taikhoan, masp, soluong) VALUES (?, ?, ?)";
	            PreparedStatement ps3 = conn.prepareStatement(sql2);
	            ps3.setString(1, tentaikhoan);
	            ps3.setString(2, masp);
	            ps3.setInt(3, soluong);
	            ps3.executeUpdate();
	        }

	        conn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	    return true;
	}
	
	public boolean delete(String ma, String tk) {
		try {
			Connection conn = ConnectionClass.getConnection();
			String sql = "DELETE FROM cart WHERE masp = ? AND taikhoan = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, ma);
			stmt.setString(2, tk);
			stmt.executeUpdate();
			stmt.close();
			conn.close();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		
	}

	public List<Product_model> getAllProduct(String tentaikhoan) {
		try {
			List<Product_model> plist = new ArrayList<>();
			Connection conn = ConnectionClass.getConnection();
			String sql = "SELECT * FROM users,cart,sanpham WHERE  cart.masp = sanpham.MaSP and taikhoan = username and taikhoan = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, tentaikhoan);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				plist.add(new Product_model(rs.getString("MaSP"), rs.getString("TenSP"), rs.getString("HinhSP"), rs.getString("MoTaSP"), rs.getString("more_img"), rs.getString("more_img1"), rs.getString("more_img2"), rs.getDouble("GiaSP"), rs.getInt("brand_id"), rs.getInt("category_id") , rs.getInt("soluong")));
			}
			stmt.close();
			conn.close();
			return plist;
		} catch (Exception ex) {
			// TODO: handle exception
			System.out.println("ERROR: " + ex.getMessage());
		}
		return null;
	}

}
