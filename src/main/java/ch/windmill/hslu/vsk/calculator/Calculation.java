package ch.windmill.hslu.vsk.calculator;

import java.util.concurrent.Callable;

import ch.windmill.hslu.vsk.calculator.arithmetics.ArithmeticOperation;

/**
 * This class represents a Calculation.
 * 
 * @param <V>
 *            The result type.
 */
public abstract class Calculation<V> implements Callable<V> {

	public V result;
	private ArithmeticOperation arithmeticOperation;

	@Override
	public V call() throws Exception {
		calculate();
		return result;
	}

	/**
	 * Calculates the result of the calculation. This method is invoked by the
	 * call() method.
	 */
	public abstract void calculate();

	public ArithmeticOperation getArithmeticOperation() {
		return arithmeticOperation;
	}

	public void setArithmeticOperation(final ArithmeticOperation arithmeticOperation) {
		this.arithmeticOperation = arithmeticOperation;
	}
}
