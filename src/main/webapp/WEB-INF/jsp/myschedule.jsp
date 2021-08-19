<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>my schedule</title>
<link href='${pageContext.request.contextPath}/calendar-assets/main.css' rel='stylesheet' />
<script type="text/javascript">
var fullcalendar = null;
document.addEventListener('DOMContentLoaded', function() {
	var calendar = document.getElementById('calendar');
	
	fullcalendar = new FullCalendar.Calendar(calendar, {
		initialView: 'dayGridMonth',
		events: ${cJson},
		eventClick: function(arg){
			//해당 강의/수강 정보로 이동
			alert ("event");
		}
	});
	fullcalendar.render();
});
</script>

</head>
<body>
	<div id='calendar'></div>
	<script src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/calendar-assets/main.js"></script>
</body>
</html>