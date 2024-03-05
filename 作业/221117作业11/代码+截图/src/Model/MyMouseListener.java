package Model;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.*;

public class MyMouseListener implements MouseListener,MouseMotionListener {
	private MyMouseListener(){}
	private static MyMouseListener Instance;
	public static MyMouseListener getInstance(){
		if(Instance == null){
			Instance = new MyMouseListener();
		}
		return Instance;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		MyPaint.getInstance().setPaintingX(e.getX());
		MyPaint.getInstance().setPaintingY(e.getY());
		MyPaint.getInstance().repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		MyFrame.getInstance().setMouseX(e.getX());
		MyFrame.getInstance().setMouseY(e.getY());
		View.getInstance().updateMouseLocation();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(MyPaint.getInstance().getNextshape().equals("Text")){
			MyPaint.getInstance().setNextShapeX(e.getX());
			MyPaint.getInstance().setNextShapeY(e.getY());
			MyPaint.getInstance().AddText();
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		MyPaint.getInstance().setNextShapeX(e.getX());
		MyPaint.getInstance().setNextShapeY(e.getY());
		System.out.println("鼠标已经按下");
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(!MyPaint.getInstance().getNextshape().equals("Text")){
			System.out.println("鼠标已经松开");
		MyPaint.getInstance().addShape(e.getX(), e.getY());
		MyPaint.getInstance().repaint();
		}
	}
	
	
}
