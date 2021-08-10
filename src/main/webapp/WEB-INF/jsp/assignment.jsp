<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--  
	1. 개인정보(이름, 전화번호, 포인트) 불러오기
	2. 포인트 차감
-->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<body>
	<header>
		<div>
			<span>takeit!</span> 
			<input type="search">
		</div>
		<div>결제하기</div>
	</header>
	<main>
		<section>
			<div>
				<h4>주문 정보</h4>
			</div>
			<div>className</div>
			<div>썸네일</div>
		</section>
		<div><hr></div>
		<form action="#">
			<section>
				<div><h4>연락처 정보</h4></div>
				<div>
					<div>주문자 정보</div>
					<div>
						<input type="text" placeholder="이름을 입력해 주세요." value="">
						<h6>
							<img>
							<span>이름을 입력해 주세요.</span>
						</h6>
					</div>
				</div>
				<div>
					<div>휴대폰 번호</div>
					<div>
						<input type="text" placeholder="휴대폰 번호를 입력해 주세요." value="">
						<h6>
							<img>
							<span>올바른 휴대폰 번호를 입력해 주세요.</span>
						</h6>
					</div>
				</div>
			</section>
			<section>
				<div><h4>결제 금액</h4></div>
				<table>
					<tbody>
						<tr>
							<th>총 상품 금액</th>
							<td>price</td>
						</tr>
						<tr>
							<th>상품 할인 금액</th>
							<td>price*(sale*0.01)</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<th>최종 결제 금액</th>
							<td>price*(1-(sale*0.01))</td>
						</tr>
					</tfoot>
				</table>
			</section>
			<section>
				<div><h4>잔여 포인트</h4></div>
				<div>point</div>
			</section>
			<div>
				<input type="checkbox">
				<span>개인정보 제 3자 제공에 동의합니다. </span>
				<button>
					<span>상세보기</span>
				</button>
			</div>
			<button>
				<span>다음</span>
			</button>
		</form>
	</main>
	<footer>
	
	</footer>
</body>
</html>