package ch.windmill.hslu.vsk.gof;

/**
 * Dummy implementation of the singleton pattern.
 */
public class Singleton {

	private static Singleton instance;
	
	private Singleton() {
		
	}
	
	public static Singleton getInstance() {
		if(instance == null) {
			instance = new Singleton();
		}
		return instance;
	}
}
