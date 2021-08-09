const id = document.getElementById("userId");
const pw = document.getElementById("userPw");
const name = document.getElementById("userName");
const phone = document.getElementById("userPhone");

const logoutBtn = document.getElementById("logoutBtn");
const myInfoSaveBtn = document.getElementById("myInfoUpdateBtn");
const qnaBtn = document.getElementById("qnaBtn");


myInfoUpdateBtn.onclick = function() {
	document.form.action = "${pageContext.request.contextPath }/memo-update";
	alert("수정되었습니다.");
	document.form.submit();

}

qnaBtn.onclick = function() {
	location.href = "${pageContext.request.contextPath }/qna-regist";
}





