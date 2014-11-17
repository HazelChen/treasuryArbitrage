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
		
		ArrayList<Double> allLf1,allLf2,//���е���ʷ�۸��¼����ģ�ܴ�
						  Lf1,Lf2;//ǰһ�����յ����ڵļ۸��¼����Խ�С,��Լ240������
		ArrayList<Repository> info = dataInterface.getRepoList();
		double newprice1, newprice2,newprice3;
		String name1 = "TF1412",
				name2 = "TF1503",
				name3 = "TF1506";
		//��ȡLf1,Lf2
		//����
		//����ʱ��ʱ���ļ���ȡ
		File file = new File("in234.txt");
	 	BufferedReader reader = null;
	 	Lf1 = new ArrayList<Double>();
		Lf2 = new ArrayList<Double>();
		String s = "";
        try {
            	//System.out.println("����Ϊ��λ��ȡ�ļ����ݣ�һ�ζ�һ���У�");
	            reader = new BufferedReader(new FileReader(file));
	            String tempString = null;
	            // һ�ζ���һ�У�ֱ������nullΪ�ļ�����
	            while ((tempString = reader.readLine()) != null) {
	                // ��ʾ�к�
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
			 //����ʱ��  9:15-11:30��13:00-15:15
			if( true/*	  (now_hour == 9 && now_min >= 15)         //09:15-09:59
				   || (now_hour > 9 && now_hour <11)	 	 //10:00-10:59	
				   || (now_hour == 11 && now_min <= 30)	 //11:00-11:30
				   || (now_hour >= 13 && now_hour <15)	 	 //13:00-14:59
				   || (now_hour == 15 && now_min <= 15)		 //15:00-15:15
					*/	)
			{
					runtime = true;
				//һ���ӵ���һ��dm��ĺ���

					//��ȡ�ּ�
					newprice1 = LiveData.getInstance().getPresentPrice(name1);
					newprice2 = LiveData.getInstance().getPresentPrice(name2);
					newprice3 = LiveData.getInstance().getPresentPrice(name3);
					info = dataInterface.getRepoList();
			        //-----------------�Ի�δ���ֵ���Ͻ����жϣ����Ƿ��ʺϽ���
					String strholdings = "";
					for(int i = 0;i < info.size();i++){
						if(info.get(i).getToBuy().compareTo(info.get(i).getToSell())<0)
						{strholdings = strholdings + "G"+ info.get(i).getToBuy() + info.get(i).getToSell();}
						else{
							strholdings = strholdings + "G" + info.get(i).getToSell() + info.get(i).getToBuy();
						}
					}
					//System.out.println(strholdings);
					//�Ժ�Լ1,2�����ж�,�Ƿ񽨲�
					if(checkHoldings(strholdings,name1,name2) < 0)//
					{
						//����ģ��һ���㣬����
						//��ȡ�ּ�
						newprice1 = LiveData.getInstance().getPresentPrice(name1);
						newprice2 = LiveData.getInstance().getPresentPrice(name2);
						newprice3 = LiveData.getInstance().getPresentPrice(name3);
						System.out.println("try open group " + name1 +":" + newprice1 + name2 +":" + newprice2);
						result = dm.Open(Lf1, Lf2,Double.valueOf(newprice1),Double.valueOf(newprice2), dm.opt_x,dm.opt_y,dm.opt_k);// newprice1, newprice2, dm.opt_x, dm.opt_y, dm.opt_k);
						//���ֲ�������������ʾ��
						if(result.length >=3 &&Integer.valueOf(String.valueOf(result[0])) != 0){
							buyprice = Double.valueOf(String.valueOf(result[1]));
							saleprice = Double.valueOf(String.valueOf(result[2]));
							arb_group = new ArbGroup(name1, name2, true);
							arb_groups.add(arb_group);
							LiveData.getInstance().setArbGroups(arb_groups);
							//������ʾ��
							message = "O���ش��������ᣡ\r\n����ǰ���������ҳ��鿴��";
							this.sendMsg(message);
							(new Thread(new showDiagThread())).start();
						}
						System.out.println("open result: signal = " + result[0] + " buyprice = " + result[1] + " saleprice = " + result[2]);
						
						//����ģ�Ͷ����㣬����
//						
//						//��ȡ�ּ�
//						newprice1 = LiveData.getInstance().getPresentPrice(name1);
//						newprice2 = LiveData.getInstance().getPresentPrice(name2);
//						newprice3 = LiveData.getInstance().getPresentPrice(name3);
//						result = dm.Judge(name1, name2, Lf1, Lf2, newprice1, newprice2,
//								dm.getLambda(), 0/*last_time_state*/, 0/*last_trade_return*/,
//								-0.02/*spoint*/, dm.getMarket_condition());
//						//���ֲ�������������ʾ��
//						if(Integer.valueOf(String.valueOf(result[0])) != 0){
//							buyprice = Double.valueOf(String.valueOf(result[0]));
//							saleprice = Double.valueOf(String.valueOf(result[1]));
//							//������ʾ��
//							(new Thread(new showDiagThread())).start();
//						}
//						System.out.println("judge result: signal = " + result[0] + " buyprice = " + result[1] + " saleprice = " + result[2]);
//						
					}
					//�Ժ�Լ2,3�����ж�       ��ʱ����!!!
//					if(!info.get(i).getToBuy().equals(name2)
//						&& !info.get(i).getToSell().equals(name3))
//					{
//						result = dm.Open(Lf1, Lf2, newprice2, newprice3, dm.opt_x, dm.opt_y, dm.opt_k);
//						//���ֲ��������µ�     Order							
//					}
					//�Ժ�Լ1,3�����ж�       ��ʱ����!!!
//					if(!info.get(i).getToBuy().equals(name1)
//						&& !info.get(i).getToSell().equals(name3))
//					{
//						result = dm.Open(Lf1, Lf2, newprice2, newprice3, dm.opt_x, dm.opt_y, dm.opt_k);
//						//���ֲ��������µ�     Order							
//					}
					
					//-----------------�Գֲֽ����жϣ����Ƿ���Ҫƽ��
					info = dataInterface.getRepoList();
					if(info != null && info.size() > 0){
						System.out.println("Group Number :" + info.size());
						
						//��ʱֻ�� 1412 1503
						for(int i = 0;i <(info.size()<2 ? info.size() : 2);i++){
							//���ֲ����
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
											newprice1, newprice2,//�ּ�
											info.get(i).gettoBuy_price(),/*buyprice*/
											info.get(i).gettoSell_price(), /*saleprice*/
											dm.opt_k, info.get(i).getSignal(), -1, 1);
									System.out.println("close result: signal = " + result[0] + " buyprice = " + result[1] + " saleprice = " + result[2]);
									if(Integer.valueOf(String.valueOf(result[0])) == 2){
									    //ƽ�ֲ��� trade  ֹӯƽ��
										//������ʾ��
										message = "C���" + name1 + name2 + " ����ֹӯƽ�ֵ�,���Զ�ƽ�֣�\r\nǰ���ֲ������";
										this.sendMsg(message);
										if(dataInterface.Trade(info.get(i).getRepo_ID(), info.get(i).getProfit()))
										{
											arb_groups.remove(i);
											(new Thread(new showDiagThread())).start();
										}
									}
									if(Integer.valueOf(String.valueOf(result[0])) == -2){
									    //ƽ�ֲ��� trade  ֹ��ƽ��
										//������ʾ��
										message = "C���" + name1 + name2 + " ����ֹ��ƽ�ֵ㣡\r\nǰ���ֲ������";
										this.sendMsg(message);
										if(dataInterface.Trade(info.get(i).getRepo_ID(), info.get(i).getProfit()))
										{
											arb_groups.remove(i);
											(new Thread(new showDiagThread())).start();
										}
									}
									if(Integer.valueOf(String.valueOf(result[0])) == 3){
									    //ƽ�ֲ��� trade  ��������ƽ��
										//������ʾ��
										message = "C���" + name1 + name2 + " ������������ƽ�ֵ㣡\r\nǰ���ֲ������";
										this.sendMsg(message);
										if(dataInterface.Trade(info.get(i).getRepo_ID(), info.get(i).getProfit()))
										{
											arb_groups.remove(i);
											(new Thread(new showDiagThread())).start();
										}
									}
									if(Integer.valueOf(String.valueOf(result[0])) == -3){
									    //ƽ�ֲ��� trade  ��������ƽ��
										//������ʾ��
										message = "C���" + name1 + name2 + " ���ﷴ������ƽ�ֵ㣡\r\nǰ���ֲ������";
										this.sendMsg(message);
										if(dataInterface.Trade(info.get(i).getRepo_ID(), info.get(i).getProfit()))
										{
											arb_groups.remove(i);
											(new Thread(new showDiagThread())).start();
										}
									}
									//����ģ�Ͷ����㣬����
									
//								//��ȡ�ּ�
//								newprice1 = LiveData.getInstance().getPresentPrice(name1);
//								newprice2 = LiveData.getInstance().getPresentPrice(name2);
//								newprice3 = LiveData.getInstance().getPresentPrice(name3);
//								result = dm.Judge(name1, name2, Lf1, Lf2, newprice1, newprice2,
//										dm.getLambda(), 0/*last_time_state*/, 0/*last_trade_return*/,
//										-0.02/*spoint*/, dm.getMarket_condition());
//								//���ֲ�������������ʾ��
//								if(Double.valueOf(String.valueOf(result[0])) != 0){
//									buyprice = Double.valueOf(String.valueOf(result[0]));
//									saleprice = Double.valueOf(String.valueOf(result[1]));
//									//������ʾ��
//									(new Thread(new showDiagThread())).start();
//								}
//								System.out.println("judge result: signal = " + result[0] + " buyprice = " + result[1] + " saleprice = " + result[2]);

								
								}catch(Exception e){}
							}
							
						}//end of for
					}//end of if  ƽ�ּ��		
				//��Ӽ۸��¼
				Lf1.add(newprice1);
				Lf2.add(newprice2);
			}//end of if ����ʱ���ж�
			else 
			{
				runtime = false;
			}
			long end = System.currentTimeMillis();
			System.out.println("Analysetime �� " + (double)(end - start) / 1000  + "��");
			sleepsec = 60000 - (end - start);
			sleepsec = sleepsec > 0? sleepsec : 0;
			System.out.println("Sleeptime �� " + (double)sleepsec / 1000  + "��");

			try {
				Thread.sleep(sleepsec);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			//һ��Ľ��׽�����
			if(!runtime && now_hour == 15){
				//dm.Arbitrage_Main(allLf1, allLf2, -1, 1);
				//dm.Arbitrage_Main(allLf2, allLf3, -1, 1);
				//dm.Arbitrage_Main(allLf1, allLf3, -1, 1);
			}
			
			//����ʱ�䣬�߳�����
			while(!runtime){
				now = new Date();
				now_hour = now.getHours();
				now_min = now.getMinutes();
				//��΢��ǰһ��ʱ������
				if((now_hour == 8 && now_min >= 58)
				 || now_hour == 9
				 || now_hour == 14 && now_min >= 43){
					runtime = true;
				}
				else{
					try {
						Thread.sleep(15*60*1000);//15���Ӽ��һ��
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
