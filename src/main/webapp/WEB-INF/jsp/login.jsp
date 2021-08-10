<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Take It 로그인</title>
    <style>
	.login-btn{
		height: 48px;
		width:375px;
		border : 0;
		border-radius: 12px;
	}
	#kakao-login-btn{
		background-color: #fee500;
	}
	#naver-login{
		background-color: #03C75A;
	}
	#google-login{
		background-color: #fff;
	}
</style>
</head>
<script >
window.onload = function(){
	document.getElementById("kakao-login-btn").onclick = function(){
		location.href='https://kauth.kakao.com/oauth/authorize?client_id=ba37492513672ce5ce23c00ff86bd01d&redirect_uri=http://localhost:8080/takeit_prj/login/kakao&response_type=code';
	}
}
</script>

<body>
	<div>
		<!-- 로고 들어갈 자리 -->
	</div>
	<h1>로그인 페이지</h1>
	<div>
	<button type="button"class="login-btn" id="kakao-login-btn" alt="카카오 로그인">
		<span ><img	src="assets/img/kakao_login_btn.png" ></span>
	</button>
	</div>
	<div>
	<button class="login-btn" id="naver-login"type="button" alt="네이버 로그인">
		<span ><img	src="assets/img/naver_login_btn.png" style="height:46px"></span>
	</button>
	</div>
	<div>
	<button class="login-btn" id="google-login"type="button" alt="구글 로그인">
		<span ><img	src="assets/img/google_login_btn.png" style="height:48px"></span>
	</button>
	</div>




</body>

</html>
