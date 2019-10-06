package com.trade;

public class GoldTradeGood extends TradeGood{

	static String goldImagePath = "file:resources/images/gold_good.png"; 
	int goodAmount;
	double price;
	
	public GoldTradeGood() {
		super(GOOD_TYPE.NORMAL, 0, 0D, goldImagePath);
		this.goodAmount = 0;
		this.price = 0D;
	}
	
	public GoldTradeGood(int goodAmount, double buyPrice) {
		super(GOOD_TYPE.NORMAL, goodAmount, buyPrice, goldImagePath);
		this.goodAmount = goodAmount;
		this.price = buyPrice;
	}
}
