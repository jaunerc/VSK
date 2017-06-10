package ch.windmill.hslu.vsk.market.player;

import ch.windmill.hslu.vsk.market.ProducerStock;
import ch.windmill.hslu.vsk.market.Product;

/**
 * This class represents a producer of products.
 */
public class Producer extends BaseMarketPlayer {

	private int numProducts;
	private Product product;
	
	public Producer(int workDeltaMillis, int numProducts, Product product) {
		super(workDeltaMillis);
		this.numProducts = numProducts;
		this.product = product;
	}

	@Override
	public void work() {
		ProducerStock stock = ProducerStock.getSingleton();

		synchronized (stock) {
			for (int i = 0; i < numProducts; i++) {
				stock.pushProduct(product);
			}
			stock.notifyAll();
		}
		
		System.out.println("New products produced.");
	}
}
