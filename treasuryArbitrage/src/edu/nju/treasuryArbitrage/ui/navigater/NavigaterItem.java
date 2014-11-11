package edu.nju.treasuryArbitrage.ui.navigater;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.nju.treasuryArbitrage.factory.MajorPartsFactory;
import edu.nju.treasuryArbitrage.ui.common.ComponentPanel;
import edu.nju.treasuryArbitrage.ui.common.TreasuryFrame;

public class NavigaterItem extends JPanel{
	private static final long serialVersionUID = -4002005037897737972L;
	
	private Color selectedBackground;
	private Color selectedForeground;
	
	private Navigater parent;
	
	private boolean isImportant;
	private boolean isSelected;
	private JLabel naviLabel = new JLabel();
	private ComponentPanel changedPage;
	
	
	/*protected*/ NavigaterItem(Navigater navigater, ComponentPanel panel, String name, 
			boolean isImportant, boolean isSelected,
			Color selectedBackground, Color selectedForeground) {
		this.parent = navigater;
		this.isImportant = isImportant;
		this.changedPage = panel;
		this.isSelected = isSelected;
		init(name);
		addListeners();
		this.selectedBackground = selectedBackground;
		this.selectedForeground = selectedForeground;
	}
	
	/*protected*/ NavigaterItem(Navigater navigater, ComponentPanel panel, String name) {
		this(navigater, panel, name, true, false, Navigater.SELECTED_BACKGROUND_COLOR, Navigater.SELECTED_FOREGROUND_COLOR);
	}
	
	public void initialState() {
		setBorder(null);
		if (isSelected) {
			NavigaterItem.this.setBackground(selectedBackground);
			naviLabel.setForeground(selectedForeground);
		} else {
			this.setBackground(Navigater.BACKGROUND_COLOR);
			if (isImportant) {
				naviLabel.setForeground(Navigater.FOREGROUND_COLOR);
			} else {
				naviLabel.setForeground(Navigater.FOREGROUND_COLOR);
			}
		}
		this.repaint();
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
//				NavigaterItem.this.setBackground(Navigater.MOUSE_ENTER_BACKGROUND_COLOR);
				naviLabel.setForeground(Navigater.MOUSE_ENTER_FOREGROUND_COLOR);
				NavigaterItem.this.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Navigater.MOUSE_ENTER_FOREGROUND_COLOR));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				parent.clearSelected();
				isSelected = true;
				parent.initAllItem();
				
				TreasuryFrame frame = MajorPartsFactory.getInstance().getFrame();
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
		this.setBackground(Navigater.BACKGROUND_COLOR);
		
		naviLabel.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 16));
		naviLabel.setText(name);
		initialState();
		this.add(naviLabel);
	}
	
	
}
