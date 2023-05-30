<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="vi">
<meta charset="utf-8">
<html>
  <head>
    <title>Order form</title>
    <style>
      html, body {
      height: 100%;
      }
      body, input, select { 
      padding: 0;
      margin: 0;
      outline: none;
      font-family: Roboto, Arial, sans-serif;
      font-size: 16px;
      color: #eee;
      }
      h1, h3 {
      font-weight: 400;
      }
      h1 {
      font-size: 32px;
      }
      h3 {
      color: #1c87c9;
      }
      .main-block, .info {
      display: flex;
      flex-direction: column;
      }
      .main-block {
      justify-content: center;
      align-items: center;
      width: 100%; 
      min-height: 100%;
      background: url("/uploads/media/default/0001/01/e7a97bd9b2d25886cc7b9115de83b6b28b73b90b.jpeg") no-repeat center;
      background-size: cover;
      }
      form {
      width: 80%; 
      padding: 25px;
      margin-bottom: 20px;
      background: rgba(0, 0, 0, 0.9);
      }
      input, select {
      padding: 5px;
      margin-bottom: 20px;
      background: transparent;
      border: none;
      border-bottom: 1px solid #eee;
      }
      input::placeholder {
      color: #eee;
      }
      option {
      background: black; 
      border: none;
      }
      .metod {
      display: flex; 
      }
      input[type=radio] {
      display: none;
      }
      label.radio {
      position: relative;
      display: inline-block;
      margin-right: 20px;
      text-indent: 32px;
      cursor: pointer;
      }
      label.radio:before {
      content: "";
      position: absolute;
      top: -1px;
      left: 0;
      width: 17px;
      height: 17px;
      border-radius: 50%;
      border: 2px solid #1c87c9;
      }
      label.radio:after {
      content: "";
      position: absolute;
      width: 8px;
      height: 4px;
      top: 5px;
      left: 5px;
      border-bottom: 3px solid #1c87c9;
      border-left: 3px solid #1c87c9;
      transform: rotate(-45deg);
      opacity: 0;
      }
      input[type=radio]:checked + label:after {
      opacity: 1;
      }
      button {
      display: block;
      width: 200px;
      padding: 10px;
      margin: 20px auto 0;
      border: none;
      border-radius: 5px; 
      background: #1c87c9; 
      font-size: 14px;
      font-weight: 600;
      color: #fff;
      }
      button:hover {
      background: #095484;
      }
      @media (min-width: 568px) {
      .info {
      justify-content: space-between;
      }
      input {
      width: 46%;
      }
      input.fname {
      width: 100%;
      }
      select {
      width: 48%;
      }
      }
    </style>
  </head>
  <body>
    <div class="main-block">
      <h1>Order Form</h1>
      <form name="themsp" method="post" action="addDiaChi">
        <div class="info">
          <input type="text" name="city" placeholder="Thành phố(Tỉnh)/Quận(Huyện)/Phường(Xã)">
          <input type="text" name="duong" placeholder="Đường/Tòa Nhà">
          <input type="text" name="nha" placeholder="Số Nhà/Tầng">
          <input type="text" name="test" placeholder="Địa chỉ dự phòng(nếu có)">
        </div>
        <h3>Loại địa chỉ</h3>
        <div class="metod">
          <div> 
            <input type="radio" value="none" id="radioOne" name="metod" checked/>
            <label for="radioOne" class="radio">Nhà</label>
          </div>
          <div>
            <input type="radio" value="none" id="radioTwo" name="metod" />
            <label for="radioTwo" class="radio">Văn phòng</label>
          </div>
        </div>
        <button name="submit" class="button" onclick="return validateForm()">Lưu</button>
      </form>
      <script>
        function validateForm() {
                var gia = document.forms["themsp"]["city"].value;
                var name = document.forms["themsp"]["duong"].value;
                var img1 = document.forms["themsp"]["nha"].value;

                if (name == "" || gia =="" || img1 == "" ) {
                    alert("Vui lòng nhập đủ thông tin");
                    return false;
                }
            }
    </script>
    
    </div>
  </body>
</html>