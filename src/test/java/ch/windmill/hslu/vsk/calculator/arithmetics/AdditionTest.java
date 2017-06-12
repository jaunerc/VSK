package ch.windmill.hslu.vsk.calculator.arithmetics;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @see ch.windmill.hslu.vsk.calculator.arithmetics.Addition.
 */
public class AdditionTest {

	@Test
	public void testInteger() {
		final Addition addition = new Addition();
		assertEquals(8, addition.calc(4, 4));
		assertEquals(-12, addition.calc(-14, 2));
	}

	@Test
	public void testFloat() {
		final Addition addition = new Addition();
		assertEquals("", 5.2, addition.calc(2.4f, 2.8f), 0.01);
		assertEquals("", -8.35, addition.calc(-9.35f, 1f), 0.01);
	}
	
	@Test
	public void testIsOperatorSignMatching() {
		final Addition addition = new Addition();
		assertTrue(addition.isOperatorSignMatching("+"));
	}
}
