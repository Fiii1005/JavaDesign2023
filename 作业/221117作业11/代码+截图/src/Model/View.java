package Model;

public class View {
	private View(){}
	private static View Instance;
	public static View getInstance(){
		if(Instance==null){
			Instance=new View();
		}
		return Instance;
	}
	public static void main(String[] args) {
		MyFrame.getInstance();
	}
	public void updateMouseLocation(){
		MyFrame.getInstance().getMouseLocation().setText(" Mouse Location ¡ª¡ª x:"
															+MyFrame.getInstance().getMouseX()
															+" y:"
															+MyFrame.getInstance().getMouseY());
	}
	public void updateBC(){
		MyPaint.getInstance().repaint();
		MyFrame.getInstance().updateBGB();
	}
	public void updateSC(){
		MyPaint.getInstance().repaint();
		MyFrame.getInstance().updateSCB();
	}
	
}
