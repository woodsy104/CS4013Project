package guiClasses;

import javafx.geometry.Insets;
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
		addProps.setMinWidth(500);
		addProps.setMinHeight(400);

		Label label = new Label();
		label.setText("Insert data here to add a property " +message);
		
		Label addressLine1Label = new Label("Address Line 1:");
		TextField addressLine1 = new TextField();
		addressLine1.setPromptText("Address Line 1");
		
		Label addressLine2Label = new Label("Address Line 2:");
		TextField addressLine2 = new TextField();
		addressLine2.setPromptText("Address Line 2");
		
		Label addressLine3Label = new Label("Address Line 3:");
		TextField addressLine3 = new TextField();
		addressLine3.setPromptText("Address Line 3 (Optional)");
		
		Label eirCodeLabel = new Label("Eircode:");
		TextField eirCode = new TextField();
		eirCode.setPromptText("Eircode (Optional)");
		
		Label marketValueLabel = new Label("Estimated market value:");
		TextField marketValue = new TextField();
		marketValue.setPromptText("Market value");
		
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
		
		//Listen for selection change
		//location.getSelectionModel().selectedItemProperty().addListener( (v, oldValue, newValue) -> System.out.println(newValue) );
		
		//Main residence or not
		
		ChoiceBox<String> mainRes = new ChoiceBox<>();
		Label mainResLabel = new Label("Is this your main residence");
		mainRes.getItems().add("Yes");
		mainRes.getItems().add("No");
		


		Button confirmButton = new Button("Confirm choices");
		confirmButton.setOnAction(e -> {

			if (isAddress(addressLine1.getText(), "address line 1 is empty") == true &&
					isAddress(addressLine2.getText(), "address line 2 is empty") == true && 
					isValue(marketValue, "Not a valid value") == true) {
				
				
				

				getChoice(location);
				boolean result = ConfirmBox.display("Confirmation", "Are you sure you want to add this properties");
				System.out.println(result);
				if(result == true) {
					//save all this information and now close both windows
					//ALL THE INFORMATION AND METHODS MUST BE CALLED HERE TO ACTUALLY ADD THE SAVED INFO TO CLASSES 
					
					
					
					
					
					System.out.println(addressLine1.getText());
					System.out.println(addressLine2.getText());
					System.out.println(addressLine3.getText());
					System.out.println(eirCode.getText());
					System.out.println(marketValue.getText());
					getChoice(location);
					addProps.close();
				}
			}
		}); 


		

		GridPane layout = new GridPane();
		layout.setPadding(new Insets(20, 20, 20, 20));
		layout.setVgap(8);
		layout.setHgap(10);
		
		GridPane.setConstraints(addressLine1 , 		1, 1 );
		GridPane.setConstraints(addressLine2,      	1, 2 );
		GridPane.setConstraints(addressLine3,       1, 3 );
		GridPane.setConstraints(eirCode,			1, 4 );
		GridPane.setConstraints(marketValue,	 	1, 5 );
		GridPane.setConstraints(location,	 		1, 6 );
		GridPane.setConstraints(mainRes,	 		1, 7 );
		GridPane.setConstraints(confirmButton, 		1, 8 );
		GridPane.setConstraints(addressLine1Label , 0, 1 );
		GridPane.setConstraints(addressLine2Label,  0, 2 );
		GridPane.setConstraints(addressLine3Label,  0, 3 );
		GridPane.setConstraints(eirCodeLabel,	  	0, 4 );
		GridPane.setConstraints(marketValueLabel,	0, 5 );
		GridPane.setConstraints(locationLabel,		0, 6 );
		GridPane.setConstraints(mainResLabel, 		0, 7 );
		
	
	
		layout.getChildren().addAll(label, addressLine1, addressLine2, addressLine3, eirCode, marketValue, location, mainRes, confirmButton, 
				addressLine1Label, addressLine2Label,  addressLine3Label, eirCodeLabel,	marketValueLabel, locationLabel, mainResLabel);

		Scene scene = new Scene(layout);		
		scene.getStylesheets().add(GUI.class.getResource("styles.css").toExternalForm());
		addProps.setScene(scene);
		addProps.showAndWait();
		
	}

	

	private static boolean isAddress(String addressLine, String message) {
		if (addressLine.isEmpty() == false) {
			System.out.println(addressLine);
			return true;
		} else {
			System.out.println("ERROR: " + message);
			return false;
		}
	}
	
	private static boolean isValue(TextField input, String message) {
		try {
			double value = Double.parseDouble(input.getText());
			System.out.println("Expected value is: " + value);
			return true;
			
		} catch(NumberFormatException e) {
			System.out.println("ERROR: " + message);
			return false;
		}
	}
	
	private static void getChoice(ChoiceBox<String> location) {
		String choice = location.getValue();
		System.out.println(choice);
	}
	

}
