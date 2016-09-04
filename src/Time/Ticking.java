package Time;

import java.awt.event.ActionListener;
import java.util.ArrayList;


public class Ticking implements Runnable{
	protected Time t = null;
	protected int alertRate = 1000; //milliseconds to alert
	protected int alertCounter = 0;
	protected ArrayList<TickingListener> listeners = new  ArrayList<>();
	protected ArrayList<ActionListener> outOfTimeListeners = new ArrayList<>();
	public Ticking(Time t) {
		// TODO Auto-generated constructor stub
		this.t= t;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(t.ticking == true){
			try{
				Thread.sleep(1);
			}catch(Exception e){
				//alert();
				return;
			}
			if(alertCounter >=this.alertRate){
				alert();
				this.alertRate=0;
			}
			if(t.isOutOfTime()){
				alertOutOfTime();
				return;
			}
			alertCounter++;
			t.decrementMilli();
		}
		
	}
	
	public void alert() {
		// TODO Auto-generated method stub
		for(TickingListener t : this.listeners){
			t.haveTicked();
		}
	}
	
	public void alertOutOfTime(){
		for(ActionListener l : this.outOfTimeListeners){
			l.actionPerformed(null);
		}
	}
	
	protected void addlistener(TickingListener listener) {
		// TODO Auto-generated method stub
		this.listeners.add(listener);
	}
	
	protected void addOutOfTimeListener(ActionListener l ){
		this.outOfTimeListeners.add(l);
	}

}
