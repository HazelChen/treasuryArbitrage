package edu.nju.treasuryArbitrage.PersonalCenter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import edu.nju.treasuryArbitrage.resources.ColorResources;
import edu.nju.treasuryArbitrage.resources.NumericalResources;

public class RiskTipDialog extends JDialog {
	private static final long serialVersionUID = 5893692668956428617L;

	private JLabel riskTitle;
	private JTextArea riskDetail;
	private JPanel panel, panel2, panelbottom, conp;
	private JButton closebtn;

	/*package*/ RiskTipDialog () {
		init();
	}
	
	private void init() {
		this.setUndecorated(true);
		this.setBackground(ColorResources.LOGIN_BORDER_GRAY);
		this.setMaximumSize(new Dimension(720, 510));
		this.setMinimumSize(new Dimension(750, 510));
		this.setResizable(false);
		this.setModal(true);
		
		dml = new detailML();
		conp = new JPanel();
		conp.setLayout(new BorderLayout());
		this.setLocation(
				(NumericalResources.SCREEN_WIDTH - this.getWidth()) / 2,
				(NumericalResources.SCREEN_HEIGHT - this.getHeight()) / 2);
		panel = new JPanel();
		panelbottom = new JPanel();
		newsTitle = new JLabel(sNewsTitle);
		Font titlef = new Font("ËÎÌå", Font.BOLD, 24);
		newsTitle.setFont(titlef);
		inv = new JLabel("");
		inv.setPreferredSize(new Dimension(33, 1));
		// inv.setVisible(false);

		panel.setLayout(new BorderLayout());
		panel.setPreferredSize(new Dimension(750, 52));
		panel.add(newsTitle, FlowLayout.LEFT);
		panel.add(inv, "West");

		panel2 = new JPanel();
		panel2.setSize(682, 398);
		newsDetail = new JTextArea(snewsDetail, 25, 62);
		newsDetail.setEditable(false);
		newsDetail.setLineWrap(true);
		newsDetail.setBorder(new LineBorder(Color.BLACK, 1));
		newsDetail.setBackground(Color.WHITE);

		panel2.add(newsDetail);
		closebtn = new JButton("¹Ø±Õ´°¿Ú");
		closebtn.setFocusPainted(false);
		closebtn.addMouseListener(dml);
		closebtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelbottom.setPreferredSize(new Dimension(750, 52));
		panelbottom.add(closebtn);

		conp.setSize(720, 510);
		conp.setBorder(new LineBorder(Color.GRAY, 1));
		conp.add(panel, "North");
		conp.add(panel2, "Center");
		conp.add(panelbottom, "South");
		add(conp);
		this.pack();
	}

	class detailML implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			curdg.setVisible(false);
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

	}
}
