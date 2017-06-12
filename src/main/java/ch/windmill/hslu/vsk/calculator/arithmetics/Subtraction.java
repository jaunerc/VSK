package ch.windmill.hslu.vsk.calculator.arithmetics;

public class Subtraction extends ArithmeticOperation {

	public Subtraction() {
		super("-");
	}

	@Override
	public long calc(int minuend, int subtrahend) {
		return minuend - subtrahend;
	}

	@Override
	public double calc(float minuend, float subtrahend) {
		return minuend - subtrahend;
	}

}
