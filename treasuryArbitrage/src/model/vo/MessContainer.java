package model.vo;

import java.util.ArrayList;

public class MessContainer {
	private ArrayList<Message> messages;
	
	public MessContainer(){
		messages = new ArrayList<Message>();
	}
	
	public ArrayList<Message> getmessages(){
		return messages;
	}
	
	void AddMess(String Type){}
	void ReadMess(int MessID){}
	void DeleteMess(int MessID){}
	
	@SuppressWarnings("unused")
	private class Message{
		String info;	//����Ϣ
		String time;	//��Ϣʱ��
		String kind;	//������Ϣ����
		Boolean read;	//����Ѷ����
		
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
	
}
