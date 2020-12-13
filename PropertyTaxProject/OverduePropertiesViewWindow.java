import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class OverduePropertiesViewWindow {
	private static DoubleProperty fontSize = new SimpleDoubleProperty(4);

	
	/**
	 * @param year 			this takes in the year that the property was registered and sets the title
	 * @param properties	this takes in a string with the output that will be produced, 
	 * 						we simply set the label to become that string, all calculations done in OverduePropertiesWindow
	 */
	public static void display(int year, String properties) {
		Stage overduePropertiesView = new Stage();
		
		/*
		 * modality means that you cant click out of the window until it is closed
		 */
		overduePropertiesView.initModality(Modality.APPLICATION_MODAL);
		overduePropertiesView.setTitle("Properties of " + year );
		overduePropertiesView.setMinWidth(400);

		Label label = new Label();
		label.setText("these are the properties from previous years:\n");
		
	
		Label properties1 = new Label();
		label.setText(properties);
		label.setWrapText(true);
		
		/*
		 * Creates the layout 
		 */
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, properties1);
		layout.setAlignment(Pos.CENTER);

		Scene scene = new Scene(layout);		
		
		/*
		 * Read the size of the window and then set the variable fontsize 
		 * to change as the window gets bigger or smaller
		 */
		
		fontSize.bind(overduePropertiesView.widthProperty().add(overduePropertiesView.heightProperty()).divide(80));
		layout.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString()));
		
		
		/*
		 * Gets the style of the font from the style sheet "styles.css"
		 * 
		 */
		scene.getStylesheets().add(GUI.class.getResource("styles.css").toExternalForm());
		
		
		/*
		 * Show the window 
		 */
		overduePropertiesView.setScene(scene);
		overduePropertiesView.showAndWait();
	}
}