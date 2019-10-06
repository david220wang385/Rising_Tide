package com.trade;

public class IronTradeGood extends TradeGood{

	static String ironImagePath = "file:resources/images/iron_good.png"; 
	int goodAmount;
	double price;
	
	public IronTradeGood() {
		super(GOOD_TYPE.NORMAL, 0, 0D, ironImagePath);
		this.goodAmount = 0;
		this.price = 0D;
	}
	
	public IronTradeGood(int goodAmount, double buyPrice) {
		super(GOOD_TYPE.NORMAL, goodAmount, buyPrice, ironImagePath);
		this.goodAmount = goodAmount;
		this.price = buyPrice;
	}
}
