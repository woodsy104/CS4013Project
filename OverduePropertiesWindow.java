package guiClasses;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class OverduePropertiesWindow {

	private static DoubleProperty fontSize = new SimpleDoubleProperty(4);
	public static void display(String message) {
		Stage OverdueProperties = new Stage();

		OverdueProperties.initModality(Modality.APPLICATION_MODAL);
		OverdueProperties.setTitle("Overdue Payments");
		OverdueProperties.setMinWidth(400);

		Label label = new Label();
		label.setText("These are the properties that are overdue:\n" + message);
		Button closeButton = new Button("Close the window");
		closeButton.setOnAction(e -> {

			OverdueProperties.close();

		});

		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, closeButton);
		layout.setAlignment(Pos.CENTER);

		Scene scene = new Scene(layout);
		scene.getStylesheets().add(GUI.class.getResource("styles.css").toExternalForm());
		fontSize.bind(OverdueProperties.widthProperty().add(OverdueProperties.heightProperty()).divide(80));
		layout.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString()));
		OverdueProperties.setScene(scene);
		OverdueProperties.showAndWait();
		
	}
}
