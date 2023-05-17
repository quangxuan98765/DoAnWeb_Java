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
 * Servlet implementation class addDiaChi
 */
@WebServlet("/addDiaChi")
public class addDiaChi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addDiaChi() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.getServletContext().getRequestDispatcher("/locationForm.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username"); // Lấy username từ session
		String city= request.getParameter("city");
		String duong= request.getParameter("duong");
		String nha= request.getParameter("nha");
		
		User_model kh = new User_model(city, duong, nha);
		boolean success = new User_DAO().ThemDC(kh, username);
		if (success) {
            // Nếu thêm tài khoản thành công thì chuyển hướng về trang đăng nhập
            response.sendRedirect("Cart");
        } else {
            // Nếu thêm tài khoản thất bại thì hiển thị thông báo lỗi
            request.setAttribute("errorMsg", "thêm địa chỉ thất bại. Vui lòng thử lại!");
            //request.getRequestDispatcher("XulyDangKy").forward(request, response);
        }
	}

}
