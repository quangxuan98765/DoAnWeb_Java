package Servlet_controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO_model.Cart_DAO;
import DAO_model.User_DAO;
import DAO_model.Product_model;
import DAO_model.User_model;

/**
 * Servlet implementation class Cart
 */
@WebServlet("/Cart")
public class Cart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Cart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
	    response.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
	    String username = (String) session.getAttribute("username");
	    
		Cart_DAO pdao = new Cart_DAO();
		User_DAO pu = new User_DAO();
		List<Product_model> plist = pdao.getAllProduct(username);
		List<User_model> listDC = pu.getDC(username);
		request.setAttribute("cartList", plist);
		request.setAttribute("diachi", listDC);
		this.getServletContext().getRequestDispatcher("/cart.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
	    response.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
	    String username = (String) session.getAttribute("username");
	    String pay = request.getParameter("pay");
	    String sdt = request.getParameter("sdt");
	    String idloca = request.getParameter("select-loc");
	    Cart_DAO pdao = new Cart_DAO();
	    boolean tc = pdao.DatHang(username, pay, idloca, sdt);
	    if (tc) {
            // Nếu thêm tài khoản thành công thì chuyển hướng về trang đăng nhập
            response.sendRedirect("Historycart");
        } else {
            // Nếu thêm tài khoản thất bại thì hiển thị thông báo lỗi
            request.setAttribute("errorMsg", "Thất bại. Vui lòng thử lại!");
            //request.getRequestDispatcher("XulyDangKy").forward(request, response);
        }
	}

}
