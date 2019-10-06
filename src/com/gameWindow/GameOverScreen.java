package com.gameWindow;

import javafx.animation.FadeTransition;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class GameOverScreen {

	Font vivaldiFont = Font.loadFont("file:resources/fonts/Vivaldi.ttf", 105);
	
	public Scene getGameOverScreen() {
		
		StackPane gameOverPane = new StackPane();
		ImageView shipwreck = new ImageView("file:resources/images/sunken_wreck.png");
		Text gmovrText = new Text("Game Over");
		gmovrText.setFont(vivaldiFont);
		gmovrText.setId("text");
		gameOverPane.getChildren().addAll(shipwreck, gmovrText);
		gameOverPane.getStylesheets().clear();
		gameOverPane.getStylesheets().add("file:resources/stylesheet/game_over.css");
		
		
		// Fade in text transition
		FadeTransition fadein = new FadeTransition(Duration.seconds(2.5));
		fadein.setNode(gmovrText);
		fadein.setFromValue(0.15);
		fadein.setToValue(1);
		fadein.play();
		
		return new Scene(gameOverPane);
	}
}
