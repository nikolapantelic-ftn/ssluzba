package ssluzba.app.views;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkEvent.EventType;
import javax.swing.event.HyperlinkListener;

public class AboutDialog extends JDialog {

	private static final long serialVersionUID = 8798645539624939788L;
	
	public AboutDialog() {
		this.setTitle("About");
		this.setModal(false);
		this.setSize(400, 300);
		this.setLocationRelativeTo(MainFrame.getInstance());
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		initComponents();
	}
	
	private void initComponents() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setPreferredSize(new Dimension(400, 300));
		JTextPane about = new JTextPane();
		about.setContentType("text/html");
		JTextArea title = new JTextArea();
		title.setEditable(false);
		about.setEditable(false);
		title.setText("ssluzba\n");
		title.setFont(new Font(null, Font.BOLD, 32));
		about.setText("ssluzba - projekat iz predmeta OISiSI<br/>"
				+ "Radili: <br />"
				+ "\tDjordje Stojakovic RA-235/2017<br/>"
				+ "\tNikola Pantelic RA-234/2017<br/><br/>"
				+ "Source: <a href=\"https://github.com/nikolapantelic-ftn/ssluzba\">Github.com</a>");
		about.addHyperlinkListener(new HyperlinkListener() {
			
			@Override
			public void hyperlinkUpdate(HyperlinkEvent e) {
				if(e.getEventType() == EventType.ACTIVATED) {
					try {
						Desktop.getDesktop().browse(new URI(e.getURL().toString()));
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (URISyntaxException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		panel.add(title, BorderLayout.NORTH);
		panel.add(about, BorderLayout.CENTER);
		
		this.add(panel);
	}
}
