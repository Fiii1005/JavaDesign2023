package MyChess;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class FirstFrame extends JFrame{
	private static FirstFrame Instance;
	public static FirstFrame getInstance(){
		if(Instance==null){
			Instance=new FirstFrame();
		}
		return Instance;
	}
	private JLabel Title=new JLabel("������");
	private JButton localVBtn=new JButton("���ذ�");
	private JButton netVBtn=new JButton("�����");
	private ActionListener alBtn=new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			String command=e.getActionCommand();
			if(command.equals("�����")){
				Controller.getInstance().setNetMode(true);
			}
			GameFrame.getInstance();
			dispose();
		}
	};
	private FirstFrame(){
		setTitle("������");
		setLocation(500,500);
		setSize(400,400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Font f=new Font("Microsoft Yahei UI",Font.BOLD,20);
		Title.setFont(f);
		
		localVBtn.addActionListener(alBtn);
		netVBtn.addActionListener(alBtn);
		
		JPanel p1=new JPanel();
		p1.setLayout(new GridLayout(3,1,0,0));
		
		Title.setHorizontalAlignment(SwingConstants.CENTER);
		p1.add(Title);
		p1.add(localVBtn);
		p1.add(netVBtn);
		p1.setPreferredSize(new Dimension(350, 500));
		
		JPanel p0=new JPanel();
		p0.setPreferredSize(new Dimension(75,500));
		JPanel p2=new JPanel();
		p2.setPreferredSize(new Dimension(75,500));
		setLayout(new BorderLayout());
		JPanel p3=new JPanel();
		p3.setPreferredSize(new Dimension(350,80));
		JPanel p4=new JPanel();
		p4.setPreferredSize(new Dimension(350,80));
		add("Center",p1);
		add("West",p0);
		add("East",p2);
		add("North",p3);
		add("South",p4);
		
		setVisible(true);
	}
}
