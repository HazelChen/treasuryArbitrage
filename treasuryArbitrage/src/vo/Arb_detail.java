package vo;

//�������Ҫ�޸�
public class Arb_detail {
	String symbol;				//����
	String name;				//����
	double presentPrice;		//�ּ�
	double change;				//�Ƿ�
	double priceChange;			//����
	double callBuy;				//����
	double callSell;			//����
	int bid;					//����
	int ask;					//����
	int nvol;					//����
	int vol;					//����
	int repository;				//�ֲ�
	int warehouse;				//����
	int dailyWarehouse;			//������
	double preClose;			//����
	double open;				//��
	double high;				//���
	double low;					//���
	double fullAmount;			//���
	double preSettlePrice;		//���
	double settlePrice;			//���
	double swing;				//���
	double ratio;				//����
	double sedMoney;			//�����ʽ�
	double fundFlow;			//�ʽ�����
	int outvol;					//����
	int invol;					//����
	
	public Arb_detail(String id){
		this.treasuryID = id;
		init();
	}
	
	//���ø��ֲ���
	public void init(){}
}
