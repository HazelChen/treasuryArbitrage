package edu.nju.treasuryArbitrage.ui.futuresMarket;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.HorizontalAlignment;

import edu.nju.treasuryArbitrage.logic.liveUpdate.LiveData;
import edu.nju.treasuryArbitrage.model.Arb_detail;

public class LineChart extends JPanel {
	private static final long serialVersionUID = 1323688315244501166L;

	@SuppressWarnings("deprecation")
	private TimeSeries timeseries = new TimeSeries("价格", Second.class);
	private JFreeChart jfreechart;
	private int index;
	
	public LineChart(int index) {
		this.index = index;
		
		this.setLayout(new BorderLayout());
		
		ChartPanel chartPanel = init();
		this.add(chartPanel);
		
		this.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 4, new Color(193,193,193)));
	}
	
	/**
	 * if there is only one line
	 */
	public LineChart(int index, Color lineColor) {
		this(index);
		XYPlot plot = jfreechart.getXYPlot();
		plot.getRenderer().setSeriesPaint(0, lineColor);
		plot.setBackgroundPaint(Color.BLACK);
		plot.setDomainGridlinePaint(Color.RED);
		plot.setRangeGridlinePaint(Color.RED);
	}

	private XYDataset createDataset() { 
		ArrayList<Arb_detail> arb_details = LiveData.getInstance().getArb_details();
		
		timeseries.addOrUpdate(new Second(new Date()), arb_details.get(index).getFormattedArb_detail().getPresentPrice());
		
		TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();
		timeseriescollection.addSeries(timeseries);
		return timeseriescollection;
	}
	
	public void addNewPrice(double newPrice) {
		timeseries.addOrUpdate(new Second(new Date()), newPrice);
	}
	
	private ChartPanel init() {
		XYDataset xydataset = createDataset();
		jfreechart = ChartFactory.createTimeSeriesChart(
				"分时走势", "", "", xydataset, true, true,true);
		ChartPanel chartPanel = new ChartPanel(jfreechart, true);
		
		jfreechart.setBackgroundPaint(Color.BLACK);
		
		// 设置标题字体
		TextTitle title = jfreechart.getTitle();
		title.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		title.setHorizontalAlignment(HorizontalAlignment.LEFT);
		title.setPaint(Color.WHITE);
		
		XYPlot xyplot = jfreechart.getXYPlot();
		xyplot.setBackgroundPaint(Color.BLACK);
		xyplot.setDomainGridlinePaint(Color.RED);
		xyplot.setRangeGridlinePaint(Color.RED);
		
		DateAxis xAxis = (DateAxis) xyplot.getDomainAxis();
		xAxis.setDateFormatOverride(new SimpleDateFormat("hh:mm"));
		xAxis.setAxisLinePaint(Color.RED);
		xAxis.setTickLabelPaint(new Color(255,43,28));
		
		NumberAxis yAxis = (NumberAxis) xyplot.getRangeAxis();
		yAxis.setAxisLinePaint(Color.RED);
		yAxis.setTickLabelPaint(new Color(255,43,28));
		
		//图例
		jfreechart.getLegend().setVisible(false);
		return chartPanel;
	}
}
