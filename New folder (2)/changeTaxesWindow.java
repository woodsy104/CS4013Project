import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * admin side to change the taxes of an area or price bracket
 *
 */
public class changeTaxesWindow {
    private static DoubleProperty fontSize = new SimpleDoubleProperty(4);

    /**
     * display the change the taxes window
     */
    public static void display() {
        Stage changeTaxes = new Stage();

        changeTaxes.initModality(Modality.APPLICATION_MODAL);
        changeTaxes.setTitle("Admin change rate of tax");
        changeTaxes.setMinWidth(500);
        changeTaxes.setMinHeight(400);
        
        
        /*
         * 
         * create a choice box allowing the user to select a tax bracket they would like to change
         */
        ChoiceBox<String> selectPriceRange= new ChoiceBox<>();
        Label selectPriceRangeLabel = new Label("What tax bracket would you like to change the tax rate for");
        selectPriceRange.getItems().addAll("0-150,000", "150,000-400,000", "650,000+");

        
        /*
         * create a choice box allowing the user to select the area they would like to change
         */
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

        
        /*
         * 
         * first checks if there is values inputted in the fields
         * if there is it displays the confirm box 
         * takes the values from the text fields and uses that to change the 
         * array in property tax 
         * 
         */
        Button confirmButton = new Button("Confirm choices");
        confirmButton.setOnAction(e -> {
            if (isValue(changeTaxBracket) == true || isValue2(changeTaxAreaFixed)) {
                boolean result = ConfirmBox.display("Are you sure you want to change these taxes");
                if(result == true) {
                	//checks if there is a value inputed to change the tax of a bracket
                    if(isValue(changeTaxBracket) == true) {
                        double newTaxRatePercent = Double.parseDouble(changeTaxBracket.getText());
                        if(getChoice2(selectPriceRange) == "0-150,000" ){
                            PropertyTax.setRate(0, newTaxRatePercent);
                        } else if(getChoice2(selectPriceRange) == "150,000-400,000" ){
                            PropertyTax.setRate(1, newTaxRatePercent);
                        } else if(getChoice2(selectPriceRange) == "650,000+" ){
                            PropertyTax.setRate(2, newTaxRatePercent);
                        }
                    }
                    //checks if there is a value inputed to change the tax of an area
                    if(isValue2(changeTaxAreaFixed) == true) {
                        int newFixedTaxRate = (int) Double.parseDouble(changeTaxAreaFixed.getText());
                        if (getChoice(selectArea) == "City") {
                            PropertyTax.setLocationBase(0, newFixedTaxRate);
                        }  else if (getChoice(selectArea) == "Large town") {
                            PropertyTax.setLocationBase(1, newFixedTaxRate);
                        } else if (getChoice(selectArea) == "Small town") {
                            PropertyTax.setLocationBase(2, newFixedTaxRate);
                        } else if (getChoice(selectArea) == "Village") {
                            PropertyTax.setLocationBase(3, newFixedTaxRate);
                        } else if (getChoice(selectArea) == "Country side") {
                            PropertyTax.setLocationBase(4, newFixedTaxRate);
                                                    }
                    }
                }
                changeTaxes.close();
            }
        });

        Label blank  = new Label("");
        GridPane layout = new GridPane();
        layout.setPadding(new Insets(20, 20, 20, 20));
  
        
        /*
		 * Read the size of the window and then set the variable fontsize 
		 * to change as the window gets bigger or smaller
		 */
		
        fontSize.bind(changeTaxes.widthProperty().add(changeTaxes.heightProperty()).divide(80));
        layout.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString()));
        
        
        layout.setVgap(8);
        layout.setHgap(10);

        
        /*
         * create the layout of where everything is
         */
        GridPane.setConstraints(selectPriceRange, 1, 1 ); 
        GridPane.setConstraints(selectArea , 1, 4 ); 

        GridPane.setConstraints(changeTaxBracket, 1, 2 ); 
        GridPane.setConstraints(changeTaxAreaFixed, 1,  5); 

        GridPane.setConstraints(selectPriceRangeLabel, 0, 1); 
        GridPane.setConstraints(selectAreaLabel , 0, 4 ); 
        
        GridPane.setConstraints(changeTaxBracketLabel, 0, 2 );
        GridPane.setConstraints(changeTaxAreaFixedLabel, 0, 5); 
        GridPane.setConstraints(blank, 0, 3);

        GridPane.setConstraints(confirmButton, 1, 6 );
        layout.getChildren().addAll(selectPriceRange, blank, selectPriceRangeLabel, selectArea, selectAreaLabel, changeTaxBracketLabel, changeTaxBracket, changeTaxAreaFixedLabel,
                changeTaxAreaFixed, confirmButton);

        Scene scene = new Scene(layout);
        changeTaxes.setScene(scene);
  
        /*
         * set the styles and display the scene
         */
        scene.getStylesheets().add(GUI.class.getResource("styles.css").toExternalForm());
        changeTaxes.showAndWait();
    }

    /**
     * 
     * @param input takes in the textfield changeTaxBracket to change a tax to a percent
     * @return if the input is able to be converted to a double it will return true, otherwise it will return false
     */
    private static boolean isValue(TextField input) {
        try {
            double value = Double.parseDouble(input.getText());
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }
    
    /** 
     * @param input takes in the textfield changeTaxAreaFixed to change the rate of tax on the location to a different reate
     * @return if the input is able to be converted to a int it will return true, otherwise it will return false
     */
    private static boolean isValue2(TextField input) {
        try {
            int value = Integer.parseInt(input.getText());
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }

    
    /**
     * 
     * @param location takes in the selected location that you have
     * @return the selected location as a string
     */
    private static String getChoice(ChoiceBox<String> location) {
        try {
            String choice = location.getValue();
           // System.out.println("choice area " +choice);
            return choice;
        } catch (Exception e2) {
           // System.out.println("failed successfully");
        }
        return null;
    }

    /**
     * 
     * @param price takes in the selected price bracket that you selected 
     * @return the selected price bracket as a string
     */
    private static String getChoice2(ChoiceBox<String> price) {
        try {
        String choice = price.getValue();
            return choice;
        } catch (Exception e2){
            //System.out.println("choice price range fail success" );
            
        }
        return null;
    }


}