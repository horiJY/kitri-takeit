<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>마이 페이지</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/mypage.css">

</head>
<body onload="test('${userType}')">
	<header>
		<form action="header">
			<div>
				<img id="logo" alt="tatkit-logo" onclick="gotomain()" src="${pageContext.request.contextPath}/assets/img/takeit-logo.jpg" height="50px">
				<input type="search" id="search" name="search">
				<a href="#"><img alt="search-icon" src="${pageContext.request.contextPath}/assets/img/search-icon.png" height="20px"></a>
				<input type="button" onclick="logoutBtn()" value="LOGOUT" id="logoutBtn">
			</div>
		</form>
	</header>
	
	<br>
	
	<main>
		<header>
			<a class="MyProfile" id="myInfoNav">
				<div>
					<h2 id="MyProfile-UserName">${uvo.userName }</h2>
					<c:choose>
							<c:when test="${uvo.userType=='A'}">
								<img class="usertypeimg" alt="관리자 이미지" src="${pageContext.request.contextPath}/assets/img/Atype-icon.png" height="30px">
							</c:when>
							<c:when test="${uvo.userType=='C'}">
								<img class="usertypeimg" alt="크리에이터 이미지" src="${pageContext.request.contextPath}/assets/img/Ctype-icon.png" height="30px">
							</c:when>
							<c:when test="${uvo.userType=='U'}">
								<img class="usertypeimg" alt="유저 이미지" src="${pageContext.request.contextPath}/assets/img/Utype-icon.png" height="30px">
							</c:when>
					</c:choose>
				</div>
				<br><img id="userThumnail" alt="유저아이콘" src="${uvo.userThumnail }"><br>
				<div id="userPoint">내 포인트 : ${uvo.point}P</div>
			</a><br>
		</header>
		<div class="MyPage">
			<aside class="MyPage-aside">
				<ul>
					<c:choose>
						<c:when test="${uvo.userType=='A'}">
							<li>
								<a class="AllQnA" id="allQnaNav"><!-- 관리자 받은 QNA -->
									<div id="allQnA-text">받은 문의</div>
								</a>
							</li>
						</c:when>
						<c:otherwise>
							<c:choose>
								<c:when test="${uvo.userType=='C' }">
									<li>
										<a class="MyClass" id="myClassNav"><!-- 내 강의 등록 -->
											<div id="myClass-text">내 클래스</div> 
										</a>
									</li>
									<li>
										<a class="MyClassQnA" id="myClassQnANav"><!-- 내 강의 등록 -->
											<div id="myClassQnA-text">내 클래스 질문</div> 
										</a>
									</li>
									<li>
										<a onclick="clickMPOC()" class="MyClassReg" id="myClassRegNav"><!-- 내 강의 등록 -->
											<div id="myClassReg-text">강의 등록</div> 
										</a>
									</li>
									<hr>
								</c:when>
							</c:choose>
							<li>
								<a class="MyPage-aside-Favorite" id="myFavoriteNav"><!-- 내 응원 -->
										<div id="aside-text">내 응원</div>
								</a>
							</li>
							<li>
								<a class="MyPage-aside-Assignment" id="myAssignmentNav"><!-- 주문내역 -->
										<div id="aside-text">결제 내역</div>
								</a>
							</li>
							<li>
								<a class="MyPage-aside-Review" id="myReviewNav"><!-- 내 후기 -->
										<div id="aside-text">내 후기</div>
								</a>
							</li>
							<li>
								<a class="MyPage-aside-QnA" id="myQnANav"><!-- 내 후기 -->
										<div id="aside-text">내 질문</div>
								</a>
							</li>
						</c:otherwise>
					</c:choose>
				</ul>
			</aside>
			
			<form class="myPageForm scroll" action="form">
				<div id="myPageFormContainer" class="Container">
					<h3 id="myPageFormTitle"></h3>
					<div id="myPageContent1"></div>
					<div id="myPageContent2"></div>
					<div id="myPageBtns">
					</div>
				</div>
			</form>
		</div>
	</main>
	
	<hr>
	
	<footer>
		<div></div>
	</footer>
	<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/mypage-nav.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/mypage-btn.js"></script>
	<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
	
</body>
</html>