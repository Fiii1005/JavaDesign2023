package CDshop;

public class User {
	private int id;
	private String name;
	private int money;
	private DiskBook tools = new DiskBook();
	public User(int id, String name, int money) {
		super();
		this.id = id;
		this.name = name;
		this.money = money;
	}
	public DiskBook getTools() {
		return tools;
	}
	public void setTools(DiskBook tools) {
		this.tools = tools;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", money=" + money
				+ ", BorrowKindNum=" + tools.getNum() + "]";
	}
	public void addMoney(int m){
		money += m;
		System.out.println("该用户剩余金额为:" + money);
	}
	public void removeMoney(int m){
		if(m <= money){
			money -= m;
			System.out.println("该用户剩余金额为:" + money);
			return;
		}
		System.out.println("error!余额不足，还剩:" + money);
	}
}
