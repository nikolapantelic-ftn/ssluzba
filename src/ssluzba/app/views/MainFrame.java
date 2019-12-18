package ssluzba.app.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

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

	private MainFrame() {
		Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
		setSize((int)(screenDimension.width*0.75), (int)(screenDimension.height*0.75));
		setLocationRelativeTo(null);
		this.setJMenuBar(new MenuBar());
		this.add(new StatusBar(), BorderLayout.SOUTH);
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

}
