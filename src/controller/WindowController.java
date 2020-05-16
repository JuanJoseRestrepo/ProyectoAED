package controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import Thread.ThreadTime;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.City;
import model.IGraph;
import model.Station;

public class WindowController implements Initializable{
	
	@FXML
	private Label labelClock;
	@FXML
	private Button addVertex;
	@FXML
	private Button addEdge;
	@FXML
	private Button deleteStation;
	@FXML
	private Pane paneGraph;
	@FXML
	private Pane principalPane;
	
	private Canvas canvas;
	
	private GraphicsContext gc;
	
	private ThreadTime time;
	private City ciudad;
	
	public WindowController() {
		labelClock = new Label();
		startClock();
		ciudad = new City();
		paneGraph = new Pane();
	}
	
	
	 public Canvas getCanvas() {
		return canvas;
	}

	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}

	public GraphicsContext getGc() {
		return gc;
	}

	public void setGc(GraphicsContext gc) {
		this.gc = gc;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	public <T extends Dialog<?>> void setCss(T dialog) {
		
		DialogPane dialogPane = dialog.getDialogPane();
		dialogPane.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
		dialogPane.getStyleClass().add("dialog");
		Stage stage = (Stage) dialogPane.getScene().getWindow();
	}

	public void uptadeTime(String time) {
		
		labelClock.setText(time);
		
	}
	
	public void addVertexButton(ActionEvent e) {
		
		Dialog<Station> dialog = new Dialog<>();
		dialog.setTitle("");
		dialog.setHeaderText("Please type the City name");
		dialog.setResizable(false);
		 
		Label label1 = new Label("City name: ");
		TextField text1 = new TextField();
		         
		GridPane grid = new GridPane();
		grid.add(label1, 1, 1);
		grid.add(text1, 2, 1);
		dialog.getDialogPane().setContent(grid);
		
		ButtonType buttonTypeOk = new ButtonType("Accept", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
		
		dialog.setResultConverter(new Callback<ButtonType, Station>() {
		    public Station call(ButtonType b) {
		 
		    	if(b == buttonTypeOk) {
		    		if(!text1.getText().isEmpty()) {
		    			Station m = new Station(text1.getText());
		    			
		    			return m;
		    			
		    		}else {
		    			showAlert(4);
		    		}
		    	}
		    	
		        return null;
		    }
		});
		setCss(dialog);
		Optional<Station> m1 = dialog.showAndWait();
		
		if(m1.isPresent()) {
			ciudad.addStation(m1.get().getName());
			generarGrafico();
			
		}else {
			showAlert(3);
		}
		
		
	}
	
	
	public void generarGrafico() {
//		Station m = new Station("");
//		Station x = new Station("1");
		paneGraph.getChildren().clear();
		canvas = new Canvas(paneGraph.getPrefWidth(),paneGraph.getPrefHeight());
		paneGraph.getChildren().add(getCanvas());
		gc = canvas.getGraphicsContext2D();
		
		gc.clearRect(0,0,paneGraph.getPrefWidth(), paneGraph.getPrefHeight());
		
		for(int i = 0; i < ciudad.getAdjacentList().getVertexs().size();i++) {
			
			if(ciudad.getAdjacentList().getVertexs().get(0) !=null) {
			gc.setFill(Color.AQUA);
			gc.fillOval(10, 10, 40, 40);
			gc.strokeOval(10,10, 40, 40);
			gc.strokeLine(50,30, 210, 30);
			gc.strokeText("Soy gay", 100, 40);
			}
			
			if(ciudad.getAdjacentList().getVertexs().get(1) !=null) {
				gc.setFill(Color.ANTIQUEWHITE);
				gc.fillOval(210, 10, 40, 40);
				gc.strokeOval(210,10, 40, 40);
			}
			
		}
		
//		if(m != null) {
//			gc.setFill(Color.AQUA);
//			gc.fillOval(10, 10, 40, 40);
//			gc.strokeOval(10,10, 40, 40);
//			gc.strokeLine(50,30, 210, 30);
//			gc.strokeText("Soy gay", 100, 40);
//		}
//		
//		if(x != null) {
//			gc.setFill(Color.ANTIQUEWHITE);
//			gc.fillOval(210, 10, 40, 40);
//			gc.strokeOval(210,10, 40, 40);
//
//		}
		
		
	}
	
	
	public void showAlert(int msg) {
		Alert gameOver = new Alert(AlertType.INFORMATION);
		gameOver.setTitle("ERROR");
		switch (msg) {
		case 1:
			gameOver.setHeaderText("Please check the ID, it must be a number!");
			break;
		case 2:
			gameOver.setHeaderText("Please check the ID, it is empty...");
			break;
		case 3:
			gameOver.setTitle("We are very sorry...");
			gameOver.setHeaderText("But we could not find any bet linked to your ID");
			break;
		case 4:
			gameOver.setHeaderText("Information missing");
			break;
		case 5:
			gameOver.setHeaderText("There can only be 10 horses per race!");
			break;
		case 6:
			gameOver.setHeaderText("Please check the bet,some of the texts are empty...");
			break;
		case 7:
			gameOver.setHeaderText("Please check the horse picked, because we cannot find it");
			break;

		default:
			break;
		}
		
		gameOver.showAndWait();
	}
	
	public void DelateVertexButton(ActionEvent e) {
		
	}
	
	
	public void startClock() {
		time = new ThreadTime(this);
		time.start();
	}

}
