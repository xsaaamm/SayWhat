package gui;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JTabbedPane;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Component;


public class Main extends JFrame {
	
	private static final long serialVersionUID = 7837880633918248807L;
	static Main mainframe = new Main();
	private JPanel mainPane= new JPanel();
	HomePane homepane = new HomePane();
	FreePane freepane = new FreePane();
	PracticePane practicepane = new PracticePane();
	ChallengePane challengepane = new ChallengePane();
	SettingsPane settingspane = new SettingsPane();
	RecordsPane recordspane = new RecordsPane();
	HelpPane helppane = new HelpPane();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainframe.setVisible(true);
					mainframe.setExtendedState(MAXIMIZED_BOTH);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		getContentPane().setBackground(new Color(0, 0, 0));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 959, 660);
		setResizable(false);
		setUndecorated(true);
		//setContentPane(mainPane);
		getContentPane().add(mainPane, BorderLayout.CENTER);
		mainPane.setBackground(Color.BLACK);
		mainPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		mainPane.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		mainPane.setLayout(new BorderLayout(0, 0));

		
		JPanel topPanel = new JPanel();
		topPanel.setBackground(Color.BLACK);
		mainPane.add(topPanel, BorderLayout.NORTH);
		topPanel.setLayout(new GridLayout(0, 18, 0, 0));
		
		JPanel panel_9 = new JPanel();
		panel_9.setBackground(Color.BLACK);
		topPanel.add(panel_9);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		topPanel.add(panel);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(Color.BLACK);
		topPanel.add(panel_6);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.BLACK);
		topPanel.add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.BLACK);
		topPanel.add(panel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.BLACK);
		topPanel.add(panel_3);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.BLACK);
		topPanel.add(panel_4);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.BLACK);
		topPanel.add(panel_5);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(Color.BLACK);
		topPanel.add(panel_7);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBackground(Color.BLACK);
		topPanel.add(panel_8);
		
		JPanel panel_10 = new JPanel();
		panel_10.setBackground(Color.BLACK);
		topPanel.add(panel_10);
		
		JPanel panel_11 = new JPanel();
		panel_11.setBackground(Color.BLACK);
		topPanel.add(panel_11);
		
		JPanel panel_12 = new JPanel();
		panel_12.setBackground(new Color(0, 0, 0));
		topPanel.add(panel_12);
		
		JPanel panel_13 = new JPanel();
		panel_13.setBackground(new Color(0, 0, 0));
		topPanel.add(panel_13);
		
		JPanel panel_14 = new JPanel();
		panel_14.setBackground(new Color(0, 0, 0));
		topPanel.add(panel_14);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBackground(new Color(255, 0, 0));
		topPanel.add(btnSave);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setBackground(new Color(30, 144, 255));
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//------------------------------------------------->>>Save
				try {
					mainframe.dispose();
					Login login = new Login();
					login.setVisible(true);
					login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					login.frmLoginPage.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Could not add Parent, please try again");
				}
			}
		});
		topPanel.add(btnLogout);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.setBackground(new Color(154, 205, 50));
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//------------------------------------------------->>>Save
				System.exit(0);
			}
		});
		topPanel.add(btnQuit);
		
		JTabbedPane tabbedMain = new JTabbedPane(JTabbedPane.TOP);
		tabbedMain.setBackground(new Color(255, 255, 255));
		mainPane.add(tabbedMain, BorderLayout.CENTER);
	
		tabbedMain.addTab("Home", null, homepane, null);
		tabbedMain.setForegroundAt(0, new Color(0, 0, 0));
		tabbedMain.setBackgroundAt(0, new Color(255, 255, 255));
		
		tabbedMain.addTab("Free Mode", null, freepane, null);
		tabbedMain.setBackgroundAt(1, new Color(255, 255, 255));
		
		tabbedMain.addTab("Practice Mode", null, practicepane, null);
		tabbedMain.setBackgroundAt(2, new Color(255, 255, 255));
		
		tabbedMain.addTab("Challenge Mode", null, challengepane, null);
		tabbedMain.setBackgroundAt(3, new Color(255, 255, 255));
		
		tabbedMain.addTab("Records", null, recordspane, null);
		tabbedMain.setBackgroundAt(4, new Color(255, 255, 255));
		
		tabbedMain.addTab("Settings", null, settingspane, null);
		tabbedMain.setBackgroundAt(5, new Color(255, 255, 255));
		
		tabbedMain.addTab("Help", null, helppane, null);
		tabbedMain.setBackgroundAt(6, Color.WHITE);

	}
}
