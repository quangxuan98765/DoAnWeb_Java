<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Laptop</title>

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
                <input type="text" class="search-box" placeholder="Tìm tên thương hiệu, sản phẩm...">
                <button class="search-btn">Tìm kiếm</button>                       
            </div>
            <a>
                <img src="img/user.png" id="user-img" alt="">
                <div class="login-logout-popup hide"></div>
			</a>
    </div>
</div>
<ul class="links-container">
    <li class="link-item"><a href="Index" class="link"><img src="img/home.png">Trang chủ</a></li>
    <li class="link-item"><a href="LaptopProduct.jsp" class="link">Laptop</a></li>
    <li class="link-item"><a href="acceProduct.jsp" class="link">Phụ Kiện</a></li>
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
       	 		document.getElementById("select-category").value = 1;
       		 	document.getElementById("select-category").previousElementSibling.style.display = "none";
     		   	document.getElementById("select-category").style.display = "none";
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