package Servlet_controller;

import java.io.IOException;
import javax.servlet.http.Part;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import DAO_model.Product_DAO;
import DAO_model.Product_model;
import javax.servlet.annotation.MultipartConfig;
/**
 * Servlet implementation class addProduct
 */
@WebServlet("/addProduct")
@MultipartConfig
public class addProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tenSP = request.getParameter("ten_sp");
		String motaSP = request.getParameter("mota_sp");
		double giaSP = Double.parseDouble(request.getParameter("gia_sp"));
		String maSP = request.getParameter("ma_sp");
		String loaiSP = request.getParameter("loai_sp");
		String brands = request.getParameter("thuong_hieu");
		
		 // Lấy danh sách các Part chứa các file ảnh sản phẩm Các Part này có thể được lấy bằng cách sử dụng thuộc tính name của thẻ <input type="file">.
		List<Part> hinhSPs = new ArrayList<Part>();
		Collection<Part> parts = request.getParts();
	    for (Part part : parts) {
	        String fileName = part.getSubmittedFileName();
	        if (fileName != null && !fileName.isEmpty()) {
	            // Kiểm tra xem đây có phải là một file ảnh hay không
	            String contentType = part.getContentType();
	            if (contentType != null && contentType.startsWith("image/")) {
	                hinhSPs.add(part);
	            }
	        }
	    }
		
		Product_model p = new Product_model(maSP, tenSP, motaSP, giaSP, brands, loaiSP);
		new Product_DAO().insert(p, hinhSPs);
		request.setAttribute("message", "Thêm sản phẩm thành công");
	}

}
