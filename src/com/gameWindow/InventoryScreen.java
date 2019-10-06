package com.gameWindow;

import com.gameMechanics.Ship;
import com.trade.TradeGood;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class InventoryScreen {

	static Font pixelFont= Font.loadFont("file:resources/fonts/pixel_text.ttf", 20);
	ImageView crate_image;
	ImageView good_image;
	Text good_image_count;
	Ship playerShip;

	public InventoryScreen(Ship playerShip) {
		this.playerShip = playerShip;
	}

	// Returns the inventory Scene
	public Scene getInventoryScene() {

		BorderPane root = new BorderPane();
		root.setMaxSize(1000, 700);

		StackPane basicShipInfoPane = new StackPane();
		basicShipInfoPane.setPrefSize(650, 700);

		StackPane tabPaneWithBG = new StackPane();
		tabPaneWithBG.setPrefSize(350, 700);
		ImageView parchment_background = new ImageView("file:resources/images/parch_bg.jpg"); 
		TabPane tabbedInventoryPane = new TabPane();
		tabbedInventoryPane.setPrefSize(350, 700);
		tabPaneWithBG.getChildren().addAll(parchment_background, tabbedInventoryPane);

		Tab shipInfoTab = new Tab();
		shipInfoTab.setText("Ship Information");

		Tab inventoryTab = new Tab();
		inventoryTab.setText("Cargo Hold");

		GridPane inventoryPane = new GridPane();
		inventoryPane.setPrefSize(350, 700);

		// Define row and column constraints
		RowConstraints rc = new RowConstraints();
		rc.setPercentHeight(100/((inventoryPane.getPrefHeight()/100) - 1));
		System.out.println("Percent height" + rc.getPercentHeight());
		ColumnConstraints cc = new ColumnConstraints();
		cc.setPercentWidth(100/((inventoryPane.getPrefWidth() - 50)/100));
		System.out.println("Percent width" + cc.getPercentWidth());

		// Set vertical and horizontal gap between cells
		inventoryPane.setVgap(12.5);
		inventoryPane.setHgap(12.5);
		inventoryPane.setPadding(new Insets(12.5, 12.5, 12.5, 12.5));

		// Create arrays and set RowConstraints and ColumnConstraints
		RowConstraints[] gridRC = new RowConstraints[((int)inventoryPane.getPrefHeight()/100) -1];
		System.out.println(gridRC.length);
		System.out.println(inventoryPane.getPrefHeight() + " " + inventoryPane.getPrefWidth());
		ColumnConstraints[] gridCC = new ColumnConstraints[(int)inventoryPane.getPrefWidth()/100];
		System.out.println(gridCC.length);
		System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------");
		for(int i = 0; i < gridRC.length; i++) {
			System.out.println("Row Index " + i + ":");
			gridRC[i] = rc;
			System.out.println(gridRC[i]);
		}
		System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------");
		for(int i = 0; i < gridCC.length; i++) {
			System.out.println("Column Index " + i + ":");
			gridCC[i] = cc;
			System.out.println(gridCC[i]);
		}
		System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------");
		inventoryPane.getRowConstraints().addAll(gridRC);
		inventoryPane.getColumnConstraints().addAll(gridCC);

		// Calculate max values based on number of items in the Ship's cargo
		int numCargo = playerShip.getCargo().length;
		int xMax = (numCargo >= 3) ? 3 : numCargo;
		int yMax = (numCargo % 3 == 0) ? (numCargo/3) : (numCargo/3)+1;
		
		// Get items from Player's Ship and add it to the inventory screen
		for(int y = 0; y < yMax; y++) {
			for(int x = 0; x < xMax; x++) {
				if(x+3*y <= numCargo-1) {
					inventoryPane.add(getItemAsStack(playerShip.getCargo()[x+(3*y)]), x, y);
				}
			}
		}
		inventoryTab.setContent(inventoryPane);

		Tab questsTab = new Tab();
		questsTab.setText("Quests");
		TilePane questsPane = new TilePane();
		questsPane.setPrefSize(350, 700);

		Tab keyItemsTab = new Tab();
		keyItemsTab.setText("Key Items");
		TilePane keyItemsPane = new TilePane();
		keyItemsPane.setPrefSize(350, 700);

		tabbedInventoryPane.getTabs().addAll(shipInfoTab, inventoryTab);
//		tabbedInventoryPane.getTabs().addAll(shipInfoTab, inventoryTab, questsTab, keyItemsTab);
		StackPane shipInfoStack = new StackPane();
		basicShipInfoPane.getChildren().add(shipInfoStack);
		basicShipInfoPane.getChildren().add(new ImageView(playerShip.getShipSprite()));
		basicShipInfoPane.setAlignment(Pos.CENTER);
		shipInfoStack.getChildren().add(new ImageView("file:resources/images/inv_bg.jpg"));
		root.setLeft(basicShipInfoPane);
		root.setRight(tabPaneWithBG);


		Scene inventoryScene = new Scene(root);
		return inventoryScene;
	}

	public static StackPane getItemAsStack(TradeGood itemGood) {

		StackPane imageQuantityStack = new StackPane();
		ImageView crate_image = new ImageView("file:resources/images/crate.png");
		ImageView good_image = new ImageView(itemGood.getImagePath());
		Text good_image_count = new Text(itemGood.getQuantity()+"");

		good_image_count.setFont(pixelFont);
		good_image_count.setFill(Color.WHITE);
		good_image_count.setTranslateX(30);
		good_image_count.setTranslateY(35);
		imageQuantityStack.getChildren().addAll(crate_image, good_image, good_image_count);

		return imageQuantityStack;
	}
}
