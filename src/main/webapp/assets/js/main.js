window.onload = function() {

	const hostIndex = location.href.indexOf(location.host) + location.host.length;

	const xhr = new XMLHttpRequest();
	
	const art = document.getElementById("art");
	const cooking = document.getElementById("cooking");
	const language = document.getElementById("language");
	const programming = document.getElementById("programming");
	const sport = document.getElementById("sport");


	const recommend = document.getElementById("recommend");
	const newest = document.getElementById("newest");

	if ($('input[id="category"]').is(':checked')) {
		console.log($('input[id="category"]').val());
}
//
//
//		art.onclick = function() {
//			xhr.open("POST", location.href.substring(hostIndex, location.href.indexOf('/', hostIndex + 1)) + "/main", true);
//			xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//			xhr.send("category=ART" + "&recommend=RECOMMEND");
//		}
//
//		cooking.onclick = function() {
//			xhr.open("POST", location.href.substring(hostIndex, location.href.indexOf('/', hostIndex + 1)) + "/main", true);
//			xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//			xhr.send("category=COOKING" + "&recommend=RECOMMEND");
//		}
//		language.onclick = function() {
//			xhr.open("POST", location.href.substring(hostIndex, location.href.indexOf('/', hostIndex + 1)) + "/main", true);
//			xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//			xhr.send("category=LANGUAGE" + "&recommend=RECOMMEND");
//		}
//		programming.onclick = function() {
//			xhr.open("POST", location.href.substring(hostIndex, location.href.indexOf('/', hostIndex + 1)) + "/main", true);
//			xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//			xhr.send("category=PROGRAMMING" + "&recommend=RECOMMEND");
//		}
//		sport.onclick = function() {
//			xhr.open("POST", location.href.substring(hostIndex, location.href.indexOf('/', hostIndex + 1)) + "/main", true);
//			xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//			xhr.send("category=SPORT");
//		}
//
//
//
//
//
//		recommend.onclick = function() {
//			xhr.open("POST", location.href.substring(hostIndex, location.href.indexOf('/', hostIndex + 1)) + "/main", true);
//			xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//			xhr.send("range=RECOMMEND");
//		}
//		newest.onclick = function() {
//			xhr.open("POST", location.href.substring(hostIndex, location.href.indexOf('/', hostIndex + 1)) + "/main", true);
//			xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//			xhr.send("category=" + sessionStorage.getItem("category") + "&range=OPENDATE");
//
//		}
}
		
