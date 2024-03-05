package Model;

import java.awt.*;

public class MyShape {
	private int FirstX,FirstY,SecondX,SecondY;
	private Color shapeColor;
	private String shape;
	private String Content;
	private Font MyFont;
	private boolean fillregion;
	public void setShape(String shape) {
		this.shape = shape;
	}
	public void setFirstX(int firstX) {
		FirstX = firstX;
	}
	public void setFirstY(int firstY) {
		FirstY = firstY;
	}
	public void setSecondX(int secondX) {
		SecondX = secondX;
	}
	public void setSecondY(int secondY) {
		SecondY = secondY;
	}
	public void setShapeColor(Color c) {
		this.shapeColor = c;
	}
	public int getFirstX() {
		return FirstX;
	}
	public int getFirstY() {
		return FirstY;
	}
	public int getSecondX() {
		return SecondX;
	}
	public int getSecondY() {
		return SecondY;
	}
	public Color getShapeColor() {
		return shapeColor;
	}
	public String getShape() {
		return shape;
	}
	public void setContent(String c){
		Content = c;
	}
	public String getContent() {
		return Content;
	}
	
	public Font getMyFont() {
		return MyFont;
	}
	public void setMyFont(Font myFont) {
		MyFont = myFont;
	}
	public boolean isFillregion() {
		return fillregion;
	}
	public MyShape(String shape,Color shapeColor,int firstX,int firstY,int secondX,int secondY,boolean fillregion){
		this.shape=shape;
		this.shapeColor=shapeColor;
		this.FirstX=firstX;
		this.FirstY=firstY;
		this.SecondX=secondX;
		this.SecondY=secondY;
		this.fillregion=fillregion;
	}
	public MyShape(String shape,Color c,int x,int y){
		this.shape=shape;
		this.FirstX=x;
		this.FirstY=y;
		this.shapeColor=c;
		MyFont=new Font("Microsoft YaHei UI Light",Font.BOLD,20);
	}
}
