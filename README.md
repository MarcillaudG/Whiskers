# Whiskers

Whiskers : A tool to create Box and Whiskers chart and extract data from it.

/* Requirement */ 

  - JDK 1.8
  
/* Dependencies */

  - JFreeChart
 
 /* Usage */ 
 
 1 - Create the window 
      
       BoxPlotWindow window = new BoxPlotWindow(1000);
       
 2 - Create a map of your data and use the Toolkit to transform it then add to the window
  
		  Map<String,Double> map = new HashMap<String,Double>();
		  for(int i = 1; i < 1000; i++){
		  
		  	map.put("1",new Double(i));
			map.put("2",new Double(i+i));
			map.put("3",new Double(i*i));
			map.put("4",new Double(i+i*i));
			map.put("5",new Double(i-i*i));
      
			List<Double> list = new ArrayList<Double>();
			list = Toolkit.dataToStat(map);
			window.addItem(list), "Serie"+i, "type"+i);
			
		   }
      
       
