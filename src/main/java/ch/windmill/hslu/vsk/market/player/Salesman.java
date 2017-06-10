package ch.windmill.hslu.vsk.market.player;

import ch.windmill.hslu.vsk.market.ProducerStock;
import ch.windmill.hslu.vsk.market.Product;
import ch.windmill.hslu.vsk.market.SaleStock;

/**
 * This class represents a player that takes products of a producer
 * and adds them to the sale stock.
 */
public class Salesman extends BaseMarketPlayer {

	private int numProducts;
	
	public Salesman(int workDeltaMillis, int numProducts) {
		super(workDeltaMillis);
		this.numProducts = numProducts;
	}

	@Override
	public void work() {
		ProducerStock producerStock = ProducerStock.getSingleton();
		SaleStock saleStock = SaleStock.getSingleton();
		Product product = null;
		
		for(int i = 0; i < numProducts; i++) {
			
			// Take a product of the producer stock.
			synchronized (producerStock) {
				while(producerStock.getStockSize() == 0) {
					try {
						producerStock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				product = producerStock.popProduct();
			}
			
			// Adds the product to the sale stock.
			synchronized (saleStock) {
				saleStock.pushProduct(product);
				saleStock.notify();
			}
		}
		
		System.out.println("Products transfered to sale stock.");
	}

	public int getNumProducts() {
		return numProducts;
	}

	public void setNumProducts(int numProducts) {
		this.numProducts = numProducts;
	}
}
