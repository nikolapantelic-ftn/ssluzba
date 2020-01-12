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

import ssluzba.app.views.GlavniToolbar;

public class BazaStudenata {
	private static BazaStudenata instance = null;

	public static BazaStudenata getInstance() {
		if (instance == null) {
			instance = new BazaStudenata();
		}
		return instance;
	}

	private List<Student> studenti;
	private List<String> kolone;
	private List<Student> pretraga;
	private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");

	private BazaStudenata() {
		initStudente();
		this.kolone = new ArrayList<String>();
		this.kolone.add("Broj indeksa");
		this.kolone.add("Ime");
		this.kolone.add("Prezime");
		this.kolone.add("Datum rodjenja");
		this.kolone.add("Datum upisa");
		this.kolone.add("Trenutna godina");
		this.kolone.add("Status");
		this.kolone.add("Prosecna ocena");
	}

	private void initStudente() {
		pretraga = new ArrayList<Student>();
		studenti = new ArrayList<Student>();
		try {
			deserialize();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public List<Student> getStudenti() {
		return studenti;
	}

	public void setStudenti(List<Student> studenti) {
		this.studenti = studenti;
	}

	public Student getRow(int row) {
		return this.studenti.get(row);
	}

	public void dodajStudenta(Student s) {
		this.studenti.add(s);

	}

	public int getColumnCount() {
		return kolone.size();
	}

	public List<String> getKolone() {
		return kolone;
	}

	public String getValueAt(int row, int column) {
		Student student;
		if (GlavniToolbar.getInstance().isPretragaStudenata()) {
			student = this.pretraga.get(row);
		} else {
			student = this.studenti.get(row);
		}

		switch (column) {
		case 0:
			return student.getBrIndeksa();
		case 1:
			return student.getIme();
		case 2:
			return student.getPrezime();
		case 3:
			return student.getDatumRodjenja().format(dateFormatter).toString();
		case 4:
			return student.getDatumUpisa().format(dateFormatter).toString();
		case 5:
			return Integer.toString(student.getGodinaStudija());
		case 6:
			if (student.getStatus() == Status.S)
				return "S";
			else
				return "B";
		case 7:
			return Double.toString(student.getProsecnaOcena());
		default:
			return null;
		}
	}

	public String getColumnName(int index) {
		return this.kolone.get(index);
	}

	public List<Student> getPretraga() {
		return pretraga;
	}

	public void setPretraga(List<Student> pretraga) {
		this.pretraga = pretraga;
	}
	
	public DateTimeFormatter getDateFormatter() {
		return this.dateFormatter;
	}
	
	public void serialize() throws IOException {
		File f = new File("database/bazaStudenata.xml");
		OutputStream os = new BufferedOutputStream(new FileOutputStream(f));
		try {
			XStream xs = new XStream();
			xs.alias("student", Student.class);
			xs.toXML(studenti, os);
		} finally {
			os.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void deserialize() throws Exception {
		File f = new File("database/bazaStudenata.xml");
		try {
			XStream xs = new XStream();
			xs.setMode(XStream.XPATH_ABSOLUTE_REFERENCES);
			XStream.setupDefaultSecurity(xs);
			xs.allowTypes(new Class[] {Student.class, Profesor.class, Predmet.class});
			xs.alias("student", Student.class);
			this.studenti = (List<Student>) xs.fromXML(f);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Baza studenata ne postoji. Bice napravljena nova baza po zatvaranju aplikacije");
		}
	}

}
