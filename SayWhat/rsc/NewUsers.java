package gui.rsc;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JLayeredPane;

public class NewUsers extends JFrame {

	private static final long serialVersionUID = -170870054479679267L;
	private JPanel contentPane;
	private JLabel lblSignUp, lblFName, lblChildPass, lblChildsInfo, lblChildsLName;
	private JLabel lblConfirmChildPass, lblTherapistId; 
	private JLabel lblPhoto;
	private JTextField textFieldCFname, textFieldCLName;
	private JTextField textFieldPhoto, textFieldTID;
	private JPasswordField passFieldChild, passFieldChildConfirm;
	private JButton btnNext, btnUploadPhoto;
	private String firstname, lastname, username, pass, passconfirm;
	int childrole = 1, parentrole = 2;
	static NewUsers frame = new NewUsers();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public NewUsers() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(50, 190, 340, 407);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\eclipse\\workspace\\Say What\\img\\simon icon2.png"));
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 1, 1);
		contentPane.add(layeredPane);
		
		lblSignUp = new JLabel("Sign Up!!");
		lblSignUp.setBounds(0, 0, 332, 43);
		layeredPane.add(lblSignUp);
		lblSignUp.setForeground(new Color(154, 205, 50));
		lblSignUp.setBackground(Color.BLACK);
		lblSignUp.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSignUp.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblFName = new JLabel("First Name: ");
		lblFName.setBounds(0, 0, 143, 14);
		layeredPane.add(lblFName);
		lblFName.setForeground(Color.WHITE);
		
		textFieldCFname = new JTextField();
		textFieldCFname.setBounds(0, 0, 296, 20);
		layeredPane.add(textFieldCFname);
		textFieldCFname.setColumns(10);
		
		lblChildsLName = new JLabel("Last Name: ");
		lblChildsLName.setBounds(0, 0, 143, 14);
		layeredPane.add(lblChildsLName);
		lblChildsLName.setForeground(Color.WHITE);
		//Textfield action listener
		textFieldCFname.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				firstname = textFieldCFname.getText();
			}
		});
		
		textFieldCLName = new JTextField();
		textFieldCLName.setColumns(10);
		textFieldCLName.setBounds(21, 135, 296, 20);
		contentPane.add(textFieldCLName);
		textFieldCLName.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				lastname = textFieldCLName.getText();
			}
		});

		lblChildPass = new JLabel("Password: ");
		lblChildPass.setForeground(Color.WHITE);
		lblChildPass.setBounds(21, 166, 143, 14);
		contentPane.add(lblChildPass);
		
		passFieldChild = new JPasswordField("1234");
		passFieldChild.setBounds(21, 180, 296, 20);
		contentPane.add(passFieldChild);
		
		lblConfirmChildPass = new JLabel("Confirm Password: ");
		lblConfirmChildPass.setForeground(Color.WHITE);
		lblConfirmChildPass.setBounds(21, 206, 143, 14);
		contentPane.add(lblConfirmChildPass);
		
		passFieldChildConfirm = new JPasswordField("1234");
		passFieldChildConfirm.setBounds(21, 221, 296, 20);
		contentPane.add(passFieldChildConfirm);
				
		lblTherapistId = new JLabel("Therapist ID:");
		lblTherapistId.setForeground(Color.WHITE);
		lblTherapistId.setBounds(21, 252, 143, 14);
		contentPane.add(lblTherapistId);
	
		textFieldTID = new JTextField();
		textFieldTID.setColumns(10);
		textFieldTID.setBounds(21, 266, 296, 20);
		contentPane.add(textFieldTID);
		
		lblChildsInfo = new JLabel("Child's Info");
		lblChildsInfo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblChildsInfo.setForeground(new Color(255, 255, 0));
		lblChildsInfo.setBounds(21, 59, 143, 14);
		contentPane.add(lblChildsInfo);
		
		btnNext = new JButton("Next");
		btnNext.setHorizontalAlignment(SwingConstants.CENTER);
		btnNext.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNext.setForeground(new Color(30, 144, 255));
		btnNext.setBounds(94, 342, 143, 23);
		contentPane.add(btnNext); 
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				try {
					frame.dispose();
					NewUsers2 newusers2 = new NewUsers2();
					newusers2.setVisible(true);
					//newusers2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					//String test = getUsername();
					//System.out.println(test);
					//add firstname, lastname, username, therapist, picture
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Could not go on");
				}
			}
		});
		
		lblPhoto = new JLabel("Photo:");
		lblPhoto.setForeground(Color.WHITE);
		lblPhoto.setBounds(21, 297, 143, 14);
		contentPane.add(lblPhoto);
		
		textFieldPhoto = new JTextField();
		textFieldPhoto.setColumns(10);
		textFieldPhoto.setBounds(21, 311, 189, 20);
		contentPane.add(textFieldPhoto);
		
		btnUploadPhoto = new JButton("Upload Photo");
		btnUploadPhoto.setBounds(220, 310, 97, 23);
		contentPane.add(btnUploadPhoto);
	}
	
public String getUsername(){
	String fName = firstname;
	String lName = lastname; 
	String fLetter = "";
	fLetter = fName.substring(0, 1);
	username = lName + fLetter;
	return (username);
}
public void  checkPass(){
	if(pass == passconfirm){
		//cpassword = uI_CPass;
	}else{
	JOptionPane.showMessageDialog(null, "Child Passwords do not match");
	}
}
}
