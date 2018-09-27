package org.dase.cogan.ui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

public class RulifierGUI extends JPanel
{
	/**
	 * Bookkeeping
	 */
	private static final long serialVersionUID = 1L;

	/** Empty Constructor **/
	public RulifierGUI()
	{

	}

	public void init()
	{
		// create a top-level window and add the graph component.
		JFrame frame = new JFrame("Logician");

		frame.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;
		gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
		Box right = Box.createVerticalBox();
		
		JButton open = new JButton("OPEN");
		right.add(open);
		JTextArea jtf = new JTextArea();
		jtf.setPreferredSize(new Dimension(100,100));
		right.add(jtf);
		JButton save = new JButton("CONVERT");
		right.add(save);
		
		frame.add(right, gbc);
		
		frame.pack();		
		frame.setSize(600, 300);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
