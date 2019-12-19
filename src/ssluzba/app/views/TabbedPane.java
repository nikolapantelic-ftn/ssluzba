package ssluzba.app.views;



import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JTabbedPane;

public class TabbedPane extends JTabbedPane{
 
	/**
	 * 
	 */
	private static final long serialVersionUID = -1935222497938439202L;
	
	private static TabbedPane instance=null;
	

	public static TabbedPane getInstance() {
		if(instance==null)
			instance=new TabbedPane();
		return instance;
	}
	

	public TabbedPane() {
		super();

		
		
		this.addTab("Studenti", null);
		
		 JLabel lab = new JLabel();
		 lab.setPreferredSize(new Dimension(100, 30));
		 lab.setText("Studenti");
		 this.setTabComponentAt(0, lab);
		 
		
		this.addTab("Profesori", null);
		JLabel lab1 = new JLabel();
		lab1.setPreferredSize(new Dimension(100, 30));
		lab1.setText("Profesori");
		this.setTabComponentAt(1, lab1);
		
		
		this.addTab("Predmeti", null);
		
		JLabel lab2 = new JLabel();
		lab2.setPreferredSize(new Dimension(100, 30));
		lab2.setText("Predmeti");
		this.setTabComponentAt(2, lab2);
		
		
		
	}
	

}
