<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/modal.css" />
<script type="text/javascript">

	// 페이지 로드 시 실행
	document.addEventListener('DOMContentLoaded', function() {
		
		// 대면/비대면 수업 유형 변경 시 기간 설정 형식 변경 eventlistener
		document.querySelectorAll("[name=class-type]").forEach(function(elem) {
			elem.addEventListener("click", function() {
				// console.log(this.value);
		    	toggleType(this.value);
			});
		});
		
		//모달 창 초기화
		var modalWrap = document.getElementById("modal-wrapper");
		var cancleArea = document.getElementById("modal-cancel-area");
			cancleArea.onclick = function() {
			modalWrap.style.display = "none";
		}
		var cancleBtn = document.getElementById("modal-cancel-btn");
			cancleBtn.onclick = function() {
			modalWrap.style.display = "none";
		}
		
		//요소 별 변수 선언
		var className = document.getElementById("class-name");
		var category = document.getElementById("category");
		var classType = document.getElementById("class-type");
		var classPeriod = document.getElementById("class-period");
		var classSchedule;//함수를 통해 배열 반환
		var classCapacity = document.getElementById("class-capacity");
		var classFee = document.getElementById("class-fee");
		var classDetail = document.getElementById("class-detail");
		
		var modalContent = document.getElementById("modal-content");
		var openBtn = document.getElementById("open-btn");
		var cancelBtn = document.getElementById("cancel-btn");
		
		// 저장 버튼을 누르면 확인 모달창 출력
		openBtn.onclick = function(){
			modalWrap.style.display = "block";
			modalContent.innerHTML += "class name: ";
			modalContent.innerHTML += className.value;
			modalContent.innerHTML += "<br>";
			modalContent.innerHTML += "category: ";
			modalContent.innerHTML += category.value;
			modalContent.innerHTML += "<br>";
			modalContent.innerHTML += "class type: ";
			modalContent.innerHTML += classType.value;
			modalContent.innerHTML += "<br>";
			modalContent.innerHTML += "class period: ";
			modalContent.innerHTML += classPeriod.value;
			modalContent.innerHTML += "<br>";
// 			modalContent.innerHTML += "class schedule: ";
// 			modalContent.innerHTML += classSchedule.value;
// 			modalContent.innerHTML += "<br>";
			modalContent.innerHTML += "class capacity: ";
			modalContent.innerHTML += classCapacity.value;
			modalContent.innerHTML += "<br>";
			modalContent.innerHTML += "class fee: ";
			modalContent.innerHTML += classFee.value;
			modalContent.innerHTML += "<br>";
			modalContent.innerHTML += "class detail: ";
			modalContent.innerHTML += classDetail.value;
			
			// 확인하면 폼 전송 후 마이페이지로 이동
			// 취소하면 편집 페이지로 되돌아감
		}
		
		
		// 취소 버튼을 누르면 마이페이지로 이동
	});
	
	// 기간 설정 형식 변경 함수
	var toggleType=function(type){
		if(type=="on"){
			document.getElementById("classScheduleOn").style.display="block";
			document.getElementById("classScheduleOff").style.display="none";
		}else if(type=="off"){
			document.getElementById("classScheduleOff").style.display="block";
			document.getElementById("classScheduleOn").style.display="none";
		}
	}
	
	//스케쥴 배열 생성 함수
	
	
</script>
</head>
<body>
	<div>
		<h1>강의 개설 폼</h1>
		<div>
			강의 이름
			<input type="text" id="class-name" required>
		</div>
		<div>
			카테고리
			<select id="category">
				<option value="" selected disabled>(선택하세요)</option>
				<option value="language">언어</option>
				<option value="sport">운동</option>
				<option value="cooking">요리</option>
				<option value="programming">개발</option>
				<option value="art">예술</option>
			</select>
		</div>
		<div>
			강의 형태
			<input type="radio" name="class-type" value="on" checked>
			<label for="class-type-on">비대면</label>
			<input type="radio" name="class-type" value="off" >
			<label for="class-type-off">대면</label>
		</div>
		<div>
			강의 기간
			<div id="class-schedule-on">
				<!-- 비대면 -->
				<input type="number" id="period" value="30">주
			</div>
			<div id="class-schedule-off" style="display:none">
				<!-- 대면: -->
				<div>
					시작일 
					<!-- 오늘 이전만 선택 가능하도록 제한 -->
					<input type="date" id="startDate" >
				</div>
				<div>
					종료일 
					<!-- 시작일 이후만 선택 가능하도록 제한 -->
					<input type="date" id="endDate" >
				</div>
				<div>
					수업 요일:
					<input type="checkbox" value="monday" name="class-day">월
					<input type="checkbox" value="tuesday" name="class-day">화
					<input type="checkbox" value="wendsday" name="class-day">수
					<input type="checkbox" value="thursday" name="class-day">목
					<input type="checkbox" value="friday" name="class-day">금
					<input type="checkbox" value="saturday" name="class-day">토
					<input type="checkbox" value="sunday" name="class-day">일
				</div>
				<div>
					세부조정
					<div>
						<!--  달력 -->
					</div>
				</div>
			</div>
		</div>
		<div>
			정원
			<div>
				<input type="number" id="class-capacity" value="1" min="1">명
			</div>
		<div>
		<div>
			강의료
			<div>
				<input type="number" id="class-fee" value="0" step="10000" min="0">원
			</div>
		<div>
			강의 소개
			<div>
				<textarea name="class-detail" rows="15" cols="50"></textarea>
			</div>
		</div>
		<div>
			<button id="open-btn">개설</button>
			<button id="cancel-btn">취소</button>
		</div>
	</div>
	<div id="modal-wrapper">
		<div id="modal-box">
			<div id="modal-content"></div>
			<button id="modal-submit-btn" class="modal-btn">확인</button>
			<button id="modal-cancel-btn" class="modal-btn">취소</button>
		</div>
		<div id="modal-cancel-area"></div>
	</div>
</body>
</html>