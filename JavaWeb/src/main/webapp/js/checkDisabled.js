
var xhr4 = new XMLHttpRequest();
xhr4.open("GET", "CheckDisabledAccount", true);
xhr4.setRequestHeader("Content-Type", "application/json");
	xhr4.onload = function() {
		if (xhr4.status == 200) {
			if(xhr4.responseText == "disabled"){
				alert("Tài khoản của bạn đã bị khóa!")
				location.href = "XulyDangNhap";
				}
				else console.log("not block");
			}
			}
xhr4.send();			
