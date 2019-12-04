package ssluzba.app.views;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class MenuBar extends JMenuBar implements ActionListener {
	
	private static final long serialVersionUID = 5446478703932520621L;
	

	public MenuBar() {
		initComponents();
	}


	private void initComponents() {
		JMenu menuNew = new JMenu("New");
		menuNew.setMnemonic(KeyEvent.VK_N);
		JMenu menuEdit = new JMenu("Edit");
		menuEdit.setMnemonic(KeyEvent.VK_E);
		JMenu menuHelp = new JMenu("Help");
		menuHelp.setMnemonic(KeyEvent.VK_H);
		JMenuItem menuItemNew = new JMenuItem("New");
		menuItemNew.setIcon(createImageIcon("new.png", ""));
		menuItemNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.ALT_MASK));	
		JMenuItem menuItemClose = new JMenuItem("Close");
		menuItemClose.setIcon(createImageIcon("close.png", ""));
		menuItemClose.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.ALT_MASK));
		JMenuItem menuItemEdit = new JMenuItem("Edit");
		menuItemEdit.setIcon(createImageIcon("edit.png", ""));
		menuItemEdit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.ALT_MASK));
		JMenuItem menuItemDelete = new JMenuItem("Delete");
		menuItemDelete.setIcon(createImageIcon("delete.png", ""));
		menuItemDelete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.ALT_MASK));
		JMenuItem menuItemHelp = new JMenuItem("Help");
		menuItemHelp.setIcon(createImageIcon("help.png", ""));
		menuItemHelp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.ALT_MASK));
		JMenuItem menuItemAbout = new JMenuItem("About");
		menuItemAbout.setIcon(createImageIcon("about.png", ""));
		menuItemAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.ALT_MASK));
		menuNew.add(menuItemNew);
		menuNew.add(menuItemClose);
		menuEdit.add(menuItemEdit);
		menuEdit.add(menuItemDelete);
		menuHelp.add(menuItemHelp);
		menuHelp.add(menuItemAbout);
		
		this.add(menuNew);
		this.add(menuEdit);
		this.add(menuHelp);
		
	}
	
	public ImageIcon createImageIcon(String path, String description) {
		java.net.URL imgURL = getClass().getResource("/ssluzba/resources/" + path);
		if (imgURL != null) {
			return new ImageIcon(imgURL, description);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

}
