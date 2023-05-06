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

import javax.servlet.http.Part;

public class Product_DAO {
	public boolean insert(Object obj) {
		Product_model p = (Product_model) obj;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = ConnectionClass.getConnection();
			int loaiSP = p.getcategory_name();
			int brand = p.getbrand_name();

			String query = "SELECT * FROM category WHERE category_name=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, loaiSP);
			ResultSet rs = ps.executeQuery();
			int category_id = rs.getInt("id");
			
			String query1 = "SELECT * FROM category WHERE brand_name=?";
			PreparedStatement ps1 = conn.prepareStatement(query1);
			ps.setInt(1, brand);
			ResultSet rs1 = ps1.executeQuery();
			int brand_id = rs1.getInt("id");
			
			Statement stmt = conn.createStatement();
			String sql = "INSERT INTO sanpham (MaSP ,TenSP, HinhSP, MoTaSP, GiaSP, category_id, brand_id) VALUES ('" + p.getMasp() + "', '" + p.getTensp() + "', '" + p.getHinhsp() +  "', '" + p.getMotasp() +  "', '" + p.getGiasp() +  "', '" + category_id +  "', '" + brand_id + "')";
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
	
	public boolean update(Object obj, int id) {
		Product_model p = (Product_model) obj;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = ConnectionClass.getConnection();
			int loaiSP = p.getcategory_name();
			int brand = p.getbrand_name();
			
			String query = "SELECT * FROM category WHERE category_name=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, loaiSP);
			ResultSet rs = ps.executeQuery();
			int category_id = rs.getInt("id");
			
			String query1 = "SELECT * FROM category WHERE brand_name=?";
			PreparedStatement ps1 = conn.prepareStatement(query1);
			ps.setInt(1, brand);
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
			Class.forName("com.mysql.cj.jdbc.Driver");
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
	
	public boolean uploadHinh(String hinhSP, Part filePart) {
		String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // lấy tên tệp đính kèm
		// thư mục đích để lưu trữ tệp được tải lên
		String uploadDir = "/path/JavaWeb/src/main/webapp/img/product/"; // thay đổi đường dẫn đến thư mục tải lên của bạn 
//		Đường dẫn tuyệt đối: /home/user/myproject/img/product/
//		Đường dẫn tương đối: ../img/product/
		
		// kiểm tra và tạo thư mục đích nếu nó không tồn tại
		File uploadDirFile = new File(uploadDir);
		if (!uploadDirFile.exists()) {
		    uploadDirFile.mkdir();
		}

		// tạo đường dẫn đầy đủ của tệp được tải lên
		String filePath = uploadDir + File.separator + fileName;
		File uploadedFile = new File(filePath);

		try {
		    // ghi tệp vào đĩa
		    InputStream input = filePart.getInputStream();
		    Files.copy(input, uploadedFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
		    input.close();
		} catch (IOException e) {
		    e.printStackTrace();
		    return false;
		}

		// lưu đường dẫn tệp vào biến hinhSP
		hinhSP = filePath;

		return true;
	}

}