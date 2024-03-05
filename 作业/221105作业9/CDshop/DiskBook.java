package CDshop;

import java.util.Arrays;

public class DiskBook {
	private Disk[] data = new Disk[1000];
	private int num;
	public DiskBook() {
		num=0;
	}
	public void add(Disk d){
		if(data[d.getId()] == null){
			data[d.getId()]=d;
			System.out.println("������ӳɹ�");
			num++;
			return;
		}
		if(data[d.getId()].getName().equals(d.getName())&&data[d.getId()].getPrice()==d.getPrice()){
			data[d.getId()].addDisk(d.getNum());
			return;
		}
		System.out.println("�ù����Ѵ��ڣ����ʧ��");
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public Disk[] getData() {
		return data;
	}
	public void setData(Disk[] data) {
		this.data = data;
	}
	public Disk find(int id){
		if(data[id] != null){
			return data[id];
		}
		return null;
	}
	public void remove(Disk d){
		if(data[d.getId()] != null){
			data[d.getId()]=null;
			System.out.println("�����Ƴ��ɹ�");
			num--;
			return;
		}
		return;
	}
	public void list(){
		for(Disk a: data){
			System.out.println(a);
		}
	}
	public String toString() {
		return "DiskBook [data=" + Arrays.toString(data) + "]";
	}
}
