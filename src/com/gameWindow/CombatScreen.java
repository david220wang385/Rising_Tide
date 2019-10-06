package com.gameWindow;

import com.gameMechanics.Ship;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CombatScreen {

	Font font = Font.loadFont("file:resources/fonts/pixel_text.ttf", 17);
	Font smallerFont = Font.loadFont("file:resources/fonts/pixel_text.ttf", 13);
	ScrollPane combatActionLog = new ScrollPane();
	Label logText = new Label("");
	GameOverScreen gmeovrscrn = new GameOverScreen();
	Ship playerShip;
	Ship enemyShip;
	Text playerHpLabel;
	Text enemyHpLabel;

	public CombatScreen(Ship playerShip, Ship enemyShip) {
		this.playerShip = playerShip;
		this.enemyShip = enemyShip;
	}

	// Handle button press input
	EventHandler<ActionEvent> buttonHandler = new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent buttonPress) {

			switch(((Button)buttonPress.getSource()).getText()){
			case "Fire \nCannons":
				System.out.println(">Player fired their cannons!");
				updateLog("You fired your cannons!");
				updateLog( (playerShip.fireCannons(enemyShip))? "Direct Hit!" : "Your cannons missed!" );
				break;
			case "Ram \nShip":
				System.out.println(">Player rammed enemy ship!");
				updateLog("You rammed the enemy's ship!");
				updateLog( (playerShip.ramShip(enemyShip))? "Direct Hit!" : "You completely missed!?" );
				break;
			case "Change \nAmmo":
				System.out.println(">Player switched ammo types");
				updateLog("Your cannons switched to \nusing ...");
				updateLog(playerShip.cycleAmmo());
				break;
			case "Surrender":
				System.out.println(">Player surrendered");
				updateLog("You Surrender!");
				((Stage) ((Button)buttonPress.getSource()).getScene().getWindow()).setScene(gmeovrscrn.getGameOverScreen());
				break;
			}

			// Update the HP labels after every player action
			updateHPLabels();

			if(!((Button)buttonPress.getSource()).getText().equals("Surrender")) {

				// If enemy is heavily outmatched, they will instantly surrender
				if ((playerShip.getSpeed()-enemyShip.getSpeed() >= 7 )|| ((playerShip.getNomHealth()*playerShip.getNomArmor()) > (enemyShip.getNomHealth()*enemyShip.getNomArmor()*4))) {
					enemyShip.setNomHealth(0);
					updateHPLabels();
					System.out.println("Enemy ship surrended upon gazing at your glorious ship!");
				}
				
				double randomNum = Math.random();

				if(randomNum <= 0.4) {
					System.out.println(">Enemy ship their cannons");
					updateLog("Enemy ship fired their cannons!");
					// In future, generate dumb funny lines
					updateLog( (enemyShip.fireCannons(playerShip))? "We're taking fire!" : "Successfully evaded!" );
				}
				else if(randomNum > 0.4 && randomNum < 0.8) {
					System.out.println(">Enemy ship rammed our ship!");
					updateLog("Enemy ship rammed our ship!");
					updateLog( (enemyShip.ramShip(playerShip))? "Our ship was rammed!" : "Successfully evaded!" );
				}
				else {
					System.out.println(">Enemy ship switched ammo types");
					updateLog("Enemy ship switched to \nusing ...");
					updateLog(enemyShip.cycleAmmo());
				}
			}

			if(enemyShip.getNomHealth() <= 0) {
				// player wins battle
				// Return to naviagtional grid map
			}

			if(playerShip.getNomHealth() <= 0) {
				((Stage) ((Button)buttonPress.getSource()).getScene().getWindow()).setScene(gmeovrscrn.getGameOverScreen());
			}
		}
	};

	// Returns the combat Scene
	public Scene getCombatScene() {

		BorderPane root = new BorderPane();

		// Top portion of the Scene
		StackPane battleDisplay = new StackPane();
		battleDisplay.setPrefSize(1000, 450);
		battleDisplay.getChildren().add(new ImageView("file:resources/images/battle_bg.png"));
		BorderPane shipSpritesBox = new BorderPane();
		StackPane leftSpriteAndLabelStack = new StackPane();
		ImageView playerShipSprite = new ImageView(playerShip.getShipSprite());
		playerHpLabel = new Text("HP: " + this.playerShip.getNomHealth());
		playerHpLabel.setFont(font);
		playerHpLabel.setId("Label");
		playerHpLabel.setTranslateY(200);
		playerShipSprite.setFitWidth(490);
		playerShipSprite.setPreserveRatio(true);
		leftSpriteAndLabelStack.getChildren().addAll(playerShipSprite, playerHpLabel);
		StackPane rightSpriteAndLabelStack = new StackPane();
		ImageView enemyShipSprite = new ImageView(enemyShip.getShipSprite());
		enemyHpLabel = new Text("HP: " + this.enemyShip.getNomHealth());
		enemyHpLabel.setFont(font);
		enemyHpLabel.setId("Label");
		enemyHpLabel.setTranslateY(200);
		enemyShipSprite.setFitWidth(490);
		enemyShipSprite.setPreserveRatio(true);
		rightSpriteAndLabelStack.getChildren().addAll(enemyShipSprite, enemyHpLabel);
		shipSpritesBox.setLeft(leftSpriteAndLabelStack);
		shipSpritesBox.setRight(rightSpriteAndLabelStack);
		battleDisplay.getChildren().add(shipSpritesBox);

		StackPane bottomBoxStack = new StackPane();
		bottomBoxStack.setPrefSize(1000, 250);
		GridPane bottboxbgs = new GridPane();

		// Divide the GridPane into a 1 x 4 grid
		ColumnConstraints cc = new ColumnConstraints();
		cc.setPercentWidth(100/4);
		for(int i = 0; i < 4; i++) {
			bottboxbgs.getColumnConstraints().add(cc);
			bottboxbgs.add(new ImageView("file:resources/images/cmbt_btn_bg.png"), i , 0);
		}

		// Container for the buttons
		HBox commandsBox = new HBox();
		commandsBox.setPrefSize(700, 250);

		// Action buttons
		Button fireCannonsButton = new Button("Fire \nCannons");
		Button ramAttackButton = new Button("Ram \nShip");
		Button changeAmmoButton = new Button("Change \nAmmo");
		Button surrenderButton = new Button("Surrender");

		fireCannonsButton.setFont(font);
		ramAttackButton.setFont(font);
		changeAmmoButton.setFont(font);
		surrenderButton.setFont(smallerFont);
		fireCannonsButton.setPrefSize(125, 150);
		ramAttackButton.setPrefSize(125, 150);
		changeAmmoButton.setPrefSize(125, 150);
		surrenderButton.setPrefSize(125, 150);
		fireCannonsButton.setOnAction(buttonHandler);
		ramAttackButton.setOnAction(buttonHandler);
		changeAmmoButton.setOnAction(buttonHandler);
		surrenderButton.setOnAction(buttonHandler);

		// Add buttons to HBox and set spacing
		commandsBox.getChildren().addAll(fireCannonsButton, ramAttackButton, changeAmmoButton, surrenderButton);
		commandsBox.setSpacing(50);
		commandsBox.setPadding(new Insets(40, 50, 50, 40));

		// Create a StackPane that holds the background and the actual log
		StackPane logStack = new StackPane();
		ImageView logBackground = new ImageView("file:resources/images/log_bg.png");
		combatActionLog = new ScrollPane();
		combatActionLog.setPrefSize(300, 250);
		combatActionLog.setHbarPolicy(ScrollBarPolicy.NEVER);
		combatActionLog.setVbarPolicy(ScrollBarPolicy.NEVER);
		combatActionLog.setStyle("-fx-background: transparent;");

		// Add the ImageView and ScrolLPane to the StackPane
		logStack.getChildren().addAll(logBackground, combatActionLog);

		// Add the buttons and log StackPane to a horizontal box
		HBox bottomContent = new HBox(commandsBox, logStack);

		// Add the background and content to the bottom stack
		bottomBoxStack.getChildren().addAll(bottboxbgs, bottomContent);

		// Set the top and bottom of the root BorderPane
		root.setTop(battleDisplay);
		root.setBottom(bottomBoxStack);

		Scene combatScene = new Scene(root);
		combatScene.getStylesheets().clear();
		combatScene.getStylesheets().add("file:resources/stylesheet/combat_screen.css");
		return combatScene;
	}

	// Update the text in the scrolling combatActionLog
	public void updateLog(String message) {

		if(combatActionLog.getContent() == null) {
			combatActionLog.setContent(logText);
		}

		logText.setFont(font);
		((Label)combatActionLog.getContent()).setText(logText.getText() + ">" + message + "\n");;
		combatActionLog.setVvalue(1.0);
	}

	public void updateHPLabels() {
		playerHpLabel.setText("HP: " + this.playerShip.getNomHealth());
		enemyHpLabel.setText("HP: " + this.enemyShip.getNomHealth());
	}

}