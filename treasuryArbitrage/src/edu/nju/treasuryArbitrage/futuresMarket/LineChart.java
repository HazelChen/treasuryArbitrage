package edu.nju.treasuryArbitrage.futuresMarket;

import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

import vo.Arb_detail;
import edu.nju.treasuryArbitrage.liveUpdate.LiveData;

public class LineChart extends JPanel {
	private static final long serialVersionUID = 1323688315244501166L;

	private ChartPanel frame1;
	private HashMap<Second, Double> hashMap = new LinkedHashMap<>();
	private TimeSeries timeseries = new TimeSeries("价格", Second.class);
	private int index;
	
	public LineChart(int index) {
		this.index = index;
		init();
		this.add(frame1);
	}

	private XYDataset createDataset() { 
		ArrayList<Arb_detail> arb_details = LiveData.getInstance().getArb_details();
		
		hashMap.put(new Second(new Date()), arb_details.get(index).getPresentPrice());
		for (Entry<Second, Double> entity : hashMap.entrySet()) {
			timeseries.add(entity.getKey(), entity.getValue());
		}
		
		TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();
		timeseriescollection.addSeries(timeseries);
		return timeseriescollection;
	}
	
	public void addNewPrice(double newPrice) {
		this.remove(frame1);
		init();
		this.add(frame1);
	}
	
	private void init() {
		XYDataset xydataset = createDataset();
		JFreeChart jfreechart = ChartFactory.createTimeSeriesChart(
				"分时走势", "时间", "价格", xydataset, true, true,true);
		XYPlot xyplot = (XYPlot) jfreechart.getPlot();
		DateAxis dateaxis = (DateAxis) xyplot.getDomainAxis();
		dateaxis.setDateFormatOverride(new SimpleDateFormat("hh:mm"));
		frame1 = new ChartPanel(jfreechart, true);
		dateaxis.setLabelFont(new Font("黑体", Font.BOLD, 14)); // 水平底部标题
		dateaxis.setTickLabelFont(new Font("宋体", Font.BOLD, 12)); // 垂直标题
		ValueAxis rangeAxis = xyplot.getRangeAxis();// 获取柱状
		rangeAxis.setLabelFont(new Font("黑体", Font.BOLD, 15));
		jfreechart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));
		jfreechart.getTitle().setFont(new Font("宋体", Font.BOLD, 20));// 设置标题字体
	}
}
