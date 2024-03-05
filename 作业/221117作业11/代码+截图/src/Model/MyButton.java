package Model;


import javax.swing.*;

public class MyButton extends JButton{
	private String BName;
	public MyButton(){
		super();
	}
	public MyButton(String n){
		super();
		BName = n;
	}
	public String getBname() {
		return BName;
	}
}
