package guiClasses;


//import Exception;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Label;

public class GUI extends Application {
	
	Button buttonPayments, buttonAddProperties, overdueButton, payButton, previousYearsButton;
	Button changeTaxes, viewChangeEffects, overdueProperties, propertyTaxOfArea;
	Stage window;
	Scene scene1, ownerMain, adminMain, overdue, previousYear;
	private DoubleProperty fontSize = new SimpleDoubleProperty(4);


	public static void main(String[] args) {
		launch(args);
	}
	
	
	
	
	public void start(Stage primaryStage) throws Exception {
		
		window = primaryStage;
		window.setTitle("GUI");
		window.setMinWidth(600);
		window.setMinHeight(400);
		Label label = new Label();
		label.setText("Would you like to view this program as the department of environment or property owner");
		label.setMinWidth(Region.USE_PREF_SIZE);
		
		//scene1
		
		//create two buttons
		Button owner = new Button("Property owner");
		Button admin = new Button("Department Of environment");
		
		//yes
		owner.setOnAction(e -> {
			window.setScene(ownerMain);
			
			
		});
		
		//no
		admin.setOnAction(e -> {
			window.setScene(adminMain);
		});
		
		
		
		
		
		
		VBox layout = new VBox(20);
		layout.getChildren().addAll(label, owner, admin);
		
		
		
		
		//auto change font size
		fontSize.bind(window.widthProperty().add(window.heightProperty()).divide(80));
		layout.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString()));
		
		
		layout.setPadding(new Insets(10, 10, 10, 10));
		layout.setAlignment(Pos.CENTER);
		
		scene1 = new Scene(layout, 600, 400);
		
		//Scene scene = new Scene(layout);
		scene1.getStylesheets().add(GUI.class.getResource("styles.css").toExternalForm());
		
		
		//Owner Scene
		buttonAddProperties = new Button("Add properties");
		buttonAddProperties.setOnAction(e -> AddPropertiesWindow.display());

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




		GridPane layoutOwner = new GridPane();
		fontSize.bind(window.widthProperty().add(window.heightProperty()).divide(80));
		layoutOwner.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString()));
		
		layoutOwner.setPadding(new Insets(10, 10, 10, 10));
		layoutOwner.setVgap(8);
		layoutOwner.setHgap(10);

		GridPane.setConstraints(buttonAddProperties, 11, 35 );
		GridPane.setConstraints(buttonPayments,      12, 35 );
		GridPane.setConstraints(overdueButton,       13, 35 );
		GridPane.setConstraints(payButton,			 14, 35 );
		GridPane.setConstraints(previousYearsButton, 15, 35 );




		layoutOwner.getChildren().addAll(buttonAddProperties, buttonPayments, overdueButton, payButton, previousYearsButton);
		layout.setAlignment(Pos.CENTER);
		//auto change font size
				
		
		ownerMain = new Scene(layoutOwner, 600, 400);
		ownerMain.getStylesheets().add(GUI.class.getResource("styles.css").toExternalForm());
		
		//Button changeTaxes, viewChangeEffects, overdueProperties, propertyTaxOfArea
		
		//Department scene
		changeTaxes = new Button("Change the taxes");
		changeTaxes.setOnAction(e -> changeTaxesWindow.display("This will be where we change tax of an area"));

		viewChangeEffects = new Button("View the effects of changing tax rates");
		viewChangeEffects.setOnAction(e -> viewChangeEffectsWindow.display("This will be where view the effects of changing tax rates"));

		overdueProperties = new Button("Overdue properties");
		overdueProperties.setOnAction(e -> OverduePropertiesWindow.display("This will display any overdue properties"));
/*
		payButton = new Button("Pay");
		payButton.setOnAction(e -> PayWindow.display("This will create a form for you to make payments "
				+ "should also display a list of the houses so they can select which one they want to pay for, "
				+ "perhaps clicking on that house?"));
*/
		propertyTaxOfArea = new Button("View the property tax of an area");
		propertyTaxOfArea.setOnAction(e -> PropertyTaxOfAreaWindow.display());




		GridPane layoutAdmin = new GridPane();
		layoutAdmin.setPadding(new Insets(10, 10, 10, 10));
		layoutAdmin.setVgap(8);
		layoutAdmin.setHgap(10);

		GridPane.setConstraints(changeTaxes, 11, 35 );
		GridPane.setConstraints(viewChangeEffects,      12, 35 );
		GridPane.setConstraints(overdueProperties,       13, 35 );
		GridPane.setConstraints(propertyTaxOfArea,			 14, 35 );
		//GridPane.setConstraints(previousYearsButton, 15, 35 );




		layoutAdmin.getChildren().addAll(changeTaxes, viewChangeEffects, overdueProperties, propertyTaxOfArea);
		//layout.setAlignment(Pos.CENTER);
		
		//auto change font size
				fontSize.bind(window.widthProperty().add(window.heightProperty()).divide(80));
				layout.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString()));
				
		
		adminMain = new Scene(layoutAdmin, 600, 400);
		adminMain.getStylesheets().add(GUI.class.getResource("styles.css").toExternalForm());
		
		
		
		
		
		//default
		window.setScene(scene1);
		window.show();
		
		
	}
	
	
	

}



































