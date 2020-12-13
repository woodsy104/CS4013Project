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

public class ViewChangesEffectsViewWindow {
	private static DoubleProperty fontSize = new SimpleDoubleProperty(4);

	public static void display(String message) {
		Stage viewChangesEffectsView = new Stage();
		//USER
		viewChangesEffectsView.initModality(Modality.APPLICATION_MODAL);
		viewChangesEffectsView.setTitle("The effects of changing these taxes are");
		viewChangesEffectsView.setMinWidth(400);
		viewChangesEffectsView.setMinHeight(300);


		Label label = new Label();
		label.setText("these are the effects of changing tax:\n");
		
	
	
		
		
		Label properties1 = new Label();
		label.setText(message);
		label.setWrapText(true);
		
		
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, properties1);
		layout.setAlignment(Pos.CENTER);

		Scene scene = new Scene(layout);
		viewChangesEffectsView.setScene(scene);
		fontSize.bind(viewChangesEffectsView.widthProperty().add(viewChangesEffectsView.heightProperty()).divide(30));
		layout.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString()));
		scene.getStylesheets().add(GUI.class.getResource("styles.css").toExternalForm());
		viewChangesEffectsView.showAndWait();
	
		
		
		
		
		
		
		
	}
}