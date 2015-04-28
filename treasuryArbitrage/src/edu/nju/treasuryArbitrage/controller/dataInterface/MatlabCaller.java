package edu.nju.treasuryArbitrage.controller.dataInterface;

import java.util.ArrayList;

import Arbitrage_Main.*;

import com.mathworks.toolbox.javabuilder.*;


public class MatlabCaller {
	//-------------------------------Server---------------------------------------
	Arbitrage_Main mFXY;
	Arbitrage_Main1 mWXY;
	Arbitrage_Main2 mDJ;
	//-------------------------------Client---------------------------------------
	Open open_position;
	Close close_position;
	Open1 open_position1;
	Close1 close_position1;
	Open2 open_position2;
	Close2 close_position2;
	public MatlabCaller(){
		try {
			mFXY = new Arbitrage_Main();
			mWXY = new Arbitrage_Main1();
			mDJ = new Arbitrage_Main2();
			
			open_position = new Open(); 
			close_position = new Close(); 
			open_position1 = new Open1(); 
			close_position1 = new Close1(); 
			open_position2 = new Open2(); 
			close_position2 = new Close2(); 		
			
		} catch (MWException e3) {
			// 
			e3.printStackTrace();
		}
	}
	
	//-------------------------------Server---------------------------------------
	/*
	 * result为返回对象数组，各元素分别为x,y,k
	 */
	public Object[] Arbitrage_Main(ArrayList<Double>  Lf1,ArrayList<Double>  Lf2,double stop_loss,double stop_profit){
		Object[] result = null;
		MWNumericArray f1 = null,f2 = null,mwSL = null,mwSP = null;   
		int[] dims1 = {Lf1.size(), 1};  
        f1 = MWNumericArray.newInstance(dims1,   
           MWClassID.DOUBLE, MWComplexity.REAL);  
        for(int i = 1;i <= Lf1.size();i++){
        	f1.set(i, Double.valueOf(Lf1.get(i - 1)));
        }
        int[] dims2 = {Lf2.size(), 1};  
        f2 = MWNumericArray.newInstance(dims2,   
           MWClassID.DOUBLE, MWComplexity.REAL);
        for(int i = 1;i <= Lf2.size();i++){
        	f2.set(i, Double.valueOf(Lf2.get(i - 1)));
        }
		mwSL =  new MWNumericArray(stop_loss,MWClassID.DOUBLE);
		mwSP =  new MWNumericArray(stop_profit,MWClassID.DOUBLE); 
		try {
			//Arbitrage_Main方法调用示例
			//第一个参数数字表示需要获得的结果个数，比如这里的3，表示获得三个结果，x,y,k;如果写2,则只能得到x,y;
			//下同
			result =mFXY.Arbitrage_Main(3,f1,f2,mwSL, mwSP);
			//optimization_x,optimization_y,optimization_k
			//-------------------------------Server---------------------------------------
			double x,y,k;
			x = Double.valueOf(String.valueOf(result[0]));
			y = Double.valueOf(String.valueOf(result[1]));
			k = Double.valueOf(String.valueOf(result[2]));		
			//insert into sql
			
		} catch (Exception e) {
			System.out.println("Arbitrage_Main Exception catched!");
			System.out.println(e.toString());
		}	
		MWArray.disposeArray(f1);
		MWArray.disposeArray(f2);
		return result;
	}
	
	/*
	 * function [ol1 ol2]=Arbitrage_Main_W(f1,f2,stop_loss,stop_profit,trade_cost)
	 * % input: f1 f2 为输入的历史数据，stop_loss为止损点，stop_profit为止盈点%
	 * % output: optimization_lambda为最优的策略参数  [0.6 0.2]
	 */
	public Object[] Arbitrage_Main1(ArrayList<Double>  Lf1,ArrayList<Double>  Lf2,double stopLoss,double stopProfit,
			double TradeCost){
		Object[] result = null;
		MWNumericArray f1 = null,f2 = null,mwSL = null,mwSP = null,mwTC = null;   
		int[] dims1 = {Lf1.size(), 1};  
        f1 = MWNumericArray.newInstance(dims1,   
           MWClassID.DOUBLE, MWComplexity.REAL);  
        for(int i = 1;i <= Lf1.size();i++){
        	f1.set(i, Double.valueOf(Lf1.get(i - 1)));
        }
        int[] dims2 = {Lf2.size(), 1};  
        f2 = MWNumericArray.newInstance(dims2,   
           MWClassID.DOUBLE, MWComplexity.REAL);
        for(int i = 1;i <= Lf2.size();i++){
        	f2.set(i, Double.valueOf(Lf2.get(i - 1)));
        }

		mwTC =  new MWNumericArray(TradeCost,MWClassID.DOUBLE); 
		mwSL =  new MWNumericArray(stopLoss,MWClassID.DOUBLE); 
		mwSP =  new MWNumericArray(stopProfit,MWClassID.DOUBLE); 
		
		try {
			result =mWXY.Arbitrage_Main_W(2,f1,f2,mwSL, mwSP,mwTC);
			//-------------------------------Server---------------------------------------
			double l1,l2;
			l1 = Double.valueOf(String.valueOf(result[0]));
			l2 = Double.valueOf(String.valueOf(result[1]));	
			//insert into sql
			
		} catch (Exception e) {
			System.out.println("Arbitrage_Main_W Exception catched!");
			System.out.println(e.toString());
		}	
		MWArray.disposeArray(f1);
		MWArray.disposeArray(f2);
		return result;
	}
	/*
	 * function [ optimization_K optimization_T max_pay num loss win ] = Arbitrage_Main_D( f1,f2,stop_loss,stop_profit)
	 * %Arbitrage_Main: 根据历史数据确定参数的最优值
	 * %   Detailed explanation goes here
	 * %   Input: f1  近月国债期货价格
	 * %          f2  远月国债期货价格
	 * %          stop_loss 止损点
	 * %          stop_profit 止盈点
	 * %   Output:  K 建仓点,K倍标准差
	 * %            Optimization_T 最优移动平均天数
	 * %            trade   [总收益 单词最大损失 总交易次数 盈利次数比例 亏损次数比例]
	 */
	public Object[] Arbitrage_Main2(ArrayList<Double>  Lf1,ArrayList<Double>  Lf2,double stopLoss,double stopProfit){
		Object[] result = null;
		MWNumericArray f1 = null,f2 = null,mwSL = null,mwSP = null;   
		int[] dims1 = {Lf1.size(), 1};  
        f1 = MWNumericArray.newInstance(dims1,   
           MWClassID.DOUBLE, MWComplexity.REAL);  
        for(int i = 1;i <= Lf1.size();i++){
        	f1.set(i, Double.valueOf(Lf1.get(i - 1)));
        }
        int[] dims2 = {Lf2.size(), 1};  
        f2 = MWNumericArray.newInstance(dims2,   
           MWClassID.DOUBLE, MWComplexity.REAL);
        for(int i = 1;i <= Lf2.size();i++){
        	f2.set(i, Double.valueOf(Lf2.get(i - 1)));
        }

		mwSL =  new MWNumericArray(stopLoss,MWClassID.DOUBLE); 
		mwSP =  new MWNumericArray(stopProfit,MWClassID.DOUBLE); 
		
		try {
			result =mDJ.Arbitrage_Main_D(6,f1,f2,mwSL, mwSP);
			//-------------------------------Server---------------------------------------
			double optimization_K,optimization_T,max_pay,num,loss,win;
			optimization_K = Double.valueOf(String.valueOf(result[0]));
			optimization_T = Double.valueOf(String.valueOf(result[1]));
			max_pay = Double.valueOf(String.valueOf(result[2]));
			num = Double.valueOf(String.valueOf(result[3]));
			loss = Double.valueOf(String.valueOf(result[4]));
			win = Double.valueOf(String.valueOf(result[5]));
			//insert into sql
			
		} catch (Exception e) {
			System.out.println("Arbitrage_Main_D Exception catched!");
			System.out.println(e.toString());
		}	
		MWArray.disposeArray(f1);
		MWArray.disposeArray(f2);
		return result;
	}
	
	
	
	//-------------------------------Client---------------------------------------
	/*
	 * %l_signal当前交易状态（状态，取-2，-1,0,1,2）
	 *	% 输出：signal按照当前的最新价格，是否应当交易。signal=1，正向套利建仓；
	 *	%signal=-1，反向套利建仓；signal=0，不交易；signal=2，止盈平仓；signal=-2,止
	 *	%损平仓。
	 * result {signal,buyprice,saleprice}
	 */
	public Object[] Open(ArrayList<Double>  Lf1,ArrayList<Double>  Lf2,
			double newprice1,double newprice2,double x,double y,double k){
		
		Object[] result = null;
		MWNumericArray f1 = null,f2 = null,
				mwnewprice1 = null,mwnewprice2 = null,
				mwx = null,mwy = null,mwk = null;   
		int[] dims1 = {Lf1.size(), 1};  
        f1 = MWNumericArray.newInstance(dims1,   
           MWClassID.DOUBLE, MWComplexity.REAL);  
        for(int i = 1;i <= Lf1.size();i++){
        	f1.set(i, Double.valueOf(Lf1.get(i - 1)));
        }
        int[] dims2 = {Lf2.size(), 1};  
        f2 = MWNumericArray.newInstance(dims2,   
           MWClassID.DOUBLE, MWComplexity.REAL);
        for(int i = 1;i <= Lf2.size();i++){
        	f2.set(i, Double.valueOf(Lf2.get(i - 1)));
        }
        mwnewprice1 = new MWNumericArray(newprice1,MWClassID.DOUBLE);
        mwnewprice2 = new MWNumericArray(newprice2,MWClassID.DOUBLE);
		mwx =  new MWNumericArray(x,MWClassID.DOUBLE);
		mwy =  new MWNumericArray(y,MWClassID.DOUBLE);
		mwk =  new MWNumericArray(k,MWClassID.DOUBLE);  
		try {
		    result =open_position.open(3,f1,f2,mwnewprice1,mwnewprice2,mwx,mwy,mwk);
		    //signal,buyprice1,saleprice1
		} catch (Exception e) {
			System.out.println("Open Exception catched!");
		}
		MWArray.disposeArray(f1);
		MWArray.disposeArray(f2);
		return result;
	}
	
	/*
	 * %l_signal当前交易状态（状态，取-3,-2，-1,0,1,2,3）
	 * % 输出：signal按照当前的最新价格，是否应当交易。signal=1，正向套利建仓；
	 * %signal=-1，反向套利建仓；signal=0，不交易；signal=2，止盈平仓；signal=-2,止
	 * %损平仓,signal=3，正向套利平仓；signal=-3，反向套利平仓；
	 * result {signal,buyprice,saleprice}
	 */
	public Object[] Close(ArrayList<Double> Lf1,ArrayList<Double>  Lf2,
			double newprice1,double newprice2,double buyprice,double saleprice,
			double k,double signal,double stop_loss,double stop_profit){
		Object[] result = null;
		MWNumericArray f1 = null,f2 = null,mwsignal = null,mwstop_loss = null,mwstop_profit = null,
				mwnewprice1 = null,mwnewprice2 = null,
				mwbuyprice = null,mwsaleprice = null,mwk = null;  
		int[] dims1 = {Lf1.size(), 1};  
        f1 = MWNumericArray.newInstance(dims1,   
           MWClassID.DOUBLE, MWComplexity.REAL);  
        for(int i = 1;i <= Lf1.size();i++){
        	f1.set(i, Double.valueOf(Lf1.get(i - 1)));
        }
        int[] dims2 = {Lf2.size(), 1};  
        f2 = MWNumericArray.newInstance(dims2,   
           MWClassID.DOUBLE, MWComplexity.REAL);
        for(int i = 1;i <= Lf2.size();i++){
        	f2.set(i, Double.valueOf(Lf2.get(i - 1)));
        }
        mwnewprice1 = new MWNumericArray(newprice1,MWClassID.DOUBLE);
        mwnewprice2 = new MWNumericArray(newprice2,MWClassID.DOUBLE);
        mwbuyprice =  new MWNumericArray(buyprice,MWClassID.DOUBLE);
        mwsaleprice =  new MWNumericArray(saleprice,MWClassID.DOUBLE);
		mwk =  new MWNumericArray(k,MWClassID.DOUBLE);  
		mwsignal =  new MWNumericArray(signal,MWClassID.DOUBLE);  
		mwstop_loss =  new MWNumericArray(stop_loss,MWClassID.DOUBLE);  
		mwstop_profit =  new MWNumericArray(stop_profit,MWClassID.DOUBLE);  
		try{
			result =close_position.close(3,f1,f2,
					mwnewprice1,mwnewprice2,mwbuyprice,mwsaleprice,mwk,mwsignal,mwstop_loss,mwstop_profit);
			//signal,buyprice,saleprice
		} catch (Exception e) {
			System.out.println("Close Exception catched!");
		}
		MWArray.disposeArray(f1);
		MWArray.disposeArray(f2);
		return result;
	}

	/*
	 * function [signal,buyprice,saleprice]=open1(f1,f2,newprice1,newprice2,lambda)
	 * % 判断是否需要开仓
	 * % input:f1 f2 历史价格，newprice1 newprice2当前的最新价格，lambda策略参数
	 * % output: signal 买入开仓信号， buyprice买入的合约价格，saleprice卖出的合约价格
	 */
	public Object[] Open1(ArrayList<Double>  Lf1,ArrayList<Double>  Lf2,
			double newprice1,double newprice2,ArrayList<Double> Lambda){
		
		Object[] result = null;
		MWNumericArray f1 = null,f2 = null,
				mwnewprice1 = null,mwnewprice2 = null,
				mwLambda = null;   
		int[] dims1 = {Lf1.size(), 1};  
        f1 = MWNumericArray.newInstance(dims1,   
           MWClassID.DOUBLE, MWComplexity.REAL);  
        for(int i = 1;i <= Lf1.size();i++){
        	f1.set(i, Double.valueOf(Lf1.get(i - 1)));
        }
        int[] dims2 = {Lf2.size(), 1};  
        f2 = MWNumericArray.newInstance(dims2,   
           MWClassID.DOUBLE, MWComplexity.REAL);
        for(int i = 1;i <= Lf2.size();i++){
        	f2.set(i, Double.valueOf(Lf2.get(i - 1)));
        }
        mwnewprice1 = new MWNumericArray(newprice1,MWClassID.DOUBLE);
        mwnewprice2 = new MWNumericArray(newprice2,MWClassID.DOUBLE);
        
		int[] dims3 = {Lambda.size(), 1};  
		mwLambda = MWNumericArray.newInstance(dims3,   
           MWClassID.DOUBLE, MWComplexity.REAL);  
        for(int i = 1;i <= Lambda.size();i++){
        	mwLambda.set(i, Double.valueOf(Lambda.get(i - 1)));
        }
		try {
		    result =open_position1.open1(3,f1,f2,mwnewprice1,mwnewprice2,mwLambda);
		    //signal,buyprice1,saleprice1
		} catch (Exception e) {
			System.out.println("Open1 Exception catched!");
		}
		MWArray.disposeArray(f1);
		MWArray.disposeArray(f2);
		return result;
	}
	
	/*
	 * function [signal1,buyprice1,saleprice1,pay]=close1(f1,f2,newprice1,newprice2,buyprice,saleprice,
	 *     lambda,state,stop_loss,stop_profit,trade_cost)
	 * % 判断是否需要平仓 
	 * % input: f1 f2当前的历史数据 newprice1 newprice2当前的最新价格 buyprice saleprice 买入交易时的真实买价和卖价 
	 * % input: lambda策略参数，signal当前的信号值/持仓方向，stop_profit止盈点 stop_loos止损点
	 * % output: signal卖出信号，buyprice1应当买入的合约价格，saleprice1应当卖出的合约价格
	 */
	public Object[] Close1(ArrayList<Double> Lf1,ArrayList<Double>  Lf2,
			double newprice1,double newprice2,double buyprice,double saleprice,
			ArrayList<Double> Lambda,double signal,double stop_loss,double stop_profit){
		Object[] result = null;
		MWNumericArray f1 = null,f2 = null,mwsignal = null,mwstop_loss = null,mwstop_profit = null,
				mwnewprice1 = null,mwnewprice2 = null,
				mwbuyprice = null,mwsaleprice = null,mwLambda = null;  
		int[] dims1 = {Lf1.size(), 1};  
        f1 = MWNumericArray.newInstance(dims1,   
           MWClassID.DOUBLE, MWComplexity.REAL);  
        for(int i = 1;i <= Lf1.size();i++){
        	f1.set(i, Double.valueOf(Lf1.get(i - 1)));
        }
        int[] dims2 = {Lf2.size(), 1};  
        f2 = MWNumericArray.newInstance(dims2,   
           MWClassID.DOUBLE, MWComplexity.REAL);
        for(int i = 1;i <= Lf2.size();i++){
        	f2.set(i, Double.valueOf(Lf2.get(i - 1)));
        }
        int[] dims3 = {Lambda.size(), 1};  
		mwLambda = MWNumericArray.newInstance(dims3,   
           MWClassID.DOUBLE, MWComplexity.REAL);  
        for(int i = 1;i <= Lambda.size();i++){
        	mwLambda.set(i, Double.valueOf(Lambda.get(i - 1)));
        }
        mwnewprice1 = new MWNumericArray(newprice1,MWClassID.DOUBLE);
        mwnewprice2 = new MWNumericArray(newprice2,MWClassID.DOUBLE);
        mwbuyprice =  new MWNumericArray(buyprice,MWClassID.DOUBLE);
        mwsaleprice =  new MWNumericArray(saleprice,MWClassID.DOUBLE);  
		mwsignal =  new MWNumericArray(signal,MWClassID.DOUBLE);  
		mwstop_loss =  new MWNumericArray(stop_loss,MWClassID.DOUBLE);  
		mwstop_profit =  new MWNumericArray(stop_profit,MWClassID.DOUBLE);  
		try{
			result =close_position1.close1(3,f1,f2,
					mwnewprice1,mwnewprice2,mwbuyprice,mwsaleprice,mwLambda,mwsignal,mwstop_loss,mwstop_profit);
			//signal,buyprice,saleprice
		} catch (Exception e) {
			System.out.println("Close1 Exception catched!");
		}
		MWArray.disposeArray(f1);
		MWArray.disposeArray(f2);
		return result;
	}

	/*
	 * function [ signal buyprice saleprice ] = open2( f1,f2,newprice1,newprice2,K,T)
	 * %open 根据参数和价格决定正负建仓信号
	 * %   Input: f1  近月国债期货价格
	 * %          f2  远月国债期货价格
	 * %          newprice1  国债期货即时价格（相对近月）
	 * %          newprice2  国债期货即时价格（相对远月）
	 * %          K   建仓参数
	 * %          T   移动平均天数
	 * %   Output:  signal 建仓信号  正向建仓1  反向建仓-1  不减仓0
	 * %            buyprice  买价
	 * %            saleprice 卖价
	 */
	public Object[] Open2(ArrayList<Double>  Lf1,ArrayList<Double>  Lf2,
			double newprice1,double newprice2,double k,double T){
		
		Object[] result = null;
		MWNumericArray f1 = null,f2 = null,
				mwnewprice1 = null,mwnewprice2 = null,
				mwT = null,mwk = null;   
		int[] dims1 = {Lf1.size(), 1};  
        f1 = MWNumericArray.newInstance(dims1,   
           MWClassID.DOUBLE, MWComplexity.REAL);  
        for(int i = 1;i <= Lf1.size();i++){
        	f1.set(i, Double.valueOf(Lf1.get(i - 1)));
        }
        int[] dims2 = {Lf2.size(), 1};  
        f2 = MWNumericArray.newInstance(dims2,   
           MWClassID.DOUBLE, MWComplexity.REAL);
        for(int i = 1;i <= Lf2.size();i++){
        	f2.set(i, Double.valueOf(Lf2.get(i - 1)));
        }
        mwnewprice1 = new MWNumericArray(newprice1,MWClassID.DOUBLE);
        mwnewprice2 = new MWNumericArray(newprice2,MWClassID.DOUBLE);
		mwT =  new MWNumericArray(T,MWClassID.DOUBLE);
		mwk =  new MWNumericArray(k,MWClassID.DOUBLE);  
		try {
		    result =open_position2.open2(3,f1,f2,mwnewprice1,mwnewprice2,mwk,mwT);
		    //signal,buyprice1,saleprice1
		} catch (Exception e) {
			System.out.println("Open2 Exception catched!");
		}
		MWArray.disposeArray(f1);
		MWArray.disposeArray(f2);
		return result;
	}
	
	/*
	 * function [ signal1 buyprice1 saleprice1 pay ] = close2( f1,f2,newprice1,newprice2,buyprice,saleprice,signal,
	 * K,T,stop_loss,stop_profit)
	 * %close2 根据参数和价格决定平仓信号
	 * %   Detailed explanation goes here
	 * %   signal1: 平仓信号  保持正向持仓1  保持反向持仓-1  平仓止损-2  平仓止盈2  正向平仓套利3  反向平仓套利-3
	 */
	public Object[] Close2(ArrayList<Double> Lf1,ArrayList<Double>  Lf2,
			double newprice1,double newprice2,double buyprice,double saleprice,
			double signal,double k,double T,double stop_loss,double stop_profit){
		Object[] result = null;
		MWNumericArray f1 = null,f2 = null,mwsignal = null,mwstop_loss = null,mwstop_profit = null,
				mwnewprice1 = null,mwnewprice2 = null,
				mwbuyprice = null,mwsaleprice = null,mwk = null,mwT = null;  
		int[] dims1 = {Lf1.size(), 1};  
        f1 = MWNumericArray.newInstance(dims1,   
           MWClassID.DOUBLE, MWComplexity.REAL);  
        for(int i = 1;i <= Lf1.size();i++){
        	f1.set(i, Double.valueOf(Lf1.get(i - 1)));
        }
        int[] dims2 = {Lf2.size(), 1};  
        f2 = MWNumericArray.newInstance(dims2,   
           MWClassID.DOUBLE, MWComplexity.REAL);
        for(int i = 1;i <= Lf2.size();i++){
        	f2.set(i, Double.valueOf(Lf2.get(i - 1)));
        }
        mwnewprice1 = new MWNumericArray(newprice1,MWClassID.DOUBLE);
        mwnewprice2 = new MWNumericArray(newprice2,MWClassID.DOUBLE);
        mwbuyprice =  new MWNumericArray(buyprice,MWClassID.DOUBLE);
        mwsaleprice =  new MWNumericArray(saleprice,MWClassID.DOUBLE);
		mwk =  new MWNumericArray(k,MWClassID.DOUBLE);
		mwT =  new MWNumericArray(T,MWClassID.DOUBLE);  
		mwsignal =  new MWNumericArray(signal,MWClassID.DOUBLE);  
		mwstop_loss =  new MWNumericArray(stop_loss,MWClassID.DOUBLE);  
		mwstop_profit =  new MWNumericArray(stop_profit,MWClassID.DOUBLE);  
		try{
			result =close_position2.close2(3,f1,f2,
					mwnewprice1,mwnewprice2,mwbuyprice,mwsaleprice,mwsignal,mwk,mwT,mwstop_loss,mwstop_profit);
			//signal,buyprice,saleprice
		} catch (Exception e) {
			System.out.println("Close Exception catched!");
		}
		MWArray.disposeArray(f1);
		MWArray.disposeArray(f2);
		return result;
	}

	
	
	
	/*
	 * [signal long_name short_name long short current_state current_trade_return units_time_return]
	 * =judge(name1,name2,f1,f2,new_price1,new_price2,lambda,last_time_state,last_trade_return,spoint,market_condition,sigma_method)
	 * 	 */
/*	public Object[] Judge(String name1,String name2,ArrayList<Double> Lf1,ArrayList<Double> Lf2,
			double new_price1,double new_price2,
			ArrayList<Double> Llambda,int last_time_state, double last_trade_return,double spoint,
			ArrayList<Double> Lmarket_condition){
		Object[] result = null;
		Judge J = null;
		MWNumericArray f1 = null,f2 = null,lambda = null,market_condition = null;   
		int[] dims1 = {Lf1.size(), 1};  
        f1 = MWNumericArray.newInstance(dims1,   
           MWClassID.DOUBLE, MWComplexity.REAL);  
        for(int i = 1;i <= Lf1.size();i++){
        	f1.set(i, Double.valueOf(Lf1.get(i - 1)));
        }
        int[] dims2 = {Lf2.size(), 1};  
        f2 = MWNumericArray.newInstance(dims2,   
           MWClassID.DOUBLE, MWComplexity.REAL);
        for(int i = 1;i <= Lf2.size();i++){
        	f2.set(i, Double.valueOf(Lf2.get(i - 1)));
        }
        int[] dims3 = {2, 1};  
        lambda = MWNumericArray.newInstance(dims3,   
                MWClassID.DOUBLE, MWComplexity.REAL);
        lambda.set(1, Double.valueOf(Llambda.get(0)));
        lambda.set(2, Double.valueOf(Llambda.get(1)));
        int[] dims4 = {4, 1};  
        market_condition = MWNumericArray.newInstance(dims4,   
                MWClassID.DOUBLE, MWComplexity.REAL);
        market_condition.set(1, Double.valueOf(Lmarket_condition.get(0)));
        market_condition.set(2, Double.valueOf(Lmarket_condition.get(1)));
        market_condition.set(3, Double.valueOf(Lmarket_condition.get(2)));
        market_condition.set(4, Double.valueOf(Lmarket_condition.get(3)));
		//System.out.println("complete convertion ...");
		try {

			J = new Judge();  //!!!!important
			//System.out.println("Judging ...");
			result = J.judge(8,name1,name2,f1,f2,new_price1,new_price2,lambda,
					last_time_state,last_trade_return,spoint,market_condition);
			//[signal long_name short_name long short current_state current_trade_return unit_time_return]

		} catch (Exception e) {
			System.out.println("Judge Exception catched!");
			System.out.println(e.toString());
		}	
		MWArray.disposeArray(f1);
		MWArray.disposeArray(f2);
		MWArray.disposeArray(lambda);
		MWArray.disposeArray(market_condition);
		return result;
	}
*/
	
	/*
	 * [all_return,trade_count,opt_lambda,prob_of_win,
	 *  trade,trade_stddev,trade_maxloss sharpe_ratio]
	 * =back_test(f1,f2,spoint,opt_option,market_condition,sigma_method)
	 * */
/*	public Object[] BackTest(ArrayList<Double> Lf1, ArrayList<Double> Lf2,double spoint,
			int opt_option,ArrayList<Double> Lmarket_condition){
		Object[] result = null;
		BackTest back_test = null;
		MWNumericArray f1 = null,f2 = null,market_condition = null;   
		int[] dims1 = {Lf1.size(), 1};  
        f1 = MWNumericArray.newInstance(dims1,   
           MWClassID.DOUBLE, MWComplexity.REAL);  
        for(int i = 1;i <= Lf1.size();i++){
        	f1.set(i, Double.valueOf(Lf1.get(i - 1)));
        }
        int[] dims2 = {Lf2.size(), 1};  
        f2 = MWNumericArray.newInstance(dims2,   
           MWClassID.DOUBLE, MWComplexity.REAL);
        for(int i = 1;i <= Lf2.size();i++){
        	f2.set(i, Double.valueOf(Lf2.get(i - 1)));
        }
        int[] dims4 = {4, 1};  
        market_condition = MWNumericArray.newInstance(dims4,   
                MWClassID.DOUBLE, MWComplexity.REAL);
        market_condition.set(1, Double.valueOf(Lmarket_condition.get(0)));
        market_condition.set(2, Double.valueOf(Lmarket_condition.get(1)));
        market_condition.set(3, Double.valueOf(Lmarket_condition.get(2)));
        market_condition.set(4, Double.valueOf(Lmarket_condition.get(3)));
		try {
			back_test = new BackTest();  //!!!!important
			result = back_test.back_test(8,f1,f2,spoint,opt_option,market_condition);
			//[all_return,trade_count,opt_lambda,prob_of_win,
			//trade,trade_stddev,trade_maxloss,sharpe_ratio]
		} catch (Exception e) {
			System.out.println("BackTest Exception catched!");
			System.out.println(e.toString());
		}	
		MWArray.disposeArray(f1);
		MWArray.disposeArray(f2);
		MWArray.disposeArray(market_condition);
		return result;
	}
*/
	
	/*
	 * return_ratio=convert2ratio(return_of_trade,market_condition,trade_count)
	 * */
	public double Convert2ratio(double return_of_trade,ArrayList<Double> Lmarket_condition,double trade_count){
		double result = 0.0;
		//Convert2ratio c2ratio = null;
//		MWNumericArray market_condition = null;   
//		int[] dims4 = {4, 1};  
//        market_condition = MWNumericArray.newInstance(dims4,   
//                MWClassID.DOUBLE, MWComplexity.REAL);
//        market_condition.set(1, Double.valueOf(Lmarket_condition.get(0)));
//        market_condition.set(2, Double.valueOf(Lmarket_condition.get(1)));
//        market_condition.set(3, Double.valueOf(Lmarket_condition.get(2)));
//        market_condition.set(4, Double.valueOf(Lmarket_condition.get(3)));
		try {
			//c2ratio = new Convert2ratio();  //!!!!important
			//result = c2ratio.convert2ratio(1, return_of_trade,market_condition,trade_count);
			result=(
					(return_of_trade-2*Double.valueOf(Lmarket_condition.get(2))*trade_count)*10000
					-2*Double.valueOf(Lmarket_condition.get(0))*trade_count
					)
					/2000000/Double.valueOf(Lmarket_condition.get(1));
			//return_ratio
		} catch (Exception e) {
			System.out.println("Convert2ratio Exception catched!");
			System.out.println(e.toString());
		}	
		return result;
	}

}
