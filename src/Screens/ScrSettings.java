package Screens;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.Action;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.OptionPaneUI;

import Buttons.OptionButton;
import Game.Fencer;
import Game.Game;
import Labels.HeaderLabel;
import Labels.NoteLabel;
import Spinners.TimerSpinner;
import Time.TickingListener;
import Time.Time;

public class ScrSettings extends ScrFactory {
	protected OptionButton applyBut = new OptionButton(STYLE.GREEN, STRINGS.APPLY);
	protected OptionButton cancelBut = new OptionButton(Color.red, STRINGS.CANCEL);
	protected ScrSettingsSide scrSettingSideP1 = null;
	protected ScrSettingsSide scrSettingSideP2 = null;
	
	protected JSpinner minContactTimeSpinner = null;
	protected TimerSpinner timeLimitSpinner = new TimerSpinner();
	protected Game settings = null;
	protected ScrMain m = null;
	public ScrSettings(Game st, ScrMain m) {
		this.m =  m;
		settings = st;
		timeLimitSpinner.setValue(new Date(0, 1, 1, st.getTimeLimit().getHour(), st.getTimeLimit().getMinute(), st.getTimeLimit().getSecond()));
		timeLimitSpinner.setToolTipText(STRINGS.TIP_TIMELIMIT);
		minContactTimeSpinner =  new JSpinner(new SpinnerNumberModel(st.getDuration(), 1, 9999, 1));
		minContactTimeSpinner.setToolTipText(STRINGS.TIP_MINCONTACT);
		scrSettingSideP1 = new ScrSettingsSide(st.getFencer1());
		scrSettingSideP2 = new ScrSettingsSide (st.getFencer2());
		this.constr.gridwidth=2;
		this.constr.weighty = 1;
		add(new HeaderLabel("Settings"));
		this.constr.weighty = 9999;
		this.constr.weightx=1;
		this.constr.gridwidth=1;
		this.constr.gridy++;
		this.add(this.scrSettingSideP1);
		this.constr.gridx++;
		this.add(this.scrSettingSideP2);
		this.constr.gridx = 0;
		this.constr.gridy++;
		this.constr.gridwidth=2;
		ScrFactory scrMid = new ScrFactory();
		scrMid.add(new NoteLabel(STRINGS.TIMER));
		scrMid.constr.gridx++;
		scrMid.add(new NoteLabel(STRINGS.CONTACT_TIME));
		
		scrMid.constr.gridx=0;;
		scrMid.constr.gridy++;
		scrMid.add(timeLimitSpinner);
		scrMid.constr.gridx++;
		scrMid.add(this.minContactTimeSpinner);
		
		this.add(scrMid);
		
		
		this.constr.weighty = 1;
		this.constr.gridwidth=1;
		this.constr.gridx=0;
		this.constr.gridy++;
		this.add(this.cancelBut);
		this.constr.gridx++;
		this.add(this.applyBut);
		
		//listeners for buttons
		this.applyBut.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//Apply the changes -not implemented yet
				//Player1Save
				System.out.println("HP player 1 is " + 1.0*((int)scrSettingSideP1.maxHpSpinner.getValue()));
				Fencer fencer1 = new Fencer((int)scrSettingSideP1.stockSpinner.getValue(),((Integer)scrSettingSideP1.maxHpSpinner.getValue()).doubleValue(), (int)scrSettingSideP1.handiCap.getValue(),Color.cyan);
				Fencer fencer2 = new Fencer((int)scrSettingSideP2.stockSpinner.getValue(),((Integer)(scrSettingSideP2.maxHpSpinner.getValue())).doubleValue(), (int)scrSettingSideP2.handiCap.getValue(),Color.red);
				Date dt = (Date)timeLimitSpinner.getValue();
				System.out.println("The minute is " + dt.getMinutes());
				System.out.println(dt + "Obtained to apply");
				m.game = new Game(fencer1, fencer2, new Time(dt.getHours(), dt.getMinutes(), dt.getSeconds()), (int)minContactTimeSpinner.getValue());
				settings = m.game;
				System.out.println("setting in time limit is " + settings.getTimeLimit().getMinute());
				//Update the main screen
				m.scrHealthBarP1.healthbar.setNewHeathSettings(settings.getFencer1().health, settings.getFencer1().maxHealth);
				m.scrHealthBarP2.healthbar.setNewHeathSettings(settings.getFencer2().health, settings.getFencer2().maxHealth);
				
				m.scrHealthBarP1.percentageLabel.setText(settings.getFencer1().health+"");
				m.scrHealthBarP2.percentageLabel.setText(settings.getFencer2().health+"");
				m.time = new Time(settings.getTimeLimit().getHour(), settings.getTimeLimit().getMinute(), settings.getTimeLimit().getSecond());
				m.timeText.setText(m.time.toString());
				m.scrHealthBarP1.healthbar.setStock(settings.getFencer1().stock);
				m.scrHealthBarP2.healthbar.setStock(settings.getFencer2().stock);
				m.time.addlistener(new  TickingListener() {

					@Override
					public void haveTicked() {
						// TODO Auto-generated method stub
						//System.out.println(time.toString());
						m.timeText.setText(m.time.toString());
					}
				});
				m.time.addOutOfTimeListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
							m.startBut.setText(STRINGS.START);
							m.startBut.setBackground(STYLE.GREEN);
							m.startPressed=false;
					}
				});
				
				
				m.validate();
				m.repaint();
			}
		});
		this.cancelBut.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.dispose();
			}
		});

		//Initialize Timer and Contact time to settings
		//timeLimitSpinner.setValue(settings.getTimeLimit());
		//minContactTimeSpinner.setValue(settings.getDuration());
	}
	
	
}
