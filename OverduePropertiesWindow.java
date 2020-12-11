package guiClasses;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class OverduePropertiesWindow {
//ADMIN
	private static DoubleProperty fontSize = new SimpleDoubleProperty(4);
	
	public static void display(String message) {
		
		DeptEnvironment deptOfEnvironment = new DeptEnvironment();
	
		
		Stage OverdueProperties = new Stage();

		OverdueProperties.initModality(Modality.APPLICATION_MODAL);
		OverdueProperties.setTitle("Overdue properties");
		OverdueProperties.setMinWidth(400);

		Label label = new Label();
		label.setText("These are the properties that are overdue:\n" + message);
		
		
		Label sortByEircodeLabel = new Label("Whats the eircode you would like to sort by");
		TextField sortByEircode = new TextField();
		sortByEircode.setMaxWidth(140);
		sortByEircode.setPromptText("Enter the first digits of the Eircode");




		Label sortByYearLabel = new Label("What year would you like to sort by");
		TextField sortByYear = new TextField();
		sortByYear.setMaxWidth(140);
		sortByYear.setPromptText("Year");
		
		Button viewButton = new Button("view the properties");
		viewButton.setOnAction(e -> {
			
	
			String hello = (deptOfEnvironment.getOverdueTaxForYear(getYear(sortByYear)).toString());
			
			
			
			
			OverduePropertiesViewWindow.display(getYear(sortByYear), hello);
			label.setWrapText(true);

		});
		
		Button closeButton = new Button("Close the window");
		closeButton.setOnAction(e -> {
			
			OverdueProperties.close();

		});

		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, sortByEircodeLabel, sortByEircode, sortByYearLabel, sortByYear, viewButton,  closeButton);
		layout.setAlignment(Pos.CENTER);

		Scene scene = new Scene(layout);
		scene.getStylesheets().add(GUI.class.getResource("styles.css").toExternalForm());
		fontSize.bind(OverdueProperties.widthProperty().add(OverdueProperties.heightProperty()).divide(80));
		layout.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString()));
		OverdueProperties.setScene(scene);
		OverdueProperties.showAndWait();
		
	}
	
	private static int getYear(TextField sortByYear) {
		int yearOut = Integer.parseInt(sortByYear.getText());


		return yearOut;
	}
}
