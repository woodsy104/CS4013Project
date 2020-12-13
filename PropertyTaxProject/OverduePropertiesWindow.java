
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
/**
 * class to view the overdue properties on the admin side
 */
public class OverduePropertiesWindow {

	private static DoubleProperty fontSize = new SimpleDoubleProperty(4);
	/*
	 * displays the overdue properties for an admin
	 */
	public static void display() {
		
		DeptEnvironment deptOfEnvironment = new DeptEnvironment();
	
		
		Stage OverdueProperties = new Stage();

		OverdueProperties.initModality(Modality.APPLICATION_MODAL);
		OverdueProperties.setTitle("Overdue properties");
		OverdueProperties.setMinWidth(400);

		Label label = new Label();
		label.setText("These are the properties that are overdue:\n");
		
		
		Label sortByEircodeLabel = new Label("Whats the eircode you would like to sort by");
		TextField sortByEircode = new TextField();
		sortByEircode.setMinWidth(80);
		sortByEircode.setMaxWidth(300);
		sortByEircode.setPromptText("Enter an Eircode key");




		Label sortByYearLabel = new Label("What year would you like to sort by");
		TextField sortByYear = new TextField();
		sortByYear.setMinWidth(80);
		sortByYear.setMaxWidth(300);
		sortByYear.setPromptText("Year");
		
		/*
		 * on button click check and see what information is filled
		 * if the eircode is filled sort by the eircode and the year
		 * 
		 * else if they eircode isnt filled sort by year
		 * 
		 */
		Button viewButton = new Button("view the properties");
		viewButton.setOnAction(e -> {			
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
		
		
		/*
		 * close the window on the button press
		 */
		Button closeButton = new Button("Close the window");
		closeButton.setOnAction(e -> {
			OverdueProperties.close();
		});

		/*
		 * sets the layout of the scene
		 */
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, sortByEircodeLabel, sortByEircode, sortByYearLabel, sortByYear, viewButton,  closeButton);
		layout.setAlignment(Pos.CENTER);

		Scene scene = new Scene(layout);
		
		/*
		 * gets the styles from styles.css
		 */
		scene.getStylesheets().add(GUI.class.getResource("styles.css").toExternalForm());
		
		/*
		 * Read the size of the window and then set the variable fontsize 
		 * to change as the window gets bigger or smaller
		 */
		
		fontSize.bind(OverdueProperties.widthProperty().add(OverdueProperties.heightProperty()).divide(80));
		layout.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString()));
		
		
		
		OverdueProperties.setScene(scene);
		OverdueProperties.showAndWait();
		
	}
	
	/**
	 *@param sortByYear gets the input from TextField sortByYear and converts it to an int
	 *@return the int of sortByYear
	 */
	private static int getYear(TextField sortByYear) {
		int yearOut = Integer.parseInt(sortByYear.getText());
		return yearOut;
	}
	/**
	 * 
	 * @param sortByYear 	gets the information from textfield 
	 * @return returns true if the textfield can be converted to an int
	 */
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
	
	
	/**
	 *@param sortByEircode gets the input from TextField sortByYear
	 *@return true if the text field can be converted to a string
	 */
	private static boolean getEirTrue(TextField sortByEircode) {
		try {
			String eirOut = sortByEircode.getText();
			if(eirOut.trim().isEmpty()) {
				return false;
			}
			if (eirOut.length() > 2) {
				return true;
			}
		} catch (Exception e2) {
			ErrorWindow.display("You must input an Eircode/Eircode key");
			return false;
		}
		return false;
	
	}
	
	
}