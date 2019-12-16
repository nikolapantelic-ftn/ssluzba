package ssluzba.app.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = -7911276033183096362L;

	public MainFrame() throws HeadlessException {
		Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
		setSize((int)(screenDimension.width*0.75), (int)(screenDimension.height*0.75));
		setLocationRelativeTo(null);
		this.setJMenuBar(new MenuBar());
		this.add(new StatusBar(), BorderLayout.SOUTH);
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
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
