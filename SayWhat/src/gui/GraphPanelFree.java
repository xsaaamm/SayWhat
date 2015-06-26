package gui;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.LiveGraph.LiveGraph;
import org.LiveGraph.dataFile.write.DataStreamWriter;
import org.LiveGraph.dataFile.write.DataStreamWriterFactory;

public class GraphPanelFree extends JPanel {

	private static final long serialVersionUID = 5889920481723493490L;
	private LiveGraph lg;
	private PitchPlotPanel graph = new PitchPlotPanel();
	private static JTextArea textArea_1;
	private long startMillis;
	private DataStreamWriter out;
	int datasetNumber = 0;
	int burstNumber = 0;
	public static final String DEMO_DIR = System.getProperty("user.dir");
	//private static LiveGraphPanel lgp = new LiveGraphPanel();
	
	public GraphPanelFree() {
		initialize();
		getData();
	}
	
	private void initialize(){
		add(graph);	
		
		// Start LiveGraph:
		lg = LiveGraph.application();
		lg.execEngine();
		LiveGraph.application().eventManager().registerListener(graph);
		JPanel innerplotpanel = lg.guiManager().createPlotPanel();
		graph.add(innerplotpanel);
		
		// Setup a data writer object:
		out = DataStreamWriterFactory.createDataWriter(DEMO_DIR,"LiveGraphDemo");	
		
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
		
		// Add data series titles:
		out.addDataSeries("Time");
		out.addDataSeries("Pitch");	
		//out.addDataSeries("Timestamp");	
		
		startMillis = -1;
		if (startMillis == -1){
			startMillis = System.currentTimeMillis();
		}
	}
	private void getData(){
		while(FreePane.dispatcher != null){
			for (int b = 0; b >= 0; b++) {
				// Set-up the data values:
				out.setDataValue(System.currentTimeMillis() - startMillis);
				out.setDataValue(FreePane.pitch);
		
				// Write dataset to disk:			
				out.writeDataSet();
				
				// Check for any other IOErrors and display:			
				if (out.hadIOException()) {
					out.getIOException().printStackTrace();
					out.resetIOException();
				}
			}
			LiveGraph.application().updateInvoker().requestUpdate();
		}
	}
}

