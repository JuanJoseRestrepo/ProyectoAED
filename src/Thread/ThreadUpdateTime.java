package thread;

import controller.*;

public class ThreadUpdateTime extends Thread{

	private String time;
	private WindowController m;
	public ThreadUpdateTime(String time, WindowController m) {
		super();
		this.time = time;
		this.m = m;
		setDaemon(true);
	}
	
	public void run() {
		m.uptadeTime(time);
	}
}