package com.trade;

public class CottonTradeGood extends TradeGood{

	static String cottonImagePath = "file:resources/images/cotton_good.png"; 
	int goodAmount;
	double price;
	
	public CottonTradeGood() {
		super(GOOD_TYPE.NORMAL, 0, 0D, cottonImagePath);
		this.goodAmount = 0;
		this.price = 0D;
	}
	
	public CottonTradeGood(int goodAmount, double buyPrice) {
		super(GOOD_TYPE.NORMAL, goodAmount, buyPrice, cottonImagePath);
		this.goodAmount = goodAmount;
		this.price = buyPrice;
	}
}
