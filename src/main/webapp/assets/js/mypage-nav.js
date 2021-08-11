
const myInfoNav = document.getElementById("myInfoNav");
const myFavoriteNav = document.getElementById("myFavoriteNav");
const myAssignmentNav = document.getElementById("myAssignmentNav");
const myReviewNav = document.getElementById("myReviewNav");
const myQnANav = document.getElementById("myQnANav");
const myClassNav = document.getElementById("myClassNav");

const Container = document.getElementById("Container");
const myPageFormTitle = document.getElementById("myPageFormTitle");
const myPageContent = document.getElementById("myPageContent");

const logoutBtn = document.getElementById("logoutBtn");
const myInfoSaveBtn = document.getElementById("myInfoUpdateBtn");
const userDeleteBtn = document.getElementById("userDeleteBtn");
const showpreClassBtn = document.getElementById("showpreClassBtn");
const showClassBtn = document.getElementById("showClassBtn");
const qnaBtn = document.getElementById("qnaBtn");

myInfoSaveBtn.setAttribute("type", "hidden");
userDeleteBtn.setAttribute("type", "hidden");
showpreClassBtn.setAttribute("type", "hidden");
showClassBtn.setAttribute("type", "hidden");
qnaBtn.setAttribute("type", "hidden");

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

			var html = "";
			if (json != null) {
				html += "<img id='userThumnail' alt='유저아이콘' src='" + json.thumnail + "'><br>"
					+ "<label>ID<input type='text' value='" + json.id + "' id='userId' name='userId' readonly></label><br>"
					+ "<label>이름<input type='text' value='" + json.name + "' id='userName' name='userName' required></label><br>"
					+ "<label>번호<input type='text' value='" + json.phone + "' id='phone' name='phone' required></label><br>"
					+ "";

				myPageContent.innerHTML = html;
			}
		}
	}


	myInfoSaveBtn.setAttribute("type", "button");
	userDeleteBtn.setAttribute("type", "button");
	showpreClassBtn.setAttribute("type", "hidden");
	showClassBtn.setAttribute("type", "hidden");
	qnaBtn.setAttribute("type", "hidden");

}
myFavoriteNav.onclick = function() {
	myPageFormTitle.innerHTML = "내 응원 내역";
	//		myPageContent.innerHTML = "응원 목록";
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "myfavorite", true);

	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.send();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200) {
			var jsonStr = xhr.responseText;//json
			var json = JSON.parse(jsonStr);

			var html = "";
			if (json != "") {
				console.log("json="+json.value);
				for (var i = 0; i < json.length; i++) {
					html += "<div>"
						+ "	<div> " + json[i].className + "</div> "
						+ "	<div> " + json[i].creater + "</div>"
						+ "	<div> " + json[i].favorite + "</div>"
						+ "	<div> " + json[i].openDate + "</div>"
						+ "</div>";

				}
			} else {
				html += "<img alt='응원한 내역이 없습니다.' src=''><br>";
			}

			myPageContent.innerHTML = html;

		}
	}
	myInfoSaveBtn.setAttribute("type", "hidden");
	userDeleteBtn.setAttribute("type", "hidden");
	showpreClassBtn.setAttribute("type", "button");
	showClassBtn.setAttribute("type", "hidden");
	qnaBtn.setAttribute("type", "hidden");

}
myAssignmentNav.onclick = function() {
	myPageFormTitle.innerHTML = "결제 내역"
	//		myPageContent.innerHTML = "주문 목록";
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "myassignment", true);

	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.send();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200) {
			var jsonStr = xhr.responseText;//json
			var json = JSON.parse(jsonStr);

			var html = "";
			if (json != "") {

				for (var i = 0; i < json.length; i++) {
					html += "<div>"
						+ "	<div> " + json[i].className + "</div> "
						+ "	<div> " + json[i].creater + "</div>"
						+ "	<div> " + json[i].classType + "</div>"
						+ "	<div> " + json[i].recommend + "</div>"
						+ "	<div> " + json[i].category + "</div>"
						+ "</div>";

				}
			} else {
				html += "<img alt='결제한 내역이 없습니다.' src=''><br>";
			}

			myPageContent.innerHTML = html;
			
			myInfoSaveBtn.setAttribute("type", "hidden");
			userDeleteBtn.setAttribute("type", "hidden");
			showpreClassBtn.setAttribute("type", "hidden");
			showClassBtn.setAttribute("type", "button");
			qnaBtn.setAttribute("type", "hidden");

		}
	}
	myInfoSaveBtn.setAttribute("type", "hidden");
	userDeleteBtn.setAttribute("type", "hidden");
	showpreClassBtn.setAttribute("type", "button");
	showClassBtn.setAttribute("type", "button");
	qnaBtn.setAttribute("type", "hidden");
}
myReviewNav.onclick = function() {
	myPageFormTitle.innerHTML = "내 리뷰"
	myPageContent.innerHTML = "리뷰 목록";
}
myQnANav.onclick = function() {
	myPageFormTitle.innerHTML = "내 질문"
	myPageContent.innerHTML = "질문 목록";
}

myClassNav.onclick = function() {
	myPageFormTitle.innerHTML = "내 클래스"
	myPageContent.innerHTML = "클래스 목록";
}
console.log("내부 로딩 완료");
