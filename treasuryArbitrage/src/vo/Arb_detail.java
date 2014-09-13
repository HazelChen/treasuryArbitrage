package vo;

public class Arb_detail {
	String treasuryID;		//国债期货ID
	String symbol;			//国债期货代码
	String month;			//交割月份
	String name;			//国债期货名称
	double presentPrice;	//现价（“现价”等于“成交”等于“合约价格”，统一成现价）
	double change;			//涨跌幅（涨幅等于涨跌幅，统一成涨跌幅）
	double	priceChange;	//跌涨


	double  bidPirce;		//买价
	double askPrice;		//卖价
	int bid;				//买量
	int ask;				//卖量


	int nvol;				//现手
 	int vol;				//成交量（总手等于成交量，统一成成交量）
	int preRepository;		//昨持仓
	int repository;			//持仓量
	
	int warehouse;			//增仓
	int dailyWarehouse;		//日增仓
	double preClose;		//昨收
	double open;			//开盘（今开等于开盘，统一成开盘）
	double high;			//最高
	double low;				//最低
	double fullAmount;		//金额
	double preSettlePrice;	//前结算价（昨结等于前结算价，统一成前结算价）
	double settlePrice;		//今结
	double swing;			//振幅
	double ratio;			//量比


	double hardenPrice;		//涨停
	double limitPrice;		//跌停	
	int outvol;				//外盘
	int invol;				//内盘
	int sellOrder;			//卖盘
	int buyOrder;			//买盘

	double averPrice;		//均价
	long time;				//时间
	double committeeThan;	//委比
	
	
	public Arb_detail(String id){
		this.treasuryID = id;
		init();
	}
	
	//设置各种参数
	public void init(){}
}
