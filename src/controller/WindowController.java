package controller;

import java.net.URL;
import java.util.ResourceBundle;

import Thread.ThreadTime;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.City;

public class WindowController implements Initializable{
	
	@FXML
	private Label labelClock;
	@FXML
	private Button addVertex;
	
	private ThreadTime time;
	private City city;
	
	 public WindowController() {
		labelClock = new Label();
		startClock();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

	public void uptadeTime(String time) {
		
		labelClock.setText(time);
		
	}
	
	
	public void startClock() {
		time = new ThreadTime(this);
		time.start();
	}

}
