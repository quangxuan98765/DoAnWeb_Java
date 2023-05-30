<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ADMIN</title>

    <link rel="stylesheet" href="css/home.css">
    <link rel="stylesheet" href="css/cart.css">
    <link rel="stylesheet" href="css/search.css">
    <link rel="stylesheet" href="css/sigup.css">
    <link rel="stylesheet" href="css/admin1.css">
    <link rel="stylesheet" href="css/page.css">

</head>
<body>
    <img src="img/loader.gif" class="loader" alt="">

    <div class="alert-box">
        <img src="img/error.png" class="alert-img" alt="">
        <p class="alert-msg"></p>
    </div>
    <img src="img/dark-logo.png" class="logo" alt="">

    <!--become seller element-->
    <!--apply form-->
    <div class="nav-space">
        <div class="nav-admin">
            <img src="img/user.png">
                <p class="add-product-title name-admin">Hello ${sessionScope.username} </p>
                <form action="DangXuat" method="get">
                <button class="btn btn-new-product" id="new-product">Đăng xuất</button>
            	</form>
                
	
        </div>
        <p class="add-product-title nav-link" onclick="location.href='Index'">trang chủ</p>
        <p class="add-product-title nav-link" onclick="location.href='user.jsp'">quản lý user</p>
        <p class="add-product-title nav-link" onclick="location.href='Order'">quản lý đơn hàng</p>
        <p class="add-product-title nav-link" onclick="location.href='report.jsp'">Thống kê doanh thu</p>
    </div>
    <div class="order-list">
        <div class="add-product">
            <p class="add-product-title">quản lý đơn hàng</p>
        </div>
        <div class="box">
            <div class="date-search">
                    <a class="nameselect-combo">Từ ngày</a>
                    <input type="date" class="date from-date">
                    <a class="nameselect-combo">Tới ngày</a>
                    <input type="date" class="date to-date">
                <div class="btn-search-date">
                    <button class="btn btn-search-date">Xác nhận</button>
                </div>
            </div>
        </div>
        <h2>Danh sách đơn hàng</h2>
        <div class="box">
            <select class="select select-order">  
                <option value="ASC">mới nhất</option>
                <option value="DESC">cũ nhất</option>
                <option value="not wait">đã xử lý</option>
                <option value="waiting">chưa xử lý</option>
                <option value="confirmed">đã xác nhận</option>
                <option value="cancelled">đã huỷ</option>
            </select>
            <select class="select select-location">
       
       <script>
       		var xhr2 = new XMLHttpRequest();
     		xhr2.open("POST", "addSortLocOrder", true);
       		xhr2.setRequestHeader("Content-Type", "application/json");
       		xhr2.onreadystatechange = function() {
    	   	if (xhr2.readyState === 4 && xhr2.status === 200) {
    	     	var data = JSON.parse(xhr2.responseText);
    	     	console.log(data);
    	     	var text = '<option value="">Khu vực</option>';
    	     	data.forEach((e)=>{ 
    	    		text += (e !="")? '<option value="' + e + '">' + e + '</option>':"";	 
    	     })
    	     document.querySelector(".select-location").innerHTML = text;   
    	   }}
       xhr2.send();
       
      </script>
       </select>
        </div>
        <div class="small-container oder-page1">
            <table>
                <tr>
                    <th>#</th>
                    <th>Mã đơn hàng</th>
                    <th>Khách Hàng</th>
                    <th>Thời gian</th>
                    <th>Thành tiền</th>
                    <th>Hình thức thanh toán</th>
                    <th>Tình trạng đơn</th>
                    <th>Hành động</th>
                    <th>Địa chỉ</th>
                </tr>
                
            </table>
        </div>
        <ul class="box list_page"></ul>
    </div>
    <script type="module" src="js/order.js"> </script>
</body>
</html>