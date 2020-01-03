package ssluzba.app.controllers;


import java.time.LocalDate;

import ssluzba.app.BazaStudenata;
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

	private StudentiController() {
		// TODO Auto-generated constructor stub
	}

	public void dodaj(String ime, String prezime, String adresaStanovanja, String kontaktTelefon, String email,
			String brIndeksa, String datumRodjenja, int godinaStudija, boolean samofinansiranje, boolean budzet) {
		Status status = samofinansiranje ? Status.S : Status.B;
		LocalDate datumRodj = LocalDate.parse(datumRodjenja);
		BazaStudenata.getInstance().dodajStudenta(new Student(ime, prezime, adresaStanovanja, kontaktTelefon, email, brIndeksa, datumRodj, godinaStudija, status));
		StudentJTable.getInstance().azurirajPrikaz();
	}
	
	

}
