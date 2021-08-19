//$(document).ready(function() {

function logoutBtn() {
	$.ajax({
		type: "POST",
		url: "logout",
		success: function() { },
		error: function(XMLHttpRequest, textStatus, errorThrown) { }
	})
}

function gotomain(){
	$.ajax({
		type: "POST",
		url: "main",
		success: function() {
			location.href="/mypage/openclass";
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) { }
	})
}



//admin- 답변
function saveQnaAnswerBtn(i) {//
//	debugger;
	
	var params = {
		qnaTitle: qnaTitleArr[i]
		, userId: userIdArr[i]
		, answer: $("#answer"+i).val()
	}
	console.log(params);
	$.ajax({
		type: 'POST',
		url: 'qna-update',
		dataType: 'json',
		data: params,
		success: function(res) {
			alert(res.code);
			window.location.reload();
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			alert("클래스 생성 실패");
		}
	})

}
function deleteQnaBtn(i) {//
	
	var params = {
		qnaTitle: qnaTitleArr[i]
		, userId: userIdArr[i]
	}
	console.log(params);
	$.ajax({
		type: 'POST',
		url: 'qna-delete',
		dataType: 'json',
		data: params,
		success: function(res) {
			alert(res.code);
			window.location.reload();
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			alert("클래스 생성 실패");
		}
	})

}

// 내 정보 수정
function clickMIUB() {	//click myInfoUpdateBtn
	var params = {
		userName: $("#userName").val()
		, phone: $("#phone").val()
	}
	console.log(params);
	$.ajax({
		type: "POST",
		url: "user-update",
		data: params,
		success: function(res) {
			console.log(res.code);
			alert("성공적으로 업데이트 하였습니다.");
			window.location.reload();
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			alert("유저 정보 업데이트 실패");
		}
	})
}
function clickMIDB() {	//click myInfoDeleteBtn
	$.ajax({
		type: "POST",
		url: "user-delete",
		success: function(res) {
			alert(res.code);
			
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			alert("회원 탈퇴 실패")
		}
	})
}



// 내 응원
function deleteFavoriteBtn(i) {
	$.ajax({
		type: 'POST',
		url: 'favoritedelete',
		data: { classId: i },
		success: function(res) {
			console.log(i);
			alert(res.code);
		},

		error: function(XMLHttpRequest, textStatus, errorThrown) {
			alert("응원 내역 삭제 실패");
		}
	})
}
function showpreClassBtn() {
	$.ajax({
		type: 'POST',
		url: 'pre-class',		//오픈예정클래스 페이지 컨트롤러
		success: function() { },
		error: function(XMLHttpRequest, textStatus, errorThrown) { }
	})
}

//내 결제목록
function showClassBtn() {
	$.ajax({
		type: 'POST',
		url: 'main',
		success: function() { },
		error: function(XMLHttpRequest, textStatus, errorThrown) { }
	})
}

//내 리뷰
function deleteReviewBtn(i) {
	$.ajax({
		type: 'POST',
		url: 'review-delete',
		data: { classId: i },
		success: function(res) {
			alert(res.code);
		},

		error: function(XMLHttpRequest, textStatus, errorThrown) {
			alert("리뷰 삭제 실패");
		}
	})

}
function reviewBtn() {
	$.ajax({
		type: 'POST',
		url: '',	//리뷰 작성 페이지
		success: function() { },
		error: function(XMLHttpRequest, textStatus, errorThrown) { }
	})
}

//내 QNA
function clickSQQB(i) {//click saveQnaQuestionBtn
	
	var params = {
		qnaTitle: $("#qnaTitle"+i).val()
		, question: $("#question"+i).val()
	}
	console.log(params);
	$.ajax({
		type: 'POST',
		url: 'userqna-update',
		dataType: 'json',
		data: params,
		success: function(res) {
			alert(res.code);
			window.location.reload();
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			alert("클래스 생성 실패");
		}
	})

}

function clickDQQB(i) {//click deleteQnaQuestionBtn
	var params = {
		qTitle: $("#qnaTitle"+i).val()
	}
	$.ajax({
		type: 'POST',
		url: 'userqna-delete',
		data: params,
		success: function() {
			alert(res.code);
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			alert("QnA 삭제 실패");
		}
	})
}
//내 CLASS QNA
function clickSCQQB(i) {//click saveClassQnaQuestionBtn
	
	var params = {
		qTitle: $("#qnaTitle"+i).val()
		, question: $("#question"+i).val()
		, className: $("#className"+i).val()
	}
	console.log(params);
	$.ajax({
		type: 'POST',
		url: 'userclassqna-update',
		dataType: 'json',
		data: params,
		success: function(res) {
			alert(res.code);
			window.location.reload();
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			alert("클래스 생성 실패");
		}
	})

}

function clickDCQQB(i) {//click deleteClassQnaQuestionBtn
	var params = {
		qTitle: $("#qnaTitle"+i).val()
		, className: $("#className"+i).val()
	}
	$.ajax({
		type: 'POST',
		url: 'userclassqna-delete',
		data: params,
		success: function() {
			alert(res.code);
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			alert("QnA 삭제 실패");
		}
	})
}

//function qnaBtn() {
//	$.ajax({
//		type: 'POST',
//		url: 'qna-regist',
//		success: function(res) {
//		},
//
//		error: function(XMLHttpRequest, textStatus, errorThrown) {
//			alert("리뷰 삭제 실패");
//		}
//	})
//}
//
////내 클래스
//function createClassBtn() {	//새 클래스 만들기
//	$.ajax({
//		type: 'POST',
//		url: 'class-regist',
//		data: { classId: i },
//		success: function(res) {
//		},
//		error: function(XMLHttpRequest, textStatus, errorThrown) {
//			alert("클래스 생성 실패");
//		}
//	})
//}
//
//
//function qnaBox(i) {
//	if ($("#qna-abox[" + i + "]").css('visibility', 'hidden')) {
//		$("#qna-abox[" + i + "]").css('visibility', 'visible');
//		$("#qnaAarea").attr('disabled', false);
//	} else {
//		$("#qna-abox[" + i + "]").css('visibility', 'hidden');
//		$("#qnaAarea").attr('disabled', true);
//	}
//}
//
