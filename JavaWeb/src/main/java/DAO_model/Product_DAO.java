package DAO_model;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.Part;

public class Product_DAO {
	public boolean insert(Object obj, List<Part> fileParts) {
		Product_model p = (Product_model) obj;
		try {
			Connection conn = ConnectionClass.getConnection();
			String loaiSP = p.getcategory_name();
			String brand = p.getbrand_name();

			String query = "SELECT * FROM category WHERE category_name=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, loaiSP);
			ResultSet rs = ps.executeQuery();
			int category_id = rs.getInt("id");
			
			String query1 = "SELECT * FROM category WHERE brand_name=?";
			PreparedStatement ps1 = conn.prepareStatement(query1);
			ps.setString(1, brand);
			ResultSet rs1 = ps1.executeQuery();
			int brand_id = rs1.getInt("id");

			// Upload hình sản phẩm và lưu đường dẫn vào danh sách hình SP
			List<String> hinhSPs = new ArrayList<>();
			boolean uploadSuccess = uploadHinh(hinhSPs, fileParts);

			if (uploadSuccess) {
				// Thêm sản phẩm vào database
				String sql = "INSERT INTO sanpham (MaSP ,TenSP, HinhSP, MoTaSP, GiaSP, more_img, more_img1, more_img2, category_id, brand_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, p.getMasp());
				pstmt.setString(2, p.getTensp());
				pstmt.setString(3, hinhSPs.get(0)); // Lấy đường dẫn ảnh đầu tiên
				pstmt.setString(4, p.getMotasp());
				pstmt.setDouble(5, p.getGiasp());
				pstmt.setString(6, hinhSPs.get(1));
				pstmt.setString(7, hinhSPs.get(2));
				pstmt.setString(8, hinhSPs.get(3));
				pstmt.setInt(9, category_id);
				pstmt.setInt(10, brand_id);
				pstmt.executeUpdate();
				pstmt.close();
				conn.close();
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	
	public boolean update(Object obj, int id) {
		Product_model p = (Product_model) obj;
		try {
			Connection conn = ConnectionClass.getConnection();
			String loaiSP = p.getcategory_name();
			String brand = p.getbrand_name();
			
			String query = "SELECT * FROM category WHERE category_name=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, loaiSP);
			ResultSet rs = ps.executeQuery();
			int category_id = rs.getInt("id");
			
			String query1 = "SELECT * FROM category WHERE brand_name=?";
			PreparedStatement ps1 = conn.prepareStatement(query1);
			ps.setString(1, brand);
			ResultSet rs1 = ps1.executeQuery();
			int brand_id = rs1.getInt("id");
			
			Statement stmt = conn.createStatement();
			String hinh = p.getHinhsp();
			if(hinh == "") {
				String sql = "UPDATE sanpham SET MaSP='" + p.getMasp() + "', TenSP='" + p.getTensp() + "', MoTaSP='" + p.getMotasp() + "', GiaSP='" + p.getGiasp() + "', category_id='" + category_id + "', brand_id='" + brand_id + "' WHERE id=" + id;
				stmt.executeUpdate(sql);
			}
			else {
				String sql = "UPDATE sanpham SET MaSP='" + p.getMasp() + "', TenSP='" + p.getTensp() + "', HinhSP='" + p.getHinhsp() + "', MoTaSP='" + p.getMotasp() + "', GiaSP='" + p.getGiasp() + "', category_id='" + category_id + "', brand_id='" + brand_id + "' WHERE id=" + id;
				stmt.executeUpdate(sql);
			}
			stmt.close();
			conn.close();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean delete(int id) {
		try {
			Connection conn = ConnectionClass.getConnection();
			Statement stmt = conn.createStatement();
			String sql = "DELETE FROM sanpham WHERE sanpham.id = " + id;
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
	
	public boolean uploadHinh(List<String> hinhSPs, List<Part> fileParts) {
	    // kiểm tra và tạo thư mục đích nếu nó không tồn tại
	    String uploadDir = "/path/JavaWeb/src/main/webapp/img/product/"; // thay đổi đường dẫn đến thư mục tải lên của bạn 
	    File uploadDirFile = new File(uploadDir);
	    if (!uploadDirFile.exists()) {
	        uploadDirFile.mkdir();
	    }

	    // upload từng file
	    boolean allSuccess = true;
	    for (Part filePart : fileParts) {
	        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // lấy tên tệp đính kèm
	        
	        // tạo đường dẫn đầy đủ của tệp được tải lên
	        String filePath = uploadDir + File.separator + fileName;
	        File uploadedFile = new File(filePath);

	        try {
	            // ghi tệp vào đĩa
	            InputStream input = filePart.getInputStream();
	            Files.copy(input, uploadedFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
	            input.close();

	            // lưu đường dẫn tệp vào biến hinhSPs
	            hinhSPs.add(filePath);
	        } catch (IOException e) {
	            e.printStackTrace();
	            allSuccess = false;
	        }
	    }

	    return allSuccess;
	}

	
	public List<Product_model> getAllProduct() {
		try {
			List<Product_model> plist = new ArrayList<>();
			Connection conn = ConnectionClass.getConnection();
			String sql = "SELECT * FROM sanpham";
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				plist.add(new Product_model(rs.getString("MaSP"), rs.getString("TenSP"), rs.getString("HinhSP"), rs.getString("MoTaSP"), rs.getString("more_img"), rs.getString("more_img1"), rs.getString("more_img2"), rs.getInt("GiaSP"), rs.getInt("category_id"), rs.getInt("brand_id")));
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