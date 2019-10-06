package com.trade;

public class SilkTradeGood extends TradeGood{

	static String silkImagePath = "file:resources/images/silk_good.png"; 
	int goodAmount;
	double price;
	
	public SilkTradeGood() {
		super(GOOD_TYPE.NORMAL, 0, 0D, silkImagePath);
		this.goodAmount = 0;
		this.price = 0D;
	}
	
	public SilkTradeGood(int goodAmount, double buyPrice) {
		super(GOOD_TYPE.NORMAL, goodAmount, buyPrice, silkImagePath);
		this.goodAmount = goodAmount;
		this.price = buyPrice;
	}
}
