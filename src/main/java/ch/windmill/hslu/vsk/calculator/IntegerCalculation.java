package ch.windmill.hslu.vsk.calculator;

import ch.windmill.hslu.vsk.calculator.arithmetics.ArithmeticOperation;

public class IntegerCalculation extends Calculation<Long> {

	private int valueA;
	private int valueB;
	
	public IntegerCalculation(final int valueA, final int valueB) {
		this.valueA = valueA;
		this.valueB = valueB;
	}
	
	@Override
	public void calculate() {
		final ArithmeticOperation operation = getArithmeticOperation();
		if(operation != null) {
			result = operation.calc(valueA, valueB);
		}
	}

}
