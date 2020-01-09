package ssluzba.app;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;

public class BazaProfesora {

	private static BazaProfesora instance = null;

	public static BazaProfesora getInstance() {
		if (instance == null)
			instance = new BazaProfesora();
		return instance;
	}

	private List<Profesor> profesori;
	private List<String> kolone;
	private List<Profesor> pretragaProfesori;
	private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");

	private BazaProfesora() {
		initProf();
		this.pretragaProfesori = new ArrayList<Profesor>();

		this.kolone = new ArrayList<String>();
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
		this.profesori = new ArrayList<Profesor>();
		try {
			deserialize();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Profesor> getProfesori() {
		return profesori;
	}

	public List<Profesor> getPretraga() {
		return pretragaProfesori;
	}

	public void setProfesori(ArrayList<Profesor> prof) {
		this.profesori = prof;
	}

	public Profesor getRow(int row) {
		if (pretragaProfesori.isEmpty())
			return this.profesori.get(row);
		return this.pretragaProfesori.get(row);
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

	public void deleteProfesorPretraga(String licna) {
		if(!pretragaProfesori.isEmpty()) {
			for(Profesor p:pretragaProfesori) {
				if(p.getBrojLicne().equals(licna)) {
					pretragaProfesori.remove(p);
				break;
			}
			}
		}
	}
	
	public void deleteProfesor(String licna) {	
			
			for(Profesor p:profesori) {
				System.out.println("a");
				if(p.getBrojLicne().equals(licna)) {
					System.out.println("bam");
					profesori.remove(p);
				break;
				}
			}
		
	}

	public String getValueAt(int row, int column) {
		Profesor profesor;
		if (BazaProfesora.getInstance().getPretraga().isEmpty()) {
			profesor = this.profesori.get(row);
		} else {
			profesor = this.pretragaProfesori.get(row);
		}
		switch (column) {
		case 0:
			return profesor.getIme();
		case 1:
			return profesor.getPrezime();
		case 2:
			return profesor.getBrojLicne();
		case 3:
			return profesor.getAdresa();
		case 4:
			return profesor.getKontaktTelefon();
		case 5:
			return profesor.geteMail();
		case 6:
			return profesor.getAdresaKancelarije();
		case 7:
			return profesor.getTitula();
		case 8:
			return profesor.getZvanje();
		case 9:
			return profesor.getDatumRodjenja();
		default:
			return null;
		}

	}

	public DateTimeFormatter getDateFormatter() {
		return this.dateFormatter;
	}
	
	public void serialize() throws IOException {
		File f = new File("database/bazaProfesora.xml");
		OutputStream os = new BufferedOutputStream(new FileOutputStream(f));
		try {
			XStream xs = new XStream();
			xs.alias("profesor", Profesor.class);
			xs.toXML(profesori, os);
		} finally {
			os.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void deserialize() throws Exception {
		File f = new File("database/bazaProfesora.xml");
		try {
			XStream xs = new XStream();
			XStream.setupDefaultSecurity(xs);
			xs.allowTypes(new Class[] {Profesor.class, Predmet.class});
			xs.alias("profesor", Profesor.class);
			this.profesori = (List<Profesor>) xs.fromXML(f);
		} catch (Exception e) {
			System.out.println("Baza profesora ne postoji. Bice napravljena nova baza po zatvaranju aplikacije");
		}
	}
}
