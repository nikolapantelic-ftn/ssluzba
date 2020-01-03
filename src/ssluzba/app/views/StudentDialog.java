package ssluzba.app.views;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class StudentDialog extends JDialog {

	private static final long serialVersionUID = -1851059461360849827L;
	
	private boolean samofinansiranje, budzet;
	
	public StudentDialog() {
		this.setTitle("Dodavanje Studenta");
		this.setLayout(new FlowLayout());
		this.setModal(true);
		this.setSize(400,350);
		this.setLocationRelativeTo(MainFrame.getInstance());
		initComponents();
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	private void initComponents() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.setPreferredSize(new Dimension(360, 300));
		
		JPanel imePane = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		imePane.add(new JLabel("Ime "));
		JTextField imeText = new JTextField(20);
		imePane.add(imeText);
		
		JPanel prezimePane = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		prezimePane.add(new JLabel("Prezime "));
		JTextField prezimeText = new JTextField(20);
		prezimePane.add(prezimeText);
		
		JPanel datumRodjenjaPane = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		datumRodjenjaPane.add(new JLabel("Datum Rodjenja "));
		JTextField datumRodjenjaText = new JTextField(20);
		datumRodjenjaPane.add(datumRodjenjaText);
		
		JPanel brojTelefonaPane = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		brojTelefonaPane.add(new JLabel("Broj Telefona "));
		JTextField brojTelefonaText = new JTextField(20);
		brojTelefonaPane.add(brojTelefonaText);
		
		JPanel brojIndeksaPane = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		brojIndeksaPane.add(new JLabel("Broj Indeksa "));
		JTextField brojIndeksaText = new JTextField(20);
		brojIndeksaPane.add(brojIndeksaText);
		
		JPanel godinaStudijaPane = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		godinaStudijaPane.add(new JLabel("Trenutna Godina Studija "));
		String[] godinaStudijaStrings = {"I (prva)", "II (druga)", "III (treca)", "IV (cetvrta)"};
		JComboBox<String> godinaStudijaCombo = new JComboBox<String>(godinaStudijaStrings);
		godinaStudijaPane.add(godinaStudijaCombo);
		
		ButtonGroup finansiranjeGroup = new ButtonGroup();
		JPanel finansiranjePane = new JPanel();
		finansiranjePane.setLayout(new BoxLayout(finansiranjePane, BoxLayout.PAGE_AXIS));
		finansiranjePane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Nacin finansiranja"));
		JRadioButton samofinansiranjeButton = new JRadioButton("Samofinansiranje", samofinansiranje);
		JRadioButton budzetButton = new JRadioButton("Budzet", budzet);
		finansiranjeGroup.add(samofinansiranjeButton);
		finansiranjeGroup.add(budzetButton);
		finansiranjePane.add(samofinansiranjeButton);
		finansiranjePane.add(budzetButton);
		
		JPanel buttonPane = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		JButton potvrdaButton = new JButton("Potvrda");
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
		panel.add(brojIndeksaPane);
		panel.add(godinaStudijaPane);
		panel.add(finansiranjePane);
		panel.add(buttonPane);
		
		this.add(panel);
	}
	
	public void newStudentListener() {

}
}
