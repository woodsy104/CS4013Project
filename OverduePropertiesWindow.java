package guiClasses;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class OverduePropertiesWindow {
//ADMIN
	private static DoubleProperty fontSize = new SimpleDoubleProperty(4);
	
	public static void display(String message) {
		
		DeptEnvironment deptOfEnvironment = new DeptEnvironment();
		Owner menu = new Owner("Sean");
		 ArrayList<Property> properties = deptOfEnvironment.getTaxDataForProperty("waterford");
	
		
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
			
			int year1 = getYear(sortByYear);
			
			if (getEirTrue(sortByEircode) == true) {
				//String eir = (deptOfEnvironment.getOverdueTaxForEircode(year1, (getEir(sortByEircode))).toString());
				String year = (deptOfEnvironment.getOverdueTaxForYear(getYear(sortByYear)).toString());
				OverduePropertiesViewWindow.display(getYear(sortByYear), year);
				label.setWrapText(true);
				
			}else if (getYearTrue(sortByYear) == true) {
				String year = (deptOfEnvironment.getOverdueTaxForYear(getYear(sortByYear)).toString());
				OverduePropertiesViewWindow.display(getYear(sortByYear), year);
				label.setWrapText(true);
			}
			
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
	
	private static boolean getYearTrue(TextField sortByYear) {
		try {
			int yearOut = Integer.parseInt(sortByYear.getText());
			return true;
		}catch (Exception e2) {
			ErrorWindow.display("You must enter a year");
			return false;
		}
		
	}
	
	
	private static String getEir(TextField sortByEircode) {
		try {
			String eirOut = sortByEircode.getText();
			return eirOut;
		} catch (Exception e2) {
			ErrorWindow.display("You must input an Eircode/Eircode key");
			System.out.println("failed successfully");
		}
		return null;
	
	}
	
	private static boolean getEirTrue(TextField sortByEircode) {
		try {
			String eirOut = sortByEircode.getText();
			if (eirOut.length() > 2) {
				//System.out.println("This is returning true");
				return true;
			}
		} catch (Exception e2) {
			ErrorWindow.display("You must input an Eircode/Eircode key");
			System.out.println("failed successfully");
			return false;
		}
		return false;
	
	}
	
	
}
