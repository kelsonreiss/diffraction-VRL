package application;

import java.text.DecimalFormat;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.util.Pair;
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
	private Pane apertureWindow, dPatternWindow, welcomeScreen, intensityWindow;
	@FXML
	private TabPane tabWindow;
	
	private VisualAperture apertureGraph;
	
	private DiffractionPatternDrawer dPatternDrawer;
	
	private IntensityProfileDrawer intensityPlotDrawer;
	
	private Aperture apertureInUse;
	
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
	private double selectedSeparation, selectedWidth, selectedDistance, selectedWavelength, convertedWidth, convertedSeparation, convertedWavelength;
	private int txtChangeType;
	
	private DecimalFormat df = new DecimalFormat("#.#");
	
	final double SEP_MAX = 10;
	final double SEP_MIN = 0;
	final double WID_MAX = 3;
	final double WID_MIN = 0.5;
	final double DIS_MAX = 1;
	final double DIS_MIN = 0.5;
	
	final double WID_INIT = 1.5;
	final double WAV_INIT = 7e-7;
	final double DIS_INIT = 0.7;
	final String SLIT_INIT = "Single";
	final String COL_INIT = "Red";
	
	@FXML
	protected void start(ActionEvent e) {
		selectedWidth = WID_INIT;
		selectedWavelength = WAV_INIT;
		selectedDistance = DIS_INIT;
		selectedColor = COL_INIT;
		slitType = SLIT_INIT;
		convertedWidth = selectedWidth * 0.001;
//		convertedWavelength = selectedWavelength * 0.000000001;
		
		apertureInUse = new SingleSlit(convertedWidth, selectedWavelength, selectedDistance);
//		apertureInUse = new DoubleSlit(convertedWidth, convertedWavelength, selectedDistance, .002);
		//apertureInUse = new CircularHole(convertedWidth, convertedWavelength, selectedDistance);
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
		simulate(selectedColor, slitType, selectedSeparation, convertedWidth, selectedDistance);
	}
	
	@FXML
	protected void slitChanged(ActionEvent e) {
		RadioButton selectedRadioButton = (RadioButton) slitBts.getSelectedToggle();
		slitType = selectedRadioButton.getText();
		simulate(selectedColor, slitType, selectedSeparation, convertedWidth, selectedDistance);
	}
	

	
	protected void separationChangedSlider() {
		selectedSeparation = slSeparation.getValue();
		convertedSeparation = selectedSeparation * 0.001;
		txtSeparation.setText(df.format(selectedSeparation));
		
		//Add in functionality to tie value of input field to "OK" button, and dynamically adjust slider with change
		simulate(selectedColor, slitType, convertedSeparation, convertedWidth, selectedDistance);
	}
	
	@FXML
	protected void separationChangedText(ActionEvent e) {
		txtChangeType = 1;
		String tempSeparation = txtSeparation.getText();
		if (inputValidator(tempSeparation, txtChangeType)) {
			selectedSeparation = Double.valueOf(tempSeparation);
			convertedSeparation = selectedSeparation * 0.001;
			slSeparation.setValue(selectedSeparation);
			simulate(selectedColor, slitType, selectedSeparation, convertedWidth, selectedDistance);
		}
		else {
			txtSeparation.setText(Double.toString(selectedSeparation));
		}
	}
	
	protected void widthChangedSlider() {
		selectedWidth = slWidth.getValue();
		convertedWidth = selectedWidth * 0.001;
		
		txtWidth.setText(df.format(selectedWidth));
		
		//Add in functionality to tie value of input field to "OK" button, and dynamically adjust slider with change
		simulate(selectedColor, slitType, selectedSeparation, convertedWidth, selectedDistance);
	}
	
	@FXML
	protected void widthChangedText(ActionEvent e) {
		txtChangeType = 2;
		String tempWidth = txtWidth.getText();
		if (inputValidator(tempWidth, txtChangeType)) {
			selectedWidth = Double.valueOf(tempWidth);
			convertedWidth = selectedWidth * 0.001;
			slWidth.setValue(selectedWidth);
			simulate(selectedColor, slitType, selectedSeparation, convertedWidth, selectedDistance);
		}
		else {
			txtWidth.setText(Double.toString(selectedWidth));
		}
		
	}
	
	protected void distanceChangedSlider() {
		selectedDistance = slDistance.getValue();
		txtDistance.setText(df.format(selectedDistance));
		
		//Add in functionality to tie value of input field to "OK" button, and dynamically adjust slider with change
		simulate(selectedColor, slitType, selectedSeparation, convertedWidth, selectedDistance);
	}
	
	@FXML
	protected void distanceChangedText(ActionEvent e) {
		txtChangeType = 3;
		String tempDistance = txtDistance.getText();
		if (inputValidator(tempDistance, txtChangeType)) {
			selectedDistance = Double.valueOf(tempDistance);
			slDistance.setValue(selectedDistance);
			simulate(selectedColor, slitType, selectedSeparation, convertedWidth, selectedDistance);
		}
		else {
			txtDistance.setText(Double.toString(selectedDistance));
		}
	}

	
	protected void drawGraphs() {
		ArrayList<Pair<Double, Double>> diff_values = apertureInUse.get_values();
		apertureGraph = new VisualAperture(apertureWindow, diff_values);
		
		// Create and retrieve intensity profile plot
		intensityPlotDrawer = new IntensityProfileDrawer(diff_values, intensityWindow);
		
//		// If first time generating diffraction pattern, create new drawer
		if (dPatternDrawer == null) {
			dPatternDrawer = new DiffractionPatternDrawer(dPatternWindow, selectedColor, diff_values);
		} else {
			// If pane has already been attached, update values
			dPatternDrawer.updatePane(selectedColor, diff_values);
		}
		
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
	 * Updates the simulation including aperture, intensity graph, and diffraction pattern
	 * 
	 */
	protected void simulate(String color, String slit, double separation, double width, double distance) {
		// Add graphic simulator functions in Aperture interface and do the same for Diffraction/Intensity class... or put all in one class
		
		// Convert color selection to appropriate wavelength value
		if (color == "Red") {
			selectedWavelength = 7e-7;
		} else if (color == "Blue") {
			selectedWavelength = 4.7e-7;
		} else {
			selectedWavelength = 5.3e-7;
		}
		
		// Create appropriate experiment based on aperture selection
		if (slit == "Single") {
			apertureInUse = new SingleSlit(width, selectedWavelength, distance);
		} else if (slit == "Double") {
			apertureInUse = new DoubleSlit(width, selectedWavelength, distance, separation);
		} else {
			apertureInUse = new CircularHole(width, selectedWavelength, distance);
		}
		
		drawGraphs();
	}
	
}
