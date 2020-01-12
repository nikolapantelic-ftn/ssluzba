package ssluzba.app.views;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class HelpFrame extends JFrame {

	private static final long serialVersionUID = -1170582025623428977L;
	protected JList<String> sadrzajList;
	protected String student, profesor, predmet;
	protected JTextPane pocetnaText;
	
	public HelpFrame() {
		this.setTitle("Help");
		this.setSize(800, 600);
		this.setLocationRelativeTo(MainFrame.getInstance());
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		initComponents();
	}
	
	private void initComponents() {
		initText();
		pocetnaText = new JTextPane();
		pocetnaText.setBorder(BorderFactory.createEtchedBorder());
		pocetnaText.setText("Dobro dosli!\n"
				+ "Listajte sadrzaj s leve strane.");
		
		String[] lista = { "Studenti", "Profesori", "Predmeti"};
		sadrzajList = new JList<String>(lista);
		sadrzajList.setPreferredSize(new Dimension(120, 0));
		sadrzajList.setBorder(BorderFactory.createBevelBorder(1));
		
		addListListener(sadrzajList);
		
		this.add(sadrzajList, BorderLayout.WEST);
		this.add(pocetnaText, BorderLayout.CENTER);
	}

	private void addListListener(JList<String> list) {
		list.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				String s = list.getSelectedValue();
				switch(s) {
				case "Studenti":
					HelpFrame.this.pocetnaText.setText(HelpFrame.this.student);
					break;
				case "Profesori":
					HelpFrame.this.pocetnaText.setText(HelpFrame.this.profesor);
					break;
				case "Predmeti":
					HelpFrame.this.pocetnaText.setText(HelpFrame.this.predmet);
					break;
				}
			}
		});
	}
	
	private void initText() {
		student = "Kada se odabere tab Studenti, prikazuje se tabela unetih studenata.\n"
				+ "Iz trake sa menijima, pritiskom na File -> New, prikazuje se dijalog za dodavanje novog studenta."
				+ "Isto se moze uciniti pritiskom na dugme 'Dodaj studenta' u traci sa alatkama.\n"
				+ "Odabirom postojeceg studenta, zatim pritiskom na Edit -> Edit, prikazuje se dijalog za izmenu postojeceg studenta."
				+ "Isto se moze uciniti pritiskom na dugme 'Izmeni studenta' u traci sa alatkama.\n"
				+ "Student se moze obrisati odabirom studenta, zatim pritiskom na Edit -> Delete\n ili pritiskom na 'kanta'"
				+ "dugme u traci sa alatkama.\n"
				+ "Aplikacija omogucuje i kombinovanu pretragu studenata.\n"
				+ "Upisom teksta za pretragu u formatu 'Ime kolone1: Vrednost; Ime kolone2:Vrednost...' zatim pritiskom na dugme"
				+ "pretrage, u tabeli se pojavljuju samo studenti koji zadovoljavaju kriterijum pretrage.\n"
				+ "Za sortiranje tabele studenata, potrebno je kliknuti na ime kolone u tabeli po kojoj ce biti izvrseno sortiranje.\n\n"
				+ "Studente je moguce dodati na postojeci predmet, ukoliko studiraju na odgovarajucoj godini.\n"
				+ "Pritiskom na tab Predmeti, zatim odabirom predmeta i pritiskom na dugme 'Dodaj studenta na predmet',"
				+ "prikazuje se dijalog za dodavanje studenta na predmet.\n"
				+ "Pritiskom na polje studenata nekog predmeta, moguce je listati studente na tom predmetu i brisanje istih.\n"
				;
		profesor = "Kada se odabere tab Profesor, prikazuje se tabela unetih profesora.\n"
				+ "Iz trake sa menijima, pritiskom na File -> New, prikazuje se dijalog za dodavanje novog profesora."
				+ "Isto se moze uciniti pritiskom na dugme 'Dodaj profesora' u traci sa alatkama.\n"
				+ "Odabirom postojeceg profesora, zatim pritiskom na Edit -> Edit, prikazuje se dijalog za izmenu postojeceg profesora."
				+ "Isto se moze uciniti pritiskom na dugme 'Izmeni profesora' u traci sa alatkama.\n";
		predmet = "Predmet";
	}
	

}
