package gui;

import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import org.LiveGraph.LiveGraph;
import org.LiveGraph.dataFile.common.PipeClosedByReaderException;
import org.LiveGraph.dataFile.write.DataStreamWriter;
import org.LiveGraph.demoDataSource.LiveGraphMemoryStreamDemo;

import com.softnetConsult.utils.sys.SystemTools;

import java.awt.TextArea;

import be.tarsos.dsp.AudioEvent;
import be.tarsos.dsp.pitch.PitchDetectionResult;

public class PitchDetectionPanel extends JPanel {

	private static final long serialVersionUID = -5107785666165487335L;
	public static JTextArea textArea2;
	
	public PitchDetectionPanel(){
		super(new GridLayout(0,1));
		setBorder(new TitledBorder("Graph will be here"));
	}
}
