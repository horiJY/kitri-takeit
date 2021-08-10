<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">

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

		var className = document.getElementById("class-name");
		var category = document.getElementById("category");
		var classType = "on"; 
		
		var classPeriod = document.getElementById("class-period");
// 		var classSchedule = new date[];//함수를 통해 배열 반환
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
		
		
		// 저장 버튼을 누르면 개설 정보 확인 창 출력
		openBtn.onclick = function(){
			
			editBox.style.display = "none";
			confirmBox.style.display = "block";
			
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
				confirmContent.innerHTML += classSchedule.value;
				confirmContent.innerHTML += "<br>";
				confirmContent.innerHTML += "class capacity: ";
				confirmContent.innerHTML += classCapacity.value;
				confirmContent.innerHTML += "<br>";
			}
			confirmContent.innerHTML += "<br>";
			confirmContent.innerHTML += "class fee: ";
			confirmContent.innerHTML += classFee.value;
			confirmContent.innerHTML += "<br>";
			confirmContent.innerHTML += "class detail: ";
			confirmContent.innerHTML += classDetail.value;
			
			// 확인 버튼 클릭 시 폼 전송 후 마이페이지로 이동
			document.getElementById("confirm-open-btn").onclick = function(){
				console.log(confirmContent.innerHTML);
			}
			// 취소 버튼 클릭 시 편집 페이지로 되돌아감
			document.getElementById("confirm-cancel-btn").onclick = function(){
				editBox.style.display = "block";
				confirmBox.style.display = "none";
				
				confirmContent.innerHTML = "";
			}
		}
		
		
		// 취소 버튼을 누르면 마이페이지로 이동
	});
	
	// 기간 설정 형식 변경 함수
	var toggleType = function(type){
		if(type=="on"){
			document.getElementById("class-schedule-on").style.display="block";
			document.getElementById("class-schedule-off").style.display="none";
		}else if(type=="off"){
			document.getElementById("class-schedule-off").style.display="block";
			document.getElementById("class-schedule-on").style.display="none";
		}
	}
	
	//스케쥴 배열 생성 함수
	
	
</script>
</head>
<body>
	<div id="edit-box">
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
				<input type="number" id="class-period" value="4">주
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
				<div>
					정원
					<div>
						<input type="number" id="class-capacity" value="1" min="1">명
					</div>
				</div>
			</div>
		</div>
		<div>
			강의료
			<div>
				<input type="number" id="class-fee" value="0" step="10000" min="0">원
			</div>
		</div>
		<div>
			강의 소개
			<div>
				<textarea id="class-detail" rows="15" cols="50"></textarea>
			</div>
		</div>
		<div>
			<button id="open-btn">개설</button>
			<button id="cancel-btn">취소</button>
		</div>
	</div>
	<div id="confirm-box" style="display:none">
		<h1>개설 정보 확인</h1>
		<div id="confirm-content"></div>
		<button id="confirm-open-btn">개설</button>
		<button id="confirm-cancel-btn">취소</button>
	</div>
</body>
</html>