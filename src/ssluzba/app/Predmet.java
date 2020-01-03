package ssluzba.app;

import java.util.ArrayList;

public class Predmet {
	private String sifra,naziv,semestar;
	private int godinaPredmeta;
	private Profesor profesor;
	private ArrayList<Student> studenti;

	public Predmet(String sifra, String naziv, String semestar, int godinaPredmeta, Profesor profesor) {
		super();
		this.sifra = sifra;
		this.naziv = naziv;
		this.semestar = semestar;
		this.godinaPredmeta = godinaPredmeta;
		this.profesor = profesor;
		this.studenti=new ArrayList<Student>();
		
	}

	public Predmet(String sif, String naz, String sem, int god,Profesor p,ArrayList<Predmet> predmeti) {
		this.sifra=sif;
		this.naziv=naz;
		this.semestar=sem;
		this.godinaPredmeta=god;
	}
	

	public Predmet() {
		// TODO Auto-generated constructor stub
	}

	public String getSifra() {
		return sifra;
	}

	public void setSifra(String sifra) {
		this.sifra = sifra;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getSemestar() {
		return semestar;
	}

	public void setSemestar(String semestar) {
		this.semestar = semestar;
	}

	public int getGodinaPredmeta() {
		return godinaPredmeta;
	}

	public void setGodinaPredmeta(int godinaPredmeta) {
		this.godinaPredmeta = godinaPredmeta;
	}

	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}

	public ArrayList<Student> getStudenti() {
		return studenti;
	}

	public void setStudenti(ArrayList<Student> studenti) {
		this.studenti = studenti;
	}


	
	
	
	

}
