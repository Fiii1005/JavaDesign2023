package MyChess;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ChessPanel extends JPanel {
	private int gap=50;
	private int unit=10;
	private int sx=10;
	private int sy=10;
	private static ChessPanel Instance;
	public static ChessPanel getInstance(){
		if(Instance==null){
			Instance=new ChessPanel();
		}
		return Instance;
	}
	private ChessPanel(){
		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent arg0) {
				super.componentResized(arg0);
				int width=getWidth();
				int height=getHeight();
				int min=width<height?width:height;
				unit=(min-2*gap)/(Model.WIDTH-1);
				sx=(width-18*unit)/2;
				sy=(height-18*unit)/2;
				repaint();
			}
		});
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int col=(e.getX()-sx)/unit;
				int row=(e.getY()-sy)/unit;
				if((e.getX()-sx)%unit>unit/2){
					col++;
				}
				if((e.getY()-sy)%unit>unit/2){
					row++;
				}
				Controller.getInstance().localputChess(row,col);
			}
		});
	}
	
	
	
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		drawPanel(g);
		drawChess(g);
	}
	private void drawChess(Graphics g) {
		Model m=Model.getInstance();
		for(int row=0;row<Model.WIDTH;row++){
			for(int col=0;col<Model.WIDTH;col++){
				int color=m.getChess(row, col);
				if(color==Model.BLACK){
					g.setColor(Color.black);
					g.fillOval(sx+col*unit-unit/2,sy+row*unit-unit/2,unit,unit);
				}else if(color==Model.WHITE){
					g.setColor(Color.white);
					g.fillOval(sx+col*unit-unit/2,sy+row*unit-unit/2,unit,unit);
				}
			}
		}
		
	}
	private void drawPanel(Graphics g) {
		g.setColor(Color.black);
		for(int i=0;i<Model.WIDTH;i++){
			g.drawLine(sx, sy+unit*i, sx+unit*18, sy+unit*i);
			g.drawLine(sx+unit*i, sy, sx+unit*i, sy+unit*18);
		}
	}
}
