package com.gameMechanics;

public class NavigatorNPC extends EntityNPC{

	private static double multiplier1 = 0.25D;
	private static double multiplier2 = 0.4D;
	private static double multiplier3 = 0.65D;
	
	public NavigatorNPC() {
		super();
	}
	
	public NavigatorNPC(int level) {
		super(level, multiplier1, multiplier2, multiplier3);
	}
	
	@Override
	public String toString() {
		return "Navigator [" + multiplier1 + ", " + multiplier2 + ", " + multiplier3 + "]";
	}
}
