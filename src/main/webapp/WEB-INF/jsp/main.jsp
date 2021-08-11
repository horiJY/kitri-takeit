<%@page import="vo.ClassJson"%>
<%@page import="vo.ClassVO"%>
<%@page import="java.util.List"%>
<%@page import="pagination.Pagination"%>
<%@page import="dao.ClassDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 
	1. 페이징(v) - test 필요
	2. class 값 가져와서 보여주기(v) - test 필요
	3. 카테고리, 추천순 정렬(v) - test 필요
	4. 아이디 받아서 마이페이지 클릭 시 보내주기
	5. 별점 갯수 보여주기(ajax)
 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>main</title>
<script>
	
</script>
</head>

<body>
	<header>
		<div>
			<span>takeit!</span> <input type="search">
			<c:choose>
				<c:when test="${!empty sessionScope.userId }">
					<a href=""> 
						<input type="button" value="mypage">
					</a>
					<a href=""> 
						<input type="button" value="logout">
					</a>
				</c:when>
				<c:otherwise>
					<a href=""> 
						<input type="button" value="login">
					</a>
				</c:otherwise>
			</c:choose>
		</div>
	</header>
	<div>
		<a href="main.jsp">
			<input type="button" value="바로수강">
		</a>
		<a href="">
			<input type="button" value="오픈예정">
		</a>
		<a href="">		
			<input type="button" value="고객센터">
		</a>
	</div>
	<main>
		<div>
			<section>
				<div>
					<div id="category-drop" >
						<input type="radio" name="category" id="category" value="" checked="checked">카테고리
						<input type="radio" name="category" id="art" value="ART">Art
						<input type="radio" name="category" id="cooking" value="COOKING">Cooking
						<input type="radio" name="category" id="language" value="LANGUAGE">Language
						<input type="radio" name="category" id="programming" value="PROGRAMMING">Programming
						<input type="radio" name="category" id="sport" value="SPORT">Sport
					</div>			
					<div id="range-drop" >
						<input type="radio" name="range" id="recommend" value="RECOMMEND" checked="checked">추천순
						<input type="radio" name="range" id="newest" value="OPENDATE">최신순
					</div>
				</div>
			</section>
			<div>
				<ul>
					<li>
						<a>
							<c:forEach var="cvo" items="${clist }">
								<div>
									<img>
								</div>
								<div>
									<div id="creater"> ${cvo.creater }</div>	
									<div id="class-name"> ${cvo.className }</div>
									<div id="recommend"> ${cvo.recommend }</div>		
								</div>
								<div>
									<div id="price"> ${cvo.price }</div>
									<div id="sale"> ${cvo.sale }</div>
								</div>
								<div>
									<div id="class-type"> ${cvo.classType }</div>
								</div>
							</c:forEach> 
							
						</a>
					</li>
				</ul>
			</div>
			<div>
				<section>
					<div id="paging">
						<ul>
							<c:if test="${page.prevBtn }">
								<c:if test="${page.startPage != 1 }">
									<li>
										<a href="main.jsp?curpage=${page.startPage-1 }">
											<img src='${pageContext.request.contextPath}/assets/img/prev.png' width=15px; id="prev">
										</a>
									</li>
								</c:if>
							</c:if>
							<c:forEach var="i" begin="${page.startPage }" end="${page.endPage }" step="1">
								<c:choose>
									<c:when test="${i eq param.curpage }">
										<li>${i }</li>
									</c:when>
									<c:otherwise>
										<li><a href="main.jsp?curpage=${i }">${i }</a></li>
									</c:otherwise>

								</c:choose>
							</c:forEach>
							<c:if test="${page.nextBtn }">
								<li>
									<a href="main.jsp?curpage=${page.endPage+1 }"> 
										<img src='${pageContext.request.contextPath}/assets/img/next.png' width=15px;>
									</a>
								</li>
							</c:if>
						</ul>
					</div>
				</section>
			</div>
		</div>
	</main>
	<footer>
	
	</footer>
	<script src="https://code.jquery.com/jquery-3.1.0.min.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/main.js"></script>
</body>
</html>