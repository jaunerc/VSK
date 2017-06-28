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
		isReadingCommandLine = true;
		showStartInfoOnConsole();
		readingConsole();
	}

	private void readingConsole() {
		String input = null;
		try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
			while (isReadingCommandLine) {
				if ((input = readInput(reader)) != null) {
					if (handleInput(input)) {
						try {
							solveCalculation();
						} catch (InterruptedException | ExecutionException e) {
							e.printStackTrace();
						}
					} else {
						System.err.println("Input is not correct!");
					}
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void showStartInfoOnConsole() {
		System.out.println("This is a lazy calculator.\nType some calculations...\n");
	}

	private String readInput(final BufferedReader reader) throws IOException {
		return reader.readLine();
	}

	private boolean handleInput(final String input) {
		final String[] separatedInput = input.split(" ");
		boolean inputIsInCorrectFormat = false;
		Calculation<?> calculation = null;
		if (separatedInput.length == 3) {
			inputIsInCorrectFormat = true;
			final String firstValue = separatedInput[0];
			final String operation = separatedInput[1];
			final String secondValue = separatedInput[2];

			if (containsFloat(firstValue) || containsFloat(secondValue)) {
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
		return inputIsInCorrectFormat;
	}

	private boolean containsFloat(final String valueToCheck) {
		boolean result = false;
		if (valueToCheck.contains(".")) {
			result = true;
		}
		return result;
	}

	private void solveCalculation() throws InterruptedException, ExecutionException {
		calculator.solve();
	}
}
