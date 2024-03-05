package CDshop;


public class Disk {
	private int id;
	private String name;
	private int price;
	private int num;
	public Disk(int id, String name, int price, int num) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.num = num;
	}
	@Override
	public String toString() {
		return "Disk [id=" + id + ", name=" + name + ", price=" + price
				+ ", num=" + num + "]";
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
	public int getPrice() {
		return price;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public void removeDisk(int n){
		if(n <= num){
			num -= n;
			System.out.println("该光盘数量:" + num);
			return;
		}
		System.out.println("error!光盘余量不足，还剩:" + num);
	}
	public void addDisk(int n){
		num += n;
		System.out.println("该光盘数量:" + num);
	}
	public void adjustPrice(int p){
		price = p;
		System.out.println("该光盘的价格为:" + price);
	}
}
