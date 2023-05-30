package Servlet_controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;
import DAO_model.User_DAO;

@WebServlet("/XulyDangNhap")
public class XulyDangNhap extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public XulyDangNhap() {
        super();
        // TODO Auto-generated constructor stub
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("utf-8");
	    response.setCharacterEncoding("utf-8");

	    String username = request.getParameter("username");
	    String password = request.getParameter("password");

	    User_DAO t = new User_DAO();
	    String hashedPassword = t.getHashedPassword(username); // Retrieve the hashed password from the database based on the username

	    if (hashedPassword != null && BCrypt.checkpw(password, hashedPassword)) {
	        HttpSession session = request.getSession();
	        session.setAttribute("username", username);

	        if (t.isAdmin(username)) {
	            session.setAttribute("role", "admin");
	        } else {
	            session.setAttribute("role", "normal");
	        }
	        response.sendRedirect("Index");
	    } else {
	        String error = "Sai tên đăng nhập hoặc mật khẩu";
	        request.setAttribute("error", error);
	        request.getRequestDispatcher("XulyDangNhap").include(request, response);
	    }
	}


}
