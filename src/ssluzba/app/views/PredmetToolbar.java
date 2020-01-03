package ssluzba.app.views;

import java.awt.Color;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

public class PredmetToolbar extends JToolBar {
	
	

	
		public PredmetToolbar() {
		super(SwingConstants.HORIZONTAL);
		setFloatable(false);
		setBackground(new Color(255, 255, 204));
		
		
		JButton btnNew = new JButton();
		btnNew.setToolTipText("Dodaj");
		btnNew.setIcon(new ImageIcon("images/add.png"));
		add(btnNew);

		addSeparator();
		
		JButton btnNewS = new JButton();
		btnNewS.setToolTipText("DodajS");
		btnNewS.setIcon(new ImageIcon("images/add.png"));
		add(btnNewS);

		addSeparator();

		JButton btnEdit = new JButton();
		btnEdit.setToolTipText("Izmeni");
		btnEdit.setIcon(new ImageIcon("images/pencil-icon.png"));
		add(btnEdit);

		addSeparator();

		JButton btnDel = new JButton();
		btnDel.setToolTipText("Obrisi");
		btnDel.setIcon(new ImageIcon("images/delete.png"));
		add(btnDel);
		addSeparator();
		
		
		add(Box.createHorizontalStrut(550));
		
		
	
		JTextField txtFind=new JTextField("Pretrazi" );
		add(txtFind);
		
		JButton btnFind=new JButton();
		btnFind.setIcon(new ImageIcon("images/find.png"));
		btnFind.setToolTipText("Pretrazi");
		add(btnFind);

	

	}

}



