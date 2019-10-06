package com.gameMechanics;

public class EngineerNPC extends EntityNPC{

	private static double multiplier1 = 0.015D;
	private static double multiplier2 = 0.03D;
	private static double multiplier3 = 0.05D;
	
	public EngineerNPC() {
		super();
	}
	
	public EngineerNPC(int level) {
		super(level, multiplier1, multiplier2, multiplier3);
	}
	
	@Override
	public String toString() {
		return "Engineer [" + multiplier1 + ", " + multiplier2 + ", " + multiplier3 + "]";
	}
}
