package Screens;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import Buttons.OptionButton;
import Labels.NoteLabel;
import TextField.TextField;

public class ScrNotify extends ScrFactory {
	public ScrNotify(String message) {
		// TODO Auto-generated constructor stub
		NoteLabel textfield = new NoteLabel(STRINGS.DIVTEXTCENTER + message + STRINGS.ENDDIV);
		this.add(textfield);
		this.constr.gridy++;
		OptionButton op = new OptionButton(STYLE.GREEN, "Ok");
		this.add(op);
		op.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.dispose();
			}
		});
	}
}
