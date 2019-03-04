package application;

import java.util.ArrayList;

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
	public IntensityProfileDrawer(ArrayList<Pair<Double, Double>> diffraction_values, Pane parent) {
		parent.getChildren().clear();
		lineChart = new LineChart<Number, Number>(xAxis, yAxis);
		lineChart.setMaxWidth(parent.getWidth());
		lineChart.setMaxHeight(parent.getHeight());
		lineChart.setLegendVisible(false);
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
