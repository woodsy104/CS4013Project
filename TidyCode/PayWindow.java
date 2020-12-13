import javafx.collections.ObservableList;
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

public class PayWindow {

	static ListView<String> listView;	
	private static DoubleProperty fontSize = new SimpleDoubleProperty(4);




	public static void display(String message) {
		Stage Pay= new Stage();

		Pay.initModality(Modality.APPLICATION_MODAL);
		Pay.setTitle("Pay for properties");
		Pay.setMinWidth(600);

		Label label = new Label();
		label.setText("Select the houses you wish to pay for:\n");
		label.setWrapText(true);

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
				boolean result = ConfirmBox.display("Confirmation", "Are you sure you want to pay for this properties");

				if(result == true) {
					char h = getChar(propertyToPayFor);
					Property p = (Property) getChoice(owner.viewProperties(), h); 
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


	private static boolean isValue(TextField input) {
		try {
			double value = Double.parseDouble(input.getText());
			return true;
		} catch(NumberFormatException e) {
			ErrorWindow.display("Please input an amount to pay");
			return false;
		}

	}


	private static double amountToPay(TextField input) {
		double value = Double.parseDouble(input.getText());
		return value;
	}


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

	private static char getChar(TextField input) {
		String in = input.getText();
		String out = in.toUpperCase();
		return out.charAt(0);
	}





	private static Property getChoice(ArrayList<Property> choices, char h){ //offer choice
		boolean literallyAnything = true;
		//try ???????? catch ????????
			while (literallyAnything){
				int n = h - 'A';
				if (0 <= n && n < choices.size()){
					System.out.println(choices.get(n));
					return choices.get(n);
				} else {
					literallyAnything =false;
					ErrorWindow.display("No such property exists at " + h);
				}
			}
		return null;
	}






}