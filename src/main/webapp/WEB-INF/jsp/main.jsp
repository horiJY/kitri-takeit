<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 
	1. 페이징
	2. class 값 가져와서 보여주기
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
			<span>takeit!</span> 
			<input type="search"> 
			<a href=""> 
				<input type="button" value="login"></a> 
			<a href=""> 
				<input type="hidden" value="mypage">
			</a>
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
					<button>카테고리</button>
					<button>추천순</button>
				</div>
			</section>
			<div>
				<ul>
					<li>
						<a>
							<div>
								<img>
							</div>
							<div>
								<div>creater</div>
								<div>class name</div>
								<div>★</div>
							</div>
							<div>
								<span>가격</span>
							</div>
							<div>
								<span>온라인/오프라인</span>
							</div>
						</a>
					</li>
					<li><a>
							<div>
								<img>
							</div>
							<div>
								<div>creater</div>
								<div>class name</div>
								<div>★</div>
							</div>
							<div>
								<span>가격</span>
							</div>
							<div>
								<span>온라인/오프라인</span>
							</div>
						</a>
					</li>
				</ul>
			</div>
			<div>
				<section>
					<div id="paging">
						<ul>
			
						</ul>
					</div>
				</section>
			</div>
		</div>
	</main>
	<footer>
	
	</footer>
</body>
</html>