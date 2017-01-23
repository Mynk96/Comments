package beans;

import includes.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Comment {
	private int id;
	private String name;
	private String comment;
	
	public Comment(int id,String name,String comment){
		this.id = id;
		this.name = name;
		this.comment = comment;
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
	public ArrayList<Reply> showReplies() throws SQLException{
		ArrayList<Reply> replies = new ArrayList<Reply>();
		String sql = "SELECT * FROM replies WHERE comment_id = ?";
		ResultSet rs = querySelect(prepare(sql,getId()));
		while(rs.next()){
			String name = rs.getString("name");
			String reply = rs.getString("reply");
			Reply commentReply = new Reply(name,reply);
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

}
