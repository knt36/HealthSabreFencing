package Screens;

import java.awt.Color;
import java.awt.Graphics;

public class ScrBar extends ScrFactory {
	public int stock = -1;
	public double health;
	public double healthMax;
	private Color color;
	public ScrBar(Color c, int stock, double max) {
		// TODO Auto-generated constructor stub
		this.healthMax = max;
		this.health = max;
		this.color = c;
		this.stock = stock;
	}
	
	public void setNewHeathSettings(double health, double healthMax){
		this.healthMax = healthMax;
		this.health = health;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
	protected void update(double health, int stock){
		this.health = health;
		this.stock = stock;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		this.setToolTipText(STRINGS.TIP_HPBAR);
		int barHeight = (int)(this.getHeight()*this.health/this.healthMax);
		g.setColor(this.color);
		g.fillRect(0, this.getSize().height - barHeight, this.getSize().width, barHeight);
		g.setColor(Color.black);
		g.setFont(STYLE.TITLEFONT);
		g.drawString(this.stock+"", this.getWidth()/2 - g.getFontMetrics().stringWidth(this.stock+"")/2, getHeight()/2);
	}
	
	
}
