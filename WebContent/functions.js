/**
 * All the functions 
 */

function submitComment(form){
	var comment = form.elements["comment"];
	var name    = form.elements["name"];
	
	var ajaxRequest = new XMLHttpRequest();
	ajaxRequest.onreadystatechange = function(){
		if(this.readyState == 4 && this.status == 200){
			document.getElementById("mayank").innerHTML = ajaxRequest.responseText;
			
		}
	};
	ajaxRequest.open("POST","/Comments/Comments",true);
	ajaxRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	ajaxRequest.send("name = " + name.value & "comment = " + comment.value);
}
