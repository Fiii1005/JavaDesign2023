package MyChess;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NetPanel extends JPanel{
	private static NetPanel Instance;
	public static NetPanel getInstance(){
		if(Instance==null){
			Instance=new NetPanel();
		}
		return Instance;
	}
	private JButton listenBtn=new JButton("开始监听");
	private JButton connectionBtn=new JButton("连接服务器");
	private JTextField ipTF=new JTextField(20);
	private JLabel turnLabel=new JLabel();
	public void setTurnLabel(String s){
		turnLabel.setText(s);
	}
	private JButton fastHandBtn=new JButton("先手");
	private JButton slowHandBtn=new JButton("后手");
	private ActionListener HandAL=new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			selectedHand();
			String command=e.getActionCommand();
			if(command.equals("先手")){
				Controller.getInstance().chooseFastHand();
			}else if(command.equals("后手")){
				Controller.getInstance().chooseSlowHand();
			}
		}
	};
	private NetPanel(){
		JPanel Connection=new JPanel();
		Connection.setLayout(new FlowLayout());
		Connection.add(listenBtn);
		Connection.add(ipTF);
		Connection.add(connectionBtn);
		JPanel chooseHand=new JPanel();
		chooseHand.setLayout(new FlowLayout());
		chooseHand.add(fastHandBtn);
		chooseHand.add(slowHandBtn);
		fastHandBtn.setEnabled(false);
		slowHandBtn.setEnabled(false);
		fastHandBtn.addActionListener(HandAL);
		slowHandBtn.addActionListener(HandAL);
		setLayout(new GridLayout(3,1));
		add(Connection);
		add(turnLabel);
		add(chooseHand);
		turnLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		ipTF.setText("localhost");
		//服务端程序开始监听
		listenBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Controller.getInstance().beginListen();
				listenBtn.setEnabled(false);
				connectionBtn.setEnabled(false);
				setTurnLabel("等待客户端连接...");
			}
		});
		//客户端程序连接
		connectionBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String ip=ipTF.getText();
				Controller.getInstance().connect(ip);
				connectionBtn.setEnabled(false);
				listenBtn.setEnabled(false);
			}
		});
	}
	public void selectedHand() {
		fastHandBtn.setEnabled(false);
		slowHandBtn.setEnabled(false);
	}
	public void Replay() {
		NetPanel.getInstance().setTurnLabel("重新开始，请选择先手或后手");
		fastHandBtn.setEnabled(true);
		slowHandBtn.setEnabled(true);
	}
	public void connectSuccess() {
		setTurnLabel("连接成功，请选择先手或后手");
		fastHandBtn.setEnabled(true);
		slowHandBtn.setEnabled(true);
	}
	
	
}
