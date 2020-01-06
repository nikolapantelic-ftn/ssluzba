package ssluzba.app.views;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


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
		addCellClickListener(this);
		TableRowSorter<TableModel> sort=new TableRowSorter<>(this.getModel());
		sort.setSortable(4, false);
		sort.setSortable(5, false);
		this.setRowSorter(sort);
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
	
	private void addCellClickListener(JTable table) {
		table.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {
				int row = PredmetJTable.this.rowAtPoint(e.getPoint());
				int column = PredmetJTable.this.columnAtPoint(e.getPoint());
				if(column == 5) {
					StudentiNaPredmetuDialog dialog = new StudentiNaPredmetuDialog(BazaPredmeta.getInstance().getRow(row));
					dialog.setVisible(true);
				}
				if(column==4 && BazaPredmeta.getInstance().getRow(PredmetJTable.getInstance().getSelectedRow()).getProfesor()!=null) {
					JDialog jd=new ProfesorNaPredmetuDialog(BazaPredmeta.getInstance().getRow(getSelectedRow()).getProfesor());
					jd.setVisible(true);
				}
			}
		});
	}
}
