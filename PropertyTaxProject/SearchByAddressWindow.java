import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
/**
 * 
 * class to search by an address for the admin
 *
 */
public class SearchByAddressWindow {
	private static DoubleProperty fontSize = new SimpleDoubleProperty(4);

	/**
	 * 
	 * @param message takes the input when the method was called and uses that as a label 
	 */
	public static void display(String message) {
		Stage searchByAddress = new Stage();
		
		searchByAddress.initModality(Modality.APPLICATION_MODAL);
		searchByAddress.setTitle("Search by address");
		searchByAddress.setMinWidth(400);
		searchByAddress.setMinHeight(300);

		Label label = new Label();
		label.setWrapText(true);
		label.setText( message);


		Label addressLabel = new Label("What is the address of the person you'd like to view's history");
		
		TextField address = new TextField();
		address.setMaxWidth(120);
		address.setPromptText("Waterford");



/*
 * on button click opens up the view and passes the address from the textfield as a string
 */

		Button seeProperties = new Button("See properties");
		seeProperties.setOnAction(e -> {
			//System.out.println(getAddress(address));
			SearchByAddressViewWindow.display((getAddress(address)), getAddress(address));
			
		});

		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, addressLabel, address, seeProperties);
		layout.setAlignment(Pos.CENTER);

		Scene scene = new Scene(layout);
		searchByAddress.setScene(scene);
		/*
		 * Read the size of the window and then set the variable fontsize 
		 * to change as the window gets bigger or smaller
		 */
		
		fontSize.bind(searchByAddress.widthProperty().add(searchByAddress.heightProperty()).divide(50));
		layout.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString()));
		
		
		/*
		 * Gets the style of the font from the style sheet "styles.css"
		 * 
		 */
		scene.getStylesheets().add(GUI.class.getResource("styles.css").toExternalForm());
		searchByAddress.showAndWait();


	}
	/**
	 * 
	 * @param address takes ithe input from the address textfield 
	 * @return the textfield as a string
	 */
	private static String getAddress(TextField address) {
		String addressOut = address.getText();

		return addressOut;
	}
}