package Spinners;

import java.awt.Color;
import java.awt.Component;
import java.util.Date;

import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;
import javax.swing.text.EditorKit;

import Screens.STYLE;

public class TimerSpinner extends JSpinner {
	public TimerSpinner() {
		super(new SpinnerDateModel());
		this.setEditor(new TimerSpinner.DateEditor(this, "mm:ss"));	
		this.setValue(new Date(0, 1, 1, 0, 0, 0));
		//System.out.println("This is a date: " + this.getValue() );
	}
	
	@Override
	public Object getValue() {
		// TODO Auto-generated method stub
		return super.getValue();
	}
}
