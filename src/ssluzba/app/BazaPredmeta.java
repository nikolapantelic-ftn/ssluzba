package ssluzba.app;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;


public class BazaPredmeta {
	private static BazaPredmeta instance=null;
	
	public static BazaPredmeta getInstance() {
			if(instance==null) {
			instance=new BazaPredmeta();
		}
		return instance;
	}
	
	private List<Predmet> predmeti;
	private List<Predmet> pretraga;
	private List<String> kolone;
	
	private BazaPredmeta() {
		initPredmete();
		this.pretraga = new ArrayList<Predmet>();
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
		try {
			deserialize();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Predmet> getPredmeti(){
		return predmeti;
	}
	public List<Predmet> getPretraga(){
		return pretraga;
	}
	
	public void setPredmeti(ArrayList<Predmet> pred) {
		this.predmeti=pred;
	}
	
	public Predmet getRow(int row) {
		if(!BazaPredmeta.getInstance().getPretraga().isEmpty())
			return this.pretraga.get(row);
		return this.predmeti.get(row);
	}
	

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
		Predmet predmet;
		if(BazaPredmeta.getInstance().getPretraga().isEmpty()) {
			
			 predmet=this.predmeti.get(row);
		}else {
			predmet=this.pretraga.get(row);
		}
		switch(column) {
		case 0:
				return predmet.getSifra();
		case 1:
			return predmet.getNaziv();
		case 2:
			return predmet.getSemestar();
		case 3:
			return Integer.toString(predmet.getGodinaPredmeta());
		case 4:
			if(predmet.getProfesor()!=null)
			return predmet.getProfesor().getBrojLicne();
			else return null;
		default:
			if(!predmet.getStudenti().isEmpty())
				return "Prikazi studente";
			else return null;
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
	
	public void serialize() throws IOException {
		File f = new File("database/bazaPredmeta.xml");
		OutputStream os = new BufferedOutputStream(new FileOutputStream(f));
		try {
			XStream xs = new XStream();
			xs.alias("predmet", Predmet.class);
			xs.toXML(predmeti, os);
		} finally {
			os.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void deserialize() throws Exception {
		File f = new File("database/bazaPredmeta.xml");
		try {
			XStream xs = new XStream();
			xs.setMode(XStream.XPATH_ABSOLUTE_REFERENCES);
			XStream.setupDefaultSecurity(xs);
			xs.allowTypes(new Class[] {Predmet.class, Student.class, Profesor.class});
			xs.alias("predmet", Predmet.class);
			this.predmeti = (List<Predmet>) xs.fromXML(f);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Baza predmeta ne postoji. Bice napravljena nova baza po zatvaranju aplikacije");
		}
	}
}
