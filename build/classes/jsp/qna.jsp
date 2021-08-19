<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Insert title here</title>
<style>
@import url('https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap');


section {
	margin: 0 auto;
	width: 700px;
}

h1 {
	text-align: center;
}

div>h2>img {
	width: 16px;
	height: 16px;
}

#content {
	display: none;
	font-family: 'Gowun Dodum', sans-serif;
}

input {
	margin: 10px;
}

input[type=submit]{

width:50px;
height:30px;
background :linear-gradient(to right, #e3e3e3,#fcfcfc);
/* background-color:#26A69A; */
border:1px  solid #ccc;
border-radius: 4px;

}
</style>


<script src="assets/qna.js"></script>
</head>
<body>
	<h1>자주 묻는 질문</h1>
	
	<section>

		<div class="row-title" id="what">
			<h2>
				Q. 어떤 클래스를 개설 할 수 있나요? <img src="img/chevron.svg" alt="under">
			</h2>
		</div>

		<!-- Q&A -->
		<div id="content">TAKE IT에서는 취미부터 직무, 부업까지 다양한 카테고리의 온라인 클래스들이
			오픈되고 있어 어떤 카테고리ㅣ의 클리스이든 상관없이, 누구나 클래스를 개설하실 수 있습니다. 다만, 원데이 클래스 혹은
			오프라인 클래스 형식의 클래스는 현재 TAKE IT에서 오픈하기 어렵습니다.</div>




		<div class="row-title" id="what1">
			<h2>
				Q. 클래스 개설하는데 비용이 드나요? <img src="img/chevron.svg" alt="under">
			</h2>
		</div>
		<!-- Q&A -->
		<div id="content">클래스의 개설은 모두 무료이므로 수요조사 진행에 대한 수수료가 발생하기 않습니다.
			수수료는 멤버십에 따라 차이가 있으며, 클래스 판매 금액에 따라 일정 비율로 부과 되고 있어요.</div>

		<div class="row-title" id="what2">
			<h2>
				Q. 클래스 개설하려면 무엇이 필요한가요? <img src="img/chevron.svg" alt="under">
			</h2>
		</div>
		<!-- Q&A -->
		<div id="content">클래스로 진행해보고자 하는 주제와 간단한 소개, 그리고 함께 만들게 될 작품의
			사진만 있으면 쉽고 빠르게 수요 조사를 시작하실 수 있어요.</div>

		<div class="row-title" id="what3">
			<h2>
				Q. 사업자로 등록해야 판매가 가능한가요? <img src="img/chevron.svg" alt="under">
			</h2>
		</div>
		<!-- Q&A -->
		<div id="content">사업자가 없으셔도 클래스의 개설 및 판매가 가능합니다. 단, 크리에이터님께서 직접
			준비물 키트를 배송하고자 하시는 경우에는 사업자등록증이 필요해요. 또한 준비물의 종류에 따라 적절한 허가증이 필요해요.</div>

		<div class="row-title" id="what4">
			<h2>
				Q. 수요조사 이후에는 어떻게 진행되나요? <img src="img/chevron.svg" alt="under">
			</h2>
		</div>
		<!-- Q&A -->
		<div id="content">7일간 받은 하트 수를 통해 수요를 확인 후, 결과에 따라 클래스 판매에
			도전해보실수 있어요. 구체적인 클래스 소개와 커리큘럼을 바탕으로 수강 신청을 오픈하고,판매 추이를 바탕으로 영상 제작까지
			진행하게 됩니다.</div>

		<div class="row-title" id="what5">
			<h2>
				Q. 클래스를 어떻게 홍보할 수 있나요? <img src="img/chevron.svg" alt="under">
			</h2>
		</div>
		<!-- Q&A -->
		<div id="content">클래스들을 에비 수강생에게 알리기 위해 클래스 홍보를 다양한 곳에서 진행하고있어요.
		 클래스를 좋아할 만한 수강생에 대한 크리에이터님의 노하우를 바탕으로 SNS,커뮤니티에서 클래스 홍보를 해주시면 더효과적으로 클래스를 알릴 수 있어요.</div>
		<button id="insert-btn">글 작성</button>
<!-- 		<button class="bubbly-button">글작성</button> -->
		<div id="content">
			<form action="QnaController" method="post">
				<input type="text" name="qna_title" placeholder="제목을 입력해주세요." onclick="validator()">
				
				
				<textarea rows="15" cols="70" name="qna" placeholder="내용을 입력해주세요."  onclick="validator()"></textarea>
<!-- 				<input type="submit" id="save-btn" value="save"> -->
 					<input type="button" id="save-btn" value="save" onclick="validator()" >
 					<script type="text/javascript">
 						function validator() {
 							var qnaForm = document.forms[0];
 							// 만약 qna title에 입력값이 없으면
 							//alert(qnaForm.qna_title.value);
 							//alert(qnaForm.qna.value);
 							if(qnaForm.qna_title.value==null || qnaForm.qna_title.value == ""){
 								
 								alert("제목은 필수 항목 입니다.");
 								qnaForm.qna_title.focus();
 								return;
 							} else if (qnaForm.qna.value==null || qnaForm.qna.value == "") {
 								alert("내용은 필수 항목 입니다.");
 								qnaForm.qna.focus();
 								return;
 							} else {
 								qnaForm.submit();
 							} 							
 						}
 					
 					</script>
			</form>
		</div>
	</section>
</body>
</html>