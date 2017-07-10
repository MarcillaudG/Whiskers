package fr.irit.smac.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import fr.irit.smac.model.BoxPlotChart;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class BoxPlotWindow extends JFrame {

	private ChartPanel contentPane;
	private BoxPlotChart chart;


	/**
	 * Create the frame.
	 */
	public BoxPlotWindow(int sizeMax) {
		setTitle("Box chart");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		chart = new BoxPlotChart(sizeMax);
		setContentPane(chart);
		this.pack();
		this.setVisible(true);
	}
	
	public void addItem(List<Double> list, String serie, String type){
		this.chart.addItem(list, serie, type);
	}

}
