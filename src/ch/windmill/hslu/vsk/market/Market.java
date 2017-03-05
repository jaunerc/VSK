package ch.windmill.hslu.vsk.market;

import java.util.ArrayList;

import ch.windmill.hslu.vsk.market.player.BaseMarketPlayer;
	
/**
 * This class represents a place where producers, salesmen and consumers
 * trade with products.
 *
 */
public class Market {
	
	public final static int WAITTIMEMILLIS = 2000;
	
	private ArrayList<BaseMarketPlayer> players;
	private boolean isTrading;
	
	public Market() {
		players = new ArrayList<>();
		isTrading = false;
	}
	
	/**
	 * Starts the trade. Creates a new thread for each market player and
	 * prints out the stock level every two seconds in an endless loop.
	 */
	public void trade() {
		Thread thread = null;
		
		for(BaseMarketPlayer player : players) {
			player.setWorking(true);
			thread = new Thread(player);
			thread.setDaemon(true);
			thread.start();
		}
		
		while(isTrading) {
			System.out.println("Current stocks");
			System.out.println("- Producer stock: "+ProducerStock.getSingleton().getStockSize());
			System.out.println("- Sale stock: "+SaleStock.getSingleton().getStockSize());
			try {
				thread.sleep(WAITTIMEMILLIS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void addPlayer(BaseMarketPlayer player) {
		players.add(player);
	}
	
	public BaseMarketPlayer getPlayer(int listIndex) {
		return players.get(listIndex);
	}

	public boolean isTrading() {
		return isTrading;
	}

	public void setTrading(boolean isTrading) {
		this.isTrading = isTrading;
	}
}