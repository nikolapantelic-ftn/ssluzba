package ssluzba.app.views;

import java.awt.Color;
import java.awt.Component;
import java.time.LocalDate;
import java.util.Comparator;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import ssluzba.app.BazaStudenata;

public class StudentJTable extends JTable {

	private static final long serialVersionUID = 6822871121891981732L;

	private static StudentJTable instance = null;

	public static StudentJTable getInstance() {
		if (instance == null) {
			instance = new StudentJTable();
		}
		return instance;
	}

	public StudentJTable() {
		this.setRowSelectionAllowed(true);
		this.setColumnSelectionAllowed(true);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setModel(new AbstractTableModelStudent());
		this.getTableHeader().setReorderingAllowed(false);

		TableRowSorter<TableModel> sorter = new TableRowSorter<>(this.getModel());
		sorter.setComparator(0, new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				String regex = "-";
				String[] substring1 = s1.split(regex);
				String[] substring2 = s2.split(regex);
				int result;
				if ((result = substring1[0].compareTo(substring2[0])) != 0)
					return result;
				else if ((result = substring2[2].compareTo(substring1[2])) != 0)
					return result;
				else
					return Integer.parseInt(substring1[1]) - Integer.parseInt(substring2[1]);
			}
		});
		Comparator<String> dateComparator = new Comparator<String>() {

			@Override
			public int compare(String s1, String s2) {
				LocalDate date1 = LocalDate.parse(s1, BazaStudenata.getInstance().getDateFormatter());
				LocalDate date2 = LocalDate.parse(s2, BazaStudenata.getInstance().getDateFormatter());
				return date1.compareTo(date2);
			}
			
		};
		sorter.setComparator(3, dateComparator);
		sorter.setComparator(4, dateComparator);
		sorter.setComparator(7, new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				if(Double.parseDouble(s1) > Double.parseDouble(s2))
					return 1;
				else if(Double.parseDouble(s1) < Double.parseDouble(s2))
					return -1;
				else
					return 0;
			}
		});
		// U specifikaciji je nejasno da li treba omoguciti sortiranje po godini studija
		// sorter.setSortable(5, false);
		this.setRowSorter(sorter);

	}

	public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
		Component c = super.prepareRenderer(renderer, row, column);
		if (isRowSelected(row)) {
			c.setBackground(Color.LIGHT_GRAY);
		} else {
			c.setBackground(Color.WHITE);
		}
		return c;
	}

	public void azurirajPrikaz() {
		AbstractTableModelStudent model = (AbstractTableModelStudent) this.getModel();
		model.fireTableDataChanged();
		validate();
	}

}
