<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

<section>
    <img src="img/loader.gif" class="loader" alt="">

    <div class="alert-box">
        <img class="alert-img" alt="">
        <p class="alert-msg">Thông báo lỗi</p>
    </div>
    <img src="img/dark-logo.png" class="logo" alt="">
<div class="nav-space">
        <div class="nav-admin">

        </div>
        <p class="add-product-title nav-link" onclick="location.href='Index'">trang chủ</p>
        <p class="add-product-title nav-link" onclick="location.href='user.jsp'">quản lý user</p>
        <p class="add-product-title nav-link" onclick="location.href='Order'">quản lý đơn hàng</p>
        <p class="add-product-title nav-link" onclick="location.href='order.jsp'">Thống kê doanh thu</p>
    </div>
<h4>Doanh thu theo: <select class="select select-type"></h4>
            <option value="month">Tháng </option>
            <option value="year">Năm</option>
        </select>
    <div class="nav-space">
       
        <select class="select select-month">
        </select>
        <select class="select select-year">
        </select>
    <div class="small-container cart-page">

    <button class="btn report btn-report">In báo cáo</button>
      
</div>
</div>
</section>

    <h1 class="info-date" style="font-size:30px"></h1><br>
  <div class="small-container oder-page1">
            <table>
              
                
            </table>
        </div>
        <ul class="box list_page"></ul>
<script>
    var text1= "<option value=0>Phân loại: Tháng</option>",text2 ="<option value=0>Phân loại: Năm</option>";
    for(var i=1;i<=12;i++){
        text1+="<option value= "+i+ ">"+i+"</option>";
    }
    for(var i=2000;i<=2023;i++){
        text2+="<option value= "+i+ ">"+i+"</option>";
    }
    document.querySelector(".select-month").innerHTML = text1;
    document.querySelector(".select-year").innerHTML = text2;
</script>
<script type="module" src="js/report.js"></script>
</body>
</html>