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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class GUI extends Application {

	Button buttonPayments, buttonAddProperties, overdueButton, payButton, previousYearsButton;
	Button changeTaxes, viewChangeEffects, overdueProperties, propertyTaxOfArea, searchByNameButton,  searchByAddressButton;
	Stage window;
	Scene scene1, ownerMain, adminMain, ownerName;
	private static String ownerNameText;
	private DoubleProperty fontSize = new SimpleDoubleProperty(4);
	String title = "GUI";
	
	
	
	
	

	public static void main(String[] args) {
		launch(args);
	}




	public void start(Stage primaryStage) throws Exception {

		window = primaryStage;
		window.setTitle(title);
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
			title = "Owner Home GUI";
			window.setScene(ownerName);

		});

		//no
		admin.setOnAction(e -> {
			title = "Admin Home GUI";
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











		//OwnerName
		window.setTitle("Owner home");

		Label ownerLabel = new Label("Who is the owner of this property?");
		TextField ownerNameInput = new TextField();

		ownerNameInput.setPromptText("Owner");

		Button confirm = new Button("Confirm this is your name");


		confirm.setOnAction(e -> {
			window.setScene(ownerMain);
			ownerNameText = ownerNameInput.getText();

		});
		
		
		
		
		
		
		

		VBox layoutOwnerName = new VBox(10);
		layoutOwnerName.getChildren().addAll(label, ownerLabel, ownerNameInput, confirm);
		layoutOwnerName.setAlignment(Pos.CENTER);
		layoutOwnerName.setPadding(new Insets(10, 10, 10, 10));


		ownerName = new Scene(layoutOwnerName, 600, 400);
		ownerName.getStylesheets().add(GUI.class.getResource("styles.css").toExternalForm());

















		//Owner Scene
		
		GridPane layoutOwner = new GridPane();
		
		
		buttonAddProperties = new Button("Register property");
		buttonAddProperties.prefWidthProperty().bind(Bindings.divide(layoutOwner.widthProperty(), 3.0));
		buttonAddProperties.prefHeightProperty().bind(Bindings.divide(layoutOwner.heightProperty(), 6.0));
		buttonAddProperties.setOnAction(e -> AddPropertiesWindow.display());

		buttonPayments = new Button("View properties");
		buttonPayments.prefWidthProperty().bind(Bindings.divide(layoutOwner.widthProperty(), 3.0));
		buttonPayments.prefHeightProperty().bind(Bindings.divide(layoutOwner.heightProperty(), 6.0));
		buttonPayments.setOnAction(e -> ViewPropertiesWindow.display("This will be where we call the array of the users payments"));

		overdueButton = new Button("Balancing statement");
		overdueButton.prefWidthProperty().bind(Bindings.divide(layoutOwner.widthProperty(), 3.0));
		overdueButton.prefHeightProperty().bind(Bindings.divide(layoutOwner.heightProperty(), 6.0));
		overdueButton.setOnAction(e -> BalancingStatementWindow.display("This will display any overdue payments"));

		payButton = new Button("Make a payment");
		payButton.prefWidthProperty().bind(Bindings.divide(layoutOwner.widthProperty(), 3.0));
		payButton.prefHeightProperty().bind(Bindings.divide(layoutOwner.heightProperty(), 6.0));
		payButton.setOnAction(e -> PayWindow.display("This will create a form for you to make payments "
				+ "should also display a list of the houses so they can select which one they want to pay for, "
				+ "perhaps clicking on that house?"));

		previousYearsButton = new Button("Search by year");
		previousYearsButton.prefWidthProperty().bind(Bindings.divide(layoutOwner.widthProperty(), 3.0));
		previousYearsButton.prefHeightProperty().bind(Bindings.divide(layoutOwner.heightProperty(), 6.0));
		previousYearsButton.setOnAction(e -> PreviousYearsWindow.display("This will display the list of previous years"));
		
		
	
		Button back = new Button("Back");

		back.setOnAction(e -> {
			window.setScene(scene1);
		});
	
		HBox topMenu = new HBox();
		topMenu.getChildren().add(back);


		
		layoutOwner.setPadding(new Insets(10, 10, 10, 10));

		
		//auto change font size
		fontSize.bind(window.widthProperty().add(window.heightProperty()).divide(80));
		layoutOwner.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString()));
		
		layoutOwner.setVgap(8);
		layoutOwner.setHgap(10);
		
		GridPane.setConstraints(buttonAddProperties, 	0, 0 );
		GridPane.setConstraints(buttonPayments,     	0, 1 );
		GridPane.setConstraints(overdueButton,  	    1, 0 );
		GridPane.setConstraints(payButton,				2, 0 );
		GridPane.setConstraints(previousYearsButton, 	2, 1 );
		
		layoutOwner.setAlignment(Pos.CENTER);
		layoutOwner.getChildren().addAll(buttonAddProperties, buttonPayments, 
				overdueButton, payButton, previousYearsButton);


		BorderPane borderPane = new BorderPane();
		borderPane.setTop(topMenu);
		borderPane.setCenter(layoutOwner);


		ownerMain = new Scene(borderPane, 600, 400);
		ownerMain.getStylesheets().add(GUI.class.getResource("styles.css").toExternalForm());

		//Button changeTaxes, viewChangeEffects, overdueProperties, propertyTaxOfArea




















		//Department scene
		window.setTitle(title);
		
		GridPane layoutAdmin = new GridPane();
		
		changeTaxes = new Button("Change the taxes");
		changeTaxes.setWrapText(true);
		changeTaxes.prefWidthProperty().bind(Bindings.divide(layoutAdmin.widthProperty(), 3.0));
		changeTaxes.prefHeightProperty().bind(Bindings.divide(layoutAdmin.heightProperty(), 6.0));
		changeTaxes.setOnAction(e -> changeTaxesWindow.display(""));

		viewChangeEffects = new Button("View the effects of changing tax rates");
		viewChangeEffects.setWrapText(true);
		viewChangeEffects.prefWidthProperty().bind(Bindings.divide(layoutAdmin.widthProperty(), 3.0));
		viewChangeEffects.prefHeightProperty().bind(Bindings.divide(layoutAdmin.heightProperty(), 6.0));
		viewChangeEffects.setOnAction(e -> viewChangeEffectsWindow.display("This will be where view the effects of changing tax rates"));

		overdueProperties = new Button("Overdue properties");
		overdueProperties.setWrapText(true);
		overdueProperties.prefWidthProperty().bind(Bindings.divide(layoutAdmin.widthProperty(), 3.0));
		overdueProperties.prefHeightProperty().bind(Bindings.divide(layoutAdmin.heightProperty(), 6.0));
		overdueProperties.setOnAction(e -> OverduePropertiesWindow.display("This will display any overdue properties"));
		
		searchByAddressButton = new Button("Search an address");
		searchByAddressButton.setWrapText(true);
		searchByAddressButton.prefWidthProperty().bind(Bindings.divide(layoutAdmin.widthProperty(), 3.0));
		searchByAddressButton.prefHeightProperty().bind(Bindings.divide(layoutAdmin.heightProperty(), 6.0));
		searchByAddressButton.setOnAction(e -> SearchByAddressWindow.display(""));
				
		
		searchByNameButton = new Button("Search by owner name");
		searchByNameButton.setWrapText(true);
		searchByNameButton.prefWidthProperty().bind(Bindings.divide(layoutAdmin.widthProperty(), 3.0));
		searchByNameButton.prefHeightProperty().bind(Bindings.divide(layoutAdmin.heightProperty(), 6.0));
		searchByNameButton.setOnAction(e -> SearchByNameWindow.display(""));
		 
		
		propertyTaxOfArea = new Button("View the property tax of an area");
		propertyTaxOfArea.setWrapText(true);
		propertyTaxOfArea.prefWidthProperty().bind(Bindings.divide(layoutAdmin.widthProperty(), 3.0));
		propertyTaxOfArea.prefHeightProperty().bind(Bindings.divide(layoutAdmin.heightProperty(), 6.0));
		propertyTaxOfArea.setOnAction(e -> PropertyTaxOfAreaWindow.display());


	//	GridPane layoutAdmin = new GridPane();
		
		layoutAdmin.setPadding(new Insets(20, 20, 20, 20));
		//auto change font size
		fontSize.bind(window.widthProperty().add(window.heightProperty()).divide(80));
		layoutAdmin.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString()));
				
		layoutAdmin.setVgap(8);
		layoutAdmin.setHgap(10);
		
		GridPane.setConstraints(changeTaxes , 			0, 0 );
		GridPane.setConstraints(viewChangeEffects,      0, 1 );
		GridPane.setConstraints(overdueProperties,      1, 0 );
		GridPane.setConstraints(propertyTaxOfArea,		1, 1 );
		GridPane.setConstraints(searchByNameButton,	 	2, 0 );
		GridPane.setConstraints(searchByAddressButton,	2, 1 );

		
		
		layoutAdmin.setAlignment(Pos.CENTER);
		layoutAdmin.getChildren().addAll(changeTaxes, viewChangeEffects, overdueProperties, propertyTaxOfArea,
				searchByNameButton, searchByAddressButton);
		

		


		Button backA = new Button("Back");

		backA.setOnAction(e -> {
			window.setScene(scene1);
		});
	
		HBox topMenuA = new HBox();
		topMenuA.getChildren().add(backA);


		
		
		BorderPane borderPaneA = new BorderPane();
		borderPaneA.setTop(topMenuA);
		borderPaneA.setCenter(layoutAdmin);		

		adminMain = new Scene(borderPaneA, 600, 400);
		adminMain.getStylesheets().add(GUI.class.getResource("styles.css").toExternalForm());


		//default
		window.setScene(scene1);
		window.show();


	}




	/**
	 * @return the ownerNameText
	 */
	public static String getOwnerNameText() {
		return ownerNameText;
	}




}



































