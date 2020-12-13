import java.io.FileNotFoundException;
import java.time.Year;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;



/**
 * Class for a user to add a new property
*/
public class AddPropertiesWindow {
	
	/*
	 * Creates a DoubleProperty variable called fontsize to get bigger or smaller as the window gets bigger
	 * 
	 */
	private static DoubleProperty fontSize = new SimpleDoubleProperty(4);

	/**
	 * displays the window
	 */
	public static void display() {
		Stage addProps = new Stage();

		addProps.initModality(Modality.APPLICATION_MODAL);
		addProps.setTitle("Add Properties");
		addProps.setMinWidth(500);
		addProps.setMinHeight(400);

		Label label = new Label();
		label.setText("Insert data here to add a property:");
	
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
		
		Label marketValueLabel = new Label("Estimated Market Value:");
		TextField marketValue = new TextField();
		marketValue.setPromptText("Market Value");
		
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
		
		
		//Main residence or not
		
		ChoiceBox<String> mainRes = new ChoiceBox<>();
		Label mainResLabel = new Label("Is this your Main Residence?:");
		mainRes.getItems().add("Yes");
		mainRes.getItems().add("No");

		Button confirmButton = new Button("Confirm Choices");
		confirmButton.setOnAction(e -> {

			if (isText(addressLine1.getText(), "address line 1 is empty") == true &&
					isText(addressLine2.getText(), "address line 2 is empty") == true && 
					isValue(marketValue, "Not a valid value") == true) {
				boolean result = ConfirmBox.display("Are you sure you want to add this property?");
				System.out.println(result);
				if(result == true) {
					//save all this information and now close both windows
					//ALL THE INFORMATION AND METHODS MUST BE CALLED HERE TO ACTUALLY ADD THE SAVED INFO TO CLASSES 
					
					//String Owner = owner.getText();
					String address = (addressLine1.getText() + " " +  addressLine2.getText() + " " + addressLine3.getText());
					//double marketValue1 = Double.parseDouble(marketValue);
					double value = Double.parseDouble(marketValue.getText());
					int year = Year.now().getValue();
					try {
						readOrWriteFile.writeProperty(GUI.getOwnerNameText(), address, eirCode.getText(), value, getChoice(location), getChoice2(mainRes), year);
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
					
					System.out.println(GUI.getOwnerNameText() + " " + address + " " + eirCode.getText() + " " +  value + " " +  getChoice(location) +" " +   getChoice2(mainRes) + " " +  year);
					
					getChoice(location);
					getChoice2(mainRes);
					addProps.close();
				}
			}
		}); 

		GridPane layout = new GridPane();		
		layout.setPadding(new Insets(20, 20, 20, 20));
		
		/*
		 * Read the size of the window and then set the variable fontsize 
		 * to change as the window gets bigger or smaller
		 */
		
		fontSize.bind(addProps.widthProperty().add(addProps.heightProperty()).divide(80));
		layout.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString()));
				
		layout.setVgap(8);
		layout.setHgap(10);
		
		GridPane.setConstraints(addressLine1 , 1, 2);
		GridPane.setConstraints(addressLine2, 1, 3);
		GridPane.setConstraints(addressLine3, 1, 4);
		GridPane.setConstraints(eirCode, 1, 5);
		GridPane.setConstraints(marketValue, 1, 6);
		GridPane.setConstraints(location, 1, 7);
		GridPane.setConstraints(mainRes, 1, 8);
		GridPane.setConstraints(confirmButton, 1, 9);
		GridPane.setConstraints(addressLine1Label, 0, 2);
		GridPane.setConstraints(addressLine2Label, 0, 3);
		GridPane.setConstraints(addressLine3Label, 0, 4);
		GridPane.setConstraints(eirCodeLabel, 0, 5);
		GridPane.setConstraints(marketValueLabel, 0, 6);
		GridPane.setConstraints(locationLabel, 0, 7);
		GridPane.setConstraints(mainResLabel, 0, 8);
		
		layout.setAlignment(Pos.CENTER);
		layout.getChildren().addAll(label,  addressLine1, addressLine2, addressLine3, eirCode, marketValue, location, mainRes, confirmButton, 
				 addressLine1Label, addressLine2Label,  addressLine3Label, eirCodeLabel,	marketValueLabel, locationLabel, mainResLabel);
		
		BorderPane borderPane = new BorderPane();
		//borderPane.setAlignment(layout, Pos.CENTER);
		
		borderPane.setCenter(layout);
	
		Scene scene = new Scene(borderPane, 600, 400);		
		scene.getStylesheets().add(GUI.class.getResource("styles.css").toExternalForm());
		addProps.setScene(scene);
		addProps.showAndWait();	
	}
	
	/**
	 * 
	 * @param text checks if the textfield is empty in addressLine1
	 * @param message displays the error window stating the line that is the problem
	 * @return true if there is text and false if it is empty
	 */
	private static boolean isText(String text, String message) {
		if (text.isEmpty() == false) {
			return true;
		} else {
			ErrorWindow.display(message);
			return false;
		}
	}
	/**
	 * 
	 * @param input checks if the textfield is empty in marketValue
	 * @param message displays the error window stating the line that is the problem
	 * @return true if it contains a double and false otherwise
	 */
	private static boolean isValue(TextField input, String message) {
		try {
			double value = Double.parseDouble(input.getText());
			return true;		
		} catch(NumberFormatException e) {
			ErrorWindow.display(message);
			return false;
		}
	}
	/**
	 * 
	 * @param location takes in the location selected in the choicebox
	 * @return the location as a string
	 */
	private static String getChoice(ChoiceBox<String> location) {
		String choice = location.getValue();
		return choice;
	}
	
	/**
	 * 
	 * @param mainRes takes in your choice in the choicebox mainRes
	 * @return the choice selected as a string
	 */
	private static boolean getChoice2(ChoiceBox<String> mainRes) {
		String choice = mainRes.getValue();
		if (choice == "Yes") {
			return true;
		} else {
			return false;
		}
	}
	

}