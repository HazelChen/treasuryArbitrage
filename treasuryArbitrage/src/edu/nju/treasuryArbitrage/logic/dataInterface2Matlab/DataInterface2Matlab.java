package edu.nju.treasuryArbitrage.logic.dataInterface2Matlab;

import java.util.ArrayList;

import Arbitrage_Main.*;

import com.mathworks.toolbox.javabuilder.*;
//����java�Դ�Matlab��ذ�
//�������Ա�Լ���matlab�����jar��

public class DataInterface2Matlab {
		
	public DataInterface2Matlab(){
		
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
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Arbitrage_MAin Exception catched!");
			System.out.println(e.toString());
		}	
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
			//Open��������ʾ��
			open_position = new Open(); //!!!!important
			result =open_position.open(3,f1,f2,newprice1,newprice2,x,y,k);
		    //signal,buyprice1,saleprice1
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Open Exception catched!");
		}
		return result;
	}
	
	/*
	 * ���룺%signal��ǰ����״̬��״̬��ȡ-3,-2��-1,0,1,2,3��
	 * Lf1��Lf2��ǰһ�����տ��̵�����ǰһ��ļ۸�����
	 * % �����signal���յ�ǰ�����¼۸��Ƿ�Ӧ�����ס�signal=1�������������֣�
	 * %signal=-1�������������֣�signal=0�������ף�signal=2��ֹӯƽ�֣�signal=-2,ֹ
	 * %��ƽ��,signal=3����������ƽ�֣�signal=-3����������ƽ�֣�
	 * ����� resultΪ���ض������飬��Ԫ�طֱ�Ϊsignal,buyprice,saleprice
	 */
	public Object[] Close(ArrayList<Double> Lf1,ArrayList<Double>  Lf2,
			double newprice1,double newprice2,double buyprice,double saleprice,
			double k,double signal,double stop_loss,double stop_profit){
		Close close_position = null;
		Object[] result = null;
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
		try{
			close_position = new Close(); //!!!!important
			result =close_position.close(3,f1,f2,
					newprice1,newprice2,buyprice,saleprice,k,signal,stop_loss,stop_profit);
			//signal,buyprice,saleprice
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Close Exception catched!");
		}
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
			ArrayList<Double> Lmarket_condition,String sigma_method){
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
		try {
			//Arbitrage_Main��������ʾ��
			//��һ���������ֱ�ʾ��Ҫ��õĽ�����������������3����ʾ������������x,y,k;���д2,��ֻ�ܵõ�x,y;
			//��ͬ
			J = new Judge();  //!!!!important
			
			result = J.judge(8,name1,name2,f1,f2,new_price1,new_price2,lambda,
					last_time_state,last_trade_return,spoint,market_condition,sigma_method);
			//[signal long_name short_name long short current_state current_trade_return unit_time_return]
			/*   8������
			 * %% �����signal���յ�ǰ�����¼۸��Ƿ�Ӧ�����ס�
			 * %  signal=1�������������֣�signal=-1�������������֣�signal=0���޶�����signal=2��ֹӯƽ�֣�signal=-2,ֹ��ƽ�֡�
			 * %  long_name������ĺ�Լ���ƣ�short_name�����յĺ�Լ���ƣ�long�������Լ�Ĳο�������short�����պ�Լ�Ĳο�������
			 * %  current_state���ڰ��յ�ǰ�źŽ�����ɺ󣬵�ǰ�ĳֲ��Ǹ���������ϡ��ղֻ�������������ϣ��ֱ�Ϊ-1,0��1����Ӧ��last_time_state��
			 * %  current_trade_return��ǰ�ֲ��ڱ��ν�����ɺ���µ��ۼ����������unit_time_return����ǰ�����ڵ�λ����ʱ���������������޽�����Ϊ0��
			 *  */
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Judge Exception catched!");
			System.out.println(e.toString());
		}	
		return result;
	}
	
	
	/*
	 * [all_return,trade_count,opt_lambda,prob_of_win,trade,trade_stddev,trade_maxloss sharpe_ratio]
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
			int opt_option,ArrayList<Double> Lmarket_condition,String sigma_method){
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
			result = back_test.back_test(8,f1,f2,spoint,opt_option,market_condition,sigma_method);
			//[all_return,trade_count,opt_lambda,prob_of_win,
			//trade,trade_stddev,trade_maxloss,sharpe_ratio]
			/* %% �����all_returnΪ�ز�õ���������棬opt_lambdaΪ���ŵĲ��Բ���
			 * %  prob_of_winΪ����ӯ���ĸ��ʣ�tradeΪ������ϸ���ṹΪ[�Ƿ�ֲ� �в����� �۲����� ���������� ƫ����� ��ǰʱ��������� ��ǰ�����������]
			 * %  trade_stddev trade_maxloss sharpe_ratio�ֱ�Ϊ��׼����س��Ͳ������ձ���
			 * */
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("BackTest Exception catched!");
			System.out.println(e.toString());
		}	
		return result;
	}
	
	
	/*
	 * return_ratio=convert2ratio(return_of_trade,market_condition,trade_count)
	 * %% ����һ���ĵ���������������
	 * % ���룺����������г�����
	 * */
	public Object[] Convert2ratio(double return_of_trade,ArrayList<Double> Lmarket_condition,double trade_count){
		Object[] result = null;
		Convert2ratio c2ratio = null;
		MWNumericArray market_condition = null;   
		int[] dims4 = {4, 1};  
        market_condition = MWNumericArray.newInstance(dims4,   
                MWClassID.DOUBLE, MWComplexity.REAL);
        market_condition.set(1, Double.valueOf(Lmarket_condition.get(0)));
        market_condition.set(2, Double.valueOf(Lmarket_condition.get(1)));
        market_condition.set(3, Double.valueOf(Lmarket_condition.get(2)));
        market_condition.set(4, Double.valueOf(Lmarket_condition.get(3)));
		try {
			c2ratio = new Convert2ratio();  //!!!!important
			result = c2ratio.convert2ratio(1, return_of_trade,market_condition,trade_count);
			//return_ratio
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Convert2ratio Exception catched!");
			System.out.println(e.toString());
		}	
		return result;
	}
}
