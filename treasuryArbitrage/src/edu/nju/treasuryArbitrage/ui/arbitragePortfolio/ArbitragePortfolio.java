package edu.nju.treasuryArbitrage.ui.arbitragePortfolio;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import edu.nju.treasuryArbitrage.logic.liveUpdate.LiveData;
import edu.nju.treasuryArbitrage.model.ArbGroup;
import edu.nju.treasuryArbitrage.ui.common.ComponentPanel;
import edu.nju.treasuryArbitrage.ui.common.ScreenSize;
import edu.nju.treasuryArbitrage.ui.navigater.Navigater;

public class ArbitragePortfolio extends JPanel implements ComponentPanel{
	private static final long serialVersionUID = -8995031434902984463L;

	/*package*/ static final Color BACKGROUND_COLOR = Color.BLACK;
	/*package*/ static final Color FOREGROUND_COLOR = Color.WHITE;
	
	private JTabbedPane tabbedPane = new JTabbedPane();
	
	private InternalList internalList = new InternalList();
	
	
	public ArbitragePortfolio() {
		init();
	}
	
	private void init() {
		setLayout(null);
		setBackground(Navigater.BACKGROUND_COLOR);
		
		tabbedPane.setBounds(0, 0, ScreenSize.WIDTH, ScreenSize.HEIGHT);
		tabbedPane.setBackground(Navigater.BACKGROUND_COLOR);
		tabbedPane.setForeground(Color.BLACK);
		this.add(tabbedPane);
		
		internalList.addInt(new ArbGroup("TF1412", "TF1503"), 
				LiveData.getInstance().getBriefsTF1412(),
				LiveData.getInstance().getBriefsTF1503());
		internalList.addInt(new ArbGroup("TF1412", "TF1506"),
				LiveData.getInstance().getBriefsTF1412(),
				LiveData.getInstance().getBriefsTF1506());
		internalList.addInt(new ArbGroup("TF1503", "TF1506"),
				LiveData.getInstance().getBriefsTF1503(),
				LiveData.getInstance().getBriefsTF1506());
		tabbedPane.add("组合一", internalList.get(0));
		tabbedPane.add("组合二", internalList.get(1));
		tabbedPane.add("组合三", internalList.get(2));
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
