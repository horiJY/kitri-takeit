const pw = document.getElementById("userPw");
const name = document.getElementById("userName");
const phone = document.getElementById("userPhone");

const logoutBtn = document.getElementById("logoutBtn");
const myInfoSaveBtn = document.getElementById("myInfoUpdateBtn");
const pwUpdateBtn = document.getElementById("pwUpdateBtn");
const userDeleteBtn = document.getElementById("userDeleteBtn");
const qnaBtn = document.getElementById("qnaBtn");

logoutBtn.onclick = function(){
	document.formI.action = "${pageContext.request.contextPath }/logout";
	alert("로그아웃 되었습니다.");
}

myInfoUpdateBtn.onclick = function() {
	document.formI.action = "${pageContext.request.contextPath }/memo-update";
	alert("수정되었습니다.");
	document.form.submit();

}

qnaBtn.onclick = function() {
	location.href = "${pageContext.request.contextPath }/qna-regist";
}





