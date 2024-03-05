package CDshop;

public class UserBook {
	User[] data=new User[1000];
	public void add(User d){
		if(data[d.getId()] == null){
			data[d.getId()]=d;
			System.out.println("用户添加成功");
			return;
		}
		System.out.println("该用户已存在！添加失败");
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
			System.out.println("成功删除该用户");
			return;
		}
		System.out.println("error！该用户不存在");
		return;
	}
	public void list(){
		for(User a: data){
			System.out.println(a);
		}
	}
	
}
