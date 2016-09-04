package Screens;

import java.awt.Color;

import Buttons.OptionButton;

public class ScrSaveTrack extends ScrFactory {
	OptionButton yesBut = new OptionButton(STYLE.GREEN, STRINGS.YES);
	OptionButton noBut = new OptionButton(Color.red, STRINGS.NO);
	public ScrSaveTrack() {
		// TODO Auto-generated constructor stub
		this.constr.gridwidth++;
		this.add(new Labels.NoteLabel(STRINGS.SAVETRACK));
		this.constr.gridy++;
		this.constr.gridwidth--;
		this.constr.fill=this.constr.NONE;
		this.add(noBut);
		this.constr.gridx++;
		this.add(yesBut);
	}
}
