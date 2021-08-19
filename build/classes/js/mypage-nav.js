function test(userType) {
//	네비
	const myInfoNav = document.getElementById("myInfoNav");
	const myFavoriteNav = document.getElementById("myFavoriteNav");
	const myAssignmentNav = document.getElementById("myAssignmentNav");
	const myReviewNav = document.getElementById("myReviewNav");
	const myQnANav = document.getElementById("myQnANav");
	const myClassNav = document.getElementById("myClassNav");
	const myClassQnANav = document.getElementById("myClassQnANav");
	const myClassRegNav = document.getElementById("myClassRegNav");

//	폼
	const Container = document.getElementById("Container");
	const myPageFormTitle = document.getElementById("myPageFormTitle");
	const myPageContent1 = document.getElementById("myPageContent1");
	const myPageContent2 = document.getElementById("myPageContent2");
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
var answerArr = [];
var emptyImgs =['assets/img/empty-favorite.jpg'
				,'assets/img/empty-assignment.jpg'
				,'assets/img/empty-review.jpg'
				,'assets/img/empty-qna.jpg'
				,'assets/img/empty-myclass-qna.jpg'
				,'assets/img/empty-preclass.jpg'
				,'assets/img/empty-openclass.jpg'
				,'assets/img/empty-myclass-qna.jpg'
				];

function allQNA() {
	
	try {
		$.ajax({
			type: 'POST',
			url: 'admin-qna',
			dataType: 'json',
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			data: {},
			success: function(result) {
				
				$("#myPageContent1").empty();
				$("#myPageContent2").empty();
				if (result.length != 0) {
					$("#myPageContent1").append("<table border='1' id='allQnaTable'>"
						+ "<thead>"
						+ "<tr> <th>제목</th> <th>날짜</th> <th>ID</th> </tr> </thead>"
						+ "<tbody class='qnaBody'></tbody>"
						+ "</table>");
//					console.log(result + " 총 " + result.length + "개 데이터");
					$.each(result, function(inx, obj) {

						$(".qnaBody").append("<tr class='qna_qbox'>"
							+ "<td><span>" + obj.qnaTitle + "</span></td>"
							+ "<td><span>" + obj.qnaDate + "</span></td>"
							+ "<td><span>" + obj.userId + "</span></td>"
							+ "</tr>");
						$(".qnaBody").append("<tr id='qna_abox' class='qna_abox'>"
							+ "<td colspan='3'>"
							+ "<div><span id='question'>" + obj.question + "</span></div>"
							+ "<textarea id='answer" +inx + "' class='answer' row='5' col='5'>" + obj.answer + "</textarea><br>"
							+ "<div class='qnaAboxBtns'>"
							+ "<input type='button' onclick='saveQnaAnswerBtn(" + inx + ")' id='Save' class='qnabtns' value='SAVE'>"
							+ "<input type='button' onclick='deleteQnaBtn()' id='Delete' class='qnabtns' value='DELETE'>"
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
				$("#myPageContent1").empty();
				$("#myPageContent2").empty();
				$("#myPageBtns").empty();
				var usertype = result.userType;
				usertype = (usertype=='C') ? 'Creater' : 'User';
				
				$("#myPageFormTitle").append("내 정보 조회 / 수정");
				if (result.length != 0) {
					$("#myPageContent1").append("<div class='userinfo-box'></div>");
					$(".userinfo-box").append("<img id='userThumnail' alt='유저아이콘' src='" + result.thumnail + "'><br>"
									+ "<div class='myInfoLabel'><div>ID</div> <input type='text' value='" + result.id + "' id='userId' name='userId' disabled></div><br>"
									+ "<div class='myInfoLabel'><div>TYPE</div> <input type='text' value='" + usertype + "' id='userType' name='userType' disabled></div><br>"
									+ "<div class='myInfoLabel'><div>이름</div> <input type='text' value='" + result.name + "' id='userName' name='userName' required></div><br>"
									+ "<div class='myInfoLabel'><div>번호</div> <input type='text' value='" + result.phone + "' id='phone' name='phone' required></div><br>"
					);
					$("#myPageBtns").append("<button type='button' onclick='clickMIUB()' id='myInfoUpdateBtn'></button><br>"
										  + "<button type='button' onclick='clickMIDB()' id='myInfoDeleteBtn'></button>"
					);
					$("#myInfoUpdateBtn").html('정보 수정하기');
					$("#myInfoDeleteBtn").html('회원탈퇴');
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
				$("#myPageContent1").empty();
				$("#myPageContent2").empty();
				$("#myPageBtns").empty();
				
				$("#myPageFormTitle").append("내 응원 내역");
				$("#myPageBtns").append("<button type='button' onclick='showPreClassBtn()' id='showPreClassBtn' value='오픈 예정 클래스 둘러보기'></button>");
				$("#showPreClassBtn").html('오픈 예정 클래스 둘러보기');
				if (result.length != 0) {
					$("#myPageContent1").append("<div class='class-box'></div>");
					$.each(result, function(inx, obj) {

						$(".class-box").append("<div class='className'> " + obj.className + "</div> "
						+ "	<div class='creater'> " + obj.creater + "</div>"
						+ "	<div class='favorite'> " + obj.favorite + "</div>"
						+ "	<div class='openDate'> " + obj.openDate + "</div>"
						+ "	<button type='button' onclick='clickDFB(" + obj.classId + ")' id='deleteFavoriteBtn'></button> "
						);
						$("#deleteFavoriteBtn").html('그만 응원 할래요');

					});
				} else {
					$("#myPageContent1").append("<img class='emptylist' alt='응원한 내역이 없습니다.' src=" + emptyImgs[0] + "><br>");
				}
			}, error: function(XMLHttpRequest, textStatus, erorThrown) {
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
				$("#myPageContent1").empty();
				$("#myPageContent2").empty();
				$("#myPageBtns").empty();
				
				$("#myPageFormTitle").append("결제 내역");
				$("#myPageBtns").append("<button type='button' onclick='clickSCB()' id='showClassBtn'></button>");
				$("#showClassBtn").html('클래스 둘러보기');
				if (result.length != 0) {
					$("#myPageContent1").append("<div class='class-box'></div>");
					$.each(result, function(inx, obj) {

						$(".class-box").append("<div class='className'> " + obj.className + "</div> "
							+ "	<div class='creater'> " + obj.creater + "</div>"
							+ "	<div class='classType'> " + obj.classType + "</div>"
							+ "	<div class='recommend'> " + obj.recommend + "</div>"
							+ "	<div class='category'> " + obj.category + "</div>"
						);

					});
				} else {
					$("#myPageContent1").append("<img class='emptylist' alt='결제한 내역이 없습니다.' src=" + emptyImgs[1] + "><br>");
				}
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
				$("#myPageContent1").empty();
				$("#myPageContent2").empty();
				$("#myPageBtns").empty();
				
				$("#myPageFormTitle").append("내 리뷰");
				$("#myPageBtns").append("<button type='button' onclick='clickRB()' id='reviewBtn'></button>");
				$("#reviewBtn").html('리뷰 작성하기');
				if (result.length != 0) {
					$("#myPageContent1").append("<div class='review-box'></div>");
					$.each(result, function(inx, obj) {

						$(".review-box").append("<div class='className'> " + obj.className + "</div> "
							+ " <div class='reviewDate'> " + obj.reviewDate + "</div>"
							+ " <div class='recommend'> " + obj.recommend + "</div>"
							+ " <div class='category'> " + obj.category + "</div>"
							+ " <button type='button' onclick='deleteReviewBtn(" + obj.classId + ")' id='deleteReviewBtn'></button> "
						);
						$("#deleteReviewBtn").html('삭제');

					});
				} else {
					$("#myPageContent1").append("<img class='emptylist' alt='작성한 리뷰가 없습니다.' src=" + emptyImgs[2] + "><br>");
				}
				
			}, error: function(XMLHttpRequest, textStatus, errorThrown) {
				alert('There is an error : method(group)에 에러가 있습니다.');
			}
		})
	} catch (e) {

	}
}
function myQnA() {
	myqna();
	myclassqna();
	
}

function myqna(){
	try {
		$.ajax({
			type: 'POST',
			url: 'myqna',
			dataType: 'json',
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			data: {},
			success: function(result) {
				$("#myPageFormTitle").empty();
				$("#myPageContent1").empty();
				$("#myPageBtns").empty();
				
				$("#myPageFormTitle").append("내 질문");
				$("#myPageBtns").append("<button type='button' onclick='clickQB()' id='qnaBtn'></button>");
				$("#qnaBtn").html('문의하기');
				if (result.length != 0) {
					$("#myPageContent1").append("<h4>질문 내역</h4>");
					$("#myPageContent1").append("<table border='1' class='myQna'>"
					+ "<thead> <tr> <th>제목</th> <th>날짜</th> </tr> </thead>"
					+ "<tbody class='qna_list'></tbody></table>");
					$.each(result, function(inx, obj) {
						$(".qna_list").append("<tr>"
							+ "	<td class='qnaTitle'> " + obj.qnaTitle + "</td>"
							+ "	<td class='qnaDate'> " + obj.qnaDate + "</td>"
							+ "</tr>"
						);
						$(".qna_list").append("<tr id='qnaAbox' class='qnaAbox'>"
							+ "<td colspan='2'>"
							+ "<input type='hidden' id='qnaTitle" +inx + "' value="+ obj.qnaTitle +">"
							+ "<textarea id='question" +inx + "' class='question' row='5' col='5'>" + obj.question + "</textarea><br>"
							+ "<div><span id='answer'>" + obj.answer + "</span></div>"
							+ "<div class='qnaAboxBtns'>"
							+ "<input type='button' onclick='clickSQQB(" + inx + ")' id='Save' class='qnabtns' value='SAVE'>"
							+ "<input type='button' onclick='clickDQQB(" + inx + ")' id='Delete' class='qnabtns' value='DELETE'>"
							+ "</div>"
							+ "</td>"
							+ "</tr>"
						);
					});
				} else {
					$("#myPageContent1").append("<img class='emptylist' alt='일반 문의 내역이 없습니다.' src=" + emptyImgs[3] + "><br>");
				}
			}, error: function(XMLHttpRequest, textStatus, errorThrown) {
				alert('There is an error : method(group)에 에러가 있습니다.');
			}
		})
	} catch (e) {

	}
}
function myclassqna(){
	try {
		$.ajax({
			type: 'POST',
			url: 'myclassqna',
			dataType: 'json',
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			data: {},
			success: function(result) {
				$("#myPageContent2").empty();
				$("#myPageContent2").append("<h4>클래스 질문 내역</h4>");
				if (result.length != 0) {
					$("#myPageContent2").append("<table border='1' class='classQna'>"
						+ "<thead> <tr> <th>클래스 이름</th> <th>제목</th> <th>날짜</th> <th>ID</th> </tr> </thead>"
					+ "<tbody class='qna_list'></tbody></table>");
					$.each(result, function(inx, obj) {
						$(".qna_list").append("<tr>"
						+ "		<td class='className"+inx+"'> " + obj.className + "</td> "
						+ "		<td class='qnaTitle"+inx+"'> " + obj.qnaTitle + "</td>"
						+ "		<td class='qnaDate'> " + obj.qnaDate + "</td>"
						+ "</tr>"
						);
						$(".qna_list").append("<tr id='qnaAbox' class='qnaAbox'>"
							+ "<td colspan='3'>"
							+ "<input type='hidden' id='className" +inx + "' value="+ obj.className +">"
							+ "<input type='hidden' id='qnaTitle" +inx + "' value="+ obj.qnaTitle +">"
							+ "<textarea id='question" +inx + "' class='question' row='5' col='5'>" + obj.question + "</textarea><br>"
							+ "<div><span id='answer'>" + obj.answer + "</span></div>"
							+ "<div class='qnaAboxBtns'>"
							+ "<input type='button' onclick='clickSCQQB(" + inx + ")' id='Save' class='qnabtns' value='SAVE'>"
							+ "<input type='button' onclick='clickDCQQB(" + inx + ")' id='Delete' class='qnabtns' value='DELETE'>"
							+ "</div>"
							+ "</td>"
							+ "</tr>"
						);

					});
				} else {
					$("#myPageContent2").append("<img class='emptylist' alt='내 클래스의 문의 내역이 없습니다.' src=" + emptyImgs[4] + "><br>");
				}
					
			}, error: function(XMLHttpRequest, textStatus, errorThrown) {
				alert('There is an error : method(group)에 에러가 있습니다.');
			}
		})
	} catch (e) {

	}
}

function myClass() {
	mypreclass();
	myopenclass();
}

function mypreclass(){
	try {
		$.ajax({
			type: 'POST',
			url: 'mypreclass',
			dataType: 'json',
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			data: {},
			success: function(result) {
				$("#myPageFormTitle").empty();
				$("#myPageContent1").empty();
				$("#myPageBtns").empty();
				
				$("#myPageFormTitle").append("내 클래스");
				$("#myPageBtns").append("<button type='button' onclick='clickMPOC()' id='createClassBtn'></button>");
				$("#createClassBtn").html('강의 등록하기');
				$("#myPageContent1").append("<h4>오픈 예정 클래스</h4>");
				if (result.length != 0) {
				$("#myPageContent1").append("<div class='class-box'></div>");
					$.each(result, function(inx, obj) {
						$(".class-box").append("<a class='class-box-a'> "
						+ "	<div class='className'> " + obj.className + "</div> "
						+ "	<div class='category'> " + obj.category + "</div>"
						+ "	<div class='openDate'> " + obj.openDate + "</div>"
						+ "	<div class='favorite'> " + obj.favorite + "</div>"
						+ "	</a>"
						);

					});
				} else {
					$("#myPageContent1").append("<img class='emptylist' alt='오픈 예정 클래스가 없습니다.' src=" + emptyImgs[5] + "><br>");
				}
			}, error: function(XMLHttpRequest, textStatus, errorThrown) {
				alert('There is an error : method(group)에 에러가 있습니다.');
			}
		})
	} catch (e) {

	}
}

function myopenclass(){
	try {
		$.ajax({
			type: 'POST',
			url: 'myopenclass',
			dataType: 'json',
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			data: {},
			success: function(result) {
				$("#myPageContent2").empty();	
				$("#myPageContent2").append("<h4>오픈된 클래스</h4>");
				if (result.length != 0) {
					$("#myPageContent2").append("<div class='class-box'></div>");
					$.each(result, function(inx, obj) {
						$(".class-box").append("<a class='class-box-a'> "
							+ "	<div class='className'> " + obj.className + "</div> "
							+ "	<div class='category'> " + obj.category + "</div>"
							+ "	<div class='classType'> " + obj.classType + "</div>"
							+ "	<div class='recommend'> " + obj.recommend + "</div>"
							+ "	</a>"
						);

					});
				} else {
					$("#myPageContent2").append("<img class='emptylist' alt='오픈된 클래스가 없습니다.' src=" + emptyImgs[6] + "><br>");
				}

			}, error: function(XMLHttpRequest, textStatus, errorThrown) {
				alert('There is an error : method(group)에 에러가 있습니다.');
			}
		})
	} catch (e) {

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
				$("#myPageContent1").empty();
				$("#myPageContent2").empty();
				$("#myPageBtns").empty();
				
				$("#myPageFormTitle").append("받은 질문 내역");
				if (result.length != 0) {
					$("#myPageContent1").append("<table border='1' class='myClassQna'>"
					+ " <thead> <tr> <th>클래스 이름</th> <th>제목</th> <th>날짜</th> <th>ID</th> </tr> </thead>"
					+ "<tbody class='qna-qbox'></tbody></table>");
					$.each(result, function(inx, obj) {

						$(".qna-qbox").append("<tr onclick='qnaBox(" + inx + ")' class='qna-qbox"+inx+"'>"
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
							+ "<button type='button' onclick='saveQnaAnswerBtn(" + inx + ")' id='Save' class='qnabtns'></button>"
							+ "<button type='button' onclick='deleteQnaAnswerBtn(" + inx + ")' id='Delete' class='qnabtns'></button>"
							+ "</div>"
							+ "</td>"
							+ "</tr>"
						);
						$("#Save").html('SAVE');
						$("#Delete").html('DELETE');
						qnaTitleArr.push($(this).attr("qnaTitle"));
						userIdArr.push($(this).attr("userId"));
					});
				} else {
					$("#myPageBtns").append("<img class='emptylist' alt='받은 질문이 없습니다.' src=" + emptyImgs[7] + "><br>");
				}
			}, error: function(XMLHttpRequest, textStatus, errorThrown) {
				alert('There is an error : method(group)에 에러가 있습니다.');
			}
		})
	} catch (e) {

	}
}

console.log("내부 로딩 완료");
