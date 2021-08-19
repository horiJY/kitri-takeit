	var hostIndex = location.href.indexOf(location.host) + location.host.length;
	var classId;

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

	var favorite = document.getElementById("favorite");
	var newest = document.getElementById("newest");

	sessionStorage.clear();
	var cSession;
	sessionStorage.setItem('cSession', 'null');
	var rSession;
	sessionStorage.setItem('rSession', 'null');

	var c_val = sessionStorage.getItem('cSession');
	var r_val = sessionStorage.getItem('rSession');

	var logoutBtn = document.getElementById('logout');

	var dataPerPage = 8; //한 페이지에 나타낼 글 수
	var pageCount = 5; //페이징에 나타낼 페이지 수
	var currentPage = 1; //현재 페이지
	var totalData;
	var type = 'P';
	
	var favoriteCnt;
	
	var swiper = document.getElementById("swiper");
	
	var creater;
	var className;
	
	function logout() {
		$.ajax({
			type: 'POST',
			url: 'logout',
			success: function(data) {
				document.location.reload();
				console.log(id);
			}
		})
	}


	function list(c_val, r_val, type) {
		$.ajax({
			type: 'POST',
			url: 'range',
			async: false,
			data: { category: c_val, range: r_val, type: type },
			success: function(result) {
				for(var i = 0; i < result.length; i++){
					favoriteCnt = result[i].favorite;
					update(favoriteCnt);
				}
				totalData = result.length;		
				paging(c_val, r_val, totalData, dataPerPage, pageCount, currentPage);		
			}
		})
	}
	
	function paging(c_val, r_val, totalData, dataPerPage, pageCount, currentPage){
		var totalPage = Math.ceil(totalData/dataPerPage);
		var pageGroup = Math.ceil(currentPage/pageCount);
		
		var last = pageGroup * pageCount;
		if(last > totalPage){
			last = totalPage;
		} 
		var first = last - (pageCount - 1);
		var next = last + 1;
		var prev = first - 1;
		
		if(totalPage < 1){
			first = last;
		}
		
		var start = (currentPage - 1) * dataPerPage+1;
		var end = (currentPage - 1) * dataPerPage + dataPerPage;
		
		$.ajax({
			type: 'POST',
			url: 'paging',
			async: false,
			data: { category: c_val, range: r_val, type: 'P', start: start, end: end },
			success: function(result) {
				let pageHtml = "";
				if (prev > 0) {
					pageHtml += '<li><button onclick="pageClickPN()" id="prev"> 이전 </li>';
				}
				for (var i = first; i <= last; i++) {
					if (currentPage == i) {
						pageHtml +=
							'<li class="on"><button onclick="pageClick('+i+')" id="' + i + '">' + i + '</li>';
					} else if(i > 0){
						pageHtml += '<li><button onclick="pageClick('+i+')" id="' + i + '">' + i + '</li>';
					}
				}
				if (last < totalPage) {
					pageHtml += '<li><button onclick="pageClickPN()" id="next"> 다음 </a></li>';
				}

				$("#paging").html(pageHtml);
				
				$('#class').empty();
				
				for (var i = 0; i < result.length; i++) {
					$('#class').append(
						'<li class="detail"><label onclick="classDetail('+result[i].classId +')" class="label">'
						+ '<div><img></div>'
						+ '<div><div>' + result[i].creater + '</div>'
						+ '<div>' + result[i].className + '</div>'
						+ '<div>' + result[i].favorite + '</div></div>'
						+ '<div><div>' + result[i].price + '</div>'
						+ '<div>' + result[i].sale + '</div></div>'
						+ '<div><div>' + result[i].classType + '</div></div>'
						+ '<div><div><span>응원 마감까지 ' + result[i].countdown + '일</span></div></div></label>'
						+ '</li>'
					);	
				}
			}
		})	
	}

function update(favoriteCnt) {
	$.ajax({
		type: 'POST',
		url: 'pre-class-update',
		async: false,
		data: { favorite: favoriteCnt },
		success: function(result) {

		}
	})
}
var favoriteBtn = document.getElementById("favorite-btn");

function classDetail(classId) {
	const floating = document.querySelector('#floating');
  
	 floating.style.display = 'block';

	$.ajax({
		type: 'POST',
		url: 'select-class',
		data: { classId: classId },
		success: function(result) {
			var classType = "";
			if(result[0].classType = "ON"){
				classType = "온라인";
			}else{
				classType ="오프라인";
			}
			
			$('#favorite-btn').empty();
			$('#swiper').empty();
			$('#swiper').append(
				'<button onclick="close()">닫기</button></div>'
				+ '<div id="slide"><img>'
				+ '<div>' + result[0].creater + ' 의 </div>'
				+ '<div>' + result[0].className + ' 수업 </div></div>'
				+ '<div> 이 강의는 ' + classType + '강의예요!</div>'
				+ '<div><div id="favoriteF">'
				+ '<div><span>현재 응원 수 </span>'
				+ '<span>' + result[0].favorite + '</span>'
				+ '<span> / 10 명</span></div></div></div>'
			);
		selectFavorite(classId);
		}
	})
	swiper.style.display = "block";
}

function closeDetail(){
	const floating = document.querySelector('#floating');
	floating.style.display = 'none';
	swiper.style.display = 'none';
}


function selectFavorite(classId){
	favoriteBtn.style.display = "none";
	$.ajax({
		type: 'POST',
		url: 'favorite',
		data: { classId: classId },
		success: function(result) {
			if(Number(result) == 0){
				$('#favorite-btn').append(
					'<div><button onclick="favoriteClick('+classId+')">응원하기</button></div>'
				);
			}else if(Number(result) == 1){
				$('#favorite-btn').append(
					'<div><button>응원완료</button></div>'
				);
			}
		}
	})
	favoriteBtn.style.display = "block";
}

function favoriteClick(classId, id){
	if(id == null){
		location.href.substring(hostIndex, location.href.indexOf('/', hostIndex + 1)/login);
	}
	$.ajax({
		type: 'POST',
		url: 'favorite-regist',
		data: { classId: classId },
		success: function(result) {
			console.log(result);
			list(c_val, r_val, type);
		}
	})
}

function pageClick(i){
	currentPage = i;
	list(c_val, r_val, type);
}

	

	list(c_val, r_val, type);
	
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
				list(c_val, r_val, type);
			}

			art.onclick = function() {
				category.value = art.value;
				c_val = 'ART';
				r_val = sessionStorage.getItem('rSession');
				sessionStorage.setItem('cSession', c_val);
				categoryDrop.style.display = "none";
				list(c_val, r_val, type);
			}

			cooking.onclick = function() {
				category.value = cooking.value;
				c_val = 'COOKING';
				r_val = sessionStorage.getItem('rSession');
				sessionStorage.setItem('cSession', c_val);
				categoryDrop.style.display = "none";
				list(c_val, r_val, type);
			}

			language.onclick = function() {
				category.value = language.value;
				c_val = 'LANGUAGE';
				r_val = sessionStorage.getItem('rSession');
				sessionStorage.setItem('cSession', c_val);
				categoryDrop.style.display = "none";
				list(c_val, r_val, type);
			}

			programming.onclick = function() {
				category.value = programming.value;
				c_val = 'PROGRAMMING';
				r_val = sessionStorage.getItem('rSession');
				sessionStorage.setItem('cSession', c_val);
				categoryDrop.style.display = "none";
				list(c_val, r_val, type);
			}

			sport.onclick = function() {
				category.value = sport.value;
				c_val = 'SPORT';
				r_val = sessionStorage.getItem('rSession');
				sessionStorage.setItem('cSession', c_val);
				categoryDrop.style.display = "none";
				list(c_val, r_val, type);
			}
		}
	}

	range.onclick = function() {
		if (rangeDrop.style.display == "block") {
			rangeDrop.style.display = "none";
		} else {
			rangeDrop.style.display = "block";

			favorite.onclick = function() {
				range.value = favorite.value;
				c_val = sessionStorage.getItem('cSession');
				r_val = 'FAVORITE';
				sessionStorage.setItem('rSession', r_val);
				rangeDrop.style.display = "none";
				list(c_val, r_val, type);
			}

			newest.onclick = function() {
				range.value = newest.value;
				c_val = sessionStorage.getItem('cSession');
				r_val = 'OPENDATE';
				sessionStorage.setItem('rSession', r_val);
				rangeDrop.style.display = "none";
				list(c_val, r_val, type);
			}
		}
	}

