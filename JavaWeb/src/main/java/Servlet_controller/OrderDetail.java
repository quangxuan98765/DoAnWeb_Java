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

import java.util.ArrayList;
import java.util.Base64;
/**
 * Servlet implementation class OrderDetail
 */
@WebServlet("/OrderDetail")
public class OrderDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderDetail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/OrderDetail.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id_dh_encoded = request.getParameter("iddh");
		byte[] decodedBytes = Base64.getDecoder().decode(id_dh_encoded);
		String id_dh = new String(decodedBytes, "UTF-8");
		List<HashMap<String ,String>> list = new ArrayList<HashMap<String,String>>();
		list =  new Order_DAO().getOrderByID(id_dh);
		String json = new Gson().toJson(list);
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
		System.out.println(json);
		response.getWriter().write(json);
	}

}