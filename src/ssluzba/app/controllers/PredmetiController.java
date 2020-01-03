package ssluzba.app.controllers;

import java.util.ArrayList;

import ssluzba.app.BazaPredmeta;
import ssluzba.app.Predmet;
import ssluzba.app.Profesor;
import ssluzba.app.Student;
import ssluzba.app.views.MainFrame;
import ssluzba.app.views.PredmetJTable;

public class PredmetiController implements Controller {

	private static PredmetiController instance=null;
	
	public static PredmetiController getInstance() {
		if(instance==null) {
			instance=new PredmetiController();
		}
		return instance;
	}
	public PredmetiController() {
		// TODO Auto-generated constructor stub
	}

	public void dodaj(String sifra,String naziv,String sem,int god) {
		BazaPredmeta.getInstance().dodajPredmet(new Predmet(sifra,naziv,sem,god,null,null));
		PredmetJTable.getInstance().azurirajPrikaz();
	}

	@Override
	public void izbrisi(int row) {
		if(row<0)
			return;
		BazaPredmeta.getInstance().deletePredmet(row);
		PredmetJTable.getInstance().azurirajPrikaz();
	}

	
	public void izmeni(String sifra, String naziv, String semestar, int godina) {
		for(Predmet p:BazaPredmeta.getInstance().getPredmeti()) {
				if(p.getSifra().equals(sifra)) {
				p.setNaziv(naziv);
				p.setGodinaPredmeta(godina);
				p.setSemestar(semestar);
			}
		}
		PredmetJTable.getInstance().azurirajPrikaz();

	}

	@Override
	public void dodaj() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void izmeni() {
		// TODO Auto-generated method stub
		
	}

}
