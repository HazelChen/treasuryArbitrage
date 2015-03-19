package edu.nju.treasuryArbitrage.model.ctp;

public class snapshotOfData {
		///������
		public String	TradingDay;
		///��Լ����
		public String	InstrumentID;
		///����������
		public String	ExchangeID;
		///��Լ�ڽ������Ĵ���
		public String	ExchangeInstID;
		///���¼�
		public double	LastPrice;
		///�ϴν����
		public double	PreSettlementPrice;
		///������
		public double	PreClosePrice;
		///��ֲ���
		public double	PreOpenInterest;
		///����
		public double	OpenPrice;
		///��߼�
		public double	HighestPrice;
		///��ͼ�
		public double	LowestPrice;
		///����
		public double	Volume;
		///�ɽ����
		public double	Turnover;
		///�ֲ���
		public double	OpenInterest;
		///������
		public double	ClosePrice;
		///���ν����
		public double	SettlementPrice;
		///��ͣ���
		public double	UpperLimitPrice;
		///��ͣ���
		public double	LowerLimitPrice;
		///����ʵ��
		public double	PreDelta;
		///����ʵ��
		public double	CurrDelta;
		///����޸�ʱ��
		public String	UpdateTime;
		///����޸ĺ���
		public double	UpdateMillisec;
		///�����һ
		public double	BidPrice1;
		///������һ
		public double	BidVolume1;
		///������һ
		public double	AskPrice1;
		///������һ
		public double	AskVolume1;
		///����۶�
		public double	BidPrice2;
		///��������
		public double	BidVolume2;
		///�����۶�
		public double	AskPrice2;
		///��������
		public double	AskVolume2;
		///�������
		public double	BidPrice3;
		///��������
		public double	BidVolume3;
		///��������
		public double	AskPrice3;
		///��������
		public double	AskVolume3;
		///�������
		public double	BidPrice4;
		///��������
		public double	BidVolume4;
		///��������
		public double	AskPrice4;
		///��������
		public double	AskVolume4;
		///�������
		public double	BidPrice5;
		///��������
		public double	BidVolume5;
		///��������
		public double	AskPrice5;
		///��������
		public double	AskVolume5;
		///���վ���
		public double	AveragePrice;
		
		public snapshotOfData(CThostFtdcDepthMarketDataField data){
			this.AskPrice1 = data.AskPrice1;
			this.AskPrice2 = data.AskPrice2;
			this.AskPrice3 = data.AskPrice3;
			this.AskPrice4 = data.AskPrice4;
			this.AskPrice5 = data.AskPrice5;
			this.AskVolume1 = data.AskVolume1;
			this.AskVolume2 = data.AskVolume2;
			this.AskVolume3 = data.AskVolume3;
			this.AskVolume4 = data.AskVolume4;
			this.AskVolume5 = data.AskVolume5;
			this.AveragePrice = data.AveragePrice;
			this.BidPrice1 = data.BidPrice1;
			this.BidPrice2 = data.BidPrice2;
			this.BidPrice3 = data.BidPrice3;
			this.BidPrice4 = data.BidPrice4;
			this.BidPrice5 = data.BidPrice5;
			this.BidVolume1 = data.BidVolume1;
			this.BidVolume2 = data.BidVolume2;
			this.BidVolume3 = data.BidVolume3;
			this.BidVolume4 = data.BidVolume4;
			this.BidVolume5 = data.BidVolume5;
			this.ClosePrice = data.ClosePrice;
			this.CurrDelta = data.CurrDelta;
			this.ExchangeID = data.ExchangeID;
			this.ExchangeInstID = data.ExchangeInstID;
			this.HighestPrice = data.HighestPrice;
			this.InstrumentID = data.InstrumentID;
			this.LastPrice = data.LastPrice;
			this.LowerLimitPrice = data.LowerLimitPrice;
			this.LowestPrice = data.LowestPrice;
			this.OpenInterest = data.OpenInterest;
			this.OpenPrice = data.OpenPrice;
			this.PreClosePrice = data.PreClosePrice;
			this.PreDelta = data.PreDelta;
			this.PreOpenInterest = data.PreOpenInterest;
			this.PreSettlementPrice = data.PreSettlementPrice;
			this.SettlementPrice = data.SettlementPrice;
			this.TradingDay = data.TradingDay;
			this.Turnover = data.Turnover;
			this.UpdateMillisec = data.UpdateMillisec;
			this.UpdateTime = data.UpdateTime;
			this.UpperLimitPrice = data.UpperLimitPrice;
			this.Volume = data.Volume;
		}
}
