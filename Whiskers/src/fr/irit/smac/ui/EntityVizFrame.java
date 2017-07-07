package fr.irit.smac.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;

public class EntityVizFrame extends JFrame {

	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	public EntityVizFrame(String name, Number number, Number number2, Number number3, Number number4, Number number5, Number number6) {
		setTitle("Visualization of the entity : "+name);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTextPane textPane = new JTextPane();
		String str = "Average : " + number + "\nMedian : " + number2 + "\nFirst Quartile : "+number3 
				+ "\nThird Quartile : " + number4 + "\n Min : "+number5 + "\nMax : "+number6;
		textPane.setText(str);
		textPane.setEditable(false);
		contentPane.add(textPane);
	}

}
