package gui;
import java.awt.Color;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import be.tarsos.dsp.AudioDispatcher;
import be.tarsos.dsp.AudioEvent;
import be.tarsos.dsp.io.jvm.JVMAudioInputStream;
import be.tarsos.dsp.pitch.PitchDetectionHandler;
import be.tarsos.dsp.pitch.PitchDetectionResult;
import be.tarsos.dsp.pitch.PitchProcessor;
import be.tarsos.dsp.pitch.PitchProcessor.PitchEstimationAlgorithm;

public class FreePane extends JPanel implements PitchDetectionHandler {

	private static final long serialVersionUID = 5336983837352763066L;

	public static float pitch;
	public static float probability;
	//public static double rms;
	public static double timeStamp;
	TopPaneFree topPanel = new TopPaneFree();
	//final int SLEEP_MEAN = 100;
	//final int SLEEP_SCATTER = 100;
	static JTextArea textArea = new JTextArea();
	static AudioDispatcher dispatcher;
	private Mixer currentMixer;
	private PitchEstimationAlgorithm algo = PitchEstimationAlgorithm.MPM;
	static JPanel pitchGraphPanel = new GraphPanelFree();
	private long startMillis;
	
	public FreePane() {
		intialize();
	}
	
	private void intialize(){
		setBackground(Color.LIGHT_GRAY);
		this.setLayout(new GridLayout(0, 1));
		add(topPanel);
	
		//second panel- numbers
		textArea.setEditable(false);
		JScrollPane pitchNumPanel = new JScrollPane(textArea);
		add(pitchNumPanel);
		
		//bottom panel- graph
		add(pitchGraphPanel);
				
		TopPaneFree.inputPanel.addPropertyChangeListener("mixer",new PropertyChangeListener(){
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
		currentMixer = mixer;
		
		float sampleRate = 44100;
		int bufferSize = 1024;
		int overlap = 0;
		
		//textArea.append("Started listening with " + Shared.toLocalString(mixer.getMixerInfo().getName()) + " ");
		textArea.append("You may now begin.  Started listening with " + Shared.toLocalString(mixer.getMixerInfo().getName()) + "\n");

		final AudioFormat format = new AudioFormat(sampleRate, 16, 1, true,true);
		//final DataLine.Info dataLineInfo = new DataLine.Info(TargetDataLine.class, format);
		TargetDataLine line;
		//line = (TargetDataLine) mixer.getLine(dataLineInfo);
		line = AudioSystem.getTargetDataLine(format);
		
		final int numberOfSamples = bufferSize;
		line.open(format, numberOfSamples);
		line.start();
		final AudioInputStream stream = new AudioInputStream(line);

		JVMAudioInputStream audioStream = new JVMAudioInputStream(stream);
		// create a new dispatcher
		dispatcher = new AudioDispatcher(audioStream, bufferSize, overlap);

		// add a processor
		dispatcher.addAudioProcessor(new PitchProcessor(algo, sampleRate, bufferSize, this));
		
		new Thread(dispatcher,"Audio dispatching").start();
	}

	@Override
	public void handlePitch(PitchDetectionResult pitchDetectionResult,AudioEvent audioEvent) {
		if(pitchDetectionResult.getPitch() != -1){
			if (startMillis == -1){
				startMillis = System.currentTimeMillis();
			}
			timeStamp = audioEvent.getTimeStamp();
			pitch = pitchDetectionResult.getPitch();
		
			//test will be deleted 
			String message = String.format("Pitch detected at %.2fs: %.2fHz ( %.2f probability, RMS: %.5f )\n", timeStamp,pitch,probability);
			textArea.append(message);
			textArea.setCaretPosition(textArea.getDocument().getLength());
		}
	}
}
		

	

