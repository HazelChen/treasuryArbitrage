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
		
		//-------------输入数据------------
		ArrayList<Double> lf1 = null;
		ArrayList<Double> lf2 = null;//价格历史记录
		String s1 = null,s2 = null,s = "";
	 	File file = new File("E:\\nju\\2014花旗杯\\in234.txt");
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
                System.out.println("line " + line + ": " + s);
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
		double stop_loss = -1,stop_profit = 1,
				newprice1 = 91,newprice2 = 93;
        System.out.println("End of input!");
		
       //Arbitrage_Main调用示例
        System.out.println("Arbitrage_Main ...");
        long start1 = System.currentTimeMillis();
		result = dm.Arbitrage_Main(lf1, lf2, stop_loss, stop_profit);
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
        System.out.println("Open ...");
		long start2 = System.currentTimeMillis();
		result = dm.Open(lf1, lf2, newprice1, newprice2, x, y, k);
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
        System.out.println("Close ...");
		long start3 = System.currentTimeMillis();
		result = dm.Close(lf1, lf2, newprice1, newprice2,
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
