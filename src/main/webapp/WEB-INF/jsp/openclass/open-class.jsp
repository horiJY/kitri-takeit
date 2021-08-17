<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>강의 개설 페이지</title>
<link href='${pageContext.request.contextPath}/calendar-assets/main.css' rel='stylesheet' />
<script src='${pageContext.request.contextPath}/calendar-assets/main.js'></script>
</head>
<body>
	<div id="edit-box">
		<h1>강의 개설 폼</h1>
		<div>
			강의 이름
			<input type="text" id="class-name">
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
			비대면
			<input type="radio" name="class-type" value="off" >
			대면
		</div>
		<div>
			강의 기간
			<!-- 비대면 -->
			<div id="class-schedule-on">
				<input type="number" id="class-period" value="4">주
			</div>
			<!-- 대면: -->
			<div id="class-schedule-off" style="display:none">
				<!-- 일정 추가 ui -->
				<div id="schedule-edit">
					일정 일괄 입력기
					<div>
						시작일 
						<!-- 오늘 이전만 선택 가능하도록 제한 -->
						<input type="date" id="start-date">
					</div>
					<div>
						반복 기준: 
						<input type="radio" name="repeat-method" value="repeat" checked>
						반복횟수
						<input type="radio" name="repeat-method" value="enddate" >
						종료일
						<div id = "schedule-repeat">
							<!-- 시작일 이후만 선택 가능하도록 제한 -->
							<input type="number" id="repeat" value="1" min="1">
							회 반복
						</div>
						<div id = "schedule-enddate" style="display:none">
							종료일 
							<!-- 시작일 이후만 선택 가능하도록 제한 -->
							<input type="date" id="end-date" disabled>
						</div>
					</div>
					<div>
						수업 시간
						<input type="time" id="start-time">
						~
						<input type="time" id="end-time">
					</div>
					<div>
						<button id="schedule-add-btn">추가</button>
						<button id="schedule-reset-btn">리셋</button>
					</div>
				</div>
				<div>
					일정
					<div>
						<!-- 추가된 일정 표시 달력 (세부조정) -->
						<div id="calendar"></div>
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
				<textarea id="class-introduce" rows="5" cols="50"></textarea>
			</div>
		</div>
		<div>
			강의 상세
			<div>
				<textarea id="class-detail" rows="15" cols="50"></textarea>
			</div>
		</div>
		<div>
			<button id="open-btn">개설</button>
		</div>
	</div>
	<div id="confirm-box" style="display:none">
		<h1>개설 정보 확인</h1>
		<div id="confirm-content"></div>
		<button id="confirm-open-btn">개설</button>
		<button id="confirm-cancel-btn">취소</button>
	</div>
	<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/open-class.js"></script>
</body>
</html>