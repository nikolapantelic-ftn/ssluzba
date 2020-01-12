package ssluzba;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import ssluzba.app.BazaPredmeta;
import ssluzba.app.BazaProfesora;
import ssluzba.app.BazaStudenata;
import ssluzba.app.controllers.ProfesoriController;
import ssluzba.app.views.MainFrame;

public class MainApp {
	public static void main(String[] args) {
		MainFrame mf = MainFrame.getInstance();
		mf.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					BazaStudenata.getInstance().serialize();
					BazaProfesora.getInstance().serialize();
					BazaPredmeta.getInstance().serialize();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
	}

}
