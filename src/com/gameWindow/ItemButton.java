package com.gameWindow;

import com.trade.TradeGood;

import javafx.scene.control.Button;

public class ItemButton extends Button{
	TradeGood item;

	public ItemButton(TradeGood item) {
		super();
		this.item = item;
		this.setMaxSize(100, 100);
		this.setId("ItemBtn");
	}
	
	public void setTradeGood(TradeGood newTradeGood) {
		this.item = newTradeGood;
	}
	
	public TradeGood getTradeGood() {
		return this.item;
	}
	
	@Override public String toString() {
		return super.toString() + "tradegood = " + this.getTradeGood();
	}
	
}
