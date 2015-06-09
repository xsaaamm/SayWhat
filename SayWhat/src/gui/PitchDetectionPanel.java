package gui;

import java.awt.GridLayout;
import java.io.IOException;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import org.LiveGraph.LiveGraph;
import org.LiveGraph.dataFile.common.PipeClosedByReaderException;
import org.LiveGraph.dataFile.write.DataStreamWriter;
import org.LiveGraph.demoDataSource.LiveGraphMemoryStreamDemo;
import com.softnetConsult.utils.sys.SystemTools;
import java.awt.TextArea;

public class PitchDetectionPanel extends JPanel {

	private static final long serialVersionUID = -5107785666165487335L;
	
	public PitchDetectionPanel(){
		super(new GridLayout(0,1));
		setBorder(new TitledBorder("Graph will be here"));
		
		TextArea textArea = new TextArea();
		add(textArea);
		
		String message = "Pitch: " + FreePane.pitch + "(Hz). Probability: " + FreePane.probability*100 + "%. Timestamp: " + FreePane.timeStamp;
		textArea.append(message);
		
	}
	}
