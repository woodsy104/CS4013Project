import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class SearchByNameWindow {
	private static DoubleProperty fontSize = new SimpleDoubleProperty(4);

	public static void display(String message) {
		Stage searchByName = new Stage();
		
		searchByName.initModality(Modality.APPLICATION_MODAL);
		searchByName.setTitle("View properties of a person's name");
		searchByName.setMinWidth(400);
		searchByName.setMinHeight(300);

		Label label = new Label();
		label.setWrapText(true);
		label.setText(message);


		Label nameLabel = new Label("What is the name of the person you'd like to view's history");
		
		TextField name = new TextField();
		name.setMaxWidth(120);
		name.setPromptText("Sean Ryan");

		Button closeButton = new Button("See properties");
		closeButton.setOnAction(e -> {
			SearchByNameViewWindow.display((getName(name)), getName(name));
		});

		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, nameLabel, name, closeButton);
		layout.setAlignment(Pos.CENTER);

		Scene scene = new Scene(layout);
		
		/*
		 * Read the size of the window and then set the variable fontsize 
		 * to change as the window gets bigger or smaller
		 */
		
		fontSize.bind(searchByName.widthProperty().add(searchByName.heightProperty()).divide(50));
		layout.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString()));
		
		
		/*
		 * Gets the style of the font from the style sheet "styles.css"
		 * 
		 */
		scene.getStylesheets().add(GUI.class.getResource("styles.css").toExternalForm());
		
		/*
		 * Show the window 
		 */
		searchByName.setScene(scene);
		searchByName.showAndWait();


	}

	private static String getName(TextField name) {
		String nameOut = name.getText();

		return nameOut;
	}
}