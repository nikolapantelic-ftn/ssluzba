package ssluzba.app.views;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class TabbedPane extends JTabbedPane {

	private static final long serialVersionUID = -1935222497938439202L;

	public TabbedPane(JTable tabelaStudenata, JTable tabelaProfesora, JTable tabelaPredmeta) {
		// super();
		JScrollPane studentiPane = new JScrollPane(tabelaStudenata);
		JScrollPane scP = new JScrollPane(tabelaProfesora);
		this.addTab("Studenti", studentiPane);

		this.addTab("Profesori", scP);

		JScrollPane scrollPane = new JScrollPane(tabelaPredmeta);
		this.addTab("Predmeti", scrollPane);

		this.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				GlavniToolbar.getInstance().setPretragaStudenata(false);
				StudentJTable.getInstance().azurirajPrikaz();
			}
		});
	}

}
