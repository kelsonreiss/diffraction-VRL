package application;

import javafx.fxml.FXML;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
//import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static void main(String[] args) {
		Application.launch(Main.class, args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
		
		//Set maximum and minimum bounds to fixedSize
		int fixedSize = 800;
		primaryStage.setMinHeight(fixedSize + 13);
		primaryStage.setMaxHeight(fixedSize + 13);
		primaryStage.setMinWidth(fixedSize);
		primaryStage.setMaxWidth(fixedSize);
		primaryStage.setTitle("Optics Virtual Lab");
		
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}




}
