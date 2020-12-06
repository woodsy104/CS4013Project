package guiClasses;


//import Exception;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;

public class GUI extends Application {
	
	Button buttonPayments, buttonAddProperties, overdueButton, payButton, previousYearsButton;
	Stage window;
	//Scene scene1, payments, unpaid, overdue, previousYear;


	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.setTitle("GUI");
		
		//window.setOnCloseRequest(e -> closeProgram());
		
		
		buttonAddProperties = new Button("Add properties");
		buttonAddProperties.setOnAction(e -> AddPropertiesWindow.display("This will be where we add a new property "
				+ "I still need to create a method to allow users to input all their data. That shall be done either later tonight or tomorrow"));
		
		buttonPayments = new Button("Payments");
		buttonPayments.setOnAction(e -> PaymentsWindow.display("This will be where we call the array of the users payments"));
		
		overdueButton = new Button("Overdue");
		overdueButton.setOnAction(e -> OverduePaymentsWindow.display("This will display any overdue payments"));
		
		payButton = new Button("Pay");
		payButton.setOnAction(e -> PayWindow.display("This will create a form for you to make payments "
				+ "should also display a list of the houses so they can select which one they want to pay for, "
				+ "perhaps clicking on that house?"));
		
		previousYearsButton = new Button("Previous years");
		previousYearsButton.setOnAction(e -> PreviousYearsWindow.display("This will display the list of previous years"));
		
		
		//StackPane layout = new StackPane();
	
		HBox layout = new HBox(10);
		layout.getChildren().addAll(buttonAddProperties, buttonPayments, overdueButton, payButton, previousYearsButton);
		layout.setAlignment(Pos.CENTER);
		Scene scene = new Scene(layout, 600, 400);
		window.setScene(scene);
		window.show();
	}
	
	
	private void closeProgram() {
		boolean result = ConfirmBox.display("Confirmation", "Are you sure you want to close this program without saving");
		System.out.println(result);
		if(result == true) {
			window.close();
		}
		
	}
	

}



































