package com.gameMechanics;

public class EntityNPC {
	
	private int level;
	private double[] multipliers = new double[3];

	public EntityNPC() {
		this.level = 0;
	}
	
	public EntityNPC(int level, double multipliers1, double multipliers2, double multipliers3) {
		this.level = level;
		this.multipliers[0] = multipliers1;
		this.multipliers[1] = multipliers2; 
		this.multipliers[2] = multipliers3; 
	}
	
	// Getters and setters for the private variables
	public int getlevel() {
		return this.level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public double getMultipliers() {
		return this.multipliers[level];
	}
	
	public void setMultipliers(double multiplier, int level) {
		this.multipliers[level] = multiplier;
	}
	
	public void upgradeNPC() {
		this.level++;
	}
	
	@Override
	public String toString() {
		return "Default NPC";
	}
}
