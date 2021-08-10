/*
* 
*/

var minday = new Date();
	minday.setDate(minday.getDate()+7);

	// 페이지 로드 시 실행
	document.addEventListener('DOMContentLoaded', function() {
		
		//요소 별 변수 선언
		var editBox = document.getElementById("edit-box");
		var openBtn = document.getElementById("open-btn");
		var cancelBtn = document.getElementById("cancel-btn");
		
		var confirmBox = document.getElementById("confirm-box");
		var confirmContent = document.getElementById("confirm-content");
		var confirmOpenBtn = document.getElementById("confirm-open-btn");
		var confirmCancelBtn = document.getElementById("confirm-cancel-btn");
		
		// 확인 버튼 클릭 시 폼 전송 후 마이페이지로 이동
		confirmOpenBtn.onclick = function(){
			console.log(confirmContent.innerHTML);
		}
		
		// 취소 버튼 클릭 시 편집 페이지로 되돌아감
		confirmCancelBtn.onclick = function(){
			editBox.style.display = "block";
			confirmBox.style.display = "none";
			
			confirmContent.innerHTML = "";
		}

		var className = document.getElementById("class-name");
		var category = document.getElementById("category");
		var classType = "on"; 
		var classPeriod = document.getElementById("class-period");

		var classSchedule = [];
		var schedulAddBtn = document.getElementById("schedule-add-btn");
		//스케쥴 추가: 시작일~종료일까지 해당 요일 추가
		var schedulResetBtn = document.getElementById("schedule-reset-btn");
		//스케쥴 리셋: 스케쥴 배열 비우기
		
		var startDate = document.getElementById("startDate");
		var endDate = document.getElementById("endDate");
		startDate.min = minday.toISOString().slice(0,10);
		startDate.onchange = function(){
			if(startDate.value==null){
				endDate.disabled = true;
				return;
			} 
			endDate.disabled = false;
			endDate.min = startDate.value; 
		}
		
		var classCapacity = document.getElementById("class-capacity");
		var classFee = document.getElementById("class-fee");
		var classDetail = document.getElementById("class-detail");

		// 대면/비대면 수업 유형 변경 시 기간 설정 형식 변경 eventlistener
		document.querySelectorAll("[name=class-type]").forEach(function(element) {
			element.addEventListener("click", function() {
				// console.log(this.value);
				classType = this.value;
		    	toggleType(this.value);
			});
		});
		
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
	        		startDate.focus();
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
				for(var schedule in classSchedule){
					confirmContent.innerHTML += schedule;
					confirmContent.innerHTML += "<br>";
				}
				
 				//confirmContent.innerHTML += "<div id='calendar'></div>";
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
	});
	
	// 기간 설정 형식 변경 함수
	var toggleType = function(type){
		var classOn = document.getElementById("class-schedule-on"); 
		var classOff = document.getElementById("class-schedule-off"); 
		
		if(type=="on"){
			classOn.style.display="block";
			classOff.style.display="none";
		}else if(type=="off"){
			classOff.style.display="block";
			classOn.style.display="none";
		}
	}
	
	//스케쥴 배열 생성 함수
	
