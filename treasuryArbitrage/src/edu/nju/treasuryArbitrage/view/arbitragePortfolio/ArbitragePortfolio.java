package edu.nju.treasuryArbitrage.view.arbitragePortfolio;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import edu.nju.treasuryArbitrage.controller.threads.LiveData;
import edu.nju.treasuryArbitrage.model.ArbGroup;
import edu.nju.treasuryArbitrage.view.common.ComponentPanel;
import edu.nju.treasuryArbitrage.view.common.ScreenSize;
import edu.nju.treasuryArbitrage.view.navigater.NavigationBar;

public class ArbitragePortfolio extends JPanel implements ComponentPanel{
	private static final long serialVersionUID = -8995031434902984463L;

	/*package*/ static final Color BACKGROUND_COLOR = Color.BLACK;
	/*package*/ static final Color FOREGROUND_COLOR = Color.WHITE;
	
	private JTabbedPane tabbedPane = new JTabbedPane();
	
	private InternalList internalList = new InternalList();
	
	
	public ArbitragePortfolio() {
		init();
	}
	
	public void initPoint() {
		internalList.initPoint(0, LiveData.getInstance().getBriefsTF1412(),
				LiveData.getInstance().getBriefsTF1503());
		internalList.initPoint(1, LiveData.getInstance().getBriefsTF1503(),
				LiveData.getInstance().getBriefsTF1506());
		internalList.initPoint(2, LiveData.getInstance().getBriefsTF1412(),
				LiveData.getInstance().getBriefsTF1506());
	}
	
	private void init() {
		setLayout(null);
		setBackground(NavigationBar.BACKGROUND_COLOR);
		
		tabbedPane.setBounds(0, 0, ScreenSize.WIDTH, ScreenSize.HEIGHT);
		tabbedPane.setBackground(NavigationBar.BACKGROUND_COLOR);
		tabbedPane.setForeground(Color.BLACK);
		this.add(tabbedPane);
		
		internalList.addInt(new ArbGroup("TF1412", "TF1503"));
		internalList.addInt(new ArbGroup("TF1412", "TF1506"));
		internalList.addInt(new ArbGroup("TF1503", "TF1506"));
		tabbedPane.add("锟斤拷锟揭�", internalList.get(0));
		tabbedPane.add("锟斤拷隙锟�", internalList.get(1));
		tabbedPane.add("锟斤拷锟斤拷锟�", internalList.get(2));
		updatePage();
	}
	
	@Override
	public void updatePage() {
		ArrayList<ArbGroup> arbGroups = LiveData.getInstance().getArbGroups();
		for (int i = 0; i < arbGroups.size(); i++) {
			ArbGroup arbGroup = arbGroups.get(i);
			ArbitragePortfolioInternal internal = internalList.get(arbGroup);
			internal.setGroupRecentBuy(arbGroup.isRecentBuy());
			internal.update();
		}
		internalList.update();
	}
}
