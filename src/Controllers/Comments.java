package Controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import beans.Comment;




/**
 * Servlet implementation class Comments
 */
public class Comments extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Comments() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/html");
		PrintWriter out = response.getWriter();
		if(!((request.getParameter("comment").isEmpty())|| (request.getParameter("comment").equals(" "))|| (request.getParameter("comment").equals("")))){
			String name = (String)request.getSession().getAttribute("name");
			String comment = request.getParameter("comment");
			try {
				beans.Comments comments = new beans.Comments(name,comment);
				out.write("<div class = \"row\">");
				out.write("<div class = \"col-lg-12\">");
				out.write("<strong class = \"edited-font\">" + name + "</strong><span class = \"pull-right\">2 minutes ago</span>");
				out.write("<p>" + comment + "</p>");
				out.write("<div class = \"comment-box\" hidden>");
				out.write("<form class = \"replyForm\">");
				out.write("<div class =\"form-group\">");
				out.write("<textarea class = \"form-control commentsReply\" value = \"\" name = \"reply\"></textarea>");
				out.write("</div>");
				out.write("<div class =\"form-group\">");
				out.write("<input type = \"hidden\" value =\"\" name = \"commentId\"/>");
				out.write("</div>");
				out.write("</form>");
				out.write("</div>");
				out.write("<button class = \"reply btn btn-primary\" onclick = \"toogleAndSubmit(this)\">Reply</button>");
				out.write("</div>");
				out.write("</div>");
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else{
			out.write("No comment");
		}
	}
}
