import { pagesToElement } from "../js/page.js";
firstFunc();
export function firstFunc() {
	document.querySelector(".search-btn").addEventListener("click", (e) => {
		var searchBox = document.querySelector(".search-box");
		var inputValue = searchBox.value;
		if (inputValue.trim() == "" || inputValue.trim() == " ") {
			location.href = "searchFilter.jsp";
		} else {
			const params = new URLSearchParams({ searchValue: inputValue });
			const url = "searchFilter.jsp?" + params.toString();
			window.location.href = url;
		
			window.addEventListener("DOMContentLoaded", function() {
				newFunc();
			});
		}
	});
}
export function newFunc() {

	const searchValue = new URLSearchParams(window.location.search).get(
		"searchValue"
	);
	document.querySelector(".search-box").value = searchValue;
	if (searchValue != null){
		document.querySelector(".product-category").innerText =
			"Tìm kiếm cho '" + searchValue + "'";
			document.querySelector(".product-category").style.textTransform = "none";
			}
	const selectElement = document.querySelectorAll(".select-combo");
	selectElement.forEach((e) => {
		if (!e.onchange) {
			 e.onchange = () => {
           		 newFunc();
        		};
		}
	});
	// var e = document.querySelector(".list_page");
	var getIdBrand = document.getElementById("select-brand").value;
	var getIdCategory = document.getElementById("select-category").value;
	var getCost = document.getElementById("select-cost").value;
	var getSort = document.getElementById("select-sort").value;
	var getSearch = document.querySelector(".search-box");


	let obj = {};

	if (getSearch.value.trim() !== "" && getSearch.value.trim() !== " ") {
		obj.searchValue = getSearch.value;
	}

	if (getIdBrand !== "") {
		obj.brand_id = getIdBrand;
	}

	if (getIdCategory !== "") {
		obj.category_id = getIdCategory;
	}

	if (getCost !== "") {
		obj.GiaSP = getCost;
	}

	if (getSort !== "") {
		obj.sort = getSort;
	}

	var xhr = new XMLHttpRequest();
	xhr.open("GET", "LaptopProduct?data="+ encodeURIComponent(JSON.stringify(obj)) , true);
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.onload = function() {
 	 if (xhr.readyState === 4 && xhr.status === 200){
			var data = JSON.parse(xhr.responseText);

			var DpP = 9;
			pagesToElement(data.length,DpP,
				document.querySelector(".list_page"),
				(num) => {
					var p = document.querySelector(".order-page");
					var s = "";
					var role = '<%= session.getAttribute("role") %>'; // Lấy giá trị của biến 'role' từ session
					for (let i = 0; i < DpP && DpP * (num - 1) + i < data.length; i++) {
						if (i % 3 == 0) s += '<div class="product-container">';
						var page = DpP * (num - 1) + i;
						s += '<div class="product-card"><div class="product-image">';
						s += '<a href="Product?Masp=' + data[page].masp + '">';
						if (role === 'admin') {
					        s += '<img src="' + data[page].hinhsp + '" class="product-thumb"> <button class="card-btn">mua ngay</button>';
					        s += '<a href="editProduct?id=' + data[page].id + '"><button class="card-action-btn edit-btn">Sửa</button></a>';
					        s += '<a href="DeleteProduct?id=' + data[page].id + '" onclick="return confirm(\'Are you sure?\');"><button class="card-action-btn delete-popup-btn">Xóa</button></a>';
					    } else {
					        s += '<img src="' + data[page].hinhsp + '" class="product-thumb"><button class="card-btn">mua ngay</button>';
					    }
					    s += '</a></div><div class="product-info"><h2 class="product-brand">' + data[page].tensp + '(' + data[page].masp + ')</h2>';
						s += '<p class="product-short-des">' + data[page].motasp + '</p>';
						var gia = parseInt(data[page].giasp);
						s += '<span class="price">' + gia + ' $</span></div></div>';
						if ((i % 3 == 2 && i != 0) || page == data.length) s += "</div>";
					}
					p.innerHTML = s;
				}
			);
			};
		};
		
		xhr.send();
	};
