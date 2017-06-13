package ch.windmill.hslu.vsk.calculator;

import ch.windmill.hslu.vsk.calculator.arithmetics.ArithmeticOperation;

public class FloatCalculation extends Calculation<Double> {

	private float valueA;
	private float valueB;

	public FloatCalculation(final float valueA, final float valueB) {
		this.valueA = valueA;
		this.valueB = valueB;
	}

	@Override
	public void calculate() {
		final ArithmeticOperation operation = getArithmeticOperation();
		if (operation != null) {
			result = operation.calc(valueA, valueB);
		}
	}

}
