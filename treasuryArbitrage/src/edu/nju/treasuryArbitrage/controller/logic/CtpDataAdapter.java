package edu.nju.treasuryArbitrage.controller.logic;

//import java.text.DecimalFormat;
//import java.util.*;
//
//import edu.nju.treasuryArbitrage.model.ArbDetail;
import edu.nju.treasuryArbitrage.controller.ctp.*;

public class CtpDataAdapter {
	
//	private ArrayList<ArbDetail> detail_list;
	
	public static void main(String[] args) {
		CtpDataAdapter adapter = new CtpDataAdapter();
		adapter.startOrder();
//		ArrayList<ArbDetail> list = adapter.getDetailList();
//		for(ArbDetail tem:list){
//			System.out.println(tem.getSymbol());
//		}
	}
	
	public void startOrder(){
		String[] codes = {"rb1505","rb1510","ag1505"};
		Thread th = new Thread(new GetCtpData(codes));
		th.start();
//		try {
//			Thread.sleep(1500);
//		} catch (InterruptedException e) {
//			// TODO �Զ����ɵ� catch ��
//			e.printStackTrace();
//		}
	}
	

//	
//	public ArrayList<ArbDetail> getDetailList(){
//		detail_list = new ArrayList<ArbDetail>();
//		for(int i=0;i<6;i++){
//			System.err.println("Copying->"+i);
//			ArbDetail detail = getSingleData();
//			boolean find = false;
//			for(ArbDetail tem:detail_list){
//				if(tem.getSymbol().equals(detail.getSymbol())){
//					//if(i>=3) detail.setSymbol("Test"+i);
//					tem = detail;
//					find=true;
//					break;
//				}
//			}
//			if(!find){detail_list.add(detail);}
//		}
//		return detail_list;
//	}
//	

	
}
