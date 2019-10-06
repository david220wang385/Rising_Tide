package com.trade;

public class GrainTradeGood extends TradeGood{

	static String grainImagePath = "file:resources/images/grain_good.png"; 
	int goodAmount;
	double price;
	
	public GrainTradeGood() {
		super(GOOD_TYPE.NORMAL, 0, 0D, grainImagePath);
		this.goodAmount = 0;
		this.price = 0D;
	}
	
	public GrainTradeGood(int goodAmount, double buyPrice) {
		super(GOOD_TYPE.NORMAL, goodAmount, buyPrice, grainImagePath);
		this.goodAmount = goodAmount;
		this.price = buyPrice;
	}
}
