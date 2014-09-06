package vo;

public class Record {
	String reco_ID;	//��¼��ǡ����������������Ҹо����б�Ҫ
	String time;	//���׽���ʱ��
	int count;		//��������
	double guarantee;	//Ͷ�뱣֤��
	String state;	//����״̬

	Arbitrage toSell;	//��ͷ
	Arbitrage toBuy;	//��ͷ
	
	public Record(){
		
	}
	
	public String getRepo_ID() {
		return reco_ID;
	}
	public void setRepo_ID(String reco_ID) {
		this.reco_ID = reco_ID;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Arbitrage getToSell() {
		return toSell;
	}
	public void setToSell(Arbitrage toSell) {
		this.toSell = toSell;
	}
	public Arbitrage getToBuy() {
		return toBuy;
	}
	public void setToBuy(Arbitrage toBuy) {
		this.toBuy = toBuy;
	}
	
	//���о�̬��Ϣ����,�ڲ���
	public class Arbitrage {
		String id;
		double value;
		
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public double getValue() {
			return value;
		}
		public void setValue(double value) {
			this.value = value;
		}
	}
}