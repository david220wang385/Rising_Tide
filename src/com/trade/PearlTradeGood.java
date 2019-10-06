package com.trade;

public class PearlTradeGood extends TradeGood{

	static String pearlImagePath = "file:resources/images/pearl_good.png"; 
	int goodAmount;
	double price;
	
	public PearlTradeGood() {
		super(GOOD_TYPE.NORMAL, 0, 0D, pearlImagePath);
		this.goodAmount = 0;
		this.price = 0D;
	}
	
	public PearlTradeGood(int goodAmount, double buyPrice) {
		super(GOOD_TYPE.NORMAL, goodAmount, buyPrice, pearlImagePath);
		this.goodAmount = goodAmount;
		this.price = buyPrice;
	}
}
