package vo;

//这个类需要修改
public class Arb_detail {
	String treasuryID;
	String symbol;				//代码
	String month;               //交易月份
	String name;				//名称
	double presentPrice;		//现价
	double change;				//涨跌幅
	double priceChange;			//涨跌
	double bidPrice;            //买价
	double askPrice;            //卖价
	int bid;					//买量
	int ask;					//卖量
	int nvol;					//现手
	int vol;					//总手
	int trade;                  //成交量
	int repository;				//持仓
	int warehouse;				//增仓
	int dailyWarehouse;			//日增仓
	double preClose;			//昨收
	double open;				//今开
	double high;				//最高
	double low;					//最低
	double fullAmount;			//金额
	double preSettlePrice;		//昨结
	double settlePrice;			//今结
	double swing;				//振幅
	double ratio;				//量比
	double sedMoney;			//沉淀资金
	double fundFlow;			//资金流向
	double hardenPrice;         //涨停
	double limitPrice;          //跌停
	int outvol;					//外盘
	int invol;					//内盘
	
	public Arb_detail(String id){
		this.treasuryID = id;
		init();
	}
	
	//设置各种参数
	public void init(){}
}
