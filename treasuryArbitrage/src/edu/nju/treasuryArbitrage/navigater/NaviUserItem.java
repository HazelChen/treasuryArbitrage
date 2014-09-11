package edu.nju.treasuryArbitrage.navigater;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.nju.treasuryArbitrage.factory.MajorPartsFactory;
import edu.nju.treasuryArbitrage.framework.LoginedUser;
import edu.nju.treasuryArbitrage.framework.TreasuryFrame;
import edu.nju.treasuryArbitrage.resources.ColorResources;

public class NaviUserItem extends JPanel{
	private static final long serialVersionUID = -1940967418792821751L;
	
	private JLabel loggoutLabel = new JLabel("��ȫ�˳�");
	
	/*package*/ NaviUserItem() {
		this.setBackground(ColorResources.NAVIGATER_GRAY);
		assemble();
		addListeners();
	}
	
	private void addListeners() {
		loggoutLabel.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseEntered(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				LoginedUser.setLoginedUser(null);
				TreasuryFrame frame = MajorPartsFactory.getInstance().getFrame();
				frame.enterLogin();
			}
		});
	}

	private void assemble() {
		String userMessage = "���," + LoginedUser.getLoginedUser();
		JLabel label = new JLabel(userMessage);
		label.setForeground(Color.WHITE);
		this.add(label);
		
		loggoutLabel.setForeground(ColorResources.LINK_BLUE);
		this.add(loggoutLabel);
	}

}
