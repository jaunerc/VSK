package ch.windmill.hslu.vsk.calculator.arithmetics;

public class Division extends ArithmeticOperation {

	public Division() {
		super("/");
	}

	@Override
	public long calc(int dividend, int divisor) {
		handleDivideByZero(divisor);
		return dividend / divisor;
	}

	@Override
	public double calc(float dividend, float divisor) {
		handleDivideByZero((int) divisor);
		return dividend / divisor;
	}

	private void handleDivideByZero(final int divisor) {
		if(divisor == 0) {
			throw new ArithmeticException("The divisor cannot be zero.");
		}
	}
}
