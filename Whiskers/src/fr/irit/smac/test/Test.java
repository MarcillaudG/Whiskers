package fr.irit.smac.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import fr.irit.smac.ui.BoxPlotWindow;
import fr.irit.smac.util.Toolkit;

public class Test {

	public static void main(String[] args) {
		
		BoxPlotWindow window = new BoxPlotWindow(1000);
		
		
		Map<String,Double> map = new HashMap<String,Double>();
		Random r = new Random(1000);
		for(int i = 1; i < 1000; i++){
			List<Double> list = new ArrayList<Double>();
			map.put("1",new Double(r.nextDouble()));
			map.put("2",new Double(r.nextDouble()));
			map.put("3",new Double(r.nextDouble()));
			map.put("4",new Double(r.nextDouble()));
			map.put("5",new Double(r.nextDouble()));
			map.put("6",new Double(r.nextDouble()));
			map.put("7",new Double(r.nextDouble()));
			map.put("8",new Double(r.nextDouble()));
			list = Toolkit.dataToStat(map);
			window.addItem(list, "Serie"+i, "type"+i);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		}
	}
	

}
