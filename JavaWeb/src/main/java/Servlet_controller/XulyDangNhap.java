package Servlet_controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
		
		response.setContentType("text/html;charset=UTF-8"); // thiết lập kiểu dữ liệu trả về cho phần mềm duyệt web là HTML. Điều này đảm bảo rằng phần mềm duyệt web sẽ hiểu được dữ liệu trả về từ Servlet là HTML và hiển thị nó đúng cách.
        //PrintWriter out = response.getWriter();// để ghi các thẻ HTML và các nội dung khác vào phần thân của trang web

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        
        if (new User_DAO().checkLogin(username, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            //out.println("true");
            response.sendRedirect("Index");
        } else {
            String erorr = "Sai tên đăng nhập hoặc mật khẩu";
            request.setAttribute("error", erorr);
            request.getRequestDispatcher("XulyDangNhap").include(request, response);
        }
	}

}
