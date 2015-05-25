package gui;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class NewUsers2 extends JFrame {
	
	private static final long serialVersionUID = 8328621135688487818L;
	private JLabel lblSignUp;
	private JPanel contentPane;
	private JTextField tFfirstname;
	private JTextField tFlastname;
	private JPasswordField passField;
	private JPasswordField passConfirm;
	private JTextField tFemail;
	private JTextField tFphone;
	private JButton btnSubmit;
	static NewUsers2 newUsers2 = new NewUsers2(); 
	private String firstname, lastname, username, pass, passconfirm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					newUsers2.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public NewUsers2() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(50, 190, 340, 407);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\eclipse\\workspace\\Say What\\img\\simon icon2.png"));
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblSignUp = new JLabel("Sign Up!!");
		lblSignUp.setBounds(0, 0, 332, 43);
		lblSignUp.setForeground(new Color(154, 205, 50));
		lblSignUp.setBackground(Color.BLACK);
		lblSignUp.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSignUp.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblSignUp);
		
		JLabel label = new JLabel("Parent's Info");
		label.setForeground(Color.YELLOW);
		label.setFont(new Font("Tahoma", Font.BOLD, 12));
		label.setBounds(22, 62, 302, 14);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("First Name: ");
		label_1.setForeground(Color.WHITE);
		label_1.setBounds(22, 81, 302, 14);
		contentPane.add(label_1);
		
		tFfirstname = new JTextField();
		tFfirstname.setColumns(10);
		tFfirstname.setBounds(22, 94, 291, 20);
		contentPane.add(tFfirstname);
		tFfirstname.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				firstname = tFfirstname.getText();
			}
		});
		
		JLabel label_2 = new JLabel("Last Name: ");
		label_2.setForeground(Color.WHITE);
		label_2.setBounds(22, 125, 302, 14);
		contentPane.add(label_2);
		
		tFlastname = new JTextField();
		tFlastname.setColumns(10);
		tFlastname.setBounds(22, 137, 291, 20);
		tFlastname.add(tFlastname);
		tFlastname.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				lastname = tFlastname.getText();
			}
		});
		
		passField = new JPasswordField("1234");
		passField.setBounds(22, 182, 291, 20);
		contentPane.add(passField);
		
		JLabel label_3 = new JLabel("Password: ");
		label_3.setForeground(Color.WHITE);
		label_3.setBounds(22, 168, 302, 14);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("Confirm Password: ");
		label_4.setForeground(Color.WHITE);
		label_4.setBounds(22, 208, 302, 14);
		contentPane.add(label_4);
		
		passConfirm = new JPasswordField("1234");
		passConfirm.setBounds(22, 223, 291, 20);
		contentPane.add(passConfirm);
		
		JLabel label_5 = new JLabel("Email:");
		label_5.setForeground(Color.WHITE);
		label_5.setBounds(22, 254, 302, 14);
		contentPane.add(label_5);
		
		tFemail = new JTextField();
		tFemail.setColumns(10);
		tFemail.setBounds(22, 268, 291, 20);
		contentPane.add(tFemail);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setHorizontalAlignment(SwingConstants.CENTER);
		btnSubmit.setForeground(new Color(30, 144, 255));
		btnSubmit.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSubmit.setBounds(92, 344, 143, 23);
		contentPane.add(btnSubmit);
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				try {
					//add to db
					newUsers2.dispose();
					Login login = new Login();
					login.setVisible(true);
					login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					login.frmLoginPage.setVisible(true);
					JOptionPane.showMessageDialog(null, "User added, your username is:" + username);
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Could not add Parent, please try again");
				}
				
				//add firstname, lastname, username, therapist, picture
			}
		});
		//ResultSet usernamesC_result = stmt.executeQuery("INSERT INTO usernames (username, password) VALUES ('childusername', 'cpassword')");
		//ResultSet usersP_result = stmt.executeQuery("INSERT INTO users (fname, lname, fk_roles_id, email) VALUES ('uI_PFname', 'uI_PLname', 'parentrole', 'uI_Email')");
		//ResultSet usernamesP_result = stmt.executeQuery("INSERT INTO usernames (username, password) VALUES ('parentusername', 'ppassword')");
		
		tFphone = new JTextField();
		tFphone.setColumns(10);
		tFphone.setBounds(22, 313, 291, 20);
		contentPane.add(tFphone);
		
		JLabel lblPhone = new JLabel("Phone:");
		lblPhone.setForeground(Color.WHITE);
		lblPhone.setBounds(22, 299, 302, 14);
		contentPane.add(lblPhone);
	}
	public String getUsername(){
		String fName = firstname;
		String lName = lastname; 
		String fLetter = "";
		fLetter = fName.substring(0, 1);
		username = lName + fLetter;
		return (username);
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getPassconfirm() {
		return passconfirm;
	}

	public void setPassconfirm(String passconfirm) {
		this.passconfirm = passconfirm;
	}
}
