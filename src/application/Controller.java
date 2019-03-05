package application;

import java.text.DecimalFormat;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.util.Pair;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;

public class Controller {
	@FXML
	private Button btSeparation, btWidth, btDistance, startButton;


	@FXML
	private RadioButton btRed, btBlue, btGreen, btSingle, btDouble, btCircular;
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
	
	//Initiate global custom class variables
	private VisualAperture apertureGraph;
	private DiffractionPatternDrawer dPatternDrawer;
	private IntensityProfileDrawer intensityPlotDrawer;
	private Aperture apertureInUse;
	
	//Initiate variable to represent user input and conversions between them for equations
	private String selectedColor, slitType;
	private double selectedSeparation, selectedWidth, selectedDistance, selectedWavelength, convertedWidth, convertedSeparation, convertedWavelength;
	
	//To track user input of valid numbers and format values from slider placed in text field
	private int txtChangeType;
	private DecimalFormat df = new DecimalFormat("#.#");
	
	//Maximum and Minimum values for each input
	final double SEP_MAX = 10;
	final double SEP_MIN = 0;
	final double WID_MAX = 3;
	final double WID_MIN = 0.5;
	final double DIS_MAX = 1;
	final double DIS_MIN = 0.5;
	
	//Initial input values
	final double WID_INIT = 1.5;
	final double WAV_INIT = 700;
	final double DIS_INIT = 0.7;
	final int SLIT_INIT = 1;
	final String COL_INIT = "Red";
	
	//Boolean to only initialize objects on first function call
	private boolean init = true;
	
	//Create Toggle Groups for Radio Buttons
	private ToggleGroup colorBts, slitBts;
	

	
    /**
	 * Function which initializes listeners for the three sliders
     */
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
		
		colorBts = new ToggleGroup();
		slitBts = new ToggleGroup();
		
		//Add color buttons to colorBts;
		btRed.setToggleGroup(colorBts);
		btBlue.setToggleGroup(colorBts);
		btGreen.setToggleGroup(colorBts);
		
		//Add slit buttons to slitBts
		btSingle.setToggleGroup(slitBts);
		btDouble.setToggleGroup(slitBts);
		btCircular.setToggleGroup(slitBts);
		
	}
	
<<<<<<< HEAD
    /**
	 * Function which prepares selected values and calls drawGraphs with initial inputs set
     */
=======
	

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
	
>>>>>>> branch 'master' of https://github.com/kpr404/diffraction-VRL.git
	@FXML
	protected void start() {
		selectedWidth = WID_INIT;
		selectedWavelength = WAV_INIT;
		selectedDistance = DIS_INIT;
		selectedColor = COL_INIT;
		slitType = SLIT_INIT;
		convertedWidth = selectedWidth * 0.001;
//		convertedWavelength = selectedWavelength * 0.000000001;
		
<<<<<<< HEAD
		
=======
		apertureInUse = new SingleSlit(convertedWidth, selectedWavelength, selectedDistance);
//		apertureInUse = new DoubleSlit(convertedWidth, convertedWavelength, selectedDistance, .002);
		//apertureInUse = new CircularHole(convertedWidth, convertedWavelength, selectedDistance);
>>>>>>> branch 'master' of https://github.com/kpr404/diffraction-VRL.git
		drawGraphs();
		welcomeScreen.setVisible(false);
		
	}
	
    /**
	 * Function to update graph colors when the user selects a new color
     */
	@FXML
	protected void colorChanged() {
		RadioButton selectedRadioButton = (RadioButton) colorBts.getSelectedToggle();
		selectedColor = selectedRadioButton.getText();
<<<<<<< HEAD
		if (selectedColor.equals("Red")) {
			selectedWavelength = 700;
		}
		else if (selectedColor.equals("Blue")) {
			selectedWavelength = 450;
		}
		else {
			selectedWavelength = 530;
		}
		convertedWavelength = selectedWavelength * 0.000000001;
		drawGraphs();
=======
		simulate(selectedColor, slitType, selectedSeparation, convertedWidth, selectedDistance);
>>>>>>> branch 'master' of https://github.com/kpr404/diffraction-VRL.git
	}
	
	
    /**
	 * Function to update slit type to user input
     */
	@FXML
	protected void slitChanged() {
		RadioButton selectedRadioButton = (RadioButton) slitBts.getSelectedToggle();
		slitType = selectedRadioButton.getText();
<<<<<<< HEAD
		drawGraphs();
=======
		simulate(selectedColor, slitType, selectedSeparation, convertedWidth, selectedDistance);
>>>>>>> branch 'master' of https://github.com/kpr404/diffraction-VRL.git
	}
	

    /**
	 * Function to update slit separation to user input on slider
     */
	protected void separationChangedSlider() {
		selectedSeparation = slSeparation.getValue();
		convertedSeparation = selectedSeparation * 0.001;
		txtSeparation.setText(df.format(selectedSeparation));
<<<<<<< HEAD
		drawGraphs();
=======
		
		//Add in functionality to tie value of input field to "OK" button, and dynamically adjust slider with change
		simulate(selectedColor, slitType, convertedSeparation, convertedWidth, selectedDistance);
>>>>>>> branch 'master' of https://github.com/kpr404/diffraction-VRL.git
	}
	
    /**
	 * Function to update slit separation when user enters a new value into the separation text field
     */
	@FXML
	protected void separationChangedText() {
		txtChangeType = 1;
		String tempSeparation = txtSeparation.getText();
		if (inputValidator(tempSeparation, txtChangeType)) {
			selectedSeparation = Double.valueOf(tempSeparation);
			convertedSeparation = selectedSeparation * 0.001;
			slSeparation.setValue(selectedSeparation);
<<<<<<< HEAD
			drawGraphs();
=======
			simulate(selectedColor, slitType, selectedSeparation, convertedWidth, selectedDistance);
>>>>>>> branch 'master' of https://github.com/kpr404/diffraction-VRL.git
		}
		else {
			txtSeparation.setText(Double.toString(selectedSeparation));
		}
	}
	
    /**
	 * Function to update slit width to user input on slider
     */
	protected void widthChangedSlider() {
		selectedWidth = slWidth.getValue();
		convertedWidth = selectedWidth * 0.001;
		
		txtWidth.setText(df.format(selectedWidth));
		
<<<<<<< HEAD
		drawGraphs();
=======
		//Add in functionality to tie value of input field to "OK" button, and dynamically adjust slider with change
		simulate(selectedColor, slitType, selectedSeparation, convertedWidth, selectedDistance);
>>>>>>> branch 'master' of https://github.com/kpr404/diffraction-VRL.git
	}
	
    /**
	 * Function to update slit width when user enters a new value into the width text field
     */
	@FXML
	protected void widthChangedText() {
		txtChangeType = 2;
		String tempWidth = txtWidth.getText();
		if (inputValidator(tempWidth, txtChangeType)) {
			selectedWidth = Double.valueOf(tempWidth);
			convertedWidth = selectedWidth * 0.001;
			slWidth.setValue(selectedWidth);
<<<<<<< HEAD
			drawGraphs();
=======
			simulate(selectedColor, slitType, selectedSeparation, convertedWidth, selectedDistance);
>>>>>>> branch 'master' of https://github.com/kpr404/diffraction-VRL.git
		}
		else {
			txtWidth.setText(Double.toString(selectedWidth));
		}
		
	}
	
    /**
	 * Function to update aperture distance to user input on slider
     */
	protected void distanceChangedSlider() {
		selectedDistance = slDistance.getValue();
		txtDistance.setText(df.format(selectedDistance));
		
<<<<<<< HEAD
		drawGraphs();
=======
		//Add in functionality to tie value of input field to "OK" button, and dynamically adjust slider with change
		simulate(selectedColor, slitType, selectedSeparation, convertedWidth, selectedDistance);
>>>>>>> branch 'master' of https://github.com/kpr404/diffraction-VRL.git
	}
	
    /**
	 * Function to update aperture distance when user enters a new value into the distance text field
     */
	@FXML
	protected void distanceChangedText() {
		txtChangeType = 3;
		String tempDistance = txtDistance.getText();
		if (inputValidator(tempDistance, txtChangeType)) {
			selectedDistance = Double.valueOf(tempDistance);
			slDistance.setValue(selectedDistance);
<<<<<<< HEAD
			drawGraphs();
=======
			simulate(selectedColor, slitType, selectedSeparation, convertedWidth, selectedDistance);
>>>>>>> branch 'master' of https://github.com/kpr404/diffraction-VRL.git
		}
		else {
			txtDistance.setText(Double.toString(selectedDistance));
		}
	}

    /**
	 * Class which initializes the visualizations the first time it is called, and redraws them after
     */
	protected void drawGraphs() {
		if (init) {
			apertureInUse = new SingleSlit(convertedWidth, convertedWavelength, selectedDistance);
			ArrayList<Pair<Double, Double>> diff_values = apertureInUse.get_values();
			intensityPlotDrawer = new IntensityProfileDrawer(diff_values, intensityWindow);
			apertureGraph = new VisualAperture(apertureWindow, diff_values, selectedDistance, selectedColor);
			dPatternDrawer = new DiffractionPatternDrawer(dPatternWindow, selectedColor, diff_values);
			init = false;
			slitType = "Single";
		}

		
		else {
			if (slitType.equals("Single")) {
				apertureInUse = new SingleSlit(convertedWidth, convertedWavelength, selectedDistance);
			}
			else if (slitType.equals("Double")) {
				apertureInUse = new DoubleSlit(convertedWidth, convertedWavelength, selectedDistance, convertedSeparation);
			}
			else {
				apertureInUse = new CircularHole(convertedWidth, convertedWavelength, selectedDistance);
			}
			ArrayList<Pair<Double, Double>> diff_values = apertureInUse.get_values();
			
			intensityPlotDrawer = new IntensityProfileDrawer(diff_values, intensityWindow);
			
			dPatternDrawer.updatePane(selectedColor, diff_values);
			
			apertureGraph = new VisualAperture(apertureWindow, diff_values, selectedDistance, selectedColor);
		}
		
	}
	
    /**
	 * Function to validate user string input is a valid integer
	 * @param input - user String input which should be an integer
	 * @param valueType - identifies which value was updated
	 * @return bool - true if valid string, false otherwise
     */
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
<<<<<<< HEAD
=======
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
>>>>>>> branch 'master' of https://github.com/kpr404/diffraction-VRL.git
	
}
