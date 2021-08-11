window.onload = function(){
	
	const myInfoNav = document.getElementById("myInfoNav");
	const myFavoriteNav = document.getElementById("myFavoriteNav");
	const myAssignmentNav = document.getElementById("myAssignmentNav");
	const myReviewNav = document.getElementById("myReviewNav");
	const myQnANav = document.getElementById("myQnANav");
	const myClassNav = document.getElementById("myClassNav");

	const myInfoEdit = document.getElementById("myInfoEdit");
	const myFavorite = document.getElementById("myFavorite");
	const myAssignment = document.getElementById("myAssignment");
	const myReview = document.getElementById("myReview");
	const myQnA = document.getElementById("myQnA");
	const myClass = document.getElementById("myClass");
	
//	myInfoEdit.style.display ="none";
//	myFavorite.style.display = "none";
//	myAssignment.style.display = "none";
//	myReview.style.display = "none";
//	myQnA.style.display = "none";
//	myClass.style.display = "none";
	
	myInfoNav.onclick = function() {
			console.log(myInfoEdit.style);
		if (myInfoEdit.style.display==""||myInfoEdit.style.display == "none") {
			console.log(myInfoEdit);
			myInfoEdit.style.display = "block";
			myFavorite.style.display = "none";
			myAssignment.style.display = "none";
			myReview.style.display = "none";
			myQnA.style.display = "none";
			myClass.style.display = "none";
			
		}
	}
	myFavoriteNav.onclick = function() {
		if (myFavorite.style.display==""||myFavorite.style.display == "none") {
			myInfoEdit.style.display = "none";
			myFavorite.style.display = "block";
			myAssignment.style.display = "none";
			myReview.style.display = "none";
			myQnA.style.display = "none";
			myClass.style.display = "none";
			
			var xhr = new XMLHttpRequest();
			
			xhr.open("POST","myfavorite", true);
			
			xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
			xhr.send();
			xhr.onreadystatechange = function(){
				if(xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200){
					var jsonStr = xhr.responseText;//json
					var json = JSON.parse(jsonStr);
					console.log(json+"log");
					var MyFavoriteContent = document.getElementById("MyFavoriteContent");
					
					var html ="";
					if(json != null){
					
						for(var i=0; i < json.length; i++){
							html += "<div> "+json[i].className+"</div> "
							        +"<div> "+json[i].creater+"</div>"
							        +"<div> "+json[i].favorite+"</div>"
							        +"<div> "+json[i].openDate+"</div>";
							
						}
					} else {
						html += "<img alt='응원한 내역이 없습니다.' src='><br>"
 						+"<input type='button' value='오픈 예정 클래스 둘러보기' id='showpreClassBtn'>";
					}
					
					MyFavoriteContent.innerHTML = html;
					
				}
			}
			
			
		}
	}
	myAssignmentNav.onclick = function() {
		if (myAssignment.style.display == ""||myAssignment.style.display=="none") {
			myInfoEdit.style.display = "none";
			myFavorite.style.display = "none";
			myAssignment.style.display = "block";
			myReview.style.display = "none";
			myQnA.style.display = "none";
			myClass.style.display = "none";
		}
	}
	myReviewNav.onclick = function() {
		if (myReview.style.display == ""||myReview.style.display=="none") {
			myInfoEdit.style.display = "none";
			myFavorite.style.display = "none";
			myAssignment.style.display = "none";
			myReview.style.display = "block";
			myQnA.style.display = "none";
			myClass.style.display = "none";
		}
	}
	myQnANav.onclick = function() {
		if (myQnA.style.display == ""||myQnA.style.display=="none") {
			myInfoEdit.style.display = "none";
			myFavorite.style.display = "none";
			myAssignment.style.display = "none";
			myReview.style.display = "none";
			myQnA.style.display = "block";
			myClass.style.display = "none";
		}
	}
	
	myClassNav.onclick = function() {
		if (myClass.style.display == ""&&myClass.style.display=="none") {
			myInfoEdit.style.display = "none";
			myFavorite.style.display = "none";
			myAssignment.style.display = "none";
			myReview.style.display = "none";
			myQnA.style.display = "none";
			myClass.style.display = "block";
		}
	}
	
}