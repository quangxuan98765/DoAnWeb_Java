
var xhr1 = new XMLHttpRequest();
xhr1.open("GET", "addFilter", true);
xhr1.setRequestHeader("Content-Type", "application/json");
xhr1.onreadystatechange = function() {
  if (xhr1.readyState === 4 && xhr1.status === 200) {
    var data = JSON.parse(xhr1.responseText);
    	
  var s = ' <a class="titlefilter">Bộ lọc <img src="img/filter.png"></a><a class="nameselect-combo">Giá</a><select id="select-cost" class="select-combo"><option value="">chọn khoảng giá</option><option value="GiaSP<2000000">dưới 2 triệu</option><option value="GiaSP>=2000000 AND GiaSP<4000000">từ 2 tới 4 triệu</option> <option value="GiaSP>=4000000 AND GiaSP<6000000">từ 4 tới 6 triệu</option> <option value="GiaSP>=6000000">trên 6 triệu</option></select>'; 
    s+='<a class="nameselect-combo">Thương hiệu</a><select id="select-brand" class="select-combo"><option value ="">Chọn thương hiệu</option>';
    Object.keys(data["brands"]).forEach(function(key) {
        s+= '<option value ="'+key + '">' + data["brands"][key] + '</option>'; 
      });
    s+='</select><a class="nameselect-combo">Loại</a><select id="select-category" class="select-combo"><option value ="">Chọn loại</option>';
    Object.keys(data["category"]).forEach(function(key) {
        s+= '<option value ="'+key + '">' + data["category"][key] + '</option>'; 
      });
    document.querySelector(".box-select").innerHTML = s;
    
	
}
}

xhr1.send();