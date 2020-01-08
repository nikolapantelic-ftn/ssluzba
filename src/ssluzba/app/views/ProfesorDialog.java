package ssluzba.app.views;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ssluzba.app.controllers.ProfesoriController;

public class ProfesorDialog extends JDialog {

	private static final long serialVersionUID = 3114665161777399839L;

	protected JTextField imeText;
	protected JTextField prezimeText;
	protected JTextField brojLicneText;
	protected JTextField datumRodjenjaText;
	protected JTextField adresaStanovanjaText;
	protected JTextField adresaKancelarijeText;
	protected JTextField brojTelefonaText;
	protected JTextField emailText;
	protected JTextField titulaText;
	protected JTextField zvanjeText;

	public ProfesorDialog() {
		this.setTitle("Dodavanje profesora");
		this.setLayout(new FlowLayout());
		this.setModal(true);
		this.setSize(400, 480);
		this.setLocationRelativeTo(MainFrame.getInstance());
		initComponents();
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	private void initComponents() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.setPreferredSize(new Dimension(380, 430));

		JPanel imePane = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		imePane.add(new JLabel("Ime "));
		imeText = new JTextField(20);
		imePane.add(imeText);

		JPanel prezimePane = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		prezimePane.add(new JLabel("Prezime "));
		prezimeText = new JTextField(20);
		prezimePane.add(prezimeText);

		JPanel brojLicnePane = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		brojLicnePane.add(new JLabel("Broj licne karte "));
		brojLicneText = new JTextField(20);
		brojLicnePane.add(brojLicneText);

		JPanel datumRodjenjaPane = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		datumRodjenjaPane.add(new JLabel("Datum Rodjenja "));
		datumRodjenjaText = new JTextField(20);
		datumRodjenjaPane.add(datumRodjenjaText);

		JPanel adresaStanovanjaPane = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		adresaStanovanjaPane.add(new JLabel("Adresa Stanovanja "));
		adresaStanovanjaText = new JTextField(20);
		adresaStanovanjaPane.add(adresaStanovanjaText);

		JPanel adresaKancelarijePane = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		adresaKancelarijePane.add(new JLabel("Adresa Kancelarije "));
		adresaKancelarijeText = new JTextField(20);
		adresaKancelarijePane.add(adresaKancelarijeText);

		JPanel brojTelefonaPane = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		brojTelefonaPane.add(new JLabel("Broj Telefona "));
		brojTelefonaText = new JTextField(20);
		brojTelefonaPane.add(brojTelefonaText);

		JPanel emailPane = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		emailPane.add(new JLabel("E-mail "));
		emailText = new JTextField(20);
		emailPane.add(emailText);

		JPanel titulaPane = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		titulaPane.add(new JLabel("Titula "));
		titulaText = new JTextField(20);
		titulaPane.add(titulaText);

		JPanel zvanjePane = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		zvanjePane.add(new JLabel("Zvanje "));
		zvanjeText = new JTextField(20);
		zvanjePane.add(zvanjeText);

		JPanel buttonPane = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		JButton potvrdaButton = new JButton("Potvrda");
		potvrdaListener(potvrdaButton);
		JButton odustanakButton = new JButton("Odustanak");
		odustanakButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProfesorDialog.super.dispose();
			}
		});
		buttonPane.add(odustanakButton);
		buttonPane.add(potvrdaButton);

		panel.add(imePane);
		panel.add(prezimePane);
		panel.add(brojLicnePane);
		panel.add(datumRodjenjaPane);
		panel.add(brojTelefonaPane);
		panel.add(emailPane);
		panel.add(adresaStanovanjaPane);
		panel.add(adresaKancelarijePane);
		panel.add(titulaPane);
		panel.add(zvanjePane);
		panel.add(buttonPane);

		this.add(panel);
	}

	protected String getImeText() {
		return imeText.getText();
	}

	protected String getPrezimeText() {
		return prezimeText.getText();
	}

	protected String getDatumRodjenjaText() {
		return datumRodjenjaText.getText();
	}

	protected String getAdresaStanovanjaText() {
		return adresaStanovanjaText.getText();
	}

	protected String getBrojTelefonaText() {
		return brojTelefonaText.getText();
	}

	protected String getEmailText() {
		return emailText.getText();
	}

	protected String getZvanjeText() {
		return zvanjeText.getText();
	}

	protected String getTitulaText() {
		return titulaText.getText();
	}

	protected String getAdresaKancelarijeText() {
		return adresaKancelarijeText.getText();
	}

	protected String getBrojLicneText() {
		return brojLicneText.getText();
	}

	protected void potvrdaListener(JButton potvrdaButton) {
		potvrdaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ime = ProfesorDialog.this.getImeText();
				String prezime = ProfesorDialog.this.getPrezimeText();
				String brojLicne = ProfesorDialog.this.getBrojLicneText();
				String adresaStanovanja = ProfesorDialog.this.getAdresaStanovanjaText();
				String kontaktTelefon = ProfesorDialog.this.getBrojTelefonaText();
				String email = ProfesorDialog.this.getEmailText();
				String adresaKancelarije = ProfesorDialog.this.getAdresaKancelarijeText();
				String titula = ProfesorDialog.this.getTitulaText();
				String zvanje = ProfesorDialog.this.getZvanjeText();
				String datumRodjenja = ProfesorDialog.this.getDatumRodjenjaText();

				try {
					ProfesoriController.getInstance().dodaj(ime, prezime, brojLicne, adresaStanovanja, kontaktTelefon,
							email, adresaKancelarije, titula, zvanje, datumRodjenja);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
					return;
				}
				ProfesorDialog.super.dispose();
			}
		});
	}

}
