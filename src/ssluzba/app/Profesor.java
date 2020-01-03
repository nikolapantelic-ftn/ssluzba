package ssluzba.app;
import java.util.ArrayList;
import java.util.Date;

public class Profesor {
	private String ime,prezime,adresa,kontaktTelefon,eMail,adresaKancelarije,titula,zvanje;
	private Date datumRodjenja;
	private ArrayList<Predmet> predmeti;
	private int[] brojLicne;
	
	


	public Profesor(String ime, String prezime, String adresa, String kontaktTelefon, String eMail,
			String adresaKancelarije, String titula, String zvanje, Date datumRodjenja,
			int[] brojLicne) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.adresa = adresa;
		this.kontaktTelefon = kontaktTelefon;
		this.eMail = eMail;
		this.adresaKancelarije = adresaKancelarije;
		this.titula = titula;
		this.zvanje = zvanje;
		this.datumRodjenja = datumRodjenja;
		this.predmeti = new ArrayList<Predmet>();
		this.brojLicne = brojLicne;
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

	public Date getDatumRodjenja() {
		return datumRodjenja;
	}

	public void setDatumRodjenja(Date datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}

	public ArrayList<Predmet> getPredmeti() {
		return predmeti;
	}

	public void setPredmeti(ArrayList<Predmet> predmeti) {
		this.predmeti = predmeti;
	}

	

	public int[] getBrojLicne() {
		return brojLicne;
	}

	public void setBrojLicne(int[] brojLicne) {
		this.brojLicne = brojLicne;
	}

	
	
	

	
	
	
	

}
