package edu.nju.treasuryArbitrage.model.ctp;

public class GetCtpData implements Runnable{
	public String[] codes = {"rb1505","rb1510","ag1505"};
	public GetCtpData(String[] codes){this.codes = codes;}
	
	@Override
	public void run() {
		// TODO �Զ����ɵķ������
		System.loadLibrary("../ThostTraderApi/thostmduserapi");
		TestJNA.INSTANCE.callBack_String(new callBack_String());
		TestJNA.INSTANCE.callBack_Double(new callBack_Double());
		TestJNA.INSTANCE.initial(codes,codes.length);
	}
	
}
