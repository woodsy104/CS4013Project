package guiClasses;

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

public class OverduePropertiesViewWindow {
	private static DoubleProperty fontSize = new SimpleDoubleProperty(4);

	public static void display(int year, String properties) {
		Stage overduePropertiesView = new Stage();
		
		overduePropertiesView.initModality(Modality.APPLICATION_MODAL);
		overduePropertiesView.setTitle("Properties of " + year );
		overduePropertiesView.setMinWidth(400);

		Label label = new Label();
		label.setText("these are the properties from previous years:\n");
		
	
	
		
		
		Label properties1 = new Label();
		label.setText(properties);
		label.setWrapText(true);
		
		
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, properties1);
		layout.setAlignment(Pos.CENTER);

		Scene scene = new Scene(layout);
		overduePropertiesView.setScene(scene);
		fontSize.bind(overduePropertiesView.widthProperty().add(overduePropertiesView.heightProperty()).divide(80));
		layout.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString()));
		scene.getStylesheets().add(GUI.class.getResource("styles.css").toExternalForm());
		overduePropertiesView.showAndWait();
	
		
		
		
		
		
		
		
	}
}
