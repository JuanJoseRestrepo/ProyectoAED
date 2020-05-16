package controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import Thread.ThreadTime;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
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

	private ThreadTime time;
	
	private Canvas canvas;
	
	private GraphicsContext gc;
	
	private City ciudad;
	
	private Station[] stacion;
	
	public WindowController() {
		labelClock = new Label();
		startClock();
		ciudad = new City();
		stacion = new Station[5];
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
			
			if(ciudad.getAdjacentList().getVertexs().size() <= 5) {
			
				if(ciudad.getAdjacentList().getVertexs().isEmpty()) {
					stacion[0] = m1.get();
				}else {
				boolean t = false;
			for(int i = 0; i < stacion.length && !t;i++) {
				if(stacion[i] == null){
					t = true;
					System.out.println("asda");
					stacion[i] = m1.get();
					}
				}
			}
			ciudad.addStation(m1.get().getName());
			generarGrafico();
			
			}else {
				showAlert(1);
			}
		}else {
			showAlert(3);
		}
		
		
	}
	
	
	public void generarGrafico() {
		try {
			canvas = new Canvas(paneGraph.getWidth(),paneGraph.getHeight());
			gc = canvas.getGraphicsContext2D();
			paneGraph.getChildren().add(canvas);
			paneGraph.setOnMouseClicked(new EventHandler<MouseEvent>() {
			    @Override
			    public void handle(MouseEvent event) {
			        System.out.println(event.getSceneX());
			        System.out.println(event.getSceneY());
			    }
			});
			gc.clearRect(0,0,canvas.getWidth(), canvas.getHeight());
			gc.beginPath(); 
			
		if(stacion[0] != null) {
				gc.setFill(Color.WHITE);
				gc.fillOval(10, 10, 70, 70);
				gc.strokeOval(10,10, 70, 70);
				gc.strokeText(stacion[0].getName(),25,50);
				
		}
			
		if(stacion[1] != null) {
				gc.setFill(Color.WHITE);
				gc.fillOval(210, 10, 70, 70);
				gc.strokeOval(210,10, 70, 70);
				gc.strokeText(stacion[1].getName(),225,50);
		}
			
			
		if(stacion[2] != null) {
			gc.setFill(Color.WHITE);
			gc.fillOval(10, 210, 70, 70);
			gc.strokeOval(10, 210, 70, 70);
			gc.strokeText(stacion[2].getName(),25,235);
		}
			
		if(stacion[3]!= null) {
			gc.setFill(Color.WHITE);
			gc.fillOval(210, 210, 70, 70);
			gc.strokeOval(210, 210, 70, 70);	
			gc.strokeText(stacion[3].getName(),225,235);
		}
			
		if(stacion[4] != null) {
			gc.setFill(Color.WHITE);
			gc.fillOval(446, 203, 70, 70);
			gc.strokeOval(446, 203, 70, 70);	
			gc.strokeText(stacion[4].getName(),454,206);
		}
			
			
		}catch(NullPointerException e) {
			
		}catch(IndexOutOfBoundsException e) {
			
		}
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
		try {
		Dialog<Station> dialog = new Dialog<>();
		dialog.setTitle("");
		dialog.setHeaderText("Please type the City name, to be deleted");
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
			
			if(ciudad.getAdjacentList().getVertexs().size() <= 5) {
			for(int i = 0; i < ciudad.getAdjacentList().getVertexs().size();i++) {
				if(m1.get().getName().equalsIgnoreCase(stacion[i].getName())) {
					stacion[i] = null;
				}
				
			}
				
			ciudad.delete(m1.get().getName());
			paneGraph.getChildren().clear();
			generarGrafico();
			}else {
				showAlert(1);
			}
		}else {
			showAlert(3);
		}
		
		}catch(NullPointerException e1) {
			showAlert(5);
		}
	}
	
	
	public void startClock() {
		time = new ThreadTime(this);
		time.start();
	}

}
