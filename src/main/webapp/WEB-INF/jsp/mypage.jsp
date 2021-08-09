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
	</header>
	
	<main>
		<header>
			<a class="MyProfile" href="#myinfoedit" id="myInfoNav">
				<h2 class="MyProfile-UserName">${uvo.userId }</h2>
				<c:choose>
						<c:when test="${uvo.userType=='C'}">
							<img alt="크리에이터 이미지" src="${pageContext.request.contextPath}/assets/img/Ctype-icon.png" width="50px">
						</c:when>
						<c:when test="${uvo.userType=='U'}">
							<img alt="유저 이미지" src="${pageContext.request.contextPath}/assets/img/Utype-icon.png" width="50px">
						</c:when>
				</c:choose>
			</a><br>
			<button type="button" value="LOGOUT" id="logoutBtn"></button>
		</header>
		<div class="MyPage">
			<aside class="MyPage-aside">
				<h4 class="MyPage-aside-title">내 정보</h4>
				<ul>
					<li>
						<a class="MyPage-aside-MyPoint" href="#mypoint" id="myPointNav"><!-- 내 포인트 -->
							<div class="aside-text">내 포인트</div> 
							<div>${uvo.point}P</div>
						</a>
					</li>
					<li>
						<a class="MyPage-aside-Favorite" href="#myfavorite" id="myFavoriteNav"><!-- 내 응원 -->
							<div class="aside-text">내 응원</div> 
						</a>
					</li>
					<li>
						<a class="MyPage-aside-Assignment" href="#myassignment" id="myAssignmentNav"><!-- 주문내역 -->
							<div class="aside-text">주문내역</div> 
						</a>
					</li>
					<li>
						<a class="MyPage-aside-Review" href="#myreview" id="myReviewNav"><!-- 내 후기 -->
							<div class="aside-text">내 후기</div> 
						</a>
					</li>
					<li>
						<a class="MyPage-aside-QnA" href="#myqna" id="myQnANav"><!-- 내 후기 -->
							<div class="aside-text">내 질문</div> 
						</a>
					</li>
					<c:choose>
						<c:when test="${uvo.userType=='C' }">
							<li>
								<a class="Create-class" href="#" id="createClass"><!-- 내 강의 등록 -->
									<div class="createClass-text">강의 등록</div> 
								</a>
							</li>
						</c:when>
					</c:choose>
				</ul>
			</aside>
			
			<!-- My Info -->
			<div id="myInfoEdit" class="Container">
				<h3>내 정보 수정</h3>
				<div class="MyInfoContent">
					<label>ID<input type="text" value="${uvo.userId }" id="userId" name="userId" readonly></label><br>
					<label>PW<input type="password" value="" id="userPw" name="userPw" required></label><br>
					<label>이름<input type="text" value="${uvo.userName }" id="userName" name="userName" required></label><br>
					<label>전화번호<input type="text" value="${uvo.phone }" id="userPhone"name="userPhone" required></label><br>
					<button type="button" value="SAVE" id="myInfoUpdateBtn"></button>
				</div>
			</div>
			
			<!-- My point -->
			<article id="myPoint" class="Container">
				<h3>내 포인트</h3>
				<div class="MyPointContent"></div>
			</article>
			
			<!-- My Favorite -->
			<article id="myFavorite" class="Container">
				<h3>내 응원 내역</h3>
				<div class="MyFavoriteContent"></div>
			</article>
			
			<!-- My Assignment -->
			<article id="myAssignment" class="Container">
				<h3>주문 내역</h3>
				<div class="MyAssignmentContent"></div>
			</article>
			
			<!-- My Review -->
			<article id="myReview" class="Container">
				<h3>내 리뷰</h3>
				<div class="MyReviewContent"></div>
			</article>
			
			<!-- My QnA -->
			<article id="myQnA" class="Container">
				<h3>내 질문</h3>
				<div class="MyQnAContent"></div>
				<button type="button" value="문의하기" id="qnaBtn"></button>
			</article>
		</div>
	</main>
	
	<footer>
	</footer>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/mypage-nav.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/mypage-btn.js"></script>
</body>
</html>