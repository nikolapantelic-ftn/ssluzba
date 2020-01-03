package ssluzba.app.views;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

public class TabbedPane extends JTabbedPane {

	/**
	 * 
	 */
	//private static JTable tabelaPredmeta;
	//private static JTable tabelaProfesora;
	private static final long serialVersionUID = -1935222497938439202L;

	

	public TabbedPane(JTable tabelaProfesora,JTable tabelaPredmeta) {
		//super();
		JLabel lab=new JLabel("bb");
		JScrollPane scP = new JScrollPane(tabelaProfesora);
		this.addTab("Studenti", lab);

		this.addTab("Profesori", scP);

		JScrollPane scrollPane = new JScrollPane(tabelaPredmeta);
		this.addTab("Predmeti", scrollPane);

	}

	
}
