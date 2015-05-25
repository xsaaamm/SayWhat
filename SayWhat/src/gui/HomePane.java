package gui;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.FlowLayout;


public class HomePane extends JPanel {

	private static final long serialVersionUID = -7041257335555335691L;

	/**
	 * Create the panel.
	 */
	public HomePane() {
		setBackground(new Color(0, 0, 0));
		setForeground(new Color(0, 0, 0));
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		
		JButton btntest = new JButton("New button");
		add(btntest);
		
	}
	
	

}
