package bizLogic;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import vo.Message;

public class MessContainerBL {
	private ArrayList<Message> messages;
	
	public MessContainerBL(){
		messages = new ArrayList<Message>();
	}
	
	public ArrayList<Message> getmessages(){
		return messages;
	}
	
	void AddUnwind(){
		AddMess("unwind");
	}
	
	void AddArb(){
		AddMess("arbitrage");
	}
	
	void AddMess(String kind){
		String info;
		String time;
		if(kind.equalsIgnoreCase("unwind")){
			info="���ش�ƽ�ֻ���";
		}else if(kind.equalsIgnoreCase("arbitrage")){
			info="���ش���������";
		}else{
			info="δ֪��Ϣ";
		}
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
		time = df.format(new Date());// new Date()Ϊ��ȡ��ǰϵͳʱ��
		
		messages.add(new Message(info,time,kind));
	}
	void ReadMess(int index){
		messages.get(index).setRead(true);
	}
	void DeleteMess(int index){
		messages.remove(index);
	}
		
}
