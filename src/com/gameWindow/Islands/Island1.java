package com.gameWindow.Islands;

import com.gameWindow.SailingScreen;

import javafx.scene.Scene;

public class Island1 {

	public Scene getIslandScreen() {
		SailingScreen sc1 = new SailingScreen(25);

		return sc1.getSailingScene();
	}
}
