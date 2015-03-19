package edu.nju.treasuryArbitrage.model;


public class ArbDetail {
	String symbol;			//***��ծ�ڻ�����
	String month;			//***�����·�
	
	//==========================================================
	//˵��1��ȥ��time����ԭ����day��Ϊclock;date,clock��ΪString����
	//˵��2��change��swingȡ��Ϊ��%�ŵ�String����ת��Ϊdouble����ʹ��ʱ��Ҫ ����*100�����%��ʾʵ�ʺ���
	//==========================================================
	String date;			//***����
	String clock;				//***ʱ��
	//long time;				//***ʱ��
	
	double presentPrice;	//***�ּۣ����ּۡ����ڡ��ɽ������ڡ���Լ�۸񡱣�ͳһ���ּۣ�
	double change;			//***�ǵ������Ƿ������ǵ�����ͳһ���ǵ�����
	double	priceChange;	//***����

	double  bidPirce;		//***���
	double askPrice;		//***����
	int bid;				//***����
	int ask;				//***����

	//==========================================================
	//˵��3��ȥ��nvol,settlePrice,ratio,outvol,invol
	//==========================================================
	//int nvol;				//***����
 	int vol;				//***�ɽ��������ֵ��ڳɽ�����ͳһ�ɳɽ�����
	int preRepository;		//***��ֲ�
	int repository;			//***�ֲ���
	
	int dailyWarehouse;		//***������
	double preClose;		//***����
	double open;			//***���̣��񿪵��ڿ��̣�ͳһ�ɿ��̣�
	double high;			//***���
	double low;				//***���
	double fullAmount;		//***���
	double preSettlePrice;	//***ǰ����ۣ�������ǰ����ۣ�ͳһ��ǰ����ۣ�
	//double settlePrice;		//***���
	double swing;			//***���
	//double ratio;			//***����


	double hardenPrice;		//***��ͣ
	double limitPrice;		//***��ͣ	
	//int outvol;				//***����
	//int invol;				//***����

	double averPrice;		//***����
	
	
	public ArbDetail(){}
	
	
	private ArbDetail cloneArb() {
		ArbDetail arb_detail = new ArbDetail();
		arb_detail.symbol = this.symbol;
		arb_detail.month = this.month;
		arb_detail.date = this.date;
		arb_detail.clock = this.clock;
		//arb_detail.time = this.time;
		arb_detail.presentPrice = this.presentPrice;
		arb_detail.change = this.change;
		arb_detail.priceChange = this.priceChange;
		arb_detail.bidPirce = this.bidPirce;
		arb_detail.askPrice = this.askPrice;
		arb_detail.bid = this.bid;
		arb_detail.ask = this.ask;
		//arb_detail.nvol = this.nvol;
		arb_detail.vol = this.vol;
		arb_detail.preRepository = this.preRepository;
		arb_detail.repository = this.repository;
		arb_detail.dailyWarehouse = this.dailyWarehouse;
		arb_detail.preClose = this.preClose;
		arb_detail.open = this.open;
		arb_detail.high = this.high;
		arb_detail.low = this.low;
		arb_detail.fullAmount = this.fullAmount;
		arb_detail.preSettlePrice = this.preSettlePrice;
		//arb_detail.settlePrice = this.settlePrice;
		arb_detail.swing = this.swing;
		//arb_detail.ratio = this.ratio;
		arb_detail.hardenPrice = this.hardenPrice;
		arb_detail.limitPrice = this.limitPrice;
		//arb_detail.outvol = this.outvol;
		//arb_detail.invol = this.invol;
		arb_detail.averPrice = this.averPrice;
		return arb_detail;
	}
	
	public ArbDetail getFormattedArb_detail() {
		ArbDetail arb_detail = cloneArb();
		
		arb_detail.presentPrice = format(presentPrice);
		arb_detail.change = format(change);
		arb_detail.bidPirce = format(bidPirce);
		arb_detail.preSettlePrice = format(preSettlePrice);
		arb_detail.high = format(high);
		arb_detail.low = format(low);
		arb_detail.swing = format(swing);
		arb_detail.fullAmount = format(fullAmount);
		
		return arb_detail;
	}
	
	private double format(double num) {
		return (int)(num * 1000) / 1000.0;
	}
	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getClock() {
		return clock;
	}

	public void setClock(String clock) {
		this.clock = clock;
	}
	
	public double getPresentPrice() {
		return presentPrice;
	}


	public void setPresentPrice(double presentPrice) {
		this.presentPrice = presentPrice;
	}


	public double getChange() {
		return change;
	}


	public void setChange(double change) {
		this.change = change;
	}


	public double getPriceChange() {
		return priceChange;
	}


	public void setPriceChange(double priceChange) {
		this.priceChange = priceChange;
	}


	public double getBidPirce() {
		return bidPirce;
	}


	public void setBidPirce(double bidPirce) {
		this.bidPirce = bidPirce;
	}


	public double getAskPrice() {
		return askPrice;
	}


	public void setAskPrice(double askPrice) {
		this.askPrice = askPrice;
	}


	public int getBid() {
		return bid;
	}


	public void setBid(int bid) {
		this.bid = bid;
	}


	public int getAsk() {
		return ask;
	}


	public void setAsk(int ask) {
		this.ask = ask;
	}


//	public int getNvol() {
//		return nvol;
//	}
//
//
//	public void setNvol(int nvol) {
//		this.nvol = nvol;
//	}


	public int getVol() {
		return vol;
	}


	public void setVol(int vol) {
		this.vol = vol;
	}


	public int getPreRepository() {
		return preRepository;
	}


	public void setPreRepository(int preRepository) {
		this.preRepository = preRepository;
	}


	public int getRepository() {
		return repository;
	}


	public void setRepository(int repository) {
		this.repository = repository;
	}

	
	public int getDailyWarehouse() {
		return dailyWarehouse;
	}


	public void setDailyWarehouse(int dailyWarehouse) {
		this.dailyWarehouse = dailyWarehouse;
	}


	public double getPreClose() {
		return preClose;
	}


	public void setPreClose(double preClose) {
		this.preClose = preClose;
	}


	public double getOpen() {
		return open;
	}


	public void setOpen(double open) {
		this.open = open;
	}


	public double getHigh() {
		return high;
	}


	public void setHigh(double high) {
		this.high = high;
	}


	public double getLow() {
		return low;
	}


	public void setLow(double low) {
		this.low = low;
	}


	public double getFullAmount() {
		return fullAmount;
	}


	public void setFullAmount(double fullAmount) {
		this.fullAmount = fullAmount;
	}


	public double getPreSettlePrice() {
		return preSettlePrice;
	}


	public void setPreSettlePrice(double preSettlePrice) {
		this.preSettlePrice = preSettlePrice;
	}


//	public double getSettlePrice() {
//		return settlePrice;
//	}
//
//
//	public void setSettlePrice(double settlePrice) {
//		this.settlePrice = settlePrice;
//	}


	public double getSwing() {
		return swing;
	}


	public void setSwing(double swing) {
		this.swing = swing;
	}


//	public double getRatio() {
//		return ratio;
//	}
//
//
//	public void setRatio(double ratio) {
//		this.ratio = ratio;
//	}


	public double getHardenPrice() {
		return hardenPrice;
	}


	public void setHardenPrice(double hardenPrice) {
		this.hardenPrice = hardenPrice;
	}


	public double getLimitPrice() {
		return limitPrice;
	}


	public void setLimitPrice(double limitPrice) {
		this.limitPrice = limitPrice;
	}


//	public int getOutvol() {
//		return outvol;
//	}
//
//
//	public void setOutvol(int outvol) {
//		this.outvol = outvol;
//	}
//
//
//	public int getInvol() {
//		return invol;
//	}
//
//
//	public void setInvol(int invol) {
//		this.invol = invol;
//	}


	public double getAverPrice() {
		return averPrice;
	}


	public void setAverPrice(double averPrice) {
		this.averPrice = averPrice;
	}


	public static ArbDetail nullObject() {
		ArbDetail arb_detail = new ArbDetail();
		arb_detail.symbol = "-";
		arb_detail.month = "-";
		arb_detail.date = "-";
		arb_detail.clock = "-";
		return arb_detail;
	}


//	public long getTime() {
//		return time;
//	}
//
//
//	public void setTime(long time) {
//		this.time = time;
//	}

}
