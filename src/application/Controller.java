package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.*;

public class Controller {
	@FXML
	private Button btSeparation, btWidth, btDistance;
	@FXML
	private RadioButton btRed, btBlue, btGreen, btSingle, btDouble;
	@FXML
	private Slider slSeparation, slWidth, slDistance;

	private String currentColor;

	public String getCurrentColor() {
		return currentColor;
	}

	public void setCurrentColor(String currentColor) {
		this.currentColor = currentColor;
	}

	protected void colorPressed() {

	}
	
}
