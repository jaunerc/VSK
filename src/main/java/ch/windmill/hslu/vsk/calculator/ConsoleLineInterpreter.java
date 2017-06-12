package ch.windmill.hslu.vsk.calculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutionException;

/**
 * This class represents a console line interpreter.
 */
public class ConsoleLineInterpreter {

	private Calculator calculator;
	private boolean isReadingCommandLine;
	
	public ConsoleLineInterpreter() {
		calculator = new Calculator();
		isReadingCommandLine = false;
	}
	
	public void start() {
		String input = null;
		isReadingCommandLine = true;
		final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		while(isReadingCommandLine) {
			if((input = readInput(reader)) != null) {
				handleInput(input);
				try {
					solveCalculation();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private String readInput(final BufferedReader reader) {
		String input = null;
		try {
			input = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return input;
	}
	
	private void handleInput(final String input) {
		final String[] separatedInput = input.split(" ");
		Calculation<?> calculation = null;
		if(separatedInput.length == 3) {
			final String firstValue = separatedInput[0];
			final String operation = separatedInput[1];
			final String secondValue = separatedInput[2];
			
			if(containsFloat(firstValue) || containsFloat(secondValue)){
				final float valueA = Float.parseFloat(firstValue);
				final float valueB = Float.parseFloat(secondValue);
				calculation = CalculationFactory.createFloatCalculation(valueA, valueB, operation);
			} else {
				final int valueA = Integer.parseInt(firstValue);
				final int valueB = Integer.parseInt(secondValue);
				calculation = CalculationFactory.createIntegerCalculation(valueA, valueB, operation);
			}
			
			calculator.setCalculation(calculation);
		}
	}
	
	private boolean containsFloat(final String valueToCheck) {
		boolean result = false;
		if(valueToCheck.contains(".")) {
			result = true;
		}
		return result;
	}
	
	private void solveCalculation() throws InterruptedException, ExecutionException {
		calculator.solve();
	}
}
