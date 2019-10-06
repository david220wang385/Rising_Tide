package com.gameMechanics;

public enum AMMO_TYPE {

	BASIC_AMMO (5, "Standard Cannonballs"),
	AP_AMMO (7.5, "Armor Piercing Cannonballs"), 
	HIGHEX_AMMO (9, "Volatile Cannonballs"), 
	INCEND_AMMO (9, "Flaming Cannonballs");

	private final double baseDamage;
	private final String name;
	
	AMMO_TYPE(double damage, String name) {
		this.baseDamage = damage;
		this.name = name;
	}

	public double getDamage() { 
		return this.baseDamage; 
	}

	public String getName() { 
		return this.name; 
	}
}
