/**package gui;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class FreePane extends JPanel {

	private static final long serialVersionUID = 5336983837352763066L;

	private TopPaneFree topPanel = new TopPaneFree();
	private static JPanel pitchGraphPanel = new GraphPanelFree();
	private JPanel pitchDetectionPanel = new PitchDetectionPanel();
	static JTextArea textArea;
	static JScrollPane pitchNumPanel = new JScrollPane(textArea);
	
	public FreePane() {
		intialize();
	}
	
	private void intialize(){
		//Main Jpanel
		setBackground(Color.LIGHT_GRAY);
		setLayout(new GridLayout(0, 2));
		
		//top panel
		add(topPanel);
	
		//second panel- numbers
		add(pitchNumPanel);
		textArea.add(pitchDetectionPanel);
		textArea.setEditable(false);
		
		//bottom panel- graph
		add(pitchGraphPanel);
		//pitchGraphPanel.setLayout(new GridLayout(0,2));
			
	}
}*/


package gui;
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
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.LiveGraph.LiveGraph;
import org.LiveGraph.dataFile.common.PipeClosedByReaderException;
import org.LiveGraph.dataFile.write.DataStreamWriter;

import be.tarsos.dsp.AudioDispatcher;
import be.tarsos.dsp.AudioEvent;
import be.tarsos.dsp.io.jvm.JVMAudioInputStream;
import be.tarsos.dsp.pitch.PitchDetectionHandler;
import be.tarsos.dsp.pitch.PitchDetectionResult;
import be.tarsos.dsp.pitch.PitchProcessor;
import be.tarsos.dsp.pitch.PitchProcessor.PitchEstimationAlgorithm;

public class FreePane extends JPanel implements PitchDetectionHandler {

	private static final long serialVersionUID = 5336983837352763066L;
	public static JTextArea textArea_1;
	public static JTextArea textArea_2;
	public static float pitch;
	public static float probability;
	public static double timeStamp;
	LiveGraph lg;
	DataStreamWriter out;
	long startMillis;

	private AudioDispatcher dispatcher;
	@SuppressWarnings("unused")
	private Mixer currentMixer;
	
	private PitchEstimationAlgorithm algo;	

	public FreePane() {
		setBackground(Color.LIGHT_GRAY);
		this.setLayout(new GridLayout(0, 1));
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel inputPanel = new InputPanel();
		panel.add(inputPanel);
		inputPanel.setLayout(new GridLayout(1, 0, 0, 0));
		ButtonPanel buttonPanel = new ButtonPanel();
		panel.add(buttonPanel);
		buttonPanel.setLayout(new GridLayout(0, 1));
		
		JPanel displayPanel = new JPanel();
		panel.add(displayPanel);
		displayPanel.add(new displayOptionsPanel());
		displayPanel.setLayout(new GridLayout(0, 1));
		
		inputPanel.addPropertyChangeListener("mixer",
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
		
		algo = PitchEstimationAlgorithm.MPM;
		
		textArea_2 = new JTextArea();
		textArea_2.setEditable(false);
		add(new JScrollPane(textArea_2));
		
		JPanel pitchDetectionPanel = new PitchDetectionPanel();
		add(pitchDetectionPanel);		
	
		// Start LiveGraph:
		lg = LiveGraph.application();
		lg.execEngine();
		
		PitchPlotPanel graph = new PitchPlotPanel();
		LiveGraph.application().eventManager().registerListener(graph);
		JPanel innerplotpanel = lg.guiManager().createPlotPanel();
		graph.add(innerplotpanel);
		
		// Turn LiveGraph into memory mode:
		out = lg.updateInvoker().startMemoryStreamMode();
			if (null == out) {
				String message2 =  ("Could not switch LiveGraph into memory stream mode.");
				textArea_2.append(message2);
				lg.disposeGUIAndExit();                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
			}
			
		// Set a values separator:
		out.setSeparator(";");
		
		// Add a file description line:
		out.writeFileInfo("SayWhat! demo file.");
		
		// Set-up the data series:
		out.addDataSeries("Time");
		out.addDataSeries("Pitch");	
		out.addDataSeries("Probability");
		out.addDataSeries("RMS");
		out.addDataSeries("Timestamp");	
		
		startMillis = -1;

		pitchDetectionPanel.add(graph);
			
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
			textArea_2.append("You may now begin.  Started listening with " + Shared.toLocalString(mixer.getMixerInfo().getName()) + "\n");
	
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
			dispatcher.addAudioProcessor(new PitchProcessor(algo, sampleRate, bufferSize, this));
			
			new Thread(dispatcher,"Audio dispatching").start();
	}

	public static void main(String... strings) throws InterruptedException, InvocationTargetException {
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
					frame = new PitchDetectorExample();
				} catch (LineUnavailableException e) {
					e.printStackTrace();
				}
				//textArea_1 = new JTextArea();
				frame.pack();
				frame.setVisible(true);
				//frame.getContentPane().add(textArea_1);
			}
		});
	}

	@Override
	public void handlePitch(PitchDetectionResult pitchDetectionResult,AudioEvent audioEvent) {
		if(pitchDetectionResult.getPitch() != -1){
			if (startMillis == -1){
				startMillis = System.currentTimeMillis();
			}
			timeStamp = audioEvent.getTimeStamp();
			pitch = pitchDetectionResult.getPitch();
			probability = pitchDetectionResult.getProbability();
			
			//test will be deleted 
			String message = String.format("Pitch detected at %.2fs: %.2fHz ( %.2f probability, RMS: %.5f )\n", timeStamp,pitch,probability);
			textArea_2.append(message);
			textArea_2.setCaretPosition(textArea_2.getDocument().getLength());

			// Set-up the data values:
			//out.setDataValue(System.currentTimeMillis() - startMillis);
			out.setDataValue(pitch);
			//out.setDataValue(probability);
	

			// Write dataset to disk:			
			out.writeDataSet();
			LiveGraph.application().updateInvoker().requestUpdate();
			
			// If LiveGraph's main window was closed by user, we can finish the demo:
			if (out.hadIOException()) {
				if (out.getIOException() instanceof PipeClosedByReaderException) {
					textArea_2.append("LiveGraph window closed. No reason for more data. Finishing.");
					out.close();
					textArea_2.append("Demo finished. Cheers.");
					
					// Finish:
					out.close();
					//lg.disposeGUIAndExit();
					
					return;
				}
			}
			// Check for any other IOErrors and display:			
			if (out.hadIOException()) {
				out.getIOException().printStackTrace();
				out.resetIOException();
			}

		}
	}
}
		

	




	

