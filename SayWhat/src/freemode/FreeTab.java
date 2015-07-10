package freemode;
import java.awt.Color;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.InvocationTargetException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import be.tarsos.dsp.AudioDispatcher;
import be.tarsos.dsp.AudioEvent;
import be.tarsos.dsp.io.jvm.JVMAudioInputStream;
import be.tarsos.dsp.pitch.PitchDetectionHandler;
import be.tarsos.dsp.pitch.PitchDetectionResult;
import be.tarsos.dsp.pitch.PitchProcessor;

public class FreeTab extends JPanel implements PitchDetectionHandler {

	private static final long serialVersionUID = 5336983837352763066L;
	
	@SuppressWarnings("unused")
	private Mixer currentMixer;
	public static JTextArea textArea;
	public static float pitch;
	public static float probability;
	public static double timeStamp;
	static AudioDispatcher dispatcher;
	private TopPanel topPanel = new TopPanel();
	private GraphPanel GraphPanel = new GraphPanel();
	public static ImageIcon black; 
	public static ImageIcon blue;
	public static ImageIcon green;
	public static ImageIcon yellow;
	public static ImageIcon orange;
	public static ImageIcon red;
	private JScrollPane scroll = new JScrollPane(GraphPanel);
	private JLabel label;
		
	public FreeTab() {
		setBackground(Color.LIGHT_GRAY);
		setLayout(new GridLayout(0, 1));
		add(topPanel);
		
		topPanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		TopPanel.startPanel.addPropertyChangeListener("mixer",
			new PropertyChangeListener() {
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

/**		
		textArea = new JTextArea();
		textArea.setEditable(false);
		add(new JScrollPane(textArea));
*/		
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		add(scroll);
		scroll.isWheelScrollingEnabled();
		
	}
	
	private void setNewMixer(Mixer mixer) throws LineUnavailableException, UnsupportedAudioFileException {
			if(dispatcher!= null){
				dispatcher.stop();
			}
			currentMixer = mixer;
			
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

	public static void main(String... strings) throws InterruptedException, InvocationTargetException {
		SwingUtilities.invokeAndWait(new Runnable() {
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (Exception e) {
				}
				JFrame frame = null;
				frame = new JFrame();
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
/**
			//test will be deleted 
			String message = String.format("Pitch detected at %.2fs: %.2fHz ( %.2f probability )", timeStamp,pitch,probability);
			textArea.append(message);
			for (float i=0; i<=pitch; i+=5){
				textArea.append("|");
			}
			textArea.append("\n");
			textArea.setCaretPosition(textArea.getDocument().getLength());
*/
			if ( pitch == 0){
				black = new ImageIcon("img/bar00.png");
				label = new JLabel();
					label.setIcon(black);
					label.setVisible(true);
				GraphPanel.add(label);
			}if ( pitch > 0 && pitch <= 25 ){
				blue = new ImageIcon("img/bar01.png");
				label = new JLabel();
					label.setIcon(blue);
					label.setVisible(true);
				GraphPanel.add(label);
			}if (pitch > 25 && pitch <= 50){
				green = new ImageIcon("img/bar02.png");
				label = new JLabel();
					label.setIcon(green);
					label.setVisible(true);
				GraphPanel.add(label);
			}if (pitch > 50 && pitch <= 75){
				yellow = new ImageIcon("img/bar03.png");
				label = new JLabel();
					label.setIcon(yellow);
					label.setVisible(true);
				GraphPanel.add(label);
			}if (pitch > 75 && pitch <= 100){
				orange = new ImageIcon("img/bar04.png");
				label = new JLabel();
					label.setIcon(orange);
					label.setVisible(true);
				GraphPanel.add(label);
			}if ( pitch > 100 && pitch <= 125 ){
				blue = new ImageIcon("img/bar05.png");
				label = new JLabel();
					label.setIcon(blue);
					label.setVisible(true);
				GraphPanel.add(label);
			}if (pitch > 125 && pitch <= 150){
				green = new ImageIcon("img/bar06.png");
				label = new JLabel();
					label.setIcon(green);
					label.setVisible(true);
				GraphPanel.add(label);
			}if (pitch > 150 && pitch <= 175){
				yellow = new ImageIcon("img/bar07.png");
				label = new JLabel();
					label.setIcon(yellow);
					label.setVisible(true);
				GraphPanel.add(label);
			}if (pitch > 175 && pitch <= 200){
				orange = new ImageIcon("img/bar08.png");
				label = new JLabel();
					label.setIcon(orange);
					label.setVisible(true);
				GraphPanel.add(label);
			}if ( pitch > 200 && pitch <= 225 ){
				blue = new ImageIcon("img/bar09.png");
				label = new JLabel();
					label.setIcon(blue);
					label.setVisible(true);
				GraphPanel.add(label);
			}if (pitch > 225 && pitch <= 250){
				green = new ImageIcon("img/bar10.png");
				label = new JLabel();
					label.setIcon(green);
					label.setVisible(true);
				GraphPanel.add(label);
			}if (pitch > 250 && pitch <= 275){
				yellow = new ImageIcon("img/bar11.png");
				label = new JLabel();
					label.setIcon(yellow);
					label.setVisible(true);
				GraphPanel.add(label);
			}if (pitch > 275 && pitch <= 300){
				orange = new ImageIcon("img/bar12.png");
				label = new JLabel();
					label.setIcon(orange);
					label.setVisible(true);
				GraphPanel.add(label);
			}if ( pitch > 300 && pitch <= 325 ){
				blue = new ImageIcon("img/bar13.png");
				label = new JLabel();
					label.setIcon(blue);
					label.setVisible(true);
				GraphPanel.add(label);
			}if (pitch > 325 && pitch <= 350){
				green = new ImageIcon("img/bar14.png");
				label = new JLabel();
					label.setIcon(green);
					label.setVisible(true);
				GraphPanel.add(label);
			}if (pitch > 350 && pitch <= 375){
				yellow = new ImageIcon("img/bar15.png");
				label = new JLabel();
					label.setIcon(yellow);
					label.setVisible(true);
				GraphPanel.add(label);
			}if (pitch > 375){
				red = new ImageIcon("img/bar16.png");
				label = new JLabel();
					label.setIcon(red);
					label.setVisible(true);
				GraphPanel.add(label);
				GraphPanel.add(label);
			}
			scroll.getHorizontalScrollBar().setValue(scroll.getHorizontalScrollBar().getMaximum());
			GraphPanel.revalidate();
			


		
		}
	}
}
		


