package beans;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import includes.Database;

public class Users {
	private String email;
	private String password;
	private String name;
	public static String error = "hello";
	
	public Users(String email,String password){
		this.email = email;
		this.password = password;
		
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public static String getError(){
		return error;
	}
	
	
	public boolean validate() throws SQLException{
		String sql = "SELECT * FROM users WHERE email=? and password=?";
		ResultSet rs = query(prepare(sql,getEmail(),getPassword()));  
		if(!(rs.next())){
			error = "Sorry you're not registered";
			return false;
		}
		setName(rs.getString(4));
		return true;
	}
	
	public PreparedStatement prepare(String sql,String email,String password){
		try {
			PreparedStatement st = Database.conn.prepareStatement(sql);
			st.setString(1,email);
			st.setString(2,password);
			return st;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	public ResultSet query(PreparedStatement stm) throws SQLException{
		return stm.executeQuery();
	}
	

}
