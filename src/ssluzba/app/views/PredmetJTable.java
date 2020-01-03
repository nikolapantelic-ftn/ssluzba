package ssluzba.app.views;

import java.awt.Color;
import java.awt.Component;
import java.nio.channels.SelectionKey;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;

import javafx.scene.control.SelectionModel;
import ssluzba.app.BazaPredmeta;

public class PredmetJTable extends JTable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8251893327400624728L;
	
	private static PredmetJTable instance=null;
	public static PredmetJTable getInstance() {
		if(instance==null) {
		instance=new PredmetJTable();
	}
	return instance;
}

	
	
	public PredmetJTable() {
		this.setRowSelectionAllowed(true);
		this.setColumnSelectionAllowed(true);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setModel(new AbstractTableModellPredmeti());
		
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
		
		AbstractTableModellPredmeti model = (AbstractTableModellPredmeti) this.getModel();
		model.fireTableDataChanged();
		validate();
	}
}
