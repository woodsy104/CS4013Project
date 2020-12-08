package guiClasses;


import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PayWindow {

	static ListView<String> listView;		



	public static void display(String message) {
		Stage Pay= new Stage();

		Pay.initModality(Modality.APPLICATION_MODAL);
		Pay.setTitle("Pay for properties");
		Pay.setMinWidth(400);

		Label label = new Label();
		label.setText("Select the houses you wish to pay for:\n" + message);



		listView = new ListView<>();
		listView.getItems().addAll("X91 WN0X\n5 Whitethorn Avenue\nGrantstown Village\n€500000", "prop2", "prop3", "prop4");

		//We use this to add in all the houses from the property list 
		String array[] = {"hello", "my", "name", "is", "sean"};
		for(int i = 0; i < array.length; i ++) {
			listView.getItems().add(array[i]);
		}
		//listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		Button closeButton = new Button("Pay for selected properties");
		closeButton.setOnAction(e -> { 
			
			boolean result = ConfirmBox.display("Confirmation", "Are you sure you want to pay for this properties");
			System.out.println(result);
			if(result == true) {
				//Pay for this house
				handleOptions();
				Pay.close();
			}
		});

		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, closeButton, listView);
		layout.setAlignment(Pos.CENTER);
		layout.setPadding(new Insets(10, 10, 10, 10));



		Scene scene = new Scene(layout);
		scene.getStylesheets().add(GUI.class.getResource("styles.css").toExternalForm());
		Pay.setScene(scene);
		Pay.showAndWait();
	}


	//handle checkbox options
	private static void handleOptions() {
		String message = "Selected to pay\n";
		ObservableList<String> words;
		words = listView.getSelectionModel().getSelectedItems();

		for(String w: words) {
			message += w + "\n";

		}

		System.out.print(message);


	}





}