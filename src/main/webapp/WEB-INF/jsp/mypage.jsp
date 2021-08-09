<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이 페이지</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/mypage.css">
<script src="${pageContext.request.contextPath }/assets/js/mypage.js"></script>
</head>
<body>
	<header>
	</header>
	
	<main>
		<header>
			<a class="MyProfile" href="#">
				<h2 class="MyProfile-UserName">이름</h2>
				<div>
					<img alt="타입 이미지" src="">
				</div>
				<div>
					이메일<svg></svg>
				</div>
			</a>
		</header>
		<div class="MyPage">
			<aside class="MyPage-aside">
				<h4 class="MyPage-aside-title">내 정보</h4>
				<ul>
					<li>
						<a class="MyPage-aside-MyPoint" href="#" id="mypoint"><!-- 내 포인트 -->
							<div class="MyPoint-text">내 포인트</div> 
							<div class="MyPoint-num">P</div> 
						</a>
					</li>
					<li>
						<a class="MyPage-aside-Favorite" href="#" id="myfavorite"><!-- 내 응원 -->
							<div class="Favorite-text">내 응원</div> 
						</a>
					</li>
					<li>
						<a class="MyPage-aside-Assignment" href="#" id="myassignment"><!-- 주문내역 -->
							<div class="MyPoint-text">주문내역</div> 
						</a>
					</li>
					<li>
						<a class="MyPage-aside-Review" href="#" id="myreview"><!-- 내 후기 -->
							<div class="MyPoint-text">내 후기</div> 
						</a>
					</li>
				</ul>
			</aside>
			<div class="MyPage-Container">
				<h3 class="MyPage-Container-title"></h3>
				<div class="MyPage-Container-content"></div>
				<button class="MyPage-Container-button"><span></span></button>
			</div>
		</div>
	</main>
	
	<footer>
	</footer>
</body>
</html>