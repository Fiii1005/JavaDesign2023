package MyChess;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame{
	private static GameFrame Instance;
	public static GameFrame getInstance(){
		if(Instance==null){
			Instance=new GameFrame();
		}
		return Instance;
	}
	private JButton AllReplayBtn=new JButton("继续切磋");
	public void setAllReplayBtn(boolean e){
		if(e){
			AllReplayBtn.setEnabled(true);
		}else{
			AllReplayBtn.setEnabled(false);
		}
	}
	private ActionListener BtnAL=new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			Controller.getInstance().AllReplay();
		}
	};
	private JLabel currentColor=new JLabel("当前所下棋子颜色为：黑色");
	public void changeColor(int color){
		if(color==Model.BLACK){
			currentColor.setText("当前所下棋子颜色为：黑色");
		}else if(color==Model.WHITE){
			currentColor.setText("当前所下棋子颜色为：白色");
		}
	}
	private GameFrame(){
		JPanel p0=new JPanel();
		p0.setLayout(new FlowLayout());
		p0.add(AllReplayBtn);
		AllReplayBtn.setEnabled(false);
		AllReplayBtn.addActionListener(BtnAL);
		currentColor.setHorizontalAlignment(SwingConstants.CENTER);
		setTitle("开始游戏");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		if(Controller.getInstance().getNetMode()){
			getContentPane().add(NetPanel.getInstance(),BorderLayout.NORTH);
			getContentPane().add(ChatPanel.getInstance(),BorderLayout.EAST);
			getContentPane().add(p0,BorderLayout.SOUTH);
		}else{
			getContentPane().add(currentColor,BorderLayout.NORTH);
		}
		getContentPane().add(FunctionPanel.getInstance(),BorderLayout.WEST);
		getContentPane().add(ChessPanel.getInstance(),BorderLayout.CENTER);
		setSize(800, 600);
		setVisible(true);
	}
}
