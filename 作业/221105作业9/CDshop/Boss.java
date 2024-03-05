package CDshop;

import java.io.*;

public class Boss {
	UserBook userBook=new UserBook();
	DiskBook diskBook=new DiskBook();
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args)throws Exception {
		new Boss().begin();
	}
	public void begin()throws Exception{
		while(true){
			printMainMenu();
			String line=in.readLine();
			int choice =Integer.parseInt(line);
			switch(choice){
			case 1:
				UserManager();
				break;
			case 2:
				DiskManager();
				break;
			case 3:
				Borrow();
				break;
			case 4:
				Ret();
				break;
			case 5:
				Sell();
				break;
			case 6:
				return;
			}
		}
	}
	private void Sell() throws IOException {
		// TODO Auto-generated method stub
		System.out.println("请输入用户id:");
		String line=in.readLine();
		int userID =Integer.parseInt(line);
		User user = userBook.find(userID);
		System.out.println(user);
		if(user != null){
			System.out.println("请输入总共需要购买的光盘种类数:");
			String line1=in.readLine();
			int buyNum =Integer.parseInt(line1);
			for(int i = 0; i < buyNum; i++){
				System.out.println("请输入光盘id:");
				String line3=in.readLine();
				int diskID =Integer.parseInt(line3);
				Disk disk = diskBook.find(diskID);
				if(disk != null){
					System.out.println(disk);
					System.out.println("请输入购买数量:");
					String line4=in.readLine();
					int diskNum =Integer.parseInt(line4);
					if(diskNum <= disk.getNum()){
						int totalPrice = diskNum*disk.getPrice();
						if(totalPrice <= user.getMoney()){
							System.out.println("成功购买");
							user.removeMoney(totalPrice);
							disk.removeDisk(diskNum);
						}
						else{
							System.out.println("购买失败！余额不足");
						}
					}
					else{
						System.out.println("购买失败！光盘余量不足");
					}
				}
				else{
					System.out.println("该光盘不存在");
				}
			}
		}
		else{
			System.out.println("该用户不存在");
		}
	}
	private void Ret() throws IOException {
		// TODO Auto-generated method stub
		System.out.println("请输入用户id：");
		String line=in.readLine();
		int userID =Integer.parseInt(line);
		User user = userBook.find(userID);
		System.out.println(user);
		if(user != null){
			System.out.println("请输入总共需要归还的光盘种类数:");
			String line1=in.readLine();
			int returnNum =Integer.parseInt(line1);
			for(int i = 0; i < returnNum; i++){
				System.out.println("请输入光盘id:");
				String line3=in.readLine();
				int diskID =Integer.parseInt(line3);
				Disk disk = user.getTools().find(diskID);
				Disk diskofBook = diskBook.find(diskID);
				if(disk != null){
					System.out.println(disk);
					System.out.println("请输入归还数量:");
					String line4=in.readLine();
					int diskNum =Integer.parseInt(line4);
					if(diskNum < disk.getNum()){
						System.out.print("用户借用");
						disk.removeDisk(diskNum);
						diskofBook.addDisk(diskNum);
					}
					else if(diskNum == disk.getNum()){
						System.out.print("用户借用");
						user.getTools().remove(disk);
						diskofBook.addDisk(diskNum);
					}
					else{
						System.out.println("归还失败！借用数量不足");
					}
				}
				else{
					System.out.println("归还失败！未借用该书");
				}
			}
		}
	}
	private void Borrow() throws IOException {
		// TODO Auto-generated method stub
		System.out.println("请输入用户id:");
		String line=in.readLine();
		int userID =Integer.parseInt(line);
		User user = userBook.find(userID);
		System.out.println(user);
		if(user != null){
			System.out.println("请输入总共需要借用的光盘种类数:");
			String line1=in.readLine();
			int borrowNum =Integer.parseInt(line1);
			for(int i = 0; i < borrowNum; i++){
				System.out.println("请输入光盘id:");
				String line3=in.readLine();
				int diskID =Integer.parseInt(line3);
				Disk disk = diskBook.find(diskID);
				if(disk != null){
					System.out.println(disk);
					System.out.println("请输入借用数量:");
					String line4=in.readLine();
					int diskNum =Integer.parseInt(line4);
					if(diskNum <= disk.getNum()){
						System.out.println("成功借用");
						disk.removeDisk(diskNum);
						Disk borrowDisk = new Disk(disk.getId(),disk.getName(),disk.getPrice(),diskNum);
						System.out.print("用户借用");
						user.getTools().add(borrowDisk);
					}
					else{
						System.out.println("借用失败！光盘余量不足");
					}
				}
				else{
					System.out.println("该光盘不存在");
				}
			}
		}
		else{
			System.out.println("该用户不存在");
		}
	}
	private void DiskManager() throws IOException {
		// TODO Auto-generated method stub
		while(true){
			printDiskMenu();
			String line=in.readLine();
			int choice =Integer.parseInt(line);
			switch(choice){
			case 1:
				diskAdd();
				break;
			case 2:
				diskFind();
				break;
			case 3:
				diskBook.list();
				break;
			case 4:
				return;
			}
		}
	}
	private void diskFind() throws IOException {
		// TODO Auto-generated method stub
		while(true){
			System.out.println("请输入需要查找光盘的id：");
			String line=in.readLine();
			int id =Integer.parseInt(line);
			Disk find = diskBook.find(id);
			if(find != null){
				boolean jump = false;
				System.out.print("成功找到该光盘:");
				while(!jump){
					System.out.println(find);
					printFindDiskMenu();
					String line1=in.readLine();
					int choice =Integer.parseInt(line1);
					switch(choice){
					case 1:
						System.out.println("请输入取出光盘数量：");
						String line2=in.readLine();
						int n1 =Integer.parseInt(line2);
						find.removeDisk(n1);
						break;
					case 2:
						System.out.println("请输入存入光盘数量:");
						String line3=in.readLine();
						int n2 =Integer.parseInt(line3);
						find.addDisk(n2);
						break;
					case 3:
						System.out.println("请输入调整后的价格:");
						String line4=in.readLine();
						int p =Integer.parseInt(line4);
						find.adjustPrice(p);
						break;
					case 4:
						jump = true;
						break;
					case 5:
						return;
					}
				}
			}
			else{
				System.out.println("该光盘不存在");
				return;
			}
		}
	}
	private void printFindDiskMenu() {
		// TODO Auto-generated method stub
		System.out.println("1:取出光盘");
		System.out.println("2:存入光盘");
		System.out.println("3:调整价格");
		System.out.println("4:继续查找光盘");
		System.out.println("5:返回光盘菜单");
		System.out.println("请选择");
	}
	private void diskAdd() throws IOException {
		// TODO Auto-generated method stub
		System.out.println("请输入添加光盘的id、名称、价格和数量");
		String line=in.readLine();
		int id =Integer.parseInt(line);
		String name = in.readLine();
		String line1=in.readLine();
		int price =Integer.parseInt(line1);
		String line2=in.readLine();
		int num =Integer.parseInt(line2);
		Disk disk = new Disk(id, name, price, num);
		diskBook.add(disk);
	}
	private void printDiskMenu() {
		// TODO Auto-generated method stub
		System.out.println("1:添加光盘");
		System.out.println("2:查找光盘");
		System.out.println("3:光盘列表");
		System.out.println("4:返回主菜单");
		System.out.println("请选择");
	}
	private void UserManager() throws IOException {
		// TODO Auto-generated method stub
		while(true){
			printUserMenu();
			String line=in.readLine();
			int choice =Integer.parseInt(line);
			switch(choice){
			case 1:
				userAdd();
				break;
			case 2:
				userFind();
				break;
			case 3:
				userBook.list();
				break;
			case 4:
				return;
			}
		}
	}
	private void userFind() throws IOException {
		// TODO Auto-generated method stub
		while(true){
			System.out.println("请输入要查找用户的id:");
			String line=in.readLine();
			int id =Integer.parseInt(line);
			User find = userBook.find(id);
			if(find != null){
				System.out.println("成功找到该用户:" + find);
				printFindUserMenu();
				String line1=in.readLine();
				int choice =Integer.parseInt(line1);
				switch(choice){
				case 1:
					moneyManager(find);
					return;
				case 2:
					find.getTools().list();
					return;
				case 3:
					userBook.remove(find);
					return;
				case 4:
					break;
				case 5:
					return;
				}
			}
			else{
				System.out.println("不存在该用户");
				printNotFindUserMenu();
				String line1=in.readLine();
				int choice =Integer.parseInt(line1);
				switch(choice){
				case 1:
					break;
				case 2:
					return;
				}
			}
		}
	}
	private void moneyManager(User find) throws IOException {
		// TODO Auto-generated method stub
		while(true){
			printMoneyMenu();
			String line = in.readLine();
			int choice = Integer.parseInt(line);
			switch(choice){
			case 1:
				System.out.println("请输入充值金额:");
				String line1=in.readLine();
				int m =Integer.parseInt(line1);
				find.addMoney(m);
				break;
			case 2:
				System.out.println("请输入取出金额");
				String line2=in.readLine();
				int m1 =Integer.parseInt(line2);
				find.removeMoney(m1);
				break;
			case 3:
				return;
			}
		}
	}
	private void printMoneyMenu() {
		// TODO Auto-generated method stub
		System.out.println("1:充值资金");
		System.out.println("2:取出资金");
		System.out.println("3:返回用户菜单");
		System.out.println("请选择");
	}
	private void printNotFindUserMenu() {
		// TODO Auto-generated method stub
		System.out.println("1:继续查找用户");
		System.out.println("2:返回用户菜单");
		System.out.println("请选择");
	}
	private void printFindUserMenu() {
		// TODO Auto-generated method stub
		System.out.println("1:资金管理");
		System.out.println("2:用户借用光盘列表");
		System.out.println("3:删除用户");
		System.out.println("4:继续查找用户");
		System.out.println("5:返回用户菜单");
		System.out.println("请选择");
	}
	private void userAdd() throws IOException {
		// TODO Auto-generated method stub
		System.out.println("请输入添加用户的id、名字和充值金额");
		String line=in.readLine();
		int id =Integer.parseInt(line);
		String name = in.readLine();
		String line1=in.readLine();
		int money =Integer.parseInt(line1);
		User user = new User(id, name, money);
		userBook.add(user);
	}
	private void printUserMenu() {
		// TODO Auto-generated method stub
		System.out.println("1:添加用户");
		System.out.println("2:查找用户");
		System.out.println("3:用户列表");
		System.out.println("4:返回主菜单");
		System.out.println("请选择");
	}
	private void printMainMenu() {
		// TODO Auto-generated method stub
		System.out.println("1:进入会员管理");
		System.out.println("2:进入光盘管理");
		System.out.println("3:借光盘");
		System.out.println("4:还光盘");
		System.out.println("5:出售光盘");
		System.out.println("6:退出系统");
		System.out.println("请选择");
	}
}
