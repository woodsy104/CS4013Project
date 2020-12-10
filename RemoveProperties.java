package guiClasses;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RemoveProperties {

	public RemoveProperties() {
		Stage removeProps = new Stage();

		removeProps.initModality(Modality.APPLICATION_MODAL);
		removeProps.setTitle("Add Properties");
		removeProps.setMinWidth(500);
		removeProps.setMinHeight(400);

		Label label = new Label();
		label.setText("Select a property to remove it");

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



		fontSize.bind(removeProps.widthProperty().add(removeProps.heightProperty()).divide(40));
	}

}
