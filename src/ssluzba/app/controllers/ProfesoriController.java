package ssluzba.app.controllers;

import ssluzba.app.BazaPredmeta;
import ssluzba.app.BazaProfesora;
import ssluzba.app.Predmet;
import ssluzba.app.Profesor;
import ssluzba.app.views.PredmetJTable;
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

	public void deleteProfesor(int row) {
		BazaProfesora.getInstance().deleteProfesor(row);
		ProfesorJTable.getInstance().azurirajPrikaz();
	}

	public void ocistiPretragu() {
		BazaProfesora.getInstance().getPretraga().clear();

	}

	public String[] parseString(String str) {
		String[] string = new String[9];
		string[0] = "";
		string[1] = "";
		string[2] = "";
		string[3] = "";
		string[4] = "";
		string[5] = "";
		string[6] = "";
		string[7] = "";
		string[8] = "";

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
			}

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
				System.out.println("dodato");

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
	public void setProfesorNaTrenutniPredmet(Profesor p) {
		Predmet pr=BazaPredmeta.getInstance().getRow(PredmetJTable.getInstance().getSelectedRow());
		pr.setProfesor(p);
	}
}
