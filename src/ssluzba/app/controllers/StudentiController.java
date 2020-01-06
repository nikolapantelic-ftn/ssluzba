package ssluzba.app.controllers;

import java.time.LocalDate;
import java.util.ArrayList;

import ssluzba.app.BazaStudenata;
import ssluzba.app.Predmet;
import ssluzba.app.Status;
import ssluzba.app.Student;
import ssluzba.app.views.StudentJTable;

public class StudentiController {

	private static StudentiController instance = null;

	public static StudentiController getInstance() {
		if (instance == null) {
			instance = new StudentiController();
		}
		return instance;
	}

	private StudentiController() {
	}

	public void dodaj(String ime, String prezime, String adresaStanovanja, String kontaktTelefon, String email,
			String brIndeksa, String datumRodjenja, int godinaStudija, boolean samofinansiranje, boolean budzet) {
		Status status = samofinansiranje ? Status.S : Status.B;
		LocalDate datumRodj = LocalDate.parse(datumRodjenja);
		BazaStudenata.getInstance().dodajStudenta(new Student(ime, prezime, adresaStanovanja, kontaktTelefon, email,
				brIndeksa, datumRodj, godinaStudija, status));
		StudentJTable.getInstance().azurirajPrikaz();
	}

	public void izbrisi(int row) {
		if (row < 0)
			return;
		BazaStudenata.getInstance().deleteStudent(row);
		StudentJTable.getInstance().azurirajPrikaz();

	}

	public void izmeni(String ime, String prezime, String adresaStanovanja, String kontaktTelefon, String email,
			String brIndeksa, String datumRodjenja, int godinaStudija, boolean samofinansiranje, boolean budzet) {
		for (Student s : BazaStudenata.getInstance().getStudenti()) {
			if (s.getBrIndeksa().equals(brIndeksa)) {
				s.setIme(ime);
				s.setPrezime(prezime);
				s.setAdresaStanovanja(adresaStanovanja);
				s.setKontaktTelefon(kontaktTelefon);
				s.setEmail(email);
				s.setBrIndeksa(brIndeksa);
				s.setDatumRodjenja(LocalDate.parse(datumRodjenja));
				s.setGodinaStudija(godinaStudija);
				Status status = samofinansiranje ? Status.S : Status.B;
				s.setStatus(status);
			}
		}
		StudentJTable.getInstance().azurirajPrikaz();

	}

	public Student nadji(String brojIndeksa) {
		for (Student s : BazaStudenata.getInstance().getStudenti()) {
			if (s.getBrIndeksa().equals(brojIndeksa))
				return s;
		}
		return null;
	}

	public int dodajStudentaNaPredmet(String brojIndeksa, Predmet predmet) {
		int errorCode = 3;
		for (Student s : BazaStudenata.getInstance().getStudenti()) {
			if (s.getBrIndeksa().equals(brojIndeksa)) {
				if (predmet.getStudenti().contains(s))
					errorCode = 1;
				else if (predmet.getGodinaPredmeta() != s.getGodinaStudija())
					errorCode = 2;
				else {
					predmet.getStudenti().add(s);
					errorCode = 0;
				}
			}
		}
		return errorCode;
	}

	public String[] getIndeksi(ArrayList<Student> studenti) {
		ArrayList<String> indeksi = new ArrayList<String>();
		for (Student s : studenti) {
			indeksi.add(s.getBrIndeksa());
		}
		String[] indeksiArray = indeksi.toArray(new String[indeksi.size()]);
		return indeksiArray;
	}

	public void izbrisiStudenta(Predmet p, String brIndeksa) {
		for (Student s : p.getStudenti()) {
			if (brIndeksa.equals(s.getBrIndeksa())) {
				p.getStudenti().remove(s);
				return;
			}
		}
	}

	public int pretraziStudente(String pretragaString) {
		if (!pretragaString.matches("([a-zA-Z0-9 ]+:[a-zA-Z0-9]+;?)+"))
			return 1; // Ne poklapa se string
		String[] kriterijumi = pretragaString.split(";");
		for (String kriterijum : kriterijumi) {
			String[] deo = kriterijum.split(":");
			String kolona = deo[0];
			if (!BazaStudenata.getInstance().getKolone().contains(kolona)) {
				return 2; // ne sadrzi kolonu
			}
		}
		BazaStudenata.getInstance().getPretraga().clear();
		for (Student s : BazaStudenata.getInstance().getStudenti()) {
			boolean ubaci = true;
			for (String kriterijum : kriterijumi) {
				String[] deo = kriterijum.split(":");
				switch (deo[0]) {
				case "Broj Indeksa":
					if (!s.getBrIndeksa().equals(deo[1]))
						ubaci = false;
					break;
				case "Ime":
					if (!s.getIme().equals(deo[1]))
						ubaci = false;
					break;
				case "Prezime":
					if (!s.getPrezime().equals(deo[1]))
						ubaci = false;
					break;
				case "Datum rodjenja":
					if (!s.getDatumRodjenja().equals(LocalDate.parse(deo[1])))
						ubaci = false;
					break;
				case "Datum upisa":
					if (!s.getDatumUpisa().equals(LocalDate.parse(deo[1])))
						ubaci = false;
					break;
				case "Trenutna godina":
					if (s.getGodinaStudija() != Integer.parseInt(deo[1]))
						ubaci = false;
					break;
				case "Status":
					if (!s.getStatus().toString().equals(deo[1]))
						ubaci = false;
					break;
				case "Prosecna ocena":
					if (s.getProsecnaOcena() != Double.parseDouble(deo[1]))
						ubaci = false;
				}
			}
			if (ubaci) {
				BazaStudenata.getInstance().getPretraga().add(s);
			}
		}
		return 0;
	}

}
