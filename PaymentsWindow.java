package guiClasses;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PaymentsWindow {

	public static void display(String message) {
		Stage payments = new Stage();
		
		payments.initModality(Modality.APPLICATION_MODAL);
		payments.setTitle("Payments");
		payments.setMinWidth(400);

		Label label = new Label();
		label.setText("These are the payments made by user:\n" +message);
		Button closeButton = new Button("Close the window");
		closeButton.setOnAction(e -> payments.close());
		
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, closeButton);
		layout.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(layout);
		payments.setScene(scene);
		scene.getStylesheets().add(GUI.class.getResource("styles.css").toExternalForm());
		payments.showAndWait();
		
		
	}

}
