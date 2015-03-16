package edu.nju.treasuryArbitrage.controller.dataInterface;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import Arbitrage_Main.*;

import com.mathworks.toolbox.javabuilder.*;
//����java�Դ�Matlab��ذ�
//�������Ա�Լ���matlab�����jar��

public class DataInterface2Matlab {
	 ArrayList<Double> Lmarket_condition = null;	
	public double opt_x,opt_y,opt_k;
	 ArrayList<Double> Llambda = null;
	
	public DataInterface2Matlab(){
	    Lmarket_condition = new ArrayList<Double>();
	    Llambda = new ArrayList<Double>();
	    Lmarket_condition.add(4.0);
	    Lmarket_condition.add(0.05);
	    Lmarket_condition.add(0.002);
	    Lmarket_condition.add(0.028);
	    //{4, 0.05, 0.002, 0.028};
		opt_x = 0;opt_y = 0;opt_k = 0;
		File file = new File("para_xyk");
	 	BufferedReader reader = null;
	 	String s = "";
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            // һ�ζ���һ�У�ֱ������nullΪ�ļ�����
            while ((tempString = reader.readLine()) != null) {
                // ��ʾ�к�
                s = tempString.trim();
                String t[]=s.split("\t");
            	opt_x = (Double.parseDouble(t[0]));
            	opt_y = (Double.parseDouble(t[1]));
            	opt_k = (Double.parseDouble(t[2]));
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
        File file2 = new File("para_lambda");
	 	BufferedReader reader2 = null;
	 	String s2 = "";
        try {
            //System.out.println("����Ϊ��λ��ȡ�ļ����ݣ�һ�ζ�һ���У�");
            reader2 = new BufferedReader(new FileReader(file2));
            String tempString2 = null;
            // һ�ζ���һ�У�ֱ������nullΪ�ļ�����
            while ((tempString2 = reader2.readLine()) != null) {
                // ��ʾ�к�
                s2 = tempString2.trim();
                String t2[]=s2.split("\t");
                Llambda.add(Double.parseDouble(t2[0]));
                Llambda.add(Double.parseDouble(t2[1]));
            	s2 = "";
            }
            reader2.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader2 != null) {
                try {
                    reader2.close();
                } catch (IOException e2) {
                }
            }
        }
	}
	
	
	/*
	 * resultΪ���ض������飬��Ԫ�طֱ�Ϊx,y,k
	 */
	public Object[] Arbitrage_Main(ArrayList<Double>  Lf1,ArrayList<Double>  Lf2,double stop_loss,double stop_profit){
		Object[] result = null;
		Arbitrage_Main m = null;
		MWNumericArray f1 = null,f2 = null;   
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
		try {
			//Arbitrage_Main��������ʾ��
			//��һ���������ֱ�ʾ��Ҫ��õĽ�����������������3����ʾ������������x,y,k;���д2,��ֻ�ܵõ�x,y;
			//��ͬ
			m = new Arbitrage_Main();  //!!!!important
			result =m.Arbitrage_Main(3,f1,f2,stop_loss, stop_profit);
			//optimization_x,optimization_y,optimization_k
			double x,y,k;
			x = Double.valueOf(String.valueOf(result[0]));
			y = Double.valueOf(String.valueOf(result[1]));
			k = Double.valueOf(String.valueOf(result[2]));
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
		} catch (Exception e) {
			System.out.println("Arbitrage_MAin Exception catched!");
			System.out.println(e.toString());
		}	
		MWArray.disposeArray(f1);
		MWArray.disposeArray(f2);
		return result;
	}
	
	/*
	 * 
	 * resultΪ���ض������飬��Ԫ�طֱ�Ϊsignal,buyprice,saleprice
	 */
	public Object[] Open(ArrayList<Double>  Lf1,ArrayList<Double>  Lf2,
			double newprice1,double newprice2,double x,double y,double k){
		Open open_position = null;
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
			//Open��������ʾ��
			open_position = new Open(); //!!!!important
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
	 * ����� resultΪ���ض������飬��Ԫ�طֱ�Ϊsignal,buyprice,saleprice
	 * ���룺%signal��ǰ����״̬��״̬��ȡ-3,-2��-1,0,1,2,3��
	 * Lf1��Lf2��ǰһ�����տ��̵�����ǰһ��ļ۸�����
	 * % �����signal���յ�ǰ�����¼۸��Ƿ�Ӧ�����ס�signal=1�������������֣�
	 * %signal=-1�������������֣�signal=0�������ף�signal=2��ֹӯƽ�֣�signal=-2,ֹ
	 * %��ƽ��,signal=3����������ƽ�֣�signal=-3����������ƽ�֣�
	 * 
	 */
	public Object[] Close(ArrayList<Double> Lf1,ArrayList<Double>  Lf2,
			double newprice1,double newprice2,double buyprice,double saleprice,
			double k,double signal,double stop_loss,double stop_profit){
		Close close_position = null;
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
			close_position = new Close(); //!!!!important
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
	 * [signal long_name short_name long short current_state current_trade_return units_time_return]
	 * =judge(name1,name2,f1,f2,new_price1,new_price2,lambda,last_time_state,last_trade_return,spoint,market_condition,sigma_method)
	 * %% �жϵ�ǰ�ļ۸��Ƿ�ƫ�룬���������������ź�
	 * %% ���룺
	 * %  name1����Լ1�����ƣ�����'TF1412';name2����Լ2������
	 * %  f1��ʷ��������1��һά���飩��f2��ʷ��������2��һά���飩����Ϊ������
	 * %  new_price1Ϊ�¼۸�1��double����new_price2������2��double����lambda���Բ�����2*1��������
	 * %  last_time_state��ǰ����״̬��ȡ-1,0,1���ֱ������������ϡ��ղֺ�����������ϣ�
	 * %  last_trade_returnָ���ǵ�ǰ�ֲֵ�ĿǰΪֹ���ۼ����������
	 * %  spointΪֹ��㣬����-0.02
	 * %  market_condition��Ϊ��ӳ��ǰ�г�״����һϵ�в������乹��Ϊ[һ�ֽ��׷�,��֤�����,���Ƶĳ���ɱ�,�޷�������]
	 * %  sigma_methodΪ����δ�������ʵľ��巽������Ϊ'garch'��������ǰ�߾��ȸߵ��ٶ��������߾��ȵ͵��ٶȿ�����
	 * %% �����
	 * %  signal���յ�ǰ�����¼۸��Ƿ�Ӧ�����ס�
	 * %  signal=1�������������֣�signal=-1�������������֣�signal=0���޶�����signal=2��ֹӯƽ�֣�signal=-2,ֹ��ƽ�֡�
	 * %  long_name������ĺ�Լ���ƣ�short_name�����յĺ�Լ���ƣ�long�������Լ�Ĳο�������short�����պ�Լ�Ĳο�������
	 * %  current_state���ڰ��յ�ǰ�źŽ�����ɺ󣬵�ǰ�ĳֲ��Ǹ���������ϡ��ղֻ�������������ϣ��ֱ�Ϊ-1,0��1����Ӧ��last_time_state��
	 * %  current_trade_return��ǰ�ֲ��ڱ��ν�����ɺ���µ��ۼ����������unit_time_return����ǰ�����ڵ�λ����ʱ���������������޽�����Ϊ0��
	 * 	 */
	public Object[] Judge(String name1,String name2,ArrayList<Double> Lf1,ArrayList<Double> Lf2,
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
			//Arbitrage_Main��������ʾ��
			//��һ���������ֱ�ʾ��Ҫ��õĽ�����������������3����ʾ������������x,y,k;���д2,��ֻ�ܵõ�x,y;
			//��ͬ
			J = new Judge();  //!!!!important
			//System.out.println("Judging ...");
			result = J.judge(8,name1,name2,f1,f2,new_price1,new_price2,lambda,
					last_time_state,last_trade_return,spoint,market_condition);
			//[signal long_name short_name long short current_state current_trade_return unit_time_return]
			/*   8������
			 * %% �����signal���յ�ǰ�����¼۸��Ƿ�Ӧ�����ס�
			 * %  signal=1�������������֣�signal=-1�������������֣�signal=0���޶�����signal=2��ֹӯƽ�֣�signal=-2,ֹ��ƽ�֡�
			 * %  long_name������ĺ�Լ���ƣ�short_name�����յĺ�Լ���ƣ�long�������Լ�Ĳο�������short�����պ�Լ�Ĳο�������
			 * %  current_state���ڰ��յ�ǰ�źŽ�����ɺ󣬵�ǰ�ĳֲ��Ǹ���������ϡ��ղֻ�������������ϣ��ֱ�Ϊ-1,0��1����Ӧ��last_time_state��
			 * %  current_trade_return��ǰ�ֲ��ڱ��ν�����ɺ���µ��ۼ����������unit_time_return����ǰ�����ڵ�λ����ʱ���������������޽�����Ϊ0��
			 *  */
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
	
	
	/*
	 * [all_return,trade_count,opt_lambda,prob_of_win,
	 *  trade,trade_stddev,trade_maxloss sharpe_ratio]
	 * =back_test(f1,f2,spoint,opt_option,market_condition,sigma_method)
	 * %% �ز⺯�����������е����ݻز����ŵĲ��Բ���������������
	 * %% ���룺f1��ʷ��������1������������f2��ʷ��������2������������spointֹ���
	 * %  opt_choice���Ż�ѡ�1Ϊ��������󻯣�2Ϊ���յ������������󻯣�3Ϊʤ�����4Ϊʤ�ʵ�������������
	 * %  market_conditionΪ��ӳ��ǰ�г�״����һϵ�в������乹��Ϊ[һ�ֽ��׷�,��֤�����,���Ƶĳ���ɱ�,�޷�������]
	 * %  sigma_methodΪ���Ʋ����ʵķ�������Ϊgarch��������ȡֵΪ��Garch��ʱʹ��GARCHģ�ͣ������ִ�Сд���������������ʹ����ʷ������
	 * %% �����all_returnΪ�ز�õ���������棬opt_lambdaΪ���ŵĲ��Բ���
	 * %  prob_of_winΪ����ӯ���ĸ��ʣ�tradeΪ������ϸ���ṹΪ[�Ƿ�ֲ� �в����� �۲����� ���������� ƫ����� ��ǰʱ��������� ��ǰ�����������]
	 * %  trade_stddev trade_maxloss sharpe_ratio�ֱ�Ϊ��׼����س��Ͳ������ձ���
	 * */
	public Object[] BackTest(ArrayList<Double> Lf1, ArrayList<Double> Lf2,double spoint,
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
			/* %% �����all_returnΪ�ز�õ���������棬opt_lambdaΪ���ŵĲ��Բ���
			 * %  prob_of_winΪ����ӯ���ĸ��ʣ�tradeΪ������ϸ���ṹΪ[�Ƿ�ֲ� �в����� �۲����� ���������� ƫ����� ��ǰʱ��������� ��ǰ�����������]
			 * %  trade_stddev trade_maxloss sharpe_ratio�ֱ�Ϊ��׼����س��Ͳ������ձ���
			 * */
		} catch (Exception e) {
			System.out.println("BackTest Exception catched!");
			System.out.println(e.toString());
		}	
		MWArray.disposeArray(f1);
		MWArray.disposeArray(f2);
		MWArray.disposeArray(market_condition);
		return result;
	}
	
	
	/*
	 * return_ratio=convert2ratio(return_of_trade,market_condition,trade_count)
	 * %% ����һ���ĵ���������������
	 * % ���룺����������г�����
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
	
	public ArrayList<Double> getMarket_condition(){
		return Lmarket_condition;
	}
	public ArrayList<Double> getLambda(){
		return Llambda;
	}
}
