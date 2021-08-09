window.onload = function(){
	
	const myInfoNav = document.getElementById("myInfoNav");
	const myPointNav = document.getElementById("myPointNav");
	const myFavoriteNav = document.getElementById("myFavoriteNav");
	const myAssignmentNav = document.getElementById("myAssignmentNav");
	const myReviewNav = document.getElementById("myReviewNav");
	const myQnANav = document.getElementById("myQnANav");

	const myInfoEdit = document.getElementById("myInfoEdit");
	const myPoint = document.getElementById("myPoint");
	const myFavorite = document.getElementById("myFavorite");
	const myAssignment = document.getElementById("myAssignment");
	const myReview = document.getElementById("myReview");
	const myQnA = document.getElementById("myQnA");
	
	myInfoEdit.style.display ="none";
	myPoint.style.display = "none";
	myFavorite.style.display = "none";
	myAssignment.style.display = "none";
	myReview.style.display = "none";
	myQnA.style.display = "none";
	
	myInfoNav.onclick = function() {
		if (myInfoEdit.style.display == "none") {
//			console.log(myInfoEdit);
			myInfoEdit.style.display = "block";
			myPoint.style.display = "none";
			myFavorite.style.display = "none";
			myAssignment.style.display = "none";
			myReview.style.display = "none";
			myQnA.style.display = "none";
		}
	}
	myPointNav.onclick = function() {
		if (myPoint.style.display == "none") {
			myInfoEdit.style.display = "none";
			myPoint.style.display = "block";
			myFavorite.style.display = "none";
			myAssignment.style.display = "none";
			myReview.style.display = "none";
			myQnA.style.display = "none";
		}
	}
	myFavoriteNav.onclick = function() {
		if (myFavorite.style.display == "none") {
			myInfoEdit.style.display = "none";
			myPoint.style.display = "none";
			myFavorite.style.display = "block";
			myAssignment.style.display = "none";
			myReview.style.display = "none";
			myQnA.style.display = "none";
		}
	}
	myAssignmentNav.onclick = function() {
		if (myAssignment.style.display == "none") {
			myInfoEdit.style.display = "none";
			myPoint.style.display = "none";
			myFavorite.style.display = "none";
			myAssignment.style.display = "block";
			myReview.style.display = "none";
			myQnA.style.display = "none";
		}
	}
	myReviewNav.onclick = function() {
		if (myReview.style.display == "none") {
			myInfoEdit.style.display = "none";
			myPoint.style.display = "none";
			myFavorite.style.display = "none";
			myAssignment.style.display = "none";
			myReview.style.display = "block";
			myQnA.style.display = "none";
		}
	}
	myQnANav.onclick = function() {
		if (myQnA.style.display == "none") {
			myInfoEdit.style.display = "none";
			myPoint.style.display = "none";
			myFavorite.style.display = "none";
			myAssignment.style.display = "none";
			myReview.style.display = "none";
			myQnA.style.display = "block";
		}
	}
	
}