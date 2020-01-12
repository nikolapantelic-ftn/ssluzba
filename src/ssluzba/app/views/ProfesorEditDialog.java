package ssluzba.app.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import ssluzba.app.Profesor;
import ssluzba.app.controllers.ProfesoriController;

public class ProfesorEditDialog extends ProfesorDialog {

	private static final long serialVersionUID = 5857425175033002909L;
	
	public ProfesorEditDialog() {
		super();
		Profesor profesor = ProfesoriController.getInstance().nadjiIzabranog();
		imeText.setText(profesor.getIme());
		prezimeText.setText(profesor.getPrezime());
		brojLicneText.setText(profesor.getBrojLicne());
		datumRodjenjaText.setText(profesor.getDatumRodjenja());
		adresaStanovanjaText.setText(profesor.getAdresa());
		adresaKancelarijeText.setText(profesor.getAdresaKancelarije());
		brojTelefonaText.setText(profesor.getKontaktTelefon());
		emailText.setText(profesor.getKontaktTelefon());
		titulaText.setText(profesor.getTitula());
		zvanjeText.setText(profesor.getZvanje());
	}

	@Override
	protected void potvrdaListener(JButton potvrdaButton) {
		potvrdaButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String ime = ProfesorEditDialog.this.getImeText();
				String prezime = ProfesorEditDialog.this.getPrezimeText();
				String brojLicne = ProfesorEditDialog.this.getBrojLicneText();
				String adresaStanovanja = ProfesorEditDialog.this.getAdresaStanovanjaText();
				String kontaktTelefon = ProfesorEditDialog.this.getBrojTelefonaText();
				String email = ProfesorEditDialog.this.getEmailText();
				String adresaKancelarije = ProfesorEditDialog.this.getAdresaKancelarijeText();
				String titula = ProfesorEditDialog.this.getTitulaText();
				String zvanje = ProfesorEditDialog.this.getZvanjeText();
				String datumRodjenja = ProfesorEditDialog.this.getDatumRodjenjaText();
				
				try {
					ProfesoriController.getInstance().izmeni(ime, prezime, brojLicne, adresaStanovanja, kontaktTelefon,
							email, adresaKancelarije, titula, zvanje, datumRodjenja);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
					return;
				}
				ProfesorEditDialog.super.dispose();
			}
		});
	}
}
