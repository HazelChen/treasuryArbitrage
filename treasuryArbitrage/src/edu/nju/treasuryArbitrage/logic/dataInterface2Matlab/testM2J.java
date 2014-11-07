package edu.nju.treasuryArbitrage.logic.dataInterface2Matlab;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import com.mathworks.toolbox.javabuilder.MWClassID;
import com.mathworks.toolbox.javabuilder.MWComplexity;
import com.mathworks.toolbox.javabuilder.MWNumericArray;

public class testM2J {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		DataInterface2Matlab dm = new DataInterface2Matlab();
		Object[] result = null;
		
		System.out.println("dm-x,y,k:" + dm.opt_x + "," + dm.opt_y + "," + dm.opt_k);
		//-------------输入数据------------
		ArrayList<Double> lf1 = null;
		ArrayList<Double> lf2 = null;//价格历史记录
		String s1 = null,s2 = null,s = "";
	 	File file = new File("in234.txt");
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
                //System.out.println("line " + line + ": " + s);
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
		/**
       //Arbitrage_Main调用示例   OK!
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
		
        File parafile =new File("para_xyk");
	    if(!parafile.exists()){
	    	try {
				parafile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        FileWriter fileWritter;
		try {
			fileWritter = new FileWriter(parafile.getName());
             BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
             bufferWritter.write(x + "\t" + y+ "\t" + k);
             bufferWritter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
        // System.out.println("Done");
		*/
		//Open调用示例   OK!
        System.out.println("Open ...");
		long start2 = System.currentTimeMillis();
		result = dm.Open(lf1, lf2, newprice1, newprice2, 0.39,-0.37,93.0);
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
		
		
		//Close调用示例  OK!
        System.out.println("Close ...");
		long start3 = System.currentTimeMillis();
		result = dm.Close(lf1, lf2, newprice1, newprice2,
				buyprice, saleprice, 93, signal, stop_loss, stop_profit);
		long end3 = System.currentTimeMillis();
		System.out.println("数据量:" + lf1.size());   
	    System.out.println("Close运行时间：" + (end3 - start3) + "毫秒");//应该是end - start
	    signal = Integer.valueOf(String.valueOf(result[0]));
		buyprice = Double.valueOf(String.valueOf(result[1]));
		saleprice = Double.valueOf(String.valueOf(result[2]));
		System.out.println("signal = " + signal);   
		System.out.println("buyprice = " + buyprice);
		System.out.println("saleprice = " + saleprice); 
		/****/
        
        
        ArrayList<Double> Llambda = null;
        Llambda = new ArrayList<Double>();
        Llambda.add(0.6);Llambda.add(0.2);
        
        ArrayList<Double> Lmarket_condition = null;
        Lmarket_condition = new ArrayList<Double>();
        Lmarket_condition.add(4.0);
        Lmarket_condition.add(0.05);
        Lmarket_condition.add(0.002);
        Lmarket_condition.add(0.028);
        //{4, 0.05, 0.002, 0.028};
       
        /****
		//Judge     ERR
        System.out.println(Lmarket_condition.toString());  
        System.out.println("Judge ...");
		long start4 = System.currentTimeMillis();
		 result = dm.Judge("TF1409", "TF1503", lf1, lf2, 91.0, 93.0, Llambda, 0, 0.0, -0.02, Lmarket_condition);
		long end4 = System.currentTimeMillis();  
	    System.out.println("Judge运行时间：" + (end4 - start4) + "毫秒");//应该是end - start
        if(result != null && result.length > 0)
        {
		    System.out.println(result[0]);  
			System.out.println(result[1]);
			System.out.println(result[2]);
			System.out.println(result[3]);
			System.out.println(result[4]);
			System.out.println(result[5]); 
			System.out.println(result[6]);
			System.out.println(result[7]);
        }
        ****/
        
        /****
		//BackTest    ERR
        System.out.println(Lmarket_condition.toString());  
        System.out.println("BackTest ...");
		long start5 = System.currentTimeMillis();
		 result = dm.BackTest(lf1, lf2, -0.02, 3, Lmarket_condition);
		long end5 = System.currentTimeMillis();
		System.out.println("数据量:" + lf1.size());   
	    System.out.println("BackTest运行时间：" + (end5 - start5) + "毫秒");//应该是end - start
        if(result != null && result.length > 0)
        {
		    System.out.println(result[0]);  
			System.out.println(result[1]);
			System.out.println(result[2]);
			System.out.println(result[3]);
			System.out.println(result[4]);
			System.out.println(result[5]); 
			System.out.println(result[6]);
			System.out.println(result[7]);
        }
        ***/
        
        /****
        //convert2ratio    OK!
        System.out.println("convert2ratio ...");
		long start6 = System.currentTimeMillis();
		double result2;
        result2 = dm.Convert2ratio(100, Lmarket_condition, 5);
        System.out.println(result2);  
        long end6 = System.currentTimeMillis();
	    System.out.println("BackTest运行时间：" + (end6 - start6) + "毫秒");//应该是end - start
        ****/
	}

}
