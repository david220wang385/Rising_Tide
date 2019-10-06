package com.gameWindow;

import java.util.ArrayList;
import java.util.Random;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;

public class SailingScreen {

	int distance;
	RowConstraints[] gridRC;
	ColumnConstraints[] gridCC;
	GridPane mapPane;
	Image[][] tileMap;
	GraphicsContext gc;
	int horizModifier = 0;
	int shipPosX = 3;
	int shipPosY = 5;

	public SailingScreen(int distance) {
		this.distance = distance;
	}

	public Scene getSailingScene() {

		mapPane = new GridPane();
		mapPane.setPrefSize(1000, 700);
		RowConstraints rc = new RowConstraints();
		rc.setPercentHeight(100/(mapPane.getPrefHeight()/50));
		ColumnConstraints cc = new ColumnConstraints();
		cc.setPercentWidth(100/(mapPane.getPrefWidth()/50));

		// Set vertical and horizontal gap between cells
		mapPane.setVgap(0);
		mapPane.setHgap(0);

		// Create arrays and set RowConstraints and ColumnConstraints
		System.out.println(mapPane.getPrefHeight() + " " + mapPane.getPrefWidth());
		gridRC = new RowConstraints[(int)mapPane.getPrefHeight()/50];
		gridCC = new ColumnConstraints[(int)mapPane.getPrefWidth()/50];

		// Add RowConstaints and ColumnConstraints to arrays
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

		// Create default 2d map of transition scene using 2d array
		tileMap = new Image[20][distance];
		randomizeMap();
		setBounds(horizModifier);

		Scene mapScene = new Scene(mapPane);
		mapScene.addEventFilter(KeyEvent.KEY_PRESSED, keyboardHandler);
		return mapScene;
	}

	public void moveShip(int translateX, int  translateY) {
		if (shipPosX != gridCC.length-1) {
			shipPosX += translateX;
		}
		if (shipPosY != 0 || shipPosY != 10) {
			shipPosY += translateY;
		}
	}

	EventHandler<KeyEvent> keyboardHandler = new EventHandler<KeyEvent>() {

		@SuppressWarnings("incomplete-switch")
		@Override
		public void handle(KeyEvent keyInput) {
			switch(keyInput.getCode()){
			case W:
				moveShip(0,-1);
				break;
			case A:
				if(horizModifier > 0) {
					horizModifier -= 1;
				}
				if(horizModifier == 0 && shipPosX != 0) {
					moveShip(-1,0);
				}
				break;
			case S:
				moveShip(0,1);
				break;
			case D:
				if(horizModifier < distance-20) {
					horizModifier += 1;
				}
				if(horizModifier == distance-20) {
					moveShip(1,0);
				}
				break;
			}
			System.out.println(shipPosX+"pos");
			System.out.println(horizModifier);
			setBounds(horizModifier);
		}
	};

	// Controls which cells of the map are shown
	public void setBounds(int horizDist) {

		// Clear existing GridPane 
		mapPane.getChildren().clear();

		// Add individual tiles to the GridPane
		for(int y = 0; y < gridRC.length; y++) {
			for(int x = 0; x < gridCC.length; x++) {

				// Set top and bottom 2 rows to black squares to narrow FOV
				if(y ==  0 || y == 1 || y == 12 || y ==13) {
					mapPane.add(new ImageView("file:resources/images/blk_square.png"),x, y);
				}

				if(y > 1 && y < 12) {
					mapPane.add(new ImageView(tileMap[y-2][x+horizDist]),x, y);
				}
			}
			mapPane.add(new ImageView("file:resources/images/ship.png"), shipPosX, shipPosY);
		}
	}

	public void randomizeMap() {

		Random rand = new Random();
		ArrayList<Image> tileList = new ArrayList<Image>();
		tileList.add(new Image("file:resources/images/ocean.gif"));
//		tileList.add(new Image("file:resources/images/grass_tile.png"));

		for(int y = 0; y < 10; y++) {
			for(int x = 0; x < distance; x++) {
				tileMap[y][x] = tileList.get(rand.nextInt(tileList.size())); 
			}
		}
		forceEncounter(8, new Image("file:resources/images/warning.png"));
		forceEncounter(12, new Image("file:resources/images/treasure.png"));

	}

	public void forceEncounter(int xCoord, Image image) {
		for(int y = 0; y < 10; y++) {
			for(int x = 0; x < distance; x++) {
				tileMap[y][xCoord] = image;
			}
		}
	}

	// Getters and setters for instance variables
	public void setDistance(int distance) {
		this.distance = distance;
	}

	public int getDistance() {
		return this.distance;
	}
}
