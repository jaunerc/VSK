package ch.windmill.hslu.vsk.calculator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ch.windmill.hslu.vsk.calculator.arithmetics.Addition;

public class IntegerCalculationTest {

	private IntegerCalculation integerCalculation;
	
	@Before
	public void setUp() {
		integerCalculation = new IntegerCalculation(10, 5);
	}

	@Test
	public void testAddition() {
		final Addition addition = new Addition();
		integerCalculation.setArithmeticOperation(addition);
		integerCalculation.calculate();
		assertEquals(15, integerCalculation.getResult().longValue());
	}

}
