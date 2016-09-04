package HealthBar;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Labels.NoteLabel;
import Screens.STYLE;
import Screens.ScrFactory;

public class HealthBar extends JPanel {
	protected double hpMax = -1; // by default
	protected double hp = -1; //full hp at the start
	protected int stock = -1;
	public HealthBar(Color c, double max, int st) {
		// TODO Auto-generated constructor stub
		//everyone starts out with full hp
		//Set initials
		this.stock = st;
		this.hpMax = max;
		
		this.setBackground(STYLE.BACKGROUND);
		this.setBorder(BorderFactory.createLineBorder(Color.black, STYLE.BOARDBORDERSTHICK));
	}
}
