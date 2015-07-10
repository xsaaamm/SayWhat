/*
*      _______                       _____   _____ _____  
*     |__   __|                     |  __ \ / ____|  __ \ 
*        | | __ _ _ __ ___  ___  ___| |  | | (___ | |__) |
*        | |/ _` | '__/ __|/ _ \/ __| |  | |\___ \|  ___/ 
*        | | (_| | |  \__ \ (_) \__ \ |__| |____) | |     
*        |_|\__,_|_|  |___/\___/|___/_____/|_____/|_|     
*                                                         
* -------------------------------------------------------------
*
* TarsosDSP is developed by Joren Six at IPEM, University Ghent
*  
* -------------------------------------------------------------
*
*  Info: http://0110.be/tag/TarsosDSP
*  Github: https://github.com/JorenSix/TarsosDSP
*  Releases: http://0110.be/releases/TarsosDSP/
*  
*  TarsosDSP includes modified source code by various authors,
*  for credits and info, see README.
* 
*/


package freemode;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Mixer;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import tarsos.Shared;
import be.tarsos.dsp.pitch.PitchProcessor.PitchEstimationAlgorithm;

public class StartPanel extends JPanel {

	private static final long serialVersionUID = 1L;		
	private JPanel buttonPanel = new JPanel(new GridLayout(2,2));
	private JButton startButton = new JButton();
	public static PitchEstimationAlgorithm algo = PitchEstimationAlgorithm.MPM;
	
	
	Mixer mixer = null;
	
	public StartPanel(){
		super(new BorderLayout());
		this.setBorder(new TitledBorder("Click start to begin"));
		add(buttonPanel);
		
		buttonPanel.add(startButton);
				
		startButton.setText("Start");
		startButton.addActionListener(startMic);
	}
	
	private ActionListener startMic = new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent arg0) {
			Mixer.Info info = Shared.getMixerInfo(false, true).get(1);
			mixer = AudioSystem.getMixer(info);
			StartPanel.this.firePropertyChange("mixer", null, mixer);
			
			
		}

	};
}
