package ssluzba.app.views;

import java.awt.FlowLayout;

import javax.swing.JDialog;
import javax.swing.JList;

import ssluzba.app.Predmet;
import ssluzba.app.Profesor;
import ssluzba.app.controllers.ProfesoriController;

public class PredmetiProfesoraDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -988261733774940592L;
	public PredmetiProfesoraDialog() {
	this.setTitle("Predmeti profesora");
	//this.setLayout(new FlowLayout());
	this.setModal(true);
	this.setSize(400,350);
	this.setLocationRelativeTo(MainFrame.getInstance());
	addComponents();
	this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	private void addComponents() {
		Profesor p=ProfesoriController.getInstance().getProfesor(ProfesorJTable.getInstance().getSelectedRow());
		String predmeti[]=new String[p.getPredmeti().size()];
		int i=0;
		for(Predmet pr:p.getPredmeti()) {
			predmeti[i++]=" "+pr.getNaziv()+"        "+pr.getSifra();
			
		}
		
		JList lista = new JList(predmeti);
		lista.setFixedCellWidth(50);
		lista.setFixedCellHeight(50);
		add(lista);
		
	}
}
