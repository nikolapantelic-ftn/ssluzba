package ssluzba.app.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JMenuBar;

public class Toolbar extends JMenuBar implements ActionListener {
	
	private static final long serialVersionUID = 5446478703932520621L;
	
	JButton dugmeDodaj;
	JButton dugmeIzmeni;
	JButton dugmeObrisi;

	public Toolbar() {
		dugmeDodaj = new JButton("Neka ikonica");
		dugmeIzmeni = new JButton("ikonica");
		dugmeObrisi = new JButton("Ikonica");
		
		dugmeDodaj.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//uradi nesto
			}
		});
		
		dugmeIzmeni.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//uradi nesto
			}
		});
		
		dugmeObrisi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//uradi nesto
			}
		});
		
		add(dugmeDodaj);
		add(dugmeIzmeni);
		add(dugmeObrisi);
		
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

}
