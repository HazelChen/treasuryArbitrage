package edu.nju.treasuryArbitrage.news;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextField;



public class dateInTextField extends JTextField{
	private dateInTextField dateChooser = this;
	private textKeySL keyl = new textKeySL();
	private textMSL mousel = new textMSL();
	
	public dateInTextField() {
		  super(8);
		  dateChooser.setText("年/月/日");//("年/月/日");
		  dateChooser.addKeyListener(keyl);
		  dateChooser.addMouseListener(mousel);
		 }
	
	  class textMSL implements MouseListener{
		 private String Text;
		 private int sindex1 , sindex2;
		

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO 自动生成的方法存根
							Text = dateChooser.getText();
							sindex1 = Text.indexOf('/');
							sindex2 = Text.indexOf('/',Text.indexOf('/')+1);
							
							if(dateChooser.getCaretPosition() <= sindex1)
							{
								dateChooser.select(0 , sindex1);
								keyl.yf = false;
								keyl.mf = false;
								keyl.mcount = 1;
								keyl.dcount = 1;
							}
							else if(dateChooser.getCaretPosition() <= sindex2){
								dateChooser.select(sindex1 + 1 , sindex2);
								keyl.mf = false;
								keyl.yf = false;
								keyl.mcount = 1;
								keyl.dcount = 1;
							}
							else{
								dateChooser.select(sindex2 + 1 , Text.length());
							}
							
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO 自动生成的方法存根
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO 自动生成的方法存根
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO 自动生成的方法存根
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO 自动生成的方法存根
				
			}
		}
	  class textKeySL implements KeyListener{
		  private String Text,preText,y,m,d,py,pm,pd;
		  private int sindex1 , sindex2 ,ps1 , ps2;
		  private int pos;
		  public boolean yf = false,mf = false,df = false;
		  int mcount = 1,dcount = 1;
		
		@Override
		public void keyPressed(KeyEvent e) {
			// TODO 自动生成的方法存根
			int t = e.getKeyCode();
			pos = dateChooser.getCaretPosition();
			preText = Text;
			ps1 = sindex1;
			ps2 = sindex2;
			py = y; pm = m; pd = d;
			Text = dateChooser.getText();
			sindex1 = Text.indexOf('/');
			sindex2 = Text.indexOf('/',Text.indexOf('/')+1);
			y = Text.substring(0,sindex1);
			m = Text.substring(sindex1 + 1,sindex2);
			d = Text.substring(sindex2 + 1,Text.length());
			
	        if(t >= '0' && t <= '9'){
	    		// TODO 自动生成的方法存根
	    		m.compareTo("09");
	    		m.compareTo("12");
	    		d.compareTo("31");
	    		
	    		if(pos <= sindex1){
					if(!yf){yf = true;mf = false;df = false;}
					else {
						dateChooser.select(pos, pos);
		    			if(y.length() >= 4){
		    				sindex2 = Text.indexOf('/',Text.indexOf('/')+1);
		    				dateChooser.setCaretPosition(sindex2);
		    				dateChooser.select(sindex1 + 1 , sindex2);
		        			dateChooser.replaceSelection("0");
		        			mf = true;yf = false;
		    			}
	    			}
	    		}
	    		else if(pos <= sindex2){
	    			if(!mf){
	    				mf = true;mcount = 2;yf = false;df = false;
	    				dateChooser.replaceSelection("0");
	    			}
	    			else if(mcount == 2){
		    				if((m = m.substring(1) + (char)t ).compareTo("12") > 0){
			    				dateChooser.select(sindex1 + 1 , sindex2);
			        			dateChooser.replaceSelection("0");
		    				}
		    				else{
		    					dateChooser.select(sindex1 + 1 , sindex2);
			        			dateChooser.replaceSelection(m.substring(0,1));
		    				}	
		    			}
	    			//dateChooser.replaceSelection(preText.substring(0,sindex1 - 1));
	    		}
	    		else{
	    			mf = false;yf = false;
	    			if(!df){df = true;dcount = 2;yf = false;dateChooser.replaceSelection("0");}
	    			else if(dcount == 2){
		    				if((d = d.substring(1) + (char)t ).compareTo("31") > 0){
			    				dateChooser.select(sindex2 + 1 , Text.length());
			        			dateChooser.replaceSelection("0");
		    				}
		    				else{
		    					dateChooser.select(sindex2 + 1 , Text.length());
			        			dateChooser.replaceSelection(d.substring(0,1));
		    				}	
		    			}
	    		}
	    	}
	        else {
	        	//dateChooser.setText(dateChooser.getText().replaceAll("^[0-9]", ""));
	        }

		}
		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO 自动生成的方法存根
			Text = dateChooser.getText();
			sindex1 = Text.indexOf('/');
			sindex2 = Text.indexOf('/',Text.indexOf('/')+1);
			if(yf){dateChooser.select(0 , sindex1);}
			else if(mf)	{dateChooser.select(sindex1 + 1 , sindex2);	}
			else{dateChooser.select(sindex2 + 1 , Text.length());}
			//dateChooser.setText(dateChooser.getText().replaceAll("^[0-9]", ""));
		}
		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO 自动生成的方法存根		
			//dateChooser.select(0, sindex1 - 1);
			//dateChooser.setText(dateChooser.getText().replaceAll("^[0-9]", ""));
		}
		  
	  }
	  
}
