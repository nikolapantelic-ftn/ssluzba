package ssluzba.app.controllers;


import java.time.LocalDate;

import ssluzba.app.BazaStudenata;
import ssluzba.app.Predmet;
import ssluzba.app.Status;
import ssluzba.app.Student;
import ssluzba.app.views.StudentJTable;

public class StudentiController  {
	
	private static StudentiController instance = null;
	
	public static StudentiController getInstance() {
		if(instance==null) {
			instance=new StudentiController();
		}
		return instance;
	}

	private StudentiController() {}

	public void dodaj(String ime, String prezime, String adresaStanovanja, String kontaktTelefon, String email,
			String brIndeksa, String datumRodjenja, int godinaStudija, boolean samofinansiranje, boolean budzet) {
		Status status = samofinansiranje ? Status.S : Status.B;
		LocalDate datumRodj = LocalDate.parse(datumRodjenja);
		BazaStudenata.getInstance().dodajStudenta(new Student(ime, prezime, adresaStanovanja, kontaktTelefon, email, brIndeksa, datumRodj, godinaStudija, status));
		StudentJTable.getInstance().azurirajPrikaz();
	}

	public void izbrisi(int row) {
		if(row<0)
			return;
		BazaStudenata.getInstance().deleteStudent(row);
		StudentJTable.getInstance().azurirajPrikaz();
		
	}

	public void izmeni(String ime, String prezime, String adresaStanovanja, String kontaktTelefon, String email,
			String brIndeksa, String datumRodjenja, int godinaStudija, boolean samofinansiranje, boolean budzet) {
		for(Student s:BazaStudenata.getInstance().getStudenti()) {
			if(s.getBrIndeksa().equals(brIndeksa)) {
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
		for(Student s : BazaStudenata.getInstance().getStudenti()) {
			if(s.getBrIndeksa().equals(brojIndeksa))
				return s;
		}
		return null;
	}

	public void dodajStudentaNaPredmet(String brojIndeksa, Predmet predmet) {
		for(Student s : BazaStudenata.getInstance().getStudenti()) {
			if(s.getBrIndeksa().equals(brojIndeksa)) {
				predmet.getStudenti().add(s);
			}	
		}
	}
	
	

}
