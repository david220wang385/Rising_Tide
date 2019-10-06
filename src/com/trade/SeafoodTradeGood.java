package com.trade;

public class SeafoodTradeGood extends TradeGood{

	static String seafoodImagePath = "file:resources/images/seafood_good.png"; 
	int goodAmount;
	double price;
	
	public SeafoodTradeGood() {
		super(GOOD_TYPE.NORMAL, 0, 0D, seafoodImagePath);
		this.goodAmount = 0;
		this.price = 0D;
	}
	
	public SeafoodTradeGood(int goodAmount, double buyPrice) {
		super(GOOD_TYPE.NORMAL, goodAmount, buyPrice, seafoodImagePath);
		this.goodAmount = goodAmount;
		this.price = buyPrice;
	}
}
