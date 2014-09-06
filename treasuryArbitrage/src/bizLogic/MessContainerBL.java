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
			info="有重大平仓机会";
		}else if(kind.equalsIgnoreCase("arbitrage")){
			info="有重大套利机会";
		}else{
			info="未知信息";
		}
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		time = df.format(new Date());// new Date()为获取当前系统时间
		
		messages.add(new Message(info,time,kind));
	}
	void ReadMess(int index){
		messages.get(index).setRead(true);
	}
	void DeleteMess(int index){
		messages.remove(index);
	}
		
}
