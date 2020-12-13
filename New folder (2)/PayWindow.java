import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
/**
 * 
 * class to pay for a property
 *
 */
public class PayWindow {

	static ListView<String> listView;	
	private static DoubleProperty fontSize = new SimpleDoubleProperty(4);

	/**
	 * displays the scene
	 */
	public static void display() {
		Stage Pay= new Stage();

		Pay.initModality(Modality.APPLICATION_MODAL);
		Pay.setTitle("Pay for properties");
		Pay.setMinWidth(600);

		Label label = new Label();
		label.setText("Select the houses you wish to pay for:\n");
		label.setWrapText(true);

		
		/*
		 * returns the properties owned as a list so that the user can easily scroll through and view all 
		 * also adds a letter for each property so they can pay for it easily by inputting the letter
		 * 
		 */
		listView = new ListView<>();
		Owner owner = new Owner(GUI.getOwnerNameText());
		char j = 'A';
		for(int i = 0; i < owner.viewProperties().size(); i++){
			listView.getItems().add(j + ":\n" + owner.viewProperties().get(i).toString());
			j++;
		}



		Label propertyToPayForLabel = new Label("Type in the letter of the property to pay for it");
		TextField propertyToPayFor = new TextField();
		propertyToPayFor.setMaxWidth(120);
		propertyToPayFor.setPromptText("A");


		Label amountToPayLabel = new Label("How much would you like to pay for this :");
		TextField amountToPay = new TextField();
		amountToPay.setMaxWidth(120);
		amountToPay.setPromptText("100");




		Button closeButton = new Button("Pay for selected properties");
		closeButton.setOnAction(e -> { 
			if (getCharTrue(propertyToPayFor) == true && (isValue(amountToPay) == true )) {
				boolean result = ConfirmBox.display("Are you sure you want to pay for this properties");

				if(result == true) {
					char h = getChar(propertyToPayFor);
					Property p = getChoice(owner.viewProperties(), h); 
					try {
						owner.payPropertyTax(p, amountToPay(amountToPay));
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
					Pay.close();
				}
			}
		});

		VBox layout = new VBox(10);

		layout.setAlignment(Pos.CENTER);
		layout.setPadding(new Insets(10, 10, 10, 10));
		layout.getChildren().addAll(label, listView, amountToPayLabel, amountToPay, propertyToPayForLabel, 
				propertyToPayFor, closeButton);



		Scene scene = new Scene(layout);
		scene.getStylesheets().add(GUI.class.getResource("styles.css").toExternalForm());
		fontSize.bind(Pay.widthProperty().add(Pay.heightProperty()).divide(80));
		layout.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString()));
		Pay.setScene(scene);
		Pay.showAndWait();
	}

	/**
	 * 
	 * @param input takes the amount you want to pay and checks if it was filled and if it can be converted to a double
	 * @return true if it can be converted to a double and false if it cant
	 */
	private static boolean isValue(TextField input) {
		try {
			double value = Double.parseDouble(input.getText());
			return true;
		} catch(NumberFormatException e) {
			ErrorWindow.display("Please input an amount to pay");
			return false;
		}

	}

/**
 * 
 * @param input takes the amount you want to pay and converts it to a double
 * @return the input as a double
 */
	private static double amountToPay(TextField input) {
		double value = Double.parseDouble(input.getText());
		return value;
	}

/**
 * 
 * @param input checks if the letter inputted is a singular character
 * @return	if it is returns true otherwise returns false
 */
	private static boolean getCharTrue(TextField input) {
		try {

			String in = input.getText();
			if(in.trim().isEmpty()) {
				ErrorWindow.display("Please input a single character");
				return false;
			}
			in.toUpperCase();
			if (Character.isLetter(in.charAt(0)) && in.length() == 1) {
				return true;
			}
		}catch(Exception e ) {
			ErrorWindow.display("Please input a single character");
			return false;
		}
		ErrorWindow.display("Please input a single character");
		return false;
	}

	/**
	 * 
	 * @param input gets the character inputted 
	 * @return the character inputted as a char
	 */
	private static char getChar(TextField input) {
		String in = input.getText();
		String out = in.toUpperCase();
		return out.charAt(0);
	}




/**
 * 
 * @param choices 
 * @param h	gets the character inputted 
 * @return	the property selected 
 */
	private static Property getChoice(ArrayList<Property> choices, char h){ //offer choice
		boolean truth = true;
			while (truth){
				int n = h - 'A';
				if (0 <= n && n < choices.size()){
					System.out.println(choices.get(n));
					return choices.get(n);
				} else {
					truth =false;
					ErrorWindow.display("No such property exists at " + h);
				}
			}
		return null;
	}






}