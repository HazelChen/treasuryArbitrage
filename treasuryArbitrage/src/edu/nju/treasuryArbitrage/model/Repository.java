package edu.nju.treasuryArbitrage.model;

import java.util.ArrayList;

import edu.nju.treasuryArbitrage.controller.threads.LiveData;

public class Repository {
	int repo_ID;	//�ֱֲ�ǡ����������������Ҹо����б�Ҫ
	long time;	//���׽���ʱ��
	int count;		//��������
	double guarantee;	//Ͷ�뱣֤��
	double profit;	//���������������ó�
	
	int signal;     //����״̬       signal=1�������������֣�signal=-1�������������֣�signal=0�������ס�signal=0�������ף�signal=2��ֹӯƽ�֣�signal=-2,ֹ��ƽ��,signal=3����������ƽ�֣�signal=-3����������ƽ�֣�
	
	String toSell;	//��ͷ
	String toBuy;	//��ͷ
	double toSell_price;
	double toBuy_price;
	
	double sellPrecentPrice;
	double buyPrecentPrice;
	
	public Repository(){
	}
	
	public void update() {
		ArbDetail buyArb = getLiveArb_detail(toBuy);
		ArbDetail sellArb = getLiveArb_detail(toSell);
		if (buyArb == null || sellArb == null) {
			return;
		}
		profit = (buyArb.getPresentPrice() - toBuy_price + toSell_price - sellArb.getPresentPrice())
				* count * 10000;
		sellPrecentPrice = sellArb.getPresentPrice();
		buyPrecentPrice = buyArb.getPresentPrice();
	}

	public double getFormatProfit() {
		return (int) (profit * 1000) / 1000.0;
	}
	
	private ArbDetail getLiveArb_detail(String symble) {
		ArrayList<ArbDetail> arb_details = LiveData.getInstance()
				.getArbDetails();
		for (ArbDetail arb_detail : arb_details) {
			if (arb_detail.getSymbol().equals(symble)) {
				return arb_detail;
			}
		}
		return null;
	}
	
	
	public double getSellPrecentPrice() {
		return sellPrecentPrice;
	}

	public double getBuyPrecentPrice() {
		return buyPrecentPrice;
	}

	public int getSignal() {
		return signal;
	}
	public int getRepo_ID() {
		return repo_ID;
	}
	public void setRepo_ID(int repo_ID) {
		this.repo_ID = repo_ID;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getGuarantee() {
		return guarantee;
	}
	public void setGuarantee(double guarantee) {
		this.guarantee = guarantee;
	}
	public double getProfit() {
		return profit;
	}
	public void setProfit(double profit) {
		this.profit = profit;
	}
	public String getToSell() {
		return toSell;
	}
	public void setToSell(String toSell) {
		this.toSell = toSell;
	}
	public String getToBuy() {
		return toBuy;
	}
	public void setToBuy(String toBuy) {
		this.toBuy = toBuy;
	}
	public double gettoBuy_price() {
		return toBuy_price;
	}
	public void settoBuy_price(double toBuy_price) {
		this.toBuy_price = toBuy_price;
	}
	public double gettoSell_price() {
		return toSell_price;
	}
	public void settoSell_price(double d) {
		this.toSell_price = d;
	}
	
}
