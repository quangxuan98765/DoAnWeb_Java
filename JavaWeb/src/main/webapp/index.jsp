<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>ARMOR-SHOP</title>
<link rel="stylesheet" href="css/home.css">
<link rel="stylesheet" href="css/admin.css">
<link rel="stylesheet" href="css/searchIndex.css">
</head>
<body>
	<div class="nav">
		<img src="img/dark-logo.png" class="brand-logo" alt="">
		<div class="nav-items">
			<div class="search">
				<input type="text" class="search-box"
					placeholder="TÃ¬m tÃªn thÆ°Æ¡ng hiá»u, sáº£n pháº©m...">
				<button class="search-btn">TÃ¬m kiáº¿m</button>
			</div>
			<a>
				 <img src="img/user.png" id="user-img" alt="">
				 <%
					 String username = (String) session.getAttribute("username");
					 if (username != null) {
				  %>
				<div class="login-logout-popup">
					<p class="account-info">
						Äang ÄÄng nháº­p
						<%=username%></p>
					<form action="DangXuat" method="get">
						<button class="btn" id="user-btn" type="submit">ÄÄng xuáº¥t</button>
					</form>
				</div> 
				<%
				} else {
				%>
				<div class="login-logout-popup">
					<p class="account-info">ChÆ°a ÄÄng nháº­p</p>
					<button class="btn" id="user-btn">ÄÄng nháº­p</button>
				</div>
				<script>
	                document.getElementById("user-btn").addEventListener("click", function() {
	                    window.location.href = "login.jsp";
	                });
	            </script>
				<%
 				}
				%>

			</a> <a href="historycart.html"><img src="img/history.png"></a> <a
				href="cart.html"><img src="img/cart.png"></a>
		</div>
	</div>
	<ul class="links-container">
		<li class="link-item"><a href="index.php" class="link"><img src="img/home.png">Trang chá»§</li>
	    <li class="link-item"><a href="laptopProduct.php" class="link">Laptop</li>
	    <li class="link-item"><a href="womenarmor.html" class="link">Phá»¥ Kiá»n</li>
	    <li class="link-item"><a class="link"></li>
	</ul>
	<!--hero section-->
	<header class="hero-section">
		<div class="content">
			<img src="img/light-logo.png" class="logo" alt="">
			<p class="sub-heading">Biáº¿n chiáº¿n trÆ°á»ng thÃ nh sÃ¢n kháº¥u cá»§a báº¡n</p>
		</div>
	</header>

	<!--cards-container-->
	<section class="product">
		<h2 class="product-category">
			Sáº£n pháº©m má»i <img src="img/new.png">
		</h2>
		<button class="pre-btn">
			<img src="img/arrow.png" alt="">
		</button>
		<button class="nxt-btn">
			<img src="img/arrow.png" alt="">
		</button>
		<div class="product-container">
			<div class="product-card">
				<div class="product-image">
					<span class="discount-tag">Äang giáº£m 20%</span> <img
						src="img/pro1.png" class="product-thumb" alt=""
						onclick="location.href='product.html'">
					<button class="card-btn">thÃªm vÃ o giá» hÃ ng</button>
				</div>
				<div class="product-info">
					<h2 class="product-brand">Sister of Battle Power Armor</h2>
					<p class="product-short-des">Power Armor</p>
					<span class="price">280.000â«</span><span class="actual-price">350.000â«</span>
				</div>
			</div>
			<div class="product-card">
				<div class="product-image">
					<span class="discount-tag">Äang giáº£m 25%</span> <img
						src="img/pro3.png" class="product-thumb" alt=""
						onclick="location.href='product.html'">
					<button class="card-btn">thÃªm vÃ o giá» hÃ ng</button>
				</div>
				<div class="product-info">
					<h2 class="product-brand">Inquisition Sister of Battle Power
						Armor</h2>
					<p class="product-short-des">Power Armor of inquisition</p>
					<span class="price">270.000â«</span><span class="actual-price">360.000â«</span>
				</div>
			</div>
			<div class="product-card">
				<div class="product-image">
					<span class="discount-tag">Äang giáº£m 10%</span> <img
						src="img/pro4.png" class="product-thumb" alt=""
						onclick="location.href='product.html'">
					<button class="card-btn">thÃªm vÃ o giá» hÃ ng</button>
				</div>
				<div class="product-info">
					<h2 class="product-brand">Flak Armor</h2>
					<p class="product-short-des">Armor of Imperial Guard</p>
					<span class="price">360.000â«</span><span class="actual-price">400.000â«</span>
				</div>
			</div>
			<div class="product-card">
				<div class="product-image">
					<span class="discount-tag">Äang giáº£m 10%</span> <img
						src="img/pro5.png" class="product-thumb" alt=""
						onclick="location.href='product.html'">
					<button class="card-btn">thÃªm vÃ o giá» hÃ ng</button>
				</div>
				<div class="product-info">
					<h2 class="product-brand">Adeptus Arbites Armor</h2>
					<p class="product-short-des">Power Armor</p>
					<span class="price">270.000â«</span><span class="actual-price">300.000â«</span>
				</div>
			</div>
			<div class="product-card">
				<div class="product-image">
					<span class="discount-tag">Äang giáº£m 50%</span> <img
						src="img/pro2.png" class="product-thumb" alt=""
						onclick="location.href='product.html'">
					<button class="card-btn">thÃªm vÃ o giá» hÃ ng</button>
				</div>
				<div class="product-info">
					<h2 class="product-brand">Nun sister of battle</h2>
					<p class="product-short-des">Power Armor</p>
					<span class="price">230.000â«</span><span class="actual-price">460.000â«</span>
				</div>
			</div>
			<div class="product-card">
				<div class="product-image">
					<span class="discount-tag">Äang giáº£m 30%</span> <img
						src="img/pro7.png" class="product-thumb" alt=""
						onclick="location.href='product.html'">
					<button class="card-btn">thÃªm vÃ o giá» hÃ ng</button>
				</div>
				<div class="product-info">
					<h2 class="product-brand">Witch Hunter Power Armor</h2>
					<p class="product-short-des">Power Armor</p>
					<span class="price">280.000â«</span><span class="actual-price">400.000â«</span>
				</div>
			</div>
			<div class="product-card">
				<div class="product-image">
					<span class="discount-tag">Äang giáº£m 30%</span> <img
						src="img/pro8.png" class="product-thumb" alt=""
						onclick="location.href='product.html'">
					<button class="card-btn">thÃªm vÃ o giá» hÃ ng</button>
				</div>
				<div class="product-info">
					<h2 class="product-brand">Inquisiton Power Armor</h2>
					<p class="product-short-des">Power Armor</p>
					<span class="price">266.000â«</span><span class="actual-price">380.000â«</span>
				</div>
			</div>

		</div>
	</section>

	<section class="product">
		<h2 class="product-category">
			Sáº£n pháº©m bÃ¡n cháº¡y <img src="img/bestsell.png">
		</h2>
		<button class="pre-btn">
			<img src="img/arrow.png" alt="">
		</button>
		<button class="nxt-btn">
			<img src="img/arrow.png" alt="">
		</button>
		<div class="product-container">
			<div class="product-card">
				<div class="product-image">
					<span class="discount-tag">Äang giáº£m 50%</span> <img
						src="img/card1.png" class="product-thumb" alt=""
						onclick="location.href='product.html'">
					<button class="card-btn">thÃªm vÃ o giá» hÃ ng</button>
				</div>
				<div class="product-info">
					<h2 class="product-brand">BloodAngels Primaris</h2>
					<p class="product-short-des">Mark X Tacticus Power Armor</p>
					<span class="price">230.000â«</span><span class="actual-price">460.000â«</span>
				</div>
			</div>
			<div class="product-card">
				<div class="product-image">
					<span class="discount-tag">Äang giáº£m 50%</span> <img
						src="img/pro2.png" class="product-thumb" alt=""
						onclick="location.href='product.html'">
					<button class="card-btn">thÃªm vÃ o giá» hÃ ng</button>
				</div>
				<div class="product-info">
					<h2 class="product-brand">Nun sister of battle</h2>
					<p class="product-short-des">Power Armor</p>
					<span class="price">230.000â«</span><span class="actual-price">460.000â«</span>
				</div>
			</div>
			<div class="product-card">
				<div class="product-image">
					<span class="discount-tag">Äang giáº£m 30%</span> <img
						src="img/card3.png" class="product-thumb" alt=""
						onclick="location.href='product.html'">
					<button class="card-btn">thÃªm vÃ o giá» hÃ ng</button>
				</div>
				<div class="product-info">
					<h2 class="product-brand">Blood Ravens Primaris</h2>
					<p class="product-short-des">Mark X Tacticus Power Armor</p>
					<span class="price">280.000â«</span><span class="actual-price">400.000â«</span>
				</div>
			</div>
			<div class="product-card">
				<div class="product-image">
					<span class="discount-tag">Äang giáº£m 30%</span> <img
						src="img/card4.png" class="product-thumb" alt=""
						onclick="location.href='product.html'">
					<button class="card-btn">thÃªm vÃ o giá» hÃ ng</button>
				</div>
				<div class="product-info">
					<h2 class="product-brand">Exorcists Primaris</h2>
					<p class="product-short-des">Mark X Tacticus Power Armor</p>
					<span class="price">266.000â«</span><span class="actual-price">380.000â«</span>
				</div>
			</div>
			<div class="product-card">
				<div class="product-image">
					<span class="discount-tag">Äang giáº£m 25%</span> <img
						src="img/card5.png" class="product-thumb" alt=""
						onclick="location.href='product.html'">
					<button class="card-btn">thÃªm vÃ o giá» hÃ ng</button>
				</div>
				<div class="product-info">
					<h2 class="product-brand">Fire Lords Primaris</h2>
					<p class="product-short-des">Mark X Tacticus Power Armor</p>
					<span class="price">337.500â«</span><span class="actual-price">450.000â«</span>
				</div>
			</div>
			<div class="product-card">
				<div class="product-image">
					<span class="discount-tag">Äang giáº£m 25%</span> <img
						src="img/card6.png" class="product-thumb" alt=""
						onclick="location.href='product.html'">
					<button class="card-btn">thÃªm vÃ o giá» hÃ ng</button>
				</div>
				<div class="product-info">
					<h2 class="product-brand">Iron Hands Primaris</h2>
					<p class="product-short-des">Mark X Tacticus Power Armor</p>
					<span class="price">270.000â«</span><span class="actual-price">360.000â«</span>
				</div>
			</div>
			<div class="product-card">
				<div class="product-image">
					<span class="discount-tag">Äang giáº£m 10%</span> <img
						src="img/pro4.png" class="product-thumb" alt=""
						onclick="location.href='product.html'">
					<button class="card-btn">thÃªm vÃ o giá» hÃ ng</button>
				</div>
				<div class="product-info">
					<h2 class="product-brand">Flak Armor</h2>
					<p class="product-short-des">Armor of Imperial Guard</p>
					<span class="price">360.000â«</span><span class="actual-price">400.000â«</span>
				</div>
			</div>
			<div class="product-card">
				<div class="product-image">
					<span class="discount-tag">Äang giáº£m 15%</span> <img
						src="img/card8.png" class="product-thumb" alt=""
						onclick="location.href='product.html'">
					<button class="card-btn">thÃªm vÃ o giá» hÃ ng</button>
				</div>
				<div class="product-info">
					<h2 class="product-brand">Rift Stalkers Primaris</h2>
					<p class="product-short-des">Mark X Tacticus Power Armor</p>
					<span class="price">297.500â«</span><span class="actual-price">350.000â«</span>
				</div>
			</div>
		</div>
	</section>

	<section class="product">
		<h2 class="product-category">
			Äang giáº£m giÃ¡ sá»c <img src="img/hotsell.png">
		</h2>
		<button class="pre-btn">
			<img src="img/arrow.png" alt="">
		</button>
		<button class="nxt-btn">
			<img src="img/arrow.png" alt="">
		</button>
		<div class="product-container">
			<div class="product-card">
				<div class="product-image">
					<span class="discount-tag">Äang giáº£m 30%</span> <img
						src="img/card3.png" class="product-thumb" alt=""
						onclick="location.href='product.html'">
					<button class="card-btn">thÃªm vÃ o giá» hÃ ng</button>
				</div>
				<div class="product-info">
					<h2 class="product-brand">Blood Ravens Primaris</h2>
					<p class="product-short-des">Mark X Tacticus Power Armor</p>
					<span class="price">280.000â«</span><span class="actual-price">400.000â«</span>
				</div>
			</div>
			<div class="product-card">
				<div class="product-image">
					<span class="discount-tag">Äang giáº£m 30%</span> <img
						src="img/card4.png" class="product-thumb" alt=""
						onclick="location.href='product.html'">
					<button class="card-btn">thÃªm vÃ o giá» hÃ ng</button>
				</div>
				<div class="product-info">
					<h2 class="product-brand">Exorcists Primaris</h2>
					<p class="product-short-des">Mark X Tacticus Power Armor</p>
					<span class="price">266.000â«</span><span class="actual-price">380.000â«</span>
				</div>
			</div>
			<div class="product-card">
				<div class="product-image">
					<span class="discount-tag">Äang giáº£m 25%</span> <img
						src="img/card5.png" class="product-thumb" alt=""
						onclick="location.href='product.html'">
					<button class="card-btn">thÃªm vÃ o giá» hÃ ng</button>
				</div>
				<div class="product-info">
					<h2 class="product-brand">Fire Lords Primaris</h2>
					<p class="product-short-des">Mark X Tacticus Power Armor</p>
					<span class="price">337.500â«</span><span class="actual-price">450.000â«</span>
				</div>
			</div>
			<div class="product-card">
				<div class="product-image">
					<span class="discount-tag">Äang giáº£m 25%</span> <img
						src="img/card6.png" class="product-thumb" alt=""
						onclick="location.href='product.html'">
					<button class="card-btn">thÃªm vÃ o giá» hÃ ng</button>
				</div>
				<div class="product-info">
					<h2 class="product-brand">Iron Hands Primaris</h2>
					<p class="product-short-des">Mark X Tacticus Power Armor</p>
					<span class="price">270.000â«</span><span class="actual-price">360.000â«</span>
				</div>
			</div>
			<div class="product-card">
				<div class="product-image">
					<span class="discount-tag">Äang giáº£m 15%</span> <img
						src="img/card7.png" class="product-thumb" alt=""
						onclick="location.href='product.html'">
					<button class="card-btn">thÃªm vÃ o giá» hÃ ng</button>
				</div>
				<div class="product-info">
					<h2 class="product-brand">Knights of the Chalice Primaris</h2>
					<p class="product-short-des">Mark X Tacticus Power Armor</p>
					<span class="price">306.000â«</span><span class="actual-price">360.000â«</span>
				</div>
			</div>
			<div class="product-card">
				<div class="product-image">
					<span class="discount-tag">Äang giáº£m 15%</span> <img
						src="img/card8.png" class="product-thumb" alt=""
						onclick="location.href='product.html'">
					<button class="card-btn">thÃªm vÃ o giá» hÃ ng</button>
				</div>
				<div class="product-info">
					<h2 class="product-brand">Rift Stalkers Primaris</h2>
					<p class="product-short-des">Mark X Tacticus Power Armor</p>
					<span class="price">297.500â«</span><span class="actual-price">350.000â«</span>
				</div>
			</div>
		</div>
	</section>


	<!--collections-->
	<h2 class="title-colection">Má»¥c ÄÃ¡ng chÃº Ã½</h2>
	<section class="collection-container">
		<a href="womenarmor.html" class="collection"> <img
			src="img/women-collection.png" alt="">
			<p class="collection-title">
				women <br> armor
			</p>
		</a> <a href="menarmor.html" class="collection"> <img
			src="img/men-collection.png" alt="">
			<p class="collection-title">
				Man <br> armor
			</p>
		</a> <a href="accessories.html" class="collection"> <img
			src="img/accessories-collection.png" alt="">
			<p class="collection-title">phá»¥ kiá»n</p>
		</a>
	</section>
	</div>
	</div>
	</section>

	<script>
	   const userImageButton = document.querySelector('#user-img');
	   const userPop = document.querySelector('.login-logout-popup');
	   const popuptext = document.querySelector('.account-info');
	   const actionBtn = document.querySelector('#user-btn');
	
	   userImageButton.addEventListener('click', () =>{
	       userPop.classList.toggle('hide');
	   })
   </script>

	<script src="js/home.js"></script>
	<script src="js/footer.js"></script>
</body>
</html>