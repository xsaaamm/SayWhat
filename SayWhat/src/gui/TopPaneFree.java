package gui;

import java.awt.GridLayout;

import javax.swing.JPanel;

public class TopPaneFree extends JPanel {

	private static final long serialVersionUID = -6630087096470361185L;
	static JPanel inputPanel = new InputPanel();
	JPanel displayPanel = new JPanel();
	ButtonPanel playOptPanel = new ButtonPanel();
	
	public TopPaneFree() {
		intitalize();
	}

	private void intitalize() {
		setLayout(new GridLayout(1, 0, 0, 0));
		add(inputPanel);
		add(playOptPanel);	
		add(displayPanel);
			
		displayPanel.add(new displayOptionsPanel());
		displayPanel.setLayout(new GridLayout(0, 1));
		
		playOptPanel.setLayout(new GridLayout(0, 1));
		
		inputPanel.setLayout(new GridLayout(1, 0, 0, 0));
	}


}
