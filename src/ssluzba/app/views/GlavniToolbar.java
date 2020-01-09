package ssluzba.app.views;





import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import ssluzba.app.BazaPredmeta;
import ssluzba.app.controllers.PredmetiController;
import ssluzba.app.controllers.ProfesoriController;
import ssluzba.app.controllers.StringController;
import ssluzba.app.controllers.StudentiController;



public class GlavniToolbar extends JToolBar {

	private static final long serialVersionUID = 1209699209668701828L;
	private JButton addStud;
	private JButton addPr;
	private JButton addProf;
	private JButton editS;
	private JButton delS;
	private JButton editP;
	private JButton editPr;
	private JButton delP;
	private JButton delPr;
	private JTextField pretraga;
	private JButton findB;
	private JButton addSP;
	private JButton addPP;
	private JTextField pretragaP;
	private JTextField pretragaPR;
	private JButton findP;
	private JButton findPr;
	private JTable tabelaPredmeta;
	private JTable tabelaProfesora;
	private boolean aktivirajPretraguPredmeta;
	private boolean pretragaStudenata;
	
	private static GlavniToolbar instance=null;
	

	public static GlavniToolbar getInstance() {
		if(instance==null)
			instance=new GlavniToolbar();
		return instance;
	}
	public void setTabelaPredmeta(JTable tabela) {
		this.tabelaPredmeta=tabela;
	}
	public void setTabelaProfesora(JTable tabela) {
		this.tabelaProfesora=tabela;
	}
	
	public JButton getAddStud() {
		return addStud;
	}

	public JButton getAddPr() {
		return addPr;
	}

	public JButton getAddProf() {
		return addProf;
	}

	public JButton getAddSP() {
		return addSP;
	}

	public JButton getAddPP() {
		return addPP;
	}

	public GlavniToolbar() {
		
		
		super(SwingConstants.HORIZONTAL);
		setFloatable(false);
		setBackground(new Color(153,204,255));
		
		
		addStud = new JButton();
		addStud.setToolTipText("Dodaj studenta");
		addStud.setIcon(new ImageIcon("images/add.png"));
		addStud.setMnemonic(KeyEvent.VK_D );
		
		add(addStud);
		addStud.setOpaque(false);
		addStud.setContentAreaFilled(false);
		addStud.setBorderPainted(false);

		addSeparator();

		editS = new JButton();
		editS.setToolTipText("Izmeni studenta ");
		editS.setIcon(new ImageIcon("images/pencil-icon.png"));
		
		editS.setOpaque(false);
		editS.setContentAreaFilled(false);
		editS.setBorderPainted(false);
		add(editS);
 
		addSeparator();

		delS = new JButton();
		delS.setToolTipText("Obrisi studenta");
		delS.setIcon(new ImageIcon("images/delete.png"));
		
		setButton(delS);
		
		add(delS);
		addSeparator();
		
		
		add(Box.createHorizontalStrut(550));
		
		
	
		pretraga=new JTextField();
		//pretraga.setBorder(null);
		add(pretraga);
		
		findB=new JButton();
		findB.setIcon(new ImageIcon("images/find.png"));
		findB.setToolTipText("Pretrazi");
		findB.setOpaque(false);
		findB.setContentAreaFilled(false);
		findB.setBorderPainted(false);
		add(findB);
		
		addProf=new JButton();
		addProf.setToolTipText("Dodaj profesora");
		addProf.setIcon(new ImageIcon("images/add.png"));
		addProf.setMnemonic(KeyEvent.VK_N);
	
		editP=new JButton();
		editP.setToolTipText("Izmeni profesora");
		editP.setIcon(new ImageIcon("images/pencil-icon.png"));
		editP.setMnemonic(KeyEvent.VK_E);
		delP=new JButton();
		delP.setToolTipText("Izmeni profesora");
		delP.setIcon(new ImageIcon("images/delete.png"));
		delP.setMnemonic(KeyEvent.VK_D);
		findP=new JButton();
		findP.setToolTipText("Pronadji profesora");
		findP.setIcon(new ImageIcon("images/find.png"));
		findP.setMnemonic(KeyEvent.VK_F);
		addPr=new JButton();
		addPr.setToolTipText("Dodaj predmet");
		addPr.setIcon(new ImageIcon("images/add.png"));
		addPr.setMnemonic(KeyEvent.VK_N);
		addPredmetListener();
		addStudentListener();
		editPr=new JButton();
		delPr=new JButton();
		addPP=new JButton();
		addSP=new JButton();
		findPr=new JButton();
		pretragaPR=new JTextField();
		tabelaPredmeta=new PredmetJTable();
		pretragaP=new JTextField();
		
		editPredmetListener();
		delPredmetListener();
		addProfNaPredmetListener();
		delProfesorListener();
		addStudentNaPredmetListener();
		findPredmetListener();
		findProfesorListener();
		findStudentListener();
		dodajProfesoraListener();
	}
	public boolean getAktivirajPretraguPredmeta() {
		return aktivirajPretraguPredmeta;
	}

	public void setButton(JButton button) {
		button.setOpaque(false);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
	}
	
	
	public void makeToolbar(int tab) {
		this.removeAll();
		this.revalidate();
		this.repaint();
	//	pretragaP.setText();
		if(tab==0) {
		
			
			add(addStud);
			addSeparator();
			add(editS);
			addSeparator();
			add(delS);
			addSeparator();
			add(Box.createHorizontalStrut(550));
			add(pretraga);
			add(findB);
		}else if(tab==1) {
			
			setButton(addProf);
			add(addProf);
			addSeparator();
			
			setButton(editP);
			add(editP);
			addSeparator();
		
			setButton(delP);
			add(delP);
			addSeparator();
			add(Box.createHorizontalStrut(550));
			
			add(pretragaP);
			
			setButton(findP);
			add(findP);
			}else {
			
			
			setButton(addPr);
			add(addPr);
			addSeparator();
			
			editPr.setToolTipText("Izmeni predmet");
			editPr.setIcon(new ImageIcon("images/pencil-icon.png"));
			editPr.setMnemonic(KeyEvent.VK_E);
			setButton(editPr);
			add(editPr);
			addSeparator();
			
			delPr.setToolTipText("Izbrisi predmet");
			delPr.setIcon(new ImageIcon("images/delete.png"));
			delPr.setMnemonic(KeyEvent.VK_D);
			setButton(delPr);
			add(delPr);
			addSeparator();
			
			addSP.setToolTipText("Dodaj studenta na predmet");
			addSP.setIcon(new ImageIcon("images/addS.png"));
			addSP.setMnemonic(KeyEvent.VK_S);
			setButton(addSP);
			add(addSP);
			addSeparator();
			
			addPP.setToolTipText("Dodaj profesora na predmet");
			addPP.setIcon(new ImageIcon("images/addPr.png"));
			addPP.setMnemonic(KeyEvent.VK_P);
			setButton(addPP);
			add(addPP);
			addSeparator();
			add(Box.createHorizontalStrut(405));
			
			add(pretragaPR);
			
			findPr.setToolTipText("Pronadji predmet");
			findPr.setIcon(new ImageIcon("images/find.png"));
			findPr.setMnemonic(KeyEvent.VK_F);
			setButton(findPr);
			add(findPr);
			}
		
			}
	public void addStudentListener() {
		addStud.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JDialog jp=new StudentDialog();
				jp.setVisible(true);
				
			}
		});
	}
	public void addPredmetListener() {
		addPr.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JDialog jp=new PredmetDialog();
				jp.setVisible(true);
				
			}
		});
	}
	public void delPredmetListener() {
		delPr.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(tabelaPredmeta.getSelectedRow()==-1)
					return;
				int choice=JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da obrisete predmet?");
				if (choice == JOptionPane.YES_OPTION) {
					PredmetiController.getInstance().izbrisi(PredmetiController.getInstance().getPredmet(tabelaPredmeta.getSelectedRow()).getSifra());
					JOptionPane.showMessageDialog(null, "Predmet obrisan!");
				} else {
					JOptionPane.showMessageDialog(null, "Otkazano");
				}
				
				
			}
		});
	}
	
	public void editPredmetListener() {
		
		editPr.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(tabelaPredmeta.getSelectedRow()==-1)
					return;
				JDialog jp=new PredmetEditDialog(BazaPredmeta.getInstance().getRow(tabelaPredmeta.getSelectedRow()));
				jp.setVisible(true);
				
			}
		});
	}
	
	public void addProfNaPredmetListener() {
		addPP.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(tabelaPredmeta.getSelectedRow()==-1)
					return;
				String brLicne=JOptionPane.showInputDialog("Broj licne karte profesora");
					if(brLicne==null)
						return;
					if(ProfesoriController.getInstance().pretraziPoLicnoj(brLicne)!=null) {
						ProfesoriController.getInstance().setProfesorNaTrenutniPredmet(ProfesoriController.getInstance().pretraziPoLicnoj(brLicne));
						PredmetJTable.getInstance().azurirajPrikaz();
					}
					else {
						JOptionPane.showMessageDialog(null, "Uneti profesor ne postoji!", "Greska", JOptionPane.ERROR_MESSAGE);
					}
				}
				});
	}
	public void dodajProfesoraListener() {
		addProf.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JDialog dialog = new ProfesorDialog();
				dialog.setVisible(true);
			}
		});
	}
	
	public void delProfesorListener() {
		delP.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(tabelaProfesora.getSelectedRow()==-1)
					return;
				int choice=JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da obrisete profesora?");
				if (choice == JOptionPane.YES_OPTION) {
					System.out.println(tabelaPredmeta.getSelectedRow());
					ProfesoriController.getInstance().deleteProfesor(ProfesoriController.getInstance().getProfesor(tabelaProfesora.getSelectedRow()).getBrojLicne());
					JOptionPane.showMessageDialog(null, "Profesor obrisan!");
				} else {
					JOptionPane.showMessageDialog(null, "Otkazano");
				}
				
				
			}
		});
	}
	public void findStudentListener() {
		findB.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String pretragaString = pretraga.getText();
				if(pretragaString.isEmpty()) return;
				int errorCode = StudentiController.getInstance().pretraziStudente(pretragaString);
				if(errorCode == 1) {
					JOptionPane.showMessageDialog(null, "Pogresno unet kriterijum");
				}else if(errorCode == 2) {
					JOptionPane.showMessageDialog(null, "Nepostojeca kolona");
				}else {
					GlavniToolbar.getInstance().setPretragaStudenata(true);
					StudentJTable.getInstance().azurirajPrikaz();
				}
			}
		});
	}
	
	public void findPredmetListener() {
		
		findPr.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String string=pretragaPR.getText();
				PredmetiController.getInstance().ocistiPretragu();
				if(string.isEmpty()) {
					return;
				}
				else {
					if(!StringController.proveriString(string)) {
						JOptionPane.showMessageDialog(null, "Neispravan oblik unosa!");
						return;
					}
					
					
					String[] vrednosti=PredmetiController.getInstance().parseString(string);
					if(vrednosti[4].equals("nema")) {
						JOptionPane.showMessageDialog(null, "Polje ne postoji!");
						return;
					}
					PredmetiController.getInstance().pretraziPredmete(vrednosti[0],vrednosti[1], vrednosti[2], vrednosti[3]);
					
					if(PredmetiController.getInstance().getPronadjeno()==false) {
						JOptionPane.showMessageDialog(null, "Nije pronadjeno nista za zadati kriterijum");
					}
				}
				
				
				
			}
		});
		
	}
	public void findProfesorListener() {
		
		findP.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String string=pretragaP.getText();
				ProfesoriController.getInstance().ocistiPretragu();
				
				if(string.isEmpty()) {
					return;
				}
				else {
					if(!StringController.proveriString(string)) {
						JOptionPane.showMessageDialog(null, "Neispravan oblik unosa!");
						return;
					}
					
					String[] vrednosti=ProfesoriController.getInstance().parseString(string);
					if(vrednosti[9].equals("nema")) {
						JOptionPane.showMessageDialog(null, "Polje ne postoji!");
						return;
					}
						
					ProfesoriController.getInstance().pretraziProfesore(vrednosti[0], vrednosti[1], vrednosti[2],vrednosti[3], vrednosti[4], vrednosti[5], vrednosti[6], vrednosti[7], vrednosti[8]);
					
					if(ProfesoriController.getInstance().getPronadjeno()==false) {
						JOptionPane.showMessageDialog(null, "Nije pronadjeno nista za zadati kriterijum");
					}
				}
				
				
				
			}
		});
		
	}
	public void addStudentNaPredmetListener() {
		addSP.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(tabelaPredmeta.getSelectedRow()==-1)
					return;
				JDialog jp=new DodajStudentaNaPredmetDialog(BazaPredmeta.getInstance().getRow(tabelaPredmeta.getSelectedRow()));
				jp.setVisible(true);
				
			}
		});
	}
	public boolean isPretragaStudenata() {
		return pretragaStudenata;
	}
	public void setPretragaStudenata(boolean pretragaStudenata) {
		this.pretragaStudenata = pretragaStudenata;
	}

}
