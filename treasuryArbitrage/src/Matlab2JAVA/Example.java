package Matlab2JAVA;
/*
 * ��Ҫ����matlab�İ�����Ҫ������صİ�
 */
import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.mathworks.toolbox.javabuilder.*;//����java�Դ�Matlab��ذ�

import Arbitrage_Main.*;//�������Ա�Լ���matlab�����jar��

/*
 * ��ѩ�ҵ�Arbitrage_Main.jar������������������������, []��Ϊ����ֵ
 * ʹ�þ�������μ�Ⱥ���ļ���matlab����˵��.doc
 * �ú������ܣ�
 * �������Ų���x,y,k  �ú���ÿ�����һ��,ʹ��������ʷ��¼
 * function [optimization_x,optimization_y,optimization_k]=Arbitrage_Main(f1,f2,stop_loss,stop_profit)
 * 
 * �ú������ܣ�
 * �����Ƿ�ƽ�ֵĺ�����ʹ��ǰһ�տ��̵�����ǰһ��ļ�¼��ÿ���ӣ����ڽ��ֺ�ÿ���ӣ���ÿ�룩����һ�Σ�ƽ�ֽ������ٵ���
 * %signal��ǰ����״̬��״̬��ȡ-3,-2��-1,0,1,2,3��
 * % �����signal���յ�ǰ�����¼۸��Ƿ�Ӧ�����ס�signal=1�������������֣�
 * %signal=-1�������������֣�signal=0�������ף�signal=2��ֹӯƽ�֣�signal=-2,ֹ
 * %��ƽ��,signal=3����������ƽ�֣�signal=-3����������ƽ�֣�
 * function [signal,buyprice1,saleprice1]=close(f1,f2,newprice1,newprice2,buyprice,saleprice,k,signal,stop_loss,stop_profit)
 * 
 * �ú������ܣ�
 * �����Ƿ񽨲֣�ʹ��ǰһ�տ��̵�����ǰһ��ļ�¼��ÿ���ӣ����ڽ���ǰÿ���ӣ���ÿ�룩����һ�Σ����ֺ��ٵ���
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
		getFlen g = null;		//test
		//��ѩ�ҵ�Arbitrage_Main.jar�����������
		Arbitrage_Main m = null;
		Open open_position = null;
		Close close_position = null;
		//�������jar�����������
		
		//�õ�����
		ArrayList<Double> lf1;
		ArrayList<Double> lf2;//�۸���ʷ��¼
		int stop_loss = -1,stop_profit = 1;
		double newprice1=91,newprice2=92,x=0,y=0,k = 2,
				buyprice = 0,saleprice = 0;
		int signal = 0;
		
		Object[] result = null; // ���ڱ��������
		
		lf1 = new ArrayList<Double>();
		lf2 = new ArrayList<Double>();
		String s1 = null,s2 = null,s = "";
		 	File file = new File("E:\\nju\\2014���챭\\in.txt");
		 	BufferedReader reader = null;
	        try {
	            //System.out.println("����Ϊ��λ��ȡ�ļ����ݣ�һ�ζ�һ���У�");
	            reader = new BufferedReader(new FileReader(file));
	            String tempString = null;
	            int line = 1;
	            // һ�ζ���һ�У�ֱ������nullΪ�ļ�����
	            while ((tempString = reader.readLine()) != null) {
	                // ��ʾ�к�
	                s = tempString.trim();
	                //System.out.println("line " + line + ": " + tempString);
	                line++;
	                String t[]=s.split("\t");
	                //System.out.println(t.length);
	                s1 = t[0];
                	s2 = t[1];
                	lf1.add(Double.parseDouble(s1));
                	lf2.add(Double.parseDouble(s2));
                	s = "";
	            }
	            reader.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if (reader != null) {
	                try {
	                    reader.close();
	                } catch (IOException e1) {
	                }
	            }
	        }
	        int size=lf1.size();  
	        Double[] f1=new Double[size];  
	        for(int i=0;i<lf1.size();i++){  
	            f1[i]=(Double)lf1.get(i);  
	        }  
	        size=lf2.size();  
	        Double[] f2=new Double[size];  
	        for(int i=0;i<lf2.size();i++){  
	            f2[i]=(Double)lf2.get(i);  
	        } 
		    System.out.println("f1���ȣ�" + f1.length + "; f2���ȣ�" + f2.length);
	    System.out.println("End of Input!");   
	    try {
			g = new getFlen();
		} catch (MWException e1) {
			// TODO �Զ����ɵ� catch ��
			e1.printStackTrace();
		}  //!!!!important
		Object[] a=new Object[2];
		a[0] = 1.1;a[1] = 2.2;
		/*********************************************************/
		/********************����Ĵ���ܹؼ�**************************/
		/*********************************************************/
		/*
		 * Ҫ��matlab����������������뽫����ת��MWNumericArray
		 */
		MWNumericArray b = null,c = null;   
		int[] dims1 = {f1.length, 1};  
        b = MWNumericArray.newInstance(dims1,   
           MWClassID.DOUBLE, MWComplexity.REAL);  
        for(int i = 1;i <= f1.length;i++){
        	b.set(i, f1[i - 1]);
        }
        int[] dims2 = {f1.length, 1};  
        c = MWNumericArray.newInstance(dims2,   
           MWClassID.DOUBLE, MWComplexity.REAL);
        for(int i = 1;i <= f2.length;i++){
        	c.set(i, f2[i - 1]);
        }
		/*********************************************************/
		/********************����Ĵ���ܹؼ�**************************/
		/*********************************************************/
		try {
			result = g.mygetFlen(2,b,c);
		} catch (MWException e1) {
			// TODO �Զ����ɵ� catch ��
			e1.printStackTrace();
		}
		//optimization_x,optimization_y,optimization_k
		x = Double.valueOf(String.valueOf(result[0]));
		y = Double.valueOf(String.valueOf(result[1]));
		System.out.println("result of getFlen: " + x + "; " + y);    
		System.out.println("End of getFlen");
	    
	    /*-----------------------------------*/
		long start1 = System.currentTimeMillis();
	    try {
			//Arbitrage_Main��������ʾ��
			//��һ���������ֱ�ʾ��Ҫ��õĽ�����������������3����ʾ������������x,y,k;���д2,��ֻ�ܵõ�x,y;
			//��ͬ
			m = new Arbitrage_Main();  //!!!!important
			
			result =m.Arbitrage_Main(3,b,c,stop_loss, stop_profit);
			//optimization_x,optimization_y,optimization_k
			x = Double.valueOf(String.valueOf(result[0]));
			y = Double.valueOf(String.valueOf(result[1]));
			k = Double.valueOf(String.valueOf(result[2]));
			System.out.println("x = " + x);   
			System.out.println("y = " + y);
			System.out.println("k = " + k);   
			System.out.println("End of Arbitrage_main");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception 1 catched!");
			System.out.println(e.toString());
		}
	    long end1 = System.currentTimeMillis();
	    System.out.println("Arbitrage_Main����ʱ�䣺" + (end1 - start1) + "����");//Ӧ����end - start
	    long start2 = System.currentTimeMillis();
		try {
			//Open��������ʾ��
			open_position = new Open(); //!!!!important
			x = 0.01;y = -0.06;k = 42; //�ο����� //����Arbitrage_main��������
			newprice1 = 91;newprice2 = 93;
			result =open_position.open(3,b,c,newprice1,newprice2,x,y,k);
		    //signal,buyprice1,saleprice1
			signal = Integer.valueOf(String.valueOf(result[0]));
			buyprice = Double.valueOf(String.valueOf(result[1]));
			saleprice = Double.valueOf(String.valueOf(result[2]));
			System.out.println("signal = " + signal);   
			System.out.println("buyprice = " + buyprice);
			System.out.println("saleprice = " + saleprice);   
			System.out.println("End of open");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception 2 catched!");
		}
		long end2 = System.currentTimeMillis();
	    System.out.println("Open����ʱ�䣺" + (end2 - start2) + "����");//Ӧ����end - start
	    long start3 = System.currentTimeMillis();
		try {
			//Close��������ʾ��
			close_position = new Close(); //!!!!important
			buyprice = 91;saleprice = 93;signal = 1; //�ο�����	
			result =close_position.close(3,b,c,
					newprice1,newprice2,buyprice,saleprice,k,signal,stop_loss,stop_profit);
			//signal,buyprice,saleprice
			signal = Integer.valueOf(String.valueOf(result[0]));
			buyprice = Double.valueOf(String.valueOf(result[1]));
			saleprice = Double.valueOf(String.valueOf(result[2]));
			System.out.println("signal = " + signal);   
			System.out.println("buyprice = " + buyprice);
			System.out.println("saleprice = " + saleprice);  
			System.out.println("End of close");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception 3 catched!");
		}
		long end3 = System.currentTimeMillis();
	    System.out.println("Close����ʱ�䣺" + (end3 - start3) + "����");//Ӧ����end - start
	    /*-----------------------------------*/
		//�ͷű�����Դ  
        MWArray.disposeArray(b);  
        MWArray.disposeArray(c);
		
	}//end of main
}//end of this class Example
