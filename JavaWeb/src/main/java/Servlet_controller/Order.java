package Servlet_controller;

import java.io.IOException;
import java.util.HashMap;
import java.io.BufferedReader;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import com.google.gson.Gson;

import DAO_model.*;

/**
 * Servlet implementation class Order
 */
@WebServlet("/Order")
public class Order extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Order() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/order.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuilder sb = new StringBuilder();
	    BufferedReader reader = request.getReader();
	    String line;
	    while ((line = reader.readLine()) != null) {
	        sb.append(line);
	    }
	    reader.close();
	    
	    String jsonData = sb.toString();
	     Gson gson = new Gson();
	     System.out.print(jsonData);
	     HashMap<String,String>  Map = gson.fromJson(jsonData, HashMap.class);
	     String action = Map.get("action");
	     Map.remove("action");
	     List<HashMap<String,String>> orderList = new ArrayList<HashMap<String,String>>();
	     Order_DAO  newOrder = new Order_DAO();
	     if(action.equals("changeStatus"))
	    	    newOrder.updateStatus(Map.get("status"), Map.get("id"));
	    	else if(action.equals("sort"))	
	    	    orderList = newOrder.getOrdersBySort(Map);
	     
	     String json = new Gson().toJson(orderList);
	     response.setContentType("application/json");
	     response.setCharacterEncoding("UTF-8");
	     response.getWriter().write(json);
	}

}