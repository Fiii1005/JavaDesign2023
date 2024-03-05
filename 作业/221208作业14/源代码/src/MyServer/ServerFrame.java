package MyServer;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;

public class ServerFrame extends JFrame{
	private static ServerFrame Instance;
	public static ServerFrame getInstance(){
		if(Instance==null){
			Instance=new ServerFrame();
		}
		return Instance;
	}
	private JPanel P0;
	private JLabel Tip0;
	private JTextField sPort;
	private JButton ss;
	public int getSsp(){
		return Integer.parseInt(sPort.getText());
	}
	private ActionListener a1=new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			boolean isStart=!(ServerData.getInstance().getSS().equals("start"));
			if(!isStart){
				Tip1.setText("  服务启动，端口为："+getSsp());
				ss.setText("stop");
				ServerData.getInstance().setSS("stop");
				try {
					ServerControl.getInstance().LinkToClient();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else{
				Tip1.setText(" ");
				ss.setText("start");
				ServerData.getInstance().setSS("start");
				try {
					ServerControl.getInstance().Stop();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	};
	private void setP0(){
		Tip0=new JLabel("请输入服务端口号: ");
		sPort=new JTextField(10);
		sPort.setText("7090");
		ss=new JButton(ServerData.getInstance().getSS());
		ss.addActionListener(a1);
		P0=new JPanel();
		this.add("North",P0);
		P0.add(Tip0);
		P0.add(sPort);
		P0.add(ss);
	}
	private JPanel P1;
	private JPanel P10;
	private TextArea Send;
	private TitledBorder tb0;
	private JPanel P11;
	private JButton Sendto;
	private final String[] cList=new String[]{"null"};
	private JComboBox<String> Clients=new JComboBox<String>(cList);
	private JPanel P12;
	private TitledBorder tb1;
	private TextArea Diary;
	private boolean isConnect;
	private int curC;
	public void addClients(String s){
		Clients.addItem(s);
	}
	public void deleteClients(int num){
		for(int i=1;i<=num;i++){
			Clients.removeItemAt(1);
		}
	}
	private ActionListener al0=new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			if(curC==-1){
				addDiary("没有接收对象！");
			}
			else{
				ServerControl.getInstance().Send(curC);
			}
		}
	};
	private ItemListener il=new ItemListener(){
		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			if(e.getStateChange()==e.SELECTED){
				String command=(String)e.getItem();
				if(command.equals("Client0")){
					curC=0;
				}
				else if(command.equals("Client1")){
					curC=1;
				}
				else if(command.equals("null")){
					curC=-1;
				}
			}
		}
	};
	private void setP1(){
		P1=new JPanel();
		P1.setLayout(new BorderLayout());
		P10=new JPanel();
		tb0=BorderFactory.createTitledBorder("需要发送的内容");
		P10.setBorder(tb0);
		Send=new TextArea();
		P10.add(Send);
		P1.add("North",P10);
		P11=new JPanel();
		Sendto=new JButton("发送至");
		Sendto.addActionListener(al0);
		isConnect=false;
		Clients.addItemListener(il);
		P11.add(Sendto);
		P11.add(Clients);
		P1.add("Center",P11);
		P12=new JPanel();
		tb1=BorderFactory.createTitledBorder("日志");
		P12.setBorder(tb1);
		Diary=new TextArea();
		P12.add(Diary);
		P1.add("South",P12);
		this.add("Center",P1);
	}
	public void addDiary(String s){
		Diary.append(s);
	}
	public String getSend(){
		return Send.getText();
	}
	private JLabel Tip1;
	private String tip1;
	private void setP2(){
		tip1=new String("  服务启动，端口为："+getSsp());
		Tip1=new JLabel(tip1);
		this.add("South",Tip1);
	}
	private void setMain(){
		this.setTitle("Server");
		this.setBounds(450,100,520,580);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	private ServerFrame(){
		super();
		this.setLayout(new BorderLayout());
		setP0();
		setP1();
		setP2();
		setMain();
		System.out.println("sf");
	}
}
