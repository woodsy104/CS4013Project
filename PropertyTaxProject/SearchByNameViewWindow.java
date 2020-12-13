import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
/**
 * 
 * Class to view the results from searching for a user by name
 *
 */
public class SearchByNameViewWindow {
	private static DoubleProperty fontSize = new SimpleDoubleProperty(4);
/**
 * 
 * @param title takes the name of the person that was searched for and displays it as title
 * @param name takes the name of the person that was searched for and uses that to display the list of properties owned by that person
 */
	public static void display(String title, String name) {
		Stage searchByNameView = new Stage();
		
		searchByNameView.initModality(Modality.APPLICATION_MODAL);
		searchByNameView.setTitle("Properties of " + title );
		searchByNameView.setMinWidth(400);

		Label label = new Label();
		label.setText("these are the properties from previous years:\n");
		
		String props = "";
		Owner owner = new Owner(name);
	
		String props1 = "";
			for(int i = 0; i < owner.viewProperties().size(); i++){
				props1 += "Property " + (i+1) +"\n" + owner.viewProperties().get(i).toString() + "\n";
				
		}	
		
		
		Label properties = new Label();
		label.setText(props1);
		label.setWrapText(true);
		
		
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, properties);
		layout.setAlignment(Pos.CENTER);

		Scene scene = new Scene(layout);
		
		
		/*
		 * Read the size of the window and then set the variable fontsize 
		 * to change as the window gets bigger or smaller
		 */
		
		fontSize.bind(searchByNameView.widthProperty().add(searchByNameView.heightProperty()).divide(80));
		layout.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString()));
		
		/*
		 * Gets the style of the font from the style sheet "styles.css"
		 * 
		 */
		scene.getStylesheets().add(GUI.class.getResource("styles.css").toExternalForm());
		
		/*
		 * Show the window 
		 */
		searchByNameView.setScene(scene);
		searchByNameView.showAndWait();
	

		
	}
}