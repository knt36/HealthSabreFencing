package Bluetooth;

import java.io.BufferedReader;
import java.util.ArrayList;

public class ThreadBluetooth implements Runnable {
	protected BufferedReader br = null;
	
	ArrayList<ListenerBlue> bluelistener = new ArrayList<>();
	public ThreadBluetooth(BufferedReader br) {
		// TODO Auto-generated constructor stub
		this.br = br;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub	
		boolean ready = true;
		while(ready){
			try{
				String s = br.readLine();
				notifyAllBlue(s);
				//System.out.println(s);
			}catch(Exception e){
				
			}
		}
	}
	
	public void notifyAllBlue(String msg){
		for(ListenerBlue lb : bluelistener){
			lb.action(msg);
		}
	}
	
	public void addListenerBlue(ListenerBlue lb){
		this.bluelistener.add(lb);
	}
	
	public void clearAllListenersBlue(){
		this.bluelistener.clear();
	}
	
	

}
