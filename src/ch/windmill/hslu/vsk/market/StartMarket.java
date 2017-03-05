package ch.windmill.hslu.vsk.market;

import ch.windmill.hslu.vsk.market.player.Consumer;
import ch.windmill.hslu.vsk.market.player.Producer;
import ch.windmill.hslu.vsk.market.player.Salesman;

/**
 * This class provides a main method as well as fields and methods
 * to create a new market.
 */
public class StartMarket {
	
	private final static int DELTA_PRODUCERMILLIS = 2000;
	private final static int DELTA_SALEMILLIS = 1500;
	private final static int DELTA_CONSUMERMILLIS = 1000;
	private final static int NUM_PRODUCER = 20;
	private final static int NUM_SALESMEN = 15;
	private final static int NUM_CONSUMER = 30;
	private final static int NUMPROD_PRODUCER = 10;
	private final static int NUMPROD_SALESMEN = 6;
	private final static int NUMPROD_CONSUMER = 2;
	
	public static void main(String[] args) {
		Market market = new Market();
		
		for(int i = 0; i < NUM_PRODUCER; i++) {
			market.addPlayer(createProducer());
		}
		for(int i = 0; i < NUM_SALESMEN; i++) {
			market.addPlayer(createSalesman());
		}
		for(int i = 0; i < NUM_CONSUMER; i++) {
			market.addPlayer(createConsumer());
		}
		
		market.setTrading(true);
		market.trade();
	}
	
	public static Producer createProducer() {
		int delta = (int) (DELTA_PRODUCERMILLIS * Math.random());
		return new Producer(delta, NUMPROD_PRODUCER, Product.APPLE);
	}

	public static Salesman createSalesman() {
		int delta = (int) (DELTA_SALEMILLIS * Math.random());
		return new Salesman(delta, NUMPROD_SALESMEN);
	}

	public static Consumer createConsumer() {
		int delta = (int) (DELTA_CONSUMERMILLIS * Math.random());
		return new Consumer(delta, NUMPROD_CONSUMER);
	}
}
