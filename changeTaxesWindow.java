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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class changeTaxesWindow {



	private static DoubleProperty fontSize = new SimpleDoubleProperty(4);

	public static void display(String message) {
		Stage changeTaxes = new Stage();

		changeTaxes.initModality(Modality.APPLICATION_MODAL);
		changeTaxes.setTitle("Admin change rate of tax");
		changeTaxes.setMinWidth(500);
		changeTaxes.setMinHeight(400);


		ChoiceBox<String> selectPriceRange= new ChoiceBox<>();
		Label selectPriceRangeLabel = new Label("What tax bracket would you like to change the tax rate for");
		selectPriceRange.getItems().addAll("0-150,000", "150,000-400,000", "650,000+");


		ChoiceBox<String> selectArea = new ChoiceBox<>();
		Label selectAreaLabel = new Label("What area would you like to change the rate of tax for");
		selectArea.getItems().addAll("City", "Large town", "Small town", "Village", "Country side");



		//AREA 
		//FIXED
		Label changeTaxAreaFixedLabel  = new Label("Set a fixed tax on the area");
		TextField changeTaxAreaFixed = new TextField();
		changeTaxAreaFixed.setPromptText("(Enter as whole number (400))");



		//PRICE
		//PERCENT
		Label changeTaxBracketLabel  = new Label("Set a percentage increase on a price bracket");
		TextField changeTaxBracket = new TextField();
		changeTaxBracket.setPromptText("Enter as a percent (0.04)");


		Button confirmButton = new Button("Confirm choices");
		confirmButton.setOnAction(e -> {

			if (isValue(changeTaxBracket) == true || isValue2(changeTaxAreaFixed)) {

				boolean result = ConfirmBox.display("Confirmation", "Are you sure you want to change these taxes");
				//System.out.println(result);


				if(result == true) {


					if(isValue(changeTaxBracket) == true) {

						double newTaxRatePercent = Double.parseDouble(changeTaxBracket.getText());

						//PRICE RANGE ISNT BEING USED USE AREA
						if(getChoice2(selectPriceRange) == "0-150,000" ){
							PropertyTax.setRate(0, newTaxRatePercent);
						}else if(getChoice2(selectPriceRange) == "150,000-400,000" ){
							PropertyTax.setRate(1, newTaxRatePercent);
						} else if(getChoice2(selectPriceRange) == "650,000+" ){
							PropertyTax.setRate(2, newTaxRatePercent);
						}
					}




					if(isValue2(changeTaxAreaFixed) == true) {
						int newFixedTaxRate = (int) Double.parseDouble(changeTaxAreaFixed.getText());
						//Price range is selected 

						if (getChoice(selectArea) == "City") {
							PropertyTax.setLocationBase(0, newFixedTaxRate);
						} 

						else if (getChoice(selectArea) == "Large town") {
							PropertyTax.setLocationBase(1, newFixedTaxRate);
						} 

						else if (getChoice(selectArea) == "Small town") {
							PropertyTax.setLocationBase(2, newFixedTaxRate);
						} 

						else if (getChoice(selectArea) == "Village") {
							PropertyTax.setLocationBase(3, newFixedTaxRate);
						} 

						else if (getChoice(selectArea) == "Country side") {
							PropertyTax.setLocationBase(4, newFixedTaxRate);
							//System.out.println(newFixedTaxRate);
						}
					}
				}


				changeTaxes.close();
			}
		});



		Label blank  = new Label("");


		GridPane layout = new GridPane();

		layout.setPadding(new Insets(20, 20, 20, 20));
		//auto change font size
		fontSize.bind(changeTaxes.widthProperty().add(changeTaxes.heightProperty()).divide(80));
		layout.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString()));

		layout.setVgap(8);
		layout.setHgap(10);

		GridPane.setConstraints(selectPriceRange,			1, 1 ); 
		GridPane.setConstraints(selectArea , 				1, 4 ); 

		GridPane.setConstraints(changeTaxBracket,      		1, 2 ); 
		GridPane.setConstraints(changeTaxAreaFixed, 		1,  5); 

		GridPane.setConstraints(selectPriceRangeLabel,		0, 1); 
		GridPane.setConstraints(selectAreaLabel ,			0, 4 ); 
		
		GridPane.setConstraints(changeTaxBracketLabel,  	0, 2 );
		GridPane.setConstraints(changeTaxAreaFixedLabel,  	0, 5); 
		GridPane.setConstraints(blank,						0, 3);

		GridPane.setConstraints(confirmButton, 				1, 6 );
		layout.getChildren().addAll(selectPriceRange, blank, selectPriceRangeLabel, selectArea, selectAreaLabel, changeTaxBracketLabel, changeTaxBracket, changeTaxAreaFixedLabel,
				changeTaxAreaFixed, confirmButton);


		Scene scene = new Scene(layout);
		changeTaxes.setScene(scene);
		//fontSize.bind(changeTaxes.widthProperty().add(changeTaxes.heightProperty()).divide(80));
		//layout.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString()));
		//	scene.getStylesheets().add(GUI.class.getResource("styles.css").toExternalForm());
		changeTaxes.showAndWait();
	}






	private static boolean isValue(TextField input) {
		try {
			double value = Double.parseDouble(input.getText());
			return true;

		} catch(NumberFormatException e) {
			return false;
		}
	}


	private static boolean isValue2(TextField input) {
		try {
			int value = Integer.parseInt(input.getText());
			return true;

		} catch(NumberFormatException e) {
			return false;
		}
	}






	private static String getChoice(ChoiceBox<String> location) {
		try {
			String choice = location.getValue();
			System.out.println("choice area " +choice);
			return choice;
		} catch (Exception e2) {
			System.out.println("failed successfully");
		}
		return null;
	}

	private static String getChoice2(ChoiceBox<String> price) {
		try {
		String choice = price.getValue();
			return choice;
		} catch (Exception e2){
			System.out.println("choice price range fail success" );
			
		}
		return null;
	}


}
