package MyChess;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FunctionPanel extends JPanel{
	private static FunctionPanel Instance;
	public static FunctionPanel getInstance(){
		if(Instance==null){
			Instance=new FunctionPanel();
		}
		return Instance;
	}
	
	private JButton RepealBtn=new JButton("»ÚÆå");
	private JButton ReplayBtn=new JButton("ÖØÍæ");
	private JButton SurrenderBtn=new JButton("Í¶½µ");
	private JButton AgreeBtn=new JButton("ºÍÆå");
	private ActionListener alBtn=new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			String command=e.getActionCommand();
			if(command.equals("»ÚÆå")){
				Controller.getInstance().Repeal();
			}
			else if(command.equals("ÖØÍæ")){
				Controller.getInstance().Replay();
			}
			else if(command.equals("Í¶½µ")){
				Controller.getInstance().Surrender();
			}
			else if(command.equals("ºÍÆå")){
				Controller.getInstance().Stalemate();
			}
		}
	};
	private FunctionPanel(){
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		Component vGlue=Box.createVerticalGlue();
		Component vGlue1=Box.createVerticalGlue();

		RepealBtn.addActionListener(alBtn);
		ReplayBtn.addActionListener(alBtn);
		SurrenderBtn.addActionListener(alBtn);
		AgreeBtn.addActionListener(alBtn);

		add(vGlue);
		add(RepealBtn);
		add(ReplayBtn);
		if(Controller.getInstance().getNetMode()){
			add(SurrenderBtn);
			add(AgreeBtn);
		}
		add(vGlue1);

	}
}
