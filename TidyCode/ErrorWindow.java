import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ErrorWindow {
	public static void display(String message) {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("ERROR");
		window.setMinWidth(250);
		window.setMinHeight(125);
		window.setMaxWidth(250);
		window.setMaxHeight(125);
		Label label = new Label();
		label.setText(message);
		
	
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label);
		layout.setAlignment(Pos.CENTER);
		Scene scene = new Scene(layout);
		scene.getStylesheets().add(GUI.class.getResource("styles.css").toExternalForm());
		window.setScene(scene);
		window.showAndWait();
		
		
		 
	}

}