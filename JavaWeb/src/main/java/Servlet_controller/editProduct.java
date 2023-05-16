package Servlet_controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO_model.Product_DAO;
import DAO_model.Product_model;

/**
 * Servlet implementation class editProduct
 */
@WebServlet("/editProduct")
public class editProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public editProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		Product_DAO p = new Product_DAO();
		Product_model sp = p.getByid(request.getParameter("id"));
		request.setAttribute("sp", sp);
		this.getServletContext().getRequestDispatcher("/editProduct.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id");
		String tenSP = request.getParameter("ten_sp");
		String motaSP = request.getParameter("mota_sp");
		double giaSP = Double.parseDouble(request.getParameter("gia_sp"));
		String maSP = request.getParameter("ma_sp");
		String loaiSP = request.getParameter("loai_sp");
		String brands = request.getParameter("thuong_hieu");
		System.out.println(id + " " + tenSP + " " + motaSP + " " + giaSP);
		
		 // Lấy danh sách các Part chứa các file ảnh sản phẩm Các Part này có thể được lấy bằng cách sử dụng thuộc tính name của thẻ <input type="file">.
		List<String> hinhSPs = new ArrayList<>();
		
		hinhSPs.add("img/product/" + request.getPart("fileParts1").getSubmittedFileName());
		hinhSPs.add("img/product/" + request.getPart("fileParts2").getSubmittedFileName());
		hinhSPs.add("img/product/" + request.getPart("fileParts3").getSubmittedFileName());
		hinhSPs.add("img/product/" + request.getPart("fileParts4").getSubmittedFileName());
		System.out.println(hinhSPs);
		
		
		Product_model p = new Product_model(maSP, tenSP, motaSP, giaSP, brands, loaiSP);
		new Product_DAO().update(p, id, hinhSPs);
		//request.setAttribute("message", "Thêm sản phẩm thành công");
		response.sendRedirect("Index");
	}

}
