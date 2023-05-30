
document.querySelector(".select-type").addEventListener("change",(e)=>{
if (e.target.value == "month") {
    document.querySelector(".select-month").style.display = "block";
    document.querySelector(".select-month").disabled = false;
    document.querySelector(".select-month").selectedIndex = 0;
  } else if (e.target.value == "year") {
   	document.querySelector(".select-month").value = 0;
    document.querySelector(".select-month").style.display = "none";
    document.querySelector(".select-month").disabled = true;
  }
})
document.querySelector(".btn-report").addEventListener("click", () => {
	var month = document.querySelector(".select-month").value;
	var year = document.querySelector(".select-year").value;
	if(year == 0){alert ("Chọn đầy đủ"); 
			location.href="report.jsp";}
	else if(document.querySelector(".select-type").value == "month" && (month == 0 ||year== 0))
			{
			alert ("Chọn đầy đủ"); 
			location.href="report.jsp";}
	newFunc(month, year);
})
import { pagesToElement } from "../js/page.js";
function newFunc(month, year) {
	
	var obj = { year: year, month: month }
	var xhr5 = new XMLHttpRequest();
	console.log(obj);
	var xhr5 = new XMLHttpRequest();
	xhr5.open("GET", "Report?data=" + encodeURIComponent(JSON.stringify(obj)), true);
	xhr5.setRequestHeader("Content-Type", "application/json");
	xhr5.onload = function() {
		if (xhr5.readyState === 4 && xhr5.status === 200) {
			var data1 = JSON.parse(xhr5.responseText);
			var data = data1.data;
			var total = (typeof data1.tong) == "undefined"?0:data1.tong;
			console.log(month +"ss"+ year + "ss"+total);
			document.querySelector(".info-date").innerText = "Tổng doanh thu trong "+(month==0?"năm " : "tháng " + month +"/") + year +": " + total+" $" ;
			let DpP = 8;
			pagesToElement(data.length, DpP, document.querySelector(".list_page"), function myFunc(num) {
				var p = document.querySelector(".oder-page1").querySelector("table");
				var s = ' <tr style="background-color:black"><th>#</th><th>Mã đơn hàng</th><th>Khách Hàng</th><th>Thời gian</th><th>Thành tiền</th></tr>';
				for (let i = 0; i < DpP && (DpP * (num - 1) + i) < data.length; i++) {
					var page = DpP * (num - 1) + i;
					s += '<tr class="data-row" data-id="' + data[page].id + '"><td><p style="color:black">' + (i + 1) + `</p><td><a class="see-full" data-id="${data[page].id}"`
						+ '">' + data[page].id + '</a><td><p>' + data[page].tentaikhoan + '</p></td><td><a>' + data[page].date + '</a></td><td><a>'
						+ parseInt(data[page].cost) + ' $</a></td></tr>'; 
				}
				p.innerHTML = s;
			})
			document.querySelectorAll(".see-full").forEach(function(element){
		      element.addEventListener("click",(e)=>{
		        e.preventDefault();
		        const encodedId = btoa(element.dataset.id);
				const params = new URLSearchParams({ iddh: encodedId });
		          const url = "OrderDetail.jsp?" + params.toString();
		          window.location.href = url;
		      })
			})
}
}
xhr5.send();
}

