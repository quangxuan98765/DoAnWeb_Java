<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Trang chủ</title>
	<link rel="stylesheet" href="css/home.css">
    <link rel="stylesheet" href="css/admin.css">
    <link rel="stylesheet" href="css/SearchIndex.css">
    <link rel="stylesheet" href="css/page.css">
    
    <link rel="stylesheet" href="css/alert.css">
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
    <!--hero section-->
    <header class="hero-section">
        <div class="content">
            <img src="img/light-logo.png" class="logo" alt="">
            <p class="sub-heading">Yamateeeee kudasai</p>
        </div>
    </header>
    
    <div class="box box-select"></div>
    
        <div class="box">
            <select id="select-sort" class="select select-combo">
                <option value="">Xếp theo: Nổi bật</option>
                <option value="ASC">Giá từ thấp đến cao</option>
                <option value="DESC">Giá từ cao đến thấp</option>
            </select>
        </div>
	<!--cards-container-->
	
	<section class="product">
		<h2 class="product-category">Laptop mới <img src="img/new.png"></h2>
		<button class="pre-btn">
			<img src="img/arrow.png" alt="">
		</button>
		<button class="nxt-btn">
			<img src="img/arrow.png" alt="">
		</button>
		<div class="product-container">
		<c:forEach items="${plist}" var="p">
		<div class="product-card">
				<div class="product-image">
				<a href="Product?Masp=${p.masp }">
					<img src="${p.hinhsp }" class="product-thumb">
					<c:choose>
					  <c:when test="${sessionScope.role == 'admin'}">
					  <button class="card-btn">thêm vào giỏ hàng</button>
					    <a href="editProduct?id=${p.id }"><button class="card-action-btn edit-btn">Sửa</button></a>
					    <a href="DeleteProduct?id=${p.id}" onclick="return confirm('Are you sure?');">
						  <button class="card-action-btn delete-popup-btn">Xóa</button>
						</a>
					  </c:when>
					  <c:otherwise>
					    <button class="card-btn">thêm vào giỏ hàng</button>
					  </c:otherwise>
					</c:choose>
				</a>
				</div>
				<div class="product-info">
					<h2 class="product-brand">${p.tensp}</h2>
					<p class="product-short-des">${p.motasp }</p>
					<span class="price">${p.giasp}$</span>
				</div>
			</div>	
		</c:forEach>
		</div>
		</section>
                <div class="order-page"></div>
	 <section class="product">
		<h2 class="product-category">Phụ kiện mới <img src="img/bestsell.png"></h2>
		<button class="pre-btn">
			<img src="img/arrow.png" alt="">
		</button>
		<button class="nxt-btn">
			<img src="img/arrow.png" alt="">
		</button>
		<div class="product-container">
	 <c:forEach items="${Alist}" var="p">
		<div class="product-card">
				<div class="product-image">
				<a href="Product?Masp=${p.masp }">
					<img src="${p.hinhsp }" class="product-thumb">
					<c:choose>
					  <c:when test="${sessionScope.role == 'admin'}">
					  <button class="card-btn">thêm vào giỏ hàng</button>
					    <a href="editProduct?id=${p.id }"><button class="card-action-btn edit-btn">Sửa</button></a>
					    <a href="DeleteProduct?id=${p.id}" onclick="return confirm('Are you sure?');">
						  <button class="card-action-btn delete-popup-btn">Xóa</button>
						</a>
					  </c:when>
					  <c:otherwise>
					    <button class="card-btn">thêm vào giỏ hàng</button>
					  </c:otherwise>
					</c:choose>
				</a>
				</div>
				<div class="product-info">
					<h2 class="product-brand">${p.tensp}</h2>
					<p class="product-short-des">${p.motasp }</p>
					<span class="price">${p.giasp}$</span>
				</div>
			</div>	
		</c:forEach>
		</div>
	</section>	


	<ul class="list_page"></ul>
    <!--collections-->
    <h2 class="title-colection">Mục đáng chú ý</h2>
    <section class="collection-container">
        <a href="LaptopProduct.jsp" class="collection">
            <img src="img/quangcao.jpg" alt="">
        </a>
    </section>
    <footer></footer>
    
	<script src="js/addFil.js"></script>
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
    <script src="js/search.js"></script>
    <script src="js/home.js"></script>
    <script src="js/footer.js"></script>
    <script type="module" src='js/checkDisabled.js'></script>
</body>
</html>