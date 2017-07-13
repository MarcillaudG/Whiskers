package fr.irit.smac.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Toolkit {

	public static List<Double> dataToStat(Map<String,Double> map){
		List<Double> ret = new ArrayList<Double>();
		List<Double> values = new ArrayList<Double>();
		for(String s : map.keySet()){
			values.add(map.get(s));
		}
		Collections.sort(values);
		
		double max = values.get(values.size()-1);
		double min = values.get(0);
		if(max <= 0)
			max = -min;
		else
			if(min < 0){
				min = -min;
				if(min > max)
					max = min;
			}
		// Calcul of the mean value
		ret.add(Toolkit.mean(values)/max);
		
		// Calcul of the median value
		ret.add(Toolkit.median(values)/max);
		
		// Calcul of the first quartile
		ret.add(Toolkit.firstQuartile(values)/max);
		
		// Calcul of the third quartil
		ret.add(Toolkit.thirdQuartile(values)/max);
		
		// The min value
		ret.add(values.get(0)/max);
		
		// The max value
		ret.add(values.get(values.size()-1)/max);
		
		
		return ret;
	}
	
	private static Double thirdQuartile(List<Double> values) {
		double quartil = 0.0;
		if(values.size() %4 == 0){
			quartil = values.get((values.size()/4)*3);
		}
		else{
			quartil = values.get((values.size()/4)*3+1);
		}
		return quartil;
	}

	private static Double firstQuartile(List<Double> values) {
		double quartil = 0.0;
		if(values.size() %4 == 0){
			quartil = values.get(values.size()/4);
		}
		else{
			quartil = values.get((values.size()/4)+1);
		}
		return quartil;
	}


	private static Double median(List<Double> list) {
		double median =0;
		if(list.size()%2 == 0){
			int mid = list.size()/2;
			median = (list.get(mid)+list.get(mid+1))/2.0;
		}
		else
		{
			median = list.get(list.size()/2+1);
		}
		return median;
	}

	public static double mean(List<Double> list){
		Double sum = 0.0;
		for(Double d : list){
			sum += d;
		}
		return sum / list.size();
	}
}
