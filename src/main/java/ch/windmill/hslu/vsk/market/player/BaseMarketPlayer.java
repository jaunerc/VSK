package ch.windmill.hslu.vsk.market.player;

/**
 * This is a base class for a markt player.
 */
public abstract class BaseMarketPlayer implements Runnable {

	private boolean isWorking;
	private Timer timer;
	
	/**
	 * Creates a new BaseMarketPlayer object.
	 * @param workDeltaMillis The delta time between two work steps.
	 */
	public BaseMarketPlayer(int workDeltaMillis) {
		timer = new Timer(workDeltaMillis);
		isWorking = false;
	}
	
	/**
	 * This method is executed in the run method. The timer of this
	 * class schedules this execution.
	 */
	public abstract void work();
	
	@Override
	public void run() {
		timer.setRunning(true);
		while(isWorking) {
			if(timer.tick()) {
				work();
			}
		}
		timer.setRunning(false);
	}

	public boolean isWorking() {
		return isWorking;
	}

	public void setWorking(boolean isWorking) {
		this.isWorking = isWorking;
	}
}
