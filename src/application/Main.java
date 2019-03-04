package application;

import javafx.fxml.FXML;

import java.util.ArrayList;

import org.apache.commons.math3.special.BesselJ;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.chart.LineChart;
import javafx.scene.control.*;
//import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.util.Pair;

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

//		Aperture test_aperture = new DoubleSlit(5.00E-04, 6.33E-07, 1.0, 2.00E-03);
//		Aperture test_aperture = new SingleSlit(5.00E-04, 6.33E-07, .8);
		Aperture test_aperture = new CircularHole(5.00E-04, 6.33E-07, .8);
		ArrayList<Pair<Double, Double>> test_values = test_aperture.get_values();
		System.out.print(true);
		CSV_writer writer = new CSV_writer(test_aperture.get_values());
		IntensityProfileDrawer test_drawer = new IntensityProfileDrawer(test_values);
		
		LineChart<Number, Number> test_plot = test_drawer.get_profile();
		
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}




}
