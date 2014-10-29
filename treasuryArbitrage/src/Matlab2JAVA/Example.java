package Matlab2JAVA;
import com.mathworks.toolbox.javabuilder.*;//����Matlab��ذ�
import Arbitrage_Main.*;//����matlab�����jar��

/*
 * ��ѩ�ҵ�Arbitrage_Main.jar������������������������, []��Ϊ����ֵ
 * 
 * �ú������ܣ�
 * �������Ų���x,y,k
 * function [optimization_x,optimization_y,optimization_k]=Arbitrage_Main(f1,f2,stop_loss,stop_profit)
 * 
 * �ú������ܣ�
 * %signal��ǰ����״̬��״̬��ȡ-3,-2��-1,0,1,2,3��
 * % �����signal���յ�ǰ�����¼۸��Ƿ�Ӧ�����ס�signal=1�������������֣�
 * %signal=-1�������������֣�signal=0�������ף�signal=2��ֹӯƽ�֣�signal=-2,ֹ
 * %��ƽ��,signal=3����������ƽ�֣�signal=-3����������ƽ�֣�
 * function [signal,buyprice1,saleprice1]=close(f1,f2,newprice1,newprice2,buyprice,saleprice,k,signal,stop_loss,stop_profit)
 * 
 * �ú������ܣ�
 * %signal��ǰ����״̬��״̬��ȡ-2��-1,0,1,2��
 * % �����signal���յ�ǰ�����¼۸��Ƿ�Ӧ�����ס�signal=1�������������֣�
 * %signal=-1�������������֣�signal=0�������ף�signal=2��ֹӯƽ�֣�signal=-2,ֹ
 * %��ƽ�֡�
 * function [signal,buyprice,saleprice]=open(f1,f2,newprice1,newprice2,x,y,k)
 * 
 * �������jar���İ����ĺ������������
 * 
 * 
 */

public class Example {
	
	public static void main(String[] args) {

		//matlab������jar���������class
		//��ѩ�ҵ�Arbitrage_Main.jar�����������
		Arbitrage_Main m = null;
		Open open_position = null;
		Close close_position = null;
		//�������jar�����������
		
		//�õ�����
		double f1 = 90,f2 = 91;//�۸���ʷ��¼
		int stop_loss = -1,stop_profit = 1;
		double newprice1=91,newprice2=92,x=90,y=90.1,k = 2,buyprice = 90,saleprice = 100,signal = 0;
		
		Object[] result = null; // ���ڱ��������
		
		try {
			//Arbitrage_Main��������ʾ��
			//��һ���������ֱ�ʾ��Ҫ��õĽ�����������������3����ʾ������������x,y,k;���д2,��ֻ�ܵõ�x,y;
			//��ͬ
			m = new Arbitrage_Main();  //!!!!important
			result =m.Arbitrage_Main(2,f1,f2,stop_loss, stop_profit);
			System.out.println(result[0]);   //optimization_x,optimization_y,optimization_k
			System.out.println(result[1]);
//			System.out.println(result[2]);   //m�ļ�������
			System.out.println("End of 1");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception 1 catched!");
		}

		try {
			//Open��������ʾ��
			open_position = new Open(); //!!!!important
			result =open_position.open(3,f1,f2,newprice1,newprice2,x,y,k);
			System.out.println(result[0]);  //signal,buyprice1,saleprice1
			System.out.println(result[1]);
			System.out.println(result[2]);
			System.out.println("End of 2");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception 2 catched!");
		}
		
		try {
			//Close��������ʾ��
			close_position = new Close(); //!!!!important
			result =close_position.close(3,f1,f2,newprice1,newprice2,buyprice,saleprice,k,signal,stop_loss,stop_profit);
			System.out.println(result[0]); //signal,buyprice,saleprice
			System.out.println(result[1]);
			System.out.println(result[2]);
			System.out.println("End of 3");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception 3 catched!");
		}
		
	}//end of main
}//end of this class Example
