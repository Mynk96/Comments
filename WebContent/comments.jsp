<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import = "java.util.*" %>
<%@ page import = "beans.Comments" %>
<%@ page import = "beans.Comment" %>
<%@ page import = "beans.Reply" %>

<% ArrayList<Comment> comments = Comments.showComment();%>
<script>
$(document).ready(function(){
	$("#btnReply").click(function(){
		$.post("http://localhost:8080/Comments/Reply",$("#replyForm").serialize(), function(data,status){
			document.getElementById("showReply").innerHTML = data;
		});
	});
});


</script>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<% for (Comment comment : comments){ %>		
	<div class = "container">		
			<div class = "row">
				<div class = "col-lg-12">
					<strong class = "edited-font"><%=comment.getName()%></strong><span class = "pull-right">2 minutes ago</span>
					<p><%=comment.getComment()%></p>
					<a href="#comment-box" data-toggle = "collapse">Reply</a>
				</div>
		</div>
		<div id = "comment-box" class = "collapse">
						<form id = "replyForm">
						<div class = "form-group">
							<textarea class = "form-control"></textarea>
						</div>
						<div class = "form-group">
							<input type = "submit" value = "Reply" class = "btn-primary" id = "btnReply">
						</div>
						</form>
					</div>
		
		<%	ArrayList<Reply> replies  = comment.showReplies(); %>
		<% for (Reply reply : replies){ %>
					<div class = "well well-sm">
						<a href="#"><%= reply.getName() %></a><span class = "pull-right">2 minutes ago</span>
						<p><%= reply.getReply()%></p>
					</div>
		<%} %>			
	</div>
	<div class = "container" id = "showReply"></div>
	<%} %>