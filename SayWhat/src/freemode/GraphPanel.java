package freemode;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JPanel;

public class GraphPanel extends JPanel {

	private static final long serialVersionUID = -1141095145796832192L;
	public JPanel barPanel;
	
	public GraphPanel() {
		setBackground(Color.BLACK);
		setLayout(new FlowLayout(FlowLayout.LEFT));
		setVisible(true);
		
	}


}
