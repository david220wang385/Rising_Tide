package com.gameWindow.Islands;

import com.gameWindow.SailingScreen;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class Island2 {

	public Scene getIslandScreen() {
		SailingScreen sc1 = new SailingScreen(45);
		return sc1.getSailingScene();
	}
}
