package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.SystemColor;

public class displayOptionsPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public displayOptionsPanel() {
		setBackground(SystemColor.scrollbar);
		
		String[] displayOptions = {"Choose files to display...","This Session","Last Session","First Session","All"};
		JComboBox comboBox = new JComboBox(displayOptions);
		setLayout(new BorderLayout(0, 0));
		add(comboBox, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		
		JList list = new JList();
		panel.add(list);

	}

}
