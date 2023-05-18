package Servlet_controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.*;
import DAO_model.*;
/**
 * Servlet implementation class User
 */
@WebServlet("/User")
public class User extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public User() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String role = request.getParameter("role");
	    String jsonData = new Gson().toJson( new User_DAO().getUsersByRole(role));
	    response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(jsonData);
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
	     HashMap<String,String>  map = gson.fromJson(jsonData, HashMap.class);
	     String action = map.get("action");

			System.out.println("\naction\n" +action +"\n");
	     String username = map.get("username");
	     map.remove("action");
	     map.remove("username");
	     String thisResponse = "Error!";
	     if(action.equals("disable")) {
			    System.out.println("\nSQL: 1");
	    	 if(new User_DAO().updateDisabled(username,String.valueOf(map.get("disable_value"))  ) )
	    		 thisResponse = "Updated";
	     }
	     else if (action.equals("edit")) {

			    System.out.println("\nSQL: 2" );
	    	 if(new User_DAO().updateSthWhereUNEquals(username, map))
	    		 thisResponse = "Updated";

	     }
		 else if (action.equals("delete")) {
			    System.out.println("\nSQL: 3");
			 if(new User_DAO().deleteUserWithRelateData(username))
	     		thisResponse = "Deleted"; }
	    response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(thisResponse);
	}

}