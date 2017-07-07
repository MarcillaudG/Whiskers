package fr.irit.smac.test;

import java.util.ArrayList;
import java.util.List;

import fr.irit.smac.ui.BoxPlotWindow;

public class Test {

	public static void main(String[] args) {
		
		BoxPlotWindow window = new BoxPlotWindow(1000);
		window.setVisible(true);
		
		

		for(int i = 1; i < 1000; i++){
			List<Double> list = new ArrayList<Double>();
			list.add(new Double(5));
			list.add(new Double(5));
			list.add(new Double(3));
			list.add(new Double(7));
			list.add(new Double(1));
			list.add(new Double(9));
			window.addItem(list, "Serie"+i, "type"+i);

		}
	}
	

}
