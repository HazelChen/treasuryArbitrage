package bizLogic;

import vo.Arb_detail;

public class ArbitrageBL {

	public ArbitrageBL(){}
	
	public Arb_detail getDetail(String id){
		Arb_detail arb = new Arb_detail(id);
		
		//���������ȡ��������������,ת��ΪJava�࣬����PO
		
		return arb;
	}
}
