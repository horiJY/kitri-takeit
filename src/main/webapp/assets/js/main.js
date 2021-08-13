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
	
	var c_val = sessionStorage.getItem('cSession');
	var r_val = sessionStorage.getItem('rSession');
	
	var userId = sessionStorage.getItem('userId');
	var mypageBtn = document.getElementById("mypage");
	
	//할 일 : mypage에 userId, logout, 페이징 

	function list(c_val, r_val){
		$.ajax({
			type: 'POST',
			url: 'range',
			data: { category: c_val, range: r_val },
			success: function(result) {
				$('#class').empty();
				for (var i = 0; i < result.length; i++) {
					$('#class').append(
						'<li><button onclick="classDetail('+result[i].classId+')" >'
						+ '<div><img></div>'
						+ '<div><div>' + result[i].creater + '</div>'
						+ '<div>' + result[i].className + '</div>'
						+ '<div>' + result[i].recommend + '</div></div>'
						+ '<div><div>' + result[i].price + '</div>'
						+ '<div>' + result[i].sale + '</div></div>'
						+ '<div><div>' + result[i].classType + '</div></div>'
						+ '</button></li>'
						);
				}		
			}, error: function(XMLHttpRequest, textStatus, errorThrown) {
				alert('error');
			}
		})
		
	}	
	
	list(c_val, r_val);
	
	category.onclick = function() {
		if (categoryDrop.style.display == "block") {
			categoryDrop.style.display = "none";
		} else {
			categoryDrop.style.display = "block";
			
			all.onclick = function() {
				category.value = "카테고리";
				c_val = 'null';
				r_val = sessionStorage.getItem('rSession');
				sessionStorage.setItem('cSession', c_val);
				categoryDrop.style.display = "none";
				list(c_val, r_val);
			}
			
			art.onclick = function() {
				category.value = art.value;
				c_val = 'ART';
				r_val = sessionStorage.getItem('rSession');
				sessionStorage.setItem('cSession', c_val);
				categoryDrop.style.display = "none";
				list(c_val, r_val);
			}
			
			cooking.onclick = function() {
				category.value = cooking.value;
				c_val = 'COOKING';
				r_val = sessionStorage.getItem('rSession');
				sessionStorage.setItem('cSession', c_val);
				categoryDrop.style.display = "none";
				list(c_val, r_val);
			}
			
			language.onclick = function() {
				category.value = language.value;
				c_val = 'LANGUAGE';
				r_val = sessionStorage.getItem('rSession');
				sessionStorage.setItem('cSession', c_val);
				categoryDrop.style.display = "none";
				list(c_val, r_val);
			}
			
			programming.onclick = function() {
				category.value = programming.value;
				c_val = 'PROGRAMMING';
				r_val = sessionStorage.getItem('rSession');
				sessionStorage.setItem('cSession', c_val);
				categoryDrop.style.display = "none";
				list(c_val, r_val);
			}
			
			sport.onclick = function() {
				category.value = sport.value;
				c_val = 'SPORT';
				r_val = sessionStorage.getItem('rSession');
				sessionStorage.setItem('cSession', c_val);
				categoryDrop.style.display = "none";
				list(c_val, r_val);
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
				c_val = sessionStorage.getItem('cSession');
				r_val = 'RECOMMEND';
				sessionStorage.setItem('rSession', r_val);
				rangeDrop.style.display = "none";
				list(c_val, r_val);
			}
			
			newest.onclick = function() {
				range.value = newest.value;
				c_val = sessionStorage.getItem('cSession');
				r_val = 'OPENDATE';
				sessionStorage.setItem('rSession', r_val);
				rangeDrop.style.display = "none";
				list(c_val, r_val);
			}
		}
	}
}
function classDetail(i){
	$.ajax({
		type: 'POST',
		url: 'detail',
		data: {classId: i},
		success: function() {
			console.log(i);
		}
	})
}

	


