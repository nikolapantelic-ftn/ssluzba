package ssluzba.app.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;




import javax.swing.JFrame;


public class MainFrame extends JFrame {

	private static final long serialVersionUID = -7911276033183096362L;

	private static MainFrame instance = null;
	
	public static MainFrame getInstance() {
		if (instance == null) {
			instance = new MainFrame();
		}
		return instance;
	}
	
	public TabbedPane tabbedpane;
	
	public MainFrame() throws HeadlessException {
		
		setTitle("Studentska sluzba");
		Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
		setSize((int)(screenDimension.width*0.75), (int)(screenDimension.height*0.75));
		setLocationRelativeTo(null);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		JTable tabelaPredmeta=PredmetJTable.getInstance();
		
		JTable tabelaProfesora=ProfesorJTable.getInstance();
	
		JTable tabelaStudenata = StudentJTable.getInstance();
		GlavniToolbar toolbar=new GlavniToolbar();
		add(toolbar,BorderLayout.NORTH);
		
		
		this.setJMenuBar(new MenuBar());
		this.add(new StatusBar(), BorderLayout.SOUTH);
		
		
		
		
		tabbedpane=new TabbedPane(tabelaStudenata, tabelaProfesora, tabelaPredmeta);
		add(tabbedpane,BorderLayout.CENTER);
		tabbedpane.addChangeListener(new ChangeListener() {
		
			@Override
			public void stateChanged(ChangeEvent e) {
				TabbedPane tabbedpane=(TabbedPane) e.getSource();
			toolbar.makeToolbar(tabbedpane.getSelectedIndex());
		
			
		}});
		
		this.setVisible(true);
		
		
	}
	
	

	public MainFrame(GraphicsConfiguration gc) {
		super(gc);
		// TODO Auto-generated constructor stub
	}

	public MainFrame(String title) throws HeadlessException {
		super(title);
		// TODO Auto-generated constructor stub
	}

	public MainFrame(String title, GraphicsConfiguration gc) {
		super(title, gc);
		// TODO Auto-generated constructor stub
	}

	

}

