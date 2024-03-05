package MyServer;

import java.net.*;
import java.io.*;

public class ServerControl {
	private static ServerControl Instance;
	public static ServerControl getInstance(){
		if(Instance==null){
			try {
				Instance=new ServerControl();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return Instance;
	}
	ServerSocket ss;
	Socket[] s=new Socket[2];
	PrintWriter[] out=new PrintWriter[2];
	int Cnum;
	BufferedReader in;
	public void Stop() throws IOException{
		ServerFrame.getInstance().deleteClients(Cnum);
		Cnum=0;
		new Thread(){
			public void run(){
				try {
					in.close();
					ss.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.start();
	}
	public void LinkToClient() throws IOException{
		Cnum=0;
		ss=new ServerSocket(ServerFrame.getInstance().getSsp());
		ServerFrame.getInstance().addDiary("�����������˿�Ϊ "+ServerFrame.getInstance().getSsp()+'\n');
		new Thread(){
			public void run(){
				while(true){
					try {
						s[Cnum]=ss.accept();
						ServerFrame.getInstance().addClients("Client"+Cnum);
						ServerFrame.getInstance().addDiary("���ܿͻ���Client "+Cnum+" ������"+'\n');
						in=new BufferedReader(new InputStreamReader(s[Cnum].getInputStream()));
						out[Cnum]=new PrintWriter(s[Cnum].getOutputStream(),true);
						new Thread(){
							public void run(){
								int num=Cnum-1;
								String line;
								try {
									System.out.println("Server����");
									while((line=in.readLine())!=null){
										if(Integer.parseInt(line)==-1){
											this.interrupt();
										}
										System.out.println("Server: Client "+num+ " ���գ�"+line+'\n');
										ServerFrame.getInstance().addDiary("Client "+num+" ���գ�"+line+'\n');
									}
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}.start();
						Cnum++;
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		}.start();
	}
	public void Send(int num){
		String line=ServerFrame.getInstance().getSend();
		if(line!=null){
			ServerFrame.getInstance().addDiary("��Client "+num+" ���ͣ�"+line+'\n');
			out[num].println(line);
			ServerFrame.getInstance().addDiary("�ɹ�"+'\n');
		}
	}
	public static void main(String[] args) {
		ServerFrame.getInstance();
	}
}
