package Screens;

import java.awt.Color;
import Buttons.OptionButton;
import Labels.NoteLabel;

public class ScrBluetoothDc extends ScrFactory {
	NoteLabel msg = new NoteLabel(STRINGS.BLUETOOTHDC);
	OptionButton quitBut = new OptionButton(Color.red, STRINGS.QUIT);
	public ScrBluetoothDc() {
		// TODO Auto-generated constructor stub
		this.constr.anchor = constr.CENTER;
		this.constr.gridwidth = 2;
		this.add(msg);
		this.constr.gridy++;
		this.constr.fill = constr.NONE;
		this.constr.gridwidth = 1;
		this.add(quitBut);
	}
}
