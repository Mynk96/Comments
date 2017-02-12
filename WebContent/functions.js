/**
 * All the functions 
 */
$(document).ready(function(){
	

	$("#newComments").load("comments.jsp");

	
});



setInterval(function(){getNewComments()},3000);

function getNewComments(){
	$(document).ready(function(){
		if(window.finalCountComments > window. initialCountComments){
			$(".viewMore").fadeIn();
		}
		if(window.finalCountReplies > window.initialCountReplies){
			$("#newComments").load("comments.jsp");
		}
		$.get("http://localhost:8080/Comments/Comments",function(data,status){
			window.finalCountReplies = data[0].noOfReplies;
			window.finalCountComments = data[0].noOfComments;
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
					getNewComments();
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
		return false;
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
		var forms = $('form:eq(' + (index+1) + ' )');
		$.get("http://localhost:8080/Comments/Reply",forms.serialize(),function(data,status){
			$("#newComments").load("comments.jsp");
		});
	}
}
function viewNewComments(){
	$(document).ready(function(){
			$("html body").animate({scrollTop:0},600);
			$("#newComments").load("comments.jsp");
			window.initialCountComments = window.finalCountComments;
			$(".viewMore").fadeOut();
	});
}