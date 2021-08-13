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
	
	sessionStorage.clear();
	var cSession;
	sessionStorage.setItem('cSession', 'null');
	var rSession;
	sessionStorage.setItem('rSession', 'null');
	
	category.onclick = function() {
		if (categoryDrop.style.display == "block") {
			categoryDrop.style.display = "none";
		} else {
			categoryDrop.style.display = "block";
			all.onclick = function() {
				var c_val = 'null';
				var r_val = sessionStorage.getItem('rSession');
				sessionStorage.setItem('cSession', c_val);
				categoryDrop.style.display = "none";
				$.ajax({
					type: 'POST',
					url: 'range',
					dataType: 'json',
					contentType: "application/x-www-form-urlencoded; charset=UTF-8",
					data: { category: c_val, range: r_val },
					success: function(result) {
						$('#class').empty();
						for(var i = 0; i < result.length; i ++){
							$('#class').append('<li><a>'
							//+'<div><img></div>'
							+'<div><div>'+result[i].creater+'</div>'
							+'<div>'+result[i].calssName+'</div>'
							+'<div>'+result[i].recommend+'</div></div>'
							+'<div><div>'+result[i].price+'</div>'
							+'<div>'+result[i].sale+'</div></div>'
							+'<div><div>'+result[i].classType+'</div></div>'
							+'</a></li>');	
						}
					}, error: function(XMLHttpRequest, textStatus, errorThrown) {
						alert('There is an error : method(group)에 에러가 있습니다.');
					}
				})
			}
			art.onclick = function() {
				category.value = art.value;
				var c_val = 'ART';
				var r_val = sessionStorage.getItem('rSession');
				sessionStorage.setItem('cSession', c_val);
				categoryDrop.style.display = "none";
				$.ajax({
					type: 'POST',
					url: 'range',
					dataType: 'json',
					contentType: "application/x-www-form-urlencoded; charset=UTF-8",
					data: { category: c_val, range: r_val },
					success: function(result) {
						$('#class').empty();
						for(var i = 0; i < result.length; i ++){
							$('#class').append('<li><a>'
							//+'<div><img></div>'
							+'<div><div>'+result[i].creater+'</div>'
							+'<div>'+result[i].calssName+'</div>'
							+'<div>'+result[i].recommend+'</div></div>'
							+'<div><div>'+result[i].price+'</div>'
							+'<div>'+result[i].sale+'</div></div>'
							+'<div><div>'+result[i].classType+'</div></div>'
							+'</a></li>');	
						}
					}, error: function(XMLHttpRequest, textStatus, errorThrown) {
						alert('There is an error : method(group)에 에러가 있습니다.');
					}
				})
			}
			cooking.onclick = function() {
				category.value = cooking.value;
				var c_val = 'COOKING';
				var r_val = sessionStorage.getItem('rSession');
				sessionStorage.setItem('cSession', c_val);
				categoryDrop.style.display = "none";
				$.ajax({
					type: 'POST',
					url: 'range',
					dataType: 'json',
					contentType: "application/x-www-form-urlencoded; charset=UTF-8",
					data: { category: c_val, range: r_val },
					success: function(result) {
						$('#class').empty();
						for(var i = 0; i < result.length; i ++){
							$('#class').append('<li><a>'
							//+'<div><img></div>'
							+'<div><div>'+result[i].creater+'</div>'
							+'<div>'+result[i].calssName+'</div>'
							+'<div>'+result[i].recommend+'</div></div>'
							+'<div><div>'+result[i].price+'</div>'
							+'<div>'+result[i].sale+'</div></div>'
							+'<div><div>'+result[i].classType+'</div></div>'
							+'</a></li>');	
						}
					}, error: function(XMLHttpRequest, textStatus, errorThrown) {
						alert('There is an error : method(group)에 에러가 있습니다.');
					}
				})
			}
			language.onclick = function() {
				category.value = language.value;
				var c_val = 'LANGUAGE';
				var r_val = sessionStorage.getItem('rSession');
				sessionStorage.setItem('cSession', c_val);
				categoryDrop.style.display = "none";
				$.ajax({
					type: 'POST',
					url: 'range',
					dataType: 'json',
					contentType: "application/x-www-form-urlencoded; charset=UTF-8",
					data: { category: c_val, range: r_val },
					success: function(result) {
						$('#class').empty();
						for(var i = 0; i < result.length; i ++){
							$('#class').append('<li><a>'
							//+'<div><img></div>'
							+'<div><div>'+result[i].creater+'</div>'
							+'<div>'+result[i].calssName+'</div>'
							+'<div>'+result[i].recommend+'</div></div>'
							+'<div><div>'+result[i].price+'</div>'
							+'<div>'+result[i].sale+'</div></div>'
							+'<div><div>'+result[i].classType+'</div></div>'
							+'</a></li>');	
						}
					}, error: function(XMLHttpRequest, textStatus, errorThrown) {
						alert('There is an error : method(group)에 에러가 있습니다.');
					}
				})
			}
			programming.onclick = function() {
				category.value = programming.value;
				var c_val = 'PROGRAMMING';
				var r_val = sessionStorage.getItem('rSession');
				sessionStorage.setItem('cSession', c_val);
				categoryDrop.style.display = "none";
				$.ajax({
					type: 'POST',
					url: 'range',
					dataType: 'json',
					contentType: "application/x-www-form-urlencoded; charset=UTF-8",
					data: { category: c_val, range: r_val },
					success: function(result) {
						$('#class').empty();
						for(var i = 0; i < result.length; i ++){
							$('#class').append('<li><a>'
							//+'<div><img></div>'
							+'<div><div>'+result[i].creater+'</div>'
							+'<div>'+result[i].calssName+'</div>'
							+'<div>'+result[i].recommend+'</div></div>'
							+'<div><div>'+result[i].price+'</div>'
							+'<div>'+result[i].sale+'</div></div>'
							+'<div><div>'+result[i].classType+'</div></div>'
							+'</a></li>');	
						}
					}, error: function(XMLHttpRequest, textStatus, errorThrown) {
						alert('There is an error : method(group)에 에러가 있습니다.');
					}
				})
			}
			sport.onclick = function() {
				category.value = sport.value;
				var c_val = 'SPORT';
				var r_val = sessionStorage.getItem('rSession');
				sessionStorage.setItem('cSession', c_val);
				categoryDrop.style.display = "none";
				$.ajax({
					type: 'POST',
					url: 'range',
					dataType: 'json',
					contentType: "application/x-www-form-urlencoded; charset=UTF-8",
					data: { category: c_val, range: r_val },
					success: function(result) {
						$('#class').empty();
						for(var i = 0; i < result.length; i ++){
							$('#class').append('<li><a>'
							//+'<div><img></div>'
							+'<div><div>'+result[i].creater+'</div>'
							+'<div>'+result[i].calssName+'</div>'
							+'<div>'+result[i].recommend+'</div></div>'
							+'<div><div>'+result[i].price+'</div>'
							+'<div>'+result[i].sale+'</div></div>'
							+'<div><div>'+result[i].classType+'</div></div>'
							+'</a></li>');	
						}
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
				range.value = recommend.value;
				var c_val = sessionStorage.getItem('cSession');
				var r_val = 'RECOMMEND';
				sessionStorage.setItem('rSession', r_val);
				rangeDrop.style.display = "none";
				$.ajax({
					type: 'POST',
					url: 'range',
					dataType: 'json',
					contentType: "application/x-www-form-urlencoded; charset=UTF-8",
					data: { category: c_val, range: r_val },
					success: function(result) {
						$('#class').empty();
						for(var i = 0; i < result.length; i ++){
							$('#class').append('<li><a>'
							//+'<div><img></div>'
							+'<div><div>'+result[i].creater+'</div>'
							+'<div>'+result[i].calssName+'</div>'
							+'<div>'+result[i].recommend+'</div></div>'
							+'<div><div>'+result[i].price+'</div>'
							+'<div>'+result[i].sale+'</div></div>'
							+'<div><div>'+result[i].classType+'</div></div>'
							+'</a></li>');	
						}
					}, error: function(XMLHttpRequest, textStatus, errorThrown) {
						alert('There is an error : method(group)에 에러가 있습니다.');
					}
				})
			}
			newest.onclick = function() {
				range.value = newest.value;
				var c_val = sessionStorage.getItem('cSession');
				var r_val = 'OPENDATE';
				sessionStorage.setItem('rSession', r_val);
				rangeDrop.style.display = "none";
				$.ajax({
					type: 'POST',
					url: 'range',
					dataType: 'json',
					contentType: "application/x-www-form-urlencoded; charset=UTF-8",
					data: { category: c_val, range: r_val },
					success: function(result) {
						$('#class').empty();
						for(var i = 0; i < result.length; i ++){
							$('#class').append('<li><a>'
							//+'<div><img></div>'
							+'<div><div>'+result[i].creater+'</div>'
							+'<div>'+result[i].calssName+'</div>'
							+'<div>'+result[i].recommend+'</div></div>'
							+'<div><div>'+result[i].price+'</div>'
							+'<div>'+result[i].sale+'</div></div>'
							+'<div><div>'+result[i].classType+'</div></div>'
							+'</a></li>');	
						}
					}, error: function(XMLHttpRequest, textStatus, errorThrown) {
						alert('There is an error : method(group)에 에러가 있습니다.');
					}
				})
			}
		}
	}
}


