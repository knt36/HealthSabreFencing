package Screens;

import java.awt.Color;

import Game.Fencer;
import Game.Hit;
import Time.Time;

public class ThreadDecreaseHealth implements Runnable {
	protected static final double IMPACT_DAMAGE = 10;
	protected static final double SUSTAIN_DAMAGE = 1;
	protected ScrMain scrMain = null;
	protected Fencer fencer = null;
	protected Time hitTime = null;
	protected int rate = 500;
	protected int duration = 0;
	protected double collectedDamage = 0;
	protected boolean running = true;
	protected ScrHealthBar healthBar = null;
	public ThreadDecreaseHealth(ScrMain scrMain,ScrHealthBar healthBar, Fencer f) {
		// TODO Auto-generated constructor stub
		this.scrMain = scrMain;
		this.fencer = f;
		this.hitTime = new Time(scrMain.time.getHour(),scrMain.time.getMinute(),scrMain.time.getSecond());
		this.healthBar = healthBar;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		//Delay sufficient contact to count as an attack
		try{
			this.collectedDamage = this.collectedDamage + fencer.handicap+IMPACT_DAMAGE;
			fencer.decreaseHealth(fencer.handicap+IMPACT_DAMAGE);
			healthBar.update(fencer.health, fencer.stock);
			Thread.sleep(scrMain.game.getDuration());
			this.duration = this.duration + scrMain.game.getDuration();
		}catch(Exception e){
			
		}
		while(running){
			try{
					this.collectedDamage = this.collectedDamage + fencer.handicap+SUSTAIN_DAMAGE;
					fencer.decreaseHealth(fencer.handicap+SUSTAIN_DAMAGE);
					healthBar.update(fencer.health, fencer.stock);
					this.duration = this.duration + 20;
					
					//Check if the match is over due to hp
					if(scrMain.frameWinner == null && fencer.dead){
						scrMain.StopGame();
						scrMain.frameWinner = new FrameNotify();
						if(fencer.c.equals(Color.cyan)){
							scrMain.frameWinner.add(new ScrNotify("The Winner Is " + "Fencer 2"));
							scrMain.allHits.add(new Hit(collectedDamage, duration,hitTime,Color.red));
						}else{
							scrMain.frameWinner.add(new ScrNotify("The Winner Is " + "Fencer 1"));
							scrMain.allHits.add(new Hit(collectedDamage, duration,hitTime,Color.cyan));

						}
						return;
					}
					Thread.sleep(20);
			}catch(Exception e){
				
			}
		}
	}
	public double getCollectedDamage(){
		return(this.collectedDamage);
	}
	
	public Time getHitTime(){
		return(this.hitTime);
	}
	
	public int getDuration(){
		return(this.duration);
	}
}
