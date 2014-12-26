package edu.nju.treasuryArbitrage.model.ctp;

public class getCtpData implements Runnable{
	public String[] codes = {"rb1505","rb1510","ag1505"};
	public getCtpData(String[] codes){this.codes = codes;}
	
	@Override
	public void run() {
		// TODO 自动生成的方法存根
		System.loadLibrary("../ThostTraderApi/thostmduserapi");
//		TestJNA.INSTANCE.callBack_String(new callBack_String());
//		TestJNA.INSTANCE.callBack_Double(new callBack_Double());
		TestJNA.INSTANCE.initial(codes,codes.length);
	}
	
}
