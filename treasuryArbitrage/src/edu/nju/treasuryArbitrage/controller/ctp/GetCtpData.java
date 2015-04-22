
package edu.nju.treasuryArbitrage.controller.ctp;

public class GetCtpData implements Runnable{
	public String[] codes;
	public GetCtpData(String[] codes){this.codes = codes;}
	
	public static callBack_String callStr = new callBack_String();
	public static callBack_Double callDou = new callBack_Double();
	
	@Override
	public void run() {
		System.loadLibrary("../DLL/TestJNA");
		TestJNA.INSTANCE.callBack_String(callStr);
		TestJNA.INSTANCE.callBack_Double(callDou);
		TestJNA.INSTANCE.initial(codes,codes.length);
	}
	
}
