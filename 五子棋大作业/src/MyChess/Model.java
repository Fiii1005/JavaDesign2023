package MyChess;

import java.util.*;

public class Model {
	private static Model Instance;
	public static Model getInstance(){
		if(Instance==null){
			Instance=new Model();
		}
		return Instance;
	}
	private Model(){
		for(int i=0;i<WIDTH;i++){
			for(int j=0;j<WIDTH;j++){
				data[i][j]=0;
			}
		}
	}
	public static final int WHITE=1;
	public static final int BLACK=-1;
	public static final int SPACE=0;
	public static final int WIDTH=19;
	private int[][] data=new int[WIDTH][WIDTH];
	
	private int lastRow;
	private int lastCol;
	
	private class Chess{
		int row;
		int col;
		Chess(int r,int c){
			row=r;
			col=c;
		}
	}
	private Stack chessStack=new Stack();
	
	public void localRepeal(){
		if(chessStack.isEmpty()){
			return;
		}
		Chess c=(Chess) chessStack.pop();
		int row=c.row;
		int col=c.col;
		data[row][col]=Model.SPACE;
	}
	
	public boolean putChess(int row,int col,int color){
		if(row>=0&&row<WIDTH&&col>=0&&col<WIDTH){
			if(data[row][col]==SPACE){
				data[row][col]=color;
				lastRow=row;
				lastCol=col;
				chessStack.push(new Chess(row,col));
				return true;
			}
		}
		return false;
	}
	
	public int getChess(int row,int col){
		if(row>=0&&row<WIDTH&&col>=0&&col<WIDTH){
			return data[row][col];
		}
		return -2;
	}
	public int judge() {
		int color=data[lastRow][lastCol];
		int num=1;
		//在row上判断
		for(int i=-1;i>=-4;i--){
			int row=lastRow;
			int col=lastCol+i;
			if(col>=0&&col<WIDTH){
				if(color==data[row][col]){
					num++;
					if(num==5){
						return color;
					}
				}else{
					break;
				}
			}else{
				break;
			}
		}
		for(int i=1;i<5;i++){
			int row=lastRow;
			int col=lastCol+i;
			if(col>=0&&col<WIDTH){
				if(color==data[row][col]){
					num++;
					if(num==5){
						return color;
					}
				}else{
					break;
				}
			}else{
				break;
			}
		}
		//在col上判断
		num=1;
		for(int i=-1;i>=-4;i--){
			int row=lastRow+i;
			int col=lastCol;
			if(row>=0&&row<WIDTH){
				if(color==data[row][col]){
					num++;
					if(num==5){
						return color;
					}
				}else{
					break;
				}
			}else{
				break;
			}
		}
		for(int i=1;i<5;i++){
			int row=lastRow+i;
			int col=lastCol;
			if(row>=0&&row<WIDTH){
				if(color==data[row][col]){
					num++;
					if(num==5){
						return color;
					}
				}else{
					break;
				}
			}else{
				break;
			}
		}
		//在二四象限斜线上判断
		num=1;
		for(int i=-1;i>=-4;i--){
			int row=lastRow+i;
			int col=lastCol+i;
			if(row>=0&&row<WIDTH&&col>=0&&col<WIDTH){
				if(color==data[row][col]){
					num++;
					if(num==5){
						return color;
					}
				}else{
					break;
				}
			}else{
				break;
			}
		}
		for(int i=1;i<5;i++){
			int row=lastRow+i;
			int col=lastCol+i;
			if(row>=0&&row<WIDTH&&col>=0&&col<WIDTH){
				if(color==data[row][col]){
					num++;
					if(num==5){
						return color;
					}
				}else{
					break;
				}
			}else{
				break;
			}
		}
		//在一三象限斜线上判断
		num=1;
		for(int i=-1;i>=-4;i--){
			int row=lastRow+i;
			int col=lastCol-i;
			if(row>=0&&row<WIDTH&&col>=0&&col<WIDTH){
				if(color==data[row][col]){
					num++;
					if(num==5){
						return color;
					}
				}else{
					break;
				}
			}else{
				break;
			}
		}
		for(int i=1;i<5;i++){
			int row=lastRow+i;
			int col=lastCol-i;
			if(row>=0&&row<WIDTH&&col>=0&&col<WIDTH){
				if(color==data[row][col]){
					num++;
					if(num==5){
						return color;
					}
				}else{
					break;
				}
			}else{
				break;
			}
		}
		return SPACE;
	}
	public void localReplay() {
		for(int i=0;i<WIDTH;i++){
			for(int j=0;j<WIDTH;j++){
				data[i][j]=SPACE;
			}
		}
	}
	public void netModeRepeal() {
		Chess c=(Chess) chessStack.pop();
		data[c.row][c.col]=SPACE;
		c=(Chess)chessStack.pop();
		data[c.row][c.col]=SPACE;
	}
}
