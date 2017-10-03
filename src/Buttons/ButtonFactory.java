package Buttons;

import java.awt.Color;

import javax.swing.JButton;

public abstract class ButtonFactory extends JButton {
	public ButtonFactory(Color c, String s){
		this.setOpaque(true);
		this.setBackground(c);
		this.setText(s);
	}
}
