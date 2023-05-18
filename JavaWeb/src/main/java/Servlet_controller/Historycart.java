package Servlet_controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

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
	    
	    HttpSession session = request.getSession();
	    String username = (String) session.getAttribute("username");
	    Cart_DAO pdao = new Cart_DAO();
	    
	    // Thực hiện lấy dữ liệu và xử lý
	    Map<String, Object> data = pdao.getHistoryCart(username);
	    
	    // Chuyển đổi dữ liệu thành chuỗi JSON
	    String json = convertToJson(data);
	    
	    // Gửi chuỗi JSON về phía client
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(json);
	}

	// Phương thức để chuyển đổi dữ liệu thành chuỗi JSON
	private String convertToJson(Map<String, Object> data) {
	    StringBuilder jsonBuilder = new StringBuilder();
	    jsonBuilder.append("{");
	    boolean first = true;
	    for (Map.Entry<String, Object> entry : data.entrySet()) {
	        if (!first) {
	            jsonBuilder.append(",");
	        } else {
	            first = false;
	        }
	        String key = entry.getKey();
	        Object value = entry.getValue();
	        jsonBuilder.append("\"").append(key).append("\":");
	        if (value instanceof String) {
	            jsonBuilder.append("\"").append(value).append("\"");
	        } else if (value instanceof Number || value instanceof Boolean) {
	            jsonBuilder.append(value);
	        } else {
	            // Handle other complex types as needed
	            jsonBuilder.append("null");
	        }
	    }
	    jsonBuilder.append("}");
	    return jsonBuilder.toString();
	}


}
