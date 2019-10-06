package com.gameWindow;

import com.gameMechanics.SHIP_TYPE;
import com.gameMechanics.Ship;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainMenuScreen{

	Font edwardFont = Font.loadFont("file:resources/fonts/ITCEDSCR.ttf", 35);
	Button newGameButton;
	Button loadGameButton;
	Button optionsButton;
	Button exitGameButton;
	Button returnToMenuButton;
	Text titleText;
	Text risingText;
	Text tideText;
	String descEarthTrackPath = "resources/audio/Descending Earths.wav";
	String floatyTrackPath = "resources/audio/Floaty.wav";

	//Opens main menu
	public Scene getMainMenu() {
		// Set up main menu nodes and styles
		ImageView backgroundPic = new ImageView("file:resources/images/ocean.jpg");
		newGameButton = new Button("New Game");
		newGameButton.setPrefSize(200, 75);
		newGameButton.setFont(edwardFont);
		newGameButton.setOnAction(mainMenuButtonHandler);
		newGameButton.setLayoutX(80);
		newGameButton.setLayoutY(345);

		loadGameButton = new Button("Load Game");
		loadGameButton.setPrefSize(200, 75);
		loadGameButton.setFont(edwardFont);
		loadGameButton.setLayoutX(80);
		loadGameButton.setLayoutY(445);
		loadGameButton.setOnAction(mainMenuButtonHandler);

		optionsButton = new Button("Options");
		optionsButton.setPrefSize(200, 75);
		optionsButton.setFont(edwardFont);
		optionsButton.setLayoutX(80);
		optionsButton.setLayoutY(545);
		optionsButton.setOnAction(mainMenuButtonHandler);

		exitGameButton = new Button("Exit Game");
		exitGameButton.setPrefSize(200, 75);
		exitGameButton.setFont(edwardFont);
		exitGameButton.setLayoutX(750);
		exitGameButton.setLayoutY(575);
		exitGameButton.setOnAction(mainMenuButtonHandler);

		risingText = new Text(200, 250, "Rising");
		risingText.setStyle("-fx-font: 150px Vivaldi; -fx-fill: linear-gradient(from 0% 0% to 100% 200%, repeat, aqua 0%, blue 50%);-fx-stroke: black;-fx-stroke-width: 1;");
		tideText = new Text(350, 420, "Tide");
		tideText.setStyle("-fx-font: 150px Vivaldi; -fx-fill: linear-gradient(from 0% 0% to 100% 200%, repeat, aqua 0%, blue 50%);-fx-stroke: black;-fx-stroke-width: 1;");

		// Add Nodes and apply stylesheet to scene;
		Pane mainMenuPane = new Pane(); 
		mainMenuPane.getChildren().addAll(backgroundPic, newGameButton, loadGameButton, optionsButton, exitGameButton, risingText, tideText);
		Scene mainMenuScene = new Scene(mainMenuPane);
		mainMenuScene.getStylesheets().clear();
		mainMenuScene.getStylesheets().add("file:resources/stylesheet/main_menu.css");
		return mainMenuScene;
	}

	EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent keyInput) {
			System.out.println("Mouse Entered Scene");
		}
	};
	
	// Starts new game
	public Scene getMapScene(){

		GridPane mapPane = new GridPane();
		mapPane.setPrefSize(1000, 700);
		RowConstraints rc = new RowConstraints();
		rc.setPercentHeight(100/(mapPane.getPrefHeight()/50));
		ColumnConstraints cc = new ColumnConstraints();
		cc.setPercentWidth(100/(mapPane.getPrefWidth()/50));

		// Set vertical and horizontal gap between cells
		mapPane.setVgap(0);
		mapPane.setHgap(0);

		// Create arrays and set RowConstraints and ColumnConstraints
		RowConstraints[] gridRC = new RowConstraints[(int)mapPane.getPrefHeight()/50];
		System.out.println(mapPane.getPrefHeight() + " " + mapPane.getPrefWidth());
		ColumnConstraints[] gridCC = new ColumnConstraints[(int)mapPane.getPrefWidth()/50];
		for(int i = 0; i < gridCC.length; i++) {
			System.out.println(i);
			if(i < 14) {
				gridRC[i] = rc;
				System.out.println(gridRC[i]);
			}
			gridCC[i] = cc;
			System.out.println(gridCC[i]);
		}
		mapPane.getRowConstraints().addAll(gridRC);
		mapPane.getColumnConstraints().addAll(gridCC);

		Image animated_ocean = new Image("file:resources/images/ocean.gif");
		Image grass_tile = new Image("file:resources/images/grass_tile.png");
		for(int y = 0; y < gridRC.length; y++) {
			for(int x = 0; x < gridCC.length; x++) {

				// Create and add a StackPane to each cell in the GridPane to layer Nodes on top of each other 
				StackPane image_stack = new StackPane();
				mapPane.add(image_stack,x, y);

				Label label = new Label(x+y+(19*y)+"");
				label.setFont(new Font("Arial", 25));

				image_stack.getChildren().add(new ImageView(animated_ocean));
				if(x == 0 || x == 1 || x == 2 || x == 3 || x == 4 || x == 5) {
					image_stack.getChildren().add(new ImageView(grass_tile));
				}
				image_stack.getChildren().add(label);

			}
		}

		Scene mapScene = new Scene(mapPane);
		mapScene.setOnMouseEntered(mouseHandler);
		return mapScene;
	}

	// Opens options menu
	public Scene getOptionsMenu() {

		returnToMenuButton = new Button("Back");
		returnToMenuButton.setPrefSize(200, 75);
		returnToMenuButton.setFont(edwardFont);
		returnToMenuButton.setLayoutX(750);
		returnToMenuButton.setLayoutY(575);
		returnToMenuButton.setOnAction(mainMenuButtonHandler);

		Pane optionsMenu = new Pane();
		optionsMenu.getChildren().addAll(new ImageView("file:resources/images/ocean.jpg"), returnToMenuButton);
		Scene optionsMenuScene = new Scene(optionsMenu);
		optionsMenuScene.getStylesheets().clear();
		optionsMenuScene.getStylesheets().add("file:resources/stylesheet/main_menu.css");
		((Stage) exitGameButton.getScene().getWindow()).setScene(optionsMenuScene);

		return optionsMenuScene;
	}

	EventHandler<ActionEvent> mainMenuButtonHandler = new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent e) {
			System.out.println(((Button)e.getSource()).getText() + " Button was pressed");

			if(e.getSource() == exitGameButton) {
				System.out.println("Exit button route");
				((Stage) exitGameButton.getScene().getWindow()).close();
				System.out.println("Game Window Closed");
			}
			else if(e.getSource() == newGameButton) {
				
				System.out.println("Starting new game...");
				
				Ship cruiser = new Ship(SHIP_TYPE.FRIGATE, 100, 10, 24, 6, 4, 10);
				int numDataFields = 20;
				String[] gamedata = new String[numDataFields];
				
				// Save data
				gamedata[0] = cruiser.getShipType()+"";
				gamedata[1] = cruiser.getMaxHealth()+"";
				gamedata[2] = cruiser.getMaxArmor()+"";
				gamedata[3] = cruiser.getSpeed()+"";
				gamedata[4] = cruiser.getNumCannons()+"";
				gamedata[5] = cruiser.getCrewCap()+"";
				
				// Save data on members of the crew
				for(int i = 0; i < 4; i++) {
					
					gamedata[i+6] = null+"";
					if(cruiser.getCrew()[i] != null) {
						System.out.println("in loop");
					gamedata[i+6] = cruiser.getCrew()[i]+"";
					}
				}

				// Set unused fields as null for now
				for(int j = 10; j < 20; j++) {
					gamedata[j] = null+"";
				}
				
				System.out.println("------------------------------------------------------");
				Rising_Tide.saveData(gamedata, "text.txt");
				
				// Sets scene to map for now
//				((Stage) exitGameButton.getScene().getWindow()).setScene(getMapScene());
				((Stage) exitGameButton.getScene().getWindow()).setScene(new WorldMapScreen().getWorldMapScreen());
			}
			else if(e.getSource() == loadGameButton) {
				
				// ind0: ShipType
				// ind1: Max Health
				// ind2: Max Armor
				// ind3: Speed
				// ind4: Number of Cannons
				// ind5: Crew Capacity
				// ind6: Crew Member #1
				// ind7: Crew Member #2
				// ind8: Crew Member #3
				// ind9: Crew Member #4
				// ind10-19: unused
				String[] data = Rising_Tide.loadData("text.txt");
				
				Ship dataShip = new Ship(SHIP_TYPE.valueOf(data[0]), Double.parseDouble(data[1]), Double.parseDouble(data[2]), (int)Double.parseDouble(data[3]), (int)Double.parseDouble(data[4]), (int)Double.parseDouble(data[5]),  (int)Double.parseDouble(data[6]));
				System.out.println(dataShip);
				InventoryScreen invscrn = new InventoryScreen(dataShip);
				((Stage) loadGameButton.getScene().getWindow()).setScene(invscrn.getInventoryScene());
				System.out.println("File loaded");
			}
			else if(e.getSource() == optionsButton) {
				((Stage) optionsButton.getScene().getWindow()).setScene(getOptionsMenu());
				System.out.println("Options Menu opened");
			}
			else if(e.getSource() == returnToMenuButton) {
				((Stage) returnToMenuButton.getScene().getWindow()).setScene(getMainMenu());
				System.out.println("Main Menu opened");
			}

		}
	}; 

	public Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
		for (Node node : gridPane.getChildren()) {
			if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
				return node;
			}
		}
		return null;
	}

	public Node removeNodeFromGridPane(GridPane gridPane, int columnIndex, int rowIndex) {
		Node deletedNode = getNodeFromGridPane(gridPane, columnIndex, rowIndex);

		gridPane.getChildren().remove((18*rowIndex)+(columnIndex+rowIndex));
		return deletedNode;

	}

}
