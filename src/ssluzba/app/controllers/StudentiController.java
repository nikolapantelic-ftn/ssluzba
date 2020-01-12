package ssluzba.app.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import ssluzba.app.BazaPredmeta;
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

	/**
	 * Dodaje novog studenta u bazu studenata
	 * @throws Exception Sadrzi poruku o pogresno unetom parametru
	 */
	public void dodaj(String ime, String prezime, String adresaStanovanja, String kontaktTelefon, String email,
			String brIndeksa, String datumRodjenja, String prosecnaOcena, int godinaStudija, String datumUpisa, boolean samofinansiranje, boolean budzet) throws Exception {
		try {
			proveraUnosa(ime, prezime, adresaStanovanja, kontaktTelefon, email, brIndeksa, datumRodjenja, prosecnaOcena, godinaStudija, datumUpisa, samofinansiranje, budzet);
		} catch (Exception e) {
			throw e;
		}
		for(Student s: BazaStudenata.getInstance().getStudenti()) {
			if(s.getBrIndeksa().equals(brIndeksa)) throw new Exception("Student vec postoji");
		}
		Status status = samofinansiranje ? Status.S : Status.B;
		LocalDate datumRodj = LocalDate.parse(datumRodjenja, BazaStudenata.getInstance().getDateFormatter());
		LocalDate datumUp = LocalDate.parse((datumUpisa), BazaStudenata.getInstance().getDateFormatter());
		Double prosek = Double.parseDouble(prosecnaOcena);
		Student student = new Student(ime, prezime, adresaStanovanja, kontaktTelefon, email,
				brIndeksa, datumRodj, prosek, godinaStudija, datumUp, status);
		
		BazaStudenata.getInstance().dodajStudenta(student);
		StudentJTable.getInstance().azurirajPrikaz();
	}

	/**
	 * Brise trenutno oznacenog studenta u tabeli
	 * @param row
	 */
	public void izbrisi(int row) {
		if (row < 0)
			return;
		String brIndeksa = (String) StudentJTable.getInstance().getValueAt(row, 0);
		Student s = nadji(brIndeksa);
		if(s != null) {
			for (Predmet p: BazaPredmeta.getInstance().getPredmeti()) {
				p.getStudenti().remove(s);
			}
		}
		BazaStudenata.getInstance().getStudenti().remove(s);
		
		if (GlavniToolbar.getInstance().isPretragaStudenata()) {
			s = nadjiPretraga(brIndeksa);
			BazaStudenata.getInstance().getPretraga().remove(s);
		}
		StudentJTable.getInstance().azurirajPrikaz();

	}

	/**
	 * Menja trenutno oznacenog studenta u tabeli.
	 * @throws Exception Sadrzi poruku o pogresno unetom parametru
	 */
	public void izmeni(String ime, String prezime, String adresaStanovanja, String kontaktTelefon, String email,
			String brIndeksa, String datumRodjenja, String prosecnaOcena, int godinaStudija, String datumUpisa, boolean samofinansiranje, boolean budzet) throws Exception {
		try {
			proveraUnosa(ime, prezime, adresaStanovanja, kontaktTelefon, email, brIndeksa, datumRodjenja, prosecnaOcena, godinaStudija, datumUpisa, samofinansiranje, budzet);
		} catch (Exception e) {
			throw e;
		}
		
		//Ako je studentu promenjen indeks, proveri da li novoupisani indeks vec postoji
		Student student = nadjiIzabranog();
		if(!student.getBrIndeksa().equals(brIndeksa)) {
			for(Student s: BazaStudenata.getInstance().getStudenti()) {
				if(s.getBrIndeksa().equals(brIndeksa))
					throw new Exception("Student vec postoji");
			}
		}
		
		//Ako je studentu promenjen indeks ili godina studija, obrisi ga sa predmeta i obrisi njegove predmete
		if(!student.getBrIndeksa().equals(brIndeksa) || student.getGodinaStudija() != godinaStudija) {
			for (Predmet p: BazaPredmeta.getInstance().getPredmeti()) {
				p.getStudenti().remove(student);
			}
			student.getPredmeti().clear();
		}
		
		student.setIme(ime);
		student.setPrezime(prezime);
		student.setAdresaStanovanja(adresaStanovanja);
		student.setKontaktTelefon(kontaktTelefon);
		student.setEmail(email);
		student.setBrIndeksa(brIndeksa);
		student.setDatumRodjenja(LocalDate.parse(datumRodjenja, BazaStudenata.getInstance().getDateFormatter()));
		student.setProsecnaOcena(Double.parseDouble(prosecnaOcena));
		student.setGodinaStudija(godinaStudija);
		student.setDatumUpisa(LocalDate.parse((datumUpisa), BazaStudenata.getInstance().getDateFormatter()));
		Status status = samofinansiranje ? Status.S : Status.B;
		student.setStatus(status);
		StudentJTable.getInstance().azurirajPrikaz();

	}
	
	/**
	 * Proverava tacnosti unetih parametara za studenta
	 * @throws Exception Sadrzi poruku o pogresno unetom parametru
	 */
	public void proveraUnosa(String ime, String prezime, String adresaStanovanja, String kontaktTelefon, String email,
			String brIndeksa, String datumRodjenja, String prosecnaOcena, int godinaStudija, String datumUpisa, boolean samofinansiranje, boolean budzet) throws Exception {
		if (ime.isEmpty())
			throw new Exception("Ime ne sme biti prazno!");
		if (prezime.isEmpty())
			throw new Exception("Prezime ne sme biti prazno!");
		if (datumRodjenja.isEmpty())
			throw new Exception("Datum Rodjenja ne sme biti prazan!");
		try {
			DateTimeFormatter formatter = BazaStudenata.getInstance().getDateFormatter();
			LocalDate.parse(datumRodjenja, formatter);
		} catch (DateTimeParseException pe) {
			throw new Exception("Datum mora biti u formatu dd.mm.yyyy.");
		}
		if (kontaktTelefon.isEmpty())
			throw new Exception("Broj Telefona ne sme biti prazan!");
		if (email.isEmpty())
			throw new Exception("E-mail ne sme biti prazan!");
		if (adresaStanovanja.isEmpty())
			throw new Exception("Adresa Stanovanja ne sme biti prazana!");
		if(brIndeksa.isEmpty())
			throw new Exception("Broj Indeksa ne sme biti prazan!");
		if(!brIndeksa.matches("[a-z0-9]{2,3}-[0-9]{1,3}-[0-9]{4}"))
			throw new Exception("Broj indeksa mora biti u formatu 'xx-zz-yyyy' gde je 'xx' oznaka smera, 'zz' broj upisa i 'yyyy' godina upisa");
		try {
			double prosek = Double.parseDouble(prosecnaOcena);
			if((prosek <6 || prosek > 10) && godinaStudija != 1) {
				throw new Exception("Prosecna Ocena mora biti izmedju 6 i 10!");
			} else if(godinaStudija == 1 && prosek != 0) {
				throw new Exception("Prosecna Ocena mora biti 0 za studente prve godine!");
			}
		} catch (NumberFormatException e) {
			throw new Exception("Prosecna Ocena mora biti decimalan broj!");
		}
		try {
			DateTimeFormatter formatter = BazaStudenata.getInstance().getDateFormatter();
			LocalDate.parse(datumUpisa, formatter);
		} catch (DateTimeParseException pe) {
			throw new Exception("Datum mora biti u formatu dd.mm.yyyy.");
		}
		
		if(!(samofinansiranje || budzet))
			throw new Exception("Izaberite nacin finansiranja!");
		
		
	}
	/**
	 * Trazi studenta u bazi studenata
	 * @param brojIndeksa Trazeni indeks
	 * @return Objekat studenta iz baze studenata ukoliko je nadjen
	 */
	public Student nadji(String brojIndeksa) {
		for (Student s : BazaStudenata.getInstance().getStudenti()) {
			if (s.getBrIndeksa().equals(brojIndeksa))
				return s;
		}
		return null;
	}
	
	/**
	 * Trazi studenta u trenutnoj pretrazi studenata
	 * @param brojIndeksa Trazeni indeks
	 * @return Objekat studenta iz pretrage
	 */
	public Student nadjiPretraga(String brojIndeksa) {
		for (Student s : BazaStudenata.getInstance().getPretraga()) {
			if (s.getBrIndeksa().equals(brojIndeksa))
				return s;
		}
		return null;
	}
	
	/**
	 * Trazi studenta koji je trenutno oznacen u tabeli
	 * @return Objekat studenta iz baze studenata
	 */
	public Student nadjiIzabranog() {
		int row = StudentJTable.getInstance().getSelectedRow();
		String brIndeksa = (String) StudentJTable.getInstance().getValueAt(row, 0);
		return nadji(brIndeksa);
	}

	/**
	 * Dodaje studenta u listu studenata nekog predmeta
	 * @param brojIndeksa Indeks studenta koji se dodaje
	 * @param predmet Objekat predmeta na koji se dodaje student
	 * @return Kod greske : 0 - nema greske, 1 - student je vec u listi, 2 - student nije odgovarajuca godina,
	 * 3 - student sa datim indeksom ne postoji
	 */
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
					s.getPredmeti().add(predmet);
					errorCode = 0;
				}
			}
		}
		return errorCode;
	}

	/**
	 * Pretvara listu studenata u niz njihovih indeksa
	 * @param arrayList Lista studenata
	 * @return Niz indeksa studenata
	 */
	public String[] getIndeksi(ArrayList<Student> arrayList) {
		ArrayList<String> indeksi = new ArrayList<String>();
		for (Student s : arrayList) {
			indeksi.add(s.getBrIndeksa());
		}
		String[] indeksiArray = indeksi.toArray(new String[indeksi.size()]);
		return indeksiArray;
	}

	/**
	 * Brise studenta sa odgovarajucim indeksom sa predmeta i 
	 * brise taj predmet iz liste predmeta koje student pohadja
	 * @param p
	 * @param brIndeksa
	 */
	public void izbrisiStudenta(Predmet p, String brIndeksa) {
		for(Student s: p.getStudenti()) {
			if(s.getBrIndeksa().equals(brIndeksa)) {
				p.getStudenti().remove(s);
				break;
			}
		}
		Student s = nadji(brIndeksa);
		if(s != null)
			s.getPredmeti().remove(p);
	}
	
	/**
	 * Trazi studente za uneti string pretrage i ubacuje ih u listu pretrage
	 * @param pretragaString Kriterijum pretrage
	 * @return Kod greske : 0 - nema greske, 1 - string pretrage je neispravan, 
	 * 2 - kolona iz stringa pretrage ne postoji
	 */
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
