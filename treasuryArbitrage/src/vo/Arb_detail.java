package vo;

public class Arb_detail {
	String treasuryID;		//��ծ�ڻ�ID
	String symbol;			//��ծ�ڻ�����
	String month;			//�����·�
	String name;			//��ծ�ڻ�����
	double presentPrice;	//�ּۣ����ּۡ����ڡ��ɽ������ڡ���Լ�۸񡱣�ͳһ���ּۣ�
	double change;			//�ǵ������Ƿ������ǵ�����ͳһ���ǵ�����
	double	priceChange;	//����


	double  bidPirce;		//���
	double askPrice;		//����
	int bid;				//����
	int ask;				//����


	int nvol;				//����
 	int vol;				//�ɽ��������ֵ��ڳɽ�����ͳһ�ɳɽ�����
	int preRepository;		//��ֲ�
	int repository;			//�ֲ���
	
	int warehouse;			//����
	int dailyWarehouse;		//������
	double preClose;		//����
	double open;			//���̣��񿪵��ڿ��̣�ͳһ�ɿ��̣�
	double high;			//���
	double low;				//���
	double fullAmount;		//���
	double preSettlePrice;	//ǰ����ۣ�������ǰ����ۣ�ͳһ��ǰ����ۣ�
	double settlePrice;		//���
	double swing;			//���
	double ratio;			//����


	double hardenPrice;		//��ͣ
	double limitPrice;		//��ͣ	
	int outvol;				//����
	int invol;				//����
	int sellOrder;			//����
	int buyOrder;			//����

	double averPrice;		//����
	long time;				//ʱ��
	double committeeThan;	//ί��
	
	
	public Arb_detail(String id){
		this.treasuryID = id;
		init();
	}
	
	//���ø��ֲ���
	public void init(){}
}
