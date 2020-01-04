package ssluzba.app;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BazaStudenata {
private static BazaStudenata instance=null;
	
	public static BazaStudenata getInstance() {
			if(instance==null) {
			instance=new BazaStudenata();
		}
		return instance;
	}
	
	private List<Student> studenti;
	private List<String> kolone;
	
	private BazaStudenata() {
		initStudente();
		this.kolone=new ArrayList<String>();
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
		studenti = new ArrayList<Student>();
		studenti.add(new Student("Nikola", "Pantelic", "Backi Jarak", "0615558295", "nikolapantelic@gmail.com", "ra-234-2017", LocalDate.parse("1998-12-15"), 3, Status.B));
		studenti.add(new Student("Ognjen", "Peric", "Lacarak", "0618888888", "ognjenp434@gmail.com", "ra-123-2016", LocalDate.parse("1996-12-15"), 2, Status.B));
		studenti.add(new Student("Marko", "Markovic", "Novi Sad", "0635848295", "markomarkovic@gmail.com", "ra-244-2016", LocalDate.parse("1998-10-15"), 2, Status.S));
		studenti.add(new Student("Petar", "Petrovic", "Beograd", "0625848295", "petarpetrovic@gmail.com", "ra-5-2017", LocalDate.parse("1996-12-15"), 4, Status.S));
		
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
	
	public String getValueAt(int row ,int column) {
		Student student=this.studenti.get(row);
		switch(column) {
		case 0:
			return student.getBrIndeksa();
		case 1:
			return student.getIme();
		case 2:
			return student.getPrezime();
		case 3:
			return student.getDatumRodjenja().toString();
		case 4:
			return student.getDatumUpisa().toString();
		case 5:
			return Integer.toString(student.getGodinaStudija());
		case 6:
			if(student.getStatus() == Status.S)
				return "S";
			else return "B";
		case 7: return Double.toString(student.getProsecnaOcena());
		default:
			return null;
		}
	}
	
	public String getColumnName(int index) {
		return this.kolone.get(index);
	}
	
	public  void deleteStudent(int row) {
		this.studenti.remove(row);	
	}

}
