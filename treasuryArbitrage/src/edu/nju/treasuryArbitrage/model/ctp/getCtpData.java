package edu.nju.treasuryArbitrage.model.ctp;

public class getCtpData implements Runnable{
	String code = "";
	public getCtpData(String code){this.code = code;}
	
	@Override
	public void run() {
		// TODO �Զ����ɵķ������
		System.loadLibrary("../ThostTraderApi/thostmduserapi");
		TestJNA.INSTANCE.callBack_String(new callBack_String());
		TestJNA.INSTANCE.callBack_Double(new callBack_Double());		
		TestJNA.INSTANCE.initial(code);
	}
	
}
