package edu.nju.treasuryArbitrage.futuresMarket;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Minute;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

import edu.nju.treasuryArbitrage.factory.DataInterfaceFactory;
import edu.nju.treasuryArbitrage.network.DataInterface;

public class LineChart extends JPanel {
	private static final long serialVersionUID = 1323688315244501166L;

	private ChartPanel frame1;
	private TimeSeries timeseries = new TimeSeries("价格",Minute.class);
	
	public LineChart() {
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
		DataInterface dataInterface = DataInterfaceFactory.getInstance().getDataInterfacePile();
		HashMap<Long, Double> dateAndPrice = dataInterface.getDateAndPricePair();
		
		for (Entry<Long, Double> entry : dateAndPrice.entrySet()) {
			timeseries.add(new Minute(new Date(entry.getKey())), entry.getValue());
		}
		TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();
		timeseriescollection.addSeries(timeseries);
		return timeseriescollection;
	}
	
	public void addNewPrice(double newPrice) {
		timeseries.add(new Minute(new Date()), newPrice);
	}
	
	public static void main(String[] args) {
		final LineChart lineChart = new LineChart();
		JFrame frame = new JFrame();
		frame.add(lineChart, BorderLayout.NORTH);
		JButton button = new JButton("add");
		frame.add(button,BorderLayout.SOUTH);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				lineChart.addNewPrice(140);
			}
		});
		frame.pack();
		frame.setVisible(true);
	}
}
