package edu.nju.treasuryArbitrage.holdings;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;

import edu.nju.treasuryArbitrage.network.DataInterface;
import edu.nju.treasuryArbitrage.network.DataInterfacePile;
import edu.nju.treasuryArbitrage.resources.NumericalResources;

import vo.Repository;

public class ButtonEditor extends AbstractCellEditor implements TableCellEditor {
	/**
     * serialVersionUID
     */
    private static final long serialVersionUID = -6546334664166791132L;

    private JPanel panel,p2;

    private JButton button;
    private Repository repository;
    private SellDg selldialog;

    public ButtonEditor(Repository repo) {

        initButton();

        initPanel();
        repository = new Repository();
        repository = repo;        
        
        p2 = new JPanel();
        panel.add(p2);

        p2.setBounds(20,20, 
        		100, 30);
        p2.setBackground(Color.black);
        p2.add(button,"Center");
    }

    private void initButton() {
        button = new JButton();
        button.setFocusable(false);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                selldialog = new SellDg(repository);
                selldialog.setVisible(true);
                //stopped!!!!
                fireEditingStopped();

            }
        });

    }

    private void initPanel() {
        panel = new JPanel();

        panel.setLayout(null);
        panel.setBackground(Color.black);
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        
        button.setText(value == null ? "" : "平仓");

        return panel;
    }

    @Override
    public Object getCellEditorValue() {
        return "平仓";
    }
    
    class SellDg extends JDialog{
		
			private DataInterface di;
			private SellDg curdg = this;
			sellML listener;
			JLabel confirmL;
		    JTable table;
		    JPanel btnpanel,panel;
		    Object data[][] = {
		    		{"合约名称:",""},
		    		{"卖出手数:",""},
		    		{"交易类型:",""},
		    		{"套利方向:",""},
		    		{"合约价格:",""},
		    		{"投入保证金:",""},
		    		{"收益/损失:",""},
		    };
		    Object cnames[] = {"name","data"};
			private JButton btnY;
			private JButton btnC;
			private DefaultTableCellRenderer tcr;
			private SellSecMsgDg dg;
		    
		    SellDg(Repository repo){
		    	di = new DataInterfacePile();
		    	listener = new sellML();
		    	setUndecorated(true);
		    	setBackground(Color.white);
		    	setMaximumSize(new Dimension(280,350));
		    	setMinimumSize(new Dimension(280,350));
				setResizable(false);
				setModal(true);//
				btnY = new JButton("确认卖出");
				btnC = new JButton("取消");
				btnY.setPreferredSize(new Dimension(100,25));
				btnC.setPreferredSize(new Dimension(100,25));
				btnY.setSize(100,25);
				btnC.setSize(100,25);
				btnY.setFocusable(false);
				btnC.setFocusable(false);
				btnY.addMouseListener(listener);
				btnC.addMouseListener(listener);
				confirmL = new JLabel("请确认信息正确后点击确认");
				confirmL.setForeground(Color.red);
		        this.setLocation((NumericalResources.SCREEN_WIDTH - this.getWidth())/2,
		        		(NumericalResources.SCREEN_HEIGHT - this.getHeight())/2);
		        panel = new JPanel();
		        btnpanel = new JPanel();
		        btnpanel.setLayout(new BorderLayout());
		        JPanel p2 = new JPanel();
		        p2.setBackground(Color.white);
		        p2.add(confirmL,"Center");
		        btnpanel.add(p2,"North");
		        JPanel p3 = new JPanel();
		        JLabel inv = new JLabel("   ");
		        inv.setPreferredSize(new Dimension(15,20));
		        p3.add(btnY);
		        p3.add(inv);
		        p3.add(btnC);
		        p3.setBackground(Color.white);
		        btnpanel.add(p3,"Center");
		        JPanel p4 = new JPanel();
		        p4.setBackground(Color.white);
		        btnpanel.add(p4,"South");
		        btnpanel.setPreferredSize(new Dimension(280,76));
		        btnpanel.setBackground(Color.white);
		        table = new JTable(data,cnames){
		        	public boolean isCellEditable(int row, int column){return false;}//表格不允许被编辑
		        };
		        makeface(table);
		        panel.setLayout(new BorderLayout());
		        panel.setPreferredSize(new Dimension(280,350));
		        panel.setBackground(Color.white);
		        panel.add(table,BorderLayout.NORTH);
		        panel.add(btnpanel,BorderLayout.SOUTH);
		        add(panel);
		        this.pack();
				dg = new SellSecMsgDg();
				dg.setVisible(false);
		    }
		   
			private void makeface(JTable table) {
				table.setRowHeight(30);
				table.setRowMargin(0);
				table.setRowHeight(3, 60);
				table.setRowHeight(4, 60);
				Font tablef=new Font("宋体",Font.PLAIN,16);
				table.setFont(tablef);
				table.setGridColor(Color.white);
			       table.setSelectionBackground(Color.white);
			       table.setSelectionForeground(Color.black);
			 	   table.setIntercellSpacing(new Dimension(0,0));//修改单元格间隔，因此也将影响网格线的粗细。 
			        table.setFocusable(false);
			        table.setBackground(Color.white);
				tcr = new DefaultTableCellRenderer(){

					public Component getTableCellRendererComponent(JTable table,
		                Object value, boolean isSelected, boolean hasFocus,
		                int row, int column) {          	
		              
						if(column == 0){
				              setHorizontalAlignment(SwingConstants.RIGHT);
						}
						else{
				              setHorizontalAlignment(SwingConstants.LEFT);
				              }
						  setForeground(Color.BLACK);
		            	  setBackground(Color.white);
		              return super.getTableCellRendererComponent(table, value,
		                  isSelected, hasFocus, row, column);
		            }
		          };
		          for (int i = 0; i < table.getColumnCount(); i++) {
		        	  table.getColumn(table.getColumnName(i)).setCellRenderer(tcr);
			          }
				
			}

			class sellML implements MouseListener{

				@Override
				public void mouseClicked(MouseEvent e) {
					if(e.getSource() == btnY){
						curdg.setVisible(false);
						dg.setVisible(true);
						curdg.dispose();
					}
					else if(e.getSource() == btnC){
						curdg.setVisible(false);
						curdg.dispose();
					}
				}
				public void mousePressed(MouseEvent e) {}
				public void mouseReleased(MouseEvent e) {}
				public void mouseEntered(MouseEvent e) {}
				public void mouseExited(MouseEvent e) {}
		    }

	}
    

    class SellSecMsgDg extends JDialog{
		private static final long serialVersionUID = -5441568635168086793L;
		private SellSecMsgDg curdg = this;
		JLabel information;
		JPanel panel,con;
		SellSecMsgDgML listener;
		private JButton btnY;
		SellSecMsgDg(){
			setUndecorated(true);
	    	setBackground(Color.WHITE);
	    	setMaximumSize(new Dimension(383,150));
	    	setMinimumSize(new Dimension(383,150));
	        this.setLocation((NumericalResources.SCREEN_WIDTH - this.getWidth())/2,
	        		(NumericalResources.SCREEN_HEIGHT - this.getHeight())/2);
			setResizable(false);
			setModal(true);//
			btnY = new JButton("确认");
			btnY.setFocusable(false);
			information = new JLabel("下单成功！");
			Font titlef=new Font("宋体",Font.BOLD,24);
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
			p2.add(btnY,BorderLayout.NORTH);
			p2.setPreferredSize(new Dimension(383,60));
			panel.add(p2,"South");
			panel.setPreferredSize(new Dimension(383,150));
			con.add(panel,BorderLayout.CENTER);
			
			listener = new SellSecMsgDgML();
			btnY.addMouseListener(listener);
			add(con);
			this.pack();
		}
		
    	class SellSecMsgDgML implements MouseListener{

			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getSource() == btnY){
					//-------------------卖出对应合约-----
					//repository;
					//--------------------------------
					curdg.setVisible(false);
					curdg.dispose();
				}
				else {
				}
			}

			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
	    	
	    }
    }
}
