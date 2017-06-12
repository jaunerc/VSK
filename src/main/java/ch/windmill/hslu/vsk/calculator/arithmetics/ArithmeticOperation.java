package ch.windmill.hslu.vsk.calculator.arithmetics;

/**
 * This interface provides methods to calculate arithmetic operations.
 */
public abstract class ArithmeticOperation {

	private String operatorSign;

	public ArithmeticOperation(final String operatorSign) {
		this.operatorSign = operatorSign;
	}

	/**
	 * Calculates a result with the given two integer values.
	 * 
	 * @param valueA
	 * @param valueB
	 * @return Calculation result.
	 */
	public abstract long calc(int valueA, int valueB);

	/**
	 * Calculates a result with the given two float values.
	 * 
	 * @param valueA
	 * @param valueB
	 * @return Calculation result.
	 */
	public abstract double calc(float valueA, float valueB);

	/**
	 * Whether the given operator sign matches this operation.
	 * 
	 * @param operator
	 *            Sign to check.
	 * @return
	 */
	public boolean isOperatorSignMatching(String operator) {
		return operatorSign.equals(operator);
	}

	public String getOperatorSign() {
		return operatorSign;
	}

	public void setOperatorSign(String operatorSign) {
		this.operatorSign = operatorSign;
	}
}
