package application;

import javafx.fxml.FXML;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class VisualAperture {
	
	private double _width;
	private double _height;
	
	private double yValueMid, xValue, yValue, apertureTop, apertureBottom, arrowEndX, arrowEndYTop, arrowEndYBottom, apertureDistance;

	private Line apertureLine, lineToAperture;
	
	private Polygon arrow;
	
	private Pane parentContainer;
	
	
	public VisualAperture(Pane parent) {
		parentContainer = parent;
		parentContainer.setStyle("-fx-background-color: #000000");
		_width = parentContainer.getWidth();
		_height = parentContainer.getHeight();
		yValueMid = _height/2.0;
		yValue = yValueMid;
		apertureTop = yValueMid-0.1*_height;
		apertureBottom = yValueMid+0.1*_height;
		
		drawAperture(0.7);

		
		/*GraphicsContext gc = this.getGraphicsContext2D();
		gc.setFill(Color.BLACK);
		gc.setStroke(Color.rgb(72, 244, 66, 0.7));
		gc.setLineWidth(5);
		gc.strokeLine */
		
		
		
	}
	
	public void drawAperture(double distance) {
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
		arrow.setFill(Color.rgb(255,0,0,1));
		arrow.getPoints().addAll(new Double[] {
				arrowEndX, arrowEndYTop,
				arrowEndX, arrowEndYBottom,
				apertureDistance, yValue
		});
		parentContainer.getChildren().add(arrow);
		
		lineToAperture = new Line(0, yValue, arrowEndX, yValue);
		lineToAperture.setStroke(Color.rgb(255, 0, 0));
		lineToAperture.setStrokeWidth(2);
		parentContainer.getChildren().add(lineToAperture);
	}
	
	
	
}
