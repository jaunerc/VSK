package ch.windmill.hslu.vsk.gof.strategy;

/**
 * Dummy interface of a strategy pattern.
 */
public interface StringWritingStrategy {

	/**
	 * Prints the given text in a concrete format to the console.
	 * @param text The string to print out.
	 */
	void print(String text);
}
