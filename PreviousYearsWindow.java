package guiClasses;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class PreviousYearsWindow {
	private static DoubleProperty fontSize = new SimpleDoubleProperty(4);

	public static void display(String message) {
		Stage PrevYear = new Stage();
		//USER
		PrevYear.initModality(Modality.APPLICATION_MODAL);
		PrevYear.setTitle("Previous years");
		PrevYear.setMinWidth(400);

		Label label = new Label();
		label.setText("these are the properties from previous years:\n" + message);


		Label yearLabel = new Label("What year would you like to view");
		TextField year = new TextField();
		year.setMaxWidth(120);
		year.setPromptText("2016");





		Button closeButton = new Button("See properties");
		closeButton.setOnAction(e -> {
			PreviousYearViewWindow.display(Integer.toString(getYear(year)), getYear(year));
		});

		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, yearLabel, year, closeButton);
		layout.setAlignment(Pos.CENTER);

		Scene scene = new Scene(layout);
		PrevYear.setScene(scene);
		fontSize.bind(PrevYear.widthProperty().add(PrevYear.heightProperty()).divide(40));
		layout.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString()));
		scene.getStylesheets().add(GUI.class.getResource("styles.css").toExternalForm());
		PrevYear.showAndWait();


	}

	private static int getYear(TextField year) {
		int yearOut = Integer.parseInt(year.getText());


		return yearOut;
	}



	/*		for(int i = 0; i < owner.viewProperties().size(); i++){
				props += "Property " + (i+1) +"\n" + owner.viewProperties().get(i).toString() + "\n";

		}*/

}