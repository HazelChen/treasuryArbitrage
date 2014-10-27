package edu.nju.treasuryArbitrage.model;

public class Message{
	String info;	//简单信息
	String time;	//消息时间
	String kind;	//区分消息种类
	Boolean read;	//标记已读与否
	
//	public static void main(String[] args) {
//		new Message("aa");
//	}
//	
//	public Message(String kind){
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//		this.time = df.format(new Date());// new Date()为获取当前系统时间
//		System.out.println(time);
//		this.read = false;
//	}
	
	public Message(String info,String time,String kind){
		this.info = info;
		this.time = time;
		this.kind = kind;
		this.read = false;
	}
	
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public Boolean getRead() {
		return read;
	}
	public void setRead(Boolean read) {
		this.read = read;
	}
}
