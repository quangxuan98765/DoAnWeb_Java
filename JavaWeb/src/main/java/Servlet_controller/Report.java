package Servlet_controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import DAO_model.ConnectionClass;
import DAO_model.Order_DAO;
import DAO_model.Product_DAO;
import DAO_model.Product_model;

/**
 * Servlet implementation class Report
 */
@WebServlet("/Report")
public class Report extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Report() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String tong = "0";
		try {

			String jsonData = request.getParameter("data");
			Connection conn = ConnectionClass.getConnection();
			Gson gson = new Gson();
			HashMap<String, String> Map = gson.fromJson(jsonData, HashMap.class);
			
			String month = (String) Map.get("month");
			String year = (String) Map.get("year");
			String sql = "SELECT SUM(GiaSP * soluong) AS total FROM `donhang` "
					+ "INNER JOIN `sl_sp_dh` ON `donhang`.`id` = `id_dh` "
					+ "INNER JOIN `sanpham` ON `id_sp` = `sanpham`.`id` "
					+ "WHERE "+ (!month.equals("0") ? "MONTH(`date`) = ? AND " : "") + "YEAR(`date`) = ? AND trangthai = 'confirmed'";
			PreparedStatement statement = conn.prepareStatement(sql);
			if(month.equals("0"))
				statement.setString(1, year);
			else {
				statement.setString(1, month);
				statement.setString(2, year);
			}
			
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				tong = result.getString("total");
			}
			List<HashMap<String,String>> orderList= new Order_DAO().getOrderByYearAndMonth(month,year); 
			HashMap<String,Object> Map1 = new HashMap<String,Object>();
			Map1.put("tong",tong);
			Map1.put("data", orderList);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(new Gson().toJson(Map1));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
