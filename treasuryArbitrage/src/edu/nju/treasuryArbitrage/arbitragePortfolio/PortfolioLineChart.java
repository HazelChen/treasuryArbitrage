package edu.nju.treasuryArbitrage.arbitragePortfolio;

import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

public class PortfolioLineChart extends JPanel {
	private static final long serialVersionUID = 1323688315244501166L;

	private ChartPanel frame1;
	private TimeSeries timeseries1 = new TimeSeries("价格", Second.class);
	private TimeSeries timeseries2 = new TimeSeries("价格", Second.class);
	private int index1;
	private int index2;
	
	public PortfolioLineChart(int index1, int index2) {
		this.index1 = index1;
		this.index2 = index2;
		
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
		this.add(frame1);
	}

	private XYDataset createDataset() { // 这个数据集有点多，但都不难理解
		ArrayList<Arb_detail> arb_details = LiveData.getInstance().getArb_details();
		
		timeseries1.add(new Second(new Date()), arb_details.get(index1).getPresentPrice());
		timeseries2.add(new Second(new Date()), arb_details.get(index2).getPresentPrice());
		
		TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();
		timeseriescollection.addSeries(timeseries1);
		timeseriescollection.addSeries(timeseries2);
		return timeseriescollection;
	}
	
	public void update() {
		ArrayList<Arb_detail> arb_details = LiveData.getInstance().getArb_details();
		double price1 = arb_details.get(index1).getPresentPrice();
		double price2 = arb_details.get(index2).getPresentPrice();
		timeseries1.add(new Second(new Date()), price1);
		timeseries2.add(new Second(new Date()), price2);
	}
}
