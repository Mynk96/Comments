package beans;

import includes.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Comment {
	private int id;
	private String name;
	private String comment;
	private Date comment_time;
	
	public Comment(int id,String name,String comment,Date comment_time){
		this.id = id;
		this.name = name;
		this.comment = comment;
		this.comment_time = comment_time;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ArrayList<ReplyData> showReplies() throws SQLException{
		ArrayList<ReplyData> replies = new ArrayList<ReplyData>();
		String sql = "SELECT * FROM replies WHERE comment_id = ?";
		ResultSet rs = querySelect(prepare(sql,getId()));
		while(rs.next()){
			String name = rs.getString("name");
			String reply = rs.getString("reply");
			ReplyData commentReply = new ReplyData(name,reply);
			replies.add(commentReply);
		}
		return replies;
	}
	public PreparedStatement prepare(String sql,int comment_id){
		try {
			PreparedStatement st = Database.conn.prepareStatement(sql);
			st.setInt(1,comment_id);
			return st;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public ResultSet querySelect(PreparedStatement stm) throws SQLException{
		return stm.executeQuery();
	}
	public String getCommentTime() {
		SimpleDateFormat formatDate = new SimpleDateFormat("dd MMM YYYY HH:mma");
		return formatDate.format(comment_time);
	}

}
