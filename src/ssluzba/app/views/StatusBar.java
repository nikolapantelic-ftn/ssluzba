package ssluzba.app.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class StatusBar extends JPanel{

	private static final long serialVersionUID = 7941971199086128652L;

	public StatusBar() {
		this.setLayout(new BorderLayout());
		this.setBackground(Color.LIGHT_GRAY);
		initComponents();
	}
	
	private void initComponents() {
		this.add(new JLabel("Studentska Sluzba"), BorderLayout.WEST);
		this.add(new TimeLabel(), BorderLayout.EAST);
	}
}

class TimeLabel extends JLabel implements ActionListener {

	private static final long serialVersionUID = 5090064564611462692L;
	private static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm ");
	private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy. ");

	public TimeLabel() {
		super(LocalTime.now().format(timeFormatter) + "    " + LocalDate.now().format(dateFormatter));
		Timer t = new Timer(1000, this);
		t.start();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		setText(LocalTime.now().format(timeFormatter) + "    " + LocalDate.now().format(dateFormatter));
	}
}