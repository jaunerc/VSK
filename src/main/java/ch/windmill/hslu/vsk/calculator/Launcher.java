package ch.windmill.hslu.vsk.calculator;

public class Launcher {

	private Launcher() {}
	
	public static void main(String[] args) {
		final ConsoleLineInterpreter consolePrompt = new ConsoleLineInterpreter();
		consolePrompt.start();
	}
}
