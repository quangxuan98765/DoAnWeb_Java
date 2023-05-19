﻿<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Armor : Đăng ký</title>

    <link rel="stylesheet" href="css/sigup.css">
</head>
<body>
    
    <img src="img/loader.gif" class="loader" alt="">

    <div class="alert-box">
        <img class="alert-img" alt="">
        <p class="alert-msg">Error message</p>
    </div>
	<form method="post" action="XulyDangKy">
    <div class="container">
        <img src="img/dark-logo.png" class="logo "alt="">
        <div>
            <input type="text" autocomplete="off" id="name" name="hoten" placeholder="họ tên">
            <input type="email" autocomplete="off" id="email" name="email" placeholder="email">
            <input type="text" autocomplete="off" id="username" name="username" placeholder="tên tài khoản">
            <input type="password" autocomplete="off" id="password" name="password" placeholder="mật khẩu">
            <input type="checkbox" checked class="checkbox" id="term-and-cond">
            <label for="term-and-cond">đồng ý <a href="">điều khoản và chính sách bảo mật</a></label>
            <br>
            <input type="checkbox" class="checkbox" id="term-and-cond">
            <label for="notification">nhận thông báo về những ưu đãi mới nhất</label>
            <button class="submit-btn" type="submit">Tạo Tài Khoản</button>
        </div>
        <a href="XulyDangNhap" class="link">Đã có tài khoản? Nhấn để đăng nhập</a>
	<a href="Index" class="link">Quay lại trang chủ</a>
    </div>
    </form>
    

    <script src="js/form.js"></script>

</body>
</html>