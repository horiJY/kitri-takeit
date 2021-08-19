<%@page import="com.google.gson.JsonObject"%>
<%@page import="com.google.gson.JsonArray"%>
<%@page import="com.google.gson.JsonElement"%>
<%@page import="com.google.gson.JsonParser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 
   1. 클래스 신청하기 버튼 클릭 시 - 로그인 or 신청 페이지
   2. 클래스 상세정보 받아와서 뿌려주기
   3. 공유하기API
 -->
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
* {
	box-sizing: border-box;
}

article, aside, dialog, figcaption, figure, footer, header, hgroup, main,
	nav, section {
	display: block;
}

body {
	margin: 0 auto;
}

header, section, aside {
	margin: 5px;
	padding: 10px;
}

header {
	border: 1px solid black;
}

section {
	/* border: 1px solid green; */
	/* width: 679px; */
	
}

aside {
	/* border: 1px solid red; */
	/* float: right; */
	/* width: 350px; */
	
}

p {
	width: 100%;
	height: 100%;
}

a {
	text-decoration: none;
	font-size: 14px;
	line-height: 20px;
	letter-spacing: -0.15px;
	margin: 0px;
	-webkit-box-pack: center;
	justify-content: center;
	-webkit-box-align: center;
	align-items: center;
	color: rgb(26, 26, 26);
	font-weight: bold;
}

/* div{ */
/* display: block; */
/* } */
.contentAll {
	margin-left: 3opx;
	margin-right: 20px;
	padding-top: 24px;
}

.leftSide {
	display: flex;
	flex-wrap: wrap;
}

#content {
	width: 66.6667%;
	position: static;
	padding-right: 12px;
	padding-left: 12px;
	box-sizing: border-box;
}

#floating {
	position: static;
	width: 33.3333%;
}

#float {
	position: static;
	padding: 20px;
	border: 1px solid rgb(255, 255, 255);
	border-radius: 3px;
	box-shadow: rgb(41 42 43/ 16%) 0px 2px 6px -2px;
}

#cancel {
	width: 16px;
	height: 16px;
}

#hidden_box {
	position: fixed;
	display: flex;
	inset: 0px;
	background-color: rgba(0, 0, 0, 0.72);
	visibility: hidden;
}

#floatOptions {
	position: relative;
	display: flex;
	align-items: center;
	margin-top: 16px;
}

.option_recomm :first-child {
	margin-right: 12px;
}

#application {
	margin-top: 10px;
}

div>button {
	width: 150px;
	height: 50px;
	border: none;
}

div>button:active, focus {
	border: none;
	background-color: rgb(223, 223, 223);
}

img {
	width: 50px;
	height: 50px;
}

#modalSheet {
	/* display: flex; */
	flex-direction: column;
	padding: 32px;
	width: 480px;
	max-height: 800px;
	border-radius: 8px;
	background-color: white;
	z-index: 500;
}

#stickyTab {
	background-color: rgb(255, 255, 255);
	white-space: nowrap;
	position: sticky;
	top: 0px;
	overflow-x: auto;
	box-shadow: rgb(239, 239, 239) 0px -1px 0px 0px inset;
}

#stickyTab_content {
	width: 100%;
	z-index: 100;
	display: flex;
	-webkit-box-align: center;
	align-items: center;
	-webkit-box-pack: justify;
	justify-content: space-between;
	margin: 0px auto;
}

#tabs {
	display: flex;
	white-space: nowrap;
	overflow-x: auto;
}

.tabitem_TabBox_1 {
	isplay: flex;
	-webkit-box-align: center;
	align-items: center;
	-webkit-box-pack: center;
	justify-content: center;
	padding: 14px 0px 13px;
	flex-basis: auto;
	margin-right: 24px;
	cursor: pointer;
	position: relative;
	color: rgb(26, 26, 26);
}

.tabitem_TabBox {
	-webkit-box-align: center;
	align-items: center;
	-webkit-box-pack: center;
	justify-content: center;
	padding: 14px 0px 13px;
	flex-basis: auto;
	margin-right: 24px;
	cursor: pointer;
	position: relative;
	color: rgb(162, 162, 162);
}

.styledDiv {
	position: relative;
	margin-left: auto;
	margin-right: auto;
}

.PostViewCintroller {
	padding: 48px 0px;
	display: block;
}

h2 {
	font-size: 24px;
	font-weight: bold;
	line-height: 34px;
	letter-spacing: -0.4px;
	margin: 0px;
	color: rgb(26, 26, 26);
}

.PostViewCintroller_infoContainer {
	margin-top: 24px;
	margin-bottom: 16px;
	display: flex;
	-webkit-box-align: center;
	align-items: center;
}

.PostViewCintroller_infoCard {
	width: 50%;
	display: inline-flex;
	-webkit-box-pack: center;
	justify-content: center;
	text-align: center;
}

div>a {
	text-decoration: none;
	color: inherit;
	/*     display: block; */
}

.PostViewCintroller_veticalDivider {
	flex: 0 0 auto;
	width: 1px;
	height: 50px;
	background: rgb(248, 248, 248);
}

.unit {
	font-size: 18px;
	line-height: 24px;
	letter-spacing: -0.45px;
	margin: 0px;
	font-weight: 700;
	color: rgb(26, 26, 26);
}

dd {
	font-size: 32px;
	line-height: 44px;
	letter-spacing: -0.6px;
	margin: 0px;
	font-weight: 800;
	color: rgb(26, 26, 26);
}

dt {
	font-size: 11px;
	line-height: 16px;
	letter-spacing: normal;
	margin: 0px;
	color: rgb(162, 162, 162);
	font-weight: 600;
}

.PostReviewCardList {
	display: flex;
	flex-wrap: wrap;
	margin: 16px -8px -8px;
	overflow: hidden;
}

.LinkBlock {
	width: 25%;
	margin-left: 0px;
	padding: 8px;
	display: block;
	flex: 0 0 auto;
	pointer-events: auto;
	touch-action: auto;
}

.lastMore {
	position: relative;
}

.lastMore::before {
	content: "";
	top: 0px;
	left: 0px;
	width: 100%;
	height: 100%;
	background-color: rgba(27, 28, 29, 0.8);
	border-radius: 3px;
}

.lastMore::after {
	font-size: 14px;
	line-height: 20px;
	letter-spacing: -0.15px;
	margin: 0px;
	top: 50%;
	left: 50%;
	width: 25px;
	transform: translate(-50%, -50%);
	content: "더보기";
	font-weight: bold;
	color: white;
	text-align: center;
}

.lastMore::before, .lastMore::after {
	display: flex;
	flex-direction: column;
	-webkit-box-pack: center;
	justify-content: center;
	-webkit-box-align: center;
	align-items: center;
	white-space: pre;
	position: absolute;
	z-index: 1;
}

.RatioImages_Container {
	display: block;
	position: relative;
	overflow: hidden;
	font-size: 0px;
	padding-top: 50%;
	background: rgb(238, 238, 238);
}

#line {
	height: 1px;
	border: none;
	box-shadow: rgb(248, 248, 248) 0px -1px 0px inset;
}
}
</style>
<script>
	window.onload = function() {
		// 	alert("tesdt1111");
		/*Q&A 1번째 질문*/

		var shareBtn = document.getElementById("share_btn");
		var hiddenBox = document.getElementById("hidden_box");
		var cancelBtn = document.getElementById("cancel_btn");
		var cancelImg = document.getElementById("cancel_img");
		// 	alert(shareBtn);
		// 	alert(hiddenBox);
		shareBtn.onclick = function() {
			//  		alert("testtest");
			if (hiddenBox.style.visibility == "hidden") {
				hiddenBox.style.visibility = "visible";

			} else {
				hiddenBox.style.visibility = "hidden";
			}

		}
		cancelBtn.onclick = function() {
			hiddenBox.style.visibility = "hidden";

		}

	}
</script>

</head>
<%
JsonParser parser = new JsonParser();
JsonElement element = parser.parse(String.valueOf(session.getAttribute("classdetailproperties")));

//수강 후기가져오기
String recommend_num = element.getAsJsonObject().get("recommend_num").getAsString(); 
String recommend_score = element.getAsJsonObject().get("recommend_score").getAsString(); 

//리뷰리스트 최초2개
JsonArray jarr = new JsonArray(); 
jarr.add(element.getAsJsonObject().getAsJsonArray("reviewlist")); 

//클래스 정보
String classname = element.getAsJsonObject().get("classdetailJson").getAsJsonObject().get("classname").getAsString();
String creatername = element.getAsJsonObject().get("classdetailJson").getAsJsonObject().get("creatername").getAsString();
String introduce = element.getAsJsonObject().get("classdetailJson").getAsJsonObject().get("introduce").getAsString();
String period = element.getAsJsonObject().get("classdetailJson").getAsJsonObject().get("period").getAsString();
String content_num = element.getAsJsonObject().get("classdetailJson").getAsJsonObject().get("content_num").getAsString();
String detail = element.getAsJsonObject().get("classdetailJson").getAsJsonObject().get("detail").getAsString();
String chapter = element.getAsJsonObject().get("classdetailJson").getAsJsonObject().get("chapter").getAsString();
String creater_info = element.getAsJsonObject().get("classdetailJson").getAsJsonObject().get("creater_info").getAsString();
String address = element.getAsJsonObject().get("classdetailJson").getAsJsonObject().get("address").getAsString();
%>

<body>
	<header>
		<div>
			<span>takeit!</span> <input type="search"> <a href=""> <input
				type="button" value="login"></a> <a href=""> <input
				type="hidden" value="mypage">
			</a>
		</div>
	</header>
	<main>
		<div>
			<div>
				<img alt="" src="">
			</div>
		</div>
		<div>
			<div id="stickyTab">
				<div id="stickyTab_content">
					<div id="tabs">
						<ul>
							<span class="tabitem_TabBox_1"><a href="#menu1">후기</a></span>
							<span class="tabitem_TabBox"><a href="#menu2">클래스 소개</a></span>
							<span class="tabitem_TabBox"><a href="#menu3">커리큘럼</a></span>
							<span class="tabitem_TabBox"><a href="#menu4">크리에이터</a></span>
							<span class="tabitem_TabBox"><a href="#menu5">FAQ</a></span>
							<span class="tabitem_TabBox"><a href="#menu6">환불 정책</a></span>
						</ul>
					</div>
				</div>
			</div>
			<div class="contentAll">
				<div class="leftSide">
					<div id="content">
						<div>
							<div class="styledDiv">
								<section class="PostViewCintroller">
									<h2>실제 수장생들의 생생한 후기</h2>
									<div class="PostViewCintroller_infoContainer">
										<div class="PostViewCintroller_infoCard">
											<a>
												<dt>클래스 후기</dt>
												<dd>${recommend_num}</dd>
											</a>
										</div>
										<div class="PostViewCintroller_veticalDivider"></div>
										<div class="PostViewCintroller_infoCard">
											<a>
												<dt>수강생만족도</dt>
												<dd>${recommend_score}</dd>
											</a>
										</div>
									</div>
									<div class="PostReviewCardList">
										<h2>
											이런 걸 배울거에요.😊h2> ${introduce } <a class="LinkBlock"> <span>
													<img>
											</span>
											</a> <a class="LinkBlock"> <span> <img>
											</span>
											</a> <a class="LinkBlock"> <span> <img>
											</span>
											</a> <a class="LinkBlock"> <span> <img>
											</span>
											</a> <a class="LinkBlock"> <span> <img>
											</span>
											</a> <a class="LinkBlock"> <span> <img>
											</span>
											</a> <a class="LinkBlock"> <span> <img>
											</span>
											</a> <a class="LinkBlock">
												<div class="lastMore">
													<span class="RatioImages_Container"> <img>
													</span>
												</div>
											</a>
									</div>
									<div class="Divider">
										<hr color="#f8f8f8" id="line">
									</div>

									<div id="meue1">
										후기
										<p></p>
									</div>
									<div id="meue2">
										클래스 소개
										<p></p>
									</div>
									<div id="meue3">
										커리큘럼
										<p></p>
									</div>
									<div id="meue4">
										크리에이터
										<p></p>
									</div>
									<div id="meue5">
										FAQ
										<p></p>
									</div>
									<div id="meue6">
										환불 정책
										<p></p>
									</div>
									<h2>본문 컨텐츠 끝</h2>

								</section>
							</div>
						</div>
					</div>

					<div id="floating">
						<aside id="float">
							<h2>(플로팅배너시작)</h2>
							<span>크리에이터(creater)</span>
							<h2>class name(className)</h2>
							<span>대면 비대면(classType)</span> <span>위치(Address)</span> <span>가격(price)</span>
							<div id="floatOptions"></div>
							<div class="option_recomm">
								<button id="recomm_btn">★(recommend)</button>
								<button id="share_btn">공유하기</button>
							</div>
							<div id="application">
								<button>클래스 신청하기</button>
							</div>
							<h2>(플로팅배너끝)</h2>
						</aside>
					</div>
				</div>
			</div>
		</div>
	</main>
	<!--공유하기  -->
	<div id="hidden_box">
		<div>
			<div id="modalSheet" style="min-height: 0px;">
				<h3>공유하기</h3>
				<button id="cancel_btn">
					<img alt="취소버튼" src="img/close.png" id="cancel_img"
						onerror="this.style.display='none'">
				</button>
				<div>
					<div id="kakao_btn"></div>
					<div id="kakao_btn"></div>
					<button>facebook</button>
				</div>
			</div>
		</div>
	</div>
	<footer> </footer>

</body>

</html>