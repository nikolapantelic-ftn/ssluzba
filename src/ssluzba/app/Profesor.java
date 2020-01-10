package ssluzba.app;
import java.util.ArrayList;


public class Profesor {
	private String ime,prezime,brojLicne,adresa,kontaktTelefon,eMail,adresaKancelarije,titula,zvanje;
	private String datumRodjenja;
	private ArrayList<Predmet> predmeti;
	
	
	


	public Profesor(String ime, String prezime, String brojLicne,String adresa, String kontaktTelefon, String eMail,
			String adresaKancelarije, String titula, String zvanje, String string) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.brojLicne = brojLicne;
		this.adresa = adresa;
		this.kontaktTelefon = kontaktTelefon;
		this.eMail = eMail;
		this.adresaKancelarije = adresaKancelarije;
		this.titula = titula;
		this.zvanje = zvanje;
		this.datumRodjenja = string;
		this.predmeti = new ArrayList<Predmet>();
		
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getKontaktTelefon() {
		return kontaktTelefon;
	}

	public void setKontaktTelefon(String kontaktTelefon) {
		this.kontaktTelefon = kontaktTelefon;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getAdresaKancelarije() {
		return adresaKancelarije;
	}

	public void setAdresaKancelarije(String adresaKancelarije) {
		this.adresaKancelarije = adresaKancelarije;
	}

	public String getTitula() {
		return titula;
	}

	public void setTitula(String titula) {
		this.titula = titula;
	}

	public String getZvanje() {
		return zvanje;
	}

	public void setZvanje(String zvanje) {
		this.zvanje = zvanje;
	}

	public String getDatumRodjenja() {
		return datumRodjenja;
	}

	public void setDatumRodjenja(String datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}

	public ArrayList<Predmet> getPredmeti() {
		return predmeti;
	}

	public void setPredmeti(ArrayList<Predmet> predmeti) {
		this.predmeti = predmeti;
	}
	public void addPredmet(Predmet p) {
		this.predmeti.add(p);
	}
	public void delPredmet(Predmet p) {
		this.predmeti.remove(p);
	}

	

	public String getBrojLicne() {
		return brojLicne;
	}

	public void setBrojLicne(String brojLicne) {
		this.brojLicne = brojLicne;
	}

	
	
	

	
	
	
	

}
