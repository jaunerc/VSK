package ch.windmill.hslu.vsk.market;

import java.util.Stack;

/**
 * This class provides a singleton object that stores products.
 */
public class SaleStock {
	
	private static SaleStock singleton = null;
	
	public static SaleStock getSingleton() {
		if(singleton == null) {
			singleton = new SaleStock();
		}
		return singleton;
	}
	
	private Stack<Product> products;
	
	private SaleStock() {
		products = new Stack<>();
	}
	
	public synchronized void pushProduct(Product product) {
		products.push(product);
	}
	
	public synchronized Product popProduct() {
		return products.pop();
	}

	public synchronized int getStockSize() {
		return products.size();
	}
}
