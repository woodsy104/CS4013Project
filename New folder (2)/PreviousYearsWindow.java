import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
public class PreviousYearsWindow {
	private static DoubleProperty fontSize = new SimpleDoubleProperty(4);

	
	/**
	 * Displays the user window to input the year they wish to view properties 
	 * from 
	 */
	public static void display() {
		Stage PrevYear = new Stage();
		PrevYear.initModality(Modality.APPLICATION_MODAL);
		PrevYear.setTitle("Previous Years");
		PrevYear.setMinWidth(400);

		Label label = new Label();
		label.setText("Properties from Previous Years:\n");


		Label yearLabel = new Label("What year would you like to view?");
		TextField year = new TextField();
		year.setMaxWidth(120);
		year.setPromptText("2016");




	/*
	 * Gets the input from the text fields and sends them as arguments to 
	 * PreviousYearViewWindow to set the title and the messages of the window
	 */
		Button confirmButton = new Button("See Properties");
		confirmButton.setOnAction(e -> {
			if (getYear(year) != -1 ) {
				PreviousYearViewWindow.display(Integer.toString(getYear(year)), getYear(year));
			}
		});

		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, yearLabel, year, confirmButton);
		layout.setAlignment(Pos.CENTER);

		Scene scene = new Scene(layout);
		PrevYear.setScene(scene);
		
		
		/*
		 * Read the size of the window and then set the variable fontsize 
		 * to change as the window gets bigger or smaller
		 */
		
		fontSize.bind(PrevYear.widthProperty().add(PrevYear.heightProperty()).divide(40));
		layout.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString()));

		/**
		 * Gets the style of the font from the style sheet "styles.css"
		 * 
		 */
		scene.getStylesheets().add(GUI.class.getResource("styles.css").toExternalForm());
		
		
		/*
		 * Show the window 
		 */
		PrevYear.showAndWait();
	}

	/*
	 * Gets the text that you input in the text field and convert it to an int
	 * Catches and non ints
	 */
	private static int getYear(TextField year) {
		try {
			int yearOut = Integer.parseInt(year.getText());
			return yearOut;
		}catch(NumberFormatException e2) {
			ErrorWindow.display("Must input a whole number");
		}
		return -1;
	}

}