import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
public class GUI extends Application {

	Button buttonPayments, buttonAddProperties, overdueButton, payButton, previousYearsButton, 
	changeTaxes, viewChangeEffects, overdueProperties, propertyTaxOfArea, searchByNameButton,  searchByAddressButton;
	Stage window;
	Scene scene1, ownerMain, adminMain, ownerName;
	private static String ownerNameText;
	private DoubleProperty fontSize = new SimpleDoubleProperty(4);
	String title = "GUI Home screen";


	public static void main(String[] args) {
		launch(args);
	}

	@Override
	/**
	 * Start the program and display the first scene
	 * generate buttons for owner and admin (department of environment)
	 * On owner button click change the scene to get the owners name 
	 * On admin button click change the scene to the admin home screen
	 * 
	 */

	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.setTitle(title);
		window.setMinWidth(600);
		window.setMinHeight(400);



		Label label = new Label();
		label.setText("Would you like to view this program as the Department of Environment or Property Owner");
		label.setMinWidth(Region.USE_PREF_SIZE);

		//create two buttons
		Button owner = new Button("Property Owner");
		Button admin = new Button("Department of Environment");

		//owner button click
		owner.setOnAction(e -> {
			window.setScene(ownerName);
		});

		//admin button click
		admin.setOnAction(e -> {
			window.setScene(adminMain);
		});


		//create a vertical layout 
		VBox layout = new VBox(20);
		layout.getChildren().addAll(label, owner, admin);

		/*
		 * Read the size of the window and then set the variable fontsize 
		 * to change as the window gets bigger or smaller
		 */
		fontSize.bind(window.widthProperty().add(window.heightProperty()).divide(80));
		layout.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString()));


		layout.setPadding(new Insets(10, 10, 10, 10));
		layout.setAlignment(Pos.CENTER);

		scene1 = new Scene(layout, 600, 400);


		/*
		 * Gets the style of the font from the style sheet "styles.css"
		 */
		scene1.getStylesheets().add(GUI.class.getResource("styles.css").toExternalForm());



		/*
		 * This is the owner scene where they will enter their name
		 * this allows the name to be saved so it can be used later on for 
		 * creating a property or for viewing their properties
		 */

		Label ownerLabel = new Label("Who is the owner of this property?");
		TextField ownerNameInput = new TextField();
		ownerNameInput.setPromptText("Owner");

		/*
		 * On button click it will change the scene to the owner home screen
		 * 
		 */
		Button confirm = new Button("Confirm this is your Name");
		confirm.setOnAction(e -> {
			window.setScene(ownerMain);
			ownerNameText = ownerNameInput.getText();
		});

		/*
		 * Creates the layout for them to enter their name
		 */

		VBox layoutOwnerName = new VBox(10);
		layoutOwnerName.getChildren().addAll(label, ownerLabel, ownerNameInput, confirm);
		layoutOwnerName.setAlignment(Pos.CENTER);
		layoutOwnerName.setPadding(new Insets(10, 10, 10, 10));

		ownerName = new Scene(layoutOwnerName, 600, 400);

		/*
		 * Gets the style of the font from the style sheet "styles.css"
		 * 
		 */
		ownerName.getStylesheets().add(GUI.class.getResource("styles.css").toExternalForm());



		/*
		 * Main owner home screen 
		 * this is where they will have the buttons to be able to 
		 * register a property
		 * view their properties
		 * see balancing statements 
		 * Pay for a property
		 * Search by year
		 * 
		 * The layout is created as a grid
		 */
		//Owner Scene

		GridPane layoutOwner = new GridPane();
		/* 
		 *On button click it will open a new window to input  details
		 *to register a new property 
		 */
		buttonAddProperties = new Button("Register Property");
		buttonAddProperties.prefWidthProperty().bind(Bindings.divide(layoutOwner.widthProperty(), 3.0));
		buttonAddProperties.prefHeightProperty().bind(Bindings.divide(layoutOwner.heightProperty(), 6.0));
		buttonAddProperties.setOnAction(e -> AddPropertiesWindow.display());


		/* 
		 *On button click it will open a view the properties owned by the user
		 */
		buttonPayments = new Button("View Properties");
		buttonPayments.prefWidthProperty().bind(Bindings.divide(layoutOwner.widthProperty(), 3.0));
		buttonPayments.prefHeightProperty().bind(Bindings.divide(layoutOwner.heightProperty(), 6.0));
		buttonPayments.setOnAction(e -> ViewPropertiesWindow.display());


		/*
		 * On button click it will open a new window to view a balancing statement
		 * 
		 */
		overdueButton = new Button("Balancing Statement");
		overdueButton.prefWidthProperty().bind(Bindings.divide(layoutOwner.widthProperty(), 3.0));
		overdueButton.prefHeightProperty().bind(Bindings.divide(layoutOwner.heightProperty(), 6.0));
		overdueButton.setOnAction(e -> BalancingStatementWindow.display());


		/*
		 * On button click it will create a new window to input
		 * details to pay for a property
		 */
		payButton = new Button("Make a Payment");
		payButton.prefWidthProperty().bind(Bindings.divide(layoutOwner.widthProperty(), 3.0));
		payButton.prefHeightProperty().bind(Bindings.divide(layoutOwner.heightProperty(), 6.0));
		payButton.setOnAction(e -> PayWindow.display());


		/*
		 * On button click will open a new window to allow the user to search 
		 * their properties by year
		 */
		previousYearsButton = new Button("Search by Year");
		previousYearsButton.prefWidthProperty().bind(Bindings.divide(layoutOwner.widthProperty(), 3.0));
		previousYearsButton.prefHeightProperty().bind(Bindings.divide(layoutOwner.heightProperty(), 6.0));
		previousYearsButton.setOnAction(e -> PreviousYearsWindow.display());


		/*
		 * On button click returns to home screen
		 * 
		 */
		Button back = new Button("Back");
		back.setOnAction(e -> window.setScene(scene1));


		/*
		 * Creates the back button on the top left of the screen 
		 */
		HBox topMenu = new HBox();
		topMenu.getChildren().add(back);

		//set an edge around the screen 
		layoutOwner.setPadding(new Insets(10, 10, 10, 10));

		/*
		 * Read the size of the window and then set the variable fontsize 
		 * to change as the window gets bigger or smaller
		 */
		fontSize.bind(window.widthProperty().add(window.heightProperty()).divide(80));
		layoutOwner.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString()));

		//set gap between between buttons
		layoutOwner.setVgap(8);
		layoutOwner.setHgap(10);

		/*
		 * position of buttons
		 */
		GridPane.setConstraints(buttonAddProperties, 	0, 0 );
		GridPane.setConstraints(buttonPayments,     	0, 1 );
		GridPane.setConstraints(overdueButton,  	    1, 0 );
		GridPane.setConstraints(payButton,				2, 0 );
		GridPane.setConstraints(previousYearsButton, 	2, 1 );

		//set the buttons to be centered
		layoutOwner.setAlignment(Pos.CENTER);
		layoutOwner.getChildren().addAll(buttonAddProperties, buttonPayments, 
				overdueButton, payButton, previousYearsButton);



		BorderPane borderPane = new BorderPane();
		borderPane.setTop(topMenu);
		borderPane.setCenter(layoutOwner);

		//displays the scene
		ownerMain = new Scene(borderPane, 600, 400);

		/*
		 * Gets the style of the font from the style sheet "styles.css"
		 * 
		 */
		ownerMain.getStylesheets().add(GUI.class.getResource("styles.css").toExternalForm());








		//Department scene
		/*
		 * Main department scene this is where they will have options t 
		 * Change the taxes of an area or price bracket
		 * View the effects of making changes to an area 
		 * See properties that are overdue by year or eir code
		 * search for an address to see the details of the property at that area
		 * search an owner name to see his properties 
		 * view the current property tax of an area 
		 * 
		 */


		GridPane layoutAdmin = new GridPane();

		/*
		 * Sets the size of the button to always fit comfortably in the layout window
		 * On button click opens a window to change the taxes
		 * 
		 */
		changeTaxes = new Button("Change the Taxes");
		changeTaxes.setWrapText(true);
		changeTaxes.prefWidthProperty().bind(Bindings.divide(layoutAdmin.widthProperty(), 3.0));
		changeTaxes.prefHeightProperty().bind(Bindings.divide(layoutAdmin.heightProperty(), 6.0));
		changeTaxes.setOnAction(e -> changeTaxesWindow.display());


		/*
		 * Sets the size of the button to always fit comfortably in the layout window
		 * On button click opens a window to change the taxes
		 * 
		 */
		viewChangeEffects = new Button("View the Effects of Changing Tax Rates");
		viewChangeEffects.setWrapText(true);
		viewChangeEffects.prefWidthProperty().bind(Bindings.divide(layoutAdmin.widthProperty(), 3.0));
		viewChangeEffects.prefHeightProperty().bind(Bindings.divide(layoutAdmin.heightProperty(), 6.0));
		viewChangeEffects.setOnAction(e -> viewChangeEffectsWindow.display());


		/*
		 * Sets the size of the button to always fit comfortably in the layout window
		 * On button click opens a window view properties that are overdue
		 * 
		 */
		overdueProperties = new Button("Overdue Properties");
		overdueProperties.setWrapText(true);
		overdueProperties.prefWidthProperty().bind(Bindings.divide(layoutAdmin.widthProperty(), 3.0));
		overdueProperties.prefHeightProperty().bind(Bindings.divide(layoutAdmin.heightProperty(), 6.0));
		overdueProperties.setOnAction(e -> OverduePropertiesWindow.display());


		/*
		 * Sets the size of the button to always fit comfortably in the layout window
		 * On button click opens a window to search for properties at an address
		 * 
		 */
		searchByAddressButton = new Button("Search an Address");
		searchByAddressButton.setWrapText(true);
		searchByAddressButton.prefWidthProperty().bind(Bindings.divide(layoutAdmin.widthProperty(), 3.0));
		searchByAddressButton.prefHeightProperty().bind(Bindings.divide(layoutAdmin.heightProperty(), 6.0));
		searchByAddressButton.setOnAction(e -> SearchByAddressWindow.display(""));


		/*
		 * Sets the size of the button to always fit comfortably in the layout window
		 * On button click opens a window search for properties by owner name
		 * 
		 */
		searchByNameButton = new Button("Search by Owner Name");
		searchByNameButton.setWrapText(true);
		searchByNameButton.prefWidthProperty().bind(Bindings.divide(layoutAdmin.widthProperty(), 3.0));
		searchByNameButton.prefHeightProperty().bind(Bindings.divide(layoutAdmin.heightProperty(), 6.0));
		searchByNameButton.setOnAction(e -> SearchByNameWindow.display("")); 


		/*
		 * Sets the size of the button to always fit comfortably in the layout window
		 * On button click opens a window to view the property tax of an area
		 * 
		 */
		propertyTaxOfArea = new Button("View the Property Tax of an Area");
		propertyTaxOfArea.setWrapText(true);
		propertyTaxOfArea.prefWidthProperty().bind(Bindings.divide(layoutAdmin.widthProperty(), 3.0));
		propertyTaxOfArea.prefHeightProperty().bind(Bindings.divide(layoutAdmin.heightProperty(), 6.0));
		propertyTaxOfArea.setOnAction(e -> PropertyTaxOfAreaWindow.display());



		layoutAdmin.setPadding(new Insets(20, 20, 20, 20));

		/*
		 * Read the size of the window and then set the variable fontsize 
		 * to change as the window gets bigger or smaller
		 */
		fontSize.bind(window.widthProperty().add(window.heightProperty()).divide(80));
		layoutAdmin.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString()));

		/*
		 * Set gaps between each grid position
		 */
		layoutAdmin.setVgap(8);
		layoutAdmin.setHgap(10);

		/*
		 * sets position of buttons on the grid
		 */
		GridPane.setConstraints(changeTaxes , 			0, 0 );
		GridPane.setConstraints(viewChangeEffects,      0, 1 );
		GridPane.setConstraints(overdueProperties,      1, 0 );
		GridPane.setConstraints(propertyTaxOfArea,		1, 1 );
		GridPane.setConstraints(searchByNameButton,	 	2, 0 );
		GridPane.setConstraints(searchByAddressButton,	2, 1 );

		/*
		 * adds all buttons to the window and sets the position of them to be centered
		 */
		layoutAdmin.setAlignment(Pos.CENTER);
		layoutAdmin.getChildren().addAll(changeTaxes, viewChangeEffects, overdueProperties, propertyTaxOfArea,
				searchByNameButton, searchByAddressButton);

		/*
		 * creates back button in the top left 
		 */
		Button backA = new Button("Back");
		backA.setOnAction(e -> window.setScene(scene1));	
		HBox topMenuA = new HBox();
		topMenuA.getChildren().add(backA);

		/*
		 * adds the buttons and back buttons to the scene
		 */
		BorderPane borderPaneA = new BorderPane();
		borderPaneA.setTop(topMenuA);
		borderPaneA.setCenter(layoutAdmin);		
		/*
		 * sets the scene
		 */
		adminMain = new Scene(borderPaneA, 600, 400);

		/*
		 * Gets the style of the font from the style sheet "styles.css"
		 * 
		 */
		adminMain.getStylesheets().add(GUI.class.getResource("styles.css").toExternalForm());

		//default
		/*
		 * Set the first scene to be shown scene1 
		 * where you select the admin or owner
		 */
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