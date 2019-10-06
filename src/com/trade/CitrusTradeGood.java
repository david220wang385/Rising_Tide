package com.trade;

public class CitrusTradeGood extends TradeGood{

	static String citrusImagePath = "file:resources/images/citrus_good.png"; 
	int goodAmount;
	double price;
	
	public CitrusTradeGood() {
		super(GOOD_TYPE.NORMAL, 0, 0D, citrusImagePath);
		this.goodAmount = 0;
		this.price = 0D;
	}
	
	public CitrusTradeGood(int goodAmount, double buyPrice) {
		super(GOOD_TYPE.NORMAL, goodAmount, buyPrice, citrusImagePath);
		this.goodAmount = goodAmount;
		this.price = buyPrice;
	}
}
