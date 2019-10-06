package com.gameWindow;

import com.gameWindow.Islands.Island;
import com.gameWindow.Islands.Island1;
import com.gameWindow.Islands.Island2;
import com.gameWindow.Islands.Island3;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class WorldMapScreen {
	
	// Where player started from
	private Island selectedIsland;
	
	public WorldMapScreen() {
	}
	
	public WorldMapScreen(Island passedIsland) {
		selectedIsland = passedIsland;
	}
	
	public Scene getWorldMapScreen(){
		
		Scene worldMapScene;
		
		GridPane worldMapPane = new GridPane();
		worldMapPane.setPrefSize(1000, 700);
		RowConstraints rc = new RowConstraints();
		rc.setPercentHeight(100/(worldMapPane.getPrefHeight()/50));
		ColumnConstraints cc = new ColumnConstraints();
		cc.setPercentWidth(100/((worldMapPane.getPrefWidth())/50));

		// Set vertical and horizontal gap between cells
		worldMapPane.setVgap(0);
		worldMapPane.setHgap(0);

		// Create arrays and set RowConstraints and ColumnConstraints
		RowConstraints[] gridRC = new RowConstraints[(int)worldMapPane.getPrefHeight()/50];
		System.out.println(worldMapPane.getPrefHeight() + " " + worldMapPane.getPrefWidth());
		ColumnConstraints[] gridCC = new ColumnConstraints[(int)worldMapPane.getPrefWidth()/50];
		for(int i = 0; i < gridCC.length; i++) {
			System.out.println(i);
			if(i < 14) {
				gridRC[i] = rc;
				System.out.println(gridRC[i]);
			}
			gridCC[i] = cc;
			System.out.println(gridCC[i]);
		}
		worldMapPane.getRowConstraints().addAll(gridRC);
		worldMapPane.getColumnConstraints().addAll(gridCC);

		Image animated_ocean = new Image("file:resources/images/ocean.gif");
		for(int y = 0; y < gridRC.length; y++) {
			for(int x = 0; x < gridCC.length; x++) {

				// Create and add a StackPane to each cell in the GridPane to layer Nodes on top of each other 
				worldMapPane.add(new ImageView(animated_ocean), x, y);
			}
		}
		
		StackPane oceanWithIslands = new StackPane();
		oceanWithIslands.setPrefWidth(950);
		oceanWithIslands.setPrefHeight(700);
		Button island1button = new Button();
		island1button.setTranslateX(-300);
		island1button.setTranslateY(-250);
		Button island2button = new Button();
		island2button.setTranslateX(0);
		island2button.setTranslateY(0);		
		Button island3button = new Button();
		island3button.setTranslateX(300);
		island3button.setTranslateY(150);	
		oceanWithIslands.getChildren().addAll(worldMapPane, island1button, island2button , island3button);
		
		EventHandler<ActionEvent> buttonClickHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent buttonPressed) {
				
				if(buttonPressed.getSource() == island1button) {
					Island1 startIsland = new Island1();
					((Stage) island1button.getScene().getWindow()).setScene(startIsland.getIslandScreen());
					System.out.println("Switch scene to Island 1");
				}
				
				if(buttonPressed.getSource() == island2button) {
					Island2 secondIsland = new Island2();
					((Stage) island1button.getScene().getWindow()).setScene(secondIsland.getIslandScreen());
					System.out.println("Switch scene to Island 2");
				}
				
				if(buttonPressed.getSource() == island3button) {
					Island3 thirdIsland = new Island3();
					((Stage) island1button.getScene().getWindow()).setScene(thirdIsland.getIslandScreen());
					System.out.println("Switch scene to Island 3");
				}
				
			}
		};
		
		worldMapScene = new Scene(oceanWithIslands);
		island1button.setOnAction(buttonClickHandler);
		island2button.setOnAction(buttonClickHandler);
		island3button.setOnAction(buttonClickHandler);
		worldMapScene.getStylesheets().clear();
		worldMapScene.getStylesheets().add("file:resources/stylesheet/world_map.css");
		return worldMapScene;
	}
}
