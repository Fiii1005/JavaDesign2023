package MyChess;

import java.io.ObjectInputStream.GetField;

import javax.swing.JOptionPane;

public class Controller {
	private static Controller Instance;
	public static Controller getInstance(){
		if(Instance==null){
			Instance=new Controller();
		}
		return Instance;
	}
	private Controller(){}
	private int localColor=Model.BLACK;
	private int otherColor=Model.WHITE;
	private boolean netMode=false;
	private boolean allowPutChess=false;
	public void setAllowPutChess(boolean allowPutChess) {
		this.allowPutChess = allowPutChess;
	}
	public void setNetMode(boolean netMode) {
		this.netMode = netMode;
	}
	public boolean getNetMode() {
		return netMode;
	}
	public void localputChess(int row,int col) {
		if(!netMode){
			GameFrame.getInstance().changeColor(-1*localColor);
			localModePutChess(row, col);
		}else{
			netModePutChess(row,col);
		}
	}
	//�Է�����
	public void netOtherPutChess(int row,int col){
		boolean success=Model.getInstance().putChess(row, col, otherColor);
		if(success){
			ChessPanel.getInstance().repaint();
			allowPutChess=true;
			int winner=Model.getInstance().judge();
			if(winner==Model.BLACK){
				JOptionPane.showMessageDialog(null, "����Ӯ");
			}else if(winner==Model.WHITE){
				JOptionPane.showMessageDialog(null, "����Ӯ");
			}
			NetPanel.getInstance().setTurnLabel("������");
		}
	}
	private void netModePutChess(int row, int col) {
		if(!allowPutChess){
			return;
		}
		boolean success=Model.getInstance().putChess(row, col, localColor);
		if(success){
			ChessPanel.getInstance().repaint();
			//net send row,col
			NetHelper.getInstance().sendChess(row, col);

			allowPutChess=false;
			int winner=Model.getInstance().judge();
			if(winner==Model.BLACK){
				JOptionPane.showMessageDialog(null, "����Ӯ");
			}else if(winner==Model.WHITE){
				JOptionPane.showMessageDialog(null, "����Ӯ");
			}
			NetPanel.getInstance().setTurnLabel("�ȴ��Է�������...");
		}
	}
	private void localModePutChess(int row, int col) {
		boolean success=Model.getInstance().putChess(row, col, localColor);
		if(success){
			localColor=-localColor;
			ChessPanel.getInstance().repaint();
			int winner=Model.getInstance().judge();
			if(winner==Model.BLACK){
				JOptionPane.showMessageDialog(null, "����Ӯ");
				if(netMode){
					NetPanel.getInstance().setTurnLabel("����Ӯ����Ϸ����");
					gameOver();
				}
			}else if(winner==Model.WHITE){
				JOptionPane.showMessageDialog(null, "����Ӯ");
				if(netMode){
					NetPanel.getInstance().setTurnLabel("����Ӯ����Ϸ����");
					gameOver();
				}
			}
		}
	}
	public void beginListen() {
		NetHelper.getInstance().beginListen();
	}
	public void connect(String ip) {
		localColor=Model.WHITE;
		otherColor=Model.BLACK;
		allowPutChess=false;
		NetHelper.getInstance().connect(ip);		
	}
	public void Repeal() {
		if(!netMode){
			Model.getInstance().localRepeal();
			ChessPanel.getInstance().repaint();
			return;
		}
		NetHelper.getInstance().sendRepeal();
		NetPanel.getInstance().setTurnLabel("�Ѿ�������壬�ȴ��Է�ͬ��");
		allowPutChess=false;
	}
	public void Replay() {
		if(!netMode){
			localColor=Model.BLACK;
			GameFrame.getInstance().changeColor(localColor);
			Model.getInstance().localReplay();
			ChessPanel.getInstance().repaint();
			return;
		}
		allowPutChess=false;
		NetHelper.getInstance().sendReplay();
		NetPanel.getInstance().setTurnLabel("�Ѿ��������棬�ȴ��Է�ͬ��");
		allowPutChess=false;
	}
	public void netModeRepeal(boolean agree) {
		allowPutChess=true;
		if(!agree){
			return;
		}
		Model.getInstance().netModeRepeal();
		ChessPanel.getInstance().repaint();
	}
	public void netModeReplay() {
		allowPutChess=false;
		Model.getInstance().localReplay();
		NetPanel.getInstance().Replay();
		ChessPanel.getInstance().repaint();
		GameFrame.getInstance().setAllReplayBtn(false);
	}
	public void chooseFastHand() {
		NetHelper.getInstance().chooseFastHand();
		NetPanel.getInstance().setTurnLabel("ѡ�����֣�������");
		localColor=Model.BLACK;
		otherColor=Model.WHITE;
		allowPutChess=true;
	}
	public void becomeSlowHand() {
		NetPanel.getInstance().setTurnLabel("�Է�ѡ�����֣��ȴ��Է�������...");
		localColor=Model.WHITE;
		otherColor=Model.BLACK;
		allowPutChess=false;
	}
	public void chooseSlowHand() {
		NetHelper.getInstance().chooseSlowHand();
		NetPanel.getInstance().setTurnLabel("ѡ����֣��ȴ��Է�������...");
		localColor=Model.WHITE;
		otherColor=Model.BLACK;
		allowPutChess=false;
	}
	public void becomeFastHand() {
		NetPanel.getInstance().setTurnLabel("�Է�ѡ����֣�������");
		localColor=Model.BLACK;
		otherColor=Model.WHITE;
		allowPutChess=true;
	}
	public void Surrender() {
		allowPutChess=false;
		NetHelper.getInstance().informSurrender();
		NetPanel.getInstance().setTurnLabel("����Ͷ������Ϸ����");
		gameOver();
	}
	public void Stalemate() {
		allowPutChess=false;
		NetHelper.getInstance().sendStalemate();
	}
	public void refuseReplay() {
		NetPanel.getInstance().setTurnLabel("�Է��ܾ����棬������");
		allowPutChess=true;
	}
	public void selectedStalemate() {
		NetPanel.getInstance().setTurnLabel("˫�����壬��Ϸ����");
		gameOver();
	}
	public void refuseStalemate() {
		allowPutChess=true;
		NetPanel.getInstance().setTurnLabel("�Է��ܾ����壬������");
	}
	public void AllReplay() {
		NetPanel.getInstance().setTurnLabel("������Է������д裬�ȴ��Է�ͬ��...");
		NetHelper.getInstance().sendAllReplay();
	}
	public void refuseAllReplay() {
		NetPanel.getInstance().setTurnLabel("�Է��ܾ����������");
	}
	public void otherSurrender() {
		JOptionPane.showMessageDialog(null, "�Է���Ͷ������Ϸ����");
		NetPanel.getInstance().setTurnLabel("�Է���Ͷ������Ϸ����");
		gameOver();
	}
	private void gameOver(){
		allowPutChess=false;
		GameFrame.getInstance().setAllReplayBtn(true);
	}
}
