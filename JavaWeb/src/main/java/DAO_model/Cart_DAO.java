package DAO_model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

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
	
	public boolean DatHang(String tk, String pay, String id_dc, String sdt) {
		try {
			java.util.Date currentDate = new java.util.Date();
			java.sql.Date sqlDate = new java.sql.Date(currentDate.getTime());
			
	        Connection conn = ConnectionClass.getConnection();
	        String sql = "INSERT INTO donhang (tentaikhoan, date, payment, id_dc, sdt, trangthai) VALUES (?, ?, ?, ?, ?, 'waiting')";
	        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	        ps.setString(1, tk);
	        ps.setDate(2, sqlDate);
	        ps.setString(3, pay);
	        ps.setString(4, id_dc);
	        ps.setString(5, sdt);
	        ps.executeUpdate();
	        
	        ResultSet generatedKeys = ps.getGeneratedKeys();
	        if (generatedKeys.next()) {
	            int newid_dh = generatedKeys.getInt(1);
	            String query = "SELECT * FROM cart,sanpham WHERE cart.masp = sanpham.MaSP and taikhoan = ?";
	            PreparedStatement stmt = conn.prepareStatement(query);
	            stmt.setString(1, tk);
	            ResultSet rs = stmt.executeQuery();
	            int result = 0;
	            while(rs.next()) {
	            	int idsp = rs.getInt("id");
	                int sl = rs.getInt("soluong");
	                String query1 = "INSERT INTO sl_sp_dh (id_dh, id_sp, soluong) VALUES (?, ?, ?)";
	                PreparedStatement statement = conn.prepareStatement(query1);
	                statement.setInt(1, newid_dh);
	                statement.setInt(2, idsp);
	                statement.setInt(3, sl);
	                result = statement.executeUpdate();
	                statement.close();
	            }
	            if (result > 0) {
	                String del = "DELETE FROM cart WHERE cart.taikhoan = ?";
	                PreparedStatement statement = conn.prepareStatement(del);
	                statement.setString(1, tk);
	                statement.close();
	            } else 
	                System.out.println("Error executing query: " + query);
	        }
	        ps.close();
	        conn.close();
		} catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	    return true;
	}
	
	public Map<String, Object> getHistoryCart(String taikhoan) {
	    try {
	        Connection conn = ConnectionClass.getConnection();
	        String sql1 = "SELECT id FROM donhang WHERE tentaikhoan = ?";
	        PreparedStatement ps1 = conn.prepareStatement(sql1);
	        ps1.setString(1, taikhoan);
	        ResultSet rs1 = ps1.executeQuery();

	        Map<Integer, Integer> id_1st_in_dh = new HashMap<>();
	        while (rs1.next()) {
	            int id = rs1.getInt("id");
	            String sql2 = "SELECT id_sp FROM sl_sp_dh WHERE id_dh = ?";
	            PreparedStatement ps2 = conn.prepareStatement(sql2);
	            ps2.setInt(1, id);
	            ResultSet rs2 = ps2.executeQuery();
	            int t = 0;
	            if (rs2.next()) {
	                t = rs2.getInt("id_sp");
	            }
	            id_1st_in_dh.put(id, t);
	        }

	        String sql3 = "SELECT id_dh, id_sp, HinhSP, MaSP, TenSP, MoTaSP, soluong, GiaSP, date,trangthai " +
	                      "FROM donhang " +
	                      "INNER JOIN sl_sp_dh ON donhang.id = sl_sp_dh.id_dh " +
	                      "INNER JOIN sanpham ON sl_sp_dh.id_sp = sanpham.id " +
	                      "WHERE donhang.tentaikhoan = ?";
	        PreparedStatement ps3 = conn.prepareStatement(sql3);
	        ps3.setString(1, taikhoan);
	        ResultSet rs3 = ps3.executeQuery();

	        List<Map<String, Object>> data_sp = new ArrayList<>();
	        while (rs3.next()) {
	            Map<String, Object> row = new HashMap<>();
	            row.put("id_dh", rs3.getInt("id_dh"));
	            row.put("HinhSP", rs3.getString("HinhSP"));
	            row.put("MaSP", rs3.getString("MaSP"));
	            row.put("TenSP", rs3.getString("TenSP"));
	            row.put("MoTaSP", rs3.getString("MoTaSP"));
	            row.put("soluong", rs3.getInt("soluong"));
	            row.put("GiaSP", rs3.getDouble("GiaSP"));
	            row.put("date", rs3.getString("date"));
	            row.put("trangthai", rs3.getString("trangthai"));
	            data_sp.add(row);
	        }

	        String sql4 = "SELECT id_dh, SUM(GiaSP*soluong) as GiaDH " +
	                      "FROM donhang " +
	                      "INNER JOIN sl_sp_dh ON donhang.id = sl_sp_dh.id_dh " +
	                      "INNER JOIN sanpham ON sl_sp_dh.id_sp = sanpham.id " +
	                      "WHERE donhang.tentaikhoan = ? " +
	                      "GROUP BY id_dh";
	        PreparedStatement ps4 = conn.prepareStatement(sql4);
	        ps4.setString(1, taikhoan);
	        ResultSet rs4 = ps4.executeQuery();

	        Map<Integer, Double> dh_cost = new HashMap<>();
	        while (rs4.next()) {
	            int id_dh = rs4.getInt("id_dh");
	            double gia_dh = rs4.getDouble("GiaDH");
	            dh_cost.put(id_dh, gia_dh);
	        }

	        Map<String, Object> exdata = new HashMap<>();
	        exdata.put("dh_cost", dh_cost);
	        exdata.put("id_1st_sp", id_1st_in_dh);
	        exdata.put("data_sp", data_sp);
	        return exdata;
	    } catch (Exception e) {
	        System.out.println("ERROR: " + e.getMessage());
	    }
	    return null;
	}

	
//	public boolean HuyDon() {
//		
//	}

}
