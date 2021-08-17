function test(userType) {
	const myInfoNav = document.getElementById("myInfoNav");
	const myFavoriteNav = document.getElementById("myFavoriteNav");
	const myAssignmentNav = document.getElementById("myAssignmentNav");
	const myReviewNav = document.getElementById("myReviewNav");
	const myQnANav = document.getElementById("myQnANav");
	const myClassNav = document.getElementById("myClassNav");
	const myClassQnANav = document.getElementById("myClassQnANav");
	const myClassRegNav = document.getElementById("myClassRegNav");

	const Container = document.getElementById("Container");
	const myPageFormTitle = document.getElementById("myPageFormTitle");
	const myPageContent = document.getElementById("myPageContent");
	const myPageBtns = document.getElementById("myPageBtns");

	var userType = userType;

	console.log(userType);

	if (userType == 'A') {
		console.log("Aamin");
		allQNA();

	} else {
		if (userType == 'C') {
			myClassNav.onclick = function() { myClass(); }
			myClassQnANav.onclick = function() { myClassQna(); }
			myClassRegNav.onclick = function() {
				location.href = "";//${pageContext.request.contextPath }/class-reg
			}
		}
		myInfoNav.onclick = function() { myInfo(); }
		myFavoriteNav.onclick = function() { myFavorite(); }
		myAssignmentNav.onclick = function() { myAssignment(); }
		myQnANav.onclick = function() { myQnA(); }
		myReviewNav.onclick = function() { myReview(); }
	}
}
var qnaTitleArr = [];
var userIdArr = [];

function allQNA() {
	
	try {
		$.ajax({
			type: 'POST',
			url: 'admin-qna',
			dataType: 'json',
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			data: {},
			success: function(result) {
				
				$("#myPageContent").empty();
				if (result.length != 0) {
					$("#myPageContent").append("<table border='1' id='allQnaTable'>"
						+ "<thead>"
						+ "<tr> <th>제목</th> <th>날짜</th> <th>ID</th> </tr> </thead>"
						+ "<tbody class='qnaBody'></tbody>"
						+ "</table>");
					console.log(result + " 총 " + result.length + "개 데이터");
					$.each(result, function(inx, obj) {

						$(".qnaBody").append("<tr class='qna_qbox'>"
							+ "<td><span>" + obj.qnaTitle + "</span></td>"
							+ "<td><span>" + obj.qnaDate + "</span></td>"
							+ "<td><span>" + obj.userId + "</span></td>"
							+ "</tr>");
						$(".qnaBody").append("<tr id='qna_abox' class='qna_abox'>"
							+ "<td colspan='3'>"
							+ "<div><span id='question'>" + obj.question + "</span></div>"
							+ "<textarea id='answerarea' class='answerarea' row='5' col='5' id='qnaAarea'>" + obj.answer + "</textarea><br>"
							+ "<div class='qnaAboxBtns'>"
							+ "<input type='button' onclick='saveQnaAnswerBtn(" + inx + ")' id='Save' class='qnabtns' value='SAVE'>"
							+ "</div>"
							+ "</td>"
							+ "</tr>"
						);
						qnaTitleArr.push($(this).attr("qnaTitle"));
						userIdArr.push($(this).attr("userId"));

					});
					

				}

			}, error: function(XMLHttpRequest, textStatus, errorThrown) {
				alert('There is an error : method(group)에 에러가 있습니다.');
			}
		})
	} catch (e) {

	}
}
function saveQnaAnswerBtn(i) {//
	var params = {
		qnaTitle: qnaTitleArr[i]
		, userId: userIdArr[i]
		, answer: $("#answerarea").val()
	}
//	console.log(params);
		$.ajax({
			type: 'POST',
			url: 'qna-update',
			dataType: 'json',
			contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
			data: {
				params
			},
			success: function(res) {
				alert(res.code);
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) {
				alert("클래스 생성 실패");
			}
		})

	}

function myInfo() {
	try {
		$.ajax({
			type: 'POST',
			url: 'myinfoedit',
			dataType: 'json',
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			data: {},
			success: function(result) {
				$("#myPageFormTitle").empty();
				$("#myPageContent").empty();
				$("#myPageBtns").empty();
				var usertype = result.userType;
				usertype = (usertype=='C') ? 'Creater' : 'User';
				
				$("#myPageFormTitle").append("내 정보 조회 / 수정");
				if (result.length != 0) {
					$("#myPageContent").append("<div class='userinfo-box'></div>");
					$(".userinfo-box").append("<img id='userThumnail' alt='유저아이콘' src='" + result.thumnail + "'><br>"
									+ "<label>ID <input type='text' value='" + result.id + "' id='userId' name='userId' disabled></label><br>"
									+ "<label>Type <input type='text' value='" + usertype + "' id='userType' name='userType' disabled></label><br>"
									+ "<label>이름 <input type='text' value='" + result.name + "' id='userName' name='userName' required></label><br>"
									+ "<label>번호 <input type='text' value='" + result.phone + "' id='phone' name='phone' required></label><br>"
									+ "<label>자기소개 <input type='text' value='" + result.introduce + "' id='introduce' name='introduce' required></label><br>"
					);
					$("#myPageBtns").append("<button type='button' onclick='myInfoUpdateBtn() id='myInfoUpdateBtn' value='정보 수정하기'></button><br>"
					+ "<button type='button' onclick='myInfoDeleteBtn() 'value id='myInfoDeleteBtn'value='회원탈퇴' ></button>");
				}
			}, error: function(XMLHttpRequest, textStatus, errorThrown) {
				alert('There is an error : method(group)에 에러가 있습니다.');
			}
		})
	} catch (e) {

	}

}
function myFavorite() {
	try {
		$.ajax({
			type: 'POST',
			url: 'myfavorite',
			dataType: 'json',
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			data: {},
			success: function(result) {
				$("#myPageFormTitle").empty();
				$("#myPageContent").empty();
				$("#myPageBtns").empty();
				
				$("#myPageFormTitle").append("내 응원 내역");
				if (result.length != 0) {
					$("#myPageContent").append("<div class='class-box'></div>");
					$.each(result, function(inx, obj) {

						$(".class-box").append("<div class='className'> " + obj.className + "</div> "
						+ "	<div class='creater'> " + obj.creater + "</div>"
						+ "	<div class='favorite'> " + obj.favorite + "</div>"
						+ "	<div class='openDate'> " + obj.openDate + "</div>"
						+ "	<button type='button' onclick='deleteFavoriteBtn(" + obj.classId + ")' id='deleteFavoriteBtn' value='그만 응원 할래요'></button> "
						);

					});
				} else {
					$("#myPageContent").append("<img alt='응원한 내역이 없습니다.' src=''><br>");
				}
				$("#myPageBtns").append("<button type='button' onclick='showPreClassBtn()' id='showPreClassBtn' value='오픈 예정 클래스 둘러보기'></button>");
			}, error: function(XMLHttpRequest, textStatus, errorThrown) {
				alert('There is an error : method(group)에 에러가 있습니다.');
			}
		})
	} catch (e) {

	}

}
function myAssignment() {
	try {
		$.ajax({
			type: 'POST',
			url: 'myassignment',
			dataType: 'json',
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			data: {},
			success: function(result) {
				$("#myPageFormTitle").empty();
				$("#myPageContent").empty();
				$("#myPageBtns").empty();
				
				$("#myPageFormTitle").append("결제 내역");
				if (result.length != 0) {
					$("#myPageContent").append("<div class='class-box'></div>");
					$.each(result, function(inx, obj) {

						$(".class-box").append("<div class='className'> " + obj.className + "</div> "
							+ "	<div class='creater'> " + obj.creater + "</div>"
							+ "	<div class='classType'> " + obj.classType + "</div>"
							+ "	<div class='recommend'> " + obj.recommend + "</div>"
							+ "	<div class='category'> " + obj.category + "</div>"
						);

					});
				} else {
					$("#myPageContent").append("<img alt='결제한 내역이 없습니다.' src=''><br>");
				}
				$("#myPageBtns").append("<button type='button' onclick='showClassBtn()' id='showClassBtn' value='클래스 둘러보기'></button>");
			}, error: function(XMLHttpRequest, textStatus, errorThrown) {
				alert('There is an error : method(group)에 에러가 있습니다.');
			}
		})
	} catch (e) {

	}
}
function myReview() {
	try {
		$.ajax({
			type: 'POST',
			url: 'myreview',
			dataType: 'json',
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			data: {},
			success: function(result) {
				$("#myPageFormTitle").empty();
				$("#myPageContent").empty();
				$("#myPageBtns").empty();
				
				$("#myPageFormTitle").append("내 리뷰");
				if (result.length != 0) {
					$("#myPageContent").append("<div class='review-box'></div>");
					$.each(result, function(inx, obj) {

						$(".review-box").append("<div class='className'> " + obj.className + "</div> "
							+ " <div class='reviewDate'> " + obj.reviewDate + "</div>"
							+ " <div class='recommend'> " + obj.recommend + "</div>"
							+ " <div class='category'> " + obj.category + "</div>"
							+ " <button type='button' onclick='deleteReviewBtn(" + obj.classId + ")' id='deleteReviewBtn' value='삭제'></button> "
						);

					});
				} else {
					$("#myPageContent").append("<img alt='작성한 리뷰가 없습니다.' src=''><br>");
				}
				$("#myPageBtns").append("<button type='button' onclick='reviewBtn()' id='reviewBtn' value='리뷰 작성하기'></button>");
			}, error: function(XMLHttpRequest, textStatus, errorThrown) {
				alert('There is an error : method(group)에 에러가 있습니다.');
			}
		})
	} catch (e) {

	}
}
function myQnA() {
	try {
		$.ajax({
			type: 'POST',
			url: 'myqna',
			dataType: 'json',
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			data: {},
			success: function(result) {
				$("#myPageFormTitle").empty();
				$("#myPageContent").empty();
				$("#myPageBtns").empty();
				
				$("#myPageFormTitle").append("내 질문");
				$("#myPageBtns").append("<button type='button' onclick='qnaBtn()' id='qnaBtn' value='문의하기'></button>");
				if (result.length != 0) {
					$("#myPageContent").append("<h4>질문 내역</h4>");
					$("#myPageContent").append("<table>"
					+ "<thead> <tr> <th>제목</th> <th>날짜</th> </tr> </thead>"
					+ "<tbody class='qna_list'></tbody></table>");
					$.each(result, function(inx, obj) {
						$(".qna_list").append("<tr>"
							+ "	<td class='qnaTitle'> " + obj.qnaTitle + "</td>"
							+ "	<td class='qnaDate'> " + obj.qnaDate + "</td>"
							+ "</tr>"
						);

					});
				} else {
					$("#myPageContent").append("<img alt='일반 문의 내역이 없습니다.' src=''><br>");
				}
			}, error: function(XMLHttpRequest, textStatus, errorThrown) {
				alert('There is an error : method(group)에 에러가 있습니다.');
			}
		})
	} catch (e) {

	}
	try {
		$.ajax({
			type: 'POST',
			url: 'myclassqna',
			dataType: 'json',
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			data: {},
			success: function(result) {
				
				if (result.length != 0) {
					$("#myPageContent").append("<h4>클래스 질문 내역</h4>");
					$("#myPageContent").append("<table>"
					+ "<thead> <tr> <th>클래스 이름</th> <th>제목</th> <th>날짜</th> <th>ID</th> </tr> </thead>"
					+ "<tbody class='qna_list'></tbody></table>");
					$.each(result, function(inx, obj) {

						$(".qna_list").append("<tr>"
						+ "		<td class='className'> " + obj.className + "</td> "
						+ "		<td class='qnaTitle'> " + obj.qnaTitle + "</td>"
						+ "		<td class='qnaDate'> " + obj.qnaDate + "</td>"
						+ "</tr>"
						);

					});
				} else {
					$("#myPageContent").append("<img alt='클래스 문의 내역이 없습니다.' src=''><br>");
				}
					
			}, error: function(XMLHttpRequest, textStatus, errorThrown) {
				alert('There is an error : method(group)에 에러가 있습니다.');
			}
		})
	} catch (e) {

	}
}
function myClass() {
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
						+ "		<a class='class-box-a'> "
						+ "			<div class='className'> " + json[i].className + "</div> "
						+ "			<div class='category'> " + json[i].category + "</div>"
						+ "			<div class='openDate'> " + json[i].openDate + "</div>"
						+ "			<div class='favorite'> " + json[i].favorite + "</div>"
						+ "		</a>"
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
						+ "		<a class='class-box-a'> "
						+ "			<div class='className'> " + json[i].className + "</div> "
						+ "			<div class='category'> " + json[i].category + "</div>"
						+ "			<div class='classType'> " + json[i].classType + "</div>"
						+ "			<div class='recommend'> " + json[i].recommend + "</div>"
						+ "		</a>"
						+ "</div>";
				}
			} else {
				html += "<img alt='오픈된 클래스가 없습니다.' src=''><br>";
			}
		}

		myPageContent.innerHTML = html;
		myPageBtns.innerHTML = "<button type='button' onclick='createClassBtn()' id='createClassBtn' value='강의 등록하기'></button>";
	}
}
function myClassQna() {
	try {
		$.ajax({
			type: 'POST',
			url: 'myclassqna',
			dataType: 'json',
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			data: {},
			success: function(result) {
				$("#myPageFormTitle").empty();
				$("#myPageContent").empty();
				$("#myPageBtns").empty();
				
				$("#myPageFormTitle").append("받은 질문 내역");
				if (result.length != 0) {
					$("#myPageContent").append("<table>"
					+ " <thead> <tr> <th>클래스 이름</th> <th>제목</th> <th>날짜</th> <th>ID</th> </tr> </thead>"
					+ "<tbody class='qna-qbox'></tbody></table>");
					$.each(result, function(inx, obj) {

						$(".qna-qbox").append("<tr onclick='qnaBox(" + i + ")' class='qna-qbox'>"
							+ "	<td class='className'> " + obj.className + "</td> "
							+ "	<td class='qnaTitle'> " + obj.qnaTitle + "</td>"
							+ "	<td class='qnaDate'> " + obj.qnaDate + "</td>"
							+ "	<td class='userId'> " + obj.userId + "</td>"
							+ "</tr>");
						$(".qna-qbox").append("<tr id='qna_abox' class='qna_abox'>"
							+ "<td colspan='3'>"
							+ "<div><span id='question'>" + obj.question + "</span></div>"
							+ "<textarea id='answerarea' class='answerarea' row='5' col='5' id='qnaAarea'>" + obj.answer + "</textarea><br>"
							+ "<div class='qnaAboxBtns'>"
							+ "<input type='button' onclick='saveQnaAnswerBtn(" + inx + ")' id='Save' class='qnabtns' value='SAVE'>"
							+ "</div>"
							+ "</td>"
							+ "</tr>"
						);
						qnaTitleArr.push($(this).attr("qnaTitle"));
						userIdArr.push($(this).attr("userId"));
					});
				} else {
					$("#myPageBtns").append("<img alt='받은 질문이 없습니다.' src=''><br>");
				}
			}, error: function(XMLHttpRequest, textStatus, errorThrown) {
				alert('There is an error : method(group)에 에러가 있습니다.');
			}
		})
	} catch (e) {

	}
}

console.log("내부 로딩 완료");