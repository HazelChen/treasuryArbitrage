
package edu.nju.treasuryArbitrage.controller.ctp;

public class GetCtpData implements Runnable{
	public String[] codes = {"rb1505","rb1510","ag1505"};
	public GetCtpData(String[] codes){this.codes = codes;}
	
	public static callBack_String callStr = new callBack_String();
	public static callBack_Double callDou = new callBack_Double();
	
	@Override
	public void run() {
		// TODO �Զ����ɵķ������
//		System.loadLibrary("../ThostTraderApi/thostmduserapi");
		System.loadLibrary("../DLL/TestJNA");
//		System.loadLibrary("TestJNA");
		TestJNA.INSTANCE.callBack_String(callStr);
		TestJNA.INSTANCE.callBack_Double(callDou);
		TestJNA.INSTANCE.initial(codes,codes.length);
	}
	
}
