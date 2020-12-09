package guiClasses;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class OverduePaymentsWindow {

	public static void display(String message) {
		Stage OverduePayments = new Stage();

		OverduePayments.initModality(Modality.APPLICATION_MODAL);
		OverduePayments.setTitle("Overdue Payments");
		OverduePayments.setMinWidth(400);

		Label label = new Label();
		label.setText("These are your payments that are overdue:\n" + message);
		Button closeButton = new Button("Close the window");
		closeButton.setOnAction(e -> {

			OverduePayments.close();

		});

		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, closeButton);
		layout.setAlignment(Pos.CENTER);

		Scene scene = new Scene(layout);
		scene.getStylesheets().add(GUI.class.getResource("styles.css").toExternalForm());
		OverduePayments.setScene(scene);
		OverduePayments.showAndWait();
		
	}

}
