<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import = "java.util.*" %>
<%@ page import = "beans.Comments" %>
<%@ page import = "beans.Comment" %>
<%@ page import = "beans.ReplyData" %>

<% ArrayList<Comment> comments = Comments.showComments();%>

<!DOCTYPE html PUBLIC "-/ /W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<div class = "container" id = "showReply">what the fun</div>
	<% for (Comment comment : comments){ %>		
	<div class = "container comments">		
			<div class = "row">
				<div class = "col-lg-12">
					<strong class = "edited-font"><%=comment.getName()%></strong><span class = "pull-right">2 minutes ago</span>
					<p><%=comment.getComment()%></p>
					<div class = "comment-box" hidden>
					<form class = "replyForm">
			<div class = "form-group">
				<textarea class = "form-control commentsReply" value = "" name = "reply"></textarea>
			</div>
			<div class = "form-group">
				<input type = "hidden" value = <%=comment.getId()%> name = "commentId"/>
			</div>
			</form>
		</div>
					<button class = "reply btn btn-primary" onclick = "toogleAndSubmit(this)">Reply</button>
				</div>
			</div>
			 
		
			
		
		
		<%	ArrayList<ReplyData> replies  = comment.showReplies(); %>
		<% for (ReplyData reply : replies){ %>
					<div class = "well well-sm">
						<a href="#"><%= reply.getName() %></a><span class = "pull-right">2 minutes ago</span>
						<p><%= reply.getReply()%></p>
					</div>
					
		<%} %>
		
	</div>
	
	<%} %>