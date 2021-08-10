window.onload = function() {
		var xhr = new XMLHttpRequest();
	
		var categoryBtn = document.getElementById("category-btn");
		var category = '<%=category%>';
		var categoryName = document.getElementById("category-name");
		var categoryDrop = document.getElementById("category-drop");
		
		var art = document.getElementById("art");
		var cooking = document.getElementById("cooking");
		var language = document.getElementById("language");
		var programming = document.getElementById("programming");
		var sport = document.getElementById("sport");
		
		categoryBtn.onclick = function() {
			if(categoryDrop.style.display == "block"){
				categoryDrop.style.display = "none";
			}else{
				categoryDrop.style.display = "block";
				
				art.onclick = function() {
					categoryName.innerText = "Art";
					categoryDrop.style.display = "none";
					category = "ART";
					console.log(category);
					xhr.open("GET","/takeit_prj/main", true);
					xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
					xhr.send("category="+category.value);
					
					console.log(xhr);
					
					xhr.onreadystatechange = function(){
						if(xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200){
							var data = xhr.responseText;
							console.log(data);
						}
					}
				}
				cooking.onclick = function() {
					categoryName.innerText = "Cooking";
					categoryDrop.style.display = "none";
					category = "COOKING";
					json.category = category;
					json.range = range;
					console.log(json);
				}
				language.onclick = function() {
					categoryName.innerText = "Language";
					categoryDrop.style.display = "none";
					category = "LANGUAGE";
				}
				programming.onclick = function() {
					categoryName.innerText = "Programming";
					categoryDrop.style.display = "none";
					category = "PROGRAMMING";
				}
				sport.onclick = function() {
					categoryName.innerText = "Sport";
					categoryDrop.style.display = "none";
					category = "SPORT";
				}
			}
		}
		
		var rangeBtn = document.getElementById("range-btn");
		var range = '<%=range %>';
		var rangeName = document.getElementById("range-name");
		var rangeDrop = document.getElementById("range-drop");
		
		var recommend = document.getElementById("recommend");
		var newest = document.getElementById("newest");
		
		rangeBtn.onclick = function() {
			if(rangeDrop.style.display == "block"){
				rangeDrop.style.display = "none";
			}else{
				rangeDrop.style.display = "block";
				
				recommend.onclick = function() {
					rangeName.innerText = "추천순";
					rangeDrop.style.display = "none";
					range = "RECOMMEND";
				}
				newest.onclick = function() {
					rangeName.innerText = "최신순";
					rangeDrop.style.display = "none";
					range = "OPENDATE";
				}
			}
		}
		
	}