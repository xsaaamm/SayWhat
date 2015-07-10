package main;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;


public class RecordsPane extends JPanel {

	private static final long serialVersionUID = -3139215690390725422L;

	/**
	 * Create the panel.
	 */
	public RecordsPane() {
		setBackground(new Color(0, 0, 0));
		setLayout(null);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(131, 101, 89, 23);
		add(btnNewButton);

	}

}
