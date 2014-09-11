package bizLogic;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import vo.Record;

public class RecordBL {
	private ArrayList<Record> record_list;
	
	public RecordBL(){}
	
	public ArrayList<Record> getRecordList(){
		
		Record record = new Record();
		record_list.add(record);
		
		return record_list;
	}
	
	public void addRecord(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
		String time = df.format(new Date());// new Date()Ϊ��ȡ��ǰϵͳʱ��
		
		Record record = new Record();
		record.setTime(time);
		record.setState("unknown");
		record_list.add(record);
	}
	
	public void cancle(String reco_ID){
		for(Record record:record_list){
			if(record.getRepo_ID().equals(reco_ID)){
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
				String time = df.format(new Date());// new Date()Ϊ��ȡ��ǰϵͳʱ��
				
				record.setTime(time);
				record.setState("cancle");
				break;
			}
		}
	}
	
	public void done(String reco_ID){
		for(Record record:record_list){
			if(record.getRepo_ID().equals(reco_ID)){
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
				String time = df.format(new Date());// new Date()Ϊ��ȡ��ǰϵͳʱ��
				
				record.setTime(time);
				record.setState("done");
				break;
			}
		}
	}
}
