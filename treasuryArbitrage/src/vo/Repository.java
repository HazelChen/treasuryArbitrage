package vo;

public class Repository {
	int repo_ID;	//�ֱֲ�ǡ����������������Ҹо����б�Ҫ
	long time;	//���׽���ʱ��
	int count;		//��������
	double guarantee;	//Ͷ�뱣֤��
	double profit;	//���������������ó�

	String toSell;	//��ͷ
	String toBuy;	//��ͷ
	double toSell_price;
	double toBuy_price;
	
	public Repository(){
		
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
