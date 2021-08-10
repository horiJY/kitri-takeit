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
<%
	String category = null;
	String range = "recommend";
	
	ClassDAO cdao = new ClassDAO();

	String curPage = request.getParameter("curpage");
	if(curPage == null){
		curPage = "1";
	}
	int curPageInt = Integer.parseInt(curPage);
	int totalContent = cdao.selectClassCnt("O");
	
	Pagination pagination = new Pagination(curPageInt, totalContent, 5);
	
	//한 페이지 내에 보여줘야 하는 게시물의 첫 번째 rownum
	int start = (curPageInt*pagination.getContentCnt()) - (pagination.getContentCnt() - 1);
	
	//한 페이지 내에 보여줘야 하는 게시물의 마지막 rownum
	int end = curPageInt*pagination.getContentCnt();
	List<ClassVO> clist = cdao.selectClassPage(category, range, start, end);
%>
<!DOCTYPE html>
<c:set var="paging" value="<%=pagination %>"/>
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
					<button id="category-btn"><div id="category-name">카테고리</div></button>
					<div id="category-drop" style="display:none;">
						<button id="art">Art</button>
						<button id="cooking">Cooking</button>
						<button id="language">Language</button>
						<button id="programming">Programming</button>
						<button id="sport">Sport</button>
					</div>
					<button id="range-btn"><div id="range-name">추천순</div></button>
					<div id="range-drop" style="display:none;">
						<button id="recommend">추천순</button>
						<button id="newest">최신순</button>
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
									<div> ${cvo.creater }</div>	
									<div> ${cvo.className }</div>
									<div> ${cvo.recommend }</div>		
								</div>
								<div>
									<div> ${cvo.price }</div>
									<div> ${cvo.sale }</div>
								</div>
								<div>
									<div> ${cvo.classType }</div>
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
	<script src="${pageContext.request.contextPath}/assets/js/main.js"></script>
</body>
</html>