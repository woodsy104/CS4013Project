
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class SearchByAddressViewWindow {
	private static DoubleProperty fontSize = new SimpleDoubleProperty(4);

	public static void display(String title, String address) {
		Stage searchByAddressView = new Stage();

		searchByAddressView.initModality(Modality.APPLICATION_MODAL);
		searchByAddressView.setTitle("Properties of " + title );
		searchByAddressView.setMinWidth(400);
		searchByAddressView.setMinHeight(400);


		Label label = new Label();
		label.setText("these are the properties from previous years:\n");




		DeptEnvironment admin = new DeptEnvironment();

		String props1 = "";


		ArrayList<Property> properties = admin.getTaxDataForProperty(address);
		for(int i = 0; i < properties.size(); i++){
			props1 = (properties.get(i)).toString();
		}


		Label properties1 = new Label();
		properties1.setText(props1);
		properties1.setWrapText(true);


		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, properties1);
		layout.setAlignment(Pos.CENTER);

		Scene scene = new Scene(layout);
		searchByAddressView.setScene(scene);
		
		/**
		 * Read the size of the window and then set the variable fontsize 
		 * to change as the window gets bigger or smaller
		 */
		
		fontSize.bind(searchByAddressView.widthProperty().add(searchByAddressView.heightProperty()).divide(80));
		layout.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString()));
		
		
		/**
		 * Gets the style of the font from the style sheet "styles.css"
		 * 
		 */
		scene.getStylesheets().add(GUI.class.getResource("styles.css").toExternalForm());


		searchByAddressView.showAndWait();








	}
}