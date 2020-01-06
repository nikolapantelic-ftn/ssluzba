package ssluzba.app.views;

import javax.swing.table.AbstractTableModel;

import ssluzba.app.BazaPredmeta;
import ssluzba.app.BazaProfesora;


public class AbstractTableModelProfesor extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5970304018855274513L;

	/**
	 * 
	 */
	
	


	public AbstractTableModelProfesor() {
		
		
	}

	@Override
	public int getColumnCount() {
		return BazaProfesora.getInstance().getColumnCount();
	}

	@Override
	public int getRowCount() {
		if(BazaProfesora.getInstance().getPretraga().isEmpty())
		return BazaProfesora.getInstance().getProfesori().size();
		return BazaProfesora.getInstance().getPretraga().size();
	}
	public String getColumnName(int column) {
	
		return BazaProfesora.getInstance().getColumnName(column);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return BazaProfesora.getInstance().getValueAt(rowIndex, columnIndex) ;
		
	}



}
