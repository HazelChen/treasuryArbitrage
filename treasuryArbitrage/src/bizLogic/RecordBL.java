package bizLogic;

import java.util.ArrayList;

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
		Record record = new Record();
		record.setState("unknown");
		record_list.add(record);
	}
	
	public void cancle(String reco_ID){
		for(Record record:record_list){
			if(record.getRepo_ID().equals(reco_ID)){
				record.setState("cancle");
				break;
			}
		}
	}
}
