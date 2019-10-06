package com.trade;

public class WeaponTradeGood extends TradeGood{

	static String weaponImagePath = "file:resources/images/weapon_good.png"; 
	int goodAmount;
	double price;
	
	public WeaponTradeGood() {
		super(GOOD_TYPE.CONTRABAND, 0, 0D, weaponImagePath);
		this.goodAmount = 0;
		this.price = 0D;
	}
	
	public WeaponTradeGood(int goodAmount, double buyPrice) {
		super(GOOD_TYPE.CONTRABAND, goodAmount, buyPrice, weaponImagePath);
		this.goodAmount = goodAmount;
		this.price = buyPrice;
	}
}
