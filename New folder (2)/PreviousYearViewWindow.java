import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class PreviousYearViewWindow {
	private static DoubleProperty fontSize = new SimpleDoubleProperty(4);
/**
 * 
 * @param title		Gets the inputed year from PreviousYearWindow as a string to set the title of the window
 * @param year		Gets the inputed year from PreviousYearWindow as a integer to call viewPropertiesByYear
 */
	public static void display(String title, int year) {
		Stage PrevYearView = new Stage();
		
		PrevYearView.initModality(Modality.APPLICATION_MODAL);
		PrevYearView.setTitle("Properties of " + title );
		PrevYearView.setMinWidth(400);

		Label label = new Label();
		label.setText("these are the properties from previous years:\n");
		
		String props = "";
		Owner owner = new Owner(GUI.getOwnerNameText());
		
		for(int i = 0; i < owner.viewPropertiesByYear(year).size(); i++){
			props += "Property " + (i+1) +"\n" + owner.viewPropertiesByYear(year).get(i).toString() + "\n";
			
		}	
		
		
		Label properties = new Label();
		label.setText(props);
		label.setWrapText(true);
		
		
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, properties);
		layout.setAlignment(Pos.CENTER);

		Scene scene = new Scene(layout);
		PrevYearView.setScene(scene);
		
		/*
		 * Read the size of the window and then set the variable fontsize 
		 * to change as the window gets bigger or smaller
		 */
		
		fontSize.bind(PrevYearView.widthProperty().add(PrevYearView.heightProperty()).divide(80));
		layout.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString()));
		
		/*
		 * Gets the style of the font from the style sheet "styles.css"
		 * 
		 */
		scene.getStylesheets().add(GUI.class.getResource("styles.css").toExternalForm());
		
		/*
		 * Show the window 
		 */
		PrevYearView.showAndWait();
	
	}
}