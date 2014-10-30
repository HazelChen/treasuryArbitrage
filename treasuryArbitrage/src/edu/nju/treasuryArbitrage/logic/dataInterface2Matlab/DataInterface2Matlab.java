package edu.nju.treasuryArbitrage.logic.dataInterface2Matlab;

import Arbitrage_Main.Arbitrage_Main;
import Arbitrage_Main.Close;
import Arbitrage_Main.Open;

import com.mathworks.toolbox.javabuilder.*;
//����java�Դ�Matlab��ذ�
//�������Ա�Լ���matlab�����jar��

public class DataInterface2Matlab {
		
	public DataInterface2Matlab(){
		
	}
	
	
	/*
	 * resultΪ���ض������飬��Ԫ�طֱ�Ϊx,y,k
	 */
	public Object[] Arbitrage_Main(MWNumericArray f1,MWNumericArray f2,double stop_loss,double stop_profit){
		Object[] result = null;
		Arbitrage_Main m = null;
		try {
			//Arbitrage_Main��������ʾ��
			//��һ���������ֱ�ʾ��Ҫ��õĽ�����������������3����ʾ������������x,y,k;���д2,��ֻ�ܵõ�x,y;
			//��ͬ
			m = new Arbitrage_Main();  //!!!!important
			
			result =m.Arbitrage_Main(3,f1,f2,stop_loss, stop_profit);
			//optimization_x,optimization_y,optimization_k
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Arbitrage_MAin Exception catched!");
			System.out.println(e.toString());
		}	
		return result;
	}
	
	public Object[] Open(MWNumericArray f1,MWNumericArray f2,
			double newprice1,double newprice2,double x,double y,double k){
		Open open_position = null;
		Object[] result = null;
		try {
			//Open��������ʾ��
			open_position = new Open(); //!!!!important
			result =open_position.open(3,f1,f2,newprice1,newprice2,x,y,k);
		    //signal,buyprice1,saleprice1
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception 2 catched!");
		}
		return result;
	}
	
	public Object[] Close(MWNumericArray f1,MWNumericArray f2,
			double newprice1,double newprice2,double buyprice,double saleprice,
			double k,double signal,double stop_loss,double stop_profit){
		Close close_position = null;
		Object[] result = null;
		try{
			close_position = new Close(); //!!!!important
			buyprice = 91;saleprice = 93;signal = 1; //�ο�����	
			result =close_position.close(3,f1,f2,
					newprice1,newprice2,buyprice,saleprice,k,signal,stop_loss,stop_profit);
			//signal,buyprice,saleprice
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception 3 catched!");
		}
		return result;
	}
}
