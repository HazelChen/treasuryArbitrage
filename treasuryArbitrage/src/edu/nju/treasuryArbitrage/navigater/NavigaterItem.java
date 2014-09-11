package edu.nju.treasuryArbitrage.navigater;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.nju.treasuryArbitrage.factory.MajorPartsFactory;
import edu.nju.treasuryArbitrage.framework.TreasuryFrame;
import edu.nju.treasuryArbitrage.resources.ColorResources;

public class NavigaterItem extends JPanel{
	private static final long serialVersionUID = -4002005037897737972L;
	
	private Navigater parent;
	
	private boolean isImportant;
	private boolean isSelected;
	private JLabel naviLabel = new JLabel();
	private JPanel changedPage;
	
	
	/*protected*/ NavigaterItem(Navigater navigater, JPanel panel, String name, boolean isImportant) {
		this.parent = navigater;
		this.isImportant = isImportant;
		this.changedPage = panel;
		init(name);
		addListeners();
	}
	
	public void initialState() {
		if (isSelected) {
			NavigaterItem.this.setBackground(ColorResources.NAVIGATER_SELECTED_GRAY);
			naviLabel.setForeground(ColorResources.NAVI_MOUSE_ENTER_YELLOW);
		} else {
			this.setBackground(ColorResources.NAVIGATER_GRAY);
			if (isImportant) {
				naviLabel.setForeground(ColorResources.NAVI_IMPORTANT_NORMAL);
			} else {
				naviLabel.setForeground(Color.WHITE);
			}
		}
	}
	
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	private void addListeners() {
		this.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseExited(MouseEvent e) {
				initialState();
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				NavigaterItem.this.setBackground(ColorResources.NAVIGATER_MOUSE_ENTER_GRAY);
				naviLabel.setForeground(ColorResources.NAVI_MOUSE_ENTER_YELLOW);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				parent.clearSelected();
				isSelected = true;
				parent.initAllItem();
				
				TreasuryFrame frame = MajorPartsFactory.getInstance().getFrame();
				frame.setPage(changedPage);
			}
		});
	}

	private void init(String name) {
		this.setPreferredSize(new Dimension(70, 30));
		this.setBackground(ColorResources.NAVIGATER_GRAY);
		
		naviLabel.setText(name);
		initialState();
		this.add(naviLabel);
	}
	
	
}
