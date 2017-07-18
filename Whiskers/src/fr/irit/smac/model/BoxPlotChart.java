package fr.irit.smac.model;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.entity.XYItemEntity;
import org.jfree.chart.labels.BoxAndWhiskerToolTipGenerator;
import org.jfree.chart.labels.BoxAndWhiskerXYToolTipGenerator;
import org.jfree.chart.labels.XYToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.WaferMapPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.WaferMapRenderer;
import org.jfree.chart.renderer.category.BoxAndWhiskerRenderer;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.chart.renderer.xy.XYBoxAndWhiskerRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.Range;
import org.jfree.data.general.DatasetChangeEvent;
import org.jfree.data.general.DatasetChangeListener;
import org.jfree.data.general.DatasetGroup;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.statistics.BoxAndWhiskerCategoryDataset;
import org.jfree.data.statistics.BoxAndWhiskerItem;
import org.jfree.data.statistics.BoxAndWhiskerXYDataset;
import org.jfree.data.statistics.DefaultBoxAndWhiskerCategoryDataset;
import org.jfree.data.statistics.DefaultBoxAndWhiskerXYDataset;
import org.jfree.data.time.DateRange;
import org.jfree.data.time.Day;
import org.jfree.data.time.Minute;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleInsets;

import fr.irit.smac.ui.EntityVizFrame;
import fr.irit.smac.util.Toolkit;

public class BoxPlotChart extends JPanel implements ChangeListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5834921537376235574L;

	private JFreeChart chart;

	private DefaultBoxAndWhiskerXYDataset boxDataset;

	private ChartPanel chartPanel;

	private JSlider slider;

	private TranslatingBoxDataset dataset;

	private Day d ;
	
	private XYSeries series;
	
	private XYSeriesCollection collection;
	
	private Map<String,List<Double>> datas;

	private int sliderInitialValue;
	private DateAxis domainAxis;
	private int lastValue;
	// one month (milliseconds, seconds, minutes, hours, days)
	private int delta = 10000000  ;
	private TimeSeriesCollection timeSeriesCollection;


	public BoxPlotChart(int sizeMax){
		super(new BorderLayout());
		datas = new HashMap<String,List<Double>>();
		this.delta = sizeMax/100;
		this.sliderInitialValue = sizeMax;
		lastValue = sliderInitialValue;
		this.chart = createChar();
		this.chartPanel = new ChartPanel(this.chart);
		this.chartPanel.setPreferredSize(new java.awt.Dimension(600, 270));
		this.chartPanel.setDomainZoomable(true);
		this.chartPanel.setRangeZoomable(true);
		this.chartPanel.setMouseWheelEnabled(true);
		Border border = BorderFactory.createCompoundBorder(
				BorderFactory.createEmptyBorder(4, 4, 4, 4),
				BorderFactory.createEtchedBorder()
				);
		this.chartPanel.setBorder(border);
		this.add(this.chartPanel);
		chartPanel.addChartMouseListener(new ChartMouseListener() {

			@Override
			public void chartMouseMoved(ChartMouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void chartMouseClicked(ChartMouseEvent e) {
				if(e.getEntity() instanceof XYItemEntity){
					XYItemEntity xyitem=(XYItemEntity) e.getEntity(); // get clicked entity
					DefaultBoxAndWhiskerXYDataset dataset = (DefaultBoxAndWhiskerXYDataset)xyitem.getDataset(); // get data set
					EntityVizFrame viz = new EntityVizFrame(
							xyitem.getItem()+"",
							dataset.getMeanValue(xyitem.getSeriesIndex(), xyitem.getItem()),
							dataset.getMedianValue(xyitem.getSeriesIndex(), xyitem.getItem()),
							dataset.getQ1Value(xyitem.getSeriesIndex(), xyitem.getItem()),
							dataset.getQ3Value(xyitem.getSeriesIndex(), xyitem.getItem()),
							dataset.getMinRegularValue(xyitem.getSeriesIndex(), xyitem.getItem()), 
							dataset.getMaxRegularValue(xyitem.getSeriesIndex(), xyitem.getItem()));
					viz.setVisible(true);
				}

			}
		});

		JPanel dashboard = new JPanel(new BorderLayout());
		dashboard.setBorder(BorderFactory.createEmptyBorder(0, 4, 4, 4));   
		// make the slider units "minutes"
		this.slider = new JSlider(-sizeMax, sizeMax, 0);
		slider.setPaintLabels(true);
		slider.setMajorTickSpacing(sizeMax/10);
		slider.setPaintTicks(true);
		this.slider.addChangeListener(this);
		dashboard.add(this.slider);
		add(dashboard, BorderLayout.SOUTH);

	}


	private JFreeChart createChar() {
		this.domainAxis = new DateAxis("Time");
		NumberAxis rangeAxis = new NumberAxis("");
		XYBoxAndWhiskerRenderer renderer = new XYBoxAndWhiskerRenderer();
		renderer.setToolTipGenerator(new BoxAndWhiskerXYToolTipGenerator());
		XYPlot plot = new XYPlot(getBoxDataset(), domainAxis, rangeAxis, renderer);
		JFreeChart chart = new JFreeChart(
				"Box chart", 
				JFreeChart.DEFAULT_TITLE_FONT,
				plot, 
				true);
		// performance
		chart.setAntiAlias(false);
		return chart;
	}


	private TranslatingBoxDataset getDataset() {
		return dataset;
	}


	/**
	 * Creates a sample dataset.
	 * 
	 * @return A sample dataset.
	 */
	private DefaultBoxAndWhiskerXYDataset getBoxDataset() {
		if(boxDataset == null)
			boxDataset = new DefaultBoxAndWhiskerXYDataset("Serie");

		return boxDataset;
	}
	
	private XYSeriesCollection getXYDataset(){
		if(this.collection == null)
			this.collection = new XYSeriesCollection();
		return this.collection;
	}
	
	/**
	 * Add an item to the chart
	 * @param list
	 * @param serie
	 * @param type
	 */
	public void addItem(List<Double> list, String serie, String type){
		if(d == null)
			d = new Day(new Date());
		RegularTimePeriod regularTimePeriod = d.next();
		List<Double> l = new ArrayList<Double>();
		l.add(3.0);
		BoxAndWhiskerItem item = new BoxAndWhiskerItem(
				list.get(0), list.get(1), list.get(2),
				list.get(3), list.get(4), list.get(5),
				list.get(4), list.get(5), list);
		this.getBoxDataset().add(new Date(), item);
	}
	
	/**
	 * Add an item to the chart
	 * @param list
	 * @param serie
	 * @param type
	 */
	public void addItem(List<Double> list, String serie, String type,Date date,int number){
		BoxAndWhiskerItem item = new BoxAndWhiskerItem(
				list.get(0), list.get(1), list.get(2),
				list.get(3), list.get(4), list.get(5),
				list.get(4), list.get(5), list);
		DefaultBoxAndWhiskerXYDataset test = new DefaultBoxAndWhiskerXYDataset(serie);
		test.add(date, item);
		this.chart.getXYPlot().setDataset(number,test);
	}
	
	/**
	 * Add an item to the chart
	 * @param list
	 * @param serie
	 * @param type
	 */
	public void addItem(Double point, String serie, String type,Date date,int number){
		if(this.datas.get(serie) == null)
			this.datas.put(serie,new ArrayList<Double>());
		this.datas.get(serie).add(point);
		List<Double> list = Toolkit.dataToStat(serie,datas);
		BoxAndWhiskerItem item = new BoxAndWhiskerItem(
				list.get(0), list.get(1), list.get(2),
				list.get(3), list.get(4), list.get(5),
				list.get(4), list.get(5), list);
		DefaultBoxAndWhiskerXYDataset test = new DefaultBoxAndWhiskerXYDataset(serie);
		test.add(date, item);
		this.chart.getXYPlot().setDataset(number,test);
	}
	
	public void addPoint(double value, Date date){
	}

	public JFreeChart getChart(){
		return this.chart;
	}

	public TimeSeries getSerie(String s){
		return this.timeSeriesCollection.getSeries(s);
	}

	@Override
	public void stateChanged(ChangeEvent event) {
		int value = this.slider.getValue();
		long minimum = domainAxis.getMinimumDate().getTime();
		long maximum = domainAxis.getMaximumDate().getTime();
		if (value<lastValue) { 
			// left
			minimum = minimum - delta;
			maximum = maximum - delta;
		} 
		else { 
			// right
			minimum = minimum + delta;
			maximum = maximum + delta;
		}
		DateRange range = new DateRange(minimum,maximum);
		domainAxis.setRange(range);
		lastValue = value;
	}
}
