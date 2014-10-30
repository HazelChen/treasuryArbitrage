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
		// TODO 自动生成的方法存根
		DataInterface2Matlab dm = new DataInterface2Matlab();
		Object[] result = null;
		double stop_loss = -1,stop_profit = 1,
				newprice1 = 91,newprice2 = 93;
		
		//-------------输入数据------------
		ArrayList<Double> lf1 = null;
		ArrayList<Double> lf2 = null;//价格历史记录
		String s1 = null,s2 = null,s = "";
	 	File file = new File("E:\\nju\\2014花旗杯\\in.txt");
	 	BufferedReader reader = null;
	 	lf1 = new ArrayList<Double>();
		lf2 = new ArrayList<Double>();
        try {
            //System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
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
		
		//--------------转成matlab需要的数据形式---------
        /*********************************************************/
		/********************下面的代码很关键**************************/
		/*********************************************************/
		/*
		 * 要给matlab函数传数组参数必须将数组转成MWNumericArray
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
		/********************上面的代码很关键**************************/
		/*********************************************************/
		//-----------------------------------------
		
       //Arbitrage_Main调用示例
        long start1 = System.currentTimeMillis();
		result = dm.Arbitrage_Main(f1, f2, stop_loss, stop_profit);
		long end1 = System.currentTimeMillis();
		System.out.println("数据量:" + lf1.size());   
	    System.out.println("Arbitrage_Main运行时间：" + (end1 - start1) + "毫秒");//应该是end - start
		double x,y,k;
		x = Double.valueOf(String.valueOf(result[0]));
		y = Double.valueOf(String.valueOf(result[1]));
		k = Double.valueOf(String.valueOf(result[2]));
		System.out.println("x = " + x);   
		System.out.println("y = " + y);
		System.out.println("k = " + k);  
		
		//Open调用示例
		long start2 = System.currentTimeMillis();
		result = dm.Open(f1, f2, newprice1, newprice2, x, y, k);
		long end2 = System.currentTimeMillis();
		System.out.println("数据量:" + lf1.size());   
	    System.out.println("Open运行时间：" + (end2 - start2) + "毫秒");//应该是end - start
	    double signal,buyprice,saleprice;
	    signal = Integer.valueOf(String.valueOf(result[0]));
		buyprice = Double.valueOf(String.valueOf(result[1]));
		saleprice = Double.valueOf(String.valueOf(result[2]));
		System.out.println("signal = " + signal);   
		System.out.println("buyprice = " + buyprice);
		System.out.println("saleprice = " + saleprice); 
		
		
		//Close调用示例
		long start3 = System.currentTimeMillis();
		result = dm.Close(f1, f2, newprice1, newprice2,
				buyprice, saleprice, k, signal, stop_loss, stop_profit);
		long end3 = System.currentTimeMillis();
		System.out.println("数据量:" + lf1.size());   
	    System.out.println("Close运行时间：" + (end3 - start3) + "毫秒");//应该是end - start
	    signal = Integer.valueOf(String.valueOf(result[0]));
		buyprice = Double.valueOf(String.valueOf(result[1]));
		saleprice = Double.valueOf(String.valueOf(result[2]));
		System.out.println("signal = " + signal);   
		System.out.println("buyprice = " + buyprice);
		System.out.println("saleprice = " + saleprice); 
	}

}
