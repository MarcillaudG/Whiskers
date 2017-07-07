package fr.irit.smac.model;

import java.util.List;

import org.jfree.data.general.DatasetChangeEvent;
import org.jfree.data.general.DatasetChangeListener;
import org.jfree.data.general.DatasetGroup;
import org.jfree.data.statistics.BoxAndWhiskerCategoryDataset;
import org.jfree.data.statistics.DefaultBoxAndWhiskerCategoryDataset;

public class TranslatingBoxDataset extends DefaultBoxAndWhiskerCategoryDataset implements BoxAndWhiskerCategoryDataset, DatasetChangeListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6337008254192626810L;

	private BoxAndWhiskerCategoryDataset underlyning;
	
	private double translate;
	
	public TranslatingBoxDataset(BoxAndWhiskerCategoryDataset underlying){
		this.underlyning = underlying;
		this.underlyning.addChangeListener(this);
		this.translate = 0.0;
	}
	
	public double getTranslate(){
		return this.translate;
	}
	
	public void setTranslate(double translate){
		this.translate = translate;
		fireDatasetChanged();
	}

	@Override
	public int getColumnIndex(Comparable comp) {
		return this.underlyning.getColumnIndex(comp);
	}

	@Override
	public Comparable getColumnKey(int arg0) {
		return this.underlyning.getColumnKey(arg0);
	}

	@Override
	public List getColumnKeys() {
		return this.underlyning.getColumnKeys();
	}

	@Override
	public int getRowIndex(Comparable arg0) {
		return this.underlyning.getRowIndex(arg0);
	}

	@Override
	public Comparable getRowKey(int arg0) {
		return this.underlyning.getRowKey(arg0);
	}

	@Override
	public List getRowKeys() {
		return this.underlyning.getRowKeys();
	}

	@Override
	public Number getValue(Comparable arg0, Comparable arg1) {
		return this.underlyning.getValue(arg0, arg1);
	}

	@Override
	public int getColumnCount() {
		return this.underlyning.getColumnCount();
	}

	@Override
	public int getRowCount() {
		return this.underlyning.getRowCount();
	}

	@Override
	public Number getValue(int arg0, int arg1) {
		return this.underlyning.getValue(arg0, arg1);
	}

	@Override
	public void addChangeListener(DatasetChangeListener arg0) {
		this.underlyning.addChangeListener(arg0);
		
	}

	@Override
	public DatasetGroup getGroup() {
		return this.underlyning.getGroup();
	}

	@Override
	public void removeChangeListener(DatasetChangeListener arg0) {
		this.underlyning.removeChangeListener(arg0);
		
	}

	@Override
	public void setGroup(DatasetGroup arg0) {
		this.underlyning.setGroup(arg0);
		
	}

	@Override
	public void datasetChanged(DatasetChangeEvent arg0) {
		fireDatasetChanged();
		
	}

	@Override
	public Number getMaxOutlier(int arg0, int arg1) {
		return this.underlyning.getMaxOutlier(arg0, arg1);
	}

	@Override
	public Number getMaxOutlier(Comparable arg0, Comparable arg1) {
		return this.underlyning.getMaxOutlier(arg0, arg1);
	}

	@Override
	public Number getMaxRegularValue(int arg0, int arg1) {
		return this.underlyning.getMaxRegularValue(arg0, arg1);
	}

	@Override
	public Number getMaxRegularValue(Comparable arg0, Comparable arg1) {
		return this.underlyning.getMaxRegularValue(arg0, arg1);
	}

	@Override
	public Number getMeanValue(int arg0, int arg1) {
		return this.underlyning.getMeanValue(arg0, arg1);
	}

	@Override
	public Number getMeanValue(Comparable arg0, Comparable arg1) {
		return this.underlyning.getMeanValue(arg0, arg1);
	}

	@Override
	public Number getMedianValue(int arg0, int arg1) {
		return this.underlyning.getMedianValue(arg0, arg1);
	}

	@Override
	public Number getMedianValue(Comparable arg0, Comparable arg1) {
		return this.underlyning.getMedianValue(arg0, arg1);
	}

	@Override
	public Number getMinOutlier(int arg0, int arg1) {
		return this.underlyning.getMinOutlier(arg0, arg1);
	}

	@Override
	public Number getMinOutlier(Comparable arg0, Comparable arg1) {
		return this.underlyning.getMinOutlier(arg0, arg1);
	}

	@Override
	public Number getMinRegularValue(int arg0, int arg1) {
		return this.underlyning.getMinRegularValue(arg0, arg1);
	}

	@Override
	public Number getMinRegularValue(Comparable arg0, Comparable arg1) {
		return this.underlyning.getMinRegularValue(arg0, arg1);
	}

	@Override
	public List getOutliers(int arg0, int arg1) {
		return this.underlyning.getOutliers(arg0, arg1);
	}

	@Override
	public List getOutliers(Comparable arg0, Comparable arg1) {
		return this.underlyning.getOutliers(arg0, arg1);
	}

	@Override
	public Number getQ1Value(int arg0, int arg1) {
		return this.underlyning.getQ1Value(arg0, arg1);
	}

	@Override
	public Number getQ1Value(Comparable arg0, Comparable arg1) {
		return this.underlyning.getQ1Value(arg0, arg1);
	}

	@Override
	public Number getQ3Value(int arg0, int arg1) {
		return this.underlyning.getQ3Value(arg0, arg1);
	}

	@Override
	public Number getQ3Value(Comparable arg0, Comparable arg1) {
		return this.underlyning.getQ3Value(arg0, arg1);
	}

	
}
