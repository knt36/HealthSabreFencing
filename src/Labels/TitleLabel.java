package Labels;

import javax.swing.JLabel;

import Screens.STYLE;

public class TitleLabel extends LabelFactory {

	public TitleLabel(String label) {
		super(label);
		// TODO Auto-generated constructor stub
		this.setFont(STYLE.TITLEFONT);
		this.setForeground(STYLE.TITLECOLOR);
	}
}
