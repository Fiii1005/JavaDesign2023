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
		System.out.println("�������û�id:");
		String line=in.readLine();
		int userID =Integer.parseInt(line);
		User user = userBook.find(userID);
		System.out.println(user);
		if(user != null){
			System.out.println("�������ܹ���Ҫ����Ĺ���������:");
			String line1=in.readLine();
			int buyNum =Integer.parseInt(line1);
			for(int i = 0; i < buyNum; i++){
				System.out.println("���������id:");
				String line3=in.readLine();
				int diskID =Integer.parseInt(line3);
				Disk disk = diskBook.find(diskID);
				if(disk != null){
					System.out.println(disk);
					System.out.println("�����빺������:");
					String line4=in.readLine();
					int diskNum =Integer.parseInt(line4);
					if(diskNum <= disk.getNum()){
						int totalPrice = diskNum*disk.getPrice();
						if(totalPrice <= user.getMoney()){
							System.out.println("�ɹ�����");
							user.removeMoney(totalPrice);
							disk.removeDisk(diskNum);
						}
						else{
							System.out.println("����ʧ�ܣ�����");
						}
					}
					else{
						System.out.println("����ʧ�ܣ�������������");
					}
				}
				else{
					System.out.println("�ù��̲�����");
				}
			}
		}
		else{
			System.out.println("���û�������");
		}
	}
	private void Ret() throws IOException {
		// TODO Auto-generated method stub
		System.out.println("�������û�id��");
		String line=in.readLine();
		int userID =Integer.parseInt(line);
		User user = userBook.find(userID);
		System.out.println(user);
		if(user != null){
			System.out.println("�������ܹ���Ҫ�黹�Ĺ���������:");
			String line1=in.readLine();
			int returnNum =Integer.parseInt(line1);
			for(int i = 0; i < returnNum; i++){
				System.out.println("���������id:");
				String line3=in.readLine();
				int diskID =Integer.parseInt(line3);
				Disk disk = user.getTools().find(diskID);
				Disk diskofBook = diskBook.find(diskID);
				if(disk != null){
					System.out.println(disk);
					System.out.println("������黹����:");
					String line4=in.readLine();
					int diskNum =Integer.parseInt(line4);
					if(diskNum < disk.getNum()){
						System.out.print("�û�����");
						disk.removeDisk(diskNum);
						diskofBook.addDisk(diskNum);
					}
					else if(diskNum == disk.getNum()){
						System.out.print("�û�����");
						user.getTools().remove(disk);
						diskofBook.addDisk(diskNum);
					}
					else{
						System.out.println("�黹ʧ�ܣ�������������");
					}
				}
				else{
					System.out.println("�黹ʧ�ܣ�δ���ø���");
				}
			}
		}
	}
	private void Borrow() throws IOException {
		// TODO Auto-generated method stub
		System.out.println("�������û�id:");
		String line=in.readLine();
		int userID =Integer.parseInt(line);
		User user = userBook.find(userID);
		System.out.println(user);
		if(user != null){
			System.out.println("�������ܹ���Ҫ���õĹ���������:");
			String line1=in.readLine();
			int borrowNum =Integer.parseInt(line1);
			for(int i = 0; i < borrowNum; i++){
				System.out.println("���������id:");
				String line3=in.readLine();
				int diskID =Integer.parseInt(line3);
				Disk disk = diskBook.find(diskID);
				if(disk != null){
					System.out.println(disk);
					System.out.println("�������������:");
					String line4=in.readLine();
					int diskNum =Integer.parseInt(line4);
					if(diskNum <= disk.getNum()){
						System.out.println("�ɹ�����");
						disk.removeDisk(diskNum);
						Disk borrowDisk = new Disk(disk.getId(),disk.getName(),disk.getPrice(),diskNum);
						System.out.print("�û�����");
						user.getTools().add(borrowDisk);
					}
					else{
						System.out.println("����ʧ�ܣ�������������");
					}
				}
				else{
					System.out.println("�ù��̲�����");
				}
			}
		}
		else{
			System.out.println("���û�������");
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
			System.out.println("��������Ҫ���ҹ��̵�id��");
			String line=in.readLine();
			int id =Integer.parseInt(line);
			Disk find = diskBook.find(id);
			if(find != null){
				boolean jump = false;
				System.out.print("�ɹ��ҵ��ù���:");
				while(!jump){
					System.out.println(find);
					printFindDiskMenu();
					String line1=in.readLine();
					int choice =Integer.parseInt(line1);
					switch(choice){
					case 1:
						System.out.println("������ȡ������������");
						String line2=in.readLine();
						int n1 =Integer.parseInt(line2);
						find.removeDisk(n1);
						break;
					case 2:
						System.out.println("����������������:");
						String line3=in.readLine();
						int n2 =Integer.parseInt(line3);
						find.addDisk(n2);
						break;
					case 3:
						System.out.println("�����������ļ۸�:");
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
				System.out.println("�ù��̲�����");
				return;
			}
		}
	}
	private void printFindDiskMenu() {
		// TODO Auto-generated method stub
		System.out.println("1:ȡ������");
		System.out.println("2:�������");
		System.out.println("3:�����۸�");
		System.out.println("4:�������ҹ���");
		System.out.println("5:���ع��̲˵�");
		System.out.println("��ѡ��");
	}
	private void diskAdd() throws IOException {
		// TODO Auto-generated method stub
		System.out.println("��������ӹ��̵�id�����ơ��۸������");
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
		System.out.println("1:��ӹ���");
		System.out.println("2:���ҹ���");
		System.out.println("3:�����б�");
		System.out.println("4:�������˵�");
		System.out.println("��ѡ��");
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
			System.out.println("������Ҫ�����û���id:");
			String line=in.readLine();
			int id =Integer.parseInt(line);
			User find = userBook.find(id);
			if(find != null){
				System.out.println("�ɹ��ҵ����û�:" + find);
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
				System.out.println("�����ڸ��û�");
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
				System.out.println("�������ֵ���:");
				String line1=in.readLine();
				int m =Integer.parseInt(line1);
				find.addMoney(m);
				break;
			case 2:
				System.out.println("������ȡ�����");
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
		System.out.println("1:��ֵ�ʽ�");
		System.out.println("2:ȡ���ʽ�");
		System.out.println("3:�����û��˵�");
		System.out.println("��ѡ��");
	}
	private void printNotFindUserMenu() {
		// TODO Auto-generated method stub
		System.out.println("1:���������û�");
		System.out.println("2:�����û��˵�");
		System.out.println("��ѡ��");
	}
	private void printFindUserMenu() {
		// TODO Auto-generated method stub
		System.out.println("1:�ʽ����");
		System.out.println("2:�û����ù����б�");
		System.out.println("3:ɾ���û�");
		System.out.println("4:���������û�");
		System.out.println("5:�����û��˵�");
		System.out.println("��ѡ��");
	}
	private void userAdd() throws IOException {
		// TODO Auto-generated method stub
		System.out.println("����������û���id�����ֺͳ�ֵ���");
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
		System.out.println("1:����û�");
		System.out.println("2:�����û�");
		System.out.println("3:�û��б�");
		System.out.println("4:�������˵�");
		System.out.println("��ѡ��");
	}
	private void printMainMenu() {
		// TODO Auto-generated method stub
		System.out.println("1:�����Ա����");
		System.out.println("2:������̹���");
		System.out.println("3:�����");
		System.out.println("4:������");
		System.out.println("5:���۹���");
		System.out.println("6:�˳�ϵͳ");
		System.out.println("��ѡ��");
	}
}
