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
	//对方下棋
	public void netOtherPutChess(int row,int col){
		boolean success=Model.getInstance().putChess(row, col, otherColor);
		if(success){
			ChessPanel.getInstance().repaint();
			allowPutChess=true;
			int winner=Model.getInstance().judge();
			if(winner==Model.BLACK){
				JOptionPane.showMessageDialog(null, "黑棋赢");
			}else if(winner==Model.WHITE){
				JOptionPane.showMessageDialog(null, "白棋赢");
			}
			NetPanel.getInstance().setTurnLabel("请下棋");
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
				JOptionPane.showMessageDialog(null, "黑棋赢");
			}else if(winner==Model.WHITE){
				JOptionPane.showMessageDialog(null, "白棋赢");
			}
			NetPanel.getInstance().setTurnLabel("等待对方下棋中...");
		}
	}
	private void localModePutChess(int row, int col) {
		boolean success=Model.getInstance().putChess(row, col, localColor);
		if(success){
			localColor=-localColor;
			ChessPanel.getInstance().repaint();
			int winner=Model.getInstance().judge();
			if(winner==Model.BLACK){
				JOptionPane.showMessageDialog(null, "黑棋赢");
				if(netMode){
					NetPanel.getInstance().setTurnLabel("黑棋赢，游戏结束");
					gameOver();
				}
			}else if(winner==Model.WHITE){
				JOptionPane.showMessageDialog(null, "白棋赢");
				if(netMode){
					NetPanel.getInstance().setTurnLabel("白棋赢，游戏结束");
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
		NetPanel.getInstance().setTurnLabel("已经申请悔棋，等待对方同意");
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
		NetPanel.getInstance().setTurnLabel("已经申请重玩，等待对方同意");
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
		NetPanel.getInstance().setTurnLabel("选择先手，请下棋");
		localColor=Model.BLACK;
		otherColor=Model.WHITE;
		allowPutChess=true;
	}
	public void becomeSlowHand() {
		NetPanel.getInstance().setTurnLabel("对方选择先手，等待对方下棋中...");
		localColor=Model.WHITE;
		otherColor=Model.BLACK;
		allowPutChess=false;
	}
	public void chooseSlowHand() {
		NetHelper.getInstance().chooseSlowHand();
		NetPanel.getInstance().setTurnLabel("选择后手，等待对方下棋中...");
		localColor=Model.WHITE;
		otherColor=Model.BLACK;
		allowPutChess=false;
	}
	public void becomeFastHand() {
		NetPanel.getInstance().setTurnLabel("对方选择后手，请下棋");
		localColor=Model.BLACK;
		otherColor=Model.WHITE;
		allowPutChess=true;
	}
	public void Surrender() {
		allowPutChess=false;
		NetHelper.getInstance().informSurrender();
		NetPanel.getInstance().setTurnLabel("你已投降，游戏结束");
		gameOver();
	}
	public void Stalemate() {
		allowPutChess=false;
		NetHelper.getInstance().sendStalemate();
	}
	public void refuseReplay() {
		NetPanel.getInstance().setTurnLabel("对方拒绝重玩，请下棋");
		allowPutChess=true;
	}
	public void selectedStalemate() {
		NetPanel.getInstance().setTurnLabel("双方和棋，游戏结束");
		gameOver();
	}
	public void refuseStalemate() {
		allowPutChess=true;
		NetPanel.getInstance().setTurnLabel("对方拒绝和棋，请下棋");
	}
	public void AllReplay() {
		NetPanel.getInstance().setTurnLabel("已邀请对方继续切磋，等待对方同意...");
		NetHelper.getInstance().sendAllReplay();
	}
	public void refuseAllReplay() {
		NetPanel.getInstance().setTurnLabel("对方拒绝了你的邀请");
	}
	public void otherSurrender() {
		JOptionPane.showMessageDialog(null, "对方已投降，游戏结束");
		NetPanel.getInstance().setTurnLabel("对方已投降，游戏结束");
		gameOver();
	}
	private void gameOver(){
		allowPutChess=false;
		GameFrame.getInstance().setAllReplayBtn(true);
	}
}
