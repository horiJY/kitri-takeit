//$(document).ready(function() {



//admin- 답변
function saveQnaAnswerBtn(i) {//
//   debugger;
   
   var params = {
      qnaTitle: $("#qnaTitle"+i).val()
		, userId: $("#userId"+i).val()
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
      qnaTitle: $("#qnaTitle"+i).val()
		, userId: $("#userId"+i).val()
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
function clickMIUB() {   //click myInfoUpdateBtn
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
function clickMIDB() {   //click myInfoDeleteBtn
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
      url: '',   //리뷰 작성 페이지
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
      qnaTitle: $("#qnaTitle"+i).val()
   }
   $.ajax({
      type: 'POST',
      url: 'userqna-delete',
      dataType: 'json',
      data: params,
      success: function(res) {
         console.log(res)
         alert(res.code);
         window.location.reload();
      },
      error: function(XMLHttpRequest, textStatus, errorThrown) {
         alert("QnA 삭제 실패");
      }
   })
}
//내 CLASS QNA
function clickSCQQB(i) {//click saveClassQnaQuestionBtn
   
   var params = {
      qnaTitle: $("#qnaTitle0"+i).val()
      , question: $("#question0"+i).val()
      , className: $("#className0"+i).val()
   }
   console.log(params);
   $.ajax({
      type: 'POST',
      url: 'class-qna-update',
      dataType: 'json',
      data: params,
      success: function(res) {
         alert(res.code);
         window.location.reload();
      },
      error: function(XMLHttpRequest, textStatus, errorThrown) {
         alert("Qna 업뎃 실패");
      }
   })

}

function clickDCQQB(i) {//click deleteClassQnaQuestionBtn
   var params = {
      qnaTitle: $("#qnaTitle0"+i).val()
      , className: $("#className0"+i).val()
   }
   $.ajax({
      type: 'POST',
      url: 'class-qna-delete',
      dataType: 'json',
      data: params,
      success: function(res) {
         alert("삭제되었습니다.");
         window.location.reload();
      },
      error: function(XMLHttpRequest, textStatus, errorThrown) {
         alert("QnA 삭제 실패");
      }
   })
}
function clickSCQAB(i) {//click saveClassQnaQuestionBtn
   
   var params = {
      qnaTitle: $("#qnaTitle"+i).val()
      , answer: $("#answer"+i).val()
      , className: $("#className"+i).val()
      , userId: $("#userId"+i).val()
   }
   console.log(params);
   $.ajax({
      type: 'POST',
      url: 'myclass-qna-update',
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
function cliskDCQAB(i) {//click deleteClassQnaQuestionBtn
   var params = {
      qnaTitle: $("#qnaTitle"+i).val()
      , className: $("#className"+i).val()
      , userId: $("#userId"+i).val()
   }
   console.log(params);
   $.ajax({
      type: 'POST',
      url: 'myclass-qna-delete',
      data: params,
      success: function(res) {
         alert(res.code);
         window.location.reload();
      },
      error: function(XMLHttpRequest, textStatus, errorThrown) {
         alert("QnA 삭제 실패");
      }
   })
}