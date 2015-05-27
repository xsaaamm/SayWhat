package gui;
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
import java.awt.FlowLayout;
import java.awt.GridLayout;

public class AddUsers extends JFrame {

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
	private int childrole = 1, parentrole = 2;
	static AddUsers frame = new AddUsers();

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
	public AddUsers() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(50, 190, 340, 407);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\eclipse\\workspace\\Say What\\img\\simon icon2.png"));
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLayeredPane layeredPane = new JLayeredPane();
		contentPane.add(layeredPane);
		
		JPanel addChild = new JPanel();
		addChild.setBounds(0, 0, 324, 368);
		layeredPane.add(addChild);
		
		JPanel addParent = new JPanel();
		addParent.setBounds(0, 0, 324, 368);
		layeredPane.add(addParent);
		

	

}
}
