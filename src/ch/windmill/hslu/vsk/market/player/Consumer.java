package ch.windmill.hslu.vsk.market.player;

import ch.windmill.hslu.vsk.market.SaleStock;

/**
 * This class represents a player that consumes products of
 * the sale stock.
 */
public class Consumer extends BaseMarketPlayer {

	private int numProducts;
	
	public Consumer(int workDeltaMillis, int numProducts) {
		super(workDeltaMillis);
		this.numProducts = numProducts;
	}

	@Override
	public void work() {
		SaleStock stock = SaleStock.getSingleton();
		
		for(int i = 0; i < numProducts; i++) {
			synchronized (stock) {
				while(stock.getStockSize() == 0) {
					try {
						stock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				stock.popProduct();
			}
		}
		
		System.out.println("Products consumed.");
	}
	
	
}
