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
			System.out.println("�ù�������:" + num);
			return;
		}
		System.out.println("error!�����������㣬��ʣ:" + num);
	}
	public void addDisk(int n){
		num += n;
		System.out.println("�ù�������:" + num);
	}
	public void adjustPrice(int p){
		price = p;
		System.out.println("�ù��̵ļ۸�Ϊ:" + price);
	}
}
