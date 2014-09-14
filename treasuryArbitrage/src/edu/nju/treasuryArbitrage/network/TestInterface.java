package edu.nju.treasuryArbitrage.network;

public class TestInterface {

	public static void main(String[] args) {
		DataInterfaceToServer dif = new DataInterfaceToServer();
		//System.out.println(dif.register("name", "psd"));
		//System.out.println(dif.loginValidate("name", "psd"));
		//System.out.println(dif.changePWD("name", "psd", "psdd"));
		
		System.out.println(dif.loginValidate("a", "c"));
		System.out.println(dif.getFinanceList());
		System.out.println(dif.getRecordList());
		System.out.println(dif.getRepoList());
		System.out.println(dif.getPara_GUAR());
		System.out.println(dif.getArbDetail());
	}

}
