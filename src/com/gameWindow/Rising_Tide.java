package com.gameWindow;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;

import com.gameMechanics.SHIP_TYPE;
import com.gameMechanics.Ship;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class Rising_Tide extends Application{

	// https://www.shareicon.net/pack/pirate-set
	InputStream in;
	AudioStream audioStream;
	String descEarthTrackPath = "resources/audio/Descending Earths.wav";
	String floatyTrackPath = "resources/audio/Floaty.wav";
	MainMenuScreen mmscrn = new MainMenuScreen();
	WorldMapScreen wrldmpscrn = new WorldMapScreen();
	GameOverScreen gmeovrscrn = new GameOverScreen();
	MarketScreen shopscrn;
	InventoryScreen invscrn;
	CombatScreen cmbtscrn;
	SailingScreen sailscrn;
	Stage gameWindow;
	Scene containerScene;
	
	// Remove these variables on release
	Ship cruiser = new Ship(SHIP_TYPE.FRIGATE, 10000000, 10, 24, 6, 4, 8);
	Ship battleship = new Ship(SHIP_TYPE.BATTLESHIP, 10, 10, 24, 6, 4, 9);
	
	EventHandler<KeyEvent> keyboardHandler = new EventHandler<KeyEvent>() {

		@SuppressWarnings("incomplete-switch")
		@Override
		public void handle(KeyEvent keyInput) {
			switch(keyInput.getCode()){
			case ESCAPE:
				((Stage)(keyInput.getSource())).close();
				break;
			case C:
				cmbtscrn = new CombatScreen(cruiser, battleship);
				containerScene = cmbtscrn.getCombatScene();
				((Stage)(keyInput.getSource())).setScene(containerScene);
				break;
			case G:
				containerScene = mmscrn.getMapScene();
				((Stage)(keyInput.getSource())).setScene(containerScene);
				break;
			case I:
				invscrn = new InventoryScreen(battleship);
				containerScene = invscrn.getInventoryScene();
				((Stage)(keyInput.getSource())).setScene(containerScene);
				break;
			case M:
				containerScene = wrldmpscrn.getWorldMapScreen();
				((Stage)(keyInput.getSource())).setScene(containerScene);
				break;
			case N:
				sailscrn = new SailingScreen(40);
				System.out.println(sailscrn.getDistance());
				containerScene = sailscrn.getSailingScene();
				((Stage)(keyInput.getSource())).setScene(containerScene);
				break;
			case S:
				shopscrn = new MarketScreen(cruiser);
				containerScene = shopscrn.getMarketScene();
				((Stage)(keyInput.getSource())).setScene(containerScene);
				break;
			case O:
				containerScene = gmeovrscrn.getGameOverScreen();
				((Stage)(keyInput.getSource())).setScene(containerScene);
			}
			System.out.printf(keyInput+"%nPressed "+keyInput.getCode()+"%n");
			
		}
	};
	
	EventHandler<MouseEvent> mouseMoveHandler = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent mouseInput) {
			System.out.println("Relative to source: "+mouseInput.getX() + "," +  mouseInput.getY());
			System.out.println("---------------------------------------------------------------------------");
		}
	};
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {

		// Set the Scene of the Stage to the main menu	
		primaryStage.setTitle("Rising Tide");
		containerScene = mmscrn.getMainMenu();
		primaryStage.setScene(containerScene);
		primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, keyboardHandler);
		primaryStage.setResizable(false);
		primaryStage.sizeToScene();
		primaryStage.show();

		// Make sure music stops when Stage is closed
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				primaryStage.close();
			}
		});
	}

	public void playTrack(String filePath) 
	{
		// Stop any currently playing tracks
		stopTrack();

		try
		{
			// Calls class variable
			in = new FileInputStream(filePath);
			audioStream = new AudioStream(in);
			AudioPlayer.player.start(audioStream);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void stopTrack() {
		AudioPlayer.player.stop(audioStream);
	}
	
	// Load data from a text file
	public static String[] loadData(String saveFilePath) {
		int numDataFields = 20;
		String[] numData = new String[numDataFields]; 
		int count = 0;

		// Loads data from a file to a double array
		System.out.println("Loading savedata:");
		try(BufferedReader br = new BufferedReader(new FileReader(saveFilePath))) {
			System.out.println("before");
			for(String line; (line = br.readLine()) != null; ) {
				numData[count] = line;
				System.out.println("wut");
				count++;
			}
		}
		catch(Exception e) {
			System.err.println(e);
		}

		System.out.println("---------------------------------------------------------------------------");
		System.out.println("-------------------------------Data Loaded---------------------------------");
		System.out.println("---------------------------------------------------------------------------");
		for(String p: numData) {
			System.out.println(p);
		}
		return numData;
	}
	
	// Save data to a target file
	public static void saveData(String[] data, String saveFilePath) {
		try{    
			
			// Write to a file as strings
			BufferedWriter bw = new BufferedWriter(new FileWriter(saveFilePath));
			for(String value: data) {
				bw.write(value);
				bw.newLine();
			}
			bw.close();    
		}catch(Exception e){
			System.err.println(e);
		}    
	}
}
