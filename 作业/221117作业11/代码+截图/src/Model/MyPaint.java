package Model;

import javax.swing.*;
import java.awt.*;

public class MyPaint extends JPanel{
	private static MyPaint Instance;
	public static MyPaint getInstance(){
		if(Instance==null){
			Instance=new MyPaint();
		}
		return Instance;
	}
	private MyShape[] MyShapes;
	private int Shapenum;
	private String NextShape;
	private int NextShapeX;
	private int NextShapeY;
	private int PaintingX;
	private int PaintingY;
	private boolean Fillregion;
	public void addShape(int secondX,int secondY){
		MyShapes[Shapenum]=new MyShape(NextShape,MyFrame.getInstance().getShapeColor(),NextShapeX,NextShapeY,secondX,secondY,Fillregion);
		if(NextShape.equals("Circle")){
			MyShapes[Shapenum].setSecondY(secondX-NextShapeX+NextShapeY);
		}
		System.out.println("添加了第"+Shapenum+"个图形："+MyShapes[Shapenum].getShape());
		Shapenum++;
	}
	public void AddText(){
		TextFrame newText = new TextFrame();
		MyShapes[Shapenum]=new MyShape(NextShape,MyFrame.getInstance().getShapeColor(),NextShapeX,NextShapeY);
		System.out.println("添加了第"+Shapenum+"个图形："+MyShapes[Shapenum].getShape());
		Shapenum++;
	}
	public void setNextshape(String ns){
		NextShape=ns;
	}
	public String getNextshape(){
		return NextShape;
	}
	public int getNextShapeX() {
		return NextShapeX;
	}
	public void setNextShapeX(int nextShapeX) {
		NextShapeX = nextShapeX;
	}
	public int getNextShapeY() {
		return NextShapeY;
	}
	public void setNextShapeY(int nextShapeY) {
		NextShapeY = nextShapeY;
	}
	public void setPaintingX(int paintingX) {
		PaintingX = paintingX;
	}
	public void setPaintingY(int paintingY) {
		PaintingY = paintingY;
	}
	public MyShape[] getMyShapes() {
		return MyShapes;
	}
	public int getShapenum() {
		return Shapenum;
	}
	public boolean isFillregion() {
		return Fillregion;
	}
	public void setFillregion(boolean fillregion) {
		Fillregion = fillregion;
	}
	public void paint(Graphics g){
		super.paint(g);//继承父类方法
		this.setBackground(MyFrame.getInstance().getBackgroundColor());
		for(int i=0;i<Shapenum;i++){
			System.out.println("画了第"+i+"个图形:"+MyShapes[i].getShape());
			g.setColor(MyShapes[i].getShapeColor());
			int firstX=MyShapes[i].getFirstX();
			int firstY=MyShapes[i].getFirstY();
			int secondX=MyShapes[i].getSecondX();
			int secondY=MyShapes[i].getSecondY();
			if(MyShapes[i].getShape().equals("Circle")){
				if(MyShapes[i].isFillregion()){
					if(firstX<secondX){
						if(firstY<secondY){
							g.fillOval(firstX, firstY, secondX-firstX, secondX-firstX);
						}
						else{
							g.fillOval(firstX, secondY, secondX-firstX, secondX-firstX);;
						}
					}
					else{
						if(firstY<secondY){
							g.fillOval(secondX, firstY, firstX-secondX, firstX-secondX);
						}
						else{
							g.fillOval(secondX, secondY, firstX-secondX, firstX-secondX);
						}
					}
				}else{
					if(firstX<secondX){
						if(firstY<secondY){
							g.drawOval(firstX, firstY, secondX-firstX, secondX-firstX);
						}
						else{
							g.drawOval(firstX, secondY, secondX-firstX, secondX-firstX);;
						}
					}
					else{
						if(firstY<secondY){
							g.drawOval(secondX, firstY, firstX-secondX, firstX-secondX);
						}
						else{
							g.drawOval(secondX, secondY, firstX-secondX, firstX-secondX);
						}
					}
				}
			}
			else if(MyShapes[i].getShape().equals("Elliptical")){
				if(MyShapes[i].isFillregion()){
					if(firstX<secondX){
						if(firstY<secondY){
							g.fillOval(firstX, firstY, secondX-firstX, secondY-firstY);
						}
						else{
							g.fillOval(firstX, secondY, secondX-firstX, firstY-secondY);;
						}
					}
					else{
						if(firstY<secondY){
							g.fillOval(secondX, firstY, firstX-secondX, secondY-firstY);
						}
						else{
							g.fillOval(secondX, secondY, firstX-secondX, firstY-secondY);
						}
					}
				}else{
					if(firstX<secondX){
						if(firstY<secondY){
							g.drawOval(firstX, firstY, secondX-firstX, secondY-firstY);
						}
						else{
							g.drawOval(firstX, secondY, secondX-firstX, firstY-secondY);;
						}
					}
					else{
						if(firstY<secondY){
							g.drawOval(secondX, firstY, firstX-secondX, secondY-firstY);
						}
						else{
							g.drawOval(secondX, secondY, firstX-secondX, firstY-secondY);
						}
					}
				}
			}
			else if(MyShapes[i].getShape().equals("Line")){
				g.drawLine(firstX, firstY, secondX, secondY);
			}
			else if(MyShapes[i].getShape().equals("Rectangle")){
				if(MyShapes[i].isFillregion()){
					if(firstX<secondX){
						if(firstY<secondY){
							g.fillRect(firstX, firstY, secondX-firstX, secondY-firstY);
						}
						else{
							g.fillRect(firstX, secondY, secondX-firstX, firstY-secondY);;
						}
					}
					else{
						if(firstY<secondY){
							g.fillRect(secondX, firstY, firstX-secondX, secondY-firstY);
						}
						else{
							g.fillRect(secondX, secondY, firstX-secondX, firstY-secondY);
						}
					}
				}else{
					if(firstX<secondX){
						if(firstY<secondY){
							g.drawRect(firstX, firstY, secondX-firstX, secondY-firstY);
						}
						else{
							g.drawRect(firstX, secondY, secondX-firstX, firstY-secondY);;
						}
					}
					else{
						if(firstY<secondY){
							g.drawRect(secondX, firstY, firstX-secondX, secondY-firstY);
						}
						else{
							g.drawRect(secondX, secondY, firstX-secondX, firstY-secondY);
						}
					}
				}
			}
			else if(MyShapes[i].getShape().equals("Text")){
				g.setFont(MyShapes[i].getMyFont());
				g.drawString(MyShapes[i].getContent(), firstX, firstY);
			}
		}
		g.setColor(MyFrame.getInstance().getShapeColor());
		if(NextShape.equals("Circle")){
			if(Fillregion){
				if(NextShapeX<PaintingX){
					if(NextShapeY<PaintingY){
						g.fillOval(NextShapeX, NextShapeY, PaintingX-NextShapeX, PaintingX-NextShapeX);
					}
					else{
						g.fillOval(NextShapeX, PaintingY, PaintingX-NextShapeX, PaintingX-NextShapeX);;
					}
				}
				else{
					if(NextShapeY<PaintingY){
						g.fillOval(PaintingX, NextShapeY, NextShapeX-PaintingX, NextShapeX-PaintingX);
					}
					else{
						g.fillOval(PaintingX, PaintingY, NextShapeX-PaintingX, NextShapeX-PaintingX);
					}
				}
			}
			else{
				if(NextShapeX<PaintingX){
					if(NextShapeY<PaintingY){
						g.drawOval(NextShapeX, NextShapeY, PaintingX-NextShapeX, PaintingX-NextShapeX);
					}
					else{
						g.drawOval(NextShapeX, PaintingY, PaintingX-NextShapeX, PaintingX-NextShapeX);;
					}
				}
				else{
					if(NextShapeY<PaintingY){
						g.drawOval(PaintingX, NextShapeY, NextShapeX-PaintingX, NextShapeX-PaintingX);
					}
					else{
						g.drawOval(PaintingX, PaintingY, NextShapeX-PaintingX, NextShapeX-PaintingX);
					}
				}
			}
		}
		else if(NextShape.equals("Elliptical")){
			if(Fillregion){
				if(NextShapeX<PaintingX){
					if(NextShapeY<PaintingY){
						g.fillOval(NextShapeX, NextShapeY, PaintingX-NextShapeX, PaintingY-NextShapeY);
					}
					else{
						g.fillOval(NextShapeX, PaintingY, PaintingX-NextShapeX, NextShapeY-PaintingY);;
					}
				}
				else{
					if(NextShapeY<PaintingY){
						g.fillOval(PaintingX, NextShapeY, NextShapeX-PaintingX, PaintingY-NextShapeY);
					}
					else{
						g.fillOval(PaintingX, PaintingY, NextShapeX-PaintingX, NextShapeY-PaintingY);
					}
				}
			}
			else{
				if(NextShapeX<PaintingX){
					if(NextShapeY<PaintingY){
						g.drawOval(NextShapeX, NextShapeY, PaintingX-NextShapeX, PaintingY-NextShapeY);
					}
					else{
						g.drawOval(NextShapeX, PaintingY, PaintingX-NextShapeX, NextShapeY-PaintingY);;
					}
				}
				else{
					if(NextShapeY<PaintingY){
						g.drawOval(PaintingX, NextShapeY, NextShapeX-PaintingX, PaintingY-NextShapeY);
					}
					else{
						g.drawOval(PaintingX, PaintingY, NextShapeX-PaintingX, NextShapeY-PaintingY);
					}
				}
			}
		}
		else if(NextShape.equals("Line")){
			g.drawLine(NextShapeX, NextShapeY, PaintingX, PaintingY);
		}
		else if(NextShape.equals("Rectangle")){
			if(Fillregion){
				if(NextShapeX<PaintingX){
					if(NextShapeY<PaintingY){
						g.fillRect(NextShapeX, NextShapeY, PaintingX-NextShapeX, PaintingY-NextShapeY);
					}
					else{
						g.fillRect(NextShapeX, PaintingY, PaintingX-NextShapeX, NextShapeY-PaintingY);;
					}
				}
				else{
					if(NextShapeY<PaintingY){
						g.fillRect(PaintingX, NextShapeY, NextShapeX-PaintingX, PaintingY-NextShapeY);
					}
					else{
						g.fillRect(PaintingX, PaintingY, NextShapeX-PaintingX, NextShapeY-PaintingY);
					}
				}
			}
			else{
				if(NextShapeX<PaintingX){
					if(NextShapeY<PaintingY){
						g.drawRect(NextShapeX, NextShapeY, PaintingX-NextShapeX, PaintingY-NextShapeY);
					}
					else{
						g.drawRect(NextShapeX, PaintingY, PaintingX-NextShapeX, NextShapeY-PaintingY);;
					}
				}
				else{
					if(NextShapeY<PaintingY){
						g.drawRect(PaintingX, NextShapeY, NextShapeX-PaintingX, PaintingY-NextShapeY);
					}
					else{
						g.drawRect(PaintingX, PaintingY, NextShapeX-PaintingX, NextShapeY-PaintingY);
					}
				}
			}
		}
	}
	public void ReNew(){
		for(int i=0;i<Shapenum;i++){
			MyShapes[i]=null;
		}
		Shapenum=0;
		NextShape="Mouse";
	}
	public void Repeal(){
		MyShapes[Shapenum]=null;
		Shapenum--;
		NextShape="Mouse";
	}
	
	private MyPaint(){
		NextShape="mouse";
		MyShapes = new MyShape[1000];
		Shapenum=0;
		Fillregion=false;
	}
}
