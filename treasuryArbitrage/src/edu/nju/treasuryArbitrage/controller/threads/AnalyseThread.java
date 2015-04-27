package edu.nju.treasuryArbitrage.controller.threads;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import edu.nju.treasuryArbitrage.controller.dataInterface.DataInterface2Matlab;
import edu.nju.treasuryArbitrage.model.LiveData;

public class AnalyseThread implements Runnable {
	double buyprice = 0, saleprice = 0,newprice1 = -1,newprice2 = -1,newprice3 = -1,
			price1,price2;
	double x,y,k;
	ArrayList<Double> hLf1=new ArrayList<Double>(),
			hLf2=new ArrayList<Double>(),
			hLf3=new ArrayList<Double>();
	ArrayList<Double> Lf1=new ArrayList<Double>(),
			Lf2=new ArrayList<Double>();
	String message = "DMsg",Name1="",Name2="";
	DataInterface2Matlab dm= new DataInterface2Matlab();
	Object[] result = null;
	int signal,stop_loss=-1,stop_profit=1,selModel=0,selGroup=0;
	Date now=new Date();
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	boolean newLogFile=false;
	String[] ContrNames;//合约名称
	
	public void run() {
		x=0;y=0;k=0;
		
		//------------------------------------------//
		//Do not delete any comment!!!  Important!!!
		//------------------------------------------//

		// get ContrNames
		
		//Get Contract(1、2、3) history price price(open -> now) from local
		
		while (true) {
			now=new Date();
			//Refresh hLf1,hLf2,hLf3
			if(!(newprice1<0 && newprice2 < 0 && newprice3 < 0))
			{
				hLf1.add(newprice1);
				hLf2.add(newprice2);
				hLf3.add(newprice3);
			}
			//get Contract(1、2、3)  price(now)
			//newprice1=getPrice(1);
			LiveData.getInstance().getPresentPrice(Name1);
			//Select Model(1/2/3)
			selModel=1;
			for(selModel=1;selModel<4;selModel++)
			{}
			//Select Group(1/2/3)  1/12；  2/13；  3/23
			selGroup=1;
			//Set price1/2
			switch(selGroup)
			{
			case 1:price1=newprice1;price2=newprice2;break;
			case 2:price1=newprice1;price2=newprice3;break;
			case 3:price1=newprice2;price2=newprice3;break;
			}
			//get Model(1/2/3)params{x,y,k} from server
//			double[] params=getFromServer(selModel*10+selGroup);
//			x = params[0];
//			y = params[1];
//			k = params[2];
			
			//get Contract names
			Name1="TF1506";Name2="TF1509";//示例
			
			//get history price(open -> now) from local
			//
			
			//Modify data    no need best
			
			//open
			result=dm.Open(Lf1, Lf2, price1, price2, x, y, k);
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
				      File file =new File("OCfxy.log");
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
			
			result=dm.Close(Lf1, Lf2, price1, price2, buyprice,
					saleprice, k, signal, stop_loss, stop_profit);
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
				      File file =new File("OCfxy.log");
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
			
			try {
				Thread.sleep(5);// 0.005s
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}// end of while(true)
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
