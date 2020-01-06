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

import ssluzba.app.Predmet;
import ssluzba.app.controllers.StudentiController;

public class DodajStudentaNaPredmetDialog extends JDialog {

	private static final long serialVersionUID = 1053881480060581546L;

	protected JTextField brojIndeksaText;
	private Predmet predmet;

	public DodajStudentaNaPredmetDialog(Predmet p) {
		this.predmet = p;
		this.setTitle("Dodavanje studenta na predmet");
		this.setLayout(new FlowLayout());
		this.setModal(true);
		this.setSize(370, 200);
		this.setLocationRelativeTo(MainFrame.getInstance());
		initComponents();
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	private void initComponents() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.setPreferredSize(new Dimension(350, 150));

		JPanel brojIndeksaPane = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		brojIndeksaPane.add(new JLabel("Broj Indeksa "));
		brojIndeksaText = new JTextField(20);
		brojIndeksaPane.add(brojIndeksaText);

		JPanel buttonPane = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		JButton potvrdaButton = new JButton("Potvrda");
		potvrdaListener(potvrdaButton);
		JButton odustanakButton = new JButton("Odustanak");
		odustanakButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DodajStudentaNaPredmetDialog.super.dispose();
			}
		});
		buttonPane.add(odustanakButton);
		buttonPane.add(potvrdaButton);
		panel.add(brojIndeksaPane);
		panel.add(buttonPane);
		this.add(panel);
	}

	private void potvrdaListener(JButton potvrdaButton) {
		potvrdaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String brojIndeksa = DodajStudentaNaPredmetDialog.this.getBrojIndeksaText();
				int errorCode = StudentiController.getInstance().dodajStudentaNaPredmet(brojIndeksa,
						DodajStudentaNaPredmetDialog.this.getPredmet());
				switch(errorCode) {
				case 0:
					JOptionPane.showMessageDialog(null, "Student uspesno dodat.");
					DodajStudentaNaPredmetDialog.super.dispose();
					break;
				case 1:
					JOptionPane.showMessageDialog(null, "Student je vec upisan na ovom predmetu");
					break;
				case 2:
					JOptionPane.showMessageDialog(null, "Student nije na odgovarajucoj godini");
					break;
				case 3:
					JOptionPane.showMessageDialog(null, "Nepostojeci student");
					break;
				}		
			}
		});

	}

	protected String getBrojIndeksaText() {
		return brojIndeksaText.getText();
	}

	protected Predmet getPredmet() {
		return predmet;
	}

}
