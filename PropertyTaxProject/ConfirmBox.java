import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;



/**
 * 
 * This class is called if we need confirmation before entering details
 *
 */
public class ConfirmBox {
	static boolean answer;
 
	/**
	 * 
	 * @param message 	gets the details inputted from the previous window so it can be used all the time 
	 * @return			if the yes box is selected it returns true, if the no box is selected returns false
	 */
	public static boolean display( String message) {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Confirmation");
		window.setMinWidth(250);
		window.setMinHeight(125);
		window.setMaxWidth(250);
		window.setMaxHeight(125);
		Label label = new Label();
		label.setText(message);
		
		//create two buttons
		Button yesButton = new Button("Yes");
		Button noButton = new Button("No");
		
		//yes
		yesButton.setOnAction(e -> {
			answer = true;
			window.close();	
		});
		
		//no
		noButton.setOnAction(e -> {
			answer = false;
			window.close();
		});
		
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, yesButton, noButton);
		layout.setAlignment(Pos.CENTER);
		Scene scene = new Scene(layout);
		
		/*
		 * Gets the style of the font from the style sheet "styles.css"
		 * 
		 */
		scene.getStylesheets().add(GUI.class.getResource("styles.css").toExternalForm());
		
		/*
		 * Displays the scene
		 */
		window.setScene(scene);
		window.showAndWait();
		
		return answer; 
	}
}