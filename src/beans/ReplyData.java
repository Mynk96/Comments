package beans;

import includes.Database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReplyData {
	private String name;
	private String reply;
	private int commentId;
	public static String error = "";
	
	public ReplyData(String name,String reply,int commentId){
		this.name = name;
		this.reply = reply;
		this.commentId = commentId; 
	}
	
	
	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public ReplyData(String name,String reply){
		this.name = name;
		this.reply = reply;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	public boolean doReply() throws SQLException{
		String sql = "INSERT INTO replies VALUES(id,?,?,?)";
		int result = queryUpdate(prepare(sql,getCommentId(),getName(),getReply()));  
		if(result == 0){
			error = "reply cannot be done!";
			return false;
			
		}
		return true;
		
	}
	public static PreparedStatement prepare(String sql,int commentId,String name,String reply){
		try {
			PreparedStatement st = Database.conn.prepareStatement(sql);
			st.setInt(1, commentId);
			st.setString(2,name);
			st.setString(3,reply);
			return st;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static int queryUpdate(PreparedStatement stm) throws SQLException{
		return stm.executeUpdate();
	}
	
}
