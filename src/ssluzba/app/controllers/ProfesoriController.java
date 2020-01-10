package ssluzba.app.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import ssluzba.app.BazaPredmeta;
import ssluzba.app.BazaProfesora;
import ssluzba.app.Predmet;
import ssluzba.app.Profesor;
import ssluzba.app.views.ProfesorJTable;

public class ProfesoriController {

	private static ProfesoriController instance = null;

	public static ProfesoriController getInstance() {
		if (instance == null)
			instance = new ProfesoriController();
		return instance;
	}

	private boolean pronadjeno;

	public ProfesoriController() {
		// TODO Auto-generated constructor stub
	}
	public Profesor getProfesor(int row) {
		return BazaProfesora.getInstance().getRow(row);
	}
	
	/**
	 * Dodaje novog profesora u bazu profesora
	 * @throws Exception Sadrzi poruku o pogresno unetom parametru
	 */
	public void dodaj(String ime, String prezime, String brojLicne, String adresa, String kontaktTelefon, String eMail,
			String adresaKancelarije, String titula, String zvanje, String datumRodjenja) throws Exception {
		try {
			proveraUnosa(ime, prezime, brojLicne, adresa, kontaktTelefon, eMail, adresaKancelarije, titula, zvanje, datumRodjenja);
		} catch (Exception e) {
			throw e;
		}

		for (Profesor p : BazaProfesora.getInstance().getProfesori()) {
			if (p.getBrojLicne().equals(brojLicne))
				throw new Exception("Profesor vec postoji!");
		}
		Profesor profesor = new Profesor(ime, prezime, brojLicne, adresa, kontaktTelefon, eMail, adresaKancelarije,
				titula, zvanje, datumRodjenja);
		BazaProfesora.getInstance().dodajProfesora(profesor);
		ProfesorJTable.getInstance().azurirajPrikaz();
	}
	
	/**
	 * Menja trenutno oznacenog profesora u tabeli.
	 * @throws Exception Sadrzi poruku o pogresno unetom parametru
	 */
	public void izmeni(String ime, String prezime, String brojLicne, String adresa, String kontaktTelefon,
			String eMail, String adresaKancelarije, String titula, String zvanje, String datumRodjenja) throws Exception {
		try {
			proveraUnosa(ime, prezime, brojLicne, adresa, kontaktTelefon, eMail, adresaKancelarije, titula, zvanje, datumRodjenja);
		} catch (Exception e) {
			System.out.println("bacio ovde");
			throw e;
		}
		
		//Ako je profesoru promenjen broj licne, proveri da li novoupisani broj licne vec postoji
		Profesor profesor = nadjiIzabranog();
		if(!profesor.getBrojLicne().equals(brojLicne)) {
			for (Profesor p : BazaProfesora.getInstance().getProfesori()) {
				if (p.getBrojLicne().equals(brojLicne)) {
					throw new Exception("Profesor vec postoji!");
				}
			}
			
			//Ako je profesoru promenjen broj licne, obrisi ga sa predmeta i obrisi njegove predmete
			for(Predmet pr: BazaPredmeta.getInstance().getPredmeti()) {
				if(pr.getProfesor() != null) {
					if(pr.getProfesor().getBrojLicne().equals(profesor.getBrojLicne())) {
						pr.removeProfesor();
					}
				}
			}
			
			profesor.getPredmeti().clear();
		}
		

		profesor.setIme(ime);
		profesor.setPrezime(prezime);
		profesor.setBrojLicne(brojLicne);
		profesor.setAdresa(adresa);
		profesor.setKontaktTelefon(kontaktTelefon);
		profesor.seteMail(eMail);
		profesor.setAdresaKancelarije(adresaKancelarije);
		profesor.setTitula(titula);
		profesor.setZvanje(zvanje);
		profesor.setDatumRodjenja(datumRodjenja);
		ProfesorJTable.getInstance().azurirajPrikaz();
	}
	
	/**
	 * Proverava tacnosti unetih parametara za profesora
	 * @throws Exception Sadrzi poruku o pogresno unetom parametru
	 */
	public void proveraUnosa(String ime, String prezime, String brojLicne, String adresa, String kontaktTelefon,
			String eMail, String adresaKancelarije, String titula, String zvanje, String datumRodjenja) throws Exception {
		if (ime.isEmpty())
			throw new Exception("Ime ne sme biti prazno!");
		if (prezime.isEmpty())
			throw new Exception("Prezime ne sme biti prazno!");
		if (brojLicne.isEmpty())
			throw new Exception("Broj licne karte ne sme biti prazan!");
		try {
			Integer.parseInt(brojLicne);
		} catch (NumberFormatException e) {
			throw new Exception("Broj licne karte moze sadrzati samo cifre!");
		}
		if (datumRodjenja.isEmpty())
			throw new Exception("Datum rodjenja ne sme biti prazan!");
		try {
			DateTimeFormatter formatter = BazaProfesora.getInstance().getDateFormatter();
			LocalDate.parse(datumRodjenja, formatter);
		} catch (DateTimeParseException pe) {
			throw new Exception("Datum mora biti u formatu dd.mm.yyyy.");
		}
		if (kontaktTelefon.isEmpty())
			throw new Exception("Broj telefona ne sme biti prazan!");
		if (eMail.isEmpty())
			throw new Exception("E-mail ne sme biti prazan!");
		if (adresa.isEmpty())
			throw new Exception("Adresa stanovanja ne sme biti prazana!");
		if (zvanje.isEmpty())
			throw new Exception("Zvanje ne sme biti prazno!");
	}
	
	public Profesor nadji(String brojLicne) {
		for (Profesor p : BazaProfesora.getInstance().getProfesori()) {
			if (p.getBrojLicne().equals(brojLicne))
				return p;
		}
		return null;
	}
	
	
	/**
	 * Trazi profesora koji je trenutno oznacen u tabeli
	 * @return Objekat profesora iz baze profesora
	 */
	public Profesor nadjiIzabranog() {
		int row = ProfesorJTable.getInstance().getSelectedRow();
		String brojLicne = (String) ProfesorJTable.getInstance().getValueAt(row, 2);
		return nadji(brojLicne);
	}

	public void deleteProfesor(String licna) {
		BazaProfesora.getInstance().deleteProfesor(licna);
		BazaProfesora.getInstance().deleteProfesorPretraga(licna);
		ProfesorJTable.getInstance().azurirajPrikaz();
	}

	public void ocistiPretragu() {
		BazaProfesora.getInstance().getPretraga().clear();
		ProfesorJTable.getInstance().azurirajPrikaz();

	}

	public String[] parseString(String str) {
		String[] string = new String[10];
		string[0] = "";
		string[1] = "";
		string[2] = "";
		string[3] = "";
		string[4] = "";
		string[5] = "";
		string[6] = "";
		string[7] = "";
		string[8] = "";
		string[9]="";

		String[] parametri = str.split(";");
		for (int i = 0; i < parametri.length; i++) {
			String[] vrednosti = parametri[i].split(":");
			if (vrednosti[0].toLowerCase().equals("ime")) {
				string[0] = vrednosti[1].toLowerCase();
			} else if (vrednosti[0].toLowerCase().equals("prezime")) {
				string[1] = vrednosti[1].toLowerCase();
			} else if (vrednosti[0].toLowerCase().equals("brojlicne")) {
				string[2] = vrednosti[1].toLowerCase();
			} else if (vrednosti[0].toLowerCase().equals("adresa")) {
				string[3] = vrednosti[1].toLowerCase();

			} else if (vrednosti[0].toLowerCase().equals("kontakt")) {
				string[4] = vrednosti[1].toLowerCase();
			} else if (vrednosti[0].toLowerCase().equals("email")) {
				string[5] = vrednosti[1].toLowerCase();
			} else if (vrednosti[0].toLowerCase().equals("kancelarija")) {
				string[6] = vrednosti[1].toLowerCase();
			} else if (vrednosti[0].toLowerCase().equals("titula")) {
				string[7] = vrednosti[1].toLowerCase();
			} else if (vrednosti[0].toLowerCase().equals("zvanje")) {
				string[8] = vrednosti[1].toLowerCase();
			}else 
				string[9]="nema";

		}
		return string;
	}

	public boolean getPronadjeno() {
		return pronadjeno;
	}

	public void pretraziProfesore(String ime, String prezime, String licna, String adresa, String telefon, String email,
			String kancelarija, String titula, String zvanje) {

		for (Profesor p : BazaProfesora.getInstance().getProfesori()) {
			if (pretraziIme(ime, p) && pretraziPrezime(prezime, p) && pretraziLicnu(licna, p)
					&& pretraziAdresu(adresa, p) && pretraziTelefon(telefon, p) && pretraziMail(email, p)
					&& pretraziKancelarija(kancelarija, p) && pretraziTitulu(titula, p)
					&& pretraziTelefon(telefon, p)) {
				pronadjeno = true;
				BazaProfesora.getInstance().getPretraga().add(p);

			} else if (BazaProfesora.getInstance().getPretraga().isEmpty()) {
				pronadjeno = false;
			}
		}

		ProfesorJTable.getInstance().azurirajPrikaz();
	}

	public boolean pretraziIme(String string, Profesor p) {
		if (string.isEmpty())
			return true;

		if (p.getIme().toLowerCase().equals(string))
			return true;

		return false;

	}

	public boolean pretraziPrezime(String string, Profesor p) {
		if (string.isEmpty())
			return true;

		if (p.getPrezime().toLowerCase().equals(string))
			return true;

		return false;

	}

	public boolean pretraziLicnu(String string, Profesor p) {
		if (string.isEmpty())
			return true;

		if (p.getBrojLicne().toLowerCase().equals(string))
			return true;

		return false;

	}

	public boolean pretraziAdresu(String string, Profesor p) {
		if (string.isEmpty())
			return true;

		if (p.getAdresa().toLowerCase().equals(string))
			return true;

		return false;

	}

	public boolean pretraziTelefon(String string, Profesor p) {
		if (string.isEmpty())
			return true;

		if (p.getKontaktTelefon().toLowerCase().equals(string))
			return true;

		return false;

	}

	public boolean pretraziMail(String string, Profesor p) {
		if (string.isEmpty())
			return true;

		if (p.geteMail().toLowerCase().equals(string))
			return true;

		return false;

	}

	public boolean pretraziKancelarija(String string, Profesor p) {
		if (string.isEmpty())
			return true;

		if (p.getAdresaKancelarije().toLowerCase().equals(string))
			return true;

		return false;

	}

	public boolean pretraziTitulu(String string, Profesor p) {
		if (string.isEmpty())
			return true;

		if (p.getTitula().toLowerCase().equals(string))
			return true;

		return false;

	}

	public boolean pretraziZvanje(String string, Profesor p) {
		if (string.isEmpty())
			return true;

		if (p.getZvanje().toLowerCase().equals(string))
			return true;

		return false;

	}

	public Profesor pretraziPoLicnoj(String licna) {
		for (Profesor p : BazaProfesora.getInstance().getProfesori()) {
			if (licna.toLowerCase().equals(p.getBrojLicne().toLowerCase())) {
				return p;
			}

		}
		return null;
	}
	
	public void addPredmet(Predmet pr,Profesor p) {
		p.addPredmet(pr);
	}
	public void removePredmet(Predmet pr,Profesor p) {
		p.delPredmet(pr);
	}
	
}
