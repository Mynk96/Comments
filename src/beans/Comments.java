package beans;


import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


import org.json.JSONArray;
import org.json.JSONObject;

import includes.Database;

public class Comments {
	private String comment;
	private String name;
	private int id;
	private Date comment_time;
	public static int initialCount;
	public static String error = "Commentbox";
	
	
	public Comments(String name,String comment,Date comment_time) throws SQLException{
		this.name = name;
		this.comment = comment;
		this.comment_time = comment_time;
		initialCount = countOfComments();
		comment();
	}
	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean comment() throws SQLException{
		String sql = "INSERT INTO comments VALUES(id,?,?,?)";
		int result = queryUpdate(prepare(sql,getName(),getComment(),getCommentTime()));  
		if(result == 0){
			error = "Comment cannot be done!";
			return false;
			
		}
		return true;
		
	}
	public static JSONArray  showStastics() throws SQLException{
		JSONArray stats = new JSONArray();
		JSONObject numbers = new JSONObject();
		numbers.put("noOfComments",countOfComments());
		numbers.put("noOfReplies", countOfReplies());
		stats.put(0, numbers);
		return stats;
	}
	
	
	public static PreparedStatement prepare(String sql,String name,String comment,Date comment_time){
		try {
			PreparedStatement st = Database.conn.prepareStatement(sql);
			st.setString(1, name);
			st.setString(2,comment);
			SimpleDateFormat formatDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			String formattedTime = formatDate.format(comment_time);
			st.setString(3,formattedTime);
			return st;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static PreparedStatement prepare(String sql){
		try {
			PreparedStatement st = Database.conn.prepareStatement(sql);
			return st;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static int queryUpdate(PreparedStatement stm) throws SQLException{
			return stm.executeUpdate();
	}
	public static ResultSet querySelect(PreparedStatement stm) throws SQLException{
		return stm.executeQuery();
}
	public static ArrayList<Comment>  showComments() throws SQLException{
		ArrayList<Comment> newComments = new ArrayList<Comment>();
		String sql = "SELECT * FROM comments ORDER BY id DESC";
		ResultSet rs = querySelect(prepare(sql));
		while(rs.next()){
			int id = rs.getInt("id");
			String name = rs.getString("name");
			String comment = rs.getString("comment");
			Date timeOfComment = rs.getTimestamp("comment_time");
			Comment newComment = new Comment(id,name,comment,timeOfComment);
			newComments.add(newComment);
			
			}
		return newComments;
	}
	public static int countOfComments() throws SQLException{
		String sql = "SELECT COUNT(*) FROM comments";
		ResultSet rs = querySelect(prepare(sql));
		while(rs.next()){
			return rs.getInt(1);
		}
		return 0;
	}
	public static int countOfReplies() throws SQLException{
		String sql = "SELECT COUNT(*) FROM replies";
		ResultSet rs = querySelect(prepare(sql));
		while(rs.next()){
			return rs.getInt(1);
		}
		return 0;
	}
	public static ArrayList<Comment>  showNewComments() throws SQLException{
		ArrayList<Comment> newComments = new ArrayList<Comment>();
		String sql = "SELECT * FROM comments ORDER BY id DESC";
		ResultSet rs = querySelect(prepare(sql));
		while(rs.next()){
			int id = rs.getInt("id");
			String name = rs.getString("name");
			String comment = rs.getString("comment");
			Date timeOfComment = rs.getDate("comment_time");
			Comment newComment = new Comment(id,name,comment,timeOfComment);
			newComments.add(newComment);
			
			}
		return newComments;
	}
	public Date getCommentTime(){
		return comment_time;
	}

		
	
	

}
