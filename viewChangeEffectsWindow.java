package guiClasses;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
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
		
		
		Label cityLabel = new Label();
		cityLabel.setText("Input the new tax rate for City");
		TextField cityInput = new TextField();
		cityInput.setPromptText("");
		
		Label lTownLabel = new Label();
		lTownLabel.setText("Input the new tax rate for Large town");
		TextField lTownInput = new TextField();
		lTownInput.setPromptText("Large town");
		
		Label sTownLabel = new Label();
		sTownLabel.setText("Input the new tax rate for Small town");
		TextField sTownInput = new TextField();
		sTownInput.setPromptText("Small town");
		
		Label villageLabel = new Label();
		villageLabel.setText("Input the new tax rate for Village");
		TextField villageInput = new TextField();
		villageInput.setPromptText("Village");
		
		Label countrySideLabel = new Label();
		countrySideLabel.setText("Input the new tax rate for Country side");
		TextField countrySideInput = new TextField();
		countrySideInput.setPromptText("Country Side");
		
		

		
		

		
		
		Button viewChangesButton = new Button("view the changes");
		viewChangesButton.setOnAction(e -> {
			// CALL THE METHOD AND PRINT IT 
			
			
		});
		
		Label blank  = new Label("");


		GridPane layout = new GridPane();

		layout.setPadding(new Insets(20, 20, 20, 20));
		//auto change font size
		
		fontSize.bind(viewChanges.widthProperty().add(viewChanges.heightProperty()).divide(80));
		layout.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString()));

		layout.setVgap(8);
		layout.setHgap(10);

		GridPane.setConstraints(label,						0, 1 ); 
		GridPane.setConstraints(blank,						0, 2);
		GridPane.setConstraints(cityLabel,					0, 3); 
		GridPane.setConstraints(lTownLabel ,				0, 4 ); 
		GridPane.setConstraints(sTownLabel,  				0, 5 );
		GridPane.setConstraints(villageLabel,  				0, 6); 
		GridPane.setConstraints(countrySideLabel , 			0, 7 );
		
	
		GridPane.setConstraints(cityInput,					1, 3); 
		GridPane.setConstraints(lTownInput,					1, 4 ); 
		GridPane.setConstraints(sTownInput,  				1, 5 );
		GridPane.setConstraints(villageInput,  				1, 6); 
		GridPane.setConstraints(countrySideInput, 			1, 7 );
		GridPane.setConstraints(viewChangesButton, 			1, 8 );
		
		
		layout.getChildren().addAll(label, blank, cityLabel, lTownLabel, sTownLabel, villageLabel, countrySideLabel,
				cityInput, lTownInput, sTownInput, villageInput, countrySideInput, viewChangesButton);

		Scene scene = new Scene(layout);
		scene.getStylesheets().add(GUI.class.getResource("styles.css").toExternalForm());
	
		viewChanges.setScene(scene);
		viewChanges.showAndWait();
		

	}
}
