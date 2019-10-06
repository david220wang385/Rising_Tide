package com.trade;

public class CoffeeTradeGood extends TradeGood{

	static String coffeeImagePath = "file:resources/images/coffee_good.png"; 
	int goodAmount;
	double price;
	
	public CoffeeTradeGood() {
		super(GOOD_TYPE.NORMAL, 0, 0D, coffeeImagePath);
		this.goodAmount = 0;
		this.price = 0D;
	}
	
	public CoffeeTradeGood(int goodAmount, double buyPrice) {
		super(GOOD_TYPE.NORMAL, goodAmount, buyPrice, coffeeImagePath);
		this.goodAmount = goodAmount;
		this.price = buyPrice;
	}
}
