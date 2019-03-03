package application;

import javafx.fxml.FXML;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class VisualAperture {
	
	private double _width;
	private double _height;
	
	private double xValueInit, yValue, xValue;
	
	
	VisualAperture(double width, double height) {
		_width = width;
		_height = height;
		xValueInit = _width * 0.25;
		xValue = xValueInit;
		yValue = _height/2;
		
	}
	
	
}
