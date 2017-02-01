package beans;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import org.json.JSONArray;
import org.json.JSONObject;

import includes.Database;

public class Comments {
	private String comment;
	private String name;
	public int id;
	public static String error = "Commentbox";
	
	
	public Comments(String name,String comment) throws SQLException{
		this.name = name;
		this.comment = comment;
		comment();
		showComment();
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
		String sql = "INSERT INTO comments VALUES(id,?,?)";
		int result = queryUpdate(prepare(sql,getName(),getComment()));  
		if(result == 0){
			error = "Comment cannot be done!";
			return false;
			
		}
		return true;
		
	}
	public static JSONArray  showComment() throws SQLException{
		JSONArray newComments = new JSONArray();
		String sql = "SELECT * FROM comments ORDER BY id DESC";
		ResultSet rs = querySelect(prepare(sql));
		while(rs.next()){
			int id = rs.getInt("id");
			String name = rs.getString("name");
			String comment = rs.getString("comment");
			JSONObject newComment = new JSONObject();
			newComment.put("id", id);
			newComment.put("name", name);
			newComment.put("comment",comment);
			
			newComments.put(newComment);
			
			}
		return newComments;
	}
	
	
	public static PreparedStatement prepare(String sql,String name,String comment){
		try {
			PreparedStatement st = Database.conn.prepareStatement(sql);
			st.setString(1, name);
			st.setString(2,comment);
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
			Comment newComment = new Comment(id,name,comment);
			newComments.add(newComment);
			
			}
		return newComments;
	}
		
	
	

}
