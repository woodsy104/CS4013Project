package guiClasses;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class SearchByAddressViewWindow {
	private static DoubleProperty fontSize = new SimpleDoubleProperty(4);

	public static void display(String title, String name) {
		Stage searchByAddressView = new Stage();
		
		searchByAddressView.initModality(Modality.APPLICATION_MODAL);
		searchByAddressView.setTitle("Properties of " + title );
		searchByAddressView.setMinWidth(400);

		Label label = new Label();
		label.setText("these are the properties from previous years:\n");
		
		String props = "";
		
		
		Owner owner = new Owner(name);
		
		String props1 = "";
			for(int i = 0; i < owner.viewProperties().size(); i++){
				props1 += "Property " + (i+1) +"\n" + owner.viewProperties().get(i).toString() + "\n";
				
		}	
		
		//	DeptEnvironment.getTaxDataForProperty("hei");
		
		Label properties = new Label();
		label.setText(props1);
		label.setWrapText(true);
		
		
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, properties);
		layout.setAlignment(Pos.CENTER);

		Scene scene = new Scene(layout);
		searchByAddressView.setScene(scene);
		fontSize.bind(searchByAddressView.widthProperty().add(searchByAddressView.heightProperty()).divide(80));
		layout.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString()));
		scene.getStylesheets().add(GUI.class.getResource("styles.css").toExternalForm());
		searchByAddressView.showAndWait();
	
		
		
		
		
		
		
		
	}
}
