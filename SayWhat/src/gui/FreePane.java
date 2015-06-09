package gui;
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
import org.LiveGraph.demoDataSource.LiveGraphMemoryStreamDemo;

import com.softnetConsult.utils.sys.SystemTools;

import be.tarsos.dsp.AudioDispatcher;
import be.tarsos.dsp.AudioEvent;
import be.tarsos.dsp.io.jvm.JVMAudioInputStream;
import be.tarsos.dsp.pitch.PitchDetectionHandler;
import be.tarsos.dsp.pitch.PitchDetectionResult;
import be.tarsos.dsp.pitch.PitchProcessor;
import be.tarsos.dsp.pitch.PitchProcessor.PitchEstimationAlgorithm;

import java.awt.Color;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.InvocationTargetException;

public class FreePane extends JPanel implements PitchDetectionHandler {

	private static final long serialVersionUID = 5336983837352763066L;
	public static JTextArea textArea;
	public static JTextArea textArea_1;
	public static float pitch;
	public static float probability;
	public static double rms;
	public static double timeStamp;

	private AudioDispatcher dispatcher;
	@SuppressWarnings("unused")
	private Mixer currentMixer;
	
	private PitchEstimationAlgorithm algo;	
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
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		add(new JScrollPane(textArea));
		
		JPanel pitchDetectionPanel = new PitchDetectionPanel();
		add(pitchDetectionPanel);		
		
		textArea_1 = new JTextArea();
		textArea_1.setEditable(false);
		pitchDetectionPanel.add(new JScrollPane(textArea_1));
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
			
			timeStamp = audioEvent.getTimeStamp();
			pitch = pitchDetectionResult.getPitch();
			probability = pitchDetectionResult.getProbability();
			rms = audioEvent.getRMS() * 100;
			
			//test will be deleted 
			String message = String.format("Pitch detected at %.2fs: %.2fHz ( %.2f probability, RMS: %.5f )\n", timeStamp,pitch,probability,rms);
			textArea.append(message);
			textArea.setCaretPosition(textArea.getDocument().getLength());
			
			//--> This info will output to make the graph
			//--> Values will be used to create csv file, then csv will be used to create real time graph
			
			writeFile(pitchDetectionResult,audioEvent);
			
			/**String message2 = "Pitch: " + FreePane.getPitch() + "(Hz). Probability: " 
					+ FreePane.getProb()*100 + "%. Timestamp: " + FreePane.getTimeStamp() + "\n";
			textArea_1.append(message2);
			textArea_1.setCaretPosition(textArea_1.getDocument().getLength());
			*/
		}
		
	}
	
	/**public static float getPitch(){
		return pitch;
	}	
	public static float getProb(){
		return probability;
	}	
	public static double getRMS(){
		return rms;
	}	
	public static double getTimeStamp(){
		return timeStamp;
	}
	 * @return */
	
	private void writeFile(PitchDetectionResult pitchDetectionResult,AudioEvent audioEvent){
		
		timeStamp = audioEvent.getTimeStamp();
		pitch = pitchDetectionResult.getPitch();
		probability = pitchDetectionResult.getProbability();
		rms = audioEvent.getRMS() * 100;
		
		final int SLEEP_MEAN = 100;
		final int SLEEP_SCATTER = 100;
		//final int MAX_DATASETS;
		
		// Start LiveGraph:
		LiveGraph lg = LiveGraph.application();
		//lg.execStandalone();
		
		// Turn LiveGraph into memory mode:
		DataStreamWriter out = lg.updateInvoker().startMemoryStreamMode();
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
		out.addDataSeries("Dataset number");
		out.addDataSeries("Burst number");
		out.addDataSeries("Pitch");	
		out.addDataSeries("Probability");
		out.addDataSeries("RMS");
		out.addDataSeries("Timestamp");		
					
		// Loop until enough datasets a written:
		int datasetNumber = 0;
		int burstNumber = 0;
		long startMillis = System.currentTimeMillis();
		while (pitchDetectionResult.getPitch() != -1) {
			
			// Status message:
			String message3 = ("Time: " + startMillis + ". " + "Datasets sent through memory so far: " + datasetNumber + ". "
							 + "Now sending burst " + burstNumber + "..." + "Pitch: " + pitch 
							 + "(Hz). Probability: " + probability + ". RMS: " + rms + "%. Timestamp: " 
							 + timeStamp + "\n");
			textArea_1.append(message3);
			
			// Write a few datasets to the file:
			for (int b = 0; b >= 0; b++) {
				// Set-up the data values:
				out.setDataValue(System.currentTimeMillis() - startMillis);
				out.setDataValue(datasetNumber);
				out.setDataValue(burstNumber);
				out.setDataValue(pitch);
				out.setDataValue(probability);
				out.setDataValue(rms);
				out.setDataValue(timeStamp);
						
				// Write dataset to disk:			
				out.writeDataSet();
				
				// If LiveGraph's main window was closed by user, we can finish the demo:
				if (out.hadIOException()) {
					if (out.getIOException() instanceof PipeClosedByReaderException) {
						textArea_1.append("LiveGraph window closed. No reason for more data. Finishing.");
						out.close();
						textArea_1.append("Demo finished. Cheers.");
						return;
					}
				}
				
				// Check for any other IOErrors and display:			
				if (out.hadIOException()) {
					out.getIOException().printStackTrace();
					out.resetIOException();
				}
				datasetNumber++;
			}
		
			// Pause:
			long sleep = (long) Math.max(SLEEP_MEAN + Math.random() * 2 * SLEEP_SCATTER - SLEEP_SCATTER, 1.);
			SystemTools.sleep(sleep);	
		}
		// Finish:
		out.close();
		lg.disposeGUIAndExit();
		textArea_1.append("Demo finished.");
		
		try {
			(new LiveGraphMemoryStreamDemo()).exec();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
		

	

