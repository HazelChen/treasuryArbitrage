package model.vo;

public class Repository {
	String repo_ID;	//�ֱֲ�ǡ����������������Ҹо����б�Ҫ
	String time;	//���׽���ʱ��
	int count;		//��������
	double guarantee;	//Ͷ�뱣֤��
	double profit;	//���������������ó�

	Arb_detail toSell;	//��ͷ
	Arb_detail toBuy;	//��ͷ
	
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
	public Arb_detail getToSell() {
		return toSell;
	}
	public void setToSell(Arb_detail toSell) {
		this.toSell = toSell;
	}
	public Arb_detail getToBuy() {
		return toBuy;
	}
	public void setToBuy(Arb_detail toBuy) {
		this.toBuy = toBuy;
	}

}
