package edu.nju.treasuryArbitrage.controller.threads;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import edu.nju.treasuryArbitrage.controller.dataInterface.DataInterface2Matlab;
import edu.nju.treasuryArbitrage.model.ArbBrief;
import edu.nju.treasuryArbitrage.model.LiveData;

public class AnalyseThread implements Runnable {
	double buyprice = 0, saleprice = 0,newprice1 = -1,newprice2 = -1,newprice3 = -1,
			price1,price2;
	ArrayList<ArbBrief> hLf1=new ArrayList<ArbBrief>(),
			hLf2=new ArrayList<ArbBrief>(),
			hLf3=new ArrayList<ArbBrief>();
	ArbBrief lastArbBrief = null;
	ArrayList<Double> Lf1=new ArrayList<Double>(),
			Lf2=new ArrayList<Double>();
	String message = "DMsg",Name1="",Name2="",logFile;
	DataInterface2Matlab dm= new DataInterface2Matlab();
	Object[] result = null;
	int signal,stop_loss=-1,stop_profit=1,selModel=0,selGroup=0;
	Date now=new Date();
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
	boolean newLogFile=false;
	String[] FuturesNames;//合约名称
	double x,y,k;//FXY 策略参数
	ArrayList<Double> Lambda=new ArrayList<Double>();//WXY 策略参数
	double T,kdj;//DJ 策略参数
	
	public void run() {
		x=0;y=0;k=0;
		
		//------------------------------------------//
		//Do not delete any comment!!!  Important!!!
		//------------------------------------------//

		// get FuturesNames
		FuturesNames=LiveData.getInstance().getFuturesCodes();
		//Get Contract(1、2、3) history price price(open -> now) from local
		hLf1=LiveData.getInstance().getHistoryPrice(FuturesNames[0]);
		hLf2=LiveData.getInstance().getHistoryPrice(FuturesNames[1]);
		hLf3=LiveData.getInstance().getHistoryPrice(FuturesNames[2]);
		//Modify data to make 3 hlf length be same
		
		
		
		while (true) {
			now=new Date();
			//get Contract(1、2、3)  price(now)
			//newprice1=getPrice(1);
			newprice1=LiveData.getInstance().getPresentPrice(FuturesNames[0]);
			newprice2=LiveData.getInstance().getPresentPrice(FuturesNames[1]);
			newprice3=LiveData.getInstance().getPresentPrice(FuturesNames[2]);
			//Select Model(1/2/3)
			selModel=1;
			for(selModel=1;selModel<4;selModel++)
			{
				//Select Group(1/2/3)  1/12；  2/13；  3/23
				selGroup=1;
				for(selGroup=1;selGroup<4;selGroup++)
				{
					//Set price1/2 name1/2
					switch(selGroup)
					{
					case 1:
						price1=newprice1;price2=newprice2;
						Lf1=getPrice(hLf1);Lf2=getPrice(hLf2);
						//get FuturesNames names
						Name1=FuturesNames[0];Name2=FuturesNames[1];
						break;
					case 2:
						price1=newprice1;price2=newprice3;
						Lf1=getPrice(hLf1);Lf2=getPrice(hLf3);
						//get FuturesNames names
						Name1=FuturesNames[0];Name2=FuturesNames[2];
						break;
					case 3:
						price1=newprice2;price2=newprice3;
						Lf1=getPrice(hLf2);Lf2=getPrice(hLf3);
						//get FuturesNames names
						Name1=FuturesNames[1];Name2=FuturesNames[2];
						break;
					}
					logFile="OpenCloseLogGroup" + Name1 + "" + Name2 + "Model" + selModel + ".log";
					//get Model(1/2/3)params{x,y,k}{ol1, ol2}{k,T} from server
//					double[] params=getFromServer(selGroup);
//					x = params[0];
//					y = params[1];
//					k = params[2];
					//Get WXY Strategy params
					//Lambda=getLambda();
//					kdj=getkdj(selGroup);
//					T=getT(selGroup);
					
					//open
					switch(selModel){
						case 1:result=dm.Open(Lf1, Lf2, price1, price2, x, y, k);break;
						case 2:result=dm.Open1(Lf1, Lf2, price1, price2, Lambda);break;
						case 3:result=dm.Open2(Lf1, Lf2, price1, price2, k, T);break;
					}
					if(result!=null)
					{
						signal=Integer.parseInt(String.valueOf(result[0])); 
						buyprice=Integer.parseInt(String.valueOf(result[1]));
						saleprice=Integer.parseInt(String.valueOf(result[2]));
						//out into log
						try{  
							  String str=sdf.format(now);
						      String data = str + "\t " + signal + "\t" 
						    		  	+ buyprice + "\t" + saleprice + "\r\n";
						      File file =new File(logFile);
						      //if file doesnt exists, then create it
						      if(!file.exists()){
						       file.createNewFile();
						       newLogFile=true;
						      }
						      //true = append file
						      FileWriter fileWritter = new FileWriter(file.getName(),true);
						             BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
						             if(newLogFile){
						            	 String logTitle = "\tTime" + "\t\t " + "Signal" 
									    		  	+ "\t" + "BuyPrice" + " " + "SalePrice\r\n";
						            	 bufferWritter.write(logTitle);
						            	 newLogFile=false;
						             }
						             bufferWritter.write(data);
						             bufferWritter.close();

						     }catch(IOException e){e.printStackTrace();}
					}
					
					
					//close
					//get signal buyprice saleprice
					
					switch(selModel){
						case 1:
							result=dm.Close(Lf1, Lf2, price1, price2, buyprice,
									saleprice, k, signal, stop_loss, stop_profit);break;
						case 2:
							result=dm.Close1(Lf1, Lf2, price1, price2, buyprice,
									saleprice, Lambda, signal, stop_loss, stop_profit);break;
						case 3:
							result=dm.Close2(Lf1, Lf2, price1, price2, buyprice,
									saleprice, signal,k, T, stop_loss, stop_profit);break;
					}
					if(result!=null)
					{
						signal=Integer.parseInt(String.valueOf(result[0])); 
						buyprice=Integer.parseInt(String.valueOf(result[1]));
						saleprice=Integer.parseInt(String.valueOf(result[2]));
						//out into log
						try{  
							  String str=sdf.format(now);
						      String data = str + "\t " + signal + "\t" 
						    		  	+ buyprice + "\t" + saleprice + "\r\n";
						      File file =new File(logFile);
						      //if file doesnt exists, then create it
						      if(!file.exists()){
						       file.createNewFile();
						       newLogFile=true;
						      }
						      //true = append file
						      FileWriter fileWritter = new FileWriter(file.getName(),true);
						             BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
						             if(newLogFile){
						            	 String logTitle = "\tTime" + "\t\t " + "Signal" 
									    		  	+ "\t" + "BuyPrice" + " " + "SalePrice\r\n";
						            	 bufferWritter.write(logTitle);
						            	 newLogFile=false;
						             }
						             bufferWritter.write(data);
						             bufferWritter.close();

						     }catch(IOException e){e.printStackTrace();}
					}
				}//for selGroup
			}//for selModel						
			
			//Refresh hLf1,hLf2,hLf3
			lastArbBrief=new ArbBrief(FuturesNames[0],dateFormat.format(now),newprice1);
			hLf1.add(lastArbBrief);
			lastArbBrief=new ArbBrief(FuturesNames[1],dateFormat.format(now),newprice2);
			hLf2.add(lastArbBrief);
			lastArbBrief=new ArbBrief(FuturesNames[2],dateFormat.format(now),newprice3);
			hLf3.add(lastArbBrief);
			
			try {
				Thread.sleep(5);// 0.005s
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}// end of while(true)
	}
	private ArrayList<Double> getPrice(ArrayList<ArbBrief> hLf) {
		ArrayList<Double> result = new ArrayList<Double>();
		for(int i=0;i<hLf.size();i++)
			result.add(hLf.get(i).getPrice());
		return result;
	}
	
	public static void main(String[] args){
		Date now=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str=sdf.format(now);
	      String str2 = "\tTime" + "\t\t " + "Signal" 
	    		  	+ "\t" + "BuyPrice" + " " + "SalePrice\r\n";
	      String str3 = str + "\t " + 31 
	    		  	+ "\t" + 32 + "\t " + 33 + "\r\n";
		System.out.println(str2);System.out.println(str3);
		boolean newfile=false;
		try{  
		      File file =new File("OC.log");
		      //if file doesnt exists, then create it
		      if(!file.exists()){
		       file.createNewFile();
		       newfile=true;
		      }
		      //true = append file
		      FileWriter fileWritter = new FileWriter(file.getName(),true);
		             BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
		             if(newfile){
		            	 bufferWritter.write(str2);
		             }
		             bufferWritter.write(str3);
		             bufferWritter.close();

		     }catch(IOException e){e.printStackTrace();}
		//System.out.println(System.getProperty("user.dir"));//user.dir指定了当前的路径
	}

	public double getBuyprice() {
		return buyprice;
	}

	public double getSaleprice() {
		return saleprice;
	}
}
