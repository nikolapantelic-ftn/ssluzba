package ssluzba.app.views;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractListModel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ssluzba.app.Predmet;
import ssluzba.app.controllers.StudentiController;

public class StudentiNaPredmetuDialog extends JDialog {

	private static final long serialVersionUID = -2101233597368032074L;
	String[] indeksiArray;
	JList<String> studentiList;
	JScrollPane studentiScrollPane;
	StudentListModel studentListModel;
	Predmet predmet;
	
	StudentiNaPredmetuDialog(Predmet p) {
		this.setTitle("Spisak studenata");
		this.setLayout(new FlowLayout());
		this.setModal(true);
		this.setSize(200, 350);
		this.setLocationRelativeTo(MainFrame.getInstance());
		predmet = p;
		indeksiArray = StudentiController.getInstance().getIndeksi(p.getStudenti());
		initComponents();
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	private void initComponents() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.setPreferredSize(new Dimension(180, 330));
		studentListModel = new StudentListModel(indeksiArray);
		studentiList =  new JList<String>(studentListModel);
		studentiScrollPane = new JScrollPane(studentiList);
		
		JPanel buttonPane = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		JButton obrisiButton = new JButton("Obrisi");
		potvrdaListener(obrisiButton);
		JButton odustanakButton = new JButton("Nazad");
		odustanakButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentiNaPredmetuDialog.super.dispose();
			}
		});
		buttonPane.add(obrisiButton);
		buttonPane.add(odustanakButton);
		
		panel.add(studentiScrollPane);
		panel.add(buttonPane);
		this.add(panel);
		
		
		
	}

	private void potvrdaListener(JButton obrisiButton) {
		obrisiButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String brIndeksa = StudentiNaPredmetuDialog.this.studentiList.getSelectedValue();
				if(brIndeksa == null) JOptionPane.showMessageDialog(null, "Niste izabrali studenta.");
				else {
					StudentiController.getInstance().izbrisiStudenta(StudentiNaPredmetuDialog.this.predmet, brIndeksa);
					JOptionPane.showMessageDialog(null, "Student obrisan!");;
					StudentiNaPredmetuDialog.this.azurirajPrikaz();
				}
			}
		});
	}
	
	public void azurirajPrikaz() {
		indeksiArray = StudentiController.getInstance().getIndeksi(predmet.getStudenti());
		StudentListModel model = new StudentListModel(indeksiArray);
		studentiList.setModel(model);
	}

}

class StudentListModel extends AbstractListModel<String> {

	private static final long serialVersionUID = 408742937896204257L;
	private String[] indeksi;
	
	public StudentListModel(String[] indeksi) {
		this.indeksi = indeksi;
	}

	@Override
	public String getElementAt(int index) {
		return indeksi[index];
	}

	@Override
	public int getSize() {
		return indeksi.length;
	}
	
}
