package MyServer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ClientFrame1 extends JFrame{
	private static ClientFrame1 Instance;
	public static ClientFrame1 getInstance(){
		if(Instance==null){
			Instance=new ClientFrame1();
		}
		return Instance;
	}
	private JPanel P0;
	private JLabel Tip0;
	private JTextField Cip;
	private JLabel Tip1;
	private JTextField sPort;
	private JButton Ready;
	public int getCsp(){
		return Integer.parseInt(sPort.getText());
	}
	private ActionListener al1=new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				ClientControl1.getInstance().isConnect();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	};
	private void setP0(){
		P0=new JPanel();
		Tip0=new JLabel("请输入服务器地址: ");
		Cip=new JTextField(14);
		Cip.setText(ServerData.getInstance().getCcd().getCip());
		Tip1=new JLabel("端口: ");
		sPort=new JTextField(5);
		sPort.setText("7090");
		Ready=new JButton("确认");
		Ready.addActionListener(al1);
		P0.add(Tip0);
		P0.add(Cip);
		P0.add(Tip1);
		P0.add(sPort);
		P0.add(Ready);
		this.add("North",P0);
	}
	private JPanel P1;
	private JPanel P10;
	private TitledBorder tb0;
	private TextArea Send;
	private JButton SendB;
	private JPanel P11;
	private TitledBorder tb1;
	private TextArea Diary;
	private JPanel P12;
	private ActionListener al=new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			ClientControl1.getInstance().Send();
		}
	};
	private void setP1(){
		P1=new JPanel();
		P1.setLayout(new BorderLayout());
		P10=new JPanel();
		tb0=BorderFactory.createTitledBorder("需要发送的信息");
		P10.setBorder(tb0);
		Send=new TextArea();
		P10.add(Send);
		P1.add("North",P10);
		SendB=new JButton("发送");
		SendB.addActionListener(al);
		P12=new JPanel();
		P12.add(SendB);
		P1.add("Center",P12);
		P11=new JPanel();
		tb1=BorderFactory.createTitledBorder("日志");
		P11.setBorder(tb1);
		Diary=new TextArea();
		P11.add(Diary);
		P1.add("South",P11);
		this.add("Center",P1);
	}
	public void addDiary(String s){
		Diary.append(s);
	}
	public String getSend(){
		return Send.getText();
	}
	private JLabel Tip2;
	private String tip2;
	public JLabel getTip2(){
		return Tip2;
	}
	private void setTip2(){
		tip2=new String("  连接服务器"+ServerData.getInstance().getCcd().getCip()
				+": "+getCsp()+" 成功!");
		Tip2=new JLabel(tip2);
		this.add("South",Tip2);
	}
	private void setCF(){
		this.setTitle("Client1");
		this.setBounds(1550, 100, 520, 580);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	public void closeClient(){
		this.dispose();
	}
	private static ClientControl1[] cc=new ClientControl1[3];
	private ClientFrame1(){
		super();
		this.setLayout(new BorderLayout());
		setP0();
		setP1();
		setTip2();
		ClientControl1.getInstance();
		setCF();
		System.out.println("cf");
	}
}
