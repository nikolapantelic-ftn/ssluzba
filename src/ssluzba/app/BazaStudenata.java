package ssluzba.app;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
		studenti.add(new Student("Nikola", "Pantelic", "Backi Jarak", "0615558295", "nikolapantelic@gmail.com",
				"ra-234-2017", LocalDate.parse("15.12.1998.", dateFormatter), 3, Status.B));
		studenti.add(new Student("Ognjen", "Peric", "Lacarak", "0618888888", "ognjenp434@gmail.com", "ra-123-2016",
				LocalDate.parse("1996-12-15"), 2, Status.B));
		studenti.add(new Student("Marko", "Markovic", "Novi Sad", "0635848295", "markomarkovic@gmail.com",
				"ra-244-2016", LocalDate.parse("1998-10-15"), 2, Status.S));
		studenti.add(new Student("Petar", "Petrovic", "Beograd", "0625848295", "petarpetrovic@gmail.com", "ra-5-2017",
				LocalDate.parse("1996-12-15"), 4, Status.S));

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

}
