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
		
		ArrayList<Double> allLf1,allLf2,//���е���ʷ�۸��¼����ģ�ܴ�
						  Lf1,Lf2;//ǰһ�����յ����ڵļ۸��¼����Խ�С,��Լ240������
		ArrayList<Repository> info = dataInterface.getRepoList();
		double newprice1, newprice2,newprice3;
		String name1 = arb_details.get(0).getSymbol(),
				name2 =arb_details.get(1).getSymbol(),
				name3 =arb_details.get(2).getSymbol();
		boolean runtime = true;
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
	            int line = 1;
	            // һ�ζ���һ�У�ֱ������nullΪ�ļ�����
	            while ((tempString = reader.readLine()) != null) {
	                // ��ʾ�к�
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
			 //����ʱ��  9:15-11:30��13:00-15:15
				if(	  (now_hour == 9 && now_min >= 15)         //09:15-09:59
				   || (now_hour > 9 && now_hour <11)	 	 //10:00-10:59	
				   || (now_hour == 11 && now_min <= 30)	 //11:00-11:30
				   || (now_hour >= 13 && now_hour <15)	 	 //13:00-14:59
				   || (now_hour == 15 && now_min <= 15)		 //15:00-15:15
						)
			{						
				//һ���ӵ���һ��dm��ĺ���

					//��ȡ�ּ�
					newprice1 = LiveData.getInstance().getPresentPrice(name1);
					newprice2 = LiveData.getInstance().getPresentPrice(name2);
					newprice3 = LiveData.getInstance().getPresentPrice(name3);
					info = dataInterface.getRepoList();
			        //-----------------�Ի�δ���ֵ���Ͻ����жϣ����Ƿ��ʺϽ���
					for(int i = 0;i < info.size();i++){
						//�Ժ�Լ1,2�����ж�
						if((!info.get(i).getToBuy().equals(name1)
							&& !info.get(i).getToSell().equals(name2)))
						{
							//��ȡ�ּ�
							newprice1 = LiveData.getInstance().getPresentPrice(name1);
							newprice2 = LiveData.getInstance().getPresentPrice(name2);
							newprice3 = LiveData.getInstance().getPresentPrice(name3);
							result = dm.Open(Lf1, Lf2, newprice1, newprice2, dm.opt_x, dm.opt_y, dm.opt_k);
							//���ֲ�������������ʾ��
							if(Double.valueOf(String.valueOf(result[0])) != 0){
								buyprice = Double.valueOf(String.valueOf(result[0]));
								saleprice = Double.valueOf(String.valueOf(result[1]));
								//������ʾ��
								(new Thread(new showDiagThread())).start();
							}
							System.out.println("open result: signal = " + result[0] + " buyprice = " + result[1] + " saleprice = " + result[2]);
						}
						//�Ժ�Լ2,3�����ж�       ��ʱ����!!!
//						if(!info.get(i).getToBuy().equals(name2)
//							&& !info.get(i).getToSell().equals(name3))
//						{
//							result = dm.Open(Lf1, Lf2, newprice2, newprice3, dm.opt_x, dm.opt_y, dm.opt_k);
//							//���ֲ��������µ�     Order							
//						}
						//�Ժ�Լ1,3�����ж�       ��ʱ����!!!
//						if(!info.get(i).getToBuy().equals(name1)
//							&& !info.get(i).getToSell().equals(name3))
//						{
//							result = dm.Open(Lf1, Lf2, newprice2, newprice3, dm.opt_x, dm.opt_y, dm.opt_k);
//							//���ֲ��������µ�     Order							
//						}
					}
							        
					//-----------------�Գֲֽ����жϣ����Ƿ���Ҫƽ��
					if(info.size() > 0){
						System.out.println("Group Number :" + info.size());
						
						//��ʱֻ�� 1412 1503
						for(int i = 0;i <(info.size()<2 ? info.size() : 2);i++){
							try{
								System.out.println("Group " + (i +1));
								System.out.println("BuyPirce:" + info.get(i).getToBuy() + "  " + info.get(0).gettoBuy_price());
								System.out.println("SalePirce:" + info.get(i).getToSell() + "  "  + info.get(0).gettoSell_price());

								newprice1 = LiveData.getInstance().getPresentPrice(name1);
								newprice2 = LiveData.getInstance().getPresentPrice(name2);
								newprice3 = LiveData.getInstance().getPresentPrice(name3);
								result = dm.Close(Lf1, Lf2, 
										newprice1, newprice2,//�ּ�
										info.get(i).gettoBuy_price(),/*buyprice*/
										info.get(i).gettoSell_price(), /*saleprice*/
										dm.opt_k, info.get(i).getSignal(), -1, 1);
								System.out.println("close result: signal = " + result[0] + " buyprice = " + result[1] + " saleprice = " + result[2]);
								if(Double.valueOf(String.valueOf(result[0])) == 2){
								    //ƽ�ֲ��� trade  ֹӯƽ��
								}
								if(Double.valueOf(String.valueOf(result[0])) == -2){
								    //ƽ�ֲ��� trade  ֹ��ƽ��
								}
								if(Double.valueOf(String.valueOf(result[0])) == 3){
								    //ƽ�ֲ��� trade  ��������ƽ��
								}
								if(Double.valueOf(String.valueOf(result[0])) == -3){
								    //ƽ�ֲ��� trade  ��������ƽ��
								}
								}catch(Exception e){}
						}
					}		
				//��Ӽ۸��¼
				Lf1.add(newprice1);
				Lf2.add(newprice2);
			}
			else 
			{
				runtime = false;
			}
			long end = System.currentTimeMillis();
			System.out.println("Analysetime �� " + (double)(end - start) / 1000  + "��");
			sleepsec = 60000 - (end - start);
			sleepsec = sleepsec > 0? sleepsec : 0;
			System.out.println("Sleeptime �� " + (double)sleepsec / 1000  + "��");
			
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
		
		//(new ThreadDiag(null,test,"��ʾ")).start();//�����ȴ���ʾ���߳�
	}*/
};//
