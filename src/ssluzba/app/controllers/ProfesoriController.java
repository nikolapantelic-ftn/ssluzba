package ssluzba.app.controllers;

import ssluzba.app.BazaProfesora;
import ssluzba.app.views.ProfesorJTable;

public class ProfesoriController  {

	private static ProfesoriController instance=null;
	public static ProfesoriController getInstance() {
		if(instance==null)
			instance=new ProfesoriController();
		return instance;
	}
	public ProfesoriController() {
		// TODO Auto-generated constructor stub
	}

	public void deleteProfesor(int row) {
		BazaProfesora.getInstance().deleteProfesor(row);
		ProfesorJTable.getInstance().azurirajPrikaz();
	}

}
