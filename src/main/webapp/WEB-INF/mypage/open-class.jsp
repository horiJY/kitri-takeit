<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	//페이지 로드 시 초기값
	document.addEventListener('DOMContentLoaded', function() {
		
		
		document.querySelectorAll("[name=classType]").forEach(function(elem) {
			elem.addEventListener("click", function() {
				// console.log(this.value);
		    	toggleType(this.value);
			});
		  	if (elem.checked) {
			    elem.click();
 			}
		});
	});
	
	//대면/비대면 수업 유형 변경에 따라 기간 설정 폼 변경 함수
	var toggleType=function(type){
		if(type=="on"){
			document.getElementById("classScheduleOn").style.display="block";
			document.getElementById("classScheduleOff").style.display="none";
		}else if(type=="off"){
			document.getElementById("classScheduleOff").style.display="block";
			document.getElementById("classScheduleOn").style.display="none";
		}
	}
	
	//저장 버튼을 누르면 폼 전송 후 마이페이지로 이동
	
	//취소 버튼을 누르면 마이페이지로 이동
	
</script>
</head>
<body>
	<div>
		<h1>강의 개설 폼</h1>
		<div>
			강의 이름
			<input type="text" id="className" name="className">
		</div>
		<div>
			카테고리
			<select id="category" name="category">
				<option value="language">언어</option>
				<option value="sport">운동</option>
				<option value="cooking">요리</option>
				<option value="programming">개발</option>
				<option value="art">예술</option>
			</select>
		</div>
		<div>
			강의 형태
			<input type="radio" name="classType" value="on" checked>
			<label for="classTypeOn">비대면</label>
			<input type="radio" name="classType" value="off" >
			<label for="classTypeOff">대면</label>
		</div>
		<div>
			강의 기간
			<div id="classScheduleOn">
				<!-- 비대면 -->
				<input type="number" name="period" id="period" value="30">주
			</div>
			<div id="classScheduleOff" style="display:none">
				<!-- 대면: -->
				<div>
					시작일 <!-- 오늘 이전만 선택 가능하도록 제한 -->
					<input type="date" id="startDate" name="startDate" >
				</div>
				<div>
					종료일 <!-- 시작일 이후만 선택 가능하도록 제한 -->
					<input type="date" id="endDate" name="endDate" >
				</div>
				<div>
					수업 요일:
					<input type="checkbox" value="monday" name="classDay">월
					<input type="checkbox" value="tuesday" name="classDay">화
					<input type="checkbox" value="wendsday" name="classDay">수
					<input type="checkbox" value="thursday" name="classDay">목
					<input type="checkbox" value="friday" name="classDay">금
					<input type="checkbox" value="saturday" name="classDay">토
					<input type="checkbox" value="sunday" name="classDay">일
				</div>
				<div>
					세부조정
					<!--  달력 -->
				</div>
			</div>
		</div>
		<div>
			강의 소개
			<div>
				<textarea name="detail" rows="15" cols="50"></textarea>
			</div>
		</div>
		<div>
			<button id="create">개설</button>
			<button id="cancel">취소</button>
		</div>
	</div>
</body>
</html>