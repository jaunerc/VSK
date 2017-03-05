package ch.windmill.hslu.vsk.market.player;

public class Timer {
	
	private int delta;
	private long lastTimeStep;
	private long current;
	private boolean isRunning;
	
	public Timer(int delta) {
		super();
		this.delta = delta;
	}

	public boolean tick() {
		current = System.currentTimeMillis();
		if((current - lastTimeStep) >= delta) {
			lastTimeStep = current;
			return true;
		}
		return false;
	}
	
	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	public boolean isRunning() {
		return isRunning;
	}

	public int getDelta() {
		return delta;
	}

	public void setDelta(int delta) {
		this.delta = delta;
	}
}
