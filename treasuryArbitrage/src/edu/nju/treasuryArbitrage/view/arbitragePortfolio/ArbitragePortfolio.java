package edu.nju.treasuryArbitrage.view.arbitragePortfolio;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import edu.nju.treasuryArbitrage.controller.dataInterface.DataInterface;
import edu.nju.treasuryArbitrage.controller.dataInterface.DataInterfaceFactory;
import edu.nju.treasuryArbitrage.model.LiveData;
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
        DataInterface dataInterface = DataInterfaceFactory.getInstance()
                .getDataInterfaceToServer();
        String[] futuresCodes = LiveData.getInstance().getFuturesCodes();
		internalList.initPoint(0, dataInterface.getPastPriceToday(futuresCodes[0]),
                dataInterface.getPastPriceToday(futuresCodes[1]));
		internalList.initPoint(1, dataInterface.getPastPriceToday(futuresCodes[0]),
                dataInterface.getPastPriceToday(futuresCodes[2]));
		internalList.initPoint(2, dataInterface.getPastPriceToday(futuresCodes[0]),
                dataInterface.getPastPriceToday(futuresCodes[3]));
	}
	
	private void init() {
		setLayout(null);
		setBackground(NavigationBar.BACKGROUND_COLOR);
		
		tabbedPane.setBounds(0, 0, ScreenSize.WIDTH, ScreenSize.HEIGHT);
		tabbedPane.setBackground(NavigationBar.BACKGROUND_COLOR);
		tabbedPane.setForeground(Color.BLACK);
		this.add(tabbedPane);

        //XXX Futures codes in the array is from small to large by default.
        String[] futuresCodes = LiveData.getInstance().getFuturesCodes();
		internalList.addInt(new ArbGroup(futuresCodes[0], futuresCodes[1]));
		internalList.addInt(new ArbGroup(futuresCodes[0], futuresCodes[2]));
		internalList.addInt(new ArbGroup(futuresCodes[1], futuresCodes[2]));
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