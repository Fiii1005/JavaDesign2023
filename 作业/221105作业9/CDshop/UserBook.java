package CDshop;

public class UserBook {
	User[] data=new User[1000];
	public void add(User d){
		if(data[d.getId()] == null){
			data[d.getId()]=d;
			System.out.println("�û���ӳɹ�");
			return;
		}
		System.out.println("���û��Ѵ��ڣ����ʧ��");
	}
	public User find(int id){
		if(data[id] != null){
			return data[id];
		}
		return null;
	}
	public void remove(User d){
		if(data[d.getId()] != null){
			data[d.getId()]=null;
			System.out.println("�ɹ�ɾ�����û�");
			return;
		}
		System.out.println("error�����û�������");
		return;
	}
	public void list(){
		for(User a: data){
			System.out.println(a);
		}
	}
	
}
