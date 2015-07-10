package practicemode;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import tarsos.AudioEvent;
import tarsos.PitchDetectionHandler;
import tarsos.PitchDetectionResult;
import be.tarsos.dsp.AudioDispatcher;
import freemode.TopPanel;

public class PracticePane extends JPanel implements PitchDetectionHandler{

	private static final long serialVersionUID = 2155541009755120223L;
	
	public static float pitch;
	public static float probability;
	public static double timeStamp;
	static AudioDispatcher dispatcher;
	//private TopPanel topPanel = new TopPanel();
	
	public PracticePane() {
		setBackground(Color.BLACK);
		setLayout(new GridLayout(0, 1));
		//add(topPanel);
	}

	@Override
	public void handlePitch(PitchDetectionResult pitchDetectionResult,
			AudioEvent audioEvent) {
		// TODO Auto-generated method stub
		
	}

}
