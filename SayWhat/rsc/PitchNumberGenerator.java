package gui;

import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import tarsos_pitch.AudioDispatcher;
import tarsos_pitch.PitchDetectionHandler;
import tarsos_pitch.PitchDetectionResult;
import be.tarsos.dsp.io.jvm.JVMAudioInputStream;
import be.tarsos.dsp.pitch.PitchProcessor.PitchEstimationAlgorithm;

public class PitchNumberGenerator extends JPanel implements PitchDetectionHandler {

	private static final long serialVersionUID = -5107785666165487335L;
	
	@SuppressWarnings("unused")
	private static Mixer currentMixer;
	public static AudioDispatcher dispatcher;
	public static float pitch;
	public static double timeStamp;
	private static PitchEstimationAlgorithm algo = PitchEstimationAlgorithm.MPM;
	static JTextArea textArea = new JTextArea();
	static PitchNumberGenerator PitchNumberPanel = new PitchNumberGenerator();
	
	public PitchNumberGenerator(){
		initialize();
    }
	
	private void initialize(){
		setVisible(true);
		textArea.setEditable(false);
		setLayout(new GridLayout(0, 1));
		
		
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
		
		textArea.append("You may now begin.  Started listening with " + Shared.toLocalString(mixer.getMixerInfo().getName()) + "\n");

		final AudioFormat format = new AudioFormat(sampleRate, 16, 1, true,true);
		final DataLine.Info dataLineInfo = new DataLine.Info(TargetDataLine.class, format);
		TargetDataLine line;
		line = (TargetDataLine) mixer.getLine(dataLineInfo);
		line = AudioSystem.getTargetDataLine(format);
		
		final int numberOfSamples = bufferSize;
		line.open(format, numberOfSamples);
		line.start();
		final AudioInputStream stream = new AudioInputStream(line);

		JVMAudioInputStream audioStream = new JVMAudioInputStream(stream);
		// create a new dispatcher
		//dispatcher = new AudioDispatcher(audioStream, bufferSize, overlap);

		// add a processor
		//dispatcher.addAudioProcessor(new PitchProcessor(algo, sampleRate, bufferSize, this));
		
		new Thread(dispatcher,"Audio dispatching").start();
	}

	@Override
	public void handlePitch(PitchDetectionResult pitchDetectionResult,tarsos_pitch.AudioEvent audioEvent) {
		if(pitchDetectionResult.getPitch() != -1){
			timeStamp = audioEvent.getTimeStamp();
			pitch = pitchDetectionResult.getPitch();
			float probability = pitchDetectionResult.getProbability();
			double rms = audioEvent.getRMS() * 100;	
				for (float i=0; i<=pitch; i+=5){
					textArea.append("|");
				}
			String message = String.format("Pitch detected at %.2fs: %.2fHz ( %.2f probability, RMS: %.5f )\n", timeStamp,pitch,probability,rms);
			textArea.append(message);			
			textArea.setCaretPosition(textArea.getDocument().getLength());
		}
	}
}





































































































