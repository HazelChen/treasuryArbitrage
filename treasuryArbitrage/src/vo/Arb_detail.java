package vo;

//�������Ҫ�޸�
public class Arb_detail {
	String treasuryID;
	String symbol;				//����
	String month;               //�����·�
	String name;				//����
	double presentPrice;		//�ּ�
	double change;				//�ǵ���
	double priceChange;			//�ǵ�
	double bidPrice;            //���
	double askPrice;            //����
	int bid;					//����
	int ask;					//����
	int nvol;					//����
	int vol;					//����
	int trade;                  //�ɽ���
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
	double hardenPrice;         //��ͣ
	double limitPrice;          //��ͣ
	int outvol;					//����
	int invol;					//����
	
	public Arb_detail(String id){
		this.treasuryID = id;
		init();
	}
	
	//���ø��ֲ���
	public void init(){}
}
