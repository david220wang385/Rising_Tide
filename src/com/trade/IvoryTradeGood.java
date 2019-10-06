package com.trade;

public class IvoryTradeGood extends TradeGood{

	static String ivoryImagePath = "file:resources/images/ivory_good.png"; 
	int goodAmount;
	double price;
	
	public IvoryTradeGood() {
		super(GOOD_TYPE.CONTRABAND, 0, 0D, ivoryImagePath);
		this.goodAmount = 0;
		this.price = 0D;
	}
	
	public IvoryTradeGood(int goodAmount, double buyPrice) {
		super(GOOD_TYPE.CONTRABAND, goodAmount, buyPrice, ivoryImagePath);
		this.goodAmount = goodAmount;
		this.price = buyPrice;
	}
}
