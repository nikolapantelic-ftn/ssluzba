package ssluzba.app.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import ssluzba.app.Status;
import ssluzba.app.Student;
import ssluzba.app.controllers.StudentiController;

public class StudentEditDialog extends StudentDialog{

	private static final long serialVersionUID = -3612974111767161969L;

	public StudentEditDialog(Student student) {
		super();
		imeText.setText(student.getIme());
		prezimeText.setText(student.getPrezime());
		datumRodjenjaText.setText(student.getDatumRodjenja().toString());
		adresaStanovanjaText.setText(student.getAdresaStanovanja());
		brojTelefonaText.setText(student.getKontaktTelefon());
		brojIndeksaText.setText(student.getBrIndeksa());
		emailText.setText(student.getEmail());
		godinaStudijaCombo.setSelectedIndex(student.getGodinaStudija()-1);
		samofinansiranjeButton.setSelected(student.getStatus()==Status.S ? true : false);
		budzetButton.setSelected(student.getStatus()==Status.B ? true : false);
	}
	
	@Override
	protected void potvrdaListener(JButton potvrdaButton) {
		potvrdaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ime = StudentEditDialog.this.getImeText();
				String prezime = StudentEditDialog.this.getPrezimeText();
				String adresaStanovanja = StudentEditDialog.this.getAdresaStanovanjaText();
				String kontaktTelefon = StudentEditDialog.this.getBrojTelefonaText();
				String email = StudentEditDialog.this.getEmailText();
				String brIndeksa = StudentEditDialog.this.getBrojIndeksaText();
				String datumRodjenja = StudentEditDialog.this.getDatumRodjenjaText();
				int godinaStudija = StudentEditDialog.this.getGodinaStudijaText();
				boolean samofinansiranje = StudentEditDialog.this.getSamofinansiranjeText();
				boolean budzet = StudentEditDialog.this.getBudzetText();
				
				StudentiController.getInstance().izmeni(ime, prezime, adresaStanovanja, kontaktTelefon, email, brIndeksa,
						datumRodjenja, godinaStudija, samofinansiranje, budzet);
				StudentEditDialog.super.dispose();
			}
		});
	}
}
