package ssluzba.app.views;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ssluzba.app.Predmet;
import ssluzba.app.controllers.PredmetiController;

public class PredmetEditDialog extends JDialog {
	
	private JTextField sifraText;
	private JTextField nazivText;
	private JComboBox<String> semestarPrCombo ;
	private JButton potvrdaButton;
	private JComboBox<String> godinaPrCombo;
	private Predmet predmet;
	 
	public PredmetEditDialog(Predmet p) {
		this.predmet=p;
		this.setTitle("Izmena predmeta");
		this.setLayout(new FlowLayout());
		this.setModal(true);
		this.setSize(400,350);
		this.setLocationRelativeTo(MainFrame.getInstance());
		addComponents();
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		savePredmetListener();
	}
	
	private void addComponents() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.setPreferredSize(new Dimension(360, 300));
		
		JPanel sifraPr = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		sifraPr.add(new JLabel("Sifra: "));
		sifraText = new JTextField(20);
		sifraText.setText(predmet.getSifra());
		sifraPr.add(sifraText);
		
		JPanel nazivPr = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		nazivPr.add(new JLabel("Naziv: "));
		nazivText = new JTextField(20);
		nazivText.setText(predmet.getNaziv());
		nazivPr.add(nazivText);
		
		JPanel semestarPr = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		semestarPr.add(new JLabel("Semestar: "));
		String[] semestarPrStr = {"Zimski", "Letnji"};
		semestarPrCombo = new JComboBox<String>(semestarPrStr);
		semestarPrCombo.setSelectedItem(predmet.getSemestar());
		semestarPr.add(semestarPrCombo);
		
		
		
		JPanel godinaPr = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		godinaPr.add(new JLabel("Godina predmeta "));
		String[] godinaPrStr = {"I (prva)", "II (druga)", "III (treca)", "IV (cetvrta)"};
		godinaPrCombo = new JComboBox<String>(godinaPrStr);
		godinaPrCombo.setSelectedIndex(predmet.getGodinaPredmeta()-1);
		godinaPr.add(godinaPrCombo);
		
	
		
		JPanel buttonPane = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		potvrdaButton = new JButton("Potvrda");
		JButton odustanakButton = new JButton("Odustanak");
		odustanakButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PredmetEditDialog.super.dispose();
			}
		});
		buttonPane.add(odustanakButton);
		buttonPane.add(potvrdaButton);
		
		panel.add(sifraPr);
		panel.add(nazivPr);
		panel.add(semestarPr);
		panel.add(godinaPr);
		panel.add(buttonPane);
		
		
		this.add(panel);
	}
	
	public void setPredmet(Predmet p) {
		this.predmet=p;
	}
	
	public void savePredmetListener() {
		sifraText.setEditable(false);
		potvrdaButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String sifra=sifraText.getText();
				String naziv=nazivText.getText();
				String semestar=(String) semestarPrCombo.getSelectedItem();
				int godina=godinaPrCombo.getSelectedIndex()+1;
				PredmetiController.getInstance().izmeni(sifra,naziv,semestar,godina);
				PredmetEditDialog.super.dispose();
				JOptionPane.showMessageDialog(null, "Predmet izmenjen");
			}
		});
	}

}
