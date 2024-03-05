package MyServer;

public class ServerData {
	private static ServerData Instance;
	public static ServerData getInstance(){
		if(Instance==null){
			Instance=new ServerData();
		}
		return Instance;
	}
	private String ss;
	public String getSS(){
		return ss;
	}
	public void setSS(String s){
		ss=s;
	}
	private int Cnum;
	private int curC;
	public int getCurC(){
		return curC;
	}
	private ClientData[] cd;
	public ClientData getCcd(){
		return cd[curC];
	}
	ServerData(){
		ss="start";
		cd=new ClientData[3];
		for(int i=0;i<3;i++){
			cd[i]=new ClientData();
		}
		Cnum=0;
		curC=0;
	}
}
