package application;


import java.util.ArrayList;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.util.Pair;

public class VisualAperture {
	
	private double _width;
	private double _height;
	
	private double yValueMid, yValue, apertureTop, apertureBottom, arrowEndX, arrowEndYTop, arrowEndYBottom, apertureDistance;

	private Line apertureLine, lineToAperture;
	
	private Polygon arrow;
	
	private Pane parentContainer;
	
	private ArrayList<Pair<Double, Double>> diffraction_values;

	
	public VisualAperture(Pane parent, ArrayList<Pair<Double, Double>> values, double apertureDistance, String laserColor) {
		parentContainer = parent;
		diffraction_values = values;
		parentContainer.setStyle("-fx-background-color: #000000");
		_width = parentContainer.getWidth();
		_height = parentContainer.getHeight();
		yValueMid = _height/2.0;
		yValue = yValueMid;
		apertureTop = yValueMid-0.1*_height;
		apertureBottom = yValueMid+0.1*_height;
		
		drawAperture(apertureDistance, laserColor);

		
		/*GraphicsContext gc = this.getGraphicsContext2D();
		gc.setFill(Color.BLACK);
		gc.setStroke(Color.rgb(72, 244, 66, 0.7));
		gc.setLineWidth(5);
		gc.strokeLine */
		
		
		
	}
	
	public void drawAperture(double distance, String color) {
		int r = 0;
		int b = 0;
		int g = 0;
		
		Color c;
		
		if (color.equals("Red")) {
			r = 255;
			b = 0;
			g = 0;
			c = Color.RED;
		}
		
		else if (color.equals("Blue")) {
			r = 0;
			b = 255;
			g = 0;
			c = Color.BLUE;
		}
		
		else {
			r = 0;
			b = 0;
			g = 255;
			c = Color.GREEN;
		}
		parentContainer.getChildren().clear();
		apertureDistance = _width-distance*_width;

		apertureLine = new Line(apertureDistance, apertureBottom, apertureDistance, apertureTop);
		apertureLine.setStroke(Color.rgb(255,255,0, 0.6));
		apertureLine.setStrokeWidth(3);
		parentContainer.getChildren().add(apertureLine);
		apertureDistance -= 1.5;
		arrowEndX = apertureDistance-0.02*_width;
		arrowEndYTop = yValue-0.02*_height;
		arrowEndYBottom = yValue+0.02*_height;
		arrow = new Polygon();
		arrow.setFill(c);
		arrow.getPoints().addAll(new Double[] {
				arrowEndX, arrowEndYTop,
				arrowEndX, arrowEndYBottom,
				apertureDistance, yValue
		});
		parentContainer.getChildren().add(arrow);
		
		lineToAperture = new Line(0, yValue, arrowEndX, yValue);
		lineToAperture.setStroke(c);
		lineToAperture.setStrokeWidth(2);
		parentContainer.getChildren().add(lineToAperture);
		

		
		for (int i = 0; i < diffraction_values.size(); i += 1) {
			Pair<Double, Double> current = diffraction_values.get(i);
			if (current.getValue() > 0) {
				Line line = new Line(apertureDistance+3, yValueMid, _width, current.getKey()*50000+yValueMid);
				line.setStroke(Color.rgb(r, g, b, current.getValue()));
				parentContainer.getChildren().add(line);
			}
			
			
			
		} 
	}
	
	
	
}
