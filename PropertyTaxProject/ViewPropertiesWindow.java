import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class ViewPropertiesWindow {
	private static DoubleProperty fontSize = new SimpleDoubleProperty(4);


	public static void display() {
		Stage viewPropertiesWindows = new Stage();
		/*
		 * Creates a scrollable window so that you always can view all properties
		 */
		ScrollPane layout = new ScrollPane();
		
		viewPropertiesWindows.initModality(Modality.APPLICATION_MODAL);
		viewPropertiesWindows.setTitle("Payments");
		viewPropertiesWindows.setMinWidth(400);
		
		/*
		 * Creates an instance of owner and sets the name of the owner to what was
		 * Inputed in the first screen then goes through a for loop printing them out
		 */
		Owner owner = new Owner(GUI.getOwnerNameText());
		String props = "These are the properties that you own :\n\n";
		
			for(int i = 0; i < owner.viewProperties().size(); i++){
				props += "Property " + (i+1) +"\n" + owner.viewProperties().get(i).toString() + "\n";
		}
		
			/*
			 * Prints the properties that are owned
			 */
		Label properties = new Label();
		properties.setText(props);
		properties.setMinWidth(Region.USE_PREF_SIZE);
		properties.setMaxWidth(Region.USE_PREF_SIZE);
		properties.setWrapText(true);
	

		
		
		layout.setContent(properties);
		Scene scene = new Scene(layout);
		
		/**
		 * Read the size of the window and then set the variable fontsize 
		 * to change as the window gets bigger or smaller
		 */
		fontSize.bind(viewPropertiesWindows.widthProperty().add(viewPropertiesWindows.heightProperty()).divide(60));
		layout.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString()));
		
		/**
		 * Gets the style of the font from the style sheet "styles.css"
		 * 
		 */
		scene.getStylesheets().add(GUI.class.getResource("styles.css").toExternalForm());
		
		/*
		 * Show the window 
		 */
		viewPropertiesWindows.setScene(scene);
		viewPropertiesWindows.showAndWait();
	}

}