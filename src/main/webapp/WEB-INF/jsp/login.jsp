<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Take It 로그인</title>
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
    
   	<div>로그인 페이지</div>
   	<button type="button" id="kakao-login-btn"><img src="assets/img/kakao_login_btn.png"></button>
   	<button type="button" id="naver-login-btn"><img src=""></button>
   	<button type="button" id="google-login-btn"><img src=""></button>

</body>

</html>