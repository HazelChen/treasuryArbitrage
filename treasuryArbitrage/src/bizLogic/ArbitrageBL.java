package bizLogic;

import vo.Arb_detail;

public class ArbitrageBL {

	public ArbitrageBL(){}
	
	public Arb_detail getDetail(String id){
		Arb_detail arb = new Arb_detail(id);
		
		//向服务器读取参数并进行设置,转换为Java类，完善PO
		
		return arb;
	}
}
