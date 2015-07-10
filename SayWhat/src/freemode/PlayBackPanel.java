package freemode;

import java.awt.GridLayout;
//import java.awt.event.ActionListener;
//import javax.swing.ButtonGroup;
import javax.swing.JPanel;
//import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
//import be.tarsos.dsp.pitch.PitchProcessor.PitchEstimationAlgorithm;

public class PlayBackPanel extends JPanel {


	private static final long serialVersionUID = -1869535166207654639L;
	private JTextField tFieldFileName;
	private JTextField textFieldFileDate;
	private JTextField tFFileName;
	private JTextField tFFileLength;
	
	public PlayBackPanel(){
		setBorder(new TitledBorder("Play Options"));
		setLayout(new GridLayout(3, 0, 0, 0));
		
		//JPanel fileNamePanel = new JPanel();
		//fileNamePanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel audioButtonsPanel = new JPanel();
		audioButtonsPanel.setLayout(new GridLayout(2, 0, 0, 0));
		
		JButton btnPlay = new JButton("Play");
		JButton btnPause = new JButton("Pause");	
		JButton btnStop = new JButton("Stop");		
		JButton btnRecord = new JButton("Record");
		
		JPanel uploadPanel = new JPanel();
		tFieldFileName = new JTextField();
		tFieldFileName.setHorizontalAlignment(SwingConstants.LEFT);
		tFieldFileName.setEditable(true);
		tFieldFileName.setColumns(30);
		JButton btnBrowse = new JButton("Browse");
		setLayout(new GridLayout(0, 1, 0, 0));
		JLabel uploadLabel = new JLabel("Upload New File");
		GroupLayout gl_uploadPanel = new GroupLayout(uploadPanel);
		gl_uploadPanel.setHorizontalGroup(
			gl_uploadPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_uploadPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_uploadPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_uploadPanel.createSequentialGroup()
							.addComponent(tFieldFileName, GroupLayout.PREFERRED_SIZE, 338, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnBrowse))
						.addComponent(uploadLabel))
					.addContainerGap(17, Short.MAX_VALUE))
		);
		gl_uploadPanel.setVerticalGroup(
			gl_uploadPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_uploadPanel.createSequentialGroup()
					.addGap(17)
					.addComponent(uploadLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_uploadPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(tFieldFileName, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBrowse, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(25, Short.MAX_VALUE))
		);
		uploadPanel.setLayout(gl_uploadPanel);
		setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel songInfoPanel = new JPanel();
		add(songInfoPanel);
		songInfoPanel.setLayout(new BoxLayout(songInfoPanel, BoxLayout.X_AXIS));
		songInfoPanel.setLayout(new GridLayout(3, 0, 0, 0));
		
		tFFileName = new JTextField("File name: AudioFile1");
		songInfoPanel.add(tFFileName);
		tFFileName.setColumns(10);
		tFFileName.setEditable(false);
		
		tFFileLength = new JTextField("File Length: 3:23");
		songInfoPanel.add(tFFileLength);
		tFFileLength.setColumns(10);
		tFFileLength.setEditable(false);
		
		textFieldFileDate = new JTextField(" Date Recorded: Null");
		textFieldFileDate.setEditable(false);
		songInfoPanel.add(textFieldFileDate);
		textFieldFileDate.setColumns(10);
		
		audioButtonsPanel.setLayout(new GridLayout(0, 4, 0, 0));
		audioButtonsPanel.add(btnPlay);
		audioButtonsPanel.add(btnPause);
		audioButtonsPanel.add(btnStop);
		audioButtonsPanel.add(btnRecord);
		add(audioButtonsPanel);
		add(uploadPanel);
		

	}
}
