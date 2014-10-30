package edu.nju.treasuryArbitrage.logic.dataInterface2Matlab;

import Arbitrage_Main.Arbitrage_Main;
import Arbitrage_Main.Close;
import Arbitrage_Main.Open;

import com.mathworks.toolbox.javabuilder.*;
//引入java自带Matlab相关包
//导入程序员自己用matlab输出的jar包

public class DataInterface2Matlab {
		
	public DataInterface2Matlab(){
		
	}
	
	
	/*
	 * result为返回对象数组，各元素分别为x,y,k
	 */
	public Object[] Arbitrage_Main(MWNumericArray f1,MWNumericArray f2,double stop_loss,double stop_profit){
		Object[] result = null;
		Arbitrage_Main m = null;
		try {
			//Arbitrage_Main方法调用示例
			//第一个参数数字表示需要获得的结果个数，比如这里的3，表示获得三个结果，x,y,k;如果写2,则只能得到x,y;
			//下同
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
			//Open方法调用示例
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
			buyprice = 91;saleprice = 93;signal = 1; //参考输入	
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
