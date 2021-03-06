/*
* 적용 페이지: WEB-INF/jsp/open-class.jsp
*/

//요소 별 변수 선언
var editBox = document.getElementById("edit-box");
var openBtn = document.getElementById("open-btn");

var className = document.getElementById("class-name");
var category = document.getElementById("category");
var classType = "on"; 
var classPeriod = document.getElementById("class-period");

var scheduleRepeat = document.getElementById("schedule-repeat"); 
var scheduleEnddate = document.getElementById("schedule-enddate");
var scheduleAddBtn = document.getElementById("schedule-add-btn");
var scheduleResetBtn = document.getElementById("schedule-reset-btn");
var calendar = document.getElementById("calendar");
var classSchedule = [];
var repeatMethod = "repeat";

var startDate = document.getElementById("start-date");
var endDate = document.getElementById("end-date");
var startTime = document.getElementById("start-time");
var endTime = document.getElementById("end-time");

var classCapacity = document.getElementById("class-capacity");
var classFee = document.getElementById("class-fee");
var classIntroduce = document.getElementById("class-introduce");
var classDetail = document.getElementById("class-detail");

// 대면/비대면 수업 유형 변경 시 기간 설정 형식 변경 eventlistener
document.querySelectorAll("[name='class-type']").forEach(function(element) {
	element.addEventListener("click", function() {
		var classOn = document.getElementById("class-schedule-on"); 
		var classOff = document.getElementById("class-schedule-off"); 
		
		classType = this.value;
			
		if(classType=="on"){
			classOn.style.display="block";
			classOff.style.display="none";
		}else if(classType=="off"){
			classOff.style.display="block";
			classOn.style.display="none";
			fullcalendar.render();
		}
	});
});

//일정 시작일을 최소 오늘로부터 7일 이후 부터로 제한
var minday = new Date();
minday.setDate(minday.getDate()+7);
startDate.min = minday.toISOString().slice(0,10);

//일정 종료일을 시작일이나 그 이후로 제한
startDate.onchange = function(){
	if(endDate.value!=""&&endDate.value<startDate.value){
		endDate.value = startDate.value;
		return;
	}
	endDate.disabled = false;
	endDate.min = startDate.value;
};


//일정 시작 시간을 종료 시간이전으로 제한
startTime.onchange = function(){
	if((endTime.value!="")&&(endTime.value<=startTime.value)){
		endTime.value="";
		return;
	}
}

//일정 종료 시간을 종료시간 이후로 제한
endTime.onchange = function(){
	if((startTime.value!="")&&(endTime.value<=startTime.value)){
		startTime.value="";
		return;
	}
};

//스케쥴 반복 방법 선택에 따른 입력폼 전환
document.querySelectorAll("[name='repeat-method']").forEach(function(element) {
	element.addEventListener("click", function() {
		// console.log(this.value); 
	
		repeatMethod = this.value;
		if(repeatMethod=="repeat"){
			scheduleRepeat.style.display="block";
			scheduleEnddate.style.display="none";
		}else if(repeatMethod=="enddate"){
			scheduleEnddate.style.display="block";
			scheduleRepeat.style.display="none";
		}
	});
});

//스케쥴 클래스
var schedule = function(stime, etime){
	var sStr = stime.toISOString();
	var eStr = etime.toISOString();
	return {
		'startTime' : sStr.slice(0,10)+" "+sStr.slice(11,16),
		'endTime' : eStr.slice(0,10)+" "+eStr.slice(11,16)
	}
};


//달력 표시
var fullcalendar = new FullCalendar.Calendar(calendar, {
	initialView: 'dayGridMonth',
	eventClick: function(arg){
		arg.event.remove();
	}
});

//스케쥴 추가
scheduleAddBtn.onclick = function(){
	if(startDate.value==""){
		alert("일정 시작일을 선택하세요.");
		startDate.focus();
		return;	
	}
	if(startTime.value==""){
		alert("일정 시작 시간을 선택하세요.");
		startTime.focus();
		return;
	}
	
	var stime = new Date(startDate.value+" "+startTime.value);
	var etime = new Date(startDate.value+" "+endTime.value);;
	classSchedule.push(schedule(stime,etime));
	
	//스케쥴 반복 입력
	if(repeatMethod=="repeat"){
		//반복수 지정 방식
		var repeatNum = document.getElementById("repeat").value;
		for(var i=1;i<repeatNum;i++){
			stime.setDate(stime.getDate()+7);
			etime.setDate(etime.getDate()+7);
			console.log(stime+" "+etime);
			console.log(schedule(stime,etime));
			classSchedule.push(schedule(stime,etime)); 
		}
	}else if(repeatMethod=="enddate"){
		//종료일 지정 방식
		if(endDate.value==""){
			alert("일정 종료일을 선택하세요.");
			endDate.focus();
			return;
		}
		if(endTime.value==""){
			alert("일정 종료 시간을 선택하세요.");
			endTime.focus();
			return;
		}
		
		var edate = new Date(endDate.value+" "+startTime.value);
		
		while(stime <= edate){
			stime.setDate(stime.getDate()+7);
			etime.setDate(etime.getDate()+7);
			classSchedule.push(schedule(stime,etime));
		}
	}
	fullcalendar.removeAllEvents();
	console.log(classSchedule);
	classSchedule.forEach(function(schd){
//		calendar.innerHTML += schd.day;
//		calendar.innerHTML += " ";
//		calendar.innerHTML += schd.stime;
//		calendar.innerHTML += " ";
//		calendar.innerHTML += schd.etime;
//		calendar.innerHTML += "<br>";
		fullcalendar.addEvent({	
			title : startTime.value+"~"+endTime.value,
			start : schd.startTime.slice(0,10)
		});
	});
};

//스케쥴 리셋: 스케쥴 배열 비우기
scheduleResetBtn.onclick = function(){
	if(confirm("일정이 모두 초기화됩니다.")){
		classSchedule = [];
		//calendar.innerHTML = "";
		fullcalendar.removeAllEvents();
	}
};


// 저장 버튼을 누르면 개설 정보 확인 창 출력
openBtn.onclick = function(){
	//강의 이름 미작성
	if(className.value==""){
		alert("강좌 이름을 입력하세요.");
		className.focus();
		return;
	}
	//카테고리 미선택
	if(category.value==""){
		alert("카테고리를 선택하세요.");
		category.focus();
		return;
	}
	//비대면 강의 선택 시 강의 기간 음수
	if((classType=="on")&&(classPeriod<=0)){
		alert("강의 기간을 다시 입력하세요.");
		classPeriod.focus();
		return;
	}
	
	//대면 강의 선택 시 강의 스케쥴 없음
	if((classType=="off")&&(classSchedule.length==0)){
		alert("강의 일정을 입력하세요.");
//		startDate.focus();
		return;
	}
	
	//강의료 음수
	if(classFee.value<0){
		alert("강의료를 다시 입력하세요.");
		classFee.focus();
		return;
	}
	//강의료 음수
	if(classFee.value<0){
		alert("강의료를 다시 입력하세요.");
		classFee.focus();
		return;
	}
	//강의 소개 미작성
	if(classDetail.value==""){
		alert("강의 소개를 입력하세요.");
		classDetail.focus();
		return;
	}
	
	if(confirm("강의를 개설하시겠습니까?")){
		send();
	}
}

// 확인창 확인 버튼 클릭 시 폼 전송 후 마이페이지로 이동
var send = function(){
	var xhr = new XMLHttpRequest();
	xhr.open("POST","",true);
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	
	var classStr = {
		className : className.value,
		classType : classType,
		period : classPeriod.value*7,
		introduce : classIntroduce.value,
		detail : classDetail.value,
		price : classFee.value,
		capacity : classCapacity.value,
		category : category.value,
	};
	var scheduleStr = {
		classSchedule
	}
	var classJson = JSON.stringify(classStr);
	var scheduleJson = JSON.stringify(scheduleStr);
	//console.log(classJson);
	//console.log(scheduleJson);
	xhr.send("class="+classJson+"&schedule="+scheduleJson);
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == XMLHttpRequest.DONE){
			if(xhr.status == 200){
				var code = xhr.responseText;
				if(code=="SUCCESS"){
					alert("강의 개설을 완료했습니다.");
					//강의 정보창으로 이동
					//window.location.href="";
				}else if(code==="OVERLAP"){
					//중복 일정 존재
					alert("중복 일정이 존재합니다.");
				}else if(code==="FAIL"){
					//강의 개설 실패
					alert("강의 개설을 완료하지 못 했습니다. 다시 시도해 주세요.\n"
						+ "문제가 지속되면 고객센터로 문의바랍니다.");
				}	
			}else{
				//강의 개설 실패
				alert("서버에 문제가 발생했습니다.\n"
					+ "문제가 지속되면 고객센터로 문의바랍니다.");
			}
		}
	}
}


	
