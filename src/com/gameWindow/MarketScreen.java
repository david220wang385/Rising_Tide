package com.gameWindow;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import com.gameMechanics.Ship;
import com.trade.TradeGood;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MarketScreen {

	Font pixelFont = Font.loadFont("file:resources/fonts/pixel_text.ttf", 30);
	DropShadow shadow = new DropShadow();
	ArrayList<TradeGood> shopInventory;
	TabPane tabbedInventoryPane;
	Ship playerShip;
	Button buyButton;
	Button sellButton;
	Text buyTotalPrice;
	Text sellTotalPrice;
	ArrayList<TradeGood> shoppingCart;
	double totalBuyPrice = 0;
	double totalSellPrice = 0;
	int numCargo;
	int playGridXMax;
	int playGridYMax;
	GridPane shopInvGrid = new GridPane();
	GridPane playerInvGrid = new GridPane();
	DecimalFormat dFormat = new DecimalFormat("0.00");
	InnerShadow glow = new InnerShadow();

	// Randomly generate a number from 3-12
	int numToBuy = ThreadLocalRandom.current().nextInt(3, 13);

	// Button effects for item buttons for shop and inv
	EventHandler<MouseEvent> shopEnterShadow = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent event) {

			if(((Button)event.getSource()).getEffect() != glow) {
				((Button)event.getSource()).setEffect(shadow);
			}

			Text goodPrice = new Text();
			goodPrice.setFill(Color.GOLD);
			goodPrice.setId("Price");
			goodPrice.setText(""+shopInventory.get(GridPane.getColumnIndex(((Button)event.getSource()))+(4*GridPane.getRowIndex(((Button)event.getSource())))).getInitialBuyPrice());
			StackPane priceStack = new StackPane();
			priceStack.getChildren().addAll(((Button)event.getSource()).getGraphic(), goodPrice);
			((Button)event.getSource()).setGraphic(priceStack);
		}
	};
	EventHandler<MouseEvent> shopLeaveReset = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent event) {
			if(((Button)event.getSource()).getEffect() != glow) {
				((Button)event.getSource()).setEffect(null);
				((Button)event.getSource()).setGraphic(InventoryScreen.getItemAsStack(shopInventory.get(GridPane.getColumnIndex(((Button)event.getSource()))+(4*GridPane.getRowIndex(((Button)event.getSource()))))));
			}
		}
	};
	EventHandler<MouseEvent> invEnterShadow = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent event) {

			if(((Button)event.getSource()).getEffect() != glow) {
				((Button)event.getSource()).setEffect(shadow);
			}

			Text goodPrice = new Text();
			goodPrice.setFill(Color.GOLD);
			goodPrice.setId("Price");
			goodPrice.setText(""+playerShip.getCargo()[GridPane.getColumnIndex(((Button)event.getSource()))+(4*GridPane.getRowIndex(((Button)event.getSource())))].getInitialBuyPrice());
			StackPane priceStack = new StackPane();
			priceStack.getChildren().addAll(((Button)event.getSource()).getGraphic(), goodPrice);
			((Button)event.getSource()).setGraphic(priceStack);
		}
	};
	EventHandler<MouseEvent> invLeaveReset = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent event) {
			if(((Button)event.getSource()).getEffect() != glow) {
				((Button)event.getSource()).setEffect(null);
				((Button)event.getSource()).setGraphic(InventoryScreen.getItemAsStack(playerShip.getCargo()[GridPane.getColumnIndex(((Button)event.getSource()))+(4*GridPane.getRowIndex(((Button)event.getSource())))]));
			}
		}
	};
	// Select and add item to a shopping cart
	EventHandler<ActionEvent> itemSelect = new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent press) {

			// If item already selected, then remove from shoppingCart ArrayList
			// Also update the price labels
			if((shoppingCart.contains(((ItemButton)press.getSource()).getTradeGood()))) {
				shoppingCart.remove(((ItemButton)press.getSource()).getTradeGood());

				// If first tab is selected, update price on buy label
				if(tabbedInventoryPane.getSelectionModel().getSelectedIndex() == 0) {
					updateTotalBuyPrice(totalBuyPrice -= (((ItemButton)press.getSource()).getTradeGood().getInitialBuyPrice()*((ItemButton)press.getSource()).getTradeGood().getQuantity()));
				}

				// If second tab is selected, update price on sell label
				if(tabbedInventoryPane.getSelectionModel().getSelectedIndex() == 1) {
					updateTotalSellPrice(totalSellPrice -= (((ItemButton)press.getSource()).getTradeGood().getInitialBuyPrice()*((ItemButton)press.getSource()).getTradeGood().getQuantity()));
				}

				((ItemButton)press.getSource()).setEffect(null);
				System.out.println("Removed from cart");
			}

			// Otherwise add the TradeGood to the shoppingCart
			// And add selected item effect 
			else {
				shoppingCart.add(((ItemButton)press.getSource()).getTradeGood());

				// If first tab is selected, update price on buy label
				if(tabbedInventoryPane.getSelectionModel().getSelectedIndex() == 0) {
					updateTotalBuyPrice(totalBuyPrice += (((ItemButton)press.getSource()).getTradeGood().getInitialBuyPrice()*((ItemButton)press.getSource()).getTradeGood().getQuantity()));
				}

				// If second tab is selected, update price on sell label
				if(tabbedInventoryPane.getSelectionModel().getSelectedIndex() == 1) {
					updateTotalSellPrice(totalSellPrice += (((ItemButton)press.getSource()).getTradeGood().getInitialBuyPrice()*((ItemButton)press.getSource()).getTradeGood().getQuantity()));
				}
				((ItemButton)press.getSource()).setEffect(glow);
				System.out.println("Added to cart");
			}
		}
	};

	public MarketScreen(Ship playerShip) {
		glow.setColor(Color.AQUAMARINE);
		this.playerShip = playerShip;
		shoppingCart = new ArrayList<TradeGood>(playerShip.getCargoCap());
	}

	public Scene getMarketScene() {

		BorderPane root = new BorderPane();
		root.setMaxSize(1000, 700);

		Pane basicShipInfoPane = new Pane();
		basicShipInfoPane.setPrefSize(500, 700);

		StackPane tabPaneWithBG = new StackPane();
		tabPaneWithBG.setPrefSize(500, 700);
		tabbedInventoryPane = new TabPane();
		tabbedInventoryPane.setPrefSize(500, 700);
		tabbedInventoryPane.setId("TabPane");
		tabPaneWithBG.getChildren().addAll(tabbedInventoryPane);

		// Setup bottom HBox of Buy Tab
		Tab buyGoodsTab = new Tab();
		buyGoodsTab.setText("Buy Goods");
		BorderPane buyPane = new BorderPane();
		HBox buyTotalAndButton = new HBox(50);
		buyTotalPrice = new Text("Total: " + totalBuyPrice +"\nDucats: " + playerShip.getMoney());
		buyTotalPrice.setTranslateX(25);
		buyTotalPrice.setFont(pixelFont);
		Region spacing1 = new Region();
		HBox.setHgrow(spacing1, Priority.ALWAYS);
		buyButton = new Button("Buy");
		buyButton.setId("TransactBtn");
		buyButton.setPrefSize(100, 100);
		buyButton.setTranslateX(-25);
		buyButton.setTranslateY(-25);
		buyTotalAndButton.getChildren().addAll(buyTotalPrice, spacing1, buyButton);
		buyPane.setBottom(buyTotalAndButton);

		// Setup bottom HBox of Sell Tab
		Tab sellGoodsTab = new Tab();
		sellGoodsTab.setText("Sell Goods");
		BorderPane sellPane = new BorderPane();
		HBox sellTotalAndButton = new HBox(50);
		sellTotalPrice = new Text("Total: " + totalSellPrice +"\nDucats: " + playerShip.getMoney());
		sellTotalPrice.setTranslateX(25);
		sellTotalPrice.setFont(pixelFont);
		Region spacing2 = new Region();
		HBox.setHgrow(spacing2, Priority.ALWAYS);
		sellButton = new Button("Sell");
		sellButton.setId("TransactBtn");
		sellButton.setPrefSize(100, 100);
		sellButton.setTranslateX(-25);
		sellButton.setTranslateY(-25);
		sellTotalAndButton.getChildren().addAll(sellTotalPrice, spacing2, sellButton);
		sellPane.setBottom(sellTotalAndButton);

		// Give the buttons a dropshadow when they hover for the buy/sell buttons
		EventHandler<MouseEvent> transactEnterShadow= new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				((Button)event.getSource()).setEffect(shadow);
			}
		};
		EventHandler<MouseEvent> transactLeaveReset = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				((Button)event.getSource()).setEffect(null);
			}
		};

		// Handle what happens when buy/sell are pressed
		EventHandler<ActionEvent> finishTransactHandler = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				// If first tab is selected, finish buy transaction
				if(tabbedInventoryPane.getSelectionModel().getSelectedIndex() == 0) {
					finishBuyTransact();
				}

				// If second tab is selected, finish sell transaction
				if(tabbedInventoryPane.getSelectionModel().getSelectedIndex() == 1) {
					finishSellTransact();
				}

			}
		};

		buyButton.setOnMouseEntered(transactEnterShadow);
		buyButton.setOnMouseExited(transactLeaveReset);
		buyButton.setOnAction(finishTransactHandler);
		sellButton.setOnMouseEntered(transactEnterShadow);
		sellButton.setOnMouseExited(transactLeaveReset);
		sellButton.setOnAction(finishTransactHandler);

		shopInvGrid.setPrefSize(400, 500);
		playerInvGrid.setPrefSize(400, 500);

		// Define row and column constraints
		RowConstraints rc = new RowConstraints();
		rc.setPercentHeight(100/((shopInvGrid.getPrefHeight()/100) - 1));
		System.out.println("Percent height" + rc.getPercentHeight());
		ColumnConstraints cc = new ColumnConstraints();
		cc.setPercentWidth(100/((shopInvGrid.getPrefWidth() - 50)/100));
		System.out.println("Percent width" + cc.getPercentWidth());

		// Create arrays and set RowConstraints and ColumnConstraints
		RowConstraints[] gridRC = new RowConstraints[((int)shopInvGrid.getPrefHeight()/100) -1];
		System.out.println(gridRC.length);
		System.out.println(shopInvGrid.getPrefHeight() + " " + shopInvGrid.getPrefWidth());
		ColumnConstraints[] gridCC = new ColumnConstraints[(int)shopInvGrid.getPrefWidth()/100];
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

		shopInvGrid.getRowConstraints().addAll(gridRC);
		shopInvGrid.getColumnConstraints().addAll(gridCC);
		shopInvGrid.setVgap(12.5);
		shopInvGrid.setHgap(5);
		shopInvGrid.setPadding(new Insets(10,10, 10, 10));

		playerInvGrid.getRowConstraints().addAll(gridRC);
		playerInvGrid.getColumnConstraints().addAll(gridCC);
		playerInvGrid.setVgap(12.5);
		playerInvGrid.setHgap(5);
		playerInvGrid.setPadding(new Insets(10,10, 10, 10));

		// Randomly generate a number from 3-12
		System.out.println("Number of items to buy:" + numToBuy);

		// Add random goods to the shop's inventory
		shopInventory = new ArrayList<TradeGood>(numToBuy);
		for(int i = 0; i < numToBuy; i++) {
			shopInventory.add(TradeGood.getRandomTradeGood());
		}

		for(TradeGood e: shopInventory) {
			System.out.println(e);
		}

		updateShopPane();
		updateInventoryPane();

		System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------");
		for(TradeGood e: playerShip.getCargo()) {
			System.out.println(e);
		}

		// Finalize content for the Tabs of the TabPane
		BorderPane buyPaneBox = new BorderPane();
		buyPaneBox .setTop(shopInvGrid);
		buyPaneBox.setBottom(buyPane);
		BorderPane sellPaneBox = new BorderPane();
		sellPaneBox.setTop(playerInvGrid);
		sellPaneBox.setBottom(sellPane);

		// Finalize and publish content to the screen
		buyGoodsTab.setContent(buyPaneBox);
		sellGoodsTab.setContent(sellPaneBox);

		tabbedInventoryPane.getTabs().addAll(buyGoodsTab, sellGoodsTab);
		StackPane leftSide = new StackPane();
		basicShipInfoPane.getChildren().add(leftSide);
		leftSide.getChildren().add(new ImageView("file:resources/images/inv_bg.jpg"));
		root.setLeft(basicShipInfoPane);
		root.setRight(tabPaneWithBG);
		root.getStylesheets().clear();
		root.getStylesheets().add("file:resources/stylesheet/shop_screen.css");

		Scene inventoryScene = new Scene(root);
		return inventoryScene;
	}

	public void updateTotalBuyPrice(double newPrice) {
		totalBuyPrice = newPrice;
		buyTotalPrice.setText("Total: " + dFormat.format(totalBuyPrice) +"\nDucats: " + dFormat.format(playerShip.getMoney()));
	}

	public void updateTotalSellPrice(double newPrice) {
		totalSellPrice = newPrice;
		sellTotalPrice.setText("Total: " + dFormat.format(totalSellPrice ) +"\nDucats: " + dFormat.format(playerShip.getMoney()));
	}

	public void updatePlayerBalance() {

		if(tabbedInventoryPane.getSelectionModel().getSelectedIndex() == 0) {
			buyTotalPrice.setText("Total: " + 0 +"\nDucats: " + dFormat.format(playerShip.getMoney()));
		}

		if(tabbedInventoryPane.getSelectionModel().getSelectedIndex() == 1) {
			sellTotalPrice.setText("Total: " + 0 +"\nDucats: " + dFormat.format(playerShip.getMoney()));
		}
	}

	public void updateShopPane() {

		// Update calculations on shop tab
		int shopGridXMax = (numToBuy >= 4) ? 4 : numToBuy;
		int shopGridYMax = (numToBuy % 4 == 0) ? (numToBuy) : (numToBuy/4)+1;

		System.out.println(numToBuy);
		
		// Display the shop's goods to the screen
		for(int y = 0; y < shopGridYMax; y++) {
			for(int x = 0; x < shopGridXMax; x++) {
				if(x+4*y <= numToBuy-1) {
					
					ItemButton cell = new ItemButton(shopInventory.get(x+(4*y)));
					cell.setOnMouseEntered(shopEnterShadow);
					cell.setOnMouseExited(shopLeaveReset);
					cell.setOnAction(itemSelect);
					cell.setGraphic(InventoryScreen.getItemAsStack(cell.getTradeGood()));
					shopInvGrid.add(cell,x,y);
				}
			}
		}
	}

	public void updateInventoryPane() {

		updatePlayerBalance();
		playerInvGrid.getChildren().clear();
		
		// Update the calculations for the inventory tab
		numCargo = playerShip.getCargo().length;
		playGridXMax = (numCargo >= 4) ? 4 : numCargo ;
		playGridYMax = (numCargo % 4 == 0) ? (numCargo) : (numCargo/4)+1;

		// Display the player's cargo to the screen
		System.out.println("Number of items on ship: " + numCargo);
		for(int y = 0; y < playGridYMax; y++) {
			for(int x = 0; x < playGridXMax; x++) {
				if(x+4*y <= numCargo-1) {
					ItemButton cell = new ItemButton(playerShip.getCargo()[x+(4*y)]);
					cell.setOnMouseEntered(invEnterShadow);
					cell.setOnMouseExited(invLeaveReset);
					cell.setOnAction(itemSelect);
					playerInvGrid.add(cell, x, y);
					cell.setGraphic(InventoryScreen.getItemAsStack(cell.getTradeGood()));
				}
			}
		}
	}

	public void finishBuyTransact() {

		// If player has enough money, then allow the sale
		if(playerShip.getMoney() >= totalBuyPrice) {

			// Take away items from shop's inventory
			for(int i = 0; i < shoppingCart.size(); i++) {
				for(int j = 0; j < shopInventory.size(); j++) {
					if(shoppingCart.get(i) == shopInventory.get(j)) {
						
					}
				}
			}
			
			// Add items to player's cargo  
			// Subtract from player money balance
			// Update the Text nodes to reflect changes
			playerShip.setMoney(playerShip.getMoney() - totalBuyPrice);
			updateTotalBuyPrice(0);
			updatePlayerBalance();
			updateInventoryPane();
		}

		else {
			System.out.println("not enough monies");
		}
	}

	public void finishSellTransact() {

		// Remove items from player's cargo  
		// Add to player's money balance
		playerShip.setMoney(playerShip.getMoney() + totalSellPrice);
		updateInventoryPane();
	}

	// Trim doubles to exactly 2 decimal points
	public double trimNumber(double longDouble) {
		double trimmedDouble = Math.round(longDouble * 100);
		trimmedDouble /= 100;
		return trimmedDouble;
	}
}
