package Model;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MyFrame extends JFrame{
	private JMenu[] menus = {
			new JMenu("File"),new JMenu("Edit"),new JMenu("Help")
	};
	private JMenuItem[] items = {
			new JMenuItem("New"),new JMenuItem("Repeal")
	};
	private ActionListener al = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			String command = e.getActionCommand();
			ButtonControl.getInstance().Press(command);
		}
	};
	private ActionListener al2 = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			String command = ((MyButton)e.getSource()).getBname();
			ButtonControl.getInstance().Press(command);
		}
	};
	private ItemListener il = new ItemListener(){
		@Override
		public void itemStateChanged(ItemEvent e) {
			JCheckBox jcb = (JCheckBox)e.getItem();
			MyPaint.getInstance().setFillregion(jcb.isSelected());
		}
	};
	
	private int MouseX;
	private int MouseY;
	public void setMouseX(int x){
		MouseX = x;
	}
	public void setMouseY(int y){
		MouseY = y;
	}
	public int getMouseX() {
		return MouseX;
	}
	public int getMouseY() {
		return MouseY;
	}
	private JLabel MouseLocation = new JLabel(" Mouse Location —— x:"+MouseX+" y:"+MouseY);
	
	
	private JMenuBar mb = new JMenuBar();
	
	private FlowLayout fl1 = new FlowLayout(FlowLayout.LEFT);
	private JPanel PropertyBar = new JPanel(fl1);
	private JPanel ShapeColor = new JPanel();
	private JPanel Background = new JPanel();
	private JCheckBox FillRegion;
	private JLabel SClabel = new JLabel("Shape Color");
	private JLabel BGlabel = new JLabel("Background");
	private MyButton SCbutton = new MyButton("ShapeColor");
	private MyButton BGbutton = new MyButton("BackgroundColor");
	private Border bB = BorderFactory.createBevelBorder(BevelBorder.RAISED);
	private MyButton[] DToolsButton = {
		new MyButton("Mouse"),	new MyButton("Circle"),new MyButton("Elliptical"),
		new MyButton("Line"),new MyButton("Rectangle"),new MyButton("Text")
	};
	private Icon[] DToolsIcon = {
			new ImageIcon(getClass().getResource("Mouse.png")),
			new ImageIcon(getClass().getResource("Circle.png")),
			new ImageIcon(getClass().getResource("Elliptical.png")),
			new ImageIcon(getClass().getResource("Line.png")),
			new ImageIcon(getClass().getResource("Rectangle.png")),
			new ImageIcon(getClass().getResource("Text.png"))
	};
	private JPanel DToolsPanel = new JPanel();
	
	private Color backgroundColor = new Color(255,255,255,255);
	private Color shapeColor = new Color(0,0,0,255);
	public void setShapeColor(Color shapeColor) {
		this.shapeColor = shapeColor;
	}
	public Color getShapeColor() {
		return shapeColor;
	}
	public Color getBackgroundColor() {
		return backgroundColor;
	}
	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	private void setMenu(){
		for(int i=0;i<items.length;i++){
			items[i].addActionListener(al);
		}
		menus[0].add(items[0]);
		menus[0].add(items[1]);
		for(JMenu jm:menus){
			mb.add(jm);
		}
	}
	private void setPB(){
		SCbutton.setPreferredSize(new Dimension(20,20));//只能用setPreferredSize设置大小
		SCbutton.setBackground(shapeColor);
		SCbutton.addActionListener(al2);
		BGbutton.setPreferredSize(new Dimension(20,20));
		BGbutton.setBackground(backgroundColor);
		BGbutton.addActionListener(al2);
		ShapeColor.add(SCbutton);
		ShapeColor.add(SClabel);
		Background.add(BGbutton);
		Background.add(BGlabel);
		FillRegion = new JCheckBox("Fill Region");
		FillRegion.addItemListener(il);
		
		FlowLayout fl = new FlowLayout(FlowLayout.LEFT);
		fl.setHgap(15);
		PropertyBar.setLayout(fl);
		PropertyBar.add(ShapeColor);
		PropertyBar.add(Background);
		PropertyBar.add(FillRegion,"East");
		PropertyBar.setBorder(bB);
	}
	private void setIcon(){
		for(int i=0;i<6;i++){
			Image img = ((ImageIcon) DToolsIcon[i]).getImage();
			img = img.getScaledInstance(80, 80, Image.SCALE_DEFAULT);
			((ImageIcon) DToolsIcon[i]).setImage(img);
			DToolsPanel.add(DToolsButton[i]);
			DToolsButton[i].setPreferredSize(new Dimension(80,80));
			DToolsButton[i].setIcon(DToolsIcon[i]);
			DToolsButton[i].addActionListener(al2);
		}
	}
	
	
	public void updateBGB(){
		BGbutton.setBackground(backgroundColor);
	}
	public void updateSCB(){
		SCbutton.setBackground(shapeColor);
	}
	
	
	public JLabel getMouseLocation() {
		return MouseLocation;
	}
	public MyButton getSCbutton() {
		return SCbutton;
	}
	public MyButton getBGbutton() {
		return BGbutton;
	}
	private MyFrame(){
		setMenu();
		this.setJMenuBar(mb);
		setPB();
		this.add("North",PropertyBar);
		this.add("South",MouseLocation);
		setIcon();
		DToolsPanel.setPreferredSize(new Dimension(100,800));
		this.getContentPane().add(DToolsPanel,"West");
		MyPaint.getInstance().setPreferredSize(new Dimension(900,800));
		this.add(MyPaint.getInstance());
		MyPaint.getInstance().addMouseListener(MyMouseListener.getInstance());
		MyPaint.getInstance().addMouseMotionListener(MyMouseListener.getInstance());
		MyPaint.getInstance().setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		MyPaint.getInstance().setBackground(backgroundColor);
		
		this.setTitle("JavaPainter");
		this.setLocation(200, 100);
		this.setSize(1000,800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}
	private static MyFrame Instance;
	public static MyFrame getInstance(){
		if(Instance==null){
			Instance = new MyFrame();
		}
		return Instance;
	}
}
