package MyChess;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatPanel extends JPanel{
	private static ChatPanel Instance;
	public static ChatPanel getInstance(){
		if(Instance==null){
			Instance=new ChatPanel();
		}
		return Instance;
	}
	private TextArea ReceiveTA=new TextArea(10,25);
	private TextArea SendTA=new TextArea(3,25);
	private JButton SendBtn=new JButton("发送");
	private ChatPanel(){
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		Component vStruct=Box.createVerticalStrut(20);
		Component vStruct1=Box.createVerticalStrut(20);
		Component vGlue=Box.createVerticalGlue();
		Component vGlue1=Box.createVerticalGlue();
		
		TitledBorder tb0=BorderFactory.createTitledBorder("聊天窗口");
		JPanel p0=new JPanel();
		p0.setBorder(tb0);
		p0.add(ReceiveTA);
		ReceiveTA.setEditable(false);
		TitledBorder tb1=BorderFactory.createTitledBorder("发送消息");
		JPanel p1=new JPanel();
		p1.setBorder(tb1);
		p1.add(SendTA);
		JPanel p2=new JPanel();
		p2.add(SendBtn);
		SendBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String line=SendTA.getText();
				SendTA.setText(null);
				ReceiveTA.append("我："+line+'\n');
				NetHelper.getInstance().sendChat(line);
			}
		});
		
		add(vGlue);
		add(p0);
		add(vStruct);
		add(p1);
		add(vStruct1);
		add(p2);
		add(vGlue1);
	}
	public void Receive(String line){
		ReceiveTA.append("对方："+line+'\n');
	}
}
