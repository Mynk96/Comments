package Controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		response.setContentType("text/html");
		response.getWriter().write("HELLO");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		if(!(request.getParameter("comment").isEmpty())){
			String name = (String)request.getSession().getAttribute("name");
			String comment = request.getParameter("comment");
			try {
				beans.Comments comments = new beans.Comments(name,comment);
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				out.write("<div class = \"row\">");
				out.write("<div class = \"col-lg-12\">");
				out.write("<strong class = \"edited-font\">" + name + "</strong><span class = \"pull-right\">2 minutes ago</span>");
				out.write("<p>" + comment + "</p>");
				out.write("<a href=\"#comment-box\" data-toggle = \"collapse\">Reply</a>");
				out.write("</div>");
				out.write("</div>");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
