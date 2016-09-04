package Screens;

import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Game.Fencer;
import Game.Game;
import Labels.NoteLabel;
import Spinners.TimerSpinner;

public class ScrSettingsSide extends ScrFactory {
	public JSpinner stockSpinner = null;
	public JSpinner maxHpSpinner = null;
	public JSpinner handiCap = null;
	public ScrSettingsSide(Fencer fencer) {
		// TODO Auto-generated constructor stub
		
		this.stockSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 9999, 1));
		this.stockSpinner.setToolTipText(STRINGS.TIP_STOCKSPINNER);
		this.maxHpSpinner = new JSpinner(new SpinnerNumberModel(100, 1, 9999, 1));
		this.maxHpSpinner.setToolTipText(STRINGS.TIP_MAXHPSPINNER);
		this.handiCap = new JSpinner(new SpinnerNumberModel(0, 0, 9999, 1));
		this.handiCap.setToolTipText(STRINGS.TIP_HANDICAPSPINNER);
		this.add(new NoteLabel(STRINGS.STOCKLABEL));
		this.constr.gridx++;
		this.add(stockSpinner);
		this.constr.gridy++;
		
		this.constr.gridx = 0;
		this.add(new NoteLabel(STRINGS.MAXHP));
		this.constr.gridx++;
		this.add(maxHpSpinner);
		
		this.constr.gridy++;
		this.constr.gridx = 0;
		this.add(new NoteLabel(STRINGS.HANDICAP));
		this.constr.gridx++;
		this.add(handiCap);
		
		//initialize with settings
	}
}
