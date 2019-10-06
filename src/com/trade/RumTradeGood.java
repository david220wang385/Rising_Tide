package com.trade;

public class RumTradeGood extends TradeGood{

	static String rumImagePath = "file:resources/images/alcohol_good.png"; 
	int goodAmount;
	double price;
	
	public RumTradeGood() {
		super(GOOD_TYPE.CONTRABAND, 0, 0D, rumImagePath);
		this.goodAmount = 0;
		this.price = 0D;
	}
	
	public RumTradeGood(int goodAmount, double buyPrice) {
		super(GOOD_TYPE.CONTRABAND, goodAmount, buyPrice, rumImagePath);
		this.goodAmount = goodAmount;
		this.price = buyPrice;
	}
}
