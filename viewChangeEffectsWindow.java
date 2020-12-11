package guiClasses;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class viewChangeEffectsWindow {
	private static DoubleProperty fontSize = new SimpleDoubleProperty(4);


	public static void display(String message) {
		Stage viewChanges = new Stage();

		viewChanges.initModality(Modality.APPLICATION_MODAL);
		viewChanges.setTitle("View Tax change effects");
		viewChanges.setMinWidth(500);
		viewChanges.setMinHeight(400);

		Label label = new Label();
		label.setText("Insert data here to see the effects of the change");


		//location
		ChoiceBox<String> location = new ChoiceBox<>();

		//getItems returns the observable list object which you can add items to
		Label locationLabel = new Label("Location:");
		location.getItems().add("City");
		location.getItems().add("Large town");
		location.getItems().add("Small town");
		location.getItems().add("Village");
		location.getItems().add("Country side");

		//default value
		location.setValue("City");
		
		Button viewChangesButton = new Button("view the changes");
		viewChangesButton.setOnAction(e -> {
			// CALL THE METHOD AND PRINT IT 
			
			
		});
		
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, locationLabel, location, viewChangesButton );
		layout.setAlignment(Pos.CENTER);
		Scene scene = new Scene(layout);
		scene.getStylesheets().add(GUI.class.getResource("styles.css").toExternalForm());
		fontSize.bind(viewChanges.widthProperty().add(viewChanges.heightProperty()).divide(80));
		layout.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString()));
		viewChanges.setScene(scene);
		viewChanges.showAndWait();
		

	}
}
