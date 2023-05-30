﻿<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thêm sản phẩm</title>
    <link rel="stylesheet" href="css/sigup.css">
    <link rel="stylesheet" href="css/addProduct.css">
    <link rel="stylesheet" href="css/cart.css">
</head>
<body>
    <img src="img/loader.gif" class="loader" alt="">

    <div class="alert-box">
        <img src="img/error.png" class="alert-img" alt="">
        <p class="alert-msg">Lỗi</p>
    </div>

    <img src="img/dark-logo.png" class="logo" alt="">
    <form name="themsp" method="post" action="addProduct" enctype="multipart/form-data">
        <div class="form">
            <input type="text" id="product-name" name="ten_sp" placeholder="Tên sản phẩm">
            <input type="text" id="short-des" name="ma_sp" placeholder="Mã sản phẩm">
            <textarea id="des" name="mota_sp" placeholder="Mô tả chi tiết về sản phẩm"></textarea>
        </div>

        <!-- product image -->
            <!-- upload inputs -->
        <div class="upload-image-sec">
            <p class="text"><img src="img/camera.png" alt="">tải ảnh lên</p>
            <div class="upload-catalouge">
                <input type="file" class="fileupload" id="image-upload1" name="fileParts1" accept="image/*" style="width: 90px;">
                <img id="image-preview1" style="display: block;width: 100px;">
                <input type="file" class="fileupload" id="image-upload2" name="fileParts2" accept="image/*" style="width: 90px;">
                <img id="image-preview2" style="display: block;width: 100px;">
                <input type="file" class="fileupload" id="image-upload3" name="fileParts3" accept="image/*" style="width: 90px;">
                <img id="image-preview3" style="display: block;width: 100px;">
                <input type="file" class="fileupload" id="image-upload4" name="fileParts4" accept="image/*" style="width: 90px;">
                <img id="image-preview4" style="display: block;width: 100px;">
            </div>
        </div>
        <script>
            const imageUploads = document.querySelectorAll('.fileupload');
            const imagePreviews = document.querySelectorAll('img[id^="image-preview"]');
        
            for (let i = 0; i < imageUploads.length; i++) {
                imageUploads[i].addEventListener('change', function() {
                    const file = this.files[0];
                    const imagePreview = imagePreviews[i];
        
                    if (file) {
                        const reader = new FileReader();
        
                        reader.addEventListener('load', function() {
                            imagePreview.setAttribute('src', this.result);
                            imagePreview.style.display = 'block';
                        });
        
                        reader.readAsDataURL(file);
                    }
                });
            }
        </script>

        <div class="product-price">
            <input type="number" id="selling-price" name="gia_sp" placeholder="giá bán">
        </div>

        <input type="number" id="stock" min="20" placeholder="Nhập số lượng vào kho (tối thiểu 20)">

        <select class="select" name="thuong_hieu">
            <option value = "0">chọn Thương Hiệu</option>
            <option value = "DELL">dell</option>
            <option value = "ACER">acer</option>
            <option value = "ASUS">asus</option>
            <option value = "MSI">MSI</option>
            <option value = "LENOVO">lenovo</option>
        </select>
        <select class="select" name="loai_sp">
            <option value = "0">Chọn loại</option>
            <option value = "laptop">Laptop</option>
            <option value = "phụ kiện">Phụ kiện</option>
        </select>

        <script>
            function validateForm() {
                var gia = document.forms["themsp"]["selling-price"].value;
                var name = document.forms["themsp"]["product-name"].value;
                var img1 = document.forms["themsp"]["image-upload1"].value;
                var img2 = document.forms["themsp"]["image-upload2"].value;
                var img3 = document.forms["themsp"]["image-upload3"].value;
                var img4 = document.forms["themsp"]["image-upload4"].value;
                if (name == "" || gia =="" || img1 == "" || img2 == "" || img3 == "" || img4 =="") {
                    alert("Bạn cần nhập tên,giá sản phẩm và tải ảnh sản phẩm lên trước khi thêm sản phẩm.");
                    return false;
                }
            }
        </script>

        <div class="buttons">
            <button class="btn" id="add-btn" name="submitThemsp" onclick="return validateForm()">thêm sản phẩm</button>
        </div>
        <div class="message">
		    <c:if test="${not empty message}">
		        ${message}
		    </c:if>
		</div>
</form>
    
</body>
</html>