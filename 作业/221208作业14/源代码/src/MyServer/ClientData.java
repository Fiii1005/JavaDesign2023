package MyServer;

public class ClientData {
	private String Cip;
	public String getCip(){
		return Cip;
	}
	ClientData(){
		Cip=new String("localhost");
	}
}
