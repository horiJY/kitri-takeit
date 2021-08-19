<%@page import="vo.ClassVO"%>
<%@page import="java.util.List"%>
<%@page import="dao.ClassDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 
	1. 페이징
	2. class 값 가져와서 보여주기(v) 
	3. 카테고리, 추천순 정렬(v)
	4. 아이디 받아서 마이페이지 클릭 시 보내주기
	5. 별점 갯수 보여주기(v)
 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>main</title>

</head>
<body>
	<header>
		<div> 
			<span>takeit!</span> <input type="search">
			<c:choose> 
				<c:when test="${id eq null }"> 
					
					<a href="${pageContext.request.contextPath}/mypage"> 
					<input type="button" value="mypage" id="mypage">
					</a>
					<a href="${pageContext.request.contextPath}/logout">
						<input type="button" value="logout" id="logout">
					</a>
					
				</c:when> 
				<c:otherwise> 
					
					<a href="${pageContext.request.contextPath}/login"> 
						<input type="button" value="login" id="login">
					</a>
					
 				</c:otherwise> 
			</c:choose> 
		</div>
	</header>
	<div>
		<a href="${pageContext.request.contextPath}/main">
			<input type="button" value="바로수강">
		</a>
		<a href="${pageContext.request.contextPath}/pre-class">
			<input type="button" value="오픈예정">
		</a>
		<a href="${pageContext.request.contextPath}/QnaController">		
			<input type="button" value="고객센터">
		</a>
	</div>
	<main>
		<div>
			<section>
				<div>
					<input type="button" id="category" value="카테고리">
					<div id="category-drop" style="display:none;">
						<input type="button" id="all" value="All">
						<input type="button" id="art" value="Art">
						<input type="button" id="cooking" value="Cooking">
						<input type="button" id="language" value="Language">
						<input type="button" id="programming" value="Programming">
						<input type="button" id="sport" value="Sport">
					</div>			
					<input type="button" id="range" value="추천순">
					<div id="range-drop" style="display:none;">
						<input type="button" id="recommend" value="추천순">
						<input type="button" id="newest" value="최신순">
					</div>
				</div>
			</section>
			<div id="classList">
				<ul id="class">	
				</ul>
			</div>
			<div>
				<div id="pagnation">
					<ul id="paging">
					</ul>
				</div>
			</div>
		</div>
	</main>
	<footer>
	
	</footer>
	<% 
	String id = (String)session.getAttribute("takeit-userid");
	%>
	<script>
		var id = '<%=id%>';
	</script>
	<script src="https://code.jquery.com/jquery-3.1.0.min.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/main.js"></script>
</body>
</html>