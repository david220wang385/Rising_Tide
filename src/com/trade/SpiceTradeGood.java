package com.trade;

public class SpiceTradeGood extends TradeGood{

	static String spiceImagePath = "file:resources/images/spice_good.png"; 
	int goodAmount;
	double price;
	
	public SpiceTradeGood() {
		super(GOOD_TYPE.NORMAL, 0, 0D, spiceImagePath);
		this.goodAmount = 0;
		this.price = 0D;
	}
	
	public SpiceTradeGood(int goodAmount, double buyPrice) {
		super(GOOD_TYPE.NORMAL, goodAmount, buyPrice, spiceImagePath);
		this.goodAmount = goodAmount;
		this.price = buyPrice;
	}
}
