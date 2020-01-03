package ssluzba.app.views;



import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

public class TabbedPane extends JTabbedPane{
 
	/**
	 * 
	 */
	private static JTable tabelaPredmeta;
	private static final long serialVersionUID = -1935222497938439202L;
	
	private static TabbedPane instance=null;
	

	public static TabbedPane getInstance() {
		if(instance==null)
			instance=new TabbedPane(tabelaPredmeta);
		return instance;
	}
	

	public TabbedPane(JTable tabelaPredmeta) {
		super();

		
		
		this.addTab("Studenti", null);
		
		 JLabel lab = new JLabel();
		 lab.setPreferredSize(new Dimension(100, 30));
		 lab.setText("Studenti");
		 this.setTabComponentAt(0, lab);
		 
		
		
		
		
		
		this.addTab("Profesori", lab);
		JScrollPane scrollPane=new JScrollPane(tabelaPredmeta);
		
		this.addTab("Predmeti", scrollPane);
		//MainFrame.getInstance().azurirajPrikaz();
		
		JLabel lab2 = new JLabel();
		lab2.setPreferredSize(new Dimension(100, 30));
		lab2.setText("Predmeti");
		this.setTabComponentAt(2, lab2);
		PredmetJTable.getInstance().azurirajPrikaz();
		
		
	}
	
	public JTable getTabelaPredmeta() {
		return this.tabelaPredmeta;
	}
}
