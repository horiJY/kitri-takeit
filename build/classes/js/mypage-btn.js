const name = document.getElementById("userName");
const phone = document.getElementById("userPhone");

const logoutBtn = document.getElementById("logoutBtn");
const myInfoUpdateBtn = document.getElementById("myInfoUpdateBtn");
const userDeleteBtn = document.getElementById("userDeleteBtn");
const showpreClassBtn = document.getElementById("showpreClassBtn");
const showClassBtn = document.getElementById("showClassBtn");
const qnaBtn = document.getElementById("qnaBtn");

var deleteFBtn = document.getElementById("deleteFBtn");

//logoutBtn.onclick = function() {
//	document.formI.action = "${pageContext.request.contextPath }/logout";
//	alert("로그아웃 되었습니다.");
//}
//
//myInfoUpdateBtn.onclick = function() {
//
//	var xhr = new XMLHttpRequest();
//	xhr.open("POST", "user-update", true);
//	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//	xhr.send("userName='" + name + "'&phone='" + phone+"'");
//	xhr.onreadystatechange = function() {
//		if (xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200) {
//			var jsonStr = xhr.responseText;//json
//			var json = JSON.parse(jsonStr);
//			alert("json="+json.value);
//			
//				alert(json.msg);
//		}
//		
//		document.form.submit();
//	}
//}
//
//userDeleteBtn.onclick = function() {
//	document.formI.action = "${pageContext.request.contextPath }/user-delete";
//}
//
//qnaBtn.onclick = function() {
//	location.href = "${pageContext.request.contextPath }/qna-regist";
//}

$("#myInfoUpdateBtn").click(function(){
	
	var params = {
		 userName	:$("#userName").val()
		,phone		:$("#phone").val()
	}
	alert(params)
	
	$.ajax({
		type:"POST",
		url:"userupdate",
		data: params,
		success: function(res){
			alert(res.code);
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			alert("통신 실패")
		}
	})
});

function deleteFBtn(i) {
	$.ajax({
		type: 'POST',
		url: 'myfavoritedelete',
		data: { classId: i },
		success: function() {
			console.log(i);
			alert("응원 내역 삭제 성공");
		},
		
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			alert("응원 내역 삭제 실패");
		}
   })
}



