package Matlab2JAVA;
/*
 * 在要调用matlab的包里需要导入相关的包
 */
import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.mathworks.toolbox.javabuilder.*;//引入java自带Matlab相关包

import Arbitrage_Main.*;//导入程序员自己用matlab输出的jar包

/*
 * 冯雪岩的Arbitrage_Main.jar里包含的三个函数及其参数表, []中为返回值
 * 使用具体情况参见群里文件：matlab调用说明.doc
 * 该函数功能：
 * 计算最优参数x,y,k  该函数每天调用一次,使用所有历史记录
 * function [optimization_x,optimization_y,optimization_k]=Arbitrage_Main(f1,f2,stop_loss,stop_profit)
 * 
 * 该函数功能：
 * 计算是否平仓的函数，使用前一日开盘到现在前一秒的记录（每分钟），在建仓后每分钟（或每秒）调用一次，平仓结束后不再调用
 * %signal当前交易状态（状态，取-3,-2，-1,0,1,2,3）
 * % 输出：signal按照当前的最新价格，是否应当交易。signal=1，正向套利建仓；
 * %signal=-1，反向套利建仓；signal=0，不交易；signal=2，止盈平仓；signal=-2,止
 * %损平仓,signal=3，正向套利平仓；signal=-3，反向套利平仓；
 * function [signal,buyprice1,saleprice1]=close(f1,f2,newprice1,newprice2,buyprice,saleprice,k,signal,stop_loss,stop_profit)
 * 
 * 该函数功能：
 * 计算是否建仓，使用前一日开盘到现在前一秒的记录（每分钟），在建仓前每分钟（或每秒）调用一次，建仓后不再调用
 * %signal当前交易状态（状态，取-2，-1,0,1,2）
 * % 输出：signal按照当前的最新价格，是否应当交易。signal=1，正向套利建仓；
 * %signal=-1，反向套利建仓；signal=0，不交易；signal=2，止盈平仓；signal=-2,止
 * %损平仓。
 * function [signal,buyprice,saleprice]=open(f1,f2,newprice1,newprice2,x,y,k)
 * 
 * 汪修宇的jar包的包含的函数及其参数表
 * 
 * 
 */

public class Example {
	
	public static void main(String[] args) {

		//matlab导出的jar包里包含的class
		getFlen g = null;		//test
		//冯雪岩的Arbitrage_Main.jar里包含三个类
		Arbitrage_Main m = null;
		Open open_position = null;
		Close close_position = null;
		//汪修宇的jar包里包含个类
		
		//用到的量
		ArrayList<Double> lf1;
		ArrayList<Double> lf2;//价格历史记录
		int stop_loss = -1,stop_profit = 1;
		double newprice1=91,newprice2=92,x=0,y=0,k = 2,
				buyprice = 0,saleprice = 0;
		int signal = 0;
		
		Object[] result = null; // 用于保存计算结果
		
		lf1 = new ArrayList<Double>();
		lf2 = new ArrayList<Double>();
		String s1 = null,s2 = null,s = "";
		 	File file = new File("E:\\nju\\2014花旗杯\\in.txt");
		 	BufferedReader reader = null;
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
		    System.out.println("f1长度：" + f1.length + "; f2长度：" + f2.length);
	    System.out.println("End of Input!");   
	    try {
			g = new getFlen();
		} catch (MWException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}  //!!!!important
		Object[] a=new Object[2];
		a[0] = 1.1;a[1] = 2.2;
		/*********************************************************/
		/********************下面的代码很关键**************************/
		/*********************************************************/
		/*
		 * 要给matlab函数传数组参数必须将数组转成MWNumericArray
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
		/********************上面的代码很关键**************************/
		/*********************************************************/
		try {
			result = g.mygetFlen(2,b,c);
		} catch (MWException e1) {
			// TODO 自动生成的 catch 块
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
			//Arbitrage_Main方法调用示例
			//第一个参数数字表示需要获得的结果个数，比如这里的3，表示获得三个结果，x,y,k;如果写2,则只能得到x,y;
			//下同
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
	    System.out.println("Arbitrage_Main运行时间：" + (end1 - start1) + "毫秒");//应该是end - start
	    long start2 = System.currentTimeMillis();
		try {
			//Open方法调用示例
			open_position = new Open(); //!!!!important
			x = 0.01;y = -0.06;k = 42; //参考输入 //利用Arbitrage_main计算所得
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
	    System.out.println("Open运行时间：" + (end2 - start2) + "毫秒");//应该是end - start
	    long start3 = System.currentTimeMillis();
		try {
			//Close方法调用示例
			close_position = new Close(); //!!!!important
			buyprice = 91;saleprice = 93;signal = 1; //参考输入	
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
	    System.out.println("Close运行时间：" + (end3 - start3) + "毫秒");//应该是end - start
	    /*-----------------------------------*/
		//释放本地资源  
        MWArray.disposeArray(b);  
        MWArray.disposeArray(c);
		
	}//end of main
}//end of this class Example
