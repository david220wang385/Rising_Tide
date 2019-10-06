package com.gameMechanics;

public class CargoMasterNPC extends EntityNPC{

	private static double multiplier1 = 0.25D;
	private static double multiplier2 = 0.5D;
	private static double multiplier3 = 0.75D;
	
	public CargoMasterNPC() {
		super();
	}
	
	public CargoMasterNPC(int level) {
		super(level, multiplier1, multiplier2, multiplier3);
	}
	
	@Override
	public String toString() {
		return "Cargo Master [" + multiplier1 + ", " + multiplier2 + ", " + multiplier3 + "]";
	}
}
