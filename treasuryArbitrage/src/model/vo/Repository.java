package model.vo;

public class Repository {
	String repo_ID;	//�ֱֲ�ǡ����������������Ҹо����б�Ҫ
	String time;	//���׽���ʱ��
	int count;		//��������
	double guarantee;	//Ͷ�뱣֤��
	double profit;	//���������������ó�

	Arbitrage toSell;	//��ͷ
	Arbitrage toBuy;	//��ͷ
	
	public String getRepo_ID() {
		return repo_ID;
	}
	public void setRepo_ID(String repo_ID) {
		this.repo_ID = repo_ID;
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
	public double getProfit() {
		return profit;
	}
	public void setProfit(double profit) {
		this.profit = profit;
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
}
