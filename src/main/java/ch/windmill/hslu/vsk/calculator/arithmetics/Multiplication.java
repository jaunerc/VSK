package ch.windmill.hslu.vsk.calculator.arithmetics;

public class Multiplication extends ArithmeticOperation {

	public Multiplication() {
		super("*");
	}

	@Override
	public long calc(int multiplicand, int multiplier) {
		return multiplicand * multiplier;
	}

	@Override
	public double calc(float multiplicand, float multiplier) {
		return multiplicand * multiplier;
	}

}
