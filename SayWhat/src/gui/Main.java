package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;
import javax.swing.UIManager;

public class Main extends JFrame {
	
	private static final long serialVersionUID = 7837880633918248807L;
	
	static Main mainFrame = new Main();
	private JPanel mainPane = new JPanel();
	private HomePane homepane = new HomePane();
	private FreePane freepane = new FreePane();
	private PracticePane practicepane = new PracticePane();
	private ChallengePane challengepane = new ChallengePane();
	private SettingsPane settingspane = new SettingsPane();
	private RecordsPane recordspane = new RecordsPane();
	private HelpPane helppane = new HelpPane();
	private JPanel topButtonPanel = new JPanel();
	private JButton btnSave = new JButton("Save");
	private JButton btnQuit = new JButton("Quit");
	private JButton btnLogout = new JButton("Logout");
	private JTabbedPane tabbedMain = new JTabbedPane(JTabbedPane.TOP);
	
	public static void main(String[] args){
		EventQueue.invokeLater(new Runnable(){
			@Override
			public void run(){
				try{
					mainFrame.setVisible(true);
					mainFrame.setExtendedState(MAXIMIZED_BOTH);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
	}

	public Main(){
		initialize();
	}
	
	private void initialize(){
	    // ESC key closes mainframe
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "Cancel");  //$NON-NLS-1$
        getRootPane().getActionMap().put("Cancel", new AbstractAction(){ //$NON-NLS-1$
			private static final long serialVersionUID = -1327730232303703872L;
			@Override
			public void actionPerformed(ActionEvent e){
				mainFrame.dispose();
            }
        });
        
        //Set mainFrame Defaults
		getContentPane().setBackground(new Color(0, 0, 0));
		setUndecorated(true);
		getContentPane().add(mainPane, BorderLayout.CENTER);
		
		 //Set mainPane defaults
		mainPane.setBackground(UIManager.getColor("InternalFrame.activeTitleBackground"));
		mainPane.setLayout(new BorderLayout(0, 0));
		
		//Add tabbed panel to main panel	
		mainPane.add(tabbedMain, BorderLayout.CENTER);
			//add tabs to tabbed pabel
			tabbedMain.addTab("Home", null, homepane, null);
			tabbedMain.addTab("Free Mode", null, freepane, null);
			tabbedMain.addTab("Practice Mode", null, practicepane, null);
			tabbedMain.addTab("Challenge Mode", null, challengepane, null);
			tabbedMain.addTab("Records", null, recordspane, null);
			tabbedMain.addTab("Settings", null, settingspane, null);
			tabbedMain.addTab("Help", null, helppane, null);
			
			//set tab backgrounds
			tabbedMain.setBackground(Color.LIGHT_GRAY);	
			tabbedMain.setBackgroundAt(0, SystemColor.activeCaption);
			tabbedMain.setBackgroundAt(1, SystemColor.activeCaption);
			tabbedMain.setBackgroundAt(2, SystemColor.activeCaption);
			tabbedMain.setBackgroundAt(3, SystemColor.activeCaption);
			tabbedMain.setBackgroundAt(4, SystemColor.activeCaption);	
			tabbedMain.setBackgroundAt(5, SystemColor.activeCaption);
			tabbedMain.setBackgroundAt(6, SystemColor.activeCaption);
			
			//Set tab window backgrounds
			homepane.setBackground(SystemColor.window);
			freepane.setBackground(SystemColor.window);
			practicepane.setBackground(SystemColor.window);
			challengepane.setBackground(SystemColor.window);
			recordspane.setBackground(SystemColor.window);	
			settingspane.setBackground(SystemColor.window);		
			helppane.setBackground(SystemColor.window);

		//Add Button Panel to main panel
		mainPane.add(topButtonPanel, BorderLayout.NORTH);
			topButtonPanel.setBackground(UIManager.getColor("InternalFrame.activeTitleBackground"));
			topButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
			//add buttons
			topButtonPanel.add(btnSave);
			topButtonPanel.add(btnLogout);
			topButtonPanel.add(btnQuit);		
				
			//Save Button Clicked
			btnSave.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						//Save
						/**
						 * Must create code to save data
						 */
						JOptionPane.showMessageDialog(null, "This session has been saved, keep up the good work");
					} catch (HeadlessException e1) {
						JOptionPane.showMessageDialog(null, "Could not save, try again");
						e1.printStackTrace();
					}
				}
			});
		
			//Logout Button Clicked- also saves
			btnLogout.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					try {
						//Save
						/**
						 * Must create code to save data
						 */
						mainFrame.dispose();
						Login login = new Login();
						login.frmLoginPage.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("Could not add Parent, please try again");
					}
				}
			});
		
			//Quit Button Clicked - also saves
			btnQuit.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					//Save
					/**
					 * Must create code to save data
					 */
					mainFrame.dispose();
				}
			});
	}
	
}
