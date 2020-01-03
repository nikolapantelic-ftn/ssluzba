package ssluzba.app;

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
		this.kolone.add("Trenutna godina");
		this.kolone.add("Status");
		this.kolone.add("Prosecna ocena");
	}
	
	private void initStudente() {
		studenti = new ArrayList<Student>();
		
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
			return Integer.toString(student.getGodinaStudija());
		case 4:
			if(student.getStatus() == Status.S)
				return "S";
			else return "B";
		case 5: return Double.toString(student.getProsecnaOcena());
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
