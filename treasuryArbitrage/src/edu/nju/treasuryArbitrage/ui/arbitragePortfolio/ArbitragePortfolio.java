package edu.nju.treasuryArbitrage.ui.arbitragePortfolio;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JLabel;
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
		
		updatePage();
	}
	
	@Override
	public void updatePage() {
		ArrayList<ArbGroup> arbGroups = LiveData.getInstance().getArbGroups();
		if (arbGroups == null ||arbGroups.size() == 0) {
			initEmptyPage();
			repaint();
			return;
		}
		
		boolean isChange = updateList(arbGroups);
		if (!isChange) {
			internalList.update();
		} else {
			tabbedPane.removeAll();
			Iterator<ArbitragePortfolioInternal> iterator = internalList.iterator();
			int i = 1;
			while (iterator.hasNext()) {
				ArbitragePortfolioInternal internal = iterator.next();
				tabbedPane.add("组合" + arabToChinese(i), internal);
				i++;
			}
			tabbedPane.repaint();
		}
		repaint();
	}

	private boolean updateList(ArrayList<ArbGroup> arbGroups) {
		boolean isChange = internalList.removeExcess(arbGroups);
		for (ArbGroup arbGroup : arbGroups) {
			if (!internalList.containsInt(arbGroup)) {
				internalList.addInt(arbGroup);
				isChange = true;
			}
		}
		return isChange;
	}

	private void initEmptyPage() {
		internalList.clear();
		
		JLabel label = new JLabel("目前还没有套利组合可推荐");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		label.setForeground(Color.BLACK);
		label.setBounds(ScreenSize.WIDTH / 2 - 150, 10, 300, 30);
		this.add(label);
	}
	
	private String arabToChinese(int i) {
		switch (i) {
		case 1:
			return "一";
		case 2:
			return "二";
		case 3:
			return "三";
		}
		return "";
	}

}
