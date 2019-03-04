package application;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.util.Pair;

/**
 * Helper class to draw the diffraction pattern representation
 *
 */
public class DiffractionPatternDrawer {
 
	private Pane diffractionPatternPane;
	
	/**
	 * Default constructor
	 */
	public DiffractionPatternDrawer(Pane parent, String color, ArrayList<Pair<Double, Double>> diffraction_values) {
		this.diffractionPatternPane = parent;
		updatePane(color, diffraction_values);
	}
	
	/**
	 * Redraws diffraction pattern pane
	 * @param color String value for stroke color
	 * @param diffraction_values ArrayList of x-values and intensities
	 */
	public void updatePane(String color, ArrayList<Pair<Double, Double>> diffraction_values) {
		// Clear old graph
		diffractionPatternPane.getChildren().clear();
		
		// Width of each stroke depends on total number of values
		double lineWidth = diffractionPatternPane.getWidth() 
				/ diffraction_values.size();
		
		double xPos = 0;
		Line intensityLine;
		// Populate the pattern pane with lines
		for (int i = 0; i < diffraction_values.size()-1; ++i) {
			// Line height should match its container's height
			intensityLine = new Line(xPos, diffractionPatternPane.getHeight(), xPos + lineWidth, 0);
			
			// For each color, multiply the intensity value by 255 to represent varying 
			// degrees of diffraction with different shades 
			if (color == "blue") {
				intensityLine.setStroke(Color.rgb(0, 0, (int) (diffraction_values.get(i).getValue() * 255)));
			} else if (color == "red") {
				intensityLine.setStroke(Color.rgb((int) (diffraction_values.get(i).getValue() * 255), 0,0));
			} else {
				intensityLine.setStroke(Color.rgb(0, (int) (diffraction_values.get(i).getValue() * 255),0));
			}
			
			xPos += lineWidth;
			diffractionPatternPane.getChildren().add(intensityLine);
		}
	}
	
}
