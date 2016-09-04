package Tester;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Observer;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Buttons.OptionButton;
import Screens.STRINGS;
import Screens.ScrFactory;

public class ScrTester extends ScrFactory{
	protected OptionButton p1Hitp2 = new OptionButton(Color.cyan, "Attack Player 2");
	protected OptionButton p2Hitp1 = new OptionButton(Color.red, "Attack Player 1");
	
	protected ArrayList<listener> listeners = new ArrayList<>();
	
	public ScrTester() {
		// TODO Auto-generated constructor stub
		this.add(p1Hitp2);
		this.constr.gridy++;
		this.add(p2Hitp1);
		
		//adding button listeners
		p1Hitp2.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				for(int i = 0 ; i < listeners.size(); i ++){
					listeners.get(i).action(STRINGS.P1ATTACKUP);
				}
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				for(int i = 0 ; i < listeners.size(); i ++){
					listeners.get(i).action(STRINGS.P1ATTACKDOWN);
				}
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	
		p2Hitp1.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				for(int i = 0 ; i < listeners.size(); i ++){
					listeners.get(i).action(STRINGS.P2ATTACKUP);
				}
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				for(int i = 0 ; i < listeners.size(); i ++){
					listeners.get(i).action(STRINGS.P2ATTACKDOWN);
				}
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	public void addListener(listener ob) {
		// TODO Auto-generated method stub
		this.listeners.add(ob);
	}
}
