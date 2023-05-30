package Servlet_controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO_model.User_DAO;
import DAO_model.User_model;

/**
 * Servlet implementation class editLocation
 */
@WebServlet("/editLocation")
public class editLocation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String getid;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public editLocation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		User_DAO p = new User_DAO();
		getid = request.getParameter("idc");
		User_model sp = p.getDCByid(getid);
		request.setAttribute("dc", sp);
		this.getServletContext().getRequestDispatcher("/editLocaton.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
	    String username = (String) session.getAttribute("username");
	    String city = request.getParameter("city");
	    String road = request.getParameter("duong");
	    String house = request.getParameter("nha");
	    User_model ud = new User_model(city, road, house);
	    new User_DAO().SuaDC(ud, getid, username);
	    response.sendRedirect("Cart");
	}

}
