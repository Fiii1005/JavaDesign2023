package MyChess;

import java.io.*;
import java.awt.*;
import java.net.*;

import javax.swing.*;

public class NetHelper {
	private static NetHelper Instance;
	public static NetHelper getInstance(){
		if(Instance==null){
			Instance=new NetHelper();
		}
		return Instance;
	}
	public static final int PORT=8900;
	private Socket s;
	private BufferedReader in;
	private PrintWriter out;
	private NetHelper(){}
	public void beginListen(){
		new Thread(){
			public void run() {
				try {
					ServerSocket ss=new ServerSocket(PORT);
					s=ss.accept();
					in=new BufferedReader(new InputStreamReader(s.getInputStream()));
					out=new PrintWriter(s.getOutputStream(),true);
					startReadThread();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.start();	
	}
	protected void startReadThread() {
		new Thread(){
			public void run(){
				while(true){
					try {
						String line=in.readLine();
						if(line.equals("ConnectionSuccess")){
							NetPanel.getInstance().connectSuccess();
						}
						else if(line.startsWith("PutChess")){
							parseChess(line);
						}else if(line.startsWith("Chat")){
							parseChat(line);
						}else if(line.equals("applyRepeal")){
							applyRepeal();
						}else if(line.equals("agreeRepeal")){
							NetPanel.getInstance().setTurnLabel("悔棋成功，请下棋");
							Controller.getInstance().netModeRepeal(true);
						}else if(line.equals("refuseRepeal")){
							NetPanel.getInstance().setTurnLabel("悔棋失败，请下棋");
							Controller.getInstance().netModeRepeal(false);
						}else if(line.equals("applyReplay")){
							applyReplay();
						}else if(line.equals("chooseFastHand")){
							Controller.getInstance().becomeSlowHand();
							NetPanel.getInstance().selectedHand();
						}else if(line.equals("chooseSlowHand")){
							Controller.getInstance().becomeFastHand();
							NetPanel.getInstance().selectedHand();
						}else if(line.equals("agreeReplay")){
							Controller.getInstance().netModeReplay();
						}else if(line.equals("refuseReplay")){
							Controller.getInstance().refuseReplay();
						}else if(line.equals("Surrender")){
							Surrender();
						}else if(line.equals("applyStalemate")){
							applyStalemate();
						}else if(line.equals("agreeStalemate")){
							Controller.getInstance().selectedStalemate();
						}else if(line.equals("refuseStalemate")){
							Controller.getInstance().refuseStalemate();
						}else if(line.equals("applyAllReplay")){
							applyAllReplay();
						}else if(line.equals("refuseAllReplay")){
							Controller.getInstance().refuseAllReplay();
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		}.start();
	}
	protected void Surrender() {
		Controller.getInstance().otherSurrender();
	}
	protected void applyAllReplay() {
		Object options=new Object[]{"同意","拒绝"};
		int optionSelected=JOptionPane.showOptionDialog(
				GameFrame.getInstance(), 
				"对方申请继续切磋，是否同意？", 
				"继续切磋",
				JOptionPane.YES_NO_CANCEL_OPTION, 
				JOptionPane.ERROR_MESSAGE,
				null,
				(Object[]) options,
				options);
		if(optionSelected==0){
			agreeReplay();
		}
		else if(optionSelected==1){
			refuseAllReplay();
		}
	}
	private void refuseAllReplay() {
		out.println("refuseAllReplay");
		NetPanel.getInstance().setTurnLabel("已拒绝对方邀请");
	}
	protected void applyStalemate() {
		Object options=new Object[]{"同意","拒绝"};
		int optionSelected=JOptionPane.showOptionDialog(
				GameFrame.getInstance(), 
				"对方申请和棋，是否同意？", 
				"和棋",
				JOptionPane.YES_NO_CANCEL_OPTION, 
				JOptionPane.ERROR_MESSAGE,
				null,
				(Object[]) options,
				options);
		if(optionSelected==0){
			agreeStalemate();
		}
		else if(optionSelected==1){
			refuseStalemate();
		}
	}
	private void refuseStalemate() {
		out.print("refuseStalemate");
	}
	private void agreeStalemate() {
		out.println("agreeStalemate");
		Controller.getInstance().selectedStalemate();
	}
	protected void applyReplay() {
		Object options=new Object[]{"同意","拒绝"};
		int optionSelected=JOptionPane.showOptionDialog(
				GameFrame.getInstance(), 
				"对方申请重玩，是否同意？", 
				"重玩",
				JOptionPane.YES_NO_CANCEL_OPTION, 
				JOptionPane.ERROR_MESSAGE,
				null,
				(Object[]) options,
				options);
		if(optionSelected==0){
			agreeReplay();
		}
		else if(optionSelected==1){
			refuseReplay();
		}
	}
	private void refuseReplay() {
		out.println("refuseReplay");
	}
	private void agreeReplay() {
		out.println("agreeReplay");
		Controller.getInstance().netModeReplay();
	}
	protected void applyRepeal() {
		Object options=new Object[]{"同意","拒绝"};
		int optionSelected=JOptionPane.showOptionDialog(
				GameFrame.getInstance(), 
				"对方申请悔棋，是否同意？", 
				"悔棋", 
				JOptionPane.YES_NO_CANCEL_OPTION, 
				JOptionPane.ERROR_MESSAGE,
				null,
				(Object[]) options,
				options);
		if(optionSelected==0){
			agreeRepeal();
		}else if(optionSelected==1){
			refuseRepeal();
		}
	}
	private void refuseRepeal() {
		out.println("refuseRepeal");
	}
	private void agreeRepeal() {
		Controller.getInstance().netModeRepeal(true);
		out.println("agreeRepeal");
	}
	
	protected void parseChat(String line) {
		line=line.substring(5);
		ChatPanel.getInstance().Receive(line);
	}
	protected void parseChess(String line) {
		line=line.substring(9);
		String[] array=line.split(",");
		int row=Integer.parseInt(array[0]);
		int col=Integer.parseInt(array[1]);
		Controller.getInstance().netOtherPutChess(row, col);
	}
	public void sendChess(final int row,final int col){
		new Thread(){
			public void run(){
				out.println("PutChess:"+row+","+col);
			}
		}.start();
	}
	public void sendChat(final String line){
		new Thread(){
			public void run(){
				out.println("Chat:"+line);
			}
		}.start();
	}
	//客户端程序connect
	public void connect(String ip) {
		try {
			s=new Socket(ip,PORT);
			in=new BufferedReader(new InputStreamReader(s.getInputStream()));
			out=new PrintWriter(s.getOutputStream(),true);
			out.println("ConnectionSuccess");
			NetPanel.getInstance().connectSuccess();
			startReadThread();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void sendRepeal() {
		new Thread(){
			public void run(){
				out.println("applyRepeal");
			}
		}.start();
	}
	public void sendReplay() {
		out.println("applyReplay");
	}
	public void chooseFastHand() {
		out.println("chooseFastHand");
	}
	public void chooseSlowHand() {
		out.println("chooseSlowHand");
	}
	public void sendStalemate() {
		out.println("applyStalemate");
	}
	public void sendAllReplay() {
		out.println("applyAllReplay");
	}
	public void informSurrender() {
		out.println("Surrender");
	}
}
