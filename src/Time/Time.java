package Time;

import java.awt.event.ActionListener;

public class Time {
	protected int  rHour = 0;
	protected int rMinute  = 0;
	protected int rSecond = 0;
	protected int hour = 0;
	protected int minute = 0;
	protected int second = 0;
	protected int millisecond = 0;
	protected Ticking rt = null;
	protected Thread th = null;
	
	protected boolean ticking = false;
	public Time(int hour, int minute, int second) {
		super();
		this.rHour = hour;
		this.rMinute = minute;
		this.rSecond = second;
		this.hour = hour;
		this.minute = minute;
		this.second = second;
		
		this.ticking = true;
		this.rt = new Ticking(this);
	}
	
	public void addlistener(TickingListener l){
		this.rt.addlistener(l);
	}
	public void start(){
		this.ticking = true;
	    th = new Thread(this.rt);
		th.start();
	}
	
	public void pause(){
		this.ticking = false;
		th.interrupt();
	}
	
	public void stop(){
		this.ticking = false;
	}
	
	public void incrementSecond(){
		this.second++;
		if(this.second>=60){
			incrementMinute();
			this.second=0;
		}
	}
	
	public void incrementHour(){
		this.hour++;
	}
	public void incrementMilli(){
		this.millisecond++;
		if(this.millisecond>=1000){
			incrementSecond();
			this.millisecond = 0;
		}
	}
	public void incrementMinute(){
		this.minute++;
		if(this.minute>=60){
			incrementHour();
			this.hour=0;
		}
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String second = this.second + "";
		String minute = this.minute +"";
		if(second.length()<2){
			second = "0"+second;
		}
		
		if(minute.length()<2){
			minute = "0" + minute;
		}
		return(minute+":"+second+"");
	}
	
	public void resetTime(){
		this.hour=this.rHour;
		this.minute=this.rMinute;
		this.second=this.rSecond;
		this.rt.alert();
	}
	//Private for a reason...
	private void decrementHour(){
		if(this.hour<=0){
			this.millisecond = 0;
			this.second = 0;
			this.minute=0;
			this.hour=0;
		}else{
			hour--;
		}
	}
	
	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public int getSecond() {
		return second;
	}

	public void setSecond(int second) {
		this.second = second;
	}

	public int getMillisecond() {
		return millisecond;
	}

	public void setMillisecond(int millisecond) {
		this.millisecond = millisecond;
	}

	public void decrementMinute(){
		if(this.minute<=0){
			decrementHour();
			this.minute = 59;
		}else{
			this.minute--;
		}
	}
	public void decrementSecond(){
		if(this.second <=0){
			decrementMinute();
			this.second = 59;
		}
		else{
			this.second --;
		}
	}
	
	public void decrementMilli(){
		if(this.millisecond<=0){
			decrementSecond();
			this.millisecond = 999;
		}
		else{
			this.millisecond--;
		}
	}
	
	public boolean isOutOfTime(){
		if(this.millisecond==0 && this.second ==0 && this.minute == 0 && this.hour == 0){
			return(true);
		}else{
			return(false);
		}
	}
	
	public void addOutOfTimeListener(ActionListener l){
		this.rt.addOutOfTimeListener(l);
	}

}
