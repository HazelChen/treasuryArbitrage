package edu.nju.treasuryArbitrage.logic.liveUpdate;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.nju.treasuryArbitrage.ui.common.ScreenSize;
import edu.nju.treasuryArbitrage.ui.common.TreasuryFrame;

public class ThreadDiag extends JDialog{
	/**
	 * ��ʾ��
	 */
	private static final long serialVersionUID = -5251697653622360445L;
	private ThreadDiag curdg2 = this;
	JLabel information;
	JPanel panel,con;
	MsgDgML listener;
	private JButton btnY,btnN;
	MyButtonUI btnUI = new MyButtonUI();
	
	public ThreadDiag(){
		setUndecorated(true);
    	setBackground(Color.WHITE);
    	setMaximumSize(new Dimension(383,150));
    	setMinimumSize(new Dimension(383,150));
        this.setLocation((ScreenSize.WIDTH - this.getWidth())/2,
        		(ScreenSize.HEIGHT - this.getHeight())/2);
		setResizable(false);
		setModal(false);//
		
		btnY = new JButton("��");
		btnY.setFocusable(true);
		btnY.setBackground(Color.white);
        btnY.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        //setDefaultButton(btnY);
        btnY.requestFocus();
        btnY.requestDefaultFocus();
        btnY.requestFocus(true);
        btnY.requestFocusInWindow();
        btnY.setUI(btnUI);
        btnN = new JButton("��");
		btnN.setFocusable(true);
		btnN.setBackground(Color.white);
        btnN.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnN.setUI(btnUI);
        information = new JLabel("���ش��������ᣡ\r\n����ǰ���������ҳ��鿴��");
		Font titlef=new Font("����",Font.BOLD,15);
		information.setFont(titlef);
		con = new JPanel();
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JPanel p1 = new JPanel();
		JPanel invp = new JPanel();
		invp.setPreferredSize(new Dimension(383,30));
		p1.add(invp,BorderLayout.CENTER);
		p1.add(information,BorderLayout.SOUTH);
		panel.add(p1,"Center");
		JPanel p2 = new JPanel();
		p2.add(btnY,BorderLayout.WEST);
		p2.add(btnN,BorderLayout.EAST);
		p2.setPreferredSize(new Dimension(383,60));
		panel.add(p2,"South");
		panel.setPreferredSize(new Dimension(383,150));
		con.add(panel,BorderLayout.CENTER);
		
		listener = new MsgDgML();
		btnY.addMouseListener(listener);
		btnN.addMouseListener(listener);
		btnY.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				curdg2.setVisible(false);
				go2ArbitragePortfolio();//��ת���������
			}
			
		});
		btnY.addKeyListener(new KeyListener(){
			public void keyTyped(KeyEvent e) {}
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) { 
					if(btnY.isFocusOwner()){
						curdg2.setVisible(false);
						go2ArbitragePortfolio();//��ת���������
					}
				} 
			}
			public void keyReleased(KeyEvent e) {}
		});
		btnN.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				curdg2.setVisible(false);
			}
			
		});
		btnN.addKeyListener(new KeyListener(){
			public void keyTyped(KeyEvent e) {}
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) { 
					if(btnN.isFocusOwner()){
						curdg2.setVisible(false);
					}
				} 
			}
			public void keyReleased(KeyEvent e) {}
		});

		add(con);
		this.setVisible(false);
		this.pack();
	}
	public void go2ArbitragePortfolio()
	{
		//--------------------------------------------
		//----------------��Ӵ�����ת���������ҳ��-----------
		//--------------------------------------------
	}
	
	public Object getBtnY(){
		return this.btnY;
	}
	class MsgDgML implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getSource() == btnN){
				curdg2.setVisible(false);
				//curdg2.dispose();
			}
			else if(e.getSource() == btnY){
				//��ת���������ҳ��
				curdg2.setVisible(false);
				go2ArbitragePortfolio();
			}
		}

		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
    	
    }
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 
		JFrame frame = new JFrame();
		frame.setVisible(true);
		JButton b = new JButton("�򿪶Ի���");
		frame.getContentPane().setSize(100, 100);
		b.setPreferredSize(new Dimension(100,100));

		frame.getContentPane().add(b);
		b.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent e) {
				ThreadDiag d = new ThreadDiag(); 
				d.show();
			}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
		
		});
		b.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				ThreadDiag d = new ThreadDiag(); 
				d.show();
			}
		});
		
		//(new ThreadDiag(null,test,"��ʾ")).start();//�����ȴ���ʾ���߳�
	}
	
}

//public class ThreadDiag extends Thread
//{
//	private Thread currentThread = null;//ʵ�ʵ���ʱ����TestThread�������߳�
//	private String messages = "";//��ʾ�����ʾ��Ϣ
//	private JFrame parentFrame = null;//��ʾ��ĸ�����
//	private JDialog clueDiag = null;// ��ʾ��
//	private Dimension dimensions = Toolkit.getDefaultToolkit().getScreenSize();
//	private int width = dimensions.width / 4, height = 60;
//	private int left = 0, top = 0;
//	public ThreadDiag(JFrame parentFrame, Thread currentThread, String messages)
//	{
//		this.parentFrame = parentFrame;
//		this.currentThread = currentThread;
//		this.messages = messages;
//		initDiag();//��ʼ����ʾ��
//	}
//	  protected void initDiag()
//	{
//		clueDiag = new JDialog(parentFrame,"��ʾ",true);
//		JPanel testPanel = new JPanel();
//		JLabel testLabel = new JLabel(messages);
//		JButton btnY = new JButton("��"),btnN = new JButton("��");
//		clueDiag.getContentPane().add(testPanel);
//		testPanel.add(testLabel);
//		testPanel.add(btnY);
//		testPanel.add(btnN);
//	}
//    public void run()
//	{
//		//��ʾ��ʾ��
//		int left = (dimensions.width - width)/2;
//		int top = (dimensions.height - height)/2;
//		clueDiag.setSize(new Dimension(width,height));
//		clueDiag.setLocation(left, top);
//		clueDiag.show();
//		//JOptionPane.showMessageDialog(null, "���ش��������ᣡ����ǰ���������ҳ���µ���");
//	}
//    public JDialog getDiag(){
//    	return clueDiag;
//    }
//}; 
