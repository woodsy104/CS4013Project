package guiClasses;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class PropertyTaxOfAreaWindow {
	private static DoubleProperty fontSize = new SimpleDoubleProperty(4);


	static String locationString;

	public static void display() {

		//String locationString;

		Stage propTaxOfArea = new Stage();

		propTaxOfArea.initModality(Modality.APPLICATION_MODAL);
		propTaxOfArea.setTitle("Tax by area");
		propTaxOfArea.setMinWidth(500);
		propTaxOfArea.setMinHeight(400);

		//Label label = new Label();
		//label.setText("The property tax of " + newValue);


		//location
		ChoiceBox<String> location = new ChoiceBox<>();

		//getItems returns the observable list object which you can add items to
		Label locationLabel = new Label("Location:");
		location.getItems().add("City");
		location.getItems().add("Large town");
		location.getItems().add("Small town");
		location.getItems().add("Village");
		location.getItems().add("Country side");

		//String locationString;
		//Listen for selection change
		location.getSelectionModel().selectedItemProperty().addListener( (v, oldValue, newValue) -> locationString = newValue );

		Label label = new Label();
		label.setText("The property tax");
		label.setWrapText(true);
		
		//default value
		location.setValue("City");

		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, locationLabel, location );
		layout.setAlignment(Pos.CENTER);
		Scene scene = new Scene(layout);
		scene.getStylesheets().add(GUI.class.getResource("styles.css").toExternalForm());
		fontSize.bind(propTaxOfArea.widthProperty().add(propTaxOfArea.heightProperty()).divide(60));
		layout.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString()));
		propTaxOfArea.setScene(scene);
		propTaxOfArea.showAndWait();
	}
}
