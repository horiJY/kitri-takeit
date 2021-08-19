<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>my schedule</title>
<link href='${pageContext.request.contextPath}/calendar-assets/main.css' rel='stylesheet' />
</head>
<body>
	<div id='calendar'></div>
	
	<script src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/calendar-assets/main.js"></script>
	<script type="text/javascript">
		var calendar = document.getElementById('calendar');
		var fullcalendar = new FullCalendar.Calendar(calendar, {
			initialView: 'dayGridMonth',
// 			events: ${cJson},
			eventClick: function(arg){
				alert(arg.groupid+"로 이동");
				//해당 강의/수강 정보로 이동
			}
		});
		fullcalendar.render();
	</script>
</body>
</html>