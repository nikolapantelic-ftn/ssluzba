package ssluzba.app.views;

import javax.swing.table.AbstractTableModel;

import ssluzba.app.BazaStudenata;

public class AbstractTableModelStudent extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2206154905643790704L;

	@Override
	public int getColumnCount() {
		return BazaStudenata.getInstance().getColumnCount();
	}

	@Override
	public int getRowCount() {
		return BazaStudenata.getInstance().getStudenti().size();
	}
	
	@Override
	public String getColumnName(int column) {
		
		return BazaStudenata.getInstance().getColumnName(column);
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		
		return BazaStudenata.getInstance().getValueAt(arg0, arg1);
	}

}
