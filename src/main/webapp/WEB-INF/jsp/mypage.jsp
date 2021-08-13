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
<body>
	<header>
		<form action="header">
			<div>
				<img alt="tatkit-logo" src="${pageContext.request.contextPath}/assets/img/takeit-logo.jpg" height="50px">
				<input type="text" id="search" name="search">
				<a href="#"><img alt="search-icon" src="" height="20px"></a>
				<input type="button" value="LOGOUT" id="logoutBtn">
			</div>
		</form>
	</header>
	
	<hr>
	
	<main>
		<header>
			<a class="MyProfile" id="myInfoNav">
				<h2 class="MyProfile-UserName">${uvo.userName }</h2>
				<c:choose>
						<c:when test="${uvo.userType=='C'}">
							<img class="usertypeimg" alt="크리에이터 이미지" src="${pageContext.request.contextPath}/assets/img/Ctype-icon.png" height="30px">
						</c:when>
						<c:when test="${uvo.userType=='U'}">
							<img class="usertypeimg" alt="유저 이미지" src="${pageContext.request.contextPath}/assets/img/Utype-icon.png" height="30px">
						</c:when>
				</c:choose>
				<img id="userThumnail" alt="유저아이콘" src="${uvo.userThumnail }"><br>
				<span>내 포인트 : ${uvo.point}P</span>
			</a><br>
		</header>
		<div class="MyPage">
			<aside class="MyPage-aside">
				<ul>
					<li>
						<a class="MyPage-aside-Favorite" id="myFavoriteNav"><!-- 내 응원 -->
							<div class="aside-text">내 응원</div> 
						</a>
					</li>
					<li>
						<a class="MyPage-aside-Assignment" id="myAssignmentNav"><!-- 주문내역 -->
							<div class="aside-text">결제 내역</div> 
						</a>
					</li>
					<li>
						<a class="MyPage-aside-Review" id="myReviewNav"><!-- 내 후기 -->
							<div class="aside-text">내 후기</div> 
						</a>
					</li>
					<li>
						<a class="MyPage-aside-QnA" id="myQnANav"><!-- 내 후기 -->
							<div class="aside-text">내 질문</div> 
						</a>
					</li>
					<c:choose>
						<c:when test="${uvo.userType=='C' }">
							<hr>
							<li>
								<a class="MyClass" id="myClassNav"><!-- 내 강의 등록 -->
									<div class="myClass-text">내 클래스</div> 
								</a>
							</li>
							<li>
								<a class="MyClassQnA" id="myClassQnANav"><!-- 내 강의 등록 -->
									<div class="myClassQnA-text">내 클래스 질문</div> 
								</a>
							</li>
							<li>
								<a class="Create-class" id="createClass"><!-- 내 강의 등록 -->
									<div class="createClass-text">강의 등록</div> 
								</a>
							</li>
						</c:when>
					</c:choose>
				</ul>
			</aside>
			
			<form class="myPageForm" action="form">
				<div id="myPageForm" id="Container">
					<h3 id="myPageFormTitle"></h3>
					<div id="myPageContent"></div>
					<div id="myPageBtns">
					</div>
				</div>
			</form>
		</div>
	</main>
	
	<hr>
	
	<footer>
		<div>footer</div>
	</footer>
	<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/mypage-nav.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/mypage-btn.js"></script>
</body>
</html>