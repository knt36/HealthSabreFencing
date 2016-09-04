package Screens;

import java.awt.Font;
import java.awt.Label;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;

import Labels.NoteLabel;

public class ScrHelp extends ScrFactory {
	protected JScrollPane scrollPane = new JScrollPane();
	public ScrHelp() {
		// TODO Auto-generated constructor stub
		this.constr.fill = constr.NONE;
		this.add(new Labels.HeaderLabel(STRINGS.HELPHEADER));
		this.constr.fill=constr.BOTH;
		this.constr.gridy++;
		this.constr.gridwidth++;
		this.constr.weighty = 9999;
		//Need to add scroll bar for more information
		
		JTextPane area = new JTextPane();
		area.setEditable(false);
		area.setContentType("text/html");
		JScrollPane scroll = new JScrollPane(area);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		this.add(scroll);
		
		area.setFont(new Font("Arial",Font.BOLD, 32));
		area.setText(STRINGS.DIVTEXTCENTER +
				"<b>Summary</b><br>"
				+ "DeathMatch Sabre Fencing<br>"
				+ "A fencing game that uses health points and lives to indicate the winner of a duel. Starting the duel will"
				+ "induce a timer and damage infliction can begin."
				+ "Damage calculations goes as follows: the initial impact of a hit is 10 points while any 20 milliseconds after that the sword stays on the opponent is an additional 1 point of damage."
				+ "These settings can be changed in the settings option and the record of hits done by each fencer is in the Tracker Menu.<br><br>"+
				"<b>Hardware or No Hardware</b>"
				+ "<br> If there is no fencing hardware with bluetooth detected, the program will open a simulator for fencers to attack."
				+ "<br>"
				+ "To set up with hardware, plug the usb port to a usb powered port, equip the lame and sabre like regular FIE equipment, and start program.<br>"
				+ "When red blicking light of bluetooth stops blicking, pairing is successful.<br>"
				+ "If Pairing fails, the simulator will be used instead.<br>" +
				"<br><b>Main Menu</b><br>"
				+ "Reset -resets the hp and timer of the game<br>"
				+ "Settings -enters the setting Menu <br>"
				+ "Tracker -enters the tracking menu where all attacks from previous game is saved<br>"
				+ "Start -starts the timer and begins the game, hits are now registerable<br>"
				
				+"<br><b>Settings</b><br>"
				+"Allow the changing of stocks (lives), max health points, and handicap for each fencer. Even more, "
				+ "allows the setting of the time limit and contact time (minimum time in milliseconds a hit must stay in contact with opponent to be registered as a hit."
				+ "<br>"
				+ "Pressing apply will apply changes, cancel will quit out settings without doing anything.<br>"
				+ "<br>"
				+ "<b>Tracker</b><br>"
				+ "Tracker allows a person to see information concerning a fencer's successful hits, the time it was contacted, the damage, and the duration it stayed in contact with opponent. Also allows the record to be saved to a text file outside of the program when pressing save."
				+ STRINGS.ENDDIV);
		
	}
}
