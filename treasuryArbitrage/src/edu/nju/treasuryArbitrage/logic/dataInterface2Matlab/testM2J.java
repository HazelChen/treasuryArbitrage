package edu.nju.treasuryArbitrage.logic.dataInterface2Matlab;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.mathworks.toolbox.javabuilder.MWClassID;
import com.mathworks.toolbox.javabuilder.MWComplexity;
import com.mathworks.toolbox.javabuilder.MWNumericArray;

public class testM2J {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		DataInterface2Matlab dm = new DataInterface2Matlab();
		Object[] result = null;
		double stop_loss = -1,stop_profit = 1,
				newprice1 = 91,newprice2 = 93;
		
		//-------------��������------------
		ArrayList<Double> lf1 = null;
		ArrayList<Double> lf2 = null;//�۸���ʷ��¼
		String s1 = null,s2 = null,s = "";
	 	File file = new File("E:\\nju\\2014���챭\\in.txt");
	 	BufferedReader reader = null;
	 	lf1 = new ArrayList<Double>();
		lf2 = new ArrayList<Double>();
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
		
		//--------------ת��matlab��Ҫ��������ʽ---------
        /*********************************************************/
		/********************����Ĵ���ܹؼ�**************************/
		/*********************************************************/
		/*
		 * Ҫ��matlab����������������뽫����ת��MWNumericArray
		 */
		MWNumericArray f1 = null,f2 = null;   
		int[] dims1 = {lf1.size(), 1};  
        f1 = MWNumericArray.newInstance(dims1,   
           MWClassID.DOUBLE, MWComplexity.REAL);  
        for(int i = 1;i <= lf1.size();i++){
        	f1.set(i, Double.valueOf(lf1.get(i - 1)));
        }
        int[] dims2 = {lf2.size(), 1};  
        f2 = MWNumericArray.newInstance(dims2,   
           MWClassID.DOUBLE, MWComplexity.REAL);
        for(int i = 1;i <= lf2.size();i++){
        	f2.set(i, Double.valueOf(lf2.get(i - 1)));
        }
		/*********************************************************/
		/********************����Ĵ���ܹؼ�**************************/
		/*********************************************************/
		//-----------------------------------------
		
       //Arbitrage_Main����ʾ��
        long start1 = System.currentTimeMillis();
		result = dm.Arbitrage_Main(f1, f2, stop_loss, stop_profit);
		long end1 = System.currentTimeMillis();
		System.out.println("������:" + lf1.size());   
	    System.out.println("Arbitrage_Main����ʱ�䣺" + (end1 - start1) + "����");//Ӧ����end - start
		double x,y,k;
		x = Double.valueOf(String.valueOf(result[0]));
		y = Double.valueOf(String.valueOf(result[1]));
		k = Double.valueOf(String.valueOf(result[2]));
		System.out.println("x = " + x);   
		System.out.println("y = " + y);
		System.out.println("k = " + k);  
		
		//Open����ʾ��
		long start2 = System.currentTimeMillis();
		result = dm.Open(f1, f2, newprice1, newprice2, x, y, k);
		long end2 = System.currentTimeMillis();
		System.out.println("������:" + lf1.size());   
	    System.out.println("Open����ʱ�䣺" + (end2 - start2) + "����");//Ӧ����end - start
	    double signal,buyprice,saleprice;
	    signal = Integer.valueOf(String.valueOf(result[0]));
		buyprice = Double.valueOf(String.valueOf(result[1]));
		saleprice = Double.valueOf(String.valueOf(result[2]));
		System.out.println("signal = " + signal);   
		System.out.println("buyprice = " + buyprice);
		System.out.println("saleprice = " + saleprice); 
		
		
		//Close����ʾ��
		long start3 = System.currentTimeMillis();
		result = dm.Close(f1, f2, newprice1, newprice2,
				buyprice, saleprice, k, signal, stop_loss, stop_profit);
		long end3 = System.currentTimeMillis();
		System.out.println("������:" + lf1.size());   
	    System.out.println("Close����ʱ�䣺" + (end3 - start3) + "����");//Ӧ����end - start
	    signal = Integer.valueOf(String.valueOf(result[0]));
		buyprice = Double.valueOf(String.valueOf(result[1]));
		saleprice = Double.valueOf(String.valueOf(result[2]));
		System.out.println("signal = " + signal);   
		System.out.println("buyprice = " + buyprice);
		System.out.println("saleprice = " + saleprice); 
	}

}
