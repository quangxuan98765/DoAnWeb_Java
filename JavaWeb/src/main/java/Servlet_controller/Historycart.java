package Servlet_controller;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
import com.google.gson.Gson;

import DAO_model.Order_DAO;
import DAO_model.Cart_DAO;

/**
 * Servlet implementation class Historycart
 */
@WebServlet("/Historycart")
public class Historycart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Historycart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.getServletContext().getRequestDispatcher("/historycart.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("utf-8");
	    response.setCharacterEncoding("utf-8");
	    
	    StringBuilder sb = new StringBuilder();
	    BufferedReader reader = request.getReader();
	    String line;
	    while ((line = reader.readLine()) != null) {
	        sb.append(line);
	    }
	    reader.close();
	    
	    String jsonData = sb.toString();
	     HashMap<String,String>  map = new Gson().fromJson(jsonData, HashMap.class);
	     String action = map.get("action");
	    if(action.equals("Delete")) 
	    	new Order_DAO().delete(map.get("id_dh"));
	    else {
	    HttpSession session = request.getSession();
	    String username = (String) session.getAttribute("username");
	    Cart_DAO pdao = new Cart_DAO();
	    Map<String, Object> data = pdao.getHistoryCart(username);
	    String json = new Gson().toJson(data);
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(json);}
	}


}
