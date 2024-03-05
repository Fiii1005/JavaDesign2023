package Model;

import java.awt.*;
import javax.swing.*;

public class ButtonControl {
	private Color MyColor;
	private ButtonControl(){
		MyColor = new Color(0,0,0,0);
	}
	private static ButtonControl Instance;
	public static ButtonControl getInstance(){
		if(Instance == null){
			Instance = new ButtonControl();
		}
		return Instance;
	}
	public void Press(String command){
		System.out.println(command);
		if(command.equals("New")){
			MyPaint.getInstance().ReNew();
			MyPaint.getInstance().repaint();
		}
		else if(command.equals("Repeal")){
			MyPaint.getInstance().Repeal();
			MyPaint.getInstance().repaint();
		}
		else if(command.equals("ShapeColor")){
			JFrame CC = new JFrame();
			CC.setLocation(1200, 500);
			CC.setVisible(true);
			MyColor = JColorChooser.showDialog(CC, "选取颜色", null);
			CC.dispose();
			if(MyColor!=null){
				MyFrame.getInstance().setShapeColor(MyColor);
				View.getInstance().updateSC();
			}
		}
		else if(command.equals("BackgroundColor")){
			JFrame CC = new JFrame();
			CC.setLocation(1200, 500);
			CC.setVisible(true);
			MyColor = JColorChooser.showDialog(CC, "选取颜色", null);
			CC.dispose();
			if(MyColor!=null){
				MyFrame.getInstance().setBackgroundColor(MyColor);
				View.getInstance().updateBC();
			}
		}
		else{
			MyPaint.getInstance().setNextshape(command);
		}
	}
}
