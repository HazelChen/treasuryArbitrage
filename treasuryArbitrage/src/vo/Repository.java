package vo;

public class Repository {
	String repo_ID;	//�ֱֲ�ǡ����������������Ҹо����б�Ҫ
	String time;	//���׽���ʱ��
	int count;		//��������
	double guarantee;	//Ͷ�뱣֤��
	double profit;	//���������������ó�

	String toSell;	//��ͷ
	String toBuy;	//��ͷ
	
	public Repository(){
		
	}
	
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

}
