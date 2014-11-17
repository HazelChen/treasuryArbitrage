package edu.nju.treasuryArbitrage.logic.liveUpdate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import edu.nju.treasuryArbitrage.factory.DataInterfaceFactory;
import edu.nju.treasuryArbitrage.logic.dataInterface.DataInterface;
import edu.nju.treasuryArbitrage.logic.dataInterface2Matlab.DataInterface2Matlab;
import edu.nju.treasuryArbitrage.model.ArbGroup;
import edu.nju.treasuryArbitrage.model.Repository;

public class AnalyseThread implements Runnable{
	private static AnalyseThread self = new AnalyseThread();
	double buyprice=0,saleprice=0;
	String message = "DMsg";
	
	public void run() {
		DataInterface dataInterface = DataInterfaceFactory.getInstance().getDataInterfaceToServer();
		//dataInterface.loginValidate("a", "123"); //test
		ArrayList<ArbGroup> arb_groups = new ArrayList<ArbGroup>();
		arb_groups = LiveData.getInstance().getArbGroups();
		DataInterface2Matlab dm = new DataInterface2Matlab();
        //System.out.println("x y k:" + dm.opt_x + ","+ dm.opt_y +","+ dm.opt_k);
		long sleepsec = 0;
		Object[] result = null;
		
		ArrayList<Double> allLf1,allLf2,//所有的历史价格记录，规模很大
						  Lf1,Lf2;//前一交易日到现在的价格记录，相对较小,大约240条数据
		ArrayList<Repository> info = dataInterface.getRepoList();
		double newprice1, newprice2,newprice3;
		String name1 = "TF1412",
				name2 = "TF1503",
				name3 = "TF1506";
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
	            // 一次读入一行，直到读入null为文件结束
	            while ((tempString = reader.readLine()) != null) {
	                // 显示行号
	                s = tempString.trim();
	                //System.out.println("line " + line + ": " + s);
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
        Date now = new Date(); 
		int now_hour,now_min;
		boolean runtime = true;
		ArbGroup arb_group = new ArbGroup(name1, name2, true);
		//arb_groups.add(arb_group);
		//LiveData.getInstance().setArbGroups(arb_groups);
		
		while (true) {
			long start = System.currentTimeMillis();
			now = new Date();
			now_hour = now.getHours();
			now_min = now.getMinutes();
			 //交易时间  9:15-11:30、13:00-15:15
			if( true/*	  (now_hour == 9 && now_min >= 15)         //09:15-09:59
				   || (now_hour > 9 && now_hour <11)	 	 //10:00-10:59	
				   || (now_hour == 11 && now_min <= 30)	 //11:00-11:30
				   || (now_hour >= 13 && now_hour <15)	 	 //13:00-14:59
				   || (now_hour == 15 && now_min <= 15)		 //15:00-15:15
					*/	)
			{
					runtime = true;
				//一分钟调用一次dm里的函数

					//获取现价
					newprice1 = LiveData.getInstance().getPresentPrice(name1);
					newprice2 = LiveData.getInstance().getPresentPrice(name2);
					newprice3 = LiveData.getInstance().getPresentPrice(name3);
					info = dataInterface.getRepoList();
			        //-----------------对还未建仓的组合进行判断，看是否适合建仓
					String strholdings = "";
					for(int i = 0;i < info.size();i++){
						if(info.get(i).getToBuy().compareTo(info.get(i).getToSell())<0)
						{strholdings = strholdings + "G"+ info.get(i).getToBuy() + info.get(i).getToSell();}
						else{
							strholdings = strholdings + "G" + info.get(i).getToSell() + info.get(i).getToBuy();
						}
					}
					//System.out.println(strholdings);
					//对合约1,2进行判断,是否建仓
					if(checkHoldings(strholdings,name1,name2) < 0)//
					{
						//运用模型一计算，分析
						//获取现价
						newprice1 = LiveData.getInstance().getPresentPrice(name1);
						newprice2 = LiveData.getInstance().getPresentPrice(name2);
						newprice3 = LiveData.getInstance().getPresentPrice(name3);
						System.out.println("try open group " + name1 +":" + newprice1 + name2 +":" + newprice2);
						result = dm.Open(Lf1, Lf2,Double.valueOf(newprice1),Double.valueOf(newprice2), dm.opt_x,dm.opt_y,dm.opt_k);// newprice1, newprice2, dm.opt_x, dm.opt_y, dm.opt_k);
						//建仓操作，即弹出提示框
						if(result.length >=3 &&Integer.valueOf(String.valueOf(result[0])) != 0){
							buyprice = Double.valueOf(String.valueOf(result[1]));
							saleprice = Double.valueOf(String.valueOf(result[2]));
							arb_group = new ArbGroup(name1, name2, true);
							arb_groups.add(arb_group);
							LiveData.getInstance().setArbGroups(arb_groups);
							//弹出提示框
							message = "O有重大套利机会！\r\n马上前往套利组合页面查看？";
							this.sendMsg(message);
							(new Thread(new showDiagThread())).start();
						}
						System.out.println("open result: signal = " + result[0] + " buyprice = " + result[1] + " saleprice = " + result[2]);
						
						//运用模型二计算，分析
//						
//						//获取现价
//						newprice1 = LiveData.getInstance().getPresentPrice(name1);
//						newprice2 = LiveData.getInstance().getPresentPrice(name2);
//						newprice3 = LiveData.getInstance().getPresentPrice(name3);
//						result = dm.Judge(name1, name2, Lf1, Lf2, newprice1, newprice2,
//								dm.getLambda(), 0/*last_time_state*/, 0/*last_trade_return*/,
//								-0.02/*spoint*/, dm.getMarket_condition());
//						//建仓操作，即弹出提示框
//						if(Integer.valueOf(String.valueOf(result[0])) != 0){
//							buyprice = Double.valueOf(String.valueOf(result[0]));
//							saleprice = Double.valueOf(String.valueOf(result[1]));
//							//弹出提示框
//							(new Thread(new showDiagThread())).start();
//						}
//						System.out.println("judge result: signal = " + result[0] + " buyprice = " + result[1] + " saleprice = " + result[2]);
//						
					}
					//对合约2,3进行判断       暂时不做!!!
//					if(!info.get(i).getToBuy().equals(name2)
//						&& !info.get(i).getToSell().equals(name3))
//					{
//						result = dm.Open(Lf1, Lf2, newprice2, newprice3, dm.opt_x, dm.opt_y, dm.opt_k);
//						//建仓操作，即下单     Order							
//					}
					//对合约1,3进行判断       暂时不做!!!
//					if(!info.get(i).getToBuy().equals(name1)
//						&& !info.get(i).getToSell().equals(name3))
//					{
//						result = dm.Open(Lf1, Lf2, newprice2, newprice3, dm.opt_x, dm.opt_y, dm.opt_k);
//						//建仓操作，即下单     Order							
//					}
					
					//-----------------对持仓进行判断，看是否需要平仓
					info = dataInterface.getRepoList();
					if(info != null && info.size() > 0){
						System.out.println("Group Number :" + info.size());
						
						//暂时只做 1412 1503
						for(int i = 0;i <(info.size()<2 ? info.size() : 2);i++){
							//检测持仓情况
							if(checkHoldings(strholdings,name1,name2) == i)
							{
								try{
									System.out.println("Group " + (i +1));
									System.out.println("Buy:" + info.get(i).getToBuy() + "  " + info.get(0).gettoBuy_price());
									System.out.println("Sale:" + info.get(i).getToSell() + "  "  + info.get(0).gettoSell_price());
	
									newprice1 = LiveData.getInstance().getPresentPrice(name1);
									newprice2 = LiveData.getInstance().getPresentPrice(name2);
									newprice3 = LiveData.getInstance().getPresentPrice(name3);

									System.out.println("try close Group :" + name1 + " + " + name2);
									result = dm.Close(Lf1, Lf2, 
											newprice1, newprice2,//现价
											info.get(i).gettoBuy_price(),/*buyprice*/
											info.get(i).gettoSell_price(), /*saleprice*/
											dm.opt_k, info.get(i).getSignal(), -1, 1);
									System.out.println("close result: signal = " + result[0] + " buyprice = " + result[1] + " saleprice = " + result[2]);
									if(Integer.valueOf(String.valueOf(result[0])) == 2){
									    //平仓操作 trade  止盈平仓
										//弹出提示框
										message = "C组合" + name1 + name2 + " 到达止盈平仓点,已自动平仓！\r\n前往持仓情况？";
										this.sendMsg(message);
										if(dataInterface.Trade(info.get(i).getRepo_ID(), info.get(i).getProfit()))
										{
											arb_groups.remove(i);
											(new Thread(new showDiagThread())).start();
										}
									}
									if(Integer.valueOf(String.valueOf(result[0])) == -2){
									    //平仓操作 trade  止损平仓
										//弹出提示框
										message = "C组合" + name1 + name2 + " 到达止损平仓点！\r\n前往持仓情况？";
										this.sendMsg(message);
										if(dataInterface.Trade(info.get(i).getRepo_ID(), info.get(i).getProfit()))
										{
											arb_groups.remove(i);
											(new Thread(new showDiagThread())).start();
										}
									}
									if(Integer.valueOf(String.valueOf(result[0])) == 3){
									    //平仓操作 trade  正向套利平仓
										//弹出提示框
										message = "C组合" + name1 + name2 + " 到达正向套利平仓点！\r\n前往持仓情况？";
										this.sendMsg(message);
										if(dataInterface.Trade(info.get(i).getRepo_ID(), info.get(i).getProfit()))
										{
											arb_groups.remove(i);
											(new Thread(new showDiagThread())).start();
										}
									}
									if(Integer.valueOf(String.valueOf(result[0])) == -3){
									    //平仓操作 trade  反向套利平仓
										//弹出提示框
										message = "C组合" + name1 + name2 + " 到达反向套利平仓点！\r\n前往持仓情况？";
										this.sendMsg(message);
										if(dataInterface.Trade(info.get(i).getRepo_ID(), info.get(i).getProfit()))
										{
											arb_groups.remove(i);
											(new Thread(new showDiagThread())).start();
										}
									}
									//运用模型二计算，分析
									
//								//获取现价
//								newprice1 = LiveData.getInstance().getPresentPrice(name1);
//								newprice2 = LiveData.getInstance().getPresentPrice(name2);
//								newprice3 = LiveData.getInstance().getPresentPrice(name3);
//								result = dm.Judge(name1, name2, Lf1, Lf2, newprice1, newprice2,
//										dm.getLambda(), 0/*last_time_state*/, 0/*last_trade_return*/,
//										-0.02/*spoint*/, dm.getMarket_condition());
//								//建仓操作，即弹出提示框
//								if(Double.valueOf(String.valueOf(result[0])) != 0){
//									buyprice = Double.valueOf(String.valueOf(result[0]));
//									saleprice = Double.valueOf(String.valueOf(result[1]));
//									//弹出提示框
//									(new Thread(new showDiagThread())).start();
//								}
//								System.out.println("judge result: signal = " + result[0] + " buyprice = " + result[1] + " saleprice = " + result[2]);

								
								}catch(Exception e){}
							}
							
						}//end of for
					}//end of if  平仓检测		
				//添加价格记录
				Lf1.add(newprice1);
				Lf2.add(newprice2);
			}//end of if 交易时间判断
			else 
			{
				runtime = false;
			}
			long end = System.currentTimeMillis();
			System.out.println("Analysetime ： " + (double)(end - start) / 1000  + "秒");
			sleepsec = 60000 - (end - start);
			sleepsec = sleepsec > 0? sleepsec : 0;
			System.out.println("Sleeptime ： " + (double)sleepsec / 1000  + "秒");

			try {
				Thread.sleep(sleepsec);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			//一天的交易结束后
			if(!runtime && now_hour == 15){
				//dm.Arbitrage_Main(allLf1, allLf2, -1, 1);
				//dm.Arbitrage_Main(allLf2, allLf3, -1, 1);
				//dm.Arbitrage_Main(allLf1, allLf3, -1, 1);
			}
			
			//休市时间，线程休眠
			while(!runtime){
				now = new Date();
				now_hour = now.getHours();
				now_min = now.getMinutes();
				//稍微提前一段时间启动
				if((now_hour == 8 && now_min >= 58)
				 || now_hour == 9
				 || now_hour == 14 && now_min >= 43){
					runtime = true;
				}
				else{
					try {
						Thread.sleep(15*60*1000);//15分钟检查一次
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}//end of while(!runtime)	
			
		}//end of while(true)
		
	}//end of run()
	
	public int checkHoldings(String str,String name1, String name2){
		String s = str,t1,t2;
		
		for(int i = 0; i < s.length()/13;i++)
		{
			t1 = s.substring(s.indexOf('G') + 1,s.indexOf('G') + 7);
			t2 = s.substring(s.indexOf('G') + 7,s.indexOf('G') + 13);
			
			if(t1.equals(name1) && t2.equals(name2))
				return i;
			s = s.substring(s.indexOf('G') + 13);
		}
			return -1;
	}
	
	public static AnalyseThread getInstance() {
		return self;
	}
	public double getBuyprice(){
		return buyprice;
	}
	public double getSaleprice(){
		return saleprice;
	}
	
	public void sendMsg(String msg){
		AnalyseThread.getInstance().message = msg;
	}
	
	public String getMsg(){
		return AnalyseThread.getInstance().message;
	}
	//end of run

};//
