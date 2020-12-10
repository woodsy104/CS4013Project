package guiClasses;


import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
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
		listView.getItems().addAll("X91 WN0X\n5 Whitethorn Avenue\nGrantstown Village\n€500000", "prop2", "prop3", "prop4");

		//We use this to add in all the houses from the property list 
		String array[] = {"hello", "my", "name", "is", "sean"};
		for(int i = 0; i < array.length; i ++) {
			listView.getItems().add(array[i]);
		}
		listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		
		Label amountToPayLabel = new Label("How much would you liked to pay for this :");
		TextField amountToPay = new TextField();
		amountToPay.setMaxWidth(80);
		amountToPay.setPromptText("Amount to Pay: Max is  " /*+ (amountTo pay)*/);
		// I NEED TO ADD THIS BIT HERE SO THAT THE SYSTEM KNOWS WHATS ITS WORTH,
		// AS SOON AS YOU CLICK ON THE PROPERTY IT SHOULD SAY THE MAX AMOUNT TO PAY
		
		
		
		Button closeButton = new Button("Pay for selected properties");
		closeButton.setOnAction(e -> { 
			
			boolean result = ConfirmBox.display("Confirmation", "Are you sure you want to pay for this properties");
			System.out.println(result);
			if(result == true) {
				//Pay for this house
				//THIS IS ONE OF THE AREAS THAT WE NEED TO ACTUALLY ADD THE RESULTS THAT THE USER INPUTS INTO THE CSVs OR THE METHODS
				
				
				handleOptions();
				Pay.close();
			}
		});

		VBox layout = new VBox(10);
		
		layout.setAlignment(Pos.CENTER);
		layout.setPadding(new Insets(10, 10, 10, 10));
		//amountToPay.setPadding(new Insets(10, 10, 10, 10));
		layout.getChildren().addAll(label, listView, amountToPayLabel, amountToPay, closeButton);



		Scene scene = new Scene(layout);
		scene.getStylesheets().add(GUI.class.getResource("styles.css").toExternalForm());
		fontSize.bind(Pay.widthProperty().add(Pay.heightProperty()).divide(80));
		layout.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString()));
		Pay.setScene(scene);
		Pay.showAndWait();
	}


	//handle check box options
	private static void handleOptions() {
		String message = "Selected to pay\n";
		ObservableList<String> words;
		words = listView.getSelectionModel().getSelectedItems();

		for(String w: words) {
			message += w + "\n";

		}

		System.out.print(message);


	}
	
	
	
	private static boolean isValue(TextField input) {
		try {
			double value = Double.parseDouble(input.getText());
			System.out.println("Expected value is: " + value);
			return true;
			
		} catch(NumberFormatException e) {
			System.out.println("ERROR: ");
			return false;
		}
	}
	





}