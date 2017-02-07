<%@page import="beans.Comments"%>
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


</style>
<script>
var initialCount = <%= Comments.countOfComments() %>;
var finalCount = 0;

setInterval(function(){getNewComments()},5000);

function getNewComments(){
	$(document).ready(function(){
		if(finalCount > window.initialCount){
			$(".viewMore").fadeIn();
		}
		$.get("http://localhost:8080/Comments/Comments",function(data,status){
			window.finalCount = data;
			console.log(finalCount);
			console.log(window.initialCount);
			
		});
	});
	
}

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
function viewNewComments(){
	$(document).ready(function(){
			$("html body").animate({scrollTop:0},600);
			$("#newComments").load("newComments.jsp");
			window.initialCount = window.finalCount;
			$(".viewMore").fadeOut();
	});
}


	
	


</script>
<style>
.viewMore{
	margin-left:45%;
	margin-right:45%;
	border-radius:40%;
	margin-top:2%;
	position:fixed;
	display:none;
	z-index:100;

	
}



</style>
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
	<button class = "btn btn-primary viewMore" onclick = "viewNewComments()" hidden>New Comments</button>
	<div class = "container abc">
			<h3>Comment by others:</h3>
			
	</div>
	<div class = "container" id = "newComments">
	</div>
	<div class = "container">
	<jsp:include page = "comments.jsp"></jsp:include>
	</div>

	
</body> 
</html>
