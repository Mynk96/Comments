package Controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.ReplyData;

/**
 * Servlet implementation class Reply
 */
public class Reply extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Reply() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//String userName = (String)request.getSession().getAttribute("name");
		//String reply = request.getParameter(")
		PrintWriter out = response.getWriter();
		String reply = request.getParameter("reply");
		int commentId = Integer.parseInt(request.getParameter("commentId"));
		String name = (String)request.getSession().getAttribute("name");
		ReplyData sendReply = new ReplyData(name,reply,commentId);
		if(sendReply.doReply()){
			
		}
	}

}