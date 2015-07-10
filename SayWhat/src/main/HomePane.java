package main;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Font;
import java.awt.BorderLayout;

public class HomePane extends JPanel {

	private static final long serialVersionUID = -7041257335555335691L;
	
	private JTextArea userInfo = new JTextArea();
	private String name;
	private Font font = new Font("Arial", Font.BOLD, 32);
	private String message = "\n\n\n	Hello " + getNameFromUsername() + ",\n"
										+ "	Welcome to SayWhat!\n"
										+ "	Have Fun!";
	
	public HomePane() {
		setBackground(Color.BLACK);
		setLayout(new BorderLayout());
		
		add(userInfo, BorderLayout.CENTER);
			userInfo.setBackground(Color.BLACK);
			userInfo.setForeground(Color.YELLOW);
			userInfo.setFont(font);
			userInfo.setSize(userInfo.getMaximumSize());;
			userInfo.append(message);	
	}

	public String getNameFromUsername(){
		String username = Login.username.toString();
		if (username.equals("barretts")){
			name = "Samantha Barrett";
		}if (username.equals("beatriceb")){
			name = "Bobby Beatrice";
		}if (username.equals("cortt")){
			name = "Toure Cort";
		}return name;
	}

}
