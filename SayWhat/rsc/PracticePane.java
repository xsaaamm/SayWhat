package practicemode;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import freemode.StartPanel;
import freemode.TopPanel;
import be.tarsos.dsp.AudioDispatcher;
import be.tarsos.dsp.AudioEvent;
import be.tarsos.dsp.io.jvm.JVMAudioInputStream;
import be.tarsos.dsp.pitch.PitchDetectionHandler;
import be.tarsos.dsp.pitch.PitchDetectionResult;
import be.tarsos.dsp.pitch.PitchProcessor;

import java.awt.Color;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class PracticePane extends JPanel implements PitchDetectionHandler {

	private static final long serialVersionUID = -3838278573945995384L;
	
	//private Mixer currentMixer;
	//public static JTextArea textArea;
	public static float pitch;
	public static float probability;
	public static double timeStamp;
	static AudioDispatcher dispatcher;
	private TopPanel topPanel = new TopPanel();
	
	public PracticePane() {
		setBackground(Color.BLACK);
		setLayout(new GridLayout(0, 1));
		add(topPanel);
		
		topPanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		TopPanel.startPanel.addPropertyChangeListener("mixer", new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent arg0) {
				try {
					setNewMixer((Mixer) arg0.getNewValue());
				} catch (LineUnavailableException e) {
					e.printStackTrace();
				} catch (UnsupportedAudioFileException e) {
					e.printStackTrace();
				}
			}
		});

	}
	private void setNewMixer(Mixer mixer) throws LineUnavailableException, UnsupportedAudioFileException {
		if(dispatcher!= null){
			dispatcher.stop();
		}
		//currentMixer = mixer;
		
		float sampleRate = 44100;
		int bufferSize = 1024;
		int overlap = 0;

		final AudioFormat format = new AudioFormat(sampleRate, 16, 1, true,true);
		final DataLine.Info dataLineInfo = new DataLine.Info(TargetDataLine.class, format);
		TargetDataLine line;
		line = (TargetDataLine) mixer.getLine(dataLineInfo);
		final int numberOfSamples = bufferSize;
		line.open(format, numberOfSamples);
		line.start();
		final AudioInputStream stream = new AudioInputStream(line);

		JVMAudioInputStream audioStream = new JVMAudioInputStream(stream);
		// create a new dispatcher
		dispatcher = new AudioDispatcher(audioStream, bufferSize, overlap);
		// add a processor
		dispatcher.addAudioProcessor(new PitchProcessor(StartPanel.algo, sampleRate, bufferSize, this));
		
		new Thread(dispatcher,"Audio dispatching").start();			
}

	@Override
	public void handlePitch(PitchDetectionResult arg0, AudioEvent arg1) {
		// TODO Auto-generated method stub
		
	}

}
