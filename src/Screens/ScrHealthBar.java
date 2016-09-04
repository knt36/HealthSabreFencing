package Screens;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;

import HealthBar.HealthBar;
import Labels.NoteLabel;

public class ScrHealthBar extends ScrFactory {
	public NoteLabel stockLabel = null;	
	protected ScrBar healthbar= null;
	protected NoteLabel percentageLabel = null;
	public Color barColor = null;
	protected int stock = -1;
	public ScrHealthBar(Color c, double max, int st) {
		// TODO Auto-generated constructor stub
		this.setBorder(BorderFactory.createEmptyBorder());
		percentageLabel = new NoteLabel(max + "");
		percentageLabel.setToolTipText(STRINGS.TIP_HP);
		this.add(percentageLabel);
		this.stock = st;
		this.constr.gridy++;
		this.constr.weighty = 9999;
		healthbar = new ScrBar(c,this.stock,max);
		this.add(healthbar);
		this.constr.gridy++;
	}
	
	public void update(double health, int stock){
		this.percentageLabel.setText(health+"");
		this.healthbar.update(health,stock);
		
	}
	
}
