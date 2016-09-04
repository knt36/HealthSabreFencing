package Labels;

import java.awt.Color;

import javax.swing.JLabel;

import Screens.STRINGS;
import Screens.STYLE;

public class NoteLabel extends LabelFactory {

	public NoteLabel(String label) {
		super(STRINGS.DIVTEXTCENTER + label + STRINGS.ENDDIV);
		// TODO Auto-generated constructor stub
		this.setFont(STYLE.NOTELABELFONT);
		this.setForeground(STYLE.NOTELABELCOLOR);
		this.setHorizontalAlignment(this.CENTER);
		this.setHorizontalTextPosition(this.CENTER);
		
	}
}
