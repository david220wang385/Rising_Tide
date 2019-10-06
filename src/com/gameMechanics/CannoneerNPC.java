package com.gameMechanics;

public class CannoneerNPC extends EntityNPC{

	private static double multiplier1 = 0.3D;
	private static double multiplier2 = 0.75D;
	private static double multiplier3 = 1.25D;
	
	public CannoneerNPC() {
		super();
	}
	
	public CannoneerNPC(int level) {
		super(level, multiplier1, multiplier2, multiplier3);
	}
	
	@Override
	public String toString() {
		return "Cannoneer [" + multiplier1 + ", " + multiplier2 + ", " + multiplier3 + "]"; 
	}
}
