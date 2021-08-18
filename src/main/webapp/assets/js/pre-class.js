

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

	var userId = sessionStorage.getItem('userId');
	var mypageBtn = document.getElementById("mypage");

	var loginBtn = document.getElementById('login');

	var logoutBtn = document.getElementById('logout');

	var dataPerPage = 8; //한 페이지에 나타낼 글 수
	var pageCount = 5; //페이징에 나타낼 페이지 수
	var currentPage = 1; //현재 페이지
	var totalData;
	var type = 'P';
	
	var favorite;
	
	if (userId == null) {
		loginBtn.onclick = function() {
			location.href = location.href.substring(hostIndex, location.href.ind('/', hostIndex + 1)) + '/login';
		}
	} else {
		mypageBtn.onclick = function() {
			location.href = location.href.substring(hostIndex, location.href.ind('/', hostIndex + 1)) + '/mypage';
		}
		logoutBtn.onclick = function() {
			sessionStorage.setItem('userId', null);
			location.href = location.href.substring(hostIndex, location.href.ind('/', hostIndex + 1)) + '/main';
		}
	}

	//할 일 : mypage에 userId, logout, 페이징 
	

	function list(c_val, r_val, type) {
		$.ajax({
			type: 'POST',
			url: 'range',
			async: false,
			data: { category: c_val, range: r_val, type: type },
			success: function(result) {
				for(var i = 0; i < result.length; i++){
					favorite = result[i].favorite;
					update(favorite);
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
						'<li><label onclick="classDetail(' + result[i].classId + ')" >'
						+ '<div><img></div>'
						+ '<div><div>' + result[i].creater + '</div>'
						+ '<div>' + result[i].className + '</div>'
						+ '<div>' + result[i].favorite + '</div></div>'
						+ '<div><div>' + result[i].price + '</div>'
						+ '<div>' + result[i].sale + '</div></div>'
						+ '<div><div>' + result[i].classType + '</div></div>'
						+ '<div><div><span>응원 마감까지 ' + result[i].countdown + '일</span></div></div>'
						+ '</label></li>'
					);
				}
			}
		})	
	}

	function update(favorite){
		$.ajax({
			type: 'POST',
			url: 'pre-class-update',
			async: false,
			data: {favorite: favorite },
			success: function(result) {
				
			}
		})
	}
	
//	paging(c_val, r_val, totalData, dataPerPage, pageCount, currentPage);

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
				range.value = recommend.value;
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

var swiper = document.getElementById("swiper");

function classDetail(i) {
	swiper.style.display = 'block';
}

function pageClick(i){
	currentPage = i;
	list(c_val, r_val, type);
}

//오늘 날짜 비교해서 오늘 날짜와 opendate가 같고, favorite이 10개가 넘는 class들은 type "P"로 변경, 그렇지 않을 시 type null로 변경 - ClassDAO에 classUpdate 추가 완료
// favorite 값 받아서 classUpdate 실행하고 그 후에 list 호출!
function init() {
  setInterval(list, 1000*60*60*24); 		//하루에 한 번씩 리스트 불러오기 (자정에 실행되도록 변경해야댐!)
}

init();