package bizLogic;

import java.util.ArrayList;

import vo.Finance;

public class FinanceBL {
	private ArrayList<Finance> finance_list;
	
	public FinanceBL(){}
	
	public ArrayList<Finance> getFinanceList(){
		
		Finance finance = new Finance("",0,0,0);
		finance_list.add(finance);
		
		return finance_list;
	}
	
	public void addFinace(String time,double total,double guarantee,double idle){
		Finance finance = new Finance(time,total,guarantee,idle);
		finance_list.add(finance);
	}
}
