import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;


/**
 * 
 * class to let the user see their balancing statement
 *
 */
public class BalancingStatementWindow {
	private static DoubleProperty fontSize = new SimpleDoubleProperty(4);
	
	/**
	 * displays the scene to view the balancing statemtn
	 */
	public static void display() {
		Stage OverduePayments = new Stage();

		OverduePayments.initModality(Modality.APPLICATION_MODAL);
		OverduePayments.setTitle("Overdue Payments");
		OverduePayments.setMinWidth(400);

		Label label = new Label();
		label.setText("This is your balancing statement:\n");

		Owner owner = new Owner(GUI.getOwnerNameText());
		Label balStatement = new Label();
		try {
			balStatement.setText(owner.getBalancingStatement(GUI.getOwnerNameText()));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Button closeButton = new Button("Close the window");
		closeButton.setOnAction(e -> {
			OverduePayments.close();
		});

		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, balStatement, closeButton);
		layout.setAlignment(Pos.CENTER);
		
		
		/*
		 * Read the size of the window and then set the variable fontsize 
		 * to change as the window gets bigger or smaller
		 */
		
		fontSize.bind(OverduePayments.widthProperty().add(OverduePayments.heightProperty()).divide(40));
		layout.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString()));
		
		
		/*
		 * 
		 * displays the scene 
		 */
		Scene scene = new Scene(layout);
		scene.getStylesheets().add(GUI.class.getResource("styles.css").toExternalForm());
		OverduePayments.setScene(scene);
		OverduePayments.showAndWait();

	}

}