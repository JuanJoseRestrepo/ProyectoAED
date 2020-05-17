package controller;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import thread.ThreadTime;
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
import model.Edge;
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
	private Button distance;
	@FXML
	private Button stations;
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
		stacion = new Station[6];
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
			
			if(ciudad.cityExist(m1.get().getName())) {
				showAlert(9);
			}else{
				boolean t = false;
				for(int i = 0; i < stacion.length && !t;i++) {
					if(stacion[i] == null){
						t = true;
						stacion[i] = m1.get();
					}
				}
				ciudad.addStation(m1.get().getName());
				generarGrafico();
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
			gc.clearRect(0,0,canvas.getWidth(), canvas.getHeight());
			gc.beginPath();
			gc.setFill(Color.WHITE);
			for(int i = 0; i < stacion.length; i++) {
				if(stacion[i] != null) {
					if(i < 3) {
						gc.fillOval((i%3)*200+10, 10, 70, 70);
						gc.strokeOval((i%3)*200+10,10, 70, 70);
						gc.strokeText(stacion[i].getName(), (i%3)*200+25, 50);
					} else {
						gc.fillOval((i%3)*200+10, 235, 70, 70);
						gc.strokeOval((i%3)*200+10, 235, 70, 70);
						gc.strokeText(stacion[i].getName(),(i%3)*200+25,270);
					}
				}
			}
			gc.setStroke(Color.WHITE);
			for(int i = 0; i < stacion.length; i++) {
				for(int j = i; j < stacion.length; j++) {
					if(stacion[i] !=null && stacion[j] != null && edgeExist(stacion[i],stacion[j]) ) {
						if(i < 3) {
							if(j < 3) {
								gc.strokeLine((i%3)*200+10+70,50, (j%3)*200+10,50);
								gc.strokeText(Integer.toString(ciudad.getWeightFromStation(stacion[i], stacion[j])), ((j%3)*200+70+10+(i%3)*200+10)/2, 50);
							} else {
								gc.strokeLine((i%3)*200+10+70,50, (j%3)*200+10,275);
								gc.strokeText(Integer.toString(ciudad.getWeightFromStation(stacion[i], stacion[j])), ((j%3)*200+70+10+(i%3)*200+10)/2, (50+275)/2);
							}
						} else {
							if(j > 2) {
								gc.strokeLine((i%3)*200+10+70,275, (j%3)*200+10,275);
								gc.strokeText(Integer.toString(ciudad.getWeightFromStation(stacion[i], stacion[j])), ((j%3)*200+70+10+(i%3)*200+10)/2, 275);
							}
						}
					}
				}
			}
		}catch(NullPointerException e) {

		}catch(IndexOutOfBoundsException e) {

		}
	}


	public boolean edgeExist(Station m, Station m1) {
		boolean t = false;
		List<Edge<Station>> m2 = ciudad.getAdjacentList().getEdges();
		for(int i = 0; i < m2.size() && !t;i++) {
			if((m2.get(i).getFirst().getName().equalsIgnoreCase(m.getName())) && (m2.get(i).getSecond().getName().equalsIgnoreCase(m1.getName()))) {
				t = true;
			}else if((m2.get(i).getFirst().getName().equalsIgnoreCase(m1.getName())) && (m2.get(i).getSecond().getName().equalsIgnoreCase(m.getName()))) {
				t = true;
			}	
		}
		return t;
	}
	

	public void showAlert(int msg) {
		Alert gameOver = new Alert(AlertType.INFORMATION);
		gameOver.setTitle("ERROR");
		switch (msg) {
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
			gameOver.setHeaderText("Mi rey, eso no es el formato indicado para un numero :C");
			break;
		case 8:
		gameOver.setHeaderText("Mi rey, no se pueden añadir mas");
		break;
		case 9:
		gameOver.setHeaderText("Mi rey,ya hay uno igual");
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

				if(!ciudad.getAdjacentList().getVertexs().isEmpty()) {
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

	
	public void connectToStation(ActionEvent e) {
		try {
		Dialog<String> dialog = new Dialog<>();
		dialog.setTitle("");
		dialog.setHeaderText("Please type the City name, to be deleted");
		dialog.setResizable(false);
		 
		Label label1 = new Label("City name 1: ");
		TextField text1 = new TextField();
		Label label2 = new Label("City name 2: ");
		TextField text2 = new TextField();
		Label label3 = new Label("Weight: ");
		TextField text3 = new TextField();
		         
		GridPane grid = new GridPane();
		grid.add(label1, 1, 1);
		grid.add(text1, 2, 1);
		grid.add(label2,1,2);
		grid.add(text2,2,2);
		grid.add(label3,1, 3);
		grid.add(text3,2,3);
		dialog.getDialogPane().setContent(grid);
		
		ButtonType buttonTypeOk = new ButtonType("Accept", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
		
		dialog.setResultConverter(new Callback<ButtonType, String>() {
			public String call(ButtonType b) {

				if(b == buttonTypeOk) {
					if(!text1.getText().isEmpty() && !text2.getText().isEmpty() && !text3.getText().isEmpty()) {
						String m = text1.getText() + ";" + text2.getText();

						return m;

					}else {
						showAlert(4);
					}
				}

				return null;
			}
		});
		
		
		setCss(dialog);
		
		Optional<String> m1 = dialog.showAndWait();
		
		if(m1.isPresent()) {
			
			String[] msj = m1.get().split(";");
			ciudad.connect(msj[0], msj[1],Integer.parseInt(text3.getText()));
			generarGrafico();
			 
		}else {
			showAlert(2);
		}
		}catch(NullPointerException e1) {
			showAlert(6);
		}catch(NumberFormatException e1) {
			
		}
	}
	
	
	public void startClock() {
		time = new ThreadTime(this);
		time.start();
	}
	

}
