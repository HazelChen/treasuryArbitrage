package Matlab2JAVA;
import com.mathworks.toolbox.javabuilder.*;//引入Matlab相关包
import Arbitrage_Main.*;//导入matlab输出的jar包

/*
 * 冯雪岩的Arbitrage_Main.jar里包含的三个函数及其参数表, []中为返回值
 * 
 * 该函数功能：
 * 计算最优参数x,y,k
 * function [optimization_x,optimization_y,optimization_k]=Arbitrage_Main(f1,f2,stop_loss,stop_profit)
 * 
 * 该函数功能：
 * %signal当前交易状态（状态，取-3,-2，-1,0,1,2,3）
 * % 输出：signal按照当前的最新价格，是否应当交易。signal=1，正向套利建仓；
 * %signal=-1，反向套利建仓；signal=0，不交易；signal=2，止盈平仓；signal=-2,止
 * %损平仓,signal=3，正向套利平仓；signal=-3，反向套利平仓；
 * function [signal,buyprice1,saleprice1]=close(f1,f2,newprice1,newprice2,buyprice,saleprice,k,signal,stop_loss,stop_profit)
 * 
 * 该函数功能：
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
		//冯雪岩的Arbitrage_Main.jar里包含三个类
		Arbitrage_Main m = null;
		Open open_position = null;
		Close close_position = null;
		//汪修宇的jar包里包含个类
		
		//用到的量
		double f1 = 90,f2 = 91;//价格历史记录
		int stop_loss = -1,stop_profit = 1;
		double newprice1=91,newprice2=92,x=90,y=90.1,k = 2,buyprice = 90,saleprice = 100,signal = 0;
		
		Object[] result = null; // 用于保存计算结果
		
		try {
			//Arbitrage_Main方法调用示例
			//第一个参数数字表示需要获得的结果个数，比如这里的3，表示获得三个结果，x,y,k;如果写2,则只能得到x,y;
			//下同
			m = new Arbitrage_Main();  //!!!!important
			result =m.Arbitrage_Main(2,f1,f2,stop_loss, stop_profit);
			System.out.println(result[0]);   //optimization_x,optimization_y,optimization_k
			System.out.println(result[1]);
//			System.out.println(result[2]);   //m文件有问题
			System.out.println("End of 1");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception 1 catched!");
		}

		try {
			//Open方法调用示例
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
			//Close方法调用示例
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
