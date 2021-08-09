window.onload = function(){
	const myInfoNav = document.getElementById("myInfoNav");
	const myPointNav = document.getElementById("myPointNav");
	const myFavoriteNav = document.getElementById("myFavoriteNav");
	const myAssignmentNav = document.getElementById("myAssignmentNav");
	const myReviewNav = document.getElementById("myReviewNav");
	
	const myInfoEdit = document.getElementById("myInfoEdit");
	const myPoint = document.getElementById("myPoint");
	const myFavorite = document.getElementById("myFavorite");
	const myAssignment = document.getElementById("myAssignment");
	const myReview = document.getElementById("myReview");
	
	const logoutBtn = document.getElementById("logoutBtn");
	const saveBtn = document.getElementById("saveBtn");
	
	myInfoEdit.style.display ="none";
	myPoint.style.display = "none";
	myFavorite.style.display = "none";
	myAssignment.style.display = "none";
	myReview.style.display = "none";
	
	myInfoNav.onclick = function() {
		if (myInfoEdit.style.display == "none") {
			console.log(myInfoEdit);
			myInfoEdit.style.display = "block";
			myPoint.style.display = "none";
			myFavorite.style.display = "none";
			myAssignment.style.display = "none";
			myReview.style.display = "none";
		}
	}
	myPointNav.onclick = function() {
		if (myInfoEdit.style.display == "none") {
			console.log(myInfoEdit);
			myInfoEdit.style.display = "none";
			myPoint.style.display = "block";
			myFavorite.style.display = "none";
			myAssignment.style.display = "none";
			myReview.style.display = "none";
		}
	}
	myFavoriteNav.onclick = function() {
		if (myInfoEdit.style.display == "none") {
			console.log(myInfoEdit);
			myInfoEdit.style.display = "none";
			myPoint.style.display = "none";
			myFavorite.style.display = "block";
			myAssignment.style.display = "none";
			myReview.style.display = "none";
		}
	}
	myAssignmentNav.onclick = function() {
		if (myInfoEdit.style.display == "none") {
			console.log(myInfoEdit);
			myInfoEdit.style.display = "none";
			myPoint.style.display = "none";
			myFavorite.style.display = "none";
			myAssignment.style.display = "block";
			myReview.style.display = "none";
		}
	}
	myReviewNav.onclick = function() {
		if (myInfoEdit.style.display == "none") {
			console.log(myInfoEdit);
			myInfoEdit.style.display = "none";
			myPoint.style.display = "none";
			myFavorite.style.display = "none";
			myAssignment.style.display = "none";
			myReview.style.display = "block";
		}
	}

	
	
	
	
}