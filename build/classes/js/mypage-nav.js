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
			myClassNav.onclick = function() { mypreclass(); myopenclass(); }
			myClassQnANav.onclick = function() { myClassQna(); }
			myClassRegNav.onclick = function() { clickMPOC(); }
		}
		myInfoNav.onclick = function() { myInfo(); }
		myFavoriteNav.onclick = function() { myFavorite(); }
		myAssignmentNav.onclick = function() { myAssignment(); }
		myQnANav.onclick = function() { myqna(); myclassqna(); }
		myReviewNav.onclick = function() { myReview(); }
	}
}
var qnaTitleArr = [];
var userIdArr = [];
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
						+ "<tr> <th class='qnaTitle'>제목</th> <th class='qnaDate'>날짜</th> <thclass='userId'>ID</th> </tr> </thead>"
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
				if (result.length != 0) {
					$("#myPageContent1").append("<div class='class-box'></div>");
					$.each(result, function(inx, obj) {

						$(".class-box").append("<div class='creater'> " + obj.creater + "</div>"
						+ "	<div class='className'> " + obj.className + "</div>"
						+ "	<div class='favorite'> ❤ " + obj.favorite + "</div>"
						+ "	<div class='price'> 💳 " + obj.price + "</div>"
						+ "	<div class='openDate'> 응원 마감일: " + obj.openDate + "</div>"
						+ "	<div class='classType'> " + obj.classType + "LINE CLASS</div>"
						);

					});
				} else {
					$("#myPageContent1").append("<img class='emptylist' alt='응원한 내역이 없습니다.' src=" + emptyImgs[0] + "><br>");
				}
				$("#myPageBtns").append("<button type='button' onclick='gotopreclass()' id='showPreClassBtn' value='오픈 예정 클래스 둘러보기'></button>");
				$("#showPreClassBtn").html('오픈 예정 클래스 둘러보기');
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
				if (result.length != 0) {
					$.each(result, function(inx, obj) {
						$("#myPageContent1").append("<div id='class-box"+inx+"'class='class-box'></div>");
						$("#class-box"+inx).append("<div class='creater'> " + obj.creater + "</div>"
							+ "	<div class='className'> " + obj.className + "</div>"
							+ "	<div class='price'> 💳 " + obj.price + "</div>"
							+ "	<div class='sale'> SALE " + obj.sale + "%</div>"
							+ "	<div class='recommend'> ❤ " + obj.recommend + "</div>"
							+ "	<div class='classType'> " + obj.classType + "LINE CLASS</div>"
						);

					});
				} else {
					$("#myPageContent1").append("<img class='emptylist' alt='결제한 내역이 없습니다.' src=" + emptyImgs[1] + "><br>");
				}
				$("#myPageBtns").append("<button type='button' onclick='gotomain()' id='showClassBtn'></button>");
				$("#showClassBtn").html('클래스 둘러보기');
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
				if (result.length != 0) {
					$("#myPageContent1").append("<h4>질문 내역</h4>");
					$("#myPageContent1").append("<table border='1' class='myQna'>"
					+ "<thead> <tr> <th class='qnaTitle'>제목</th> <th class='qnaDate'>날짜</th> </tr> </thead>"
					+ "<tbody id='myqnalist' class='qna_list'></tbody></table>");
					$.each(result, function(inx, obj) {
						$("#myqnalist").append("<tr id='qnaBoxList" + inx + "'>"
							+ "	<td class='qnaTitle'> " + obj.qnaTitle + "</td>"
							+ "	<td class='qnaDate'> " + obj.qnaDate + "</td>"
							+ "</tr>"
						);
						$("#myqnalist").append("<tr class='qnaAbox'>"
							+ "<td colspan='2'>"
							+ "<input type='hidden' id='qnaTitle" +inx + "' value='"+obj.qnaTitle+"'>"
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
				$("#myPageBtns").append("<button type='button' onclick='clickQB()' id='qnaBtn'></button>");
				$("#qnaBtn").html('문의하기');
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
			url: 'classqna',
			dataType: 'json',
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			data: {},
			success: function(result) {
				$("#myPageContent2").empty();
				$("#myPageContent2").append("<h4>클래스 질문 내역</h4>");
				if (result.length != 0) {
					$("#myPageContent2").append("<table border='1' class='classQna'>"
						+ "<thead> <tr> <th class='className'>클래스 이름</th> <th class='qnaTitle'>제목</th> <th class='qnaDate'>날짜</th></tr> </thead>"
					+ "<tbody id='myclassqnalist' class='qna_list'></tbody></table>");
					$.each(result, function(inx, obj) {
						$("#myclassqnalist").append("<tr id='classQnaBoxList"+inx+"'>"
						+ "		<td class='className'> " + obj.className + "</td>"
						+ "		<td class='qnaTitle'> " + obj.qnaTitle + "</td>"
						+ "		<td class='qnaDate'> " + obj.qnaDate + "</td>"
						+ "</tr>"
						);
						$("#myclassqnalist").append("<tr class='qnaAbox'>"
							+ "<td colspan='3'>"
							+ "<input type='hidden' id='qnaTitle0" +inx + "' value='"+obj.qnaTitle+"'>"
							+ "<input type='hidden' id='className0" +inx + "' value='"+obj.className+"'>"
							+ "<textarea id='question0" +inx + "' class='question' row='5' col='5'>" + obj.question + "</textarea><br>"
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
				$("#myPageContent1").append("<h4>오픈 예정 클래스</h4>");
				if (result.length != 0) {
					$.each(result, function(inx, obj) {
						$("#myPageContent1").append("<div id='mypreclass-box"+inx+"'class='class-box'></div>");
						$("#mypreclass-box"+inx).append("<a class='class-box-a'> "
						+ "	<div class='className'> " + obj.className + "</div> "
						+ "	<div class='favorite'> ❤ " + obj.favorite + "</div>"
						+ "	<div class='price'> 💳 " + obj.price + "</div>"
						+ "	<div class='openDate'> 응원 마감일: " + obj.openDate + "</div>"
						+ "	<div class='classType'> " + obj.classType + "LINE CLASS</div>"
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
					$.each(result, function(inx, obj) {
						$("#myPageContent2").append("<div id='myopenclass-box"+inx+"'class='class-box'></div>");
						$("#myopenclass-box"+inx).append("<a class='class-box-a'> "
							+ "	<div class='className'> " + obj.className + "</div> "
							+ "	<div class='recommend'> ❤: " + obj.recommend + "</div>"
							+ "	<div class='price'> 💳 " + obj.price + "</div>"
							+ "	<div class='sale'> SALE " + obj.sale + "%</div>"
							+ "	<div class='classType'> " + obj.classType + "LINE CLASS</div>"
							+ "	</a>"
						);

					});
				} else {
					$("#myPageContent2").append("<img class='emptylist' alt='오픈된 클래스가 없습니다.' src=" + emptyImgs[6] + "><br>");
				}
				$("#myPageBtns").append("<button type='button' onclick='clickMPOC()' id='createClassBtn'></button>");
				$("#createClassBtn").html('강의 등록하기');
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
					+ " <thead> <tr> <th class='className'>클래스 이름</th> <th class='qnaTitle'>제목</th> <th class='qnaDate'>날짜</th> <th class='userId'>ID</th> </tr> </thead>"
					+ "<tbody class='qna-qbox'></tbody></table>");
					$.each(result, function(inx, obj) {

						$(".qna-qbox").append("<tr onclick='qnaBox(" + inx + ")' class='qna-qbox"+inx+"'>"
							+ "	<td class='className'> " + obj.className + "</td> "
							+ "	<td class='qnaTitle'> " + obj.qnaTitle + "</td>"
							+ "	<td class='qnaDate'> " + obj.qnaDate + "</td>"
							+ "	<td class='userId'> " + obj.userId + "</td>"
							+ "</tr>");
						$(".qna-qbox").append("<tr id='qna_abox' class='qna_abox'>"
							+ "<td colspan='4'>"
							+ "<input type='hidden' id='qnaTitle" +inx + "' value='"+obj.qnaTitle+"'>"
							+ "<input type='hidden' id='className" +inx + "' value='"+obj.className+"'>"
							+ "<input type='hidden' id='userId" +inx + "' value='"+obj.userId+"'>"
							+ "<div><span id='question'>" + obj.question + "</span></div>"
							+ "<textarea id='answer"+inx+"' class='answerarea' row='5' col='5'>" + obj.answer + "</textarea><br>"
							+ "<div class='qnaAboxBtns'>"
							+ "<button type='button' onclick='clickSCQAB(" + inx + ")' id='Save' class='qnasbtns'></button>"
							+ "<button type='button' onclick='cliskDCQAB(" + inx + ")' id='Delete' class='qnadbtns'></button>"
							+ "</div>"
							+ "</td>"
							+ "</tr>"
						);
						$(".qnasbtns").html('SAVE');
						$(".qnadbtns").html('DELETE');
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
