package edu.nju.treasuryArbitrage.view.personalCenter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import edu.nju.treasuryArbitrage.controller.dataInterface.DataInterfaceFactory;
import edu.nju.treasuryArbitrage.controller.dataInterface.DataInterface;
import edu.nju.treasuryArbitrage.model.Message;

public class MessageCenter extends JPanel {

	private static final long serialVersionUID = 5622073443448011691L;

	private JPanel mainJPanel;

	private JLabel bxJLabel;
	private JLabel reward;
	private ImageIcon icon;
	private ImageIcon icon1;
	private ImageIcon icon2;
	private ImageIcon icon3;
	private JTable table;
	private JComboBox<Object> dbtype;
	private JScrollPane jScrollPane;
	private Object[][] cellData;
	private String[] messages;
	private JButton deleteButton;
	private DataInterface service = DataInterfaceFactory.getInstance().getDataInterfaceToServer();

	public MessageCenter() {
		this(600, 400);
	}

	public MessageCenter(int w, int h) {
		mainJPanel = new JPanel();
		init(w, h);
		setLayout(null);
		add(mainJPanel);
		mainJPanel.setBounds(0, 0, w, h);
	}

	private void init(final int w, final int h) {
		mainJPanel.setBackground(Color.BLACK);
		mainJPanel.setLayout(null);
		icon = new ImageIcon("image/icon.jpg");
		icon1 = new ImageIcon("image/icon2.jpg");
		icon2 = new ImageIcon("image/icon3.jpg");
		icon3 = new ImageIcon("image/icon4.jpg");
		bxJLabel = new JLabel(icon);
		dbtype = new JComboBox<>();
		dbtype.addItem("全选");
		dbtype.addItem("锟斤拷选");
		dbtype.addItem("未锟斤拷");
		dbtype.addItem("锟窖讹拷");
		dbtype.setSelectedIndex(1);;
		dbtype.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				int index = dbtype.getSelectedIndex();
				if (index == 0) {
					bxJLabel.setIcon(icon1);
					for (int i = 0; i < table.getRowCount(); i++) {
						table.setValueAt(icon3, i, 0);
					}
				} else if (index == 1) {
					bxJLabel.setIcon(icon);
					for (int i = 0; i < table.getRowCount(); i++) {
						table.setValueAt(icon2, i, 0);
					}
				} else if (index == 2) {
					for (int i = 0; i < table.getRowCount(); i++) {
						table.setValueAt(icon2, i, 0);
						if(table.getValueAt(i, 1).equals("锟斤拷")){
							table.setValueAt(icon3, i, 0);
						}
					}
				} else if (index == 3) {
					for (int i = 0; i < table.getRowCount(); i++) {
						table.setValueAt(icon2, i, 0);
						if(table.getValueAt(i, 1).equals("锟斤拷")){
							table.setValueAt(icon3, i, 0);
						}
					}
				}
			}
		});
		mainJPanel.add(dbtype);
		dbtype.setBounds(30, 20, 28, 25);
		mainJPanel.add(bxJLabel);
		bxJLabel.setBounds(0, 20, 30, 25);
		mainJPanel.setComponentZOrder(dbtype, 1);
		mainJPanel.setComponentZOrder(bxJLabel, 0);
		setData();
		setTable(w, h);
		deleteButton = new JButton(new ImageIcon("image/删锟斤拷.jpg"));
		mainJPanel.add(deleteButton);
		deleteButton.setBounds(80, 20, 40, 25);
		mainJPanel.setPreferredSize(new Dimension(600, 400));
		jScrollPane = new JScrollPane(table);
		jScrollPane.setBackground(Color.BLACK);
		jScrollPane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jScrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		mainJPanel.add(jScrollPane);
		if (table.getRowCount() * table.getRowHeight() + 35 < 400) {
			jScrollPane.setBounds(0, 45, w,
					table.getRowCount() * table.getRowHeight() + 35);
		} else {
			jScrollPane.setBounds(0, 45, w, h * 2 / 3);
		}
		bxJLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (bxJLabel.getIcon().equals(icon)) {
					bxJLabel.setIcon(icon1);
					for (int i = 0; i < table.getRowCount(); i++) {
						table.setValueAt(icon3, i, 0);
					}
				} else {
					bxJLabel.setIcon(icon);
					for (int i = 0; i < table.getRowCount(); i++) {
						table.setValueAt(icon2, i, 0);
					}
				}
			}
		});
		deleteButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				for (int i = 0; i < table.getRowCount(); i++) {
					if (table.getValueAt(i, 0).equals(icon3)) {
						service.DeleteMess(i);
					}
				}
				setData();
				mainJPanel.remove(jScrollPane);
				jScrollPane.remove(table);
				setTable(w, h);
				jScrollPane = new JScrollPane(table);
				jScrollPane.setBackground(Color.BLACK);
				jScrollPane
						.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				jScrollPane
						.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
				mainJPanel.add(jScrollPane);
				if (table.getRowCount() * table.getRowHeight() + 35 < 400) {
					jScrollPane.setBounds(0, 45, w,
							table.getRowCount() * table.getRowHeight() + 35);
				} else {
					jScrollPane.setBounds(0, 45, w, h * 2 / 3);
				}
				jScrollPane.repaint();
				mainJPanel.repaint();
			}
		});
		setReword();
	}
	private int getNoRead() {
		int result = 0;
		if(cellData.length>0){
			for (int i = 0; i < cellData.length; i++) {
				if (cellData[i][1].equals("锟斤拷")) {
					result++;
				}
			}
		}
		return result;
	}

	private void setReword() {
		reward = new JLabel("锟斤拷" + getNoRead() + "锟斤拷未锟斤拷锟斤拷息");
		reward.setForeground(Color.WHITE);
		mainJPanel.add(reward);
		reward.setBounds(150, 20, 100, 25);
	}

	private void setData() {
		ArrayList<Message> messageList = service.getMessList();
		cellData = new Object[messageList.size()][5];
		messages = new String[messageList.size()];
		for(int i = 0 ; i<messageList.size();i++){
			cellData[i][0]=icon2;
			if(messageList.get(i).getRead()){
				cellData[i][1]="锟斤拷";
			}else{
				cellData[i][1]="锟斤拷";
			}
			cellData[i][2]=String.valueOf(i+1);
			cellData[i][3]=messageList.get(i).getInfo();
			cellData[i][4]=messageList.get(i).getTime();
			messages[i] = messageList.get(i).getInfo();
		}
	}

	

	private void setTable(int w, int h) {
		String[] columnNames = { "", "未锟斤拷", "锟斤拷锟�", "锟斤拷锟斤拷", "时锟斤拷" };
		DefaultTableModel myTableModel = new MYModel(cellData, columnNames) {
			private static final long serialVersionUID = 4811840115068048761L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table = new JTable(myTableModel);
		DefaultTableCellRenderer render = new DefaultTableCellRenderer() {
			private static final long serialVersionUID = -2049950775340871250L;
			@Override
			public Component getTableCellRendererComponent(JTable table,
					Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {
				super.getTableCellRendererComponent(table, value, isSelected,
						hasFocus, row, column);
				setHorizontalAlignment(SwingConstants.CENTER);
				setBackground(Color.BLACK);
				setForeground(Color.WHITE);
				return this;
			}
		};
		table.getTableHeader().setDefaultRenderer(render);
		table.setDefaultRenderer(Object.class, render);
		table.setRowHeight(30);
		table.setBackground(Color.BLACK);
		table.setGridColor(Color.BLACK);
		table.getTableHeader().setBackground(Color.BLACK);
		table.getTableHeader().setForeground(Color.WHITE);
		table.getTableHeader().setBorder(null);
		table.setShowGrid(false);
		TableColumn column1 = table.getColumnModel().getColumn(0);
		column1.setPreferredWidth(30 * w / 600);
		column1.setMaxWidth(30 * w / 600);
		column1.setMinWidth(30 * w / 600);
		TableColumn column2 = table.getColumnModel().getColumn(1);
		column2.setPreferredWidth(50 * w / 600);
		column2.setMaxWidth(50 * w / 600);
		column2.setMinWidth(50 * w / 600);
		TableColumn column3 = table.getColumnModel().getColumn(2);
		column3.setPreferredWidth(50 * w / 600);
		column3.setMaxWidth(50 * w / 600);
		column3.setMinWidth(50 * w / 600);
		TableColumn column4 = table.getColumnModel().getColumn(3);
		column4.setPreferredWidth(250 * w / 600);
		column4.setMaxWidth(250 * w / 600);
		column4.setMinWidth(250 * w / 600);
		TableColumn column5 = table.getColumnModel().getColumn(4);
		column5.setPreferredWidth(220 * w / 600);
		column5.setMaxWidth(220 * w / 600);
		column5.setMinWidth(220 * w / 600);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row;
				int column;
				row = table.getSelectedRow();
				column = table.getSelectedColumn();
				if (row >= 0) {
					if (column == 0) {
						if (table.getValueAt(row, column).equals(icon2)) {
							table.setValueAt(icon3, row, column);
						} else {
							table.setValueAt(icon2, row, column);
						}
					} else {
						showMessage(row);
					}
				}
			}
		});
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setBounds(0, 45, w, h / 2);
		table.getTableHeader().setResizingAllowed(true);
		table.getTableHeader().setPreferredSize(new Dimension(5, 35));
		table.getTableHeader().setReorderingAllowed(false);
	}

	private void showMessage(final int row) {
		remove(mainJPanel);
		final JPanel jPanel = new JPanel();
		JLabel jLabel = new JLabel(messages[row]+" , 前锟斤拷锟介看", JLabel.CENTER);
		jLabel.setFont(new Font("微锟斤拷锟脚猴拷",1,30));
		JButton cancel = new JButton("取锟斤拷");
		jPanel.setLayout(null);
		jPanel.add(cancel);
		jPanel.setBounds(5, 5, getWidth() - 10, getHeight() - 10);
		jPanel.add(jLabel, BorderLayout.CENTER);
		jPanel.setBackground(Color.GRAY);
		jLabel.setBounds(30, 35 * jPanel.getHeight() / 100,
				jPanel.getWidth() - 60, 30);
		cancel.setBounds(jPanel.getWidth() - 65, jPanel.getHeight() - 50, 60,
				30);
		jPanel.repaint();
		add(jPanel);
		repaint();
		cancel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				remove(jPanel);
				add(mainJPanel);
				mainJPanel.setBounds(0, 0, getWidth(), getHeight());
				table.setValueAt("锟斤拷", row, 1);
				cellData[row][1] = "锟斤拷";
				service.ReadMess(row);
				mainJPanel.remove(reward);
				setReword();
				repaint();
			}
		});
	}

	public static void main(String[] args) {
		JFrame jFrame = new JFrame();
		MessageCenter jPanel = new MessageCenter(400, 300);
		jFrame.add(jPanel);
		jFrame.setSize(400, 300);
		jFrame.setVisible(true);
		jFrame.repaint();
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}

class MYModel extends DefaultTableModel {
	private static final long serialVersionUID = 4752491024931735237L;

	public MYModel(Vector<?> cells, Vector<?> columnNames) {
		super(cells, columnNames);
	}

	public MYModel(Object[][] cellData, String[] columnNames) {
		super(cellData, columnNames);
	}

	@Override
	public Class<?> getColumnClass(int col) {
		Vector<?> v = (Vector<?>) dataVector.elementAt(0);
		if (v.elementAt(col) == null) {
			return "".getClass();
		} else {
			return v.elementAt(col).getClass();
		}
	}

}
