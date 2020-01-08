package ssluzba.app.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import ssluzba.app.BazaPredmeta;
import ssluzba.app.controllers.PredmetiController;
import ssluzba.app.controllers.ProfesoriController;
import ssluzba.app.controllers.StudentiController;

public class MenuBar extends JMenuBar {

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
		JMenuItem menuItemNew = new JMenuItem("New", createImageIcon("new.png"));
		menuItemNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.ALT_MASK));
		menuItemNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JDialog dialog = null;
				switch (MainFrame.getInstance().tabbedpane.getSelectedIndex()) {
				case 0:
					dialog = new StudentDialog();
					break;
				case 1:
					dialog = new ProfesorDialog();
					break;
				case 2:
					dialog = new PredmetDialog();
					break;
				}
				dialog.setVisible(true);
			}
		});
		JMenuItem menuItemClose = new JMenuItem("Close", createImageIcon("close.png"));
		menuItemClose.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.ALT_MASK));
		JMenuItem menuItemEdit = new JMenuItem("Edit", createImageIcon("edit.png"));
		menuItemEdit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.ALT_MASK));
		menuItemEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JDialog dialog = null;
				int row;
				switch (MainFrame.getInstance().tabbedpane.getSelectedIndex()) {
				case 0:
					row = StudentJTable.getInstance().getSelectedRow();
					if (row < 0) {
						JOptionPane.showMessageDialog(null, "Niste izabrali studenta.");
						break;
					}
					dialog = new StudentEditDialog();
					dialog.setVisible(true);
					break;
				case 1:
					row = ProfesorJTable.getInstance().getSelectedRow();
					if (row < 0) {
						JOptionPane.showMessageDialog(null, "Niste izabrali profesora.");
						break;
					}
					dialog = new ProfesorEditDialog();
					dialog.setVisible(true);
					break;
				case 2:
					row = PredmetJTable.getInstance().getSelectedRow();
					if (row < 0) {
						JOptionPane.showMessageDialog(null, "Niste izabrali predmet.");
						break;
					}
					dialog = new PredmetEditDialog(
							BazaPredmeta.getInstance().getRow(PredmetJTable.getInstance().getSelectedRow()));
					dialog.setVisible(true);
					break;
				}

			}
		});
		JMenuItem menuItemDelete = new JMenuItem("Delete", createImageIcon("delete.png"));
		menuItemDelete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.ALT_MASK));
		menuItemDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int choice;
				int row;
				switch (MainFrame.getInstance().tabbedpane.getSelectedIndex()) {
				case 0:
					row = StudentJTable.getInstance().getSelectedRow();
					if (row < 0) {
						JOptionPane.showMessageDialog(null, "Niste izabrali studenta.");
						break;
					}
					choice = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da obrisete studenta?");
					if (choice == JOptionPane.YES_OPTION) {
						StudentiController.getInstance().izbrisi(row);
						JOptionPane.showMessageDialog(null, "Student obrisan!");
					} else {
						JOptionPane.showMessageDialog(null, "Otkazano");
					}
					break;
				case 1:
					row = ProfesorJTable.getInstance().getSelectedRow();
					if (row < 0) {
						JOptionPane.showMessageDialog(null, "Niste izabrali profesora.");
						break;
					}
					choice = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da obrisete profesora?");
					if (choice == JOptionPane.YES_OPTION) {
						ProfesoriController.getInstance().deleteProfesor(ProfesorJTable.getInstance().getSelectedRow());
						JOptionPane.showMessageDialog(null, "Profesor obrisan!");
					} else {
						JOptionPane.showMessageDialog(null, "Otkazano");
					}
					break;
				case 2:
					row = PredmetJTable.getInstance().getSelectedRow();
					if (row < 0) {
						JOptionPane.showMessageDialog(null, "Niste izabrali predmet.");
						break;
					}
					choice = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da obrisete predmet?");
					if (choice == JOptionPane.YES_OPTION) {
						PredmetiController.getInstance().izbrisi(row);
						JOptionPane.showMessageDialog(null, "Predmet obrisan!");
					} else {
						JOptionPane.showMessageDialog(null, "Otkazano");
					}
					break;
				}
			}
		});
		JMenuItem menuItemHelp = new JMenuItem("Help", createImageIcon("help.png"));
		menuItemHelp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.ALT_MASK));
		JMenuItem menuItemAbout = new JMenuItem("About", createImageIcon("about.png"));
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

	public ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = getClass().getResource("/ssluzba/resources/" + path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

}
