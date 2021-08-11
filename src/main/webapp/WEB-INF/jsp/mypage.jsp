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
		<div>
			<img alt="tatkit-logo" src="${pageContext.request.contextPath}/assets/img/takeit-logo.jpg" height="50px">
			<input type="text" id="search" name="search">
			<a href="#"><img alt="search-icon" src="" height="20px"></a>
			<input type="button" value="LOGOUT" id="logoutBtn">
		</div>
	</header>
	
	<hr>
	
	<main>
		<header>
			<a class="MyProfile" href="#myinfoedit" id="myInfoNav">
				<h2 class="MyProfile-UserName">${uvo.userId }</h2>
				<c:choose>
						<c:when test="${uvo.userType=='C'}">
							<img alt="크리에이터 이미지" src="${pageContext.request.contextPath}/assets/img/Ctype-icon.png" height="30px">
						</c:when>
						<c:when test="${uvo.userType=='U'}">
							<img alt="유저 이미지" src="${pageContext.request.contextPath}/assets/img/Utype-icon.png" height="30px">
						</c:when>
				</c:choose>
				<span>내 포인트 : ${uvo.point}P</span>
			</a><br>
		</header>
		<div class="MyPage">
			<aside class="MyPage-aside">
				<h4 class="MyPage-aside-title">내 정보</h4>
				<ul>
					<li>
						<a class="MyPage-aside-Favorite" id="myFavoriteNav"><!-- 내 응원 -->
							<div class="aside-text">내 응원</div> 
						</a>
					</li>
					<li>
						<a class="MyPage-aside-Assignment" id="myAssignmentNav"><!-- 주문내역 -->
							<div class="aside-text">주문내역</div> 
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
								<a class="Create-class" id="createClass"><!-- 내 강의 등록 -->
									<div class="createClass-text">강의 등록</div> 
								</a>
							</li>
						</c:when>
					</c:choose>
				</ul>
			</aside>
			
			<!-- My Info -->
			<form action="formI">
				<div id="myInfoEdit" class="Container">
					<h3>내 정보 조회 / 수정</h3>
					<div id="MyInfoContent">
						<label>ID<input type="text" value="${uvo.userId }" id="userId" name="userId" readonly></label><br>
						<label>이름<input type="text" value="${uvo.userName }" id="userName" name="userName" required></label><br>
						<label>전화번호<input type="text" value="${uvo.phone }" id="userPhone"name="userPhone" readonly></label><br>
						<input type="button" value="정보 수정하기" id="myInfoUpdateBtn"><br>
						<input type="button" value="회원탈퇴" id="userDeleteBtn">
					</div>
				</div>
			</form>
			
			<!-- My Favorite -->
			<form action="formF">
				<article id="myFavorite" class="Container">
					<h3>내 응원 내역</h3>
					<div id="MyFavoriteContent"></div>
				</article>
			</form>
			
			<!-- My Assignment -->
			<form action="formA">
				<article id="myAssignment" class="Container">
					<h3>주문 내역</h3>
					<div id="MyAssignmentContent"></div>
				</article>
			</form>
			
			<!-- My Review -->
			<form action="formR">
				<article id="myReview" class="Container">
					<h3>내 리뷰</h3>
					<div id="MyReviewContent"></div>
				</article>
			</form>
			
			<!-- My QnA -->
			<form action="formQ">
				<article id="myQnA" class="Container">
					<h3>내 질문</h3>
					<div id="MyQnAContent"></div>
					<input type="button" value="문의하기" id="qnaBtn">
				</article>
			</form>
			<!-- My Class -->
			<form action="formC">
				<article id="myClass" class="Container">
					<h3>내 클래스</h3>
					<div id="MyClassContent"></div>
				</article>
			</form>
		</div>
	</main>
	
	<hr>
	
	<footer>
	</footer>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/mypage-nav.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/mypage-btn.js"></script>
</body>
</html>