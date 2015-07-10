package freemode;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import java.awt.SystemColor;

public class displayOptionsPanel extends JPanel {

	private static final long serialVersionUID = 8605968659345501294L;
	/**
	 * Create the panel.
	 */
	public displayOptionsPanel() {
		setBackground(SystemColor.WHITE);
		setLayout(new BorderLayout(0, 0));
		
		String[] displayOptions = {"Choose files to display...","This Session","Last Session","First Session","All"};
		String[] files = {"File 1", "File 2", "File 3", "File 4","File 5", "File 6", "File 7", "File 8", 
							"File 9", "File 10", "File 7", "File 8", "File 9", "File 10", "File 7", "File 8", "File 9", "File 10"};
		
		JComboBox<String> comboBox = new JComboBox<>(displayOptions);
		add(comboBox, BorderLayout.NORTH);
		
		JList<String> list = new JList<>(files);
		list.setVisibleRowCount(-1);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		
		JScrollPane listscroll = new JScrollPane(list);
		add(listscroll, BorderLayout.CENTER);
		listscroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

	}

}
