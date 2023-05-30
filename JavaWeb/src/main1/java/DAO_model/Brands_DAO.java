package DAO_model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
public class Brands_DAO {
	public HashMap<String,String> getBrands(){
		HashMap<String,String> newMap = new HashMap<String,String>();
		try {
			Connection conn = ConnectionClass.getConnection();
			String sql = "SELECT * FROM brands ";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet s = ps.executeQuery();
			while (s.next()) {
				newMap.put(s.getString("id"),s.getString("brand_name"));
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newMap;
	}
}