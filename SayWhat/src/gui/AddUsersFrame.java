package gui;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JButton;
import java.awt.GridLayout;
/**import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
*/
public class AddUsersFrame extends JFrame {

	private static final long serialVersionUID = -170870054479679267L;
	private JPanel contentPane;
	/**private JLabel lblSignUp, lblFName, lblChildPass, lblChildsInfo, lblChildsLName;
	private JLabel lblConfirmChildPass, lblTherapistId; 
	private JLabel lblPhoto;
	private JTextField textFieldCFname, textFieldCLName;
	private JTextField textFieldPhoto, textFieldTID;
	private JPasswordField passFieldChild, passFieldChildConfirm;
	private JButton btnNext, btnUploadPhoto;
	private String firstname, lastname, username, pass, passconfirm;
	private int childrole = 1, parentrole = 2;*/
	static AddUsersFrame frame = new AddUsersFrame();

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
	public AddUsersFrame() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(50, 190, 340, 531);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\eclipse\\workspace\\Say What\\img\\simon icon2.png"));
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLayeredPane layeredPane = new JLayeredPane();
		contentPane.add(layeredPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(0, 0, 324, 492);
		layeredPane.add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Next");
		btnNewButton.setForeground(Color.BLUE);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(225, 458, 89, 23);
		panel.add(btnNewButton);
		
		JLabel lblEnterChildsInformation = new JLabel("Enter Child's Information");
		lblEnterChildsInformation.setForeground(Color.YELLOW);
		lblEnterChildsInformation.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEnterChildsInformation.setBounds(0, 0, 213, 29);
		panel.add(lblEnterChildsInformation);
		

	

}
}
