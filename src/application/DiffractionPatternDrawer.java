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
			intensityLine = new Line(xPos, diffractionPatternPane.getHeight(), xPos, 0);
			// For each color, multiply the intensity value by 255 to represent varying 
			// degrees of diffraction with different shades 
			int r;
			int g;
			int b;
			
			if (color.equals("Blue")) {
				r = 0;
				g = 0;
				b = 255;
			} else if (color.equals("Red")) {
				r = 255;
				g = 0;
				b = 0;
			} else {
				r = 0;
				g = 255;
				b = 0;
			}
			
			double opacity = diffraction_values.get(i).getValue();

			
			if (opacity > 1) {
				opacity = 1;
			}
			intensityLine.setStroke(Color.rgb(r, g, b, opacity));
			intensityLine.setStrokeWidth(lineWidth);
			
			xPos += lineWidth;
			diffractionPatternPane.getChildren().add(intensityLine);
		}
	}
	
}
