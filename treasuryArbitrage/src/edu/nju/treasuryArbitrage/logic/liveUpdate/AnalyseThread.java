package edu.nju.treasuryArbitrage.logic.liveUpdate;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
		while(true){
			try {
					Thread.sleep(5);//0.005s
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		}//end of while(true)
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


}
