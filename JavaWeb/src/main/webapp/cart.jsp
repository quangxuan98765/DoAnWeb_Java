<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thanh toán</title>

    <link rel="stylesheet" href="css/home.css">
    <link rel="stylesheet" href="css/cart.css">
    <link rel="stylesheet" href="css/searchIndex.css">

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
 <a href="historycart.html"><img src="img/history.png"></a> <a
				href="cart.html"><img src="img/cart.png"></a>
		</div>
	</div>
	<ul class="links-container">
		<li class="link-item"><a href="Index" class="link"><img src="img/home.png">Trang chủ</li>
	    <li class="link-item"><a href="laptopProduct.php" class="link">Laptop</li>
	    <li class="link-item"><a href="womenarmor.html" class="link">Phụ Kiện</li>
	    <li class="link-item"><a class="link"></li>
	</ul>
	
	<script>
    const userImageButton = document.getElementById("user-img");
    const userPop = document.querySelector('.login-logout-popup');
    userImageButton.addEventListener('click', () =>{
        userPop.classList.toggle('hide');
    })
</script>

<!--         ajax           -->
<script>
    function deleteCart(masp) {
        // Tạo đối tượng XMLHttpRequest
        var xhr = new XMLHttpRequest();
        xhr.open("GET", "deleteCart?masp=" + masp, true);
        xhr.onload = function() {
            var sum = 0;
            var billcontainer = document.getElementById('myTD');
            var billcontainer1 = document.getElementById('myTD1');
            var count = document.getElementById('count');
            var products = JSON.parse(xhr.responseText);
            var numOfItems = products.length;
            var productContainer = document.getElementById('boxajax-containter');
            var productHtml = "";
            if(products.length === 0) {
                productHtml = `<a class="back" onclick="location.href='Index'">&larr; Mua thêm sản phẩm khác</a>`;
                productContainer.innerHTML = productHtml + `<div class="container" style="text-align:center;"><img src="img/no-products.png" alt=""><p class="overlay" id="formtt">Giỏ hàng của bạn đang trống</p></div>`;
                const myForm = document.getElementById("my-form");
                myForm.style.display = "none";
            }
            else{
                products.forEach(function(product){
                    productHtml += `<tr><td><div class="cart-info"><img src="` + product.hinhsp + `"><div>`;
                    productHtml += `<h3>` + product.TenSP + `(` + product.MaSP + `)</h3>`;
                    productHtml += `<small>`+ product.MoTaSP +`</small><br>`;
                    productHtml += `<a class="link-text" href="Product?masp=` + product.masp + `">Xem chi tiết</a><br>`;
                    var gia = parseInt(product.giasp) * parseInt(product.soluong);
                    productHtml += `<button class="btn-remove" onclick="deleteCart('${product.masp}')">Xoá sản phẩm</button></div></div><td><button class="btn-value">-</button><input type="number" value="${product.soluong}"><button class="btn-value">+</button></td><td>`+ gia +`$</td></tr>`;
                    sum += gia;
                });
                productContainer.innerHTML = productHtml;
            }
            billcontainer.innerHTML = sum;
            billcontainer1.innerHTML = sum;
            count.innerHTML = `Tạm tính(` + numOfItems + ` sản phẩm)`;
        }
        xhr.onerror = function() {
            console.error(xhr.statusText);
        };
        xhr.send();
    }
</script>

<div id="boxajax-containter">
    <a class="back" onclick="location.href='index.php'">&larr; Mua thêm sản phẩm khác</a>

    <c:choose>
        <c:when test="${fn:length(cartList) > 0}">
            <div class="small-container cart-page">
                <table>
                    <tr>
                        <th>Sản phẩm</th>
                        <th>Số lượng</th>
                        <th style="width: 130px">giá</th>
                    </tr>
                    <c:forEach var="product" items="${cartList}">
                        <tr>
                            <td>
                                <div class="cart-info">
                                    <img src="${product.hinhsp}">
                                    <div>
                                        <h3>${product.tensp} (${product.masp})</h3>
                                        <small>${product.motasp}</small><br>
                                        <a class="link-text" href="Product?MaSP=${product.masp}">Xem chi tiết</a><br>
                                        <button class="btn-remove" onclick="deleteCart('${product.masp}')">Xoá sản phẩm</button>
                                    </div>
                                </div>
                            </td>
                            <td>
                                <button class="btn-value">-</button>
                                <input type="number" value="${product.soluong}">
                                <button class="btn-value">+</button>
                            </td>
                            <td>${product.giasp}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </c:when>
        <c:otherwise>
            <div class="container" style="text-align:center;">
                <img src="img/no-products.png" alt="">
                <p class="overlay" id="formtt">Giỏ hàng của bạn đang trống</p>
            </div>
        </c:otherwise>
    </c:choose>
</div>

    <form name="form" method="get" id="my-form" action="thanhtoan.php" enctype="multipart/form-data">
    <div class="input-cart">
        <p class="text header">thông tin khách hàng</p>
        <input type="checkbox" id="Nam" name="xungho" onclick="checkOnlyOne1(this)">
        <label for="Nam" class="check-title">Anh</label>
        <input type="checkbox" id="Nu" name="xungho" onclick="checkOnlyOne1(this)">
        <label for="Nu" class="check-title">Chị</label>
        <div class="zone-text-input">
        	<input id="hoten"  type="text" class="input-text" placeholder="Họ và tên">
            <input id="sdt" name="sdt" type="text" class="input-text" placeholder="Số điện thoại">
        </div>
        <p class="text header">chọn địa chỉ nhận hàng</p>

        <a href="addDiaChi" class="linked" >Thêm địa chỉ mới</a>
        <div class="address-input">
        <select id="mySelect" name="select-loc" class="select">
		    <option value="-1">Chọn địa chỉ nhận hàng</option>
		    <c:forEach var="row1" items="${diachi}">
		        <option value="${row1.idc}">${row1.city} ${row1.tenduong} ${row1.sonha}</option>
		    </c:forEach>
		</select>
        <a id="myLink" href="#">Chỉnh sửa địa chỉ</a>
        </div>
        <input type="text" class="input-text input-text3" placeholder="Yêu cầu khác (không bắt buộc)">
        <p class="text header">chọn phương thức thanh toán</p>
        <input type="checkbox" id="cod" name="pay" value="COD" onclick="checkOnlyOne2(this)">
        <label for="cod" class="check-title">COD</label>

        <input type="checkbox" id="onl" name="pay" value="Online" onclick="checkOnlyOne2(this)">
        <label for="onl" class="check-title">Online</label>
        <div class="zone-text-input">
            <input type="text" class="input-text" id="bank" placeholder="số tài khoản" style="display:none;">
        </div>

    </div>
    <div class="total-price" id="billajax">
        <table>
            <tr>
                <c:set var="sum" value="${0}" /> <!-- Khởi tạo biến sum -->

				<c:forEach var="item" items="${cartList}">
				  <c:set var="subtotal" value="${item.giasp * item.soluong}" /> <!-- Tính tổng giá cho từng sản phẩm -->
				  <c:set var="sum" value="${sum + subtotal}" /> <!-- Tính tổng giá cho toàn bộ giỏ hàng -->
				</c:forEach>
				
				<tr>
				  <td id="count">Tạm tính(${fn:length(cartList)} sản phẩm)</td> <!-- Hiển thị số lượng sản phẩm -->
				  <td id="myTD">${sum}</td>
				</tr>
				<tr>
				  <td>
				    <select class="select select3">
				      <option>Sử dụng mã giảm giá</option>
				      <option>Có cái nịt</option>
				    </select>
				  </td>
				  <td>-0₫</td>
				</tr>
				<tr>
				  <td>Tổng thanh toán</td>
				  <td id="myTD">${sum}</td>
				</tr>
            <tr>
                <td></td>
                <td><button name="dathang" class="btn-cart" onclick="return validateForm()">Đặt hàng</button></td>
            </tr>
        </table>
    </div>
    </form>
    <script src="js/cart.js"></script>
</body>
</html>