package edu.nju.treasuryArbitrage.view.navigater;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.nju.treasuryArbitrage.controller.threads.LiveData;
import edu.nju.treasuryArbitrage.view.common.ViewFactory;
import edu.nju.treasuryArbitrage.view.common.ComponentPanel;
import edu.nju.treasuryArbitrage.view.common.TreasuryFrame;
import edu.nju.treasuryArbitrage.view.common.ViewHelper;

public class NavigationBarItem extends JPanel{
	private static final long serialVersionUID = -4002005037897737972L;
	
	private Color selectedBackground;
	private Color selectedForeground;
	
	private NavigationBar parent;
	
	private boolean isImportant;
	private boolean isSelected;
	private JLabel naviLabel = new JLabel();
	private ComponentPanel changedPage;
	
	
	/*protected*/ NavigationBarItem(NavigationBar navigationBar, ComponentPanel panel, String name,
                                    boolean isImportant, boolean isSelected,
                                    Color selectedBackground, Color selectedForeground) {
		this.parent = navigationBar;
		this.isImportant = isImportant;
		this.changedPage = panel;
		this.isSelected = isSelected;
		init(name);
		addListeners();
		this.selectedBackground = selectedBackground;
		this.selectedForeground = selectedForeground;
	}
	
	/*protected*/ NavigationBarItem(NavigationBar navigationBar, ComponentPanel panel, String name) {
		this(navigationBar, panel, name, true, false, NavigationBar.SELECTED_BACKGROUND_COLOR, NavigationBar.SELECTED_FOREGROUND_COLOR);
	}
	
	public void initialState() {
		setBorder(null);
		if (isSelected) {
			NavigationBarItem.this.setBackground(selectedBackground);
			naviLabel.setForeground(selectedForeground);
		} else {
			this.setBackground(NavigationBar.BACKGROUND_COLOR);
			if (isImportant) {
				naviLabel.setForeground(NavigationBar.FOREGROUND_COLOR);
			} else {
				naviLabel.setForeground(NavigationBar.FOREGROUND_COLOR);
			}
		}
		this.repaint();
	}
	
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
		ViewHelper.getInstance().startUpdateHoldings();
	}

	private void addListeners() {
		this.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseExited(MouseEvent e) {
				initialState();
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				naviLabel.setForeground(NavigationBar.MOUSE_ENTER_FOREGROUND_COLOR);
				NavigationBarItem.this.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, NavigationBar.MOUSE_ENTER_FOREGROUND_COLOR));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				parent.clearSelected();
				isSelected = true;
				parent.initAllItem();
				
				TreasuryFrame frame = ViewFactory.getInstance().getFrame();
				frame.setPage((JPanel)changedPage);
				changedPage.updatePage();
			}
		});
	}
	
	public ComponentPanel getPage() {
		return changedPage;
	}

	private void init(String name) {
		this.setPreferredSize(new Dimension(70, parent.getHeight()));
		this.setBackground(NavigationBar.BACKGROUND_COLOR);
		
		naviLabel.setFont(new Font("΢���ź�", Font.BOLD, 16));
		naviLabel.setText(name);
		initialState();
		this.add(naviLabel);
	}
	
	
}
