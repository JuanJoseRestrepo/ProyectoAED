package thread;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import controller.*;
import javafx.application.Platform;

public class ThreadTime extends Thread{

	String hour;
	String minutes;
	String seconds;
	String timeDay;
	Calendar calendar;
	private WindowController m;
	
	
	
	public ThreadTime(WindowController m) {
		super();
		this.m = m;
		setDaemon(true);
	}

	public void run() {
		
		while(true) {
			calcula();
			String msj = hour + ":" + minutes + ":" + seconds;
			ThreadUpdateTime tim = new ThreadUpdateTime(msj,m);
			Platform.runLater(tim);
			try {
				Thread.sleep(1000);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	 public void calcula() {
		 	calendar = new GregorianCalendar();
			Date actualTime = new Date();
			
			calendar.setTime(actualTime);
			timeDay = calendar.get(Calendar.AM_PM)==Calendar.AM ? "AM" : "PM" ;
			if(timeDay.equals("PM")) {
				int h = calendar.get(Calendar.HOUR_OF_DAY) - 12;
				hour = (h > 9) ? "" + h : "0" + h; 
			}else {
				hour =  (calendar.get(Calendar.HOUR_OF_DAY) > 9 ) ? "" + calendar.get(Calendar.HOUR_OF_DAY) : "0" + calendar.get(Calendar.HOUR_OF_DAY);
			}
			minutes = (calendar.get(Calendar.MINUTE) > 9 ) ? "" + calendar.get(Calendar.MINUTE) : "0" + calendar.get(Calendar.MINUTE);
			seconds = (calendar.get(Calendar.SECOND) > 9 ) ? "" + calendar.get(Calendar.SECOND) : "0" + calendar.get(Calendar.SECOND);
	    }
	
}