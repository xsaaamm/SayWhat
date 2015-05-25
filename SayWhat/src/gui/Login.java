package gui;
import java.sql.*;

import javax.swing.*;

import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Login extends JFrame{

	private static final long serialVersionUID = 3317897313713510859L;
	JFrame frmLoginPage;
	private JPasswordField passwordField;
	private JLabel lblSayWhatLogin;
	private JLabel lblUsername;	
	private JTextField usernameField;
	private JPanel panel;
	private JLabel lblPassword;
	private JButton btnNewUser;
	private JButton btnLogin;
	private JButton btnUserPic1;
	private JButton btnUserPic2;
	private JButton btnUserPic3;
	private JLabel lbSoundBar;
	private Connection connection = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frmLoginPage.setVisible(true);
					} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize(); 
		try {
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/saywhatdb", "root", "");
				//JOptionPane.showMessageDialog(null, "Database Connection Successful");
			}catch (SQLException e) {
				System.out.println("ERROR: Could not connect to the Database");
				e.printStackTrace();
			}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		//Frame Layout
		frmLoginPage = new JFrame();
		frmLoginPage.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\eclipse\\workspace\\Say What\\img\\simon icon2.png"));
		frmLoginPage.setTitle("Login Page");
		frmLoginPage.setResizable(false);
		frmLoginPage.getContentPane().setBackground(Color.BLACK);
		frmLoginPage.setBounds(100, 100, 713, 610);
		frmLoginPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLoginPage.getContentPane().setLayout(null);
		
		//--Say What Title
		lblSayWhatLogin = new JLabel("Say What!? Login");
		lblSayWhatLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblSayWhatLogin.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSayWhatLogin.setForeground(Color.YELLOW);
		lblSayWhatLogin.setBounds(0, 0, 707, 41);
		frmLoginPage.getContentPane().add(lblSayWhatLogin);
		
		//--Username Label
		lblUsername = new JLabel("Username");
		lblUsername.setForeground(new Color(154, 205, 50));
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUsername.setBounds(206, 210, 69, 19);
		frmLoginPage.getContentPane().add(lblUsername);
		
		//--Username Field
		usernameField = new JTextField();
		usernameField.setBounds(285, 211, 221, 20);
		frmLoginPage.getContentPane().add(usernameField);
		usernameField.setColumns(10);
		
		//--Password Label
		lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPassword.setForeground(new Color(154, 205, 50));
		lblPassword.setBackground(Color.LIGHT_GRAY);
		lblPassword.setBounds(206, 243, 90, 19);
		frmLoginPage.getContentPane().add(lblPassword);
		
		//--Password Field
		passwordField = new JPasswordField("1234");
		passwordField.setEchoChar('*');
		passwordField.setBounds(285, 242, 221, 20);
		frmLoginPage.getContentPane().add(passwordField);
		
		//Sign Up Button
		btnNewUser = new JButton("New User");
		btnNewUser.setForeground(Color.BLUE);
		btnNewUser.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewUser.setBounds(417, 299, 89, 23);
		frmLoginPage.getContentPane().add(btnNewUser);
			
		//Sign Up Button Clicked
		btnNewUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					NewUsers newusers = new NewUsers();
					newusers.setVisible(true);
				}catch (Exception e3){
					JOptionPane.showMessageDialog(null, "Could not connect to server, try again");
				}
			}
		});
		
		//Login Button
		btnLogin = new JButton("Login");
		btnLogin.setForeground(Color.BLUE);
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnLogin.setBounds(417, 273, 89, 23);
		frmLoginPage.getContentPane().add(btnLogin);
	
		//Login Button Clicked
		btnLogin.addActionListener(new ActionListener() {
			@SuppressWarnings({ "deprecation" })
			public void actionPerformed(ActionEvent arg0) {
				
				try{
					String query = "SELECT * from usernames WHERE username=? and password=?";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, usernameField.getText());
					pst.setString(2, passwordField.getText());
					
					ResultSet result = pst.executeQuery();
					int count = 0;
					while (result.next()){
						count++;
					}if (count == 1){
						//*JOptionPane.showMessageDialog(null, "Username and Password is correct");
						frmLoginPage.dispose();
						Main main = new Main();
						main.setVisible(true);
						main.setExtendedState(MAXIMIZED_BOTH);
						main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						//----------------------------------------------------------------->> Log User in
					}else{
						JOptionPane.showMessageDialog(null, "Username and/or password is not correct, try again");
					}
					
					result.close();
					pst.close();
					
				}catch(Exception e2){
					//System.out.println("ERROR: Could not connect");
				//	JOptionPane.showMessageDialog(null, e2);
				}//------------------------------------------------------->>>>>>> Close Connection??
				
			}
		});

		//--Sound Bar Image 	
		lbSoundBar = new JLabel("");
		lbSoundBar.setHorizontalAlignment(SwingConstants.CENTER);
		lbSoundBar.setIcon(new ImageIcon("C:\\eclipse\\workspace\\Say What\\img\\lblSoundBar.png"));
		lbSoundBar.setBounds(0, 389, 707, 192);
		frmLoginPage.getContentPane().add(lbSoundBar);
		
		panel = new JPanel();
		panel.setForeground(Color.WHITE);
		panel.setBackground(Color.BLACK);
		panel.setBorder(null);
		panel.setBounds(194, 52, 325, 128);
		frmLoginPage.getContentPane().add(panel);
		panel.setLayout(null);
		panel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		//--User Photo Button_1
		btnUserPic1 = new JButton("");
		btnUserPic1.setBounds(6, 16, 105, 105);
		panel.add(btnUserPic1);
		btnUserPic1.setIcon(new ImageIcon("img/sam.jpg"));
		btnUserPic1.setHorizontalAlignment(SwingConstants.CENTER);
		
		//--User Photo Button_2
		btnUserPic2 = new JButton("");
		btnUserPic2.setBounds(110, 16, 105, 105);
		panel.add(btnUserPic2);
		btnUserPic2.setIcon(new ImageIcon("img/bobby.jpg"));
		btnUserPic2.setHorizontalAlignment(SwingConstants.CENTER);
		
		//--User Photo Button_3
		btnUserPic3 = new JButton("");
		btnUserPic3.setBounds(214, 16, 105, 105);
		panel.add(btnUserPic3);
		btnUserPic3.setIcon(new ImageIcon("img/toure.jpg"));
		btnUserPic3.setHorizontalAlignment(SwingConstants.CENTER);
		//--User button clicked
		btnUserPic3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/saywhatdb", "root", "");
					Statement stmt = connection.createStatement();
					ResultSet result = stmt.executeQuery("SELECT * from usernames WHERE fk_users_id=3");
					while (result.next()){
						usernameField.setText(result.getString("username"));
					}
					
			}catch (SQLException e2){
				JOptionPane.showMessageDialog(null, "User not found, please enter manually");
				}
			}
		});
		//--User button clicked
		btnUserPic2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/saywhatdb", "root", "");
					Statement stmt = connection.createStatement();
					ResultSet result = stmt.executeQuery("SELECT * from usernames WHERE fk_users_id=1");
					while (result.next()){
						usernameField.setText(result.getString("username"));
					}
					
			}catch (SQLException e2){
				JOptionPane.showMessageDialog(null, "User not found, please enter manually");
				}
			}
		});
		//--User button1 clicked
		btnUserPic1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/saywhatdb", "root", "");
					Statement stmt = connection.createStatement();
					ResultSet result = stmt.executeQuery("SELECT * from usernames WHERE fk_users_id=2");
					while (result.next()){
						usernameField.setText(result.getString("username"));
					}
					
				}catch (SQLException e2){
					JOptionPane.showMessageDialog(null, "User not found, please enter manually");
				}
			}
		});

	}
}



