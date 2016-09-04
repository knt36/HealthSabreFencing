package Game;

import java.awt.Color;

import Time.Time;

public class Hit {
	//milliseconds
	public int duration = 0;
	public double damage = 0;
	public Time time = null;
	public Color c = null;
	public Hit(double damage, int duration, Time time, Color c ){
		this.duration = duration;
		this.damage = damage;
		this.time = time;
		this.c = c;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String temp = "";
		if(c.equals(Color.cyan)){
			temp = temp +"Owner: Fencer1\n";
			temp = temp + "Time: " + time + "\n";
			temp = temp + "Duration: " + duration + "\n";
			temp = temp + "Damage: " + damage + "\n";
		}else{
			temp = temp +"Owner: Fencer2\n";
			temp = temp + "Time: " + time + "\n";
			temp = temp + "Duration: " + duration + "\n";
			temp = temp + "Damage: " + damage + "\n";
		}
		return temp;
	}
}
