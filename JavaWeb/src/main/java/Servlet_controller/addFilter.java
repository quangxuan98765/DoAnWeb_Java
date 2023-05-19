package Servlet_controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DAO_model.Brands_DAO;
import DAO_model.Category_DAO;
import java.util.HashMap;
import com.google.gson.*;

/**
 * Servlet implementation class addFilter
 */
@WebServlet("/addFilter")
public class addFilter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public addFilter() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    
		HashMap<String,String> cateMap = new Category_DAO().getCategories();
		HashMap<String,String> brandsMap = new Brands_DAO().getBrands();
		
		HashMap<String,HashMap> Map = new HashMap<String,HashMap>();
		Map.put("category", cateMap);
		Map.put("brands", brandsMap);
	    Gson gson = new Gson();
	    String jsonData = gson.toJson(Map);
	    response.getWriter().write(jsonData);
	}
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
//	}

}