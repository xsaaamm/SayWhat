package gui;
import java.awt.Color;
import java.awt.GridLayout;
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
	public static float pitch;
	public static float probability;
	public static double rms;
	public static double timeStamp;
	TopPaneFree topPanel = new TopPaneFree();
	final int SLEEP_MEAN = 100;
	final int SLEEP_SCATTER = 100;
	LiveGraph lg;
	DataStreamWriter out;
	long startMillis;
	//final int MAX_DATASETS;
	static JTextArea textArea = new JTextArea();
	private static AudioDispatcher dispatcher;
	private Mixer currentMixer;
	private PitchEstimationAlgorithm algo = PitchEstimationAlgorithm.MPM;

	/**private ActionListener algoChangeListener = new ActionListener(){
		@Override
		public void actionPerformed(final ActionEvent e) {
			String name = e.getActionCommand();
			PitchEstimationAlgorithm newAlgo = PitchEstimationAlgorithm.valueOf(name);
			algo = newAlgo;
			try {
				setNewMixer(currentMixer);
			} catch (LineUnavailableException e1) {
				e1.printStackTrace();
			} catch (UnsupportedAudioFileException e1) {
				e1.printStackTrace();
			}
	}};*/

	public FreePane() {
		setBackground(Color.LIGHT_GRAY);
		this.setLayout(new GridLayout(0, 1));
		add(topPanel);
	
		
		textArea.setEditable(false);
		JScrollPane pitchNumPanel = new JScrollPane(textArea);
		add(pitchNumPanel);
		
		JPanel pitchGraphPanel = new PitchDetectionPanel();
		add(pitchGraphPanel);		
	
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
				textArea_1.append(message2);
				lg.disposeGUIAndExit();                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
			}
			
		// Set a values separator:
		out.setSeparator(";");
		
		// Add a file description line:
		out.writeFileInfo("SayWhat! demo file.");
		
		// Set-up the data series:
		out.addDataSeries("Time");
		out.addDataSeries("Pitch");	
		//out.addDataSeries("Probability");
		//out.addDataSeries("RMS");
		//out.addDataSeries("Timestamp");	
		
		startMillis = -1;

		
		
		pitchGraphPanel.add(graph);
			
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
		FreePane.textArea.append("You may now begin.  Started listening with " + Shared.toLocalString(mixer.getMixerInfo().getName()) + "\n");

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
				JFrame frame = new PitchDetectorExample();
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
			//probability = pitchDetectionResult.getProbability();
			//rms = audioEvent.getRMS() * 100;
			
			//test will be deleted 
			String message = String.format("Pitch detected at %.2fs: %.2fHz ( %.2f probability, RMS: %.5f )\n", timeStamp,pitch,probability,rms);
			textArea.append(message);
			textArea.setCaretPosition(textArea.getDocument().getLength());
			
			//--> This info will output to make the graph
			//--> Values will be used to create csv file, then csv will be used to create real time graph	

			// Set-up the data values:
			out.setDataValue(System.currentTimeMillis() - startMillis);
			out.setDataValue(pitch);

			// Write dataset to disk:			
			out.writeDataSet();
			LiveGraph.application().updateInvoker().requestUpdate();
			
			// If LiveGraph's main window was closed by user, we can finish the demo:
			if (out.hadIOException()) {
				if (out.getIOException() instanceof PipeClosedByReaderException) {
					textArea_1.append("LiveGraph window closed. No reason for more data. Finishing.");
					out.close();
					textArea_1.append("Demo finished. Cheers.");
					
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
		

	

