//$(document).ready(function() {

	function logoutBtn(){
		$.ajax({
			type:"POST",
			url:"logout",
			success: function(){},
			error: function(XMLHttpRequest, textStatus, errorThrown) {}
		})
	}
	// 내 정보 수정
	function myInfoUpdateBtn() {
		var params = {
			userName: $("#userName").val()
			, phone: $("#phone").val()
		}

		$.ajax({
			type: "POST",
			url: "userupdate",
			data: params,
			success: function(res) {
				alert(res.code);
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) {
				alert("유저 정보 업데이트 실패")
			}
		})
	}
	function myInfoDeleteBtn() {
		$.ajax({
			type: "POST",
			url: "userupdate",
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
			url: '',		//오픈예정클래스 페이지 컨트롤러
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
			url: 'reviewdelete',
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
	function deleteQnaBtn() {	//일반 QnA
		$.ajax({
			type: 'POST',
			url: 'qna-delete',		//오픈예정클래스 페이지 컨트롤러
			success: function() {
				alert(res.code);
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) {
				alert("QnA 삭제 실패");
			}
		})
	}
	function deleteClassQnaBtn(i) {	//클래스 QnA
		$.ajax({
			type: 'POST',
			url: 'class-qna-delete',
			data: { classId: i },
			success: function(res) {
				alert(res.code);
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) {
				alert("리뷰 삭제 실패");
			}
		})
	}
	function qnaBtn() {
		$.ajax({
			type: 'POST',
			url: 'qna-regist',
			success: function(res) {
			},

			error: function(XMLHttpRequest, textStatus, errorThrown) {
				alert("리뷰 삭제 실패");
			}
		})
	}

	//내 클래스
	function createClassBtn() {	//새 클래스 만들기
		$.ajax({
			type: 'POST',
			url: 'class-regist',
			data: { classId: i },
			success: function(res) {
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) {
				alert("클래스 생성 실패");
			}
		})
	}
	function saveQnaAnswerBtn(i) {//
	var params = {
		qnaTitle: qnaTitleArr[i]
		, userId: userIdArr[i]
		, answer: $("#answerarea").val()
	}
	console.log(params);
	$.ajax({
		type: 'POST',
		url: 'qna-update',
		dataType: 'json',
		data:
			params
		,
		success: function(res) {
			alert(res.code);
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			alert("클래스 생성 실패");
		}
	})

}

	function qnaBox(i) {
		if ($("#qna-abox[" + i + "]").css('visibility', 'hidden')) {
			$("#qna-abox[" + i + "]").css('visibility', 'visible');
			$("#qnaAarea").attr('disabled', false);
		} else {
			$("#qna-abox[" + i + "]").css('visibility', 'hidden');
			$("#qnaAarea").attr('disabled', true);
		}
	}
	
	