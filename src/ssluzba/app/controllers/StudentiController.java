package ssluzba.app.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import ssluzba.app.BazaStudenata;
import ssluzba.app.Predmet;
import ssluzba.app.Status;
import ssluzba.app.Student;
import ssluzba.app.views.GlavniToolbar;
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
			String brIndeksa, String datumRodjenja, int godinaStudija, boolean samofinansiranje, boolean budzet) throws Exception {
		try {
			proveraUnosa(ime, prezime, adresaStanovanja, kontaktTelefon, email, brIndeksa, datumRodjenja, godinaStudija, samofinansiranje, budzet);
		} catch (Exception e) {
			throw e;
		}
		for(Student s: BazaStudenata.getInstance().getStudenti()) {
			if(s.getBrIndeksa().equals(brIndeksa)) throw new Exception("Student vec postoji");
		}
		Status status = samofinansiranje ? Status.S : Status.B;
		LocalDate datumRodj = LocalDate.parse(datumRodjenja, BazaStudenata.getInstance().getDateFormatter());
		Student student = new Student(ime, prezime, adresaStanovanja, kontaktTelefon, email,
				brIndeksa, datumRodj, godinaStudija, status);
		
		BazaStudenata.getInstance().dodajStudenta(student);
		StudentJTable.getInstance().azurirajPrikaz();
	}

	public void izbrisi(int row) {
		if (row < 0)
			return;
		String brIndeksa = (String) StudentJTable.getInstance().getValueAt(row, 0);
		List<Student> studenti = BazaStudenata.getInstance().getStudenti();
		for (Student s: studenti) {
			if (s.getBrIndeksa().equals(brIndeksa)) {
				studenti.remove(s);
				break;
			}
		}
		if (GlavniToolbar.getInstance().isPretragaStudenata()) {
			List<Student> pretraga = BazaStudenata.getInstance().getPretraga();
			for(Student s: pretraga) {
				if (s.getBrIndeksa().equals(brIndeksa)) {
					pretraga.remove(s);
					break;
				}
			}
		}
		StudentJTable.getInstance().azurirajPrikaz();

	}

	public void izmeni(String ime, String prezime, String adresaStanovanja, String kontaktTelefon, String email,
			String brIndeksa, String datumRodjenja, int godinaStudija, boolean samofinansiranje, boolean budzet) throws Exception {
		try {
			proveraUnosa(ime, prezime, adresaStanovanja, kontaktTelefon, email, brIndeksa, datumRodjenja, godinaStudija, samofinansiranje, budzet);
		} catch (Exception e) {
			throw e;
		}
		Student student = BazaStudenata.getInstance().getRow(StudentJTable.getInstance().getSelectedRow());
		if(!student.getBrIndeksa().equals(brIndeksa)) {
			for(Student s: BazaStudenata.getInstance().getStudenti()) {
				if(s.getBrIndeksa().equals(brIndeksa))
					throw new Exception("Student vec postoji");
			}
		}
		student.setIme(ime);
		student.setPrezime(prezime);
		student.setAdresaStanovanja(adresaStanovanja);
		student.setKontaktTelefon(kontaktTelefon);
		student.setEmail(email);
		student.setBrIndeksa(brIndeksa);
		student.setDatumRodjenja(LocalDate.parse(datumRodjenja, BazaStudenata.getInstance().getDateFormatter()));
		student.setGodinaStudija(godinaStudija);
		Status status = samofinansiranje ? Status.S : Status.B;
		student.setStatus(status);
		StudentJTable.getInstance().azurirajPrikaz();

	}
	
	public void proveraUnosa(String ime, String prezime, String adresaStanovanja, String kontaktTelefon, String email,
			String brIndeksa, String datumRodjenja, int godinaStudija, boolean samofinansiranje, boolean budzet) throws Exception {
		if (ime.isEmpty())
			throw new Exception("Ime ne sme biti prazno!");
		if (prezime.isEmpty())
			throw new Exception("Prezime ne sme biti prazno!");
		if (datumRodjenja.isEmpty())
			throw new Exception("Datum rodjenja ne sme biti prazan!");
		try {
			DateTimeFormatter formatter = BazaStudenata.getInstance().getDateFormatter();
			LocalDate.parse(datumRodjenja, formatter);
		} catch (DateTimeParseException pe) {
			throw new Exception("Datum mora biti u formatu dd.mm.yyyy.");
		}
		if (kontaktTelefon.isEmpty())
			throw new Exception("Broj telefona ne sme biti prazan!");
		if (email.isEmpty())
			throw new Exception("E-mail ne sme biti prazan!");
		if (adresaStanovanja.isEmpty())
			throw new Exception("Adresa stanovanja ne sme biti prazana!");
		if(brIndeksa.isEmpty())
			throw new Exception("Broj Indeksa ne sme biti prazan!");
		if(!brIndeksa.matches("[a-z0-9]{2,3}-[0-9]{1,3}-[0-9]{4}"))
			throw new Exception("Broj indeksa mora biti u formatu 'xx-zz-yyyy' gde je 'xx' oznaka smera, 'zz' broj upisa i 'yyyy' godina upisa");
		if(!(samofinansiranje || budzet))
			throw new Exception("Izaberite nacin finansiranja!");
		
		
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
					s.getPredmeti().add(predmet.getSifra());
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
				if(p.getStudenti().remove(s)) System.out.println("obrisan student");
					break;
			}
		}
		for (Student s : BazaStudenata.getInstance().getStudenti()) {
			if (brIndeksa.equals(s.getBrIndeksa())) {
				s.getPredmeti().remove(p.getSifra());
				break;
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
