package com.gameMechanics;

import java.io.Serializable;
import java.util.Arrays;

import com.trade.TradeGood;

import javafx.scene.image.Image;

public class Ship implements Serializable{

	private static final long serialVersionUID = 1L;
	private SHIP_TYPE shipType;
	private double maxHealth;
	private double nomHealth;
	private double maxArmor;
	private double nomArmor;
	private int speed;
	private int numCannons;
	private int crewCapacity;
	private EntityNPC[] hiredNPCs;
	private TradeGood[] cargo;
	private int cargoCapacity;
	private double money;
	private Image shipSprite;
	private AMMO_TYPE[] availableAmmoTypes;
	private AMMO_TYPE currentAmmoType;

	public Ship(SHIP_TYPE shipType, double maxHealth, double maxArmor, int speed, int numCannons, int crewCap, int cargoCap){
		this.shipType = shipType;
		this.maxHealth = maxHealth;
		this.nomHealth = this.maxHealth;
		this.maxArmor = maxArmor;
		this.nomArmor = this.maxArmor;
		this.speed = speed;
		this.numCannons = numCannons;
		this.crewCapacity = crewCap;
		this.hiredNPCs = new EntityNPC[crewCapacity];
		this.cargoCapacity = cargoCap;
		this.money = 10000.0;
		this.shipSprite = getSprite(shipType);
		this.availableAmmoTypes = new AMMO_TYPE[] {AMMO_TYPE.BASIC_AMMO, AMMO_TYPE.AP_AMMO, AMMO_TYPE.HIGHEX_AMMO, AMMO_TYPE.INCEND_AMMO};
		this.currentAmmoType = this.availableAmmoTypes[0];
		
		// Code to test the inventory system
		this.cargo = new TradeGood[10];
		for(int i = 0; i < cargo.length; i++) {
			this.cargo[i] = TradeGood.getRandomTradeGood();
		}
//		this.cargo[0] = new CoffeeTradeGood(55, 2.4);
//		this.cargo[1] = new GrainTradeGood(10, 25.6);
//		this.cargo[2] = new CoffeeTradeGood(71, 9.7);
	}

	public String toString(){
		return "-----------------------------------------------------\nShip Type: " + this.shipType + " \nMax Health: " + this.maxHealth + " \nArmor: " 
				+ maxArmor + "\nSpeed: " + speed + "\nNumber of Cannons: " + numCannons + "\nCrew Capacity: " + crewCapacity;
	}

	// Getters and setters for class variables
	public SHIP_TYPE getShipType() {
		return this.shipType;
	}

	public double getMaxHealth() {
		return this.maxHealth;
	}
	
	public double getNomHealth() {
		return this.nomHealth;
	}

	public double getMaxArmor() {
		return this.maxArmor;
	}
	
	public double getNomArmor() {
		return this.nomArmor;
	}

	public int getSpeed() {
		return this.speed;
	}

	public double getNumCannons() {
		return this.numCannons;
	}

	public double getCrewCap() {
		return this.crewCapacity;
	}

	public TradeGood[] getCargo() {
		return this.cargo;
	}
	
	public int getCargoCap() {
		return this.cargoCapacity;
	}
	
	public double getMoney() {
		return this.money;
	}
	
	public Image getShipSprite() {
		return this.shipSprite;
	}
	
	public AMMO_TYPE[] getAvailableAmmoTypes(){
		return this.availableAmmoTypes;
	}
	
	public AMMO_TYPE getCurrentAmmoType(){
		return this.currentAmmoType;
	}
	
	public void setShipType(SHIP_TYPE newShipType) {
		this.shipType = newShipType;
	}

	public void setMaxHealth(double newMaxHealth) {
		this.maxHealth = newMaxHealth;
	}
	
	public void setNomHealth(double newNomHealth) {
		this.nomHealth = newNomHealth;
	}

	public void setMaxArmor(double newMaxArmor) {
		this.maxArmor = newMaxArmor;
	}

	public void setNomArmor(double newNomArmor) {
		this.nomArmor = newNomArmor;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void setNumCannons(int newNumCannons) {
		this.numCannons = newNumCannons;
	}

	public void setCrewCap(int newCrewCap) {
		this.crewCapacity = newCrewCap;
	}

	public void setCargo(TradeGood[] newCargo) {
		this.cargo = newCargo;
	}
	
	public void setCargoCap(int cap) {
		this.cargoCapacity = cap;
	}
	
	public void setMoney(double money) {
		this.money = money;
	}
	
	public void setShipSprite(Image shipSprite) {
		this.shipSprite = shipSprite;
	}
	
	public void setAvailableAmmoTypes(AMMO_TYPE[] newAvailableTypes){
		this.availableAmmoTypes = newAvailableTypes;
	}
	
	public void setCurrentAmmoType(AMMO_TYPE newCurrentAmmoType){
		this.currentAmmoType = newCurrentAmmoType;
	}
	
	// Adds an EntityNPC object to the crew array
	public EntityNPC hireNPC(EntityNPC npcToBeHired) {
		boolean slotIsFilled = true;
		int counter = 0;
		do {
			// If slot is open, add the NPC
			if(hiredNPCs[counter] == null) {
				hiredNPCs[counter] = npcToBeHired;
				slotIsFilled = false;
			}
			counter++;
		}while(slotIsFilled);
		return npcToBeHired;	
	}

	// Returns the current members of your crew
	public EntityNPC[] getCrew() {
		System.out.println("Current NPCs on your ship: ");
		for(EntityNPC e: this.hiredNPCs) {
			System.out.println(e);
		}
		return this.hiredNPCs;
	}

	public EntityNPC getCrewMember(EntityNPC npc) {
		for(int i = 0; i < this.crewCapacity; i++) {
			if(hiredNPCs[i].getClass().isAssignableFrom(npc.getClass())) {
				return hiredNPCs[i]; 
			}
		}
		return new EntityNPC();
	}

	public void upgradeNPC(EntityNPC npc) {
		if(npc.getlevel() < 3) {
			npc.setLevel(npc.getlevel()+1);
			System.out.println("NPC " + npc + "'s level was increased to " + npc.getlevel());
		}
	}

	public Image getSprite(SHIP_TYPE type) {
		
		Image returnImage = null;
		
		switch(type) {
		case BATTLESHIP:
			returnImage =  new Image("file:resources/images/ship_sprites/battleship.png");
			break;
		case CARAVEL:
			returnImage =   new Image("file:resources/images/ship_sprites/caravel.png");
			break;
		case FRIGATE:
			returnImage =   new Image("file:resources/images/ship_sprites/frigate.png");
			break;
		case GALLEASS:
			returnImage =   new Image("file:resources/images/ship_sprites/galleass.gif");
			break;
		case WAR_JUNK:
			returnImage =   new Image("file:resources/images/ship_sprites/warJunk1.gif");
			break;
		}
		
		return returnImage;
	}
	
	// Combat methods
	
	// Calculate hit chance based on speed difference
	public double calculateHitChance(Ship source, Ship target) {
		int speedDiff = 0;
		double hitChance = 0.5;

		if(source.getSpeed() >= target.getSpeed()) {
			speedDiff = source.getSpeed()-target.getSpeed();

			if(speedDiff >= 5) {
				hitChance = 1;
			}
			else {
				hitChance = 0.5 + 0.1*(source.getSpeed()-target.getSpeed());
			}
		}

		// If player ship's speed is lower than enemy's ship
		else {
			speedDiff = target.getSpeed()-source.getSpeed();
				hitChance = 0.55 - 0.1*speedDiff;
		}
		
		return hitChance;
	}
	
	public boolean fireCannons(Ship targetShip) {

		// Attempt to fire cannons, change nominal health accordingly
		if(Math.random() <= calculateHitChance(this, targetShip)) {
			System.out.println("Cannons hit");
			targetShip.setNomHealth(targetShip.getNomHealth() - this.getNumCannons()* (this.getCurrentAmmoType().getDamage()));
			return true;
		}
		else {
			System.out.println("Cannons miss");
			return false;
		}
	}
	
	public boolean ramShip(Ship targetShip) {

		// Attempt to fire cannons, change nominal health accordingly
		if(Math.random() <= calculateHitChance(this, targetShip)) {
			System.out.println("Direct Hit!");
			targetShip.setNomHealth(targetShip.getNomHealth() - this.getNumCannons()* (this.getCurrentAmmoType().getDamage()));
			return true;
		}
		else {
			System.out.println("You completely missed!?");
			return false;
		}
	}

	// Cycles to next type of ammo
	public String cycleAmmo() {
	
		this.setCurrentAmmoType(this.availableAmmoTypes[(Arrays.asList(this.availableAmmoTypes).indexOf(this.currentAmmoType)+1)%4]);
		System.out.println("Switch to using: " + this.getCurrentAmmoType().getName());
		return this.getCurrentAmmoType().getName();
	}
	
	public static void main(String[] args) {
		Ship cruise = new Ship(SHIP_TYPE.BATTLESHIP, 100, 10, 24, 6, 4, 12);
		cruise.hireNPC(new EngineerNPC(0));
		cruise.hireNPC(new CannoneerNPC(1));
		cruise.getCrew();
		cruise.upgradeNPC(cruise.getCrewMember(new CannoneerNPC()));
	}
}
