package Screens;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Buttons.OptionButton;
import Game.Game;
import Game.Hit;
import Labels.HeaderLabel;
import Labels.NoteLabel;

public class ScrTracker extends ScrFactory	 {
	protected NoteLabel ownerHit = new NoteLabel(STRINGS.OWNERHITLABEL +STRINGS.NOHITSELECTED);
	protected NoteLabel timeHit = new NoteLabel(STRINGS.TIMEHITLABEL + STRINGS.NOHITSELECTED);
	protected NoteLabel durationHit = new NoteLabel(STRINGS.DURATIONHITLABEL + STRINGS.NOHITSELECTED);
	protected NoteLabel lostHP = new NoteLabel(STRINGS.HPLOSTLABEL + STRINGS.NOHITSELECTED);
	protected JList<String> hits = null;
	
	protected OptionButton saveBut = new OptionButton(STYLE.GREEN, STRINGS.SAVE);
	protected OptionButton closeBut = new OptionButton(Color.red, STRINGS.CLOSE);
	
	protected Game game = null;
	protected List<Hit> allHits = null;
	
	public ScrTracker(List allHits) {
		// TODO Auto-generated constructor stub
		this.allHits = allHits;
		this.game = game;
		this.constr.gridwidth++;
		add(new HeaderLabel(STRINGS.HIT_TRACKEER));
		this.constr.gridwidth=1;
		this.constr.weighty = 9999;
		this.constr.gridy++;
		this.add(leftSide());
		this.constr.gridx++;
		this.add(rightSide());
		this.constr.gridy++;
		this.constr.gridx=0;
		this.constr.gridwidth=2;
		this.constr.weighty=1;
		this.add(bottomRow());
		
		
		this.saveBut.setToolTipText("Saves to external text file when pressed...");
		//button listeners
		this.saveBut.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				 BufferedWriter writer = null;
			        try {
			            //create a temporary file
			            String timeLog = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
			            File logFile = new File(timeLog);

			            // This will output the full path where the file will be written to...
			            System.out.println(logFile.getCanonicalPath());

			            writer = new BufferedWriter(new FileWriter(logFile));
			            writer.write("Fencing Information \n");
			            for(int i = 0 ; i < allHits.size(); i ++){
			            	writer.write(allHits.get(i).toString());
			            	writer.write("\n");
			            }
			        } catch (Exception f) {
			            f.printStackTrace();
			        } finally {
			            try {
			                // Close the writer regardless of what happens...
			            	writer.flush();
			                writer.close();
			            } catch (Exception f) {
			            }
			        }
			    }
		
		});
		
		this.closeBut.addActionListener(new Action() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.dispose();
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
	}
	
	public ScrFactory leftSide(){
		ScrFactory panel = new ScrFactory();
		panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(STYLE.BOARDFRAMEBORDERTK, STYLE.BOARDFRAMEBORDERTK, STYLE.BOARDFRAMEBORDERTK, STYLE.BOARDFRAMEBORDERTK), BorderFactory.createLineBorder(Color.black, STYLE.BOARDFRAMEBORDERTK, true)));
		panel.setForeground(Color.white);
		panel.add(this.ownerHit);
		panel.constr.gridy++;
		panel.add(this.timeHit);
		panel.constr.gridy++;
		panel.add(this.durationHit);
		panel.constr.gridy++;
		panel.add(this.lostHP);
		return(panel);
	}
	
	public ScrFactory rightSide(){
		ScrFactory panel = new ScrFactory();
		panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(STYLE.BOARDFRAMEBORDERTK, STYLE.BOARDFRAMEBORDERTK, STYLE.BOARDFRAMEBORDERTK, STYLE.BOARDFRAMEBORDERTK), BorderFactory.createLineBorder(Color.black, STYLE.BOARDFRAMEBORDERTK, true)));
		DefaultListModel<String> md = new DefaultListModel<>();
		for(int i = 0 ; i < allHits.size() ; i ++){
			if(allHits.get(i).c.equals(Color.cyan)){
				md.addElement("Fencer1: " + allHits.get(i).time.toString());
			}else{
				md.addElement("Fencer2: " +allHits.get(i).time.toString());
			}
			
		}
		
		
		
		this.hits = new JList(md); //data has type Object[]
		hits.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		hits.setLayoutOrientation(JList.VERTICAL);
		hits.setVisibleRowCount(-1);

		JScrollPane js = new JScrollPane(hits);
		js.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		panel.add(js);
		hits.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				Hit hit = allHits.get(e.getLastIndex());
				
				if(hit.c.equals(Color.cyan)){
					ownerHit.setText(STRINGS.OWNERHITLABEL + "Fencer 1");
				}else{
					ownerHit.setText(STRINGS.OWNERHITLABEL + "Fencer 2");
				}
				
				
				
				timeHit.setText(STRINGS.TIMEHITLABEL + hit.time.toString());
				durationHit.setText(STRINGS.DURATIONHITLABEL + hit.duration+"");
				lostHP.setText(STRINGS.HPLOSTLABEL + hit.damage);
			}
		});
		return(panel);
	}
	
	public ScrFactory bottomRow(){
		ScrFactory panel = new ScrFactory();
		panel.add(this.closeBut);
		panel.constr.gridx++;
		panel.add(this.saveBut);
		return(panel);
	}
}
