package Game;

import java.awt.Color;
import java.util.ArrayList;

import Time.Time;

public class Game {
	//default settings
	protected Fencer fencer1 = new Fencer(5, 100.0, 0, Color.cyan);
	protected Fencer fencer2 = new Fencer(5, 100.0, 0, Color.red);
	//For all
	protected Time timeLimit = new Time(0, 5, 0);
	protected int contactTime = 1; // 1 is the minimum
	
	public ArrayList<gameListener> listeners = new ArrayList<>();
	
	
	public void addGameListener (gameListener l){
		listeners.add(l);
	}
	public Game(Fencer f1, Fencer f2 , Time t, int duration) {
		// TODO Auto-generated constructor stub
		this.fencer1 = f1;
		this.fencer2 = f2;
		this.timeLimit = t;
		this.contactTime = duration;
		
	}
	public Fencer getFencer1() {
		return fencer1;
	}


	public void setFencer1(Fencer fencer1) {
		this.fencer1 = fencer1;
	}


	public Fencer getFencer2() {
		return fencer2;
	}


	public void setFencer2(Fencer fencer2) {
		this.fencer2 = fencer2;
	}


	public Time getTimeLimit() {
		return timeLimit;
	}


	public void setTimeLimit(Time timeLimit) {
		this.timeLimit = timeLimit;
	}


	public int getDuration() {
		return contactTime;
	}


	public void setDuration(int duration) {
		this.contactTime = duration;
	}

	public Game() {
		// TODO Auto-generated constructor stub
	}
}
