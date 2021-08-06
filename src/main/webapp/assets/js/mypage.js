window.onload = function(){
	const mypoint = document.getElementById("mypoint");
	const favorite = document.getElementById("favorite");
	const assignment = document.getElementById("assignment");
	const review = document.getElementById("review");
	
	mypoint.onclick = function(){
		xhr.open("POST","${pageContext.request.contextPath}/pages",true);
		xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		xhr.send("pages=mypoint");
	}
}