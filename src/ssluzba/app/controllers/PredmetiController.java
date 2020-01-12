package ssluzba.app.controllers;



import ssluzba.app.BazaPredmeta;
import ssluzba.app.Predmet;
import ssluzba.app.Profesor;
import ssluzba.app.views.PredmetJTable;

public class PredmetiController {

	private static PredmetiController instance=null;
	
	public static PredmetiController getInstance() {
		if(instance==null) {
			instance=new PredmetiController();
		}
		return instance;
	}
	
	private boolean pronadjeno;
	
	public PredmetiController() {
		// TODO Auto-generated constructor stub
	}

	public boolean getPronadjeno() {
		return pronadjeno;
	}
	public void dodaj(String sifra,String naziv,String sem,int god) {
		BazaPredmeta.getInstance().dodajPredmet(new Predmet(sifra,naziv,sem,god,null,null));
		PredmetJTable.getInstance().azurirajPrikaz();
	}

	
	public void izbrisi(String sifra) {
		int row=PredmetJTable.getInstance().getSelectedRow();
		if(row<0)
			return;
		BazaPredmeta.getInstance().deletePredmet(sifra);
		BazaPredmeta.getInstance().deletePredmetPretraga(sifra);
		PredmetJTable.getInstance().azurirajPrikaz();
	}

	
	public void izmeni(String sifra, String naziv, String semestar, int godina) {
			Predmet p=BazaPredmeta.getInstance().getRow(PredmetJTable.getInstance().getSelectedRow());
				p.setSifra(sifra);
				p.setNaziv(naziv);
				p.setGodinaPredmeta(godina);
				p.setSemestar(semestar);
			
		
		PredmetJTable.getInstance().azurirajPrikaz();

	}


	public void dodajProfesora(Profesor p) {
		BazaPredmeta.getInstance().dodajProfesoraNaPredmet(p);
	}
	public boolean postojiSifra(String sifra) {
		for(Predmet p : BazaPredmeta.getInstance().getPredmeti()) {
			if(p.getSifra().equals(sifra))
				return true;
		}
		return false;
	}
	public Predmet getPredmet(int row) {
		return BazaPredmeta.getInstance().getRow(row);
	}
	
	public String[] parseString (String str) {
		String[] string = new String[5];
		 string[0]= "";
		 string[1]="";
		 string[2]="";
		 string[3]="";
		 string[4]="";
		String[] parametri=str.split(";");
		for(int i=0;i<parametri.length;i++) {
			String [] vrednosti=parametri[i].split(":");
			if(vrednosti[0].toLowerCase().equals("sifra")) {
				string[0]=vrednosti[1].toLowerCase();
			}else if(vrednosti[0].toLowerCase().equals("naziv")) {
				string[1]=vrednosti[1].toLowerCase();
			}else if(vrednosti[0].toLowerCase().equals("semestar")) {
				string[2]=vrednosti[1].toLowerCase();
			}else if(vrednosti[0].toLowerCase().equals("godina")) {
				string[3]=vrednosti[1].toLowerCase();
			
			}else
				string[4]="nema";
		}
		
	
		return string;
	}
	public void pretraziPredmete(String sifra,String naziv,String semestar,String godina) {
		
		for(Predmet p:BazaPredmeta.getInstance().getPredmeti()) {
			if(pretraziSifru(sifra, p) && pretraziNaziv(naziv, p) && pretraziSemestar(semestar, p)&& pretraziGodinu(godina, p)) {
				pronadjeno=true;
				BazaPredmeta.getInstance().getPretraga().add(p);
				
			}else if(BazaPredmeta.getInstance().getPretraga().isEmpty()) {
				pronadjeno=false;
			}
		}
		
		PredmetJTable.getInstance().azurirajPrikaz();
	}
	public boolean pretraziSifru(String string,Predmet p) {
		if(string.isEmpty())
			return true;
	
			if(p.getSifra().toLowerCase().equals(string)) {
				return true;
			
		}
		return false;
		
	}

	public boolean pretraziNaziv(String string,Predmet p) {
		if(string.isEmpty())
			return true;
	
			if(p.getNaziv().toLowerCase().equals(string)) {
				return true;
			
		}
		return false;
		
	}
	public boolean pretraziSemestar(String string,Predmet p) {
		if(string.isEmpty())
			return true;
		
			if(p.getSemestar().toLowerCase().equals(string)) {
				return true;
			}
		
		return false;
		
	}
	public boolean pretraziGodinu(String string,Predmet p) {
		if(string.isEmpty())
			return true;
			int godina=Integer.parseInt(string);
			if (p.getGodinaPredmeta()==godina)
				return true;
			
		
		return false;
		
	}
	
	public void ocistiPretragu() {
		BazaPredmeta.getInstance().getPretraga().clear();
		PredmetJTable.getInstance().azurirajPrikaz();
		
	}
	public void setProfesorNaTrenutniPredmet(Profesor p,Predmet pr) {
		pr.setProfesor(p);
	}
	public void ocistiStudenteSaPredmeta(String sifra) {
		for(Predmet pr:BazaPredmeta.getInstance().getPredmeti()) {
			if(pr.getSifra().equals(sifra))
				pr.getStudenti().clear();
		}
	}

}
