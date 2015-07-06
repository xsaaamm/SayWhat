package gui;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;


public class HelpPane extends JPanel {

	private static final long serialVersionUID = -2999089748391970671L;

	/**
	 * Create the panel.
	 */
	public HelpPane() {
		setBackground(new Color(0, 0, 0));
		setLayout(null);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(180, 5, 89, 23);
		add(btnNewButton);

	}

}
