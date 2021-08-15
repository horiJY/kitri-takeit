const myInfoNav = document.getElementById("myInfoNav");
const myFavoriteNav = document.getElementById("myFavoriteNav");
const myAssignmentNav = document.getElementById("myAssignmentNav");
const myReviewNav = document.getElementById("myReviewNav");
const myQnANav = document.getElementById("myQnANav");
const myClassNav = document.getElementById("myClassNav");
const myClassQnANav = document.getElementById("myClassQnANav");

const Container = document.getElementById("Container");
const myPageFormTitle = document.getElementById("myPageFormTitle");
const myPageContent = document.getElementById("myPageContent");
const myPageBtns = document.getElementById("myPageBtns");

const logoutBtn = document.getElementById("logoutBtn");
const myInfoSaveBtn = document.getElementById("myInfoUpdateBtn");
const userDeleteBtn = document.getElementById("userDeleteBtn");
const showpreClassBtn = document.getElementById("showpreClassBtn");
const showClassBtn = document.getElementById("showClassBtn");
const qnaBtn = document.getElementById("qnaBtn");


myInfoNav.onclick = function() {
	myPageFormTitle.innerHTML = "내 정보 조회 / 수정";
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "myinfoedit", true);
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.send();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200) {
			var jsonStr = xhr.responseText;//json
			var json = JSON.parse(jsonStr);
			console.log(json.value);
			var html = "";
			var usertype = json.userType;
			usertype = (usertype="C") ? 'Creater' : 'User' ;
			
			if (json.length != 0) {
				html += "<div class='userinfo-box'>"
					+ "<img id='userThumnail' alt='유저아이콘' src='" + json.thumnail + "'><br>"
					+ "<label>ID <input type='text' value='" + json.id + "' id='userId' name='userId' disabled></label><br>"
					+ "<label>Type <input type='text' value='" + usertype + "' id='userType' name='userType' disabled></label><br>"
					+ "<label>이름 <input type='text' value='" + json.name + "' id='userName' name='userName' required></label><br>"
					+ "<label>번호 <input type='text' value='" + json.phone + "' id='phone' name='phone' required></label><br>"
					+ "</div>";

				myPageContent.innerHTML = html;
				myPageBtns.innerHTML = "<button type='button' value='정보 수정하기' id='myInfoUpdateBtn' ></button><br>"
										+"<input type='button' value='회원탈퇴' id='userDeleteBtn'>";
			}
		}
	}

}
myFavoriteNav.onclick = function() {
	myPageFormTitle.innerHTML = "내 응원 내역";
	myPageContent.innerHTML = "응원 목록";
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "myfavorite", true);

	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.send();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200) {
			var jsonStr = xhr.responseText;//json
			var json = JSON.parse(jsonStr);
			var html = "";
			if (json.length != 0) {
				for (var i = 0; i < json.length; i++) {
					html += "<div class='class-box'>"
						+ "		<div class='className'> " + json[i].className + "</div> "
						+ "		<div class='creater'> " + json[i].creater + "</div>"
						+ "		<div class='favorite'> " + json[i].favorite + "</div>"
						+ "		<div class='openDate'> " + json[i].openDate + "</div>"
						+ "		<button onclick='deleteFBtn(" + json[i].classId +")' id='deleteFBtn' value='그만 응원 할래요'> "
						+ "</div>";
				}
			} else {
				html += "<img alt='응원한 내역이 없습니다.' src=''><br>";
			}
			myPageContent.innerHTML = html;
			myPageBtns.innerHTML = "<input type='button' value='오픈 예정 클래스 둘러보기' id='showpreClassBtn'>";
		}
	}

}
myAssignmentNav.onclick = function() {
	myPageFormTitle.innerHTML = "결제 내역"
	myPageContent.innerHTML = "결제 목록";
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "myassignment", true);

	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.send();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200) {
			var jsonStr = xhr.responseText;//json
			var json = JSON.parse(jsonStr);
			var html = "";
			if (json.length != 0) {
				for (var i = 0; i < json.length; i++) {
					html += "<div class='class-box'>"
						+ "		<div class='className'> " + json[i].className + "</div> "
						+ "		<div class='creater'> " + json[i].creater + "</div>"
						+ "		<div class='classType'> " + json[i].classType + "</div>"
						+ "		<div class='recommend'> " + json[i].recommend + "</div>"
						+ "		<div class='category'> " + json[i].category + "</div>"
						+ "</div>";

				}
			} else {
				html += "<img alt='결제한 내역이 없습니다.' src=''><br>";
			}

			myPageContent.innerHTML = html;
			myPageBtns.innerHTML = "<input type='button' value='클래스 둘러보기' id='showClassBtn'>";
		
		}
	}
}
myReviewNav.onclick = function() {
	myPageFormTitle.innerHTML = "내 리뷰"
	myPageContent.innerHTML = "리뷰 목록";
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "myreview", true);

	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.send();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200) {
			var jsonStr = xhr.responseText;//json
			var json = JSON.parse(jsonStr);
			var html = "";
			if (json.length != 0) {
				for (var i = 0; i < json.length; i++) {
					html += "<div class='review-box'>"
						+ "		<div class='className'> " + json[i].className + "</div> "
						+ "		<div class='reviewDate'> " + json[i].reviewDate + "</div>"
						+ "		<div class='recommend'> " + json[i].recommend + "</div>"
						+ "		<div class='category'> " + json[i].category + "</div>"
						+ "</div>";

				}
			} else {
				html += "<img alt='작성한 리뷰가 없습니다.' src=''><br>";
			}
			myPageContent.innerHTML = html;
			myPageBtns.innerHTML = "<input type='button' value='리뷰 작성하기' id='reviewBtn'>";;
			
		}
	}
}
myQnANav.onclick = function() {
	myPageFormTitle.innerHTML = "내 질문"
	myPageContent.innerHTML = "질문 목록";
	
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "myqna", true);
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.send();
	
	var html = "";
	
	xhr.onreadystatechange = function() {
		if (xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200) {
			var jsonStr = xhr.responseText;//json
			var json = JSON.parse(jsonStr);
			
			if (json.length != 0) {
				html += "<h4>질문 내역</h4>";
				for (var i = 0; i < json.length; i++) {
					html += "<div class='qna-box'>"
						+ "		<div class='className'> " + json[i].className + "</div>"
						+ "		<div class='category'> " + json[i].category + "</div>"
						+ "		<div class='openDate'> " + json[i].openDate + "</div>"
						+ "		<div class='favorite'> " + json[i].favorite + "</div>"
						+ "</div>";
				}
			} else {
				
				html += "<img alt='일반 문의 내역이 없습니다.' src=''><br>";
			}
		}
		
	}
	
	var xhrclass = new XMLHttpRequest();
	xhrclass.open("POST", "myclassqna", true);
	xhrclass.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhrclass.send();
	
	xhrclass.onreadystatechange = function() {
		if (xhrclass.readyState == XMLHttpRequest.DONE && xhrclass.status == 200) {
			var jsonStr = xhrclass.responseText;//json
			var json = JSON.parse(jsonStr);
			
			if (json.length != 0) {
				html += "<h4>클래스 질문 내역</h4>";
				for (var i = 0; i < json.length; i++) {
					html += "<div>"
						+ "		<div class='className'> " + json[i].className + "</div> "
						+ "		<div class='category'> " + json[i].category + "</div>"
						+ "		<div class='classType'> " + json[i].classType + "</div>"
						+ "		<div class='recommend'> " + json[i].recommend + "</div>"
						+ "</div>";
				}
			} else {
				html += "<img alt='클래스 문의 내역이 없습니다.' src=''><br>";
			}
			if(html==""){
				html="<img alt='문의 내역이 없습니다.' src=''><br>"
			}
			myPageContent.innerHTML = html;
			myPageBtns.innerHTML = "<input type='button' value='문의하기' id='qnaBtn'>";		
		}
	}
}
myClassNav.onclick = function() {
	myPageFormTitle.innerHTML = "내 클래스"
	myPageContent.innerHTML = "클래스 목록";
	
	var xhrpre = new XMLHttpRequest();
	xhrpre.open("POST", "mypreclass", true);
	xhrpre.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhrpre.send();
	
	var xhropen = new XMLHttpRequest();
	xhropen.open("POST", "myopenclass", true);
	xhropen.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhropen.send();
	
	var html = "";
	
	xhrpre.onreadystatechange = function() {
		if (xhrpre.readyState == XMLHttpRequest.DONE && xhrpre.status == 200) {
			var jsonStr = xhrpre.responseText;//json
			var json = JSON.parse(jsonStr);
			html += "<h4>오픈 예정 클래스</h4>";
			if (json.length != 0) {
				for (var i = 0; i < json.length; i++) {
					html += "<div class='class-box'>"
						+ "		<div class='className'> " + json[i].className + "</div> "
						+ "		<div class='category'> " + json[i].category + "</div>"
						+ "		<div class='openDate'> " + json[i].openDate + "</div>"
						+ "		<div class='favorite'> " + json[i].favorite + "</div>"
						+ "</div>";
				}
			} else {
				
				html += "<img alt='오픈 예정 클래스가 없습니다.' src=''><br>";
			}
		}
		
	}
	xhropen.onreadystatechange = function() {
		if (xhropen.readyState == XMLHttpRequest.DONE && xhropen.status == 200) {
			var jsonStr = xhropen.responseText;//json
			var json = JSON.parse(jsonStr);
			html += "<h4>오픈된 클래스</h4>";
			if (json.length != 0) {
				for (var i = 0; i < json.length; i++) {
					html += "<div class='class-box'>"
						+ "		<div class='className'> " + json[i].className + "</div> "
						+ "		<div class='category'> " + json[i].category + "</div>"
						+ "		<div class='classType'> " + json[i].classType + "</div>"
						+ "		<div class='recommend'> " + json[i].recommend + "</div>"
						+ "</div>";
				}
			} else {
				html += "<img alt='오픈된 클래스가 없습니다.' src=''><br>";
			}
		}
		
		myPageContent.innerHTML = html;
		myPageBtns.innerHTML = "<input type='button' value='강의 등록하기' id='createClassBtn'>";		
	}
}
myClassQnANav.onclick = function() {
	myPageFormTitle.innerHTML = "받은 질문 내역";
	myPageContent.innerHTML = "클래스 질문 목록";
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "myclassqna", true);

	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.send();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200) {
			var jsonStr = xhr.responseText;//json
			var json = JSON.parse(jsonStr);

			var html = "";
			if (json.length != 0) {
				for (var i = 0; i < json.length; i++) {
					html += "<div>"
						+ "		<div class='className'> " + json[i].className + "</div> "
						+ "		<div class='category'> " + json[i].category + "</div>"
						+ "		<div class='qnaTitle'> " + json[i].qnaTitle + "</div>"
						+ "		<div class='qnaDate'> " + json[i].qnaDate + "</div>"
						+ "		<div class='userId'> " + json[i].userId + "</div>"
						+ "</div>";

				}
			} else {
				html += "<img alt='받은 질문이 없습니다.' src=''><br>";
			}
			
			myPageContent.innerHTML = html;
			myPageBtns.innerHTML = "";		
		}
	}
}
console.log("내부 로딩 완료");