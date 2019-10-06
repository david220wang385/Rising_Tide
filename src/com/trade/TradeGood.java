package com.trade;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class TradeGood {

	private GOOD_TYPE goodType;
	private int quantity;
	private double initialBuyPrice;
	private String imagePath;
	
	public TradeGood(GOOD_TYPE type, int quantity, double buyPrice, String imagePath) {
		this.goodType = type;
		this.quantity = quantity;
		this.initialBuyPrice = buyPrice;
		this.imagePath = imagePath;
	}
	
	public void setType(GOOD_TYPE type) {
		this.goodType = type;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public void setInitialBuyPrice(double buyPrice) {
		this.initialBuyPrice = buyPrice;
	}
	
	public void setImagePath(String path) {
		this.imagePath = path;
	}
	
	public GOOD_TYPE getGoodType() {
		return this.goodType;
	}
	
	public int getQuantity() {
		return this.quantity;
	}
	
	public double getInitialBuyPrice() {
		return this.initialBuyPrice;
	}
	
	public String getImagePath() {
		return this.imagePath;
	}
	
	public static TradeGood getRandomTradeGood() {
		ArrayList<TradeGood> possibleGoods = new ArrayList<TradeGood>();
		possibleGoods.add(new CitrusTradeGood());
		possibleGoods.add(new CoffeeTradeGood());
		possibleGoods.add(new CottonTradeGood());
		possibleGoods.add(new GoldTradeGood());
		possibleGoods.add(new GrainTradeGood());
		possibleGoods.add(new IronTradeGood());
		possibleGoods.add(new IvoryTradeGood());
		possibleGoods.add(new PearlTradeGood());
		possibleGoods.add(new RumTradeGood());
		possibleGoods.add(new SaltTradeGood());
		possibleGoods.add(new SeafoodTradeGood());
		possibleGoods.add(new SilkTradeGood());
		possibleGoods.add(new SpiceTradeGood());
		possibleGoods.add(new WeaponTradeGood());
		
		TradeGood chosenGood = possibleGoods.get(new Random().nextInt(possibleGoods.size()));
		DecimalFormat dFormat = new DecimalFormat("#.00");
		chosenGood.setQuantity(ThreadLocalRandom.current().nextInt(1, 99 + 1));
		chosenGood.setInitialBuyPrice(Double.parseDouble(dFormat.format(ThreadLocalRandom.current().nextDouble(1.0, 100.0))));
		return chosenGood;
	}
}
