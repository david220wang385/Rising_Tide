package com.gameWindow.Islands;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class Island3 {

	public Scene getIslandScreen() {
		Pane islandPane = new Pane();
		islandPane.setPrefSize(1000, 700);
		islandPane.setStyle("-fx-background-color: gold;");
		return new Scene(islandPane);
	}
}
