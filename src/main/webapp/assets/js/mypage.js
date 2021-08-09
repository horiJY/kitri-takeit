window.onload = function(){
	const mypoint = document.getElementById("mypoint");
	const favorite = document.getElementById("favorite");
	const assignment = document.getElementById("assignment");
	const review = document.getElementById("review");
	
	mypoint.onclick = function(){
		xhr.open("POST","${pageContext.request.contextPath}/mypoint",true);
		xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		xhr.send("");
		
		xhr.onreadystatechange = function(){
			if(xhr.readyState == XMLHttpRequest.DONE && xhr.status ==200){
					
					var code = xhr.responseText;
					
					
				}
		}
		
	}
	
}