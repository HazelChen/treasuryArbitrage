package edu.nju.treasuryArbitrage.view.futuresMarket;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.DateTickUnitType;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.SegmentedTimeline;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.HorizontalAlignment;

public class LineChart extends JPanel {
	private static final long serialVersionUID = 1323688315244501166L;

	@SuppressWarnings("deprecation")
	private TimeSeries timeseries = new TimeSeries("�۸�", Second.class);
	private JFreeChart jfreechart;
	
	public LineChart() {
		this.setLayout(new BorderLayout());
		
		ChartPanel chartPanel = init();
		this.add(chartPanel);
	}
	
	/**
	 * if there is only one line
	 */
	public LineChart(Color lineColor) {
		this();
		lineColor = Color.WHITE;
		XYPlot plot = jfreechart.getXYPlot();
		plot.getRenderer().setSeriesPaint(0, lineColor);
		plot.setBackgroundPaint(Color.BLACK);
		plot.setDomainGridlinePaint(Color.RED);
		plot.setRangeGridlinePaint(Color.RED);
	}

	private XYDataset createDataset() { 
		TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();
		timeseriescollection.addSeries(timeseries);
		return timeseriescollection;
	}
	
	public void addNewPrice(double newPrice) {
		timeseries.addOrUpdate(new Second(new Date()), newPrice);
	}
	
	public void addNewPrice(Date date, double newPrice) {
		timeseries.addOrUpdate(new Second(date), newPrice);
	}
	
	public void setYRange(double low, double high) {
		XYPlot xyplot = jfreechart.getXYPlot();
		NumberAxis yAxis = (NumberAxis) xyplot.getRangeAxis();
		yAxis.setRange(low, high);
	}
	
	public void setTitle(String title) {
		jfreechart.setTitle(title);
	}
	
	private ChartPanel init() {
		XYDataset xydataset = createDataset(); 
		jfreechart = ChartFactory.createTimeSeriesChart(
				"��ʱ����", "", "", xydataset, true, true,true);
		ChartPanel chartPanel = new ChartPanel(jfreechart, true);
		
		jfreechart.setBackgroundPaint(Color.BLACK);
		
		// ���ñ�������
		TextTitle title = jfreechart.getTitle();
		title.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		title.setHorizontalAlignment(HorizontalAlignment.LEFT);
		title.setPaint(Color.WHITE);
		
		XYPlot xyplot = jfreechart.getXYPlot();
		xyplot.setBackgroundPaint(Color.BLACK);
		xyplot.setDomainGridlinePaint(Color.RED);
		xyplot.setRangeGridlinePaint(Color.RED);
		
		DateAxis xAxis = (DateAxis) xyplot.getDomainAxis();
		
		SegmentedTimeline timeline = new SegmentedTimeline(SegmentedTimeline.MINUTE_SEGMENT_SIZE, 270, 0);
		Calendar minExceptCalendar = Calendar.getInstance();
		minExceptCalendar.set(Calendar.HOUR_OF_DAY, 11);
		minExceptCalendar.set(Calendar.MINUTE, 30);
		Calendar maxExceptCalendar = Calendar.getInstance();
		maxExceptCalendar.set(Calendar.HOUR_OF_DAY, 13);
		maxExceptCalendar.set(Calendar.MINUTE, 0);
		timeline.addException(minExceptCalendar.getTimeInMillis(), maxExceptCalendar.getTimeInMillis());
		xAxis.setTimeline(timeline);
		//TODO change with date
		Calendar minCalendar = Calendar.getInstance();
		minCalendar.set(Calendar.HOUR_OF_DAY, 9);
		minCalendar.set(Calendar.MINUTE, 15);
		Calendar maxCalendar = Calendar.getInstance();
		maxCalendar.set(Calendar.HOUR_OF_DAY, 15);
		maxCalendar.set(Calendar.MINUTE, 15);
		
		xAxis.setRange(minCalendar.getTime(), maxCalendar.getTime());
		xAxis.setTickUnit(new DateTickUnit(DateTickUnitType.MINUTE, 15));
		xAxis.setDateFormatOverride(new SimpleDateFormat("HH:mm"));
		xAxis.setAxisLinePaint(Color.RED);
		xAxis.setTickLabelPaint(new Color(255,43,28));
		
		NumberAxis yAxis = (NumberAxis) xyplot.getRangeAxis();
		yAxis.setAxisLinePaint(Color.RED);
		yAxis.setTickLabelPaint(new Color(255,43,28));
		
//		yAxis.setRange(95.0, 97.0);
		
		//ͼ��
		jfreechart.getLegend().setVisible(false);
		return chartPanel;
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(800, 600);
		frame.add(new LineChart());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
