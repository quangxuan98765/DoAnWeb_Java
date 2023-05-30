package Servlet_controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.google.gson.Gson;

import DAO_model.Cart_DAO;
import DAO_model.Product_model;

/**
 * Servlet implementation class deleteCart
 */
@WebServlet("/deleteCart")
public class deleteCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
	    response.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
	    String username = (String) session.getAttribute("username");
	    Cart_DAO pdao = new Cart_DAO();
	    
	    pdao.delete(request.getParameter("masp"), username);
	    List<Product_model> plist = pdao.getAllProduct(username);
	    
	    JSONArray remainingItemsArray = new JSONArray();

		 // Duyệt qua danh sách các sản phẩm còn lại và tạo đối tượng JSON cho mỗi sản phẩm
		 for (Product_model product : plist) {
		     JSONObject productJson = new JSONObject();
		     try {
		         productJson.put("masp", product.getMasp());
		         productJson.put("tensp", product.getTensp());
		         productJson.put("hinhsp", product.getHinhsp());
		         productJson.put("motasp", product.getMotasp());
		         productJson.put("giasp", product.getGiasp());
		         productJson.put("soluong", product.getSoluong());
	
		         remainingItemsArray.put(productJson);
		     } catch (JSONException e) {
		         e.printStackTrace();
		     }
		 }
//		 Gson gson = new Gson(); //sd Gson thì trả về 1 chuỗi {} (có thể sử dụng products = (number) kiễu int và product == null)
//		 String json = gson.toJson(remainingItemsArray);
		 String json = remainingItemsArray.toString(); //sài JSON này thì trả về là 1 mảng [] (có thể sử dụng products.length, products.length == 0)
		 response.setContentType("application/json");
		 response.setCharacterEncoding("UTF-8");
		 response.getWriter().write(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
