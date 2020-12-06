package guiClasses;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PreviousYearsWindow {

	public static void display(String message) {
		Stage PrevYear = new Stage();
		
		PrevYear.initModality(Modality.APPLICATION_MODAL);
		PrevYear.setTitle("Overdue Payments");
		PrevYear.setMinWidth(400);

		Label label = new Label();
		label.setText("These are your payments that are overdue:\n" + message);
		Button closeButton = new Button("Close the window");
		closeButton.setOnAction(e -> PrevYear.close());
		
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, closeButton);
		layout.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(layout);
		PrevYear.setScene(scene);
		PrevYear.showAndWait();
		
		
	}

}