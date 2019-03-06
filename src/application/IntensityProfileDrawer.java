package application;

import java.util.*;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Pane;
import javafx.util.Pair;

/**
 * This class assists in the visual representation of the diffraction experiment by 
 * creating a JavaFX LineChart object based on a series of angles and intensities
 *
 */
public class IntensityProfileDrawer {
	
	// Help with method from: https://stackoverflow.com/questions/50869447/javafx-line-chart-using-arrays
	private NumberAxis xAxis = new NumberAxis();
	private NumberAxis yAxis = new NumberAxis();
	private LineChart<Number, Number> lineChart;
	
	/**
	 * Mandatory constructor for IntensityProfile Drawer
	 * @param diffraction_values ArrayList of pairs, with each x value representing 
	 * an angular location in radians and each y value is the intensity value
	 */
	public IntensityProfileDrawer(ArrayList<Pair<Double, Double>> diffraction_values, Pane parent, String color) {
		parent.getChildren().clear();
		ArrayList<Double> diff_x = new ArrayList<Double>();
		for (int i = 0; i < diffraction_values.size(); i++) {
			diff_x.add(diffraction_values.get(i).getKey());
		}
		Double a[] = new Double[diff_x.size()];
		diff_x.toArray(a);
		Arrays.sort(a);
		double bounds = a[0];
		lineChart = new LineChart<Number, Number>(xAxis, yAxis);
		
		lineChart.setMaxWidth(parent.getWidth());
		lineChart.setMaxHeight(parent.getHeight());
		lineChart.getYAxis().setTickLabelsVisible(false);
		//lineChart.getXAxis().setTickLabelsVisible(false);
		lineChart.setMinWidth(parent.getWidth());
		lineChart.setMinHeight(parent.getHeight());
		yAxis.setLowerBound(0);
		yAxis.setUpperBound(1);
		lineChart.setLegendVisible(false);
		lineChart.setHorizontalGridLinesVisible(false);
		lineChart.setVerticalGridLinesVisible(false);
		//lineChart.setHorizontalZeroLineVisible(false);
		lineChart.setVerticalZeroLineVisible(false);
		//xAxis.setTickLabelsVisible(false);
		//yAxis.setTickLabelsVisible(false);
		//xAxis.setTickMarkVisible(false);
		yAxis.setTickMarkVisible(false);
		//lineChart.getXAxis().setOpacity(0);
		lineChart.getYAxis().setOpacity(0);
		lineChart.setId("line");
		
		int r;
		int g;
		int b;
		
		if (color.equals("Red")) {
			r = 255;
			g = 0;
			b = 0;
		}
		
		else if (color.equals("Blue")) {
			r = 0;
			g = 0;
			b = 255;
		}
		
		else {
			r = 0;
			g = 255;
			b = 0;
			
		}
	    lineChart.setStyle( "CHART_COLOR_1: rgb(" + r + "," + g + "," + b + ");" );
	    
		XYChart.Series series = new XYChart.Series<>();
		series.setName("data");
		
		// Add a data point into the series for every value in diffraction values
		for (int i = 0; i < diffraction_values.size(); ++i) {
			series.getData().add(new XYChart.Data(diffraction_values.get(i).getKey(), 
													diffraction_values.get(i).getValue()));
		}
		lineChart.getData().add(series);
		lineChart.setCreateSymbols(false);
		
		parent.getChildren().add(lineChart);
	}
}
