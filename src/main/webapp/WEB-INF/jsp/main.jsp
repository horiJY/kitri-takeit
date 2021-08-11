<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인페이지</title>
</head>
<body>
<h1>메인페이지</h1>
<% 
String id =  (String)session.getAttribute("takeit-userid");
String name =  (String)session.getAttribute("takeit-username");
String userthumnail = (String)session.getAttribute("takeit-userthumnail");
%>
<div>
아이디: <%=id %>
<br>
닉네임: <%=name %>
<br>
<img alt="" src=<%=userthumnail %>><br>
유저 썸네일경로:<%=userthumnail %> 
</div>


</body>
</html>