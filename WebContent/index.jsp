<%@page import="java.beans.Beans"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
$(document).ready(function(){
	$("#submit").click(function(){
		$.post("http://localhost:8080/Comments/Comments",$("#testform").serialize(), function(data,status){
	        document.getElementById("newComment").innerHTML = data + document.getElementById("newComment").innerHTML;
	    });
	});	
	
	});


</script>
<link rel = "stylesheet" type = "text/css" href = "css/test.css">
 <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Catamaran">
<title>Comments</title>

</head>
<body>
	
	<div class = "container">
		<a href="#"><span class = "pull-right">Sign out</span></a>
		<span class = "pull-right">${sessionScope.name}</span>
		
	</div>
	
	<div class = "container">
		<h2>Comments Section</h2>
	</div>
	<div class = "container">	
		<h4>Add a new Comment:</h4>
	</div>
	<div class = "container">
		<form id = "testform">
			<div class = "form-group">
				<label class = "control-label" for = "comment">Comment:</label>
			</div>	
			<div>
				<textarea class = "form-control" rows = "5" cols = "100" id = "comment" name = "comment"></textarea>
			</div>
			<div class = "input-group-btn">
				<input class = "form-control btn-primary" type = "button" value = "Comment" id = "submit">
			</div>	
		</form>
	</div>
	<div class = "container">
			<h3>Comment by others:</h3>
	</div>		
	<div class = "container" id = "newComment">
	</div>
	
	<jsp:include page = "comments.jsp" />
	
</body>
</html>
