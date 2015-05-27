package gui;

import java.awt.GridLayout;
//import java.awt.event.ActionListener;
//import javax.swing.ButtonGroup;
import javax.swing.JPanel;
//import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

public class PitchDetectionPanel extends JPanel {

	private static final long serialVersionUID = -5107785666165487335L;
	
	public PitchDetectionPanel(){
		super(new GridLayout(0,1));
		setBorder(new TitledBorder("Graph will be here"));

	}
}
