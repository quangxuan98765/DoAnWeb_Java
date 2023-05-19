package DAO_model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.text.SimpleDateFormat;
import java.util.Date;
public class Order_DAO {
	public void updateStatus(String status,String id) {
		try {
			Connection conn = ConnectionClass.getConnection();
			String sql = "UPDATE donhang SET trangthai = ? WHERE id = ?";
			PreparedStatement p1 = conn.prepareStatement(sql);
			p1.setString(1, status);
			p1.setString(2, id);
			p1.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void delete(String id) {
		try {
			Connection conn = ConnectionClass.getConnection();
		    String sql1 = "DELETE FROM sl_sp_dh WHERE id_dh = ?";
		    String sql2 = "DELETE FROM donhang WHERE id = ?";
			PreparedStatement p1 = conn.prepareStatement(sql1);
			PreparedStatement p2 = conn.prepareStatement(sql2);
			p1.setString(1, id);
			p2.setString(1, id);
			p1.executeUpdate();
			p2.executeUpdate();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public List<HashMap<String,String>> getOrdersBySort(HashMap<String,String> sorts){
		List<HashMap<String,String>> list = new ArrayList<>();
		try {
			Connection conn = ConnectionClass.getConnection();
			String sqlSort = "";
			String sqlcond = "HAVING";
			String sql ="SELECT `donhang`.`id` AS `id`, `city`,`tentaikhoan`, `date`, `trangthai`, `payment`, SUM(`GiaSP` * `soluong`) AS `cost` "
						+"FROM `donhang` INNER JOIN `sl_sp_dh` ON `donhang`.`id` = `id_dh` "
						+"INNER JOIN `sanpham` ON `id_sp` = `sanpham`.`id` "
						+"INNER JOIN `diachi` ON `donhang`.`id_dc` = `diachi`.id AND `donhang`.`tentaikhoan` = `diachi`.`taikhoan` "
						+"GROUP BY `donhang`.`id`";
			if(sorts.get("sortType").equals("sortDate")) {
				sqlSort = " ORDER BY `date` " + sorts.get("sort");
				sorts.remove("sort");
				sorts.remove("sortType");
				sqlcond = sorts.size()!=0?" HAVING ":"";
			}else if(sorts.get("sortType").equals("sortStatus")) {
				if(sorts.get("sort").equals("not wait"))
		            sql += sqlcond + " `trangthai` != 'waiting' ";
		        else sql += sqlcond + " `trangthai` = '"+ sorts.get("sort")+"' ";
				sqlcond ="";
				sorts.remove("sort");
				sorts.remove("sortType");
		        sql += sorts.size()!=0?" AND ":"";
			}
			 if(sorts.get("searchDate")!=null){
				 Date currentDate = new Date();
				   
				 sorts.put("toDate",sorts.get("toDate")=="" ? new SimpleDateFormat("Y-m-d").format(currentDate):sorts.get("toDate"));
				 sorts.put("fromDate", sorts.get("fromDate")=="" ? "2000-01-01": sorts.get("fromDate"));
			      sql += sqlcond + " `date` BETWEEN '"+sorts.get("fromDate")+"' AND '"+sorts.get("toDate")+"' ";
			      sqlcond ="";
			      sorts.remove("searchDate");
					sorts.remove("toDate");
					sorts.remove("fromDate");
			        sql += sorts.size()!=0?" AND ": "";
			    }  
			  if(sorts.get("location")!=null)
			    sql += sqlcond + " `city` =  '"+sorts.get("location") + "' ";
			  sql+= sqlSort;
			  ResultSet results = conn.prepareStatement(sql).executeQuery();
				while (results.next()) {
			            HashMap<String, String> row = new HashMap<>();
			            row.put("id", results.getString("id"));
			            row.put("city", results.getString("city"));
			            row.put("tentaikhoan", results.getString("tentaikhoan"));
			            row.put("date", results.getString("date"));
			            row.put("trangthai", results.getString("trangthai"));
			            row.put("payment", results.getString("payment"));
			            row.put("cost", results.getString("cost"));
			            list.add(row);
			        }	
				conn.close();

			}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	public List<HashMap<String,String>> getOrderByID(String id){
		List<HashMap<String ,String>> orderDetailProductList = new ArrayList<HashMap<String,String>>();
		try {
			Connection conn = ConnectionClass.getConnection();
			String sql = "SELECT fullname, `donhang`.id as id, sdt, `date`, sonha, tenduong, city, trangthai, payment, GiaSP, HinhSP, TenSP, MoTaSP, soluong "
			           + "FROM `donhang` "
			           + "INNER JOIN `sl_sp_dh` ON `donhang`.`id` = `id_dh` "
			           + "INNER JOIN `sanpham` ON `id_sp` = `sanpham`.`id` "
			           + "INNER JOIN `users` ON `donhang`.tentaikhoan = `users`.username "
			           + "INNER JOIN `diachi` ON `donhang`.id_dc = `diachi`.id AND `donhang`.tentaikhoan = `diachi`.taikhoan "
			           + "WHERE `donhang`.id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				HashMap<String, String> orderDetails = new HashMap<>();
			    orderDetails.put("fullname", result.getString("fullname"));
			    orderDetails.put("id", result.getString("id"));
			    orderDetails.put("sdt", result.getString("sdt"));
			    orderDetails.put("date", result.getString("date"));
			    orderDetails.put("sonha", result.getString("sonha"));
			    orderDetails.put("tenduong", result.getString("tenduong"));
			    orderDetails.put("city", result.getString("city"));
			    orderDetails.put("trangthai", result.getString("trangthai"));
			    orderDetails.put("payment", result.getString("payment"));
			    orderDetails.put("GiaSP", result.getString("GiaSP"));
			    orderDetails.put("HinhSP", result.getString("HinhSP"));
			    orderDetails.put("TenSP", result.getString("TenSP"));
			    orderDetails.put("MoTaSP", result.getString("MoTaSP"));
			    orderDetails.put("soluong", result.getString("soluong"));
			    orderDetailProductList.add(orderDetails);
			}
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return orderDetailProductList;
	}	
	
	
	public List<String> getDistinctLocationFromOrders() {
	    List<String> list = new ArrayList<String>();
	    try {
	        Connection conn = ConnectionClass.getConnection();
	        String sql = "SELECT DISTINCT city FROM diachi";
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ResultSet rs = ps.executeQuery();
	        
	        while (rs.next()) {
	            list.add(rs.getString("city"));
	        }
	        
	        ps.close();
	        conn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return list;
	}
}
