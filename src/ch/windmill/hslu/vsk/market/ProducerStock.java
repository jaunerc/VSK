package ch.windmill.hslu.vsk.market;

import java.util.Stack;

/**
 * This class provides a singleton object that stores products.
 */
public class ProducerStock {
	
	private static ProducerStock singleton = null;
	
	public static ProducerStock getSingleton() {
		if(singleton == null) {
			singleton = new ProducerStock();
		}
		return singleton;
	}
	
	private Stack<Product> products;
	
	private ProducerStock() {
		products = new Stack<>();
	}
	
	public void pushProduct(Product product) {
		products.push(product);
	}
	
	public Product popProduct() {
		return products.pop();
	}
	
	public int getStockSize() {
		return products.size();
	}
}
