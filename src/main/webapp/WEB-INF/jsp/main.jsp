<%@page import="vo.ClassVO"%>
<%@page import="java.util.List"%>
<%@page import="pagination.Pagination"%>
<%@page import="dao.ClassDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 
	1. 페이징
	2. class 값 가져와서 보여주기(v) - test 필요
	3. 카테고리, 추천순 정렬(v) - test 필요
	4. 아이디 받아서 마이페이지 클릭 시 보내주기
	5. 별점 갯수 보여주기
 -->
 <%
 	ClassDAO cdao = new ClassDAO();
 	String category = request.getParameter("category");
 	String range = request.getParameter("range");
 	
// 	String curPage = request.getParameter("curpage");
// 	if(curPage == null){
// 		curPage = "1";
// 	}
// 	int curPageInt = Integer.parseInt(curPage);
// 	int totalContent = cdao.selectClassCnt("O");
	
// 	Pagination pagination = new Pagination(curPageInt, totalContent, 8);
	
// 	int start = (curPageInt*pagination.getContentCnt()) - (pagination.getContentCnt() - 1);
	
// 	int end = curPageInt*pagination.getContentCnt();

	List<ClassVO> clist = cdao.selectClassList(category, range);
	
	for(int i = 0; i < clist.size(); i++){
		System.out.println(clist.get(i).getCategory());
	}
// 	List<ClassVO> clist = cdao.selectClassPage(start, end);
 %>
<!DOCTYPE html>
<%-- <c:set var="paging" value="<%=pagination %>"/> --%>
<c:set var="clist" value="<%=clist %>"/>
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
					<li>
						<a>
							<div>
								<img>
							</div>
							<div>
								<div id="creater"></div>
								<div id="class-name"></div>
								<div id="recommend"></div>
							</div>
							<div>
								<div id="price"></div>
								<div id="sale"></div>
							</div>
							<div>
								<div id="class-type"></div>
							</div>
						</a>
					</li>
				</ul>
			</div>
			<div>
				<%-- <div id="paging">
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
				</div> --%>
			</div>
		</div>
	</main>
	<footer>
	
	</footer>
	<script src="https://code.jquery.com/jquery-3.1.0.min.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/main.js"></script>
</body>
</html>