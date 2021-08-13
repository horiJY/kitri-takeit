const pw = document.getElementById("userPw");
const name = document.getElementById("userName");
const phone = document.getElementById("userPhone");

const logoutBtn = document.getElementById("logoutBtn");
const myInfoSaveBtn = document.getElementById("myInfoUpdateBtn");
const userDeleteBtn = document.getElementById("userDeleteBtn");
const showpreClassBtn = document.getElementById("showpreClassBtn");
const showClassBtn = document.getElementById("showClassBtn");
const qnaBtn = document.getElementById("qnaBtn");

logoutBtn.onclick = function(){
	document.formI.action = "${pageContext.request.contextPath }/logout";
	alert("로그아웃 되었습니다.");
}

myInfoUpdateBtn.onclick = function() {
	document.formI.action = "${pageContext.request.contextPath }/user-update";
	alert("수정되었습니다.");
	document.form.submit();

}

userDeleteBtn.onclick = function() {
	document.formI.action = "${pageContext.request.contextPath }/user-delete";
}

qnaBtn.onclick = function() {
	location.href = "${pageContext.request.contextPath }/qna-regist";
}





