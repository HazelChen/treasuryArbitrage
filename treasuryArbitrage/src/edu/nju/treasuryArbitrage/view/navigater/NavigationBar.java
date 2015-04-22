package edu.nju.treasuryArbitrage.view.navigater;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.nju.treasuryArbitrage.view.common.ViewFactory;
import edu.nju.treasuryArbitrage.view.common.ComponentPanel;
import edu.nju.treasuryArbitrage.view.common.ScreenSize;
import edu.nju.treasuryArbitrage.view.common.TreasuryFrame;

public class NavigationBar extends JPanel{
	private static final long serialVersionUID = -3260728762055118620L;
	
	public static final Color BACKGROUND_COLOR = new Color(223,240,251);
	/*package*/ static final Color MOUSE_ENTER_FOREGROUND_COLOR = new Color(247, 68, 97);
	/*package*/ static final Color SELECTED_BACKGROUND_COLOR = BACKGROUND_COLOR;
	/*package*/ static final Color FOREGROUND_COLOR = Color.BLACK;
	/*package*/ static final Color SELECTED_FOREGROUND_COLOR = FOREGROUND_COLOR;
	
	private NavigationBarItem[] items = new NavigationBarItem[3];
	
	private NaviUserItem naviUserItem;
	
	
	public NavigationBar() {
		init();
		naviPanelInit();
		assemble();
	}
	
	public void init() {
		int width = ScreenSize.WIDTH;
		int height = (int) (ScreenSize.HEIGHT / 25.0);
		this.setPreferredSize(new Dimension(width, height));
		this.setBackground(BACKGROUND_COLOR);
		this.setLayout(new BorderLayout());
	}
	
	public void naviPanelInit() {
		ViewFactory factory = ViewFactory.getInstance();
		items[0] = new NavigationBarItem(this, factory.getFuturesMarket(), "期货行情");
		items[1] = new NavigationBarItem(this, factory.getArbitragePortfolio(), "套利组合");
		items[2] = new NavigationBarItem(this, factory.getHoldings(), "持仓情况"){
			private static final long serialVersionUID = -4590415644339941609L;

			@Override
			public void setSelected(boolean selected) {
				super.setSelected(selected);
				if (selected) {
					
				}
			}
		}; 
	}
	
	public void assemble() {
		JPanel left = new JPanel();
		left.setOpaque(false);
		BoxLayout boxLayout = new BoxLayout(left, BoxLayout.X_AXIS);
		left.setLayout(boxLayout);
		
		for (int i = 0; i < items.length; i++) {
			left.add(items[i]);
			if (i == items.length - 1) {
				continue;
			}
			JLabel dotLabel = new JLabel(" - ");
			dotLabel.setForeground(FOREGROUND_COLOR);
			dotLabel.setFont(new Font("微软雅黑", Font.BOLD, 15));
			dotLabel.setBackground(BACKGROUND_COLOR);
			left.add(dotLabel);
		}
		this.add(left, BorderLayout.WEST);
		
		naviUserItem = new NaviUserItem();
		this.add(naviUserItem, BorderLayout.EAST);
	}

	public void initAllItem() {
		for (int i = 0; i < items.length; i++) {
			items[i].initialState();
		}
	}

	public void clearSelected() {
		for (int i = 0; i < items.length; i++) {
			items[i].setSelected(false);
		}
	}
	
	public void setArbitragePortfolioSelected() {
		setSelected(1);
		
	}
	
	private void setSelected(int i) {
		clearSelected();
		items[i].setSelected(true);
		initAllItem();
		
		TreasuryFrame frame = ViewFactory.getInstance().getFrame();
		ComponentPanel changedPage = items[i].getPage();
		frame.setPage((JPanel)changedPage);
		changedPage.updatePage();
	}

	public void setHoldingsSelected() {
		setSelected(2);
	}

	public void setUserName(String username) {
		naviUserItem.setUserName(username);
	}

}
