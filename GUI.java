package guiClasses;


//import Exception;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
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
		window.setMinHeight(600);
		window.setMinWidth(600);
		
		//window.setOnCloseRequest(e -> closeProgram());
		
		
		buttonAddProperties = new Button("Add properties");
		buttonAddProperties.setOnAction(e -> AddPropertiesWindow.display("This will be where we add a new property "));
		
		buttonPayments = new Button("Blancing statement");
		buttonPayments.setOnAction(e -> PaymentsWindow.display("This will be where we call the array of the users payments"));
		
		overdueButton = new Button("Overdue");
		overdueButton.setOnAction(e -> OverduePaymentsWindow.display("This will display any overdue payments"));
		
		payButton = new Button("Pay");
		payButton.setOnAction(e -> PayWindow.display("This will create a form for you to make payments "
				+ "should also display a list of the houses so they can select which one they want to pay for, "
				+ "perhaps clicking on that house?"));
		
		previousYearsButton = new Button("Previous years");
		previousYearsButton.setOnAction(e -> PreviousYearsWindow.display("This will display the list of previous years"));
		
		
		
	
		GridPane layout = new GridPane();
		layout.setPadding(new Insets(10, 10, 10, 10));
		layout.setVgap(8);
		layout.setHgap(10);
		
		GridPane.setConstraints(buttonAddProperties, 11, 35 );
		GridPane.setConstraints(buttonPayments,      12, 35 );
		GridPane.setConstraints(overdueButton,       13, 35 );
		GridPane.setConstraints(payButton,			 14, 35 );
		GridPane.setConstraints(previousYearsButton, 15, 35 );
		
		
		
		
		layout.getChildren().addAll(buttonAddProperties, buttonPayments, overdueButton, payButton, previousYearsButton);
		//layout.setAlignment(Pos.CENTER);
		Scene scene = new Scene(layout, 600, 400);
		
		window.setScene(scene);
		window.show();
		
		//scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
	}
	
	
	private void closeProgram() {
		boolean result = ConfirmBox.display("Confirmation", "Are you sure you want to close this program without saving");
		System.out.println(result);
		if(result == true) {
			window.close();
		}
		
	}
	

}



































