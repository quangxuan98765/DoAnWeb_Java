package Servlet_controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO_model.User_DAO;
import DAO_model.User_model;

@WebServlet("/XulyDangKy")
public class XulyDangKy extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public XulyDangKy() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Trả về trang đăng ký tài khoản
		this.getServletContext().getRequestDispatcher("/sigup.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String hoten= request.getParameter("hoten");
		String mail= request.getParameter("email");
		String tk= request.getParameter("username");
		String mk= request.getParameter("password");
		System.out.println(hoten + " " + mail + " " + tk + " " + mk);
		User_model kh = new User_model(tk, mk, mail, hoten);
		boolean success = new User_DAO().themTaiKhoan(kh);
		if (success) {
            // Nếu thêm tài khoản thành công thì chuyển hướng về trang đăng nhập
            response.sendRedirect("login.jsp");
        } else {
            // Nếu thêm tài khoản thất bại thì hiển thị thông báo lỗi
            //request.setAttribute("errorMsg", "Đăng ký tài khoản thất bại. Vui lòng thử lại!");
            request.getRequestDispatcher("sigup.jsp").forward(request, response);
        }
	}

}