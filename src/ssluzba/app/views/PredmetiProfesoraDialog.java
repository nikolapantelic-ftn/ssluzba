package ssluzba.app.views;

import javax.swing.AbstractListModel;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JScrollPane;

import ssluzba.app.Profesor;
import ssluzba.app.controllers.ProfesoriController;

public class PredmetiProfesoraDialog extends JDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -988261733774940592L;
	private static PredmetiProfesoraDialog instance = null;

	public static PredmetiProfesoraDialog getInstance() {
		if (instance == null)
			instance = new PredmetiProfesoraDialog();
		return instance;
	}
	private JList<String> lista;
	private ListModel listModel;
	private Profesor p;

	public PredmetiProfesoraDialog() {
	this.setTitle("Predmeti profesora");
	//this.setLayout(new FlowLayout());
	this.setModal(true);
	this.setSize(200,150);
	this.setLocationRelativeTo(MainFrame.getInstance());
	
	addComponents();
	this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

	}

	private void addComponents() {
		p=ProfesoriController.getInstance().getProfesor(ProfesorJTable.getInstance().getSelectedRow());
		String predmeti[]=new String[p.getPredmeti().size()];
		System.out.println(p.getPredmeti().size());
		predmeti=ProfesoriController.getInstance().getSpisakPredmeta(p);
		listModel=new ListModel(predmeti);
		lista=new JList<String>(listModel);
		JScrollPane scroll=new JScrollPane(lista);
	
		add(scroll);
		
	}
	
	class ListModel extends AbstractListModel<String> {

		
		/**
		 * 
		 */
		private static final long serialVersionUID = -9200679300504311968L;
		private String[] predmeti;
		
		public ListModel(String[] predmeti) {
			this.predmeti = predmeti;
		}

		@Override
		public String getElementAt(int index) {
			return predmeti[index];
		}

		@Override
		public int getSize() {
			return predmeti.length;
		}
		
	}
	public void osveziTabelu() {
		String [] predmeti = ProfesoriController.getInstance().getSpisakPredmeta(p);
		StudentListModel listModel = new StudentListModel(predmeti);
		lista.setModel(listModel);
	}
}
