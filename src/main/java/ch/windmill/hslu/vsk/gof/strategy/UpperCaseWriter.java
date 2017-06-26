package ch.windmill.hslu.vsk.gof.strategy;

public class UpperCaseWriter implements StringWritingStrategy {

	@Override
	public void print(final String text) {
		System.out.println(text.toUpperCase());
	}

}
