package Game;

import java.awt.Color;
import java.util.ArrayList;

public class Fencer {
	public Color c = null;
	public ArrayList<Hit> hits = new ArrayList<>();
	public int stock = 1;
	public int maxStock = 1;
	public double health = 100.0;
	public int handicap = 0;
	public boolean dead = false;
	public double maxHealth=100.0;
	public Fencer(int stock, double health, int handicap, Color c ) {
		super();
		this.maxStock = stock;
		this.maxHealth = health;
		this.stock = stock;
		this.health = health;
		this.handicap = handicap;
		this.c = c;
	}
	
	public void decreaseHealth(double by){
		if(stock <=0){
			
		}else{
		this.health = this.health - by;
		if(this.health <= 0){
			this.health = this.maxHealth;
			this.stock--;
			if(stock <=0){
				this.dead = true;
				this.health = 0;
			}
		}
		}
	}
	
	public void reset(){
		this.stock = this.maxStock;
		this.health = this.maxHealth;
		this.dead = false;
		hits.clear();
	}
	
}
