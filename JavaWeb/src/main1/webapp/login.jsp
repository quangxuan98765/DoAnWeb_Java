<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Armor: Đăng nhập</title>

    <link rel="stylesheet" href="css/sigup.css">
</head>
<body>

    <img src="img/loader.gif" class="loader" alt="">

    <div class="alert-box">
        <img class="alert-img" alt="">
        <p class="alert-msg"></p>
    </div>

    <div>
        <img src="img/dark-logo.png" class="logo "alt="">
        <form id="login-form" method="post" action="XulyDangNhap">
            <input type="text" autocomplete="off" id="username" name="username" placeholder="Tên tài khoản">
            <div id="error-msg">${error}</div>
            <input type="password" autocomplete="off" id="password" name="password" placeholder="Mật Khấu">
            <button class="submit-btn" type="submit">Đăng nhập</button>
            <a href="XulyDangKy" class="link">Chưa có tài khoản? Đăng ký</a>
        </form>
    </div>
</body>
</html>