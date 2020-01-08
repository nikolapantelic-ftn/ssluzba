package ssluzba.app.views;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;

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
}