package com.trade;

public class SaltTradeGood extends TradeGood{

	static String saltImagePath = "file:resources/images/salt_good.png"; 
	int goodAmount;
	double price;
	
	public SaltTradeGood() {
		super(GOOD_TYPE.NORMAL, 0, 0D, saltImagePath);
		this.goodAmount = 0;
		this.price = 0D;
	}
	
	public SaltTradeGood(int goodAmount, double buyPrice) {
		super(GOOD_TYPE.NORMAL, goodAmount, buyPrice, saltImagePath);
		this.goodAmount = goodAmount;
		this.price = buyPrice;
	}
}
