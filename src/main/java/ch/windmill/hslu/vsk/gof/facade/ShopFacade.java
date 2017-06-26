package ch.windmill.hslu.vsk.gof.facade;

/**
 * Dummy implementation of the facade pattern.
 */
public class ShopFacade {

	private Customer customer;
	private Order order;
	private Stock stock;
	
	public ShopFacade() {
		customer = new Customer();
		order = new Order();
		stock = new Stock();
	}
	
	/**
	 * Pseudo method to show the approach of a facade.
	 */
	public void handleNewOrder() {
		order = new Order();
		stock.getItem();
		customer.validate();
		order.bill();
	}

	public String getItem() {
		return stock.getItem();
	}
	
	public void validate() {
		customer.validate();
	}
	
	public void bill() {
		order.bill();
	}
}
