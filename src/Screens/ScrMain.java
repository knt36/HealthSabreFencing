package Screens;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;

import javax.jws.soap.SOAPBinding.Style;
import javax.swing.Action;
import javax.swing.BorderFactory;

import Bluetooth.HC05;
import Bluetooth.ListenerBlue;
import Buttons.OptionButton;
import Game.Game;
import Game.Hit;
import HealthBar.HealthBar;
import Labels.TitleLabel;
import Tester.ScrTester;
import Tester.listener;
import Time.TickingListener;
import Time.Time;

public class ScrMain extends ScrFactory {
	protected FrameNotify frameWinner = null;
	protected List<Hit> allHits = Collections.synchronizedList(new ArrayList<>());
	protected Game game = new Game();
	protected Time time = null;
	//Gui Elements
	TitleLabel timeText = new TitleLabel(game.getTimeLimit().toString());
	
	ScrHealthBar scrHealthBarP1 = null;
	ScrHealthBar scrHealthBarP2 = null;
	
	OptionButton helpBut = new OptionButton(Color.ORANGE, "Help");
	OptionButton resetBut = new OptionButton(STYLE.GREEN, STRINGS.RESET);
	OptionButton startBut = new OptionButton(STYLE.GREEN, STRINGS.START);
	protected boolean startPressed = false;
	OptionButton trackerBut = new OptionButton(STYLE.GREEN, STRINGS.TRACKER);
	OptionButton settingsBut = new OptionButton(STYLE.GREEN, STRINGS.SETTINGS);
	FrameMain frameTester = null;
	ScrTester scrTester = null;
	protected Thread p1SustainDamage = null;
	protected ThreadDecreaseHealth p1SustainDamageRunnable = null;
	protected Thread p2SustainDamage = null;
	protected ThreadDecreaseHealth p2SustainDamageRunnable = null;
	protected HC05 bluetooth = null;
	public ScrMain() {
		// TODO Auto-generated constructor stub
		//Set up bluetooth
		FrameNotify fn1 = new FrameNotify();
		fn1.add(new ScrNotify("Connecting to bluetooth..."));
		bluetooth = new HC05();
		if(bluetooth.isconnected()){
			fn1.dispose();
		}else{
			fn1.dispose();
			FrameNotify fn2 = new FrameNotify();
			fn2.add(new ScrNotify("Failed to connect -will use simulator instead"));
			
		}
		
		//Set Button Tool Tips
		resetBut.setToolTipText(STRINGS.TIP_RESET);
		startBut.setToolTipText(STRINGS.TIP_START);
		trackerBut.setToolTipText(STRINGS.TIP_TRACKER);
		settingsBut.setToolTipText(STRINGS.TIP_SETTINGS);
		
		Time t = game.getTimeLimit();
		this.time = new Time(t.getHour(),t.getMinute(),t.getSecond());
		this.scrHealthBarP1 = new ScrHealthBar(Color.CYAN, 100, game.getFencer1().stock);
		this.scrHealthBarP2 = new ScrHealthBar(Color.red, 100, game.getFencer2().stock);
		
		this.constr.gridheight=6;
		this.add(this.scrHealthBarP1);
		this.constr.gridx++;
		this.constr.gridy = 0;
		this.constr.weighty = 0;
		this.constr.gridheight=1;
		this.add(timeText);
		this.constr.gridx++;
		this.constr.gridheight=6;
		this.add(this.scrHealthBarP2);
		this.constr.gridx = 0;
		this.constr.gridy = 0;
		
		this.constr.gridx=1;
		this.constr.gridy=1;
		
		this.constr.gridheight=1;
		this.add(this.resetBut);
		this.constr.gridy++;
		this.add(this.settingsBut);
		this.constr.gridy++;
		this.add(this.trackerBut);
		this.constr.gridy++;
		this.add(this.helpBut);
		this.constr.gridy++;
		this.add(this.startBut);
		//
		this.time.addlistener(new  TickingListener() {

			@Override
			public void haveTicked() {
				// TODO Auto-generated method stub
				//System.out.println(time.toString());
				timeText.setText(time.toString());
			}
		});
		this.time.addOutOfTimeListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//disconnet the tester
				if(bluetooth !=null && bluetooth.isconnected()){
					bluetooth.rt.clearAllListenersBlue();
				}
				if(frameTester != null){
					frameTester.dispose();
					frameTester = null;
					scrTester = null;
				}
				
				FrameNotify fn = new FrameNotify();
				ScrNotify scrNotify = new ScrNotify("Out of Time!");
				fn.add(scrNotify);
				startPressed =false;
				time.stop();
				startBut.setText(STRINGS.START);
				startBut.setBackground(STYLE.GREEN);
			}
		});
		
		//Button
		this.helpBut.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				FrameHelp helpNotify = new FrameHelp();
				helpNotify.add(new ScrHelp());
				
			}
		});
		this.resetBut.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//Must rest everything from hp to timer
				//resets the timer
				time.resetTime();
				timeText.setText("" + time);
				game.getFencer1().reset();
				game.getFencer2().reset();
				scrHealthBarP1.update(game.getFencer1().maxHealth, game.getFencer1().maxStock);
				scrHealthBarP2.update(game.getFencer2().maxHealth, game.getFencer1().maxStock);
				allHits.clear();
			}
		});
		this.trackerBut.addActionListener(new Action() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				FrameTracker ft = new FrameTracker();
				ft.add(new ScrTracker(allHits));
			}
			
			@Override
			public void setEnabled(boolean b) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void removePropertyChangeListener(PropertyChangeListener listener) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void putValue(String key, Object value) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public boolean isEnabled() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public Object getValue(String key) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public void addPropertyChangeListener(PropertyChangeListener listener) {
				// TODO Auto-generated method stub
				
			}
		});
		this.settingsBut.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				FrameSettings sf = new FrameSettings();
				ScrSettings ss = new ScrSettings(game,ScrMain.this);
				sf.add(ss);
			}
		});
		this.startBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(startPressed != true){
					frameWinner = null;
					time.start();
					startPressed = true;
					startBut.setText(STRINGS.PAUSE);
					startBut.setBackground(new Color(242, 214, 31));
					//Input type that is being used
					
					if(bluetooth.isconnected()){
						bluetooth.rt.addListenerBlue(new ListenerBlue() {
							
							@Override
							public void action(String s) {
								// TODO Auto-generated method stub
								InitiateDamage(s);
							}
						});
					}else{
						//If there is no bluetooth then user this.
						scrTester = new Tester.ScrTester();
						frameTester = new FrameMain();
						frameTester.add(scrTester);
						scrTester.addListener(new listener() {
							
							@Override
							public void action(String s) {
								// TODO Auto-generated method stub
								//Gets the attack type
								InitiateDamage(s);
							}});
					}
					
				} else{
					//disconnet the tester
					StopGame();
				}
			}
		});
		
		//Setting Listener
		
	}
	
	public void InitiateDamage(String s){
		if(s.equals(STRINGS.P1ATTACKUP)){
			if(p2SustainDamage != null){
				p2SustainDamageRunnable.running = false;
				double collectedDamage = p2SustainDamageRunnable.getCollectedDamage();
				Time hitTime = p2SustainDamageRunnable.getHitTime();
				int duration = p2SustainDamageRunnable.getDuration();
				game.getFencer1().hits.add(new Hit(collectedDamage, duration,hitTime,game.getFencer1().c));
				allHits.add(new Hit(collectedDamage, duration,hitTime,game.getFencer1().c));
				p2SustainDamage = null;
				p2SustainDamageRunnable = null;
			}
		}else if(s.equals(STRINGS.P1ATTACKDOWN)){
			if(p2SustainDamage==null){
				p2SustainDamageRunnable = new ThreadDecreaseHealth(ScrMain.this, scrHealthBarP2, game.getFencer2());
				p2SustainDamage = new Thread(p2SustainDamageRunnable);
				p2SustainDamage.start();
			}
		}else if(s.equals(STRINGS.P2ATTACKUP)){
			if(p1SustainDamage != null){
				p1SustainDamageRunnable.running = false;
				double collectedDamage = p1SustainDamageRunnable.getCollectedDamage();
				Time hitTime = p1SustainDamageRunnable.getHitTime();
				int duration = p1SustainDamageRunnable.getDuration();
				game.getFencer2().hits.add(new Hit(collectedDamage, duration,hitTime,game.getFencer2().c));
				allHits.add(new Hit(collectedDamage, duration,hitTime,game.getFencer2().c));
				p1SustainDamage = null;
				p1SustainDamageRunnable=null;
			}
		}else if(s.equals(STRINGS.P2ATTACKDOWN)){
			if(p1SustainDamage==null){
				p1SustainDamageRunnable = new ThreadDecreaseHealth(ScrMain.this, scrHealthBarP1, game.getFencer1());
				p1SustainDamage = new Thread(p1SustainDamageRunnable);
				p1SustainDamage.start();
			}
		}
	}
	
	public void StopGame(){
		if(this.p1SustainDamage != null){
			this.p1SustainDamageRunnable.running=false;
			this.p1SustainDamage = null;
		}
		
		if(this.p2SustainDamage != null){
			this.p2SustainDamageRunnable.running=false;
			this.p2SustainDamage = null;
		}
		if (bluetooth !=null && bluetooth.isconnected()){
			bluetooth.rt.clearAllListenersBlue();
			startPressed =false;
			time.stop();
			startBut.setText(STRINGS.START);
			startBut.setBackground(STYLE.GREEN);
		}else if(frameTester!=null){
			frameTester.dispose();
			frameTester = null;
			scrTester = null;
			startPressed =false;
			time.stop();
			startBut.setText(STRINGS.START);
			startBut.setBackground(STYLE.GREEN);
		}
	}
}
