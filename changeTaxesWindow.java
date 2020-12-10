package guiClasses;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class changeTaxesWindow {
	
	

	private static DoubleProperty fontSize = new SimpleDoubleProperty(4);
	
	public static void display(String message) {
		Stage changeTaxes = new Stage();

		changeTaxes.initModality(Modality.APPLICATION_MODAL);
		changeTaxes.setTitle("Add Properties");
		changeTaxes.setMinWidth(500);
		changeTaxes.setMinHeight(400);
		
		
		ChoiceBox<String> selectArea = new ChoiceBox<>();
		Label selectAreaLabel = new Label("Where would you like to change the rate of tax for");
		selectArea.getItems().addAll("City", "Large town", "Small town", "Village", "Country side");
		
		
		
	
		Label changeTax  = new Label("Set the rate of tax");
		TextField changeTaxInput = new TextField();
		changeTaxInput.setPromptText("Change the rate of tax");
		
		
		Button confirmButton = new Button("Confirm choices");
		confirmButton.setOnAction(e -> {

			if (isValue(changeTaxInput, "Not a valid value") == true) {
				
				
				//add all the information to this

				
				boolean result = ConfirmBox.display("Confirmation", "Are you sure you want to add this properties");
				System.out.println(result);
				if(result == true) {
					//save all this information and now close both windows
					System.out.println(changeTax.getText());
					
					//System.out.println(marketValue.getText());
					
					changeTaxes.close();
				}
			}
		});
		
		
		
		
		
		
		VBox layout = new VBox(10);
		layout.getChildren().addAll(changeTax, changeTaxInput, selectArea, confirmButton);
		layout.setAlignment(Pos.CENTER);
		layout.setPadding(new Insets(20, 20, 20, 20));
	
		
		Scene scene = new Scene(layout);
		changeTaxes.setScene(scene);
		fontSize.bind(changeTaxes.widthProperty().add(changeTaxes.heightProperty()).divide(80));
		layout.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString()));
		scene.getStylesheets().add(GUI.class.getResource("styles.css").toExternalForm());
		changeTaxes.showAndWait();
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
	
}
