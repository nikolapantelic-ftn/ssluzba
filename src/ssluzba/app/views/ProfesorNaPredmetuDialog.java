package ssluzba.app.views;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ssluzba.app.BazaPredmeta;
import ssluzba.app.Profesor;




public class ProfesorNaPredmetuDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7598374260803848470L;
	private JLabel ime,prezime,brojLicne,adresa,kontaktTelefon,eMail,adresaKancelarije,titula,zvanje;
	private JTextField imeT,prezimeT,brojLicneT,adresaT,kontaktTelefonT,eMailT,adresaKancelarijeT,titulaT,zvanjeT; 
	private JButton obrisi;
	private Profesor p;
	
	ProfesorNaPredmetuDialog(Profesor p){
		this.p=p;
		this.setTitle("Profesor na predmetu");
		this.setLayout(new GridLayout(10,2));
		this.setModal(true);
		this.setSize(400,400);
		this.setLocationRelativeTo(MainFrame.getInstance());
		addComponents();
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		addListener();
		
	}
	private void addComponents() {
		ime=new JLabel("Ime:");
		prezime=new JLabel("Prezime:");
		brojLicne=new JLabel("Broj licne:");
		adresa=new JLabel("Adresa:");
		kontaktTelefon=new JLabel("Kontakt telefon:");
		eMail=new JLabel("E-mail:");
		adresaKancelarije=new JLabel("Adresa kancelarije:");
		titula=new JLabel("Titula:");
		zvanje=new JLabel("Zvanje:");
		imeT=new JTextField();
		imeT.setText(p.getIme());
		imeT.setEditable(false);
		prezimeT=new JTextField();
		prezimeT.setText(p.getPrezime());
		prezimeT.setEditable(false);
		brojLicneT=new JTextField();
		brojLicneT.setText(p.getBrojLicne());
		brojLicneT.setEditable(false);
		adresaT=new JTextField();
		adresaT.setText(p.getAdresa());
		adresaT.setEditable(false);
		kontaktTelefonT=new JTextField();
		kontaktTelefonT.setText(p.getKontaktTelefon());
		kontaktTelefonT.setEditable(false);
		eMailT=new JTextField();
		eMailT.setText(p.geteMail());
		eMailT.setEditable(false);
		adresaKancelarijeT=new JTextField();
		adresaKancelarijeT.setText(p.getAdresaKancelarije());
		adresaKancelarijeT.setEditable(false);
		titulaT=new JTextField();
		titulaT.setText(p.getTitula());
		titulaT.setEditable(false);
		zvanjeT=new JTextField();
		zvanjeT.setText(p.getZvanje());
		zvanjeT.setEditable(false);
		obrisi=new JButton("Obrisi");
		
	    JPanel panel1=new JPanel();
	    panel1.add(ime);
	    panel1.add(imeT);
	    JPanel panel2=new JPanel();
	    panel2.add(prezime);
	    panel2.add(prezimeT);
	    JPanel panel3=new JPanel();
	    panel3.add(brojLicne);
	    panel3.add(brojLicneT);
	    JPanel panel4=new JPanel();
	    panel4.add(adresa);
	    panel4.add(adresaT);
	    JPanel panel5=new JPanel();
	    panel5.add(kontaktTelefon);
	    panel5.add(kontaktTelefonT);
	    JPanel panel6=new JPanel();
	    panel6.add(eMail);
	    panel6.add(eMailT);
	    JPanel panel7=new JPanel();
	    panel7.add(adresaKancelarije);
	    panel7.add(adresaKancelarijeT);
	    JPanel panel8=new JPanel();
	    panel8.add(titula);
	    panel8.add(titulaT);
	    JPanel panel9=new JPanel();
	    panel9.add(zvanje);
	    panel9.add(zvanjeT);
	    JPanel panel10=new JPanel();
	    panel10.add(obrisi);
	   
	    
	    
	    this.add(panel1);
	    this.add(panel2);
	    this.add(panel3);
	    this.add(panel4);
	    this.add(panel5);
	    this.add(panel6);
	    this.add(panel7);
	    this.add(panel8);
	    this.add(panel9);
	    this.add(panel10);
	    	
	}
	
	public void addListener() {
		obrisi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				BazaPredmeta.getInstance().getRow(PredmetJTable.getInstance().getSelectedRow()).removeProfesor();
				ProfesorNaPredmetuDialog.super.dispose();
			}
		});
	}

}
