package edu.nju.treasuryArbitrage.logic.liveUpdate;

import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import edu.nju.treasuryArbitrage.factory.DataInterfaceFactory;
import edu.nju.treasuryArbitrage.factory.MajorPartsFactory;
import edu.nju.treasuryArbitrage.logic.dataInterface.DataInterface;
import edu.nju.treasuryArbitrage.logic.dataInterface2Matlab.DataInterface2Matlab;
import edu.nju.treasuryArbitrage.model.ArbGroup;
import edu.nju.treasuryArbitrage.model.Arb_detail;
import edu.nju.treasuryArbitrage.model.Repository;

public class AnalyseThread implements Runnable{

	double buyprice=0,saleprice=0;
	
	public void run() {
		DataInterface dataInterface = DataInterfaceFactory.getInstance().getDataInterfaceToServer();
		//dataInterface.loginValidate("a", "123"); //test
		ArrayList<Arb_detail> arb_details = dataInterface.getArbDetail();
		
		DataInterface2Matlab dm = new DataInterface2Matlab();
        //System.out.println("x y k:" + dm.opt_x + ","+ dm.opt_y +","+ dm.opt_k);
		long sleepsec = 0;
		Object[] result = null;
		
		ArrayList<Double> allLf1,allLf2,//所有的历史价格记录，规模很大
						  Lf1,Lf2;//前一交易日到现在的价格记录，相对较小,大约240条数据
		ArrayList<Repository> info = dataInterface.getRepoList();
		double newprice1, newprice2,newprice3;
		String name1 = arb_details.get(0).getSymbol(),
				name2 =arb_details.get(1).getSymbol(),
				name3 =arb_details.get(2).getSymbol();
		boolean runtime = true;
		//获取Lf1,Lf2
		//代码
		//测试时暂时从文件读取
		File file = new File("in234.txt");
	 	BufferedReader reader = null;
	 	Lf1 = new ArrayList<Double>();
		Lf2 = new ArrayList<Double>();
		String s = "";
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
	            	Lf1.add(Double.parseDouble(t[0]));
	            	Lf2.add(Double.parseDouble(t[1]));
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
        
		while (runtime) {
			try {
				Thread.sleep(sleepsec);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			long start = System.currentTimeMillis();
			Date now = new Date(); 
			int now_hour = now.getHours(),
					now_min = now.getMinutes();
			 System.out.println(now_hour);  
			 //交易时间  9:15-11:30、13:00-15:15
				if(	  (now_hour == 9 && now_min >= 15)         //09:15-09:59
				   || (now_hour > 9 && now_hour <11)	 	 //10:00-10:59	
				   || (now_hour == 11 && now_min <= 30)	 //11:00-11:30
				   || (now_hour >= 13 && now_hour <15)	 	 //13:00-14:59
				   || (now_hour == 15 && now_min <= 15)		 //15:00-15:15
						)
			{						
				//一分钟调用一次dm里的函数

					//获取现价
					newprice1 = LiveData.getInstance().getPresentPrice(name1);
					newprice2 = LiveData.getInstance().getPresentPrice(name2);
					newprice3 = LiveData.getInstance().getPresentPrice(name3);
					info = dataInterface.getRepoList();
			        //-----------------对还未建仓的组合进行判断，看是否适合建仓
					for(int i = 0;i < info.size();i++){
						//对合约1,2进行判断
						if((!info.get(i).getToBuy().equals(name1)
							&& !info.get(i).getToSell().equals(name2)))
						{
							//获取现价
							newprice1 = LiveData.getInstance().getPresentPrice(name1);
							newprice2 = LiveData.getInstance().getPresentPrice(name2);
							newprice3 = LiveData.getInstance().getPresentPrice(name3);
							result = dm.Open(Lf1, Lf2, newprice1, newprice2, dm.opt_x, dm.opt_y, dm.opt_k);
							//建仓操作，即弹出提示框
							if(Double.valueOf(String.valueOf(result[0])) != 0){
								buyprice = Double.valueOf(String.valueOf(result[0]));
								saleprice = Double.valueOf(String.valueOf(result[1]));
								//弹出提示框
								(new Thread(new showDiagThread())).start();
							}
							System.out.println("open result: signal = " + result[0] + " buyprice = " + result[1] + " saleprice = " + result[2]);
						}
						//对合约2,3进行判断       暂时不做!!!
//						if(!info.get(i).getToBuy().equals(name2)
//							&& !info.get(i).getToSell().equals(name3))
//						{
//							result = dm.Open(Lf1, Lf2, newprice2, newprice3, dm.opt_x, dm.opt_y, dm.opt_k);
//							//建仓操作，即下单     Order							
//						}
						//对合约1,3进行判断       暂时不做!!!
//						if(!info.get(i).getToBuy().equals(name1)
//							&& !info.get(i).getToSell().equals(name3))
//						{
//							result = dm.Open(Lf1, Lf2, newprice2, newprice3, dm.opt_x, dm.opt_y, dm.opt_k);
//							//建仓操作，即下单     Order							
//						}
					}
							        
					//-----------------对持仓进行判断，看是否需要平仓
					if(info.size() > 0){
						System.out.println("Group Number :" + info.size());
						
						//暂时只做 1412 1503
						for(int i = 0;i <(info.size()<2 ? info.size() : 2);i++){
							try{
								System.out.println("Group " + (i +1));
								System.out.println("BuyPirce:" + info.get(i).getToBuy() + "  " + info.get(0).gettoBuy_price());
								System.out.println("SalePirce:" + info.get(i).getToSell() + "  "  + info.get(0).gettoSell_price());

								newprice1 = LiveData.getInstance().getPresentPrice(name1);
								newprice2 = LiveData.getInstance().getPresentPrice(name2);
								newprice3 = LiveData.getInstance().getPresentPrice(name3);
								result = dm.Close(Lf1, Lf2, 
										newprice1, newprice2,//现价
										info.get(i).gettoBuy_price(),/*buyprice*/
										info.get(i).gettoSell_price(), /*saleprice*/
										dm.opt_k, info.get(i).getSignal(), -1, 1);
								System.out.println("close result: signal = " + result[0] + " buyprice = " + result[1] + " saleprice = " + result[2]);
								if(Double.valueOf(String.valueOf(result[0])) == 2){
								    //平仓操作 trade  止盈平仓
								}
								if(Double.valueOf(String.valueOf(result[0])) == -2){
								    //平仓操作 trade  止损平仓
								}
								if(Double.valueOf(String.valueOf(result[0])) == 3){
								    //平仓操作 trade  正向套利平仓
								}
								if(Double.valueOf(String.valueOf(result[0])) == -3){
								    //平仓操作 trade  反向套利平仓
								}
								}catch(Exception e){}
						}
					}		
				//添加价格记录
				Lf1.add(newprice1);
				Lf2.add(newprice2);
			}
			else 
			{
				runtime = false;
			}
			long end = System.currentTimeMillis();
			System.out.println("Analysetime ： " + (double)(end - start) / 1000  + "秒");
			sleepsec = 60000 - (end - start);
			sleepsec = sleepsec > 0? sleepsec : 0;
			System.out.println("Sleeptime ： " + (double)sleepsec / 1000  + "秒");
			
		}//end of while
		
		//dm.Arbitrage_Main(allLf1, allLf2, -1, 1);
		//dm.Arbitrage_Main(allLf2, allLf3, -1, 1);
		//dm.Arbitrage_Main(allLf1, allLf3, -1, 1);
		
	}
	
	public double getBuyprice(){
		return buyprice;
	}
	public double getSaleprice(){
		return saleprice;
	}
	
	//end of run
	
	/**
	 * @param args
	 *
	public static void main(String[] args) {
		AnalyseThread test = new AnalyseThread();
		test.run();
		
		//(new ThreadDiag(null,test,"提示")).start();//启动等待提示框线程
	}*/
};//
