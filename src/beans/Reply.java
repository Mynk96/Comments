package beans;

public class Reply {
	private String name;
	private String reply;
	
	public Reply(String name,String reply){
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

}
