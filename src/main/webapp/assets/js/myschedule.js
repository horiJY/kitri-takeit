/**
 * 
 */
var calendar = document.getElementById('calendar');

var fullcalendar = new FullCalendar.Calendar(calendar, {
	initialView: 'dayGridMonth',
	events: ${cJson},
	eventClick: function(arg){
		//해당 강의/수강 정보로 이동
		alert ("event");
	}
});
fullcalendar.render();