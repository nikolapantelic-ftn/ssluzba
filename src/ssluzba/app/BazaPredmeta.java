package ssluzba.app;

import java.util.ArrayList;
import java.util.List;


import ssluzba.app.controllers.PredmetiController;

public class BazaPredmeta {
	private static BazaPredmeta instance=null;
	
	public static BazaPredmeta getInstance() {
			if(instance==null) {
			instance=new BazaPredmeta();
		}
		return instance;
	}
	
	private List<Predmet> predmeti;
	private List<String> kolone;
	
	private BazaPredmeta() {
		initPredmete();
		this.kolone=new ArrayList<String>();
		this.kolone.add("Sifra");
		this.kolone.add("Naziv");
		this.kolone.add("Semestar");
		this.kolone.add("Godina predmeta");
		this.kolone.add("Profesor");
		this.kolone.add("Studenti");
	}
	private void initPredmete() {
		this.predmeti = new ArrayList<Predmet>();
		predmeti.add(new Predmet("sdadsa", "Mika", "Letnji", 2,null,null));
		predmeti.add(new Predmet("sdadsa", "3llll", "Zimski", 2,null,null));
		predmeti.add(new Predmet("analiza", "3llll", "Zimski", 2,null,null));
	}
	
	public List<Predmet> getPredmeti(){
		return predmeti;
	}
	
	public void setPredmeti(ArrayList<Predmet> pred) {
		this.predmeti=pred;
	}
	
	public Predmet getRow(int row) {
		return this.predmeti.get(row);
	}
	
/*	public String getPredmetVal(int row,int col) {
		Predmet predmet=this.predmeti(row);
		switch(col) {
		case 0:
			return predmet.getSifra();
		case 1:
			return predmet.getNaziv();
		case 2:
			return predmet.getSemestar();
		case 3:
			return Integer.toString(predmet.getGodinaPredmeta());
		case 4:
			return Profes(predmet.getProfesor());
		case 5:
			return predmet.getStudenti();
			
		}
	}
*/
	public void dodajPredmet(String sif,String naz,String sem,int god) {
		this.predmeti.add(new Predmet(sif,naz,sem,god,null,null));
	}
	
	public void dodajPredmet(Predmet p) {
		this.predmeti.add(p);
	}
	
	public void dodajProfesoraNaPredmet(Profesor pr) {
		
	}

	public int getColumnCount() {
		return kolone.size();
	}
	public String getValueAt(int row ,int column) {
		Predmet predmet=this.predmeti.get(row);
		switch(column) {
		case 0:
				return predmet.getSifra();
		case 1:
			return predmet.getNaziv();
		case 2:
			return predmet.getSemestar();
		case 3:
			return Integer.toString(predmet.getGodinaPredmeta());
		default:
			return null;
		}
		
	}
	public String getColumnName(int index) {
		return this.kolone.get(index);
	}
	public void ispisi() {
		for(Predmet predmet : this.predmeti) {
			System.out.println(predmet.getSifra());
		}
		
	}
	public  void deletePredmet(int row) {
		this.predmeti.remove(row);
		
	}
}
