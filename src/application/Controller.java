package application;

import java.text.DecimalFormat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.*;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.event.ActionEvent;

public class Controller {
	@FXML
	private Button btSeparation, btWidth, btDistance, startButton;
	@FXML
	private ToggleGroup colorBts, slitBts;
	@FXML
	private RadioButton btRed, btBlue, btGreen, btSingle, btDouble;
	@FXML
	private Slider slSeparation, slWidth, slDistance;
	@FXML
	private NumberAxis chtX, chtY;
	@FXML
	private LineChart<Number, Number> chtIntensity;
	@FXML
	private TextField txtSeparation, txtWidth, txtDistance;
	@FXML
	private Pane apertureWindow, dPatternWindow, welcomeScreen;
	@FXML
	private TabPane tabWindow;
	
	private VisualAperture apertureGraph;

	
	@FXML
	public void initialize() {
		slSeparation.valueProperty().addListener((observable, oldValue, newValue) -> {
			separationChangedSlider();
		});
		
		slWidth.valueProperty().addListener((observable, oldValue, newValue) -> {
			widthChangedSlider();
		});
		
		slDistance.valueProperty().addListener((observable, oldValue, newValue) -> {
			distanceChangedSlider();
		});
		
		
	}
	

	

	private String selectedColor, slitType;
	private double selectedSeparation, selectedWidth, selectedDistance;
	private int txtChangeType;
	
	private DecimalFormat df = new DecimalFormat(".#");
	
	final double SEP_MAX = 10;
	final double SEP_MIN = 0;
	final double WID_MAX = 3;
	final double WID_MIN = 0.5;
	final double DIS_MAX = 1;
	final double DIS_MIN = 0.5;
	
	@FXML
	protected void start(ActionEvent e) {
		drawGraphs();
		welcomeScreen.setVisible(false);
		
	}
	/*
	 * Updates display with the chosen color
	 * 
	 */
	@FXML
	protected void colorChanged(ActionEvent e) {
		RadioButton selectedRadioButton = (RadioButton) colorBts.getSelectedToggle();
		selectedColor = selectedRadioButton.getText();
		simulate(selectedColor, slitType, selectedSeparation, selectedWidth, selectedDistance);
	}
	
	@FXML
	protected void slitChanged(ActionEvent e) {
		RadioButton selectedRadioButton = (RadioButton) slitBts.getSelectedToggle();
		slitType = selectedRadioButton.getText();
		simulate(selectedColor, slitType, selectedSeparation, selectedWidth, selectedDistance);
	}
	

	
	protected void separationChangedSlider() {
		selectedSeparation = slSeparation.getValue();
		txtSeparation.setText(df.format(selectedSeparation));
		
		//Add in functionality to tie value of input field to "OK" button, and dynamically adjust slider with change
		simulate(selectedColor, slitType, selectedSeparation, selectedWidth, selectedDistance);
	}
	
	@FXML
	protected void separationChangedText(ActionEvent e) {
		txtChangeType = 1;
		String tempSeparation = txtSeparation.getText();
		if (inputValidator(tempSeparation, txtChangeType)) {
			selectedSeparation = Double.valueOf(tempSeparation);
			slSeparation.setValue(selectedSeparation);
			simulate(selectedColor, slitType, selectedSeparation, selectedWidth, selectedDistance);
		}
		else {
			txtSeparation.setText(Double.toString(selectedSeparation));
		}
	}
	
	protected void widthChangedSlider() {
		selectedWidth = slWidth.getValue();
		txtWidth.setText(df.format(selectedWidth));
		
		//Add in functionality to tie value of input field to "OK" button, and dynamically adjust slider with change
		simulate(selectedColor, slitType, selectedSeparation, selectedWidth, selectedDistance);
	}
	
	@FXML
	protected void widthChangedText(ActionEvent e) {
		txtChangeType = 2;
		String tempWidth = txtWidth.getText();
		if (inputValidator(tempWidth, txtChangeType)) {
			selectedWidth = Double.valueOf(tempWidth);
			slWidth.setValue(selectedWidth);
			simulate(selectedColor, slitType, selectedSeparation, selectedWidth, selectedDistance);
		}
		else {
			txtWidth.setText(Double.toString(selectedWidth));
		}
		
	}
	
	protected void distanceChangedSlider() {
		selectedDistance = slDistance.getValue();
		txtDistance.setText(df.format(selectedDistance));
		
		//Add in functionality to tie value of input field to "OK" button, and dynamically adjust slider with change
		simulate(selectedColor, slitType, selectedSeparation, selectedWidth, selectedDistance);
	}
	
	@FXML
	protected void distanceChangedText(ActionEvent e) {
		txtChangeType = 3;
		String tempDistance = txtDistance.getText();
		if (inputValidator(tempDistance, txtChangeType)) {
			selectedDistance = Double.valueOf(tempDistance);
			slDistance.setValue(selectedDistance);
			simulate(selectedColor, slitType, selectedSeparation, selectedWidth, selectedDistance);
		}
		else {
			txtDistance.setText(Double.toString(selectedDistance));
		}
	}
	
	protected void drawGraphs() {
		apertureGraph = new VisualAperture(apertureWindow);
	}
	
	protected boolean inputValidator(String input, int valueType) {
		try {
			double doubInput = Double.valueOf(input);
			
			if (valueType == 1) {
				if (SEP_MIN <= doubInput && doubInput <= SEP_MAX) {
					return true;
				}
			}
				
			if (valueType == 2) {
				if (WID_MIN <= doubInput && doubInput <= WID_MAX) {
					return true;
				}
			}
			
			if (valueType == 3) {
				if (DIS_MIN <= doubInput && doubInput <= DIS_MAX) {
					return true;
				}
							}
		}
		catch(Exception e) {
			return false;
		}
		return false;
	}
	
	/*
	 * Populates the intensity graph
	 * 
	 */
	protected void populateIntensity() {
		
	}
	
	/*
	 * Updates the simulation including aperture, intensity graph, and diffraction pattern
	 * 
	 */
	protected void simulate(String color, String slit, double separation, double width, double distance) {
		// Add graphic simulator functions in Aperture interface and do the same for Diffraction/Intensity class... or put all in one class
	}
	
}
