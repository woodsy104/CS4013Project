package guiClasses;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PayWindow {

	public static void display(String message) {
		Stage Pay= new Stage();
		
		Pay.initModality(Modality.APPLICATION_MODAL);
		Pay.setTitle("Overdue Payments");
		Pay.setMinWidth(400);

		Label label = new Label();
		label.setText("These are your payments that are overdue:\n" + message);
		Button closeButton = new Button("Close the window");
		closeButton.setOnAction(e -> { 
			boolean result = ConfirmBox.display("Confirmation", "Are you sure you want to pay for this properties");
			System.out.println(result);
			if(result == true) {
				//Pay for this house
				
				Pay.close();
			}
		});
		
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, closeButton);
		layout.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(layout);
		Pay.setScene(scene);
		Pay.showAndWait();
		
		
	}

}