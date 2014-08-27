package edu.nju.treasuryArbitrage.navigater;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import edu.nju.treasuryArbitrage.factory.MajorPartsFactory;
import edu.nju.treasuryArbitrage.framework.TreasuryFrame;

public class NavigaterItem extends JPanel{
	private static final long serialVersionUID = -4002005037897737972L;
	
	private JPanel changedPage;
	
	/*protected*/ NavigaterItem(JPanel panel) {
		this.setPreferredSize(new Dimension(300, 100));
		this.setBorder(BorderFactory.createEtchedBorder());
		
		this.changedPage = panel;
		this.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				TreasuryFrame frame = MajorPartsFactory.getInstance().getFrame();
				frame.setPage(changedPage);
			}
		});
	}
	
}
