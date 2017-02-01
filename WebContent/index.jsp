<%@page import="java.beans.Beans"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import = "includes.Sessions"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style>
#hello{
	display = none;		
}

</style>
<script>
var show = false;

function validateForm(){
		var x = document.forms["testform"]["comment"].value;
		if(x == ""){
			$("#error").show();
			document.getElementById("error").innerHTML = "NO Comment";
		}
		else{
			$("#error").hide();
			submitForm();
		}
	}
function submitForm(){
	$(document).ready(function(){
				$.post("http://localhost:8080/Comments/Comments",$("#testform").serialize(), function(data,status){
					console.log(data); 
					document.getElementById("newComment").innerHTML = data + document.getElementById("newComment").innerHTML;
					$("#comment").val("");
				});
		});
}
function logout(){
	$(document).ready(function(){
		$.get("http://localhost:8080/Comments/logout",{logout:"yes"});	
	});
	
}
function isEmpty(value){
	if(value.length == 0){
		return true;
	} else if(value == "") {
		return true;
	}else if (value == this.default){
		return true;
	}else{
		return true;
	}
}

function toogleAndSubmit(object){
	var comments = document.getElementsByClassName("comment-box");
	var textAreaValue = document.getElementsByClassName("commentsReply");
	var index = $(".reply").index(object);
	var value = $(textAreaValue[index]).val();
	if(isEmpty(value)){
		$(comments[index]).toggle();
	}else{
		var forms = $('form:eq(0)');
		$.post("http://localhost/Comments/Reply",forms.serialize(),function(data,status){
			document.getElementById("showReply").innerHTML = data;
		});
	}
}


	
	


</script>
<link rel = "stylesheet" type = "text/css" href = "css/test.css">
 <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Catamaran">
<title>Comments</title>

</head>
<body>
	<%	
		if((session.getAttribute("loggedIn") == null) || (session.getAttribute("loggedIn").equals("false")) ){ %>
			<jsp:include page = "header.jsp"></jsp:include>
		<%} else {%>
			<jsp:include page="user_header.jsp"></jsp:include>
		<%} %>

	
	<% if((session.getAttribute("loggedIn")!= null)  && session.getAttribute("loggedIn").equals("true")){ %>
		<jsp:include page = "doComment.jsp"></jsp:include>
		<%} %>
	<div class = "container" >
		<div id = "error" class = "alert alert-danger" hidden></div>
	</div>
	<div class = "container abc">
			<h3>Comment by others:</h3>
	</div>
	<div class = "container" id = "newComment">
	</div>
	<jsp:include page="comments.jsp"></jsp:include>
			
	<script>
	
	</script>
	
</body> 
</html>
