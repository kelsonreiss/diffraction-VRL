import javafx.fxml.FXML;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.stage.Stage;


public class Main extends Application {
	
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Main.fxml"));

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
	
	@FXML private Button btSeparation, btWidth, btDistance;
	@FXML private RadioButton btRed, btBlue, btGreen, btSingle, btDouble;
	@FXML private Slider slSeparation, slWidth, slDistance;
	
	private String currentColor;
	currentColor = "red";
	
	fprotected void colorPressed() {
		
	}
}
