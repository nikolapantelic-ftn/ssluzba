package ssluzba.app.views;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import ssluzba.app.BazaStudenata;
import ssluzba.app.controllers.StudentiController;

public class StudentDialog extends JDialog {

	private static final long serialVersionUID = -1851059461360849827L;
	protected JRadioButton samofinansiranjeButton, budzetButton;
	protected JTextField imeText;
	protected JTextField prezimeText;
	protected JTextField datumRodjenjaText;
	protected JTextField adresaStanovanjaText;
	protected JTextField brojTelefonaText;
	protected JTextField brojIndeksaText;
	protected JTextField emailText;
	protected JComboBox<String> godinaStudijaCombo;

	public StudentDialog() {
		this.setTitle("Dodavanje studenta");
		this.setLayout(new FlowLayout());
		this.setModal(true);
		this.setSize(400, 450);
		this.setLocationRelativeTo(MainFrame.getInstance());
		initComponents();
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	private void initComponents() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.setPreferredSize(new Dimension(380, 400));

		JPanel imePane = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		imePane.add(new JLabel("Ime "));
		imeText = new JTextField(20);
		imePane.add(imeText);

		JPanel prezimePane = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		prezimePane.add(new JLabel("Prezime "));
		prezimeText = new JTextField(20);
		prezimePane.add(prezimeText);

		JPanel datumRodjenjaPane = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		datumRodjenjaPane.add(new JLabel("Datum Rodjenja "));
		datumRodjenjaText = new JTextField(20);
		datumRodjenjaPane.add(datumRodjenjaText);

		JPanel adresaStanovanjaPane = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		adresaStanovanjaPane.add(new JLabel("Adresa Stanovanja"));
		adresaStanovanjaText = new JTextField(20);
		adresaStanovanjaPane.add(adresaStanovanjaText);

		JPanel brojTelefonaPane = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		brojTelefonaPane.add(new JLabel("Broj Telefona "));
		brojTelefonaText = new JTextField(20);
		brojTelefonaPane.add(brojTelefonaText);

		JPanel emailPane = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		emailPane.add(new JLabel("E-mail "));
		emailText = new JTextField(20);
		emailPane.add(emailText);

		JPanel brojIndeksaPane = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		brojIndeksaPane.add(new JLabel("Broj Indeksa "));
		brojIndeksaText = new JTextField(20);
		brojIndeksaPane.add(brojIndeksaText);

		JPanel godinaStudijaPane = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		godinaStudijaPane.add(new JLabel("Trenutna Godina Studija "));
		String[] godinaStudijaStrings = { "I (prva)", "II (druga)", "III (treca)", "IV (cetvrta)" };
		godinaStudijaCombo = new JComboBox<String>(godinaStudijaStrings);
		godinaStudijaPane.add(godinaStudijaCombo);

		ButtonGroup finansiranjeGroup = new ButtonGroup();
		JPanel finansiranjePane = new JPanel();
		finansiranjePane.setLayout(new BoxLayout(finansiranjePane, BoxLayout.PAGE_AXIS));
		finansiranjePane
				.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Nacin finansiranja"));
		samofinansiranjeButton = new JRadioButton("Samofinansiranje");
		budzetButton = new JRadioButton("Budzet");
		finansiranjeGroup.add(samofinansiranjeButton);
		finansiranjeGroup.add(budzetButton);
		finansiranjePane.add(samofinansiranjeButton);
		finansiranjePane.add(budzetButton);

		JPanel buttonPane = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		JButton potvrdaButton = new JButton("Potvrda");
		potvrdaListener(potvrdaButton);
		JButton odustanakButton = new JButton("Odustanak");
		odustanakButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentDialog.super.dispose();
			}
		});
		buttonPane.add(odustanakButton);
		buttonPane.add(potvrdaButton);

		panel.add(imePane);
		panel.add(prezimePane);
		panel.add(datumRodjenjaPane);
		panel.add(brojTelefonaPane);
		panel.add(emailPane);
		panel.add(adresaStanovanjaPane);
		panel.add(brojIndeksaPane);
		panel.add(godinaStudijaPane);
		panel.add(finansiranjePane);
		panel.add(buttonPane);

		this.add(panel);
	}

	protected int getGodinaStudija() {
		return godinaStudijaCombo.getSelectedIndex() + 1;
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

	protected String getBrojIndeksaText() {
		return brojIndeksaText.getText();
	}

	protected boolean getSamofinansiranjeText() {
		return samofinansiranjeButton.isSelected();
	}

	protected boolean getBudzetText() {
		return budzetButton.isSelected();
	}

	protected void potvrdaListener(JButton potvrdaButton) {
		potvrdaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ime = StudentDialog.this.getImeText();
				String prezime = StudentDialog.this.getPrezimeText();
				String adresaStanovanja = StudentDialog.this.getAdresaStanovanjaText();
				String kontaktTelefon = StudentDialog.this.getBrojTelefonaText();
				String email = StudentDialog.this.getEmailText();
				String brIndeksa = StudentDialog.this.getBrojIndeksaText();
				if (!brIndeksa.matches("[a-z0-9]{2,3}-[0-9]{1,3}-[0-9]{4}")) {
					JOptionPane.showMessageDialog(null,
							"Broj indeksa mora biti u formatu 'xx-zz-yyyy' gde je 'xx' oznaka smera, 'zz' broj upisa i yyyy godina upisa");
					return;
				}
				String datumRodjenja = StudentDialog.this.getDatumRodjenjaText();
				try {
					LocalDate.parse(datumRodjenja, BazaStudenata.getInstance().getDateFormatter());
				} catch (DateTimeParseException pe) {
					JOptionPane.showMessageDialog(null, "Datumi moraju biti u formatu 'dd.mm.yyyy.'");
					return;
				}
				int godinaStudija = StudentDialog.this.getGodinaStudija();
				boolean samofinansiranje = StudentDialog.this.getSamofinansiranjeText();
				boolean budzet = StudentDialog.this.getBudzetText();
				try {
					StudentiController.getInstance().dodaj(ime, prezime, adresaStanovanja, kontaktTelefon, email, brIndeksa,
							datumRodjenja, godinaStudija, samofinansiranje, budzet);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
					return;
				}
				StudentDialog.super.dispose();
			}
		});
	}

}
