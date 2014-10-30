package edu.nju.treasuryArbitrage.ui.navigater;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.nju.treasuryArbitrage.factory.MajorPartsFactory;
import edu.nju.treasuryArbitrage.ui.common.ColorConstants;
import edu.nju.treasuryArbitrage.ui.common.LoginedUser;
import edu.nju.treasuryArbitrage.ui.common.TreasuryFrame;
import edu.nju.treasuryArbitrage.ui.personalCenter.LoginFrame;

public class NaviUserItem extends JPanel{
	private static final long serialVersionUID = -1940967418792821751L;
	
	private JLabel loggoutLabel = new JLabel("°²È«ÍË³ö");
	
	/*package*/ NaviUserItem() {
		this.setBackground(Navigater.BACKGROUND_COLOR);
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
				LoginFrame loginFrame = new LoginFrame();
				frame.setVisible(false);
				loginFrame.setVisible(true);
				frame.dispose();
				MajorPartsFactory.getInstance().setFrame(null);
			}
		});
	}

	private void assemble() {
		String userMessage = "ÄãºÃ," + LoginedUser.getLoginedUser();
		JLabel label = new JLabel(userMessage);
		label.setForeground(Navigater.FOREGROUND_COLOR);
		label.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 16));
		this.add(label);
		
		loggoutLabel.setForeground(ColorConstants.LINK_BLUE);
		loggoutLabel.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 16));
		this.add(loggoutLabel);
	}

}
