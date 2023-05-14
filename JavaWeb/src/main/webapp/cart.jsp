<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
		<li class="link-item"><a href="index.php" class="link"><img src="img/home.png">Trang chủ</li>
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
    <a class="back" onclick="location.href='index.php'">&larr; Mua thêm sản phẩm khác</a> 
    <div class="small-container cart-page">
        
    <div class="line"></div>
    <div class="input-cart">
        <p class="text header">thÃ´ng tin khÃ¡ch hÃ ng</p>
        <input type="checkbox" id="Nam">
        <label for="Nam" class="check-title">Anh</label>
        <input type="checkbox" id="Nu">
        <label for="Nu" class="check-title">Chá»</label>
        <div class="zone-text-input">
            <input type="text" class="input-text" placeholder="Há» vÃ  tÃªn">
            <input type="text" class="input-text" placeholder="Sá» Äiá»n thoáº¡i">
        </div>
        <p class="text header">chá»n cÃ¡ch thá»©c nháº­n hÃ ng</p>
        <input type="checkbox" id="ship">
        <label for="ship" class="check-title">Giao hÃ ng táº­n nÆ¡i</label>
        <input type="checkbox" id="shop">
        <label for="shop" class="check-title">Nháº­n táº¡i cá»­a hÃ ng</label>
        <div class="address-input">
            <select class="select">
                <option>Há» ChÃ­ Minh</option>
                <option>HÃ  Ná»i</option>
                <option>Máº·t TrÄng</option>
                <option>Sao Hoáº£</option>
            </select>
            <select class="select">
                <option>Chá»n Quáº­n/Huyá»n</option>
                <option>HÃ  Ná»i</option>
                <option>Máº·t TrÄng</option>
                <option>Sao Hoáº£</option>
            </select>
            <select class="select">
                <option>PhÆ°á»ng /xÃ£</option>
                <option>HÃ  Ná»i</option>
                <option>Máº·t TrÄng</option>
                <option>Sao Hoáº£</option>
            </select>
            <input type="text" class="input-text input-text2" placeholder="Sá» nhÃ , tÃªn ÄÆ°á»ng">
        </div>
        <input type="text" class="input-text input-text3" placeholder="YÃªu cáº§u khÃ¡c (khÃ´ng báº¯t buá»c)">
        <div class="check-one-line">
            <input type="checkbox" id="none">
            <label for="none" class="check-title">Gá»i ngÆ°á»i khÃ¡c nháº­n hÃ ng (náº¿u cÃ³)</label>
        </div>
        <div class="check-one-line">
            <input type="checkbox" id="none">
            <label for="none" class="check-title">HÆ°á»ng dáº«n sá»­ dá»¥ng, giáº£i ÄÃ¡p tháº¯c máº¯c</label>
        </div>
        <div class="check-one-line">
            <input type="checkbox" id="none">
            <label for="none" class="check-title">xuáº¥t hoÃ¡ ÄÆ¡n cÃ´ng ty</label>
        </div>
        <div class="check-one-line">
            <a class="check-title">*Báº¡n cÃ³ thá» chá»n hÃ¬nh thá»©c thanh toÃ¡n sau khi Äáº·t hÃ ng</a>
        </div>
    </div>
    <div class="total-price">
        <table>
            <tr>
                <td>Táº¡m tÃ­nh (3 sáº£n pháº©m)</td>
                <td>790.000â«</td>
            </tr>
            <tr>
                <td><select class=" select select3">
                    <option>Sá»­ dá»¥ng mÃ£ giáº£m giÃ¡</option>
                    <option>CÃ³ cÃ¡i ná»t</option>
                    <option>Máº·t TrÄng</option>
                    <option>Sao Hoáº£</option>
                </select></td>
                <td>-0â«</td>
            </tr>
            <tr>
                <td>Tá»ng thanh toÃ¡n</td>
                <td>869.000â«</td>
            </tr>
            <tr>
                <td></td>
                <td><button class="btn-cart">Äáº·t hÃ ng</td>
            </tr>
        </table>
    </div>
    <script src="js/nav.js"></script>
</body>
</html>