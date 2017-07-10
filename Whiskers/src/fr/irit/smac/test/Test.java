package fr.irit.smac.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.irit.smac.ui.BoxPlotWindow;
import fr.irit.smac.util.Toolkit;

public class Test {

	public static void main(String[] args) {
		
		BoxPlotWindow window = new BoxPlotWindow(1000);
		
		
		Map<String,Double> map = new HashMap<String,Double>();
		for(int i = 1; i < 1000; i++){
			List<Double> list = new ArrayList<Double>();
			map.put("1",new Double(i));
			map.put("2",new Double(i+i));
			map.put("3",new Double(i*i));
			map.put("4",new Double(i+i*i));
			map.put("5",new Double(i-i*i));
			list = Toolkit.dataToStat(map);
			window.addItem(list, "Serie"+i, "type"+i);

		}
	}
	

}
