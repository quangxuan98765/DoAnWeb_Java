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
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.Part;

public class Product_DAO {
	public boolean insert(Object obj, List<String> hinhSPs) {
		Product_model p = (Product_model) obj;
		try {
			Connection conn = ConnectionClass.getConnection();
			String loaiSP = p.getcategory_name();
			String brand = p.getbrand_name();

			String query = "SELECT * FROM category WHERE category_name=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, loaiSP);
			ResultSet rs = ps.executeQuery();
			int category_id = -1;
			if (rs.next()) { // Move cursor to first row
			    category_id = rs.getInt("id");
			}

			String query1 = "SELECT * FROM brands WHERE brand_name=?";
			PreparedStatement ps1 = conn.prepareStatement(query1);
			ps1.setString(1, brand);
			ResultSet rs1 = ps1.executeQuery();
			int brand_id = -1;
			if (rs1.next()) { // Move cursor to first row
			    brand_id = rs1.getInt("id");
			}

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
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	
	public boolean update(Object obj, String id, List<String> hinhSPs) {
		Product_model p = (Product_model) obj;
		try {
			Connection conn = ConnectionClass.getConnection();
			String loaiSP = p.getcategory_name();
			String brand = p.getbrand_name();
			
			String query = "SELECT * FROM category WHERE category_name=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, loaiSP);
			ResultSet rs = ps.executeQuery();
			int category_id = -1;
			if (rs.next()) { // Move cursor to first row
			    category_id = rs.getInt("id");
			}

			String query1 = "SELECT * FROM brands WHERE brand_name=?";
			PreparedStatement ps1 = conn.prepareStatement(query1);
			ps1.setString(1, brand);
			ResultSet rs1 = ps1.executeQuery();
			int brand_id = -1;
			if (rs1.next()) { // Move cursor to first row
			    brand_id = rs1.getInt("id");
			}
			
			Statement stmt = conn.createStatement();
			if(hinhSPs.isEmpty()) {
				String sql = "UPDATE sanpham SET MaSP='" + p.getMasp() + "', TenSP='" + p.getTensp() + "', MoTaSP='" + p.getMotasp() + "', GiaSP='" + p.getGiasp() + "', category_id='" + category_id + "', brand_id='" + brand_id + "' WHERE id= '" + id + "'";
				stmt.executeUpdate(sql);
			}
			else {
				String sql = "UPDATE sanpham SET MaSP='" + p.getMasp() + "', TenSP='" + p.getTensp() + "', HinhSP='" + hinhSPs.get(0) + "', MoTaSP='" + p.getMotasp() + "', GiaSP='" + p.getGiasp() + "', more_img='" + hinhSPs.get(1) + "', more_img1='" + hinhSPs.get(2) + "', more_img2='" + hinhSPs.get(3) + "', category_id='" + category_id + "', brand_id='" + brand_id + "' WHERE id= '" + id + "'";
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
	
	public boolean delete(String id) {
		try {
			Connection conn = ConnectionClass.getConnection();
			Statement stmt = conn.createStatement();
			String sql = "DELETE FROM sanpham WHERE sanpham.id = '" + id + "'";
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
	    String uploadDir = "C:\\Users\\ACER\\eclipse-workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\JavaWeb\\img\\product"; // thay đổi đường dẫn đến thư mục tải lên của bạn 
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
	
	public Product_model get(String ma) {
		try {
			Connection conn = ConnectionClass.getConnection();
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM sanpham WHERE MaSP = '" + ma + "'";
			ResultSet rs = stmt.executeQuery(sql);
			Product_model p = new Product_model();
			if(rs.next()) {
				p.setId(rs.getInt("id"));
				p.setTensp(rs.getString("TenSP"));
				p.setMotasp(rs.getString("MoTaSP"));
				p.setGiasp(rs.getDouble("GiaSP"));
				p.setHinhsp(rs.getString("HinhSP"));
				p.setImg1(rs.getString("more_img"));
				p.setIgm2(rs.getString("more_img1"));
				p.setImg3(rs.getString("more_img2"));
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
	
	public Product_model getByid(String id) {
		try {
			Connection conn = ConnectionClass.getConnection();
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM sanpham WHERE id = '" + id + "'";
			ResultSet rs = stmt.executeQuery(sql);
			Product_model p = new Product_model();
			if(rs.next()) {
				p.setId(Integer.parseInt(id));
				p.setMasp(rs.getString("MaSP"));
				p.setTensp(rs.getString("TenSP"));
				p.setMotasp(rs.getString("MoTaSP"));
				p.setGiasp(rs.getDouble("GiaSP"));
				p.setHinhsp(rs.getString("HinhSP"));
				p.setImg1(rs.getString("more_img"));
				p.setIgm2(rs.getString("more_img1"));
				p.setImg3(rs.getString("more_img2"));
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

	
	public List<Product_model> getAllProduct() {
		try {
			List<Product_model> plist = new ArrayList<>();
			Connection conn = ConnectionClass.getConnection();
			String sql = "SELECT * FROM sanpham";
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				plist.add(new Product_model(rs.getInt("id"), rs.getString("MaSP"), rs.getString("TenSP"), rs.getString("HinhSP"), rs.getString("MoTaSP"), rs.getString("more_img"), rs.getString("more_img1"), rs.getString("more_img2"), rs.getDouble("GiaSP"), rs.getInt("brand_id"), rs.getInt("category_id")));
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
	
	public List<Product_model> getProductsByFilter(HashMap<String,String> filters){
		List<Product_model> list = new ArrayList<>();
		try {
			Connection conn = ConnectionClass.getConnection();
			ArrayList<String> value = new ArrayList<String>();
			String sql = "SELECT * FROM sanpham";
			String setSort = " ";
			if(filters.get("sort") != null) {
				setSort += "ORDER BY GiaSP " + filters.get("sort");
				filters.remove("sort");
			}
			if(filters.size() >= 1) {	
				sql+=" WHERE";
				if(filters.get("GiaSP") != null) {
					sql+= " "+filters.get("GiaSP") + " AND";
					filters.remove("GiaSP");
				} if (filters.get("searchValue") != null) {
					sql += " LOWER(TenSP) LIKE ? AND";
					value.add("%" + filters.get("searchValue").toLowerCase() + "%");
					filters.remove("searchValue");
				}
				 for (String key : filters.keySet()) {
					
					 sql +=" "+ key + "=? AND" ;
					 value.add(filters.get(key));	 
				 }
				sql = sql.substring(0,sql.length()-4);
			}
			sql += setSort;
			PreparedStatement ps = conn.prepareStatement(sql);
			for (int i=0;i<value.size(); i++) 
				ps.setString(i+1,value.get(i));
		
			System.out.println("S: " +ps);
			ResultSet s = ps.executeQuery();
			while (s.next()) 
				list.add(new Product_model(s.getString("MaSP"), s.getString("TenSP"), s.getString("HinhSP"), s.getString("MoTaSP"), s.getString("more_img"), s.getString("more_img1"), s.getString("more_img2"), s.getInt("GiaSP"), s.getInt("category_id"), s.getInt("brand_id")));
			ps.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}