/*
* 적용 페이지: WEB-INF/jsp/open-class.jsp
*/

//요소 별 변수 선언
var editBox = document.getElementById("edit-box");
var openBtn = document.getElementById("open-btn");
var cancelBtn = document.getElementById("cancel-btn");

var className = document.getElementById("class-name");
var category = document.getElementById("category");
var classType = "on"; 
var classPeriod = document.getElementById("class-period");

var classSchedule = [];
var scheduleRepeat = document.getElementById("schedule-repeat"); 
var scheduleEnddate = document.getElementById("schedule-enddate");
var calendar = document.getElementById("calendar");
var scheduleAddBtn = document.getElementById("schedule-add-btn");
var scheduleResetBtn = document.getElementById("schedule-reset-btn");

var startDate = document.getElementById("start-date");
var endDate = document.getElementById("end-date");
var startTime = document.getElementById("start-time");
var endTime = document.getElementById("end-time");

var classCapacity = document.getElementById("class-capacity");
var classFee = document.getElementById("class-fee");
var classDetail = document.getElementById("class-detail");

var confirmBox = document.getElementById("confirm-box");
var confirmContent = document.getElementById("confirm-content");
var confirmOpenBtn = document.getElementById("confirm-open-btn");
var confirmCancelBtn = document.getElementById("confirm-cancel-btn");


// 대면/비대면 수업 유형 변경 시 기간 설정 형식 변경 eventlistener
document.querySelectorAll("[name='class-type']").forEach(function(element) {
	element.addEventListener("click", function() {
		// console.log(this.value);
		var classOn = document.getElementById("class-schedule-on"); 
		var classOff = document.getElementById("class-schedule-off"); 
	
		if(this.value=="on"){
			classOn.style.display="block";
			classOff.style.display="none";
		}else if(this.value=="off"){
			classOff.style.display="block";
			classOn.style.display="none";
		}
	});
});

//일정 시작일을 최소 오늘로부터 7일 이후 부터로 제한
var minday = new Date();
minday.setDate(minday.getDate()+7);
startDate.min = minday.toISOString().slice(0,10);

//일정 종료일을 시작일이나 그 이후로 제한
startDate.onchange = function(){
	endDate.disabled = false;
	endDate.min = startDate.value;
};


//일정 종료 시간을 시작시간 이후로 제한
startTime.onchange = function(){
	if((endTime.value!="")&&(endTime.value<=startTime.value)){
		alert("시작 시간은 종료 시간 이전이어야 합니다.");
		startTime.value="";
		return;
	}
}
endTime.onchange = function(){
	if((startTime.value!="")&&(endTime.value<=startTime.value)){
		alert("종료 시간은 시작시간 이후이어야 합니다.");
		endTime.value="";
		return;
	}
};

//스케쥴 반복 방법 선택에 따른 입력폼 전환
document.querySelectorAll("[name='repeat-method']").forEach(function(element) {
	element.addEventListener("click", function() {
		// console.log(this.value); 
	
		if(this.value=="repeat"){
			scheduleRepeat.style.display="block";
			scheduleEnddate.style.display="none";
		}else if(this.value=="enddate"){
			scheduleEnddate.style.display="block";
			scheduleRepeat.style.display="none";
		}
	});
});

//스케쥴 추가
scheduleAddBtn.onclick = function(){
	if(startDate.value==""){
		alert("일정 시작일을 선택하세요.");
		startDate.focus();
		return;	
	}
	if(endDate.value==""){
		alert("일정 종료일을 선택하세요.");
		endDate.focus();
		return;
	}
	if(startTime.value==""){
		alert("일정 시작 시간을 선택하세요.");
		startTime.focus();
		return;
	}
	if(endTime.value==""){
		alert("일정 종료 시간을 선택하세요.");
		endTime.focus();
		return;
	}
	var sdate = startDate.value;
	var edate = endDate.value;
	var stime = startTime.value;
	var etime = endTime.value;
	
	//스케쥴 반복 입력
	for(var schd = new Date(sdate);schd<=new Date(edate);schd.setDate(schd.getDate()+7)){
		console.log(schd);
		var schdStr = schd.toISOString().slice(0,10);
		classSchedule.push([schdStr,stime,etime]);
	}
	console.log(classSchedule);
	classSchedule.forEach(function(schd){
		calendar.innerHTML += schd[0];
		calendar.innerHTML += " ";
		calendar.innerHTML += schd[1];
		calendar.innerHTML += " ";
		calendar.innerHTML += schd[2];
		calendar.innerHTML += "<br>";
	});
};
//스케쥴 리셋: 스케쥴 배열 비우기

//스케쥴 날짜와 시간 클래스
var schedule =class{
	constructor(date, stime, etime){
		this.date = date;
		this.stime = stime;
		this.etime = etime;
	}
};

//달력 표시
/* var calendarEl = document.getElementById('calendar');
var calendar = new FullCalendar.Calendar(calendarEl, {
	initialView: 'dayGridMonth'
});
calendar.render(); */



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
	if(classType=="on"&&classPeriod<=0){
		alert("강의 기간을 다시 입력하세요.");
		classPeriod.focus();
		return;
	}
	
	//대면 강의 선택 시 강의 스케쥴 변환
	if(classType=="off"){
    	// 강의 스케쥴이 없음
    	if(classSchedule.length==0){
    		alert("강의 일정을 입력하세요.");
//    		startDate.focus();
    		return;
    	}
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
	
	confirmBox.style.display = "block";
	editBox.style.display = "none";
	
	confirmContent.innerHTML += "class name: ";
	confirmContent.innerHTML += className.value;
	confirmContent.innerHTML += "<br>";
	confirmContent.innerHTML += "category: ";
	confirmContent.innerHTML += category.value;
	confirmContent.innerHTML += "<br>";
	confirmContent.innerHTML += "class type: ";
	confirmContent.innerHTML += classType;
	confirmContent.innerHTML += "<br>";
	
	if(classType=="on"){
		confirmContent.innerHTML += "class period: ";
		confirmContent.innerHTML += classPeriod.value*7;
	}else{
		confirmContent.innerHTML += "class schedule: ";
		confirmContent.innerHTML += "<br>";
		confirmContent.innerHTML += calendar.innerHTML;
		confirmContent.innerHTML += "<br>";
		confirmContent.innerHTML += "class capacity: ";
		confirmContent.innerHTML += classCapacity.value;
		confirmContent.innerHTML += "<br>";
		
		editBox.style.display = "none";
		confirmBox.style.display = "block";
	}
	
	confirmContent.innerHTML += "<br>";
	confirmContent.innerHTML += "class fee: ";
	confirmContent.innerHTML += classFee.value;
	confirmContent.innerHTML += "<br>";
	confirmContent.innerHTML += "class detail: ";
	confirmContent.innerHTML += classDetail.value;
	
};
	
// 취소 버튼을 누르면 마이페이지로 이동

// 확인창 확인 버튼 클릭 시 폼 전송 후 마이페이지로 이동
confirmOpenBtn.onclick = function(){
	console.log(confirmContent.innerHTML);
}

// 확인창 취소 버튼 클릭 시 편집 페이지로 되돌아감
confirmCancelBtn.onclick = function(){
	editBox.style.display = "block";
	confirmBox.style.display = "none";
	
	confirmContent.innerHTML = "";
}


	
