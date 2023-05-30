<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lịch sử mua hàng</title>

    <link rel="stylesheet" href="css/home.css">
    <link rel="stylesheet" href="css/cart.css">
    <link rel="stylesheet" href="css/SearchIndex.css">
    <link rel="stylesheet" href="css/admin.css">
    <link rel="stylesheet" href="css/page.css">

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
	    <li class="link-item"><a href="LaptopProduct.jsp" class="link">Laptop</a></li>
	    <li class="link-item"><a href="acceProduct.jsp" class="link">Phụ Kiện</a></li>
	    <li class="link-item"><a href="addProduct" class="link">Thêm sản phẩm</a></li>
	    <li class="link-item"><a href="Order" class="link">Quản lý Shop</a></li>
	  </c:when>
	  <c:otherwise>
	    <li class="link-item"><a href="Index" class="link"><img src="img/home.png">Trang chủ</a></li>
	    <li class="link-item"><a href="LaptopProduct.jsp" class="link">Laptop</a></li>
	    <li class="link-item"><a href="acceProduct.jsp" class="link">Phụ Kiện</a></li>
	  </c:otherwise>
	</c:choose>
	</ul>
	
	<script>
    const userImageButton = document.getElementById("user-img");
    const userPop = document.querySelector('.login-logout-popup');
    userImageButton.addEventListener('click', () =>{
        userPop.classList.toggle('hide');
    })
</script>
    <div class="info-user">
        <div class="title-user">
        <h2 class="title-history-cart">Đơn hàng đã mua gần đây</h2>  
        </div>
    </div>

    <div class="small-container cart-page" id="boxajax">
  
    </div>

    <ul class="list_page"></ul>
    <script type="module" src="js/historycart.js"></script>
    <script type="module"> 
    import { newFunc } from "./js/searchBar.js";
     document.querySelectorAll(".select-combo").forEach((e) => {
    e.addEventListener("change", () => {
      document.querySelectorAll(".product").forEach(function(e){
        e.innerHTML = "";})
      newFunc();
    });
})
    </script>
</body>
</html>