package Model;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.basic.BasicArrowButton;

public class TextFrame extends JFrame{
	private JPanel addTextPanel;
	private JPanel FontSizePanel;
	private JLabel Tips;
	private JLabel Tips1;
	private JLabel Tips2;
	private JTextField Input;
	private JTextField FontSize;
	private String MyText;
	private String MyFontType;
	private String[] FontTypes={"Microsoft YaHei UI Light","AdobeDevanagari-Regular"};
	private JPanel Fonttype;
	private JComboBox FT;
	private JPanel SizeArrow;
	private BasicArrowButton upSize;
	private BasicArrowButton downSize;
	private int MyFontSize;
	private Font MyFont;
	private JButton OKButton;
	private DocumentListener dl = new DocumentListener(){
		public void changedUpdate(DocumentEvent e){
			System.out.println("changedUpdate");
		}

		@Override
		public void insertUpdate(DocumentEvent arg0) {
			System.out.println("insertUpdate");
			TextChange();
		}

		@Override
		public void removeUpdate(DocumentEvent arg0) {
			System.out.println("removeUpdate");
			TextChange();
		}
	};
	private ActionListener al = new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	};
	private ActionListener al2 = new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			int command = ((BasicArrowButton)e.getSource()).getDirection();
			if(command==SwingConstants.NORTH){
				MyFontSize++;
			}
			else{
				MyFontSize--;
			}
			FontSize.setText(String.valueOf(MyFontSize));
			FontChange();
		}
		
	};
	private ItemListener il = new ItemListener(){
		@Override
		public void itemStateChanged(ItemEvent e) {
			MyFontType=(String)e.getItem();
			FontChange();
		}
		
	};
	private void TextChange(){
		MyText = Input.getText();
		MyPaint.getInstance().getMyShapes()[MyPaint.getInstance().getShapenum()-1].setContent(MyText);
		MyPaint.getInstance().repaint();
	}
	private void FontChange(){
		MyFont = new Font(MyFontType,Font.BOLD,MyFontSize);
		MyPaint.getInstance().getMyShapes()[MyPaint.getInstance().getShapenum()-1].setMyFont(MyFont);
		System.out.println("¸üÐÂ×ÖºÅ"+MyFontSize);
		MyPaint.getInstance().repaint();
	}
	
	private void setInput(){
		addTextPanel = new JPanel();
		Tips1 = new JLabel("Please input:");
		Tips1.setFont(new Font("Britannic Bold",Font.BOLD,20));
		Input = new JTextField(MyFontSize);
		Input.getDocument().addDocumentListener(dl);
		addTextPanel.add(Tips1);
		addTextPanel.add(Input);
		
		FontSizePanel = new JPanel();
		Tips2 = new JLabel("Size:");
		Tips2.setFont(new Font("Britannic Bold",Font.BOLD,20));
		FontSize = new JTextField(String.valueOf(MyFontSize),10);
		upSize=new BasicArrowButton(SwingConstants.NORTH);
		upSize.addActionListener(al2);
		downSize=new BasicArrowButton(SwingConstants.SOUTH);
		downSize.addActionListener(al2);
		SizeArrow = new JPanel();
		SizeArrow.setLayout(new GridLayout(2,1));
		SizeArrow.add(upSize);
		SizeArrow.add(downSize);
		
		Fonttype=new JPanel();
		Tips=new JLabel("Font Type:");
		Tips.setFont(new Font("Britannic Bold",Font.BOLD,20));
		FT=new JComboBox(FontTypes);
		FT.addItemListener(il);
		Fonttype.add(Tips);
		Fonttype.add(FT);
		
		FontSizePanel.add(Tips2);
		FontSizePanel.add(FontSize);
		FontSizePanel.add(SizeArrow);
	}
	public TextFrame(){
		super();
		MyText=new String();
		MyFontType="Microsoft YaHei UI Light";
		MyFontSize=20;
		MyFont = new Font(MyFontType,Font.BOLD,MyFontSize);
		
		setInput();
		OKButton = new JButton("OK");
		OKButton.addActionListener(al);
		
		this.setLayout(new GridLayout(4,1));
		this.add(addTextPanel);
		this.add(FontSizePanel);
		this.add(Fonttype);
		this.add(OKButton);
		this.setTitle("Text");
		this.setLocation(1200, 300);
		this.setSize(500,300);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
}
