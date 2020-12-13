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

	public static void display(String title, int year) {
		Stage PrevYearView = new Stage();
		//USER
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
		fontSize.bind(PrevYearView.widthProperty().add(PrevYearView.heightProperty()).divide(80));
		layout.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString()));
		scene.getStylesheets().add(GUI.class.getResource("styles.css").toExternalForm());
		PrevYearView.showAndWait();
	
		
		
		
		
		
		
		
	}
}