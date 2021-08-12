window.onload = function() {

	var category = document.getElementById("category");
	var categoryDrop = document.getElementById("category-drop");

	var all = document.getElementById("all");
	var art = document.getElementById("art");
	var cooking = document.getElementById("cooking");
	var language = document.getElementById("language");
	var programming = document.getElementById("programming");
	var sport = document.getElementById("sport");

	var range = document.getElementById("range");
	var rangeDrop = document.getElementById("range-drop");

	var recommend = document.getElementById("recommend");
	var newest = document.getElementById("newest");

	category.onclick = function() {

		if (categoryDrop.style.display == "block") {
			categoryDrop.style.display = "none";
		} else {
			categoryDrop.style.display = "block";
			all.onclick = function() {
				var c_val = 'null';
				var r_val = range.value;
				categoryDrop.style.display = "none";
				$.ajax({
					type: 'POST',
					url: 'range',
					dataType: 'json',
					contentType: "application/x-www-form-urlencoded; charset=UTF-8",
					data: { category: c_val, range: r_val },
					success: function(result) {
						alert(result);

					}, error: function(XMLHttpRequest, textStatus, errorThrown) {
						alert('There is an error : method(group)에 에러가 있습니다.');
					}
				})

			}
			art.onclick = function() {
				var c_val = 'ART';
				var r_val = range.value;
				categoryDrop.style.display = "none";
				$.ajax({
					type: 'POST',
					url: 'range',
					dataType: 'json',
					contentType: "application/x-www-form-urlencoded; charset=UTF-8",
					data: { category: c_val, range: r_val },
					success: function(result) {
						alert(result);

					}, error: function(XMLHttpRequest, textStatus, errorThrown) {
						alert('There is an error : method(group)에 에러가 있습니다.');
					}
				})
			}
			cooking.onclick = function() {
				var c_val = 'COOKING';
				var r_val = range.value;
				categoryDrop.style.display = "none";
				$.ajax({
					type: 'POST',
					url: 'range',
					dataType: 'json',
					contentType: "application/x-www-form-urlencoded; charset=UTF-8",
					data: { category: c_val, range: r_val },
					success: function(result) {
						alert(result);

					}, error: function(XMLHttpRequest, textStatus, errorThrown) {
						alert('There is an error : method(group)에 에러가 있습니다.');
					}
				})
			}
			language.onclick = function() {
				var c_val = 'LANGUAGE';
				var r_val = range.value;
				categoryDrop.style.display = "none";
				$.ajax({
					type: 'POST',
					url: 'range',
					dataType: 'json',
					contentType: "application/x-www-form-urlencoded; charset=UTF-8",
					data: { category: c_val, range: r_val },
					success: function(result) {
						alert(result);

					}, error: function(XMLHttpRequest, textStatus, errorThrown) {
						alert('There is an error : method(group)에 에러가 있습니다.');
					}
				})
			}
			programming.onclick = function() {
				var c_val = 'PROGRAMMING';
				var r_val = range.value;
				categoryDrop.style.display = "none";
				$.ajax({
					type: 'POST',
					url: 'range',
					dataType: 'json',
					contentType: "application/x-www-form-urlencoded; charset=UTF-8",
					data: { category: c_val, range: r_val },
					success: function(result) {
						alert(result);

					}, error: function(XMLHttpRequest, textStatus, errorThrown) {
						alert('There is an error : method(group)에 에러가 있습니다.');
					}
				})
			}
			sport.onclick = function() {
				var c_val = 'SPORT';
				var r_val = range.value;
				categoryDrop.style.display = "none";
				$.ajax({
					type: 'POST',
					url: 'range',
					dataType: 'json',
					contentType: "application/x-www-form-urlencoded; charset=UTF-8",
					data: { category: c_val, range: r_val },
					success: function(result) {
						let json = JSON.parse(result);
						let res = "";
						for(let i = 0; i < json.lenth; i++){
							res +="<li>"
							+"<a><div><img>"+"</div>"
							+"<div><div>"+json[i].creater+"</div>"
							+"<div>"+json[i].className+"</div>"
							+"<div>"+json[i].recommend+"</div></div>"
							+"<div><div>"+json[i].price+"</div>"
							+"<div>"+json[i].sale+"</div></div>"
							+"<div><div>"+json[i].classType+"</div></div>"
							+"</div></a></li>"
						}
						$("#class").html(res);
					}, error: function(XMLHttpRequest, textStatus, errorThrown) {
						alert('There is an error : method(group)에 에러가 있습니다.');
					}
				})
			}
		}
	}
	range.onclick = function() {

		if (rangeDrop.style.display == "block") {
			rangeDrop.style.display = "none";
		} else {
			rangeDrop.style.display = "block";

			recommend.onclick = function() {
				var c_val = category.value;
				var r_val = range.value;
				categoryDrop.style.display = "none";
				$.ajax({
					type: 'POST',
					url: 'range',
					dataType: 'json',
					contentType: "application/x-www-form-urlencoded; charset=UTF-8",
					data: { category: c_val, range: r_val },
					success: function(result) {
						alert(result);

					}, error: function(XMLHttpRequest, textStatus, errorThrown) {
						alert('There is an error : method(group)에 에러가 있습니다.');
					}
				})
			}
			newest.onclick = function() {
				var c_val = category.value;
				var r_val = range.value;
				categoryDrop.style.display = "none";
				$.ajax({
					type: 'POST',
					url: 'range',
					dataType: 'json',
					contentType: "application/x-www-form-urlencoded; charset=UTF-8",
					data: { category: c_val, range: r_val },
					success: function(result) {
						alert(result);

					}, error: function(XMLHttpRequest, textStatus, errorThrown) {
						alert('There is an error : method(group)에 에러가 있습니다.');
					}
				})
			}
		}
	}
}


