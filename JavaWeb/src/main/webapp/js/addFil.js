
var xhr1 = new XMLHttpRequest();
xhr1.open("GET", "addFilter", true);
xhr1.setRequestHeader("Content-Type", "application/json");
xhr1.onreadystatechange = function() {
  if (xhr1.readyState === 4 && xhr1.status === 200) {
    var data = JSON.parse(xhr1.responseText);
    	
  var s = ' <a class="titlefilter">Bộ lọc <img src="img/filter.png"></a><a class="nameselect-combo">Giá</a><select id="select-cost" class="select-combo"><option value="">chọn khoảng giá</option><option value="GiaSP<500">dưới 500 đô</option><option value="GiaSP>=500 AND GiaSP<800">từ 500 tới 800 đô</option> <option value="GiaSP>=800 AND GiaSP<1200">từ 800 tới 1200 đô</option> <option value="GiaSP>=1500">trên 1500 đô</option></select>'; 
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