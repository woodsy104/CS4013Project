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

public class PaymentsWindow {
	private static DoubleProperty fontSize = new SimpleDoubleProperty(4);


	public static void display(String message) {
		Stage payments = new Stage();
		
		payments.initModality(Modality.APPLICATION_MODAL);
		payments.setTitle("Payments");
		payments.setMinWidth(400);

		Label label = new Label();
		label.setText("These are the payments made by user:\n" +message);
		Button closeButton = new Button("Close the window");
		label.setWrapText(true);
		
		
		
		
		
		Owner owner = new Owner(GUI.getOwnerNameText());
		String props = "";
			for(int i = 0; i < owner.viewProperties().size(); i++){
				props += "Property " + (i+1) +"\n" + owner.viewProperties().get(i).toString() + "\n";
				
		}
		
		Label properties = new Label();
		label.setText(props);
		label.setWrapText(true);
	

		closeButton.setOnAction(e -> payments.close());
		
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, closeButton);
		layout.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(layout);
		payments.setScene(scene);
		scene.getStylesheets().add(GUI.class.getResource("styles.css").toExternalForm());
		fontSize.bind(payments.widthProperty().add(payments.heightProperty()).divide(100));
		layout.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString()));
		
		//HERE NEEDS THE OTHER METHODS TO BE ADDED SO THAT IT DISPLAys the right infortmation
		
		
		
		
		
		payments.showAndWait();
		
	//combine overdue and this one 	
	}

}
