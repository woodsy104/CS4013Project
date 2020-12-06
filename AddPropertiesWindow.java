package guiClasses;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddPropertiesWindow {

	public static void display(String message) {
		Stage addProps = new Stage();

		addProps.initModality(Modality.APPLICATION_MODAL);
		addProps.setTitle("Add Properties");
		addProps.setMinWidth(800);

		Label label = new Label();
		label.setText("Insert data here to add a property \n" +message);
		Button confirmButton = new Button("Confirm choices");
		confirmButton.setOnAction(e ->{
			boolean result = ConfirmBox.display("Confirmation", "Are you sure you want to add this properties");
			System.out.println(result);
			if(result == true) {
				//save all this information and now close both windows
				
				addProps.close();
			}
		});

		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, confirmButton);
		layout.setAlignment(Pos.CENTER);

		Scene scene = new Scene(layout);
		addProps.setScene(scene);
		addProps.showAndWait();



	}

}
