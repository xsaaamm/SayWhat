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


package main;

import java.awt.GridLayout;
import java.lang.reflect.InvocationTargetException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import be.tarsos.dsp.AudioDispatcher;
import be.tarsos.dsp.AudioEvent;
import be.tarsos.dsp.pitch.PitchDetectionHandler;
import be.tarsos.dsp.pitch.PitchDetectionResult;
import freemode.FreeTab;

public class PitchDetectorTool extends JFrame implements PitchDetectionHandler {

	private static final long serialVersionUID = 3501426880288136245L;

	@SuppressWarnings("unused")
	private Mixer currentMixer;
	public static AudioDispatcher dispatcher;
	public static float pitch;
	public static double timeStamp;
	
	public PitchDetectorTool() throws LineUnavailableException {
		this.setLayout(new GridLayout(0, 1));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Pitch Detector");
	}
	public static void main(String... strings) throws InterruptedException,
			InvocationTargetException {
		SwingUtilities.invokeAndWait(new Runnable() {
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (Exception e) {
					//ignore failure to set default look en feel;
				}
				JFrame frame = null;
				try {
					frame = new PitchDetectorTool();
				} catch (LineUnavailableException e) {
					e.printStackTrace();
				}
				frame.pack();
				frame.setVisible(true);
			}
		});
	}

	@Override
	public void handlePitch(PitchDetectionResult pitchDetectionResult,AudioEvent audioEvent) {
		if(pitchDetectionResult.getPitch() != -1){
			timeStamp = audioEvent.getTimeStamp();
			pitch = pitchDetectionResult.getPitch();
			
			String message = String.format("Pitch detected at %.2fs: %.2fHz ( %.2f probability, RMS: %.5f )\n", timeStamp,pitch);
			System.out.println(message);
			for (float i=0; i<=pitch; i+=5){
				FreeTab.textArea.append("|");
			}
				
			FreeTab.textArea.setCaretPosition(FreeTab.textArea.getDocument().getLength());	
		}
	}
}