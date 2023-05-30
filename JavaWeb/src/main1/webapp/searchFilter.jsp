<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Kết quả tìm kiếm</title>
 	<link rel="stylesheet" href="css/nav.css">
    <link rel="stylesheet" href="css/footer.css">
    <link rel="stylesheet" href="css/product.css">
    <link rel="stylesheet" href="css/home.css">
    <link rel="stylesheet" href="css/page.css">
    <link rel="stylesheet" href="css/SearchIndex.css">
</head>

<body>
    <div class="nav">
		<img src="img/dark-logo.png" class="brand-logo" alt="">
		<div class="nav-items">
			<div class="search">
				<input type="text" class="search-box"
					placeholder="Tìm tên thương hiệu, sản phẩm...">
				<button class="search-btn">Tìm kiếm</button>
			</div>
			<a>
    <img src="img/user.png" id="user-img" alt="">
    <div class="login-logout-popup hide">
    <c:if test="${not empty sessionScope.username}">
            <p class="account-info">
                Đang đăng nhập
                ${sessionScope.username}
            </p>
            <form action="DangXuat" method="get">
                <button class="btn" id="user-btn" type="submit">đăng xuất</button>
            </form>
    </c:if>
    <c:if test="${empty sessionScope.username}">
            <p class="account-info">Chưa đăng nhập</p>
            <button class="btn" id="user-btn">đăng nhập</button>
        <script>
            document.getElementById("user-btn").addEventListener("click", function() {
                window.location.href = "XulyDangNhap";
            });
        </script>
    </c:if>
    </div>
</a>
<c:if test="${not empty sessionScope.username}">
 <a href="Historycart"><img src="img/history.png"></a> 
 <a href="Cart"><img src="img/cart.png"></a>
</c:if>
		</div>
	</div>
    <ul class="links-container">
	<c:choose>
	  <c:when test="${sessionScope.role == 'admin'}">
	  	<li class="link-item"><a href="Index" class="link"><img src="img/home.png">Trang chủ</a></li>
	    <li class="link-item"><a href="laptopProduct.php" class="link">Laptop</a></li>
	    <li class="link-item"><a href="womenarmor.html" class="link">Phụ Kiện</a></li>
	    <li class="link-item"><a href="addProduct" class="link">Thêm sản phẩm</a></li>
	    <li class="link-item"><a href="Order" class="link">Quản lý Shop</a></li>
	  </c:when>
	  <c:otherwise>
	    <li class="link-item"><a href="Index" class="link"><img src="img/home.png">Trang chủ</a></li>
	    <li class="link-item"><a href="laptopProduct.php" class="link">Laptop</a></li>
	    <li class="link-item"><a href="womenarmor.html" class="link">Phụ Kiện</a></li>
	  </c:otherwise>
	</c:choose>
	</ul>
    <script>
        const userImageButton = document.getElementById("user-img");
        const userPop = document.querySelector('.login-logout-popup');
        userImageButton.addEventListener('click', () => {
            userPop.classList.toggle('hide');
        })
    </script>
    <section class="search-results">
        <div class="box box-select">
         
        </div>
        <div class="box">
            <select id="select-sort" class="select select-combo">
                <option value="">Xếp theo: Nổi bật</option>
                <option value="ASC">Giá từ thấp đến cao</option>
                <option value="DESC">Giá từ cao đến thấp</option>
            </select>
        </div>
        <h2 class="product-category">Sản phẩm</h2>
        <div class="order-page"></div>

        <ul class="list_page">
        </ul>
    </section>
    
    <footer></footer>
    
     <script src="js/addFil.js"></script>
   <script type="module">
		import { newFunc } from "./js/searchBar.js";
		myFunction();
		function myFunction() {
  			try {	
 		   		newFunc();
  			} catch (error) {
				if(error instanceof TypeError)
					location.reload();
 				 else console.error("Error: " +error);
			}
		}
	</script>
   
    <script src="js/footer.js"></script>
    <script src="js/search.js"></script>

</body>

</html>