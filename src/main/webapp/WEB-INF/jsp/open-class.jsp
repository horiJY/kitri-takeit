<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>강의 개설 페이지</title>

<link href='${pageContext.request.contextPath}/calendar-assets/main.css' rel='stylesheet' />
<link href='${pageContext.request.contextPath}/assets/css/open-class.css' rel='stylesheet' />
<script type="text/javascript" src="${pageContext.request.contextPath}/calendar-assets/main.js"></script>
<script type="text/javascript">
   function clickMPOC(){   //click MyPageOpenClass
      location.href= "${pageContext.request.contextPath}/mypage/openclass";
   }
   function gotomain(){
      location.href= "${pageContext.request.contextPath}/main";
   }
   function gotopreclass(){
      location.href= "${pageContext.request.contextPath}/pre-class";
   }
   function logoutBtn() {
      location.href= "${pageContext.request.contextPath}/logout";
   }
</script>
</head>
<body>
	<header>
		<form action="header">
			<div>
				<img alt="tatkit-logo" onclick="gotomain()" src="${pageContext.request.contextPath}/assets/img/takeit-logo.jpg" height="50px">
				<input type="button" onclick="logoutBtn()" value="LOGOUT" id="logoutBtn">
			</div>
		</form>
	</header>
	<div id="edit-box">
		<div class="header"><h3>강의 개설</h3></div>
		<div class="inbox">
			<div class="box">
				<div class="labelbox">강의 이름</div>
				<input type="text" id="class-name">
			</div>
			<div class="box">
				<div class="labelbox">카테고리</div>
				<select id="category">
					<option value="" selected disabled>(선택하세요)</option>
					<option value="language">언어</option>
					<option value="sport">운동</option>
					<option value="cooking">요리</option>
					<option value="programming">개발</option>
					<option value="art">예술</option>
				</select>
			</div>
			<div class="box">
				<div class="labelbox">강의 형태</div>
				<input type="radio" name="class-type" value="on" checked>
				비대면
				<input type="radio" name="class-type" value="off" >
				대면
			</div>
			<div class="box">
				<div class="labelbox">강의 기간</div>
				<!-- 비대면 -->
				<div id="class-schedule-on" class-"box">
					<input type="number" id="class-period" value="4">주
				</div>
				<!-- 대면: -->
				<div id="class-schedule-off">
					<!-- 일정 추가 ui -->
					<div id="schedule-edit" class="box">
						<!-- 일정 일괄 입력기 -->
						<div>
							<div class="labelbox">시작일</div> 
							<!-- 오늘 이전만 선택 가능하도록 제한 -->
							<input type="date" id="start-date">
						</div>
						<div>
							<div class="labelbox">반복 기준: </div>
							<input type="radio" name="repeat-method" value="repeat" checked>
							반복횟수
							<input type="radio" name="repeat-method" value="enddate" >
							종료일
							<div id = "schedule-repeat">
								<!-- 시작일 이후만 선택 가능하도록 제한 -->
								<input type="number" id="repeat" value="1" min="1">
								회 반복
							</div>
							<div id = "schedule-enddate">
								종료일 
								<!-- 시작일 이후만 선택 가능하도록 제한 -->
								<input type="date" id="end-date" disabled>
							</div>
						</div>
						<div>
							수업 시간
							<input type="time" id="start-time"> ~ <input type="time" id="end-time">
							<button id="schedule-add-btn">추가</button>
							<button id="schedule-reset-btn">리셋</button>
						</div>
					</div>
					<div class="box">
						<!-- <div class="labelbox">일정</div> -->
						<div>
							<!-- 추가된 일정 표시 달력 (세부조정) -->
							<div id="calendar"></div>
						</div>
					</div>
					<div>
						<div class="labelbox">정원</div>
						<input type="number" id="class-capacity" value="1" min="1">명
					</div>
				</div>
			</div>
			<div class="box">
				<div class="labelbox">강의료</div>
				<div>
					<input type="number" id="class-fee" value="0" step="10000" min="0">원
				</div>
			</div>
			<div class="box">
				<div class="labelbox">강의 소개</div>
				<div>
					<textarea id="class-introduce" rows="3" cols="107"></textarea>
				</div>
			</div>
			<div class="box">
				<div class="labelbox">강의 상세</div>
				<div>
					<textarea id="class-detail" rows="15" cols="107"></textarea>
				</div>
			</div>
			<div class="open-btn">
				<button id="open-btn">개설</button>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
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
				classSchedule.splice(arg.event.id,1);
				fullcalendar.removeAllEvents();
				//console.log(classSchedule);
				for(var idx=0;idx<classSchedule.length;idx++){
					var schd = classSchedule[idx];
					fullcalendar.addEvent({
						id : idx,
						title : idx+"회차",
						start : schd.startTime,
						end : schd.endTime
					});
				};				
			},
			height: 380
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
			//console.log(classSchedule);
			for(var idx=0;idx<classSchedule.length;idx++){
				var schd = classSchedule[idx];
				fullcalendar.addEvent({
					id : idx,
					title : idx+"회차",
					start : schd.startTime,
					end : schd.endTime
				});
			};
		};
	
		//스케쥴 리셋: 스케쥴 배열 비우기
		scheduleResetBtn.onclick = function(){
			if(confirm("일정이 모두 초기화됩니다.")){
				classSchedule = [];
				//calendar.innerHTML = "";
				fullcalendar.removeAllEvents();
			}
		};
	
	
		// 저장 버튼을 누르면 개설 조건체크 후 확인 창 출력
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
	//			startDate.focus();
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
	</script>
	
</body>
</html>