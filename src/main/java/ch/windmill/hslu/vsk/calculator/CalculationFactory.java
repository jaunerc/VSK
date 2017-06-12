package ch.windmill.hslu.vsk.calculator;

import ch.windmill.hslu.vsk.calculator.arithmetics.Addition;
import ch.windmill.hslu.vsk.calculator.arithmetics.ArithmeticOperation;
import ch.windmill.hslu.vsk.calculator.arithmetics.Division;
import ch.windmill.hslu.vsk.calculator.arithmetics.Multiplication;
import ch.windmill.hslu.vsk.calculator.arithmetics.Subtraction;

/**
 * This class represents a factory to create Calculation objects.
 */
public class CalculationFactory {
	
	private static ArithmeticOperation[] operationList = new ArithmeticOperation[] {new Addition(), 
			new Subtraction(), new Multiplication(), new Division()};
	
	private CalculationFactory(){}
	
	public static IntegerCalculation createIntegerCalculation(final int valueA, final int valueB, final String operator) {
		final IntegerCalculation calculation = new IntegerCalculation(valueA, valueB);
		final ArithmeticOperation operation = findOperationByString(operator);
		calculation.setArithmeticOperation(operation);
		return calculation;
	}
	
	public static FloatCalculation createFloatCalculation(final float valueA, final float valueB, final String operator) {
		final FloatCalculation calculation = new FloatCalculation(valueA, valueB);
		final ArithmeticOperation operation = findOperationByString(operator);
		calculation.setArithmeticOperation(operation);
		return calculation;
	}
	
	private static ArithmeticOperation findOperationByString(final String operator) {
		ArithmeticOperation operation = null;
		for(ArithmeticOperation op : operationList) {
			if(op.isOperatorSignMatching(operator)){
				operation = op;
			}
		}
		return operation;
	}
}
