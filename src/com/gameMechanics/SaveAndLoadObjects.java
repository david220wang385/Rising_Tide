package com.gameMechanics;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SaveAndLoadObjects {

	public static void main(String[] args) {
		
		Ship cruise = new Ship(SHIP_TYPE.BATTLESHIP, 100, 10, 24, 6, 4, 10);
		Ship cruise2 = new Ship(SHIP_TYPE.BATTLESHIP, 100, 10, 24, 6, 4, 10);
		
		File saveFile = new File("J:/Senior Project/Rising_Tide/src/com/prototypes/savedata.txt"); // Change directory while testing
		FileOutputStream outStream;
		ObjectOutputStream sendObject;
		
		// Save objects
		try{
			outStream = new FileOutputStream(saveFile); // arg is destination file
			sendObject = new ObjectOutputStream(outStream);
			
			
			sendObject.writeObject(cruise);
			sendObject.writeObject(cruise2);
			System.out.println("save successful");
		}
		catch(Exception e){
			System.out.println(e);
		}
		
		FileInputStream inStream;
		ObjectInputStream receiveObject;
		
		// Receive and load objects
		try{
			inStream = new FileInputStream(saveFile); // arg is source file
			receiveObject = new ObjectInputStream(inStream);
			
//			Ship cloneBoat = (Ship)receiveObject.readObject();
//			Ship cloneBetterBoat = (Ship)receiveObject.readObject();
//			Ship bb = (Ship)receiveObject.readObject();
			
			Ship cloneShip;
			while((cloneShip = (Ship)receiveObject.readObject()) != null){
				if(cloneShip instanceof Ship){
					System.out.println(cloneShip);
				}
			}
		}
		catch(Exception e){
			System.err.println(e);
		}
	}
}
