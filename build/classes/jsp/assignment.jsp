<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!--  
	1. 개인정보(이름, 포인트) 불러오기
	2. 포인트 차감
-->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>

#black_box{
background-color: rgb(26, 26, 26);
padding: 24px;
box-sizing: border-box;
}
#black_box_title{
display:flex;
align-items: center;

}

h2{
color: white;
}
#leftPoint{
border: 1px solid ;
border-style: none;
background-color: #CEDFEE;
border-radius: 3px;
width: 200px;

}
#left{
padding :1px;
}
h4{
margin: 5px;

}
#printPoint{
margin:3px;

}
img{
width: 150px;
}
#takeit{
margin:15px;
}

#takeit > span{
padding:3px;
height: 2px;
}
#id{
display : block;

}
.bordCenter{
position: relative;
max-width:640px;
margin: 0 auto;
background-color: white;
}
#header{
padding-bottom: 3px;
}
#background{
}

</style>
</head>
<body id="all">
<div id="background">
<div class ="bordCenter">
	<header id ="header">
		<div id="takeit">
			<span>takeit!</span> 
			<input type="search">
		</div>
	</header>

	<main>
		<article>
		<header id="black_box">
			<div id="black_box_title">
				<h2>결제하기</h2>
			</div>
		</header>
		<section>
			<div>
				<h4>주문 정보</h4>
			</div>
			<div>className</div>
			<div>
			<img alt="갤럭시 탭 일상 드로잉 클래스 수강권" src="img/ticket.jpg">
			</div>
		</section>
		<div><hr></div>
		<form action="#">
			<section>
				<div><h4>연락처 정보</h4></div>
				<div>
					<div>주문자 정보</div>
					<div>
						<input type="text" placeholder="이름을 입력해 주세요." value="${AVO.username}" name="user"> 
						<%-- ${AVO.username} AVO는 서블릿에서 어트리뷰트로 만든 명 , username은 vo에 변수명 여기에 저징된 값이 화면에 반영된다.--%>
						<h6>
							<img>
							<span id="userid">이름을 입력해 주세요.</span>
						</h6>
					</div>
				</div>
				<div>
					<div>휴대폰 번호</div>
					<div>
						<input type="text" placeholder="휴대폰 번호를 입력해 주세요." value="${AVO.userphone}">
						<h6>
							<img>
							<span>올바른 휴대폰 번호를 입력해 주세요.</span>
						</h6>
					</div>
				</div>
			</section>
			<div><hr></div>
			<section>
				<div><h4>결제 금액</h4></div>
				<table>
					<tbody>
						<tr>
							<th>총 상품 금액</th>
							<td >${CVO.price}</td>
						</tr>
						<tr>
							<th>상품 할인 금액</th>
							<td>price*(sale*0.01)</td>
							<td>${CVO.price*(CVO.sale*0.01)}</td>
						
							
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<th>최종 결제 금액</th>
							<td>price*(1-(sale*0.01))</td>
							<td>${CVO.price*(1-(CVO.sale*0.01))}</td>
						</tr>
					</tfoot>
				</table>
			</section>
			<section id = "leftPoint">
				<div id ="left"><h4>잔여 포인트</h4></div>
				<div id = "printPoint">${AVO.point}</div>
			</section>
			<div>
				<input type="checkbox">
				<span>개인정보 제 3자 제공에 동의합니다. </span>
				<button>
					<span>상세보기</span>
				</button>
			</div>
			<button>
				<span>다음</span>
			</button>
		</form>
		</article>
	</main>
	<footer>
	
	</footer>
	</div>
	</div>
</body>
</html>