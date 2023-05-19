package Servlet_controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DAO_model.*;
import java.util.HashMap;
import java.util.List;
import com.google.gson.*;

@WebServlet("/LaptopProduct")
public class LaptopProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LaptopProduct() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String jsonData = request.getParameter("data");
	     Gson gson = new Gson();
	     HashMap<String,String>  Map = gson.fromJson(jsonData, HashMap.class);
	     Product_DAO product_dao = new Product_DAO();
	     List<Product_model> list = product_dao.getProductsByFilter(Map);
	     String json = new Gson().toJson(list);
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
