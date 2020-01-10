package ssluzba.app.views;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;

import ssluzba.app.BazaPredmeta;

public class ProfesorJTable extends JTable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6783986932397577032L;
	
	private static ProfesorJTable instance=null;
	public static ProfesorJTable getInstance() {
		if(instance==null) {
		instance=new ProfesorJTable();
	}
	return instance;
}

	
	
	public ProfesorJTable() {
		this.setRowSelectionAllowed(true);
		this.setColumnSelectionAllowed(true);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setModel(new AbstractTableModelProfesor());
		this.setAutoCreateRowSorter(true);
		addCellClickListener(this);
	}
	
	public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
		Component c = super.prepareRenderer(renderer, row, column);
		// selektovani red ce imati drugaciju boju od ostalih
		if (isRowSelected(row)) {
			c.setBackground(Color.LIGHT_GRAY);
		} else {
			c.setBackground(Color.WHITE);
		}
		return c;
	}
	public void azurirajPrikaz() {
		
		AbstractTableModelProfesor model = (AbstractTableModelProfesor) this.getModel();
		model.fireTableDataChanged();
		validate();
	}
	private void addCellClickListener(JTable table) {
		table.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {
				int row = ProfesorJTable.this.rowAtPoint(e.getPoint());
				int column = ProfesorJTable.this.columnAtPoint(e.getPoint());
				if(column == 10) {
					JDialog dialog = new PredmetiProfesoraDialog();
					dialog.setVisible(true);
				}
				
			}
		});
	}
}