package edu.nju.treasuryArbitrage.controller.threads;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.nju.treasuryArbitrage.view.common.ViewFactory;
import edu.nju.treasuryArbitrage.view.navigater.NavigationBar;

/**
 * ��ʾ��
 */
public class ThreadDiag extends JDialog {
	private static final long serialVersionUID = -5251697653622360445L;
	JLabel information;
	JPanel panel;
	MsgDgML listener;
	private JButton btnY, btnN;
	MyButtonUI btnUI = new MyButtonUI();

	public ThreadDiag(String message, final boolean isOpen) {

		this.setTitle("��Ϣ");
		this.getContentPane().setBackground(NavigationBar.BACKGROUND_COLOR);
		setMaximumSize(new Dimension(400, 150));
		setMinimumSize(new Dimension(400, 150));
		this.setLocationRelativeTo(null);
		setResizable(false);
		setModal(true);

		btnY = new JButton("��");
		btnY.setFocusable(true);
		btnY.setBackground(Color.white);
		btnY.setUI(btnUI);
		btnN = new JButton("��");
		btnN.setFocusable(true);
		btnN.setBackground(Color.white);
		btnN.setUI(btnUI);
		information = new JLabel(message);
		Font titlef = new Font("΢���ź�", Font.BOLD, 15);
		information.setFont(titlef);
		panel = new JPanel();
		panel.setOpaque(false);
		panel.setLayout(new BorderLayout());
		JPanel p1 = new JPanel();
		p1.setOpaque(false);
		JPanel invp = new JPanel();
		invp.setOpaque(false);
		invp.setPreferredSize(new Dimension(383, 30));
		p1.add(invp, BorderLayout.CENTER);
		p1.add(information, BorderLayout.SOUTH);
		panel.add(p1, BorderLayout.CENTER);
		JPanel p2 = new JPanel(null);
		p2.setOpaque(false);
		btnY.setBounds(80, 15, 50, 30);
		btnN.setBounds(200, 15, 50, 30);
		p2.add(btnY);
		p2.add(btnN);
		p2.setPreferredSize(new Dimension(383, 60));
		panel.add(p2, BorderLayout.SOUTH);
		panel.setPreferredSize(new Dimension(383, 150));

		listener = new MsgDgML();
		btnY.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ThreadDiag.this.setVisible(false);
				if (isOpen) {
					go2ArbitragePortfolio();// ��ת���������
				} else {
					go2Holdings();
				}
				ThreadDiag.this.setVisible(false);
				ThreadDiag.this.dispose();
			}

		});
		btnN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ThreadDiag.this.setVisible(false);
				ThreadDiag.this.dispose();
			}

		});
		this.add(panel);
	}

	public void go2ArbitragePortfolio() {
		NavigationBar navigationBar = ViewFactory.getInstance().getNavigationBar();
		navigationBar.setArbitragePortfolioSelected();
	}

	public void go2Holdings() {
		NavigationBar navigationBar = ViewFactory.getInstance().getNavigationBar();
		navigationBar.setHoldingsSelected();
	}

	public Object getBtnY() {
		return this.btnY;
	}

	class MsgDgML implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == btnN) {
				ThreadDiag.this.setVisible(false);
				// curdg2.dispose();
			} else if (e.getSource() == btnY) {
				// ��ת���������ҳ��
				ThreadDiag.this.setVisible(false);
				go2ArbitragePortfolio();
			}
		}

		public void mousePressed(MouseEvent e) {
		}

		public void mouseReleased(MouseEvent e) {
		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		JFrame frame = new JFrame();
		frame.setVisible(true);
		JButton b = new JButton("�򿪶Ի���");
		frame.setSize(100, 100);
		b.setPreferredSize(new Dimension(100, 100));

		frame.getContentPane().add(b);

		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ThreadDiag d = new ThreadDiag("���ش��������ᣡ\r\n����ǰ���������ҳ��鿴��", true);
				d.setVisible(true);
			}
		});
	}

}

// public class ThreadDiag extends Thread
// {
// private Thread currentThread = null;//ʵ�ʵ���ʱ����TestThread�������߳�
// private String messages = "";//��ʾ�����ʾ��Ϣ
// private JFrame parentFrame = null;//��ʾ��ĸ�����
// private JDialog clueDiag = null;// ��ʾ��
// private Dimension dimensions = Toolkit.getDefaultToolkit().getScreenSize();
// private int width = dimensions.width / 4, height = 60;
// private int left = 0, top = 0;
// public ThreadDiag(JFrame parentFrame, Thread currentThread, String messages)
// {
// this.parentFrame = parentFrame;
// this.currentThread = currentThread;
// this.messages = messages;
// initDiag();//��ʼ����ʾ��
// }
// protected void initDiag()
// {
// clueDiag = new JDialog(parentFrame,"��ʾ",true);
// JPanel testPanel = new JPanel();
// JLabel testLabel = new JLabel(messages);
// JButton btnY = new JButton("��"),btnN = new JButton("��");
// clueDiag.getContentPane().add(testPanel);
// testPanel.add(testLabel);
// testPanel.add(btnY);
// testPanel.add(btnN);
// }
// public void run()
// {
// //��ʾ��ʾ��
// int left = (dimensions.width - width)/2;
// int top = (dimensions.height - height)/2;
// clueDiag.setSize(new Dimension(width,height));
// clueDiag.setLocation(left, top);
// clueDiag.show();
// //JOptionPane.showMessageDialog(null, "���ش��������ᣡ����ǰ���������ҳ���µ���");
// }
// public JDialog getDiag(){
// return clueDiag;
// }
// };
