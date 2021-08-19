window.onload=function(){
	alert("tesdt1111");
	/*Q&A 1번째 질문*/
	var what = document.getElementById("what");
	what.onclick= function(){
	
		var content = what.nextElementSibling;

		var chevron = what.children[0].children[0];

		if(content.style.display == "block"){
			content.style.display = "none";
			chevron.setAttribute("src","img/chevron.svg");
			
		}else{
			content.style.display = "block";
		chevron.setAttribute("src","img/chevronUp.svg");
		
		}
	}
	/*Q&A 2번째 질문*/
	var what1 = document.getElementById("what1");
	what1.onclick= function(){
	
		var content = what1.nextElementSibling;

		var chevron = what1.children[0].children[0];

		if(content.style.display == "block"){
			content.style.display = "none";
			chevron.setAttribute("src","img/chevron.svg");
			
		}else{
			content.style.display = "block";
		chevron.setAttribute("src","img/chevronUp.svg");
		
		}
	}
		
	/*Q&A 3번째 질문*/
	var what2 = document.getElementById("what2");
	what2.onclick= function(){
	
		var content = what2.nextElementSibling;

		var chevron = what2.children[0].children[0];

		if(content.style.display == "block"){
			content.style.display = "none";
			chevron.setAttribute("src","img/chevron.svg");
			
		}else{
			content.style.display = "block";
		chevron.setAttribute("src","img/chevronUp.svg");
		
		}
	}
	
	/*Q&A 4번째 질문*/
	var what3 = document.getElementById("what3");
	what3.onclick= function(){
	
		var content = what3.nextElementSibling;

		var chevron = what3.children[0].children[0];

		if(content.style.display == "block"){
			content.style.display = "none";
			chevron.setAttribute("src","img/chevron.svg");
			
		}else{
			content.style.display = "block";
		chevron.setAttribute("src","img/chevronUp.svg");
		
		}
	}
	/*Q&A 5번째 질문*/
	var what4 = document.getElementById("what4");
	what4.onclick= function(){
	
		var content = what4.nextElementSibling;

		var chevron = what4.children[0].children[0];

		if(content.style.display == "block"){
			content.style.display = "none";
			chevron.setAttribute("src","img/chevron.svg");
			
		}else{
			content.style.display = "block";
		chevron.setAttribute("src","img/chevronUp.svg");
		
		}
	}
	
	/*Q&A 6번째 질문*/
	var what5 = document.getElementById("what5");
	what5.onclick= function(){
	
		var content = what5.nextElementSibling;

		var chevron = what5.children[0].children[0];

		if(content.style.display == "block"){
			content.style.display = "none";
			chevron.setAttribute("src","img/chevron.svg");
			
		}else{
			content.style.display = "block";
		chevron.setAttribute("src","img/chevronUp.svg");
		
		}
	}
		
		
		
	var insertBtn = document.getElementById("insert-btn");
		insertBtn.onclick= function(){
		
		var content = insertBtn.nextElementSibling;
		if(content.style.display == "block"){
			content.style.display = "none";
			
			
		}else{
			content.style.display = "block";
		
		
		}
	
	}

  /*공유_button*/

	var shareBtn = document.getElementById("share_btn");
	var hiddenBox = document.getElementById("hidden_box");
	shareBtn.onclick = function(){
		alert("testtest");
		if(hiddenBox.style.visibility == "hidden"){
			
			hiddenBox.style.visibility = "visible";
			
		}else{
			hiddenBox.style.visibility = "hidden";
		}
		
		
	}






	
}