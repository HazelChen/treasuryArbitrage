package edu.nju.treasuryArbitrage.model;

public class Message{
	String info;	//����Ϣ
	String time;	//��Ϣʱ��
	String kind;	//������Ϣ����
	Boolean read;	//����Ѷ����
	
//	public static void main(String[] args) {
//		new Message("aa");
//	}
//	
//	public Message(String kind){
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
//		this.time = df.format(new Date());// new Date()Ϊ��ȡ��ǰϵͳʱ��
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
