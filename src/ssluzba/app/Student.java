package ssluzba.app;

import java.time.LocalDate;
import java.util.ArrayList;


public class Student {
	private String ime, prezime, adresaStanovanja, kontaktTelefon, email, brIndeksa;
	private LocalDate datumRodjenja, datumUpisa;
	private int godinaStudija;
	private double prosecnaOcena;
	private Status status;
	private ArrayList<String> predmeti;
	
	public Student() {}

	public Student(String ime, String prezime, String adresaStanovanja, String kontaktTelefon, String email,
			String brIndeksa, LocalDate datumRodjenja, int godinaStudija, Status status) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.adresaStanovanja = adresaStanovanja;
		this.kontaktTelefon = kontaktTelefon;
		this.email = email;
		this.brIndeksa = brIndeksa;
		this.datumRodjenja = datumRodjenja;
		this.status = status;
		this.datumUpisa = LocalDate.now();
		this.godinaStudija = godinaStudija;
		this.prosecnaOcena = 0;
		this.predmeti = new ArrayList<String>();
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getAdresaStanovanja() {
		return adresaStanovanja;
	}

	public void setAdresaStanovanja(String adresaStanovanja) {
		this.adresaStanovanja = adresaStanovanja;
	}

	public String getKontaktTelefon() {
		return kontaktTelefon;
	}

	public void setKontaktTelefon(String kontaktTelefon) {
		this.kontaktTelefon = kontaktTelefon;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBrIndeksa() {
		return brIndeksa;
	}

	public void setBrIndeksa(String brIndeksa) {
		this.brIndeksa = brIndeksa;
	}

	public LocalDate getDatumRodjenja() {
		return datumRodjenja;
	}

	public void setDatumRodjenja(LocalDate datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}

	public LocalDate getDatumUpisa() {
		return datumUpisa;
	}

	public void setDatumUpisa(LocalDate datumUpisa) {
		this.datumUpisa = datumUpisa;
	}

	public double getProsecnaOcena() {
		return prosecnaOcena;
	}

	public void setProsecnaOcena(double prosecnaOcena) {
		this.prosecnaOcena = prosecnaOcena;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public int getGodinaStudija() {
		return godinaStudija;
	}

	public void setGodinaStudija(int godinaStudija) {
		this.godinaStudija = godinaStudija;
	}

	public ArrayList<String> getPredmeti() {
		return this.predmeti;
	}
	
	public void setPremeti(ArrayList<String> predmeti) {
		this.predmeti = predmeti;
	}
	
	
}
