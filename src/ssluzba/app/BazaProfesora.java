package ssluzba.app;

import java.util.ArrayList;
import java.util.List;

public class BazaProfesora {

	private static BazaProfesora instance=null;
	public static BazaProfesora getInstance() {
		if(instance==null)
			instance=new BazaProfesora();
	return instance;
	}
	private List<Profesor> profesori;
	private List<String> kolone;
	
	private BazaProfesora() {
		initProf();
		this.kolone=new ArrayList<String>();
		this.kolone.add("Ime");
		this.kolone.add("Prezime");
		this.kolone.add("Broj Licne");
		this.kolone.add("Adresa");
		this.kolone.add("Kontakt telefon");
		this.kolone.add("E-mail");
		this.kolone.add("Adresa Kancelarije");
		this.kolone.add("Titula");
		this.kolone.add("Zvanje");
		this.kolone.add("Datum rodjenja");
		this.kolone.add("Predmeti");
		
	}

	private void initProf() {
		this.profesori=new ArrayList<Profesor>();
		profesori.add(new Profesor("aaa", "aaa", "aaa", "aaa", "aaa", "aaa", "aaa", "aaa", "11-11-1339", "aaa11"));
		
	}
	public List<Profesor> getProfesori(){
		return profesori;
	}
	
	public void setProfesori(ArrayList<Profesor> prof) {
		this.profesori=prof;
	}
	
	public Profesor getRow(int row) {
		return this.profesori.get(row);
	}
	public int getColumnCount() {
		return kolone.size();
	}
	public void dodajProfesora(Profesor p) {
		this.profesori.add(p);
	}
	public String getColumnName(int index) {
		return this.kolone.get(index);
	}
	
	public  void deleteProfesor(int row) {
		this.profesori.remove(row);
		
	}
	
	public String getValueAt(int row ,int column) {
		Profesor profesor=this.profesori.get(row);
		switch(column) {
		case 0:
				return profesor.getIme();
		case 1:
			return profesor.getPrezime();
		case 2:
			return profesor.getBrojLicne();
		case 3:
			return profesor.getAdresa();
		default:
			return null;
		}
		
	}
}
