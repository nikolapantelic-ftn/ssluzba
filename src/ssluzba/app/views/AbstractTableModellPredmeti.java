package ssluzba.app.views;




import javax.swing.table.AbstractTableModel;


import ssluzba.app.BazaPredmeta;


public class AbstractTableModellPredmeti extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2108206132022830008L;
	


	public AbstractTableModellPredmeti() {
		
		
	}

	@Override
	public int getColumnCount() {
		return BazaPredmeta.getInstance().getColumnCount();
	}

	@Override
	public int getRowCount() {
		return BazaPredmeta.getInstance().getPredmeti().size();
	}
	public String getColumnName(int column) {
	
		return BazaPredmeta.getInstance().getColumnName(column);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return BazaPredmeta.getInstance().getValueAt(rowIndex, columnIndex) ;
		
	}



}

