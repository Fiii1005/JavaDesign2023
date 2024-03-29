package MyServer;

import java.net.*;
import java.io.*;

public class ClientControl {
	private static ClientControl Instance;
	public static ClientControl getInstance(){
		if(Instance==null){
			try {
				Instance=new ClientControl();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return Instance;
	}
	static Socket s;
	static PrintWriter out;
	static BufferedReader in;
	public void Send(){
		String line=ClientFrame.getInstance().getSend();
		if(line!=null){
			ClientFrame.getInstance().addDiary("发送："+line+'\n');
			out.println(line);
			ClientFrame.getInstance().addDiary("成功");
		}
	}
	public static void isConnect() throws UnknownHostException, IOException{
		s = new Socket("127.0.0.1",ClientFrame.getInstance().getCsp());
		if(!s.isClosed()){
			ClientFrame.getInstance().getTip2().setText("  连接服务器"
		+ServerData.getInstance().getCcd().getCip()+": "
					+ClientFrame.getInstance().getCsp()+" 成功!");
			Connect();
		}
	}
	public static void Connect(){
		try {
			ClientFrame.getInstance().addDiary("连接服务器"
					+ServerData.getInstance().getCcd().getCip()
					+": "+ClientFrame.getInstance().getCsp()+" 成功！"+'\n');
			out=new PrintWriter(s.getOutputStream(),true);
			in=new BufferedReader(new InputStreamReader(s.getInputStream()));
			new Thread(){
				public void run(){
					try {
						System.out.println("Client接收");
						String line;
						while((line=in.readLine())!=null){
							ClientFrame.getInstance().addDiary("ChatScoket接收："+line+'\n');
							System.out.println("Client: ChatScoket接收："+line+'\n');
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}.start();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws UnknownHostException, IOException {
		ClientFrame.getInstance();
		isConnect();
	}
}
