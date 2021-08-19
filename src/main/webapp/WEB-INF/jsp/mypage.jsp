<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<<<<<<< HEAD

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
				<img alt="tatkit-logo" onclick="gotomain()" src="${pageContext.request.contextPath}/assets/img/takeit-logo.jpg" height="50px">
				<input type="search" id="search" name="search">
				<a href="#"><img alt="search-icon" src="${pageContext.request.contextPath}/assets/img/search-icon.png" height="20px"></a>
				<input type="button" onclick="logoutBtn()" value="LOGOUT" id="logoutBtn">
			</div>
		</form>
	</header>
	
	<hr>
	
	<main>
		<header>
			<a class="MyProfile" id="myInfoNav">
				<h2 id="MyProfile-UserName">${uvo.userName }</h2>
				<c:choose>
						<c:when test="${uvo.userType=='A'}">
							<img class="usertypeimg" alt="관리자 이미지" src="" height="30px">
<%-- 							${pageContext.request.contextPath}/assets/img/Atype-icon.png --%>
						</c:when>
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
			
			<form class="myPageForm" action="form">
				<div id="myPageForm" id="Container">
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
		<div>footer</div>
	</footer>
	<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/mypage-nav.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/mypage-btn.js"></script>
	<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
	
=======
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
>>>>>>> refs/remotes/origin/지현
</body>
</html>