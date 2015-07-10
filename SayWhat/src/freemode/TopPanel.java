package freemode;

import java.awt.GridLayout;
import javax.swing.JPanel;

public class TopPanel extends JPanel {

	private static final long serialVersionUID = -6630087096470361185L;
	public static JPanel startPanel = new StartPanel();
	static JPanel displayPanel = new JPanel();
	static PlayBackPanel playOptPanel = new PlayBackPanel();
	
	public TopPanel() {
		intitalize();
	}

	private void intitalize() {
		setLayout(new GridLayout(1, 0, 0, 0));
		add(startPanel);
		add(playOptPanel);	
		add(displayPanel);
			
		displayPanel.add(new displayOptionsPanel());
		displayPanel.setLayout(new GridLayout(0, 1));
		
		playOptPanel.setLayout(new GridLayout(0, 1));
		
		startPanel.setLayout(new GridLayout(1, 0, 0, 0));
	}


}
