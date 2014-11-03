package edu.nju.treasuryArbitrage.ui.holdings;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import edu.nju.treasuryArbitrage.factory.DataInterfaceFactory;
import edu.nju.treasuryArbitrage.model.Repository;
import edu.nju.treasuryArbitrage.ui.common.ScreenSize;
import edu.nju.treasuryArbitrage.ui.navigater.Navigater;

public class HoldingsChen extends Holdings {
	private static final long serialVersionUID = 6470810944009110491L;

	private static final Color BACKGROUND_COLOR = new Color(254, 254, 254);

	static ArrayList<Repository> info;
	private Object holdHeaderData[] = { "���������Ϣ", "����ʱ��", "��������", "Ͷ�뱣֤��",
			"��ʱ��ʧ/ӯ�����", "����"}, empOb[][] = { { "", "", "", "", "", "" },
			{ "", "", "", "", "", "" }, { "", "", "", "", "", "" },
			{ "", "", "", "", "", "" }, { "", "", "", "", "", "" },
			{ "", "", "", "", "", "" }, { "", "", "", "", "", "" } };// !!!![Important]!!!!!
	JScrollPane jsp1, jsp2;

	JButton refreshBtn, doBtn;
	JTable hTableHeader = new JTable();
	JTable hTable;
	Color lbg = new Color(217, 122, 91), rbtnbg = new Color(41, 128, 185);
	private static TableCellRenderer tcr22;
	int w;

	public HoldingsChen() {
		this.setBackground(BACKGROUND_COLOR);
		this.setLayout(new BorderLayout());

		JPanel centerPanel = initCenter();
		JPanel southPanel = initSouth();
		add(centerPanel, BorderLayout.CENTER);
		add(southPanel, BorderLayout.SOUTH);
	}

	private JPanel initSouth() {
		// TODO Auto-generated method stub
		return new JPanel();
	}

	private JPanel initCenter() {
		JPanel northPanel = new JPanel(new BorderLayout());
		northPanel.setBackground(BACKGROUND_COLOR);
		northPanel.setPreferredSize(new Dimension(ScreenSize.WIDTH,
				(int) (ScreenSize.HEIGHT / 10.0 * 7)));

		JLabel reposityPageLabel = new JLabel("�ֲ����", JLabel.LEFT);
		reposityPageLabel.setFont(new Font("΢���ź�", Font.BOLD, 16));
		reposityPageLabel.setForeground(new Color(247, 68, 97));
		reposityPageLabel.setPreferredSize(new Dimension(ScreenSize.WIDTH,
				(int) (ScreenSize.HEIGHT / 25.0)));

		doBtn = new JButton("ƽ��");
		doBtn.setFocusPainted(false);

		DefaultTableModel model = new DefaultTableModel(new Object[0][holdHeaderData.length], holdHeaderData) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		model.addRow(holdHeaderData);
		hTableHeader.setModel(model);
		makeFaceOfHeader(hTableHeader);

		JPanel headerPanel = new JPanel(new BorderLayout());
		headerPanel.setBackground(Navigater.BACKGROUND_COLOR);
		headerPanel.add(reposityPageLabel, BorderLayout.CENTER);
		headerPanel.add(hTableHeader, BorderLayout.SOUTH);
		northPanel.add(headerPanel, BorderLayout.NORTH);
//==========================================table header end====================

		info = DataInterfaceFactory.getInstance().getDataInterfaceToServer()
				.getRepoList();// �ӷ�������ȡ����
		hTable = new JTable(new DefaultTableModel() {
			private static final long serialVersionUID = -335420676543481799L;

			@Override
			public Object getValueAt(int row, int column) {
				return empOb[row][column];
			}

			@Override
			public void setValueAt(Object aValue, int row, int column) {
				empOb[row][column] = aValue;
				fireTableCellUpdated(row, column);
			}

			@Override
			public int getRowCount() {
				return info.size();
			}

			@Override
			public int getColumnCount() {
				return 6;
			}

			@Override
			public boolean isCellEditable(int row, int column) {
				if (column == 5) {
					return true;
				} else {
					return false;
				}
			}
		});
		hTable.getTableHeader().setPreferredSize(new Dimension(0, 0));
		hTable.getTableHeader().setVisible(false);
		makeFace2(hTable);
		hTable.setRowHeight(80);
		for (int i = 1; i < 5; i++) {
			hTable.getColumn(hTable.getColumnName(i)).setMinWidth(137);
			hTable.getColumn(hTable.getColumnName(i)).setMaxWidth(137);
		}
		hTable.getColumn(hTable.getColumnName(5)).setMinWidth(135);
		hTable.getColumn(hTable.getColumnName(5)).setMaxWidth(135);
		hTable.setFocusable(false);
		hTable.setBackground(Color.black);
		updateRepoList();
		hTable.addMouseMotionListener(new MouseMotionListener() {
			public void mouseDragged(MouseEvent e) {
				hTable.editCellAt(e.getY() / 80, 5);
			}

			public void mouseMoved(MouseEvent e) {
				hTable.editCellAt(e.getY() / 80, 5);
			}
		});

		jsp1 = new JScrollPane(hTable);
		jsp1.getViewport().setBackground(Color.black);
		jsp1.getVerticalScrollBar().setPreferredSize(new Dimension(13, 12));
		jsp1.getVerticalScrollBar().setMaximumSize(new Dimension(13, 12));
		jsp1.getVerticalScrollBar().setMinimumSize(new Dimension(13, 12));

		northPanel.add(jsp1, BorderLayout.CENTER);
		return northPanel;
	}

	public void updatePage() {
		// ���³ֲ����ҳ����ʾ
		updateFTable();
		updateRepoList();
	}

	public void updateRepoList() {
		info = DataInterfaceFactory.getInstance().getDataInterfaceToServer()
				.getRepoList();// �ӷ�������ȡ����
		if (info.size() > 0) {
			for (int j = 0; j < info.size(); j++) {
				Date dt = new Date(info.get(j).getTime());
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				String time = df.format(dt);
				hTable.setValueAt(time, j, 1);
				hTable.setValueAt(info.get(j).getCount(), j, 2);
				hTable.setValueAt(info.get(j).getGuarantee(), j, 3);
				hTable.setValueAt(info.get(j).getProfit(), j, 4);
			}
			hTable.getColumnModel().getColumn(5)
					.setCellEditor(new ButtonEditor(info));
			hTable.getColumnModel().getColumn(5)
					.setCellRenderer(new ButtonCellRenderer());
			hTable.getColumnModel().getColumn(0)
					.setCellEditor(new MyTableEditor(info));
			hTable.getColumnModel().getColumn(0)
					.setCellRenderer(new MyTableCellRenderer(info));//

		}
		hTable.repaint();
	}

	private void makeFace2(JTable table) {
		table.setRowHeight(30);
		table.setSelectionBackground(Color.black);
		table.setSelectionForeground(Color.white);
		tcr22 = new DefaultTableCellRenderer() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 2220633049102091416L;

			public Component getTableCellRendererComponent(JTable table,
					Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {

				setForeground(Color.white);
				setBackground(Color.black);
				setHorizontalAlignment(SwingConstants.CENTER);
				return super.getTableCellRendererComponent(table, value,
						isSelected, hasFocus, row, column);
			}
		};
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumn(table.getColumnName(i)).setCellRenderer(tcr22);
		}

	}

	public void updateFTable() {
	}

	private void makeFaceOfHeader(JTable table) {
		table.setRowHeight(30);
		table.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		DefaultTableCellRenderer tcr1 = new DefaultTableCellRenderer() {
			private static final long serialVersionUID = 2220633049102091416L;

			public Component getTableCellRendererComponent(JTable table,
					Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {
				if (row % 2 == 0)
					setBackground(BACKGROUND_COLOR); // ���������е�ɫ else
				if (row % 2 == 1)
					setBackground(new Color(196, 196, 196));
				setHorizontalAlignment(SwingConstants.CENTER);
				return super.getTableCellRendererComponent(table, value,
						isSelected, hasFocus, row, column);
			}
		};
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumn(table.getColumnName(i)).setCellRenderer(tcr1);
		}
	}

	public static void main(String[] args) {
		JFrame mw = new JFrame("chen");
		mw.setSize(ScreenSize.WIDTH, ScreenSize.HEIGHT);
		JPanel Holdings = new HoldingsChen();
		mw.getContentPane().add(Holdings);
		mw.setVisible(true);
		mw.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}