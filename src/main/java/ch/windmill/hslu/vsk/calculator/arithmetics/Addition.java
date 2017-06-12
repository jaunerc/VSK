package ch.windmill.hslu.vsk.calculator.arithmetics;

public class Addition extends ArithmeticOperation {

	public Addition() {
		super("+");
	}

	@Override
	public long calc(int summandA, int summandB) {
		return summandA + summandB;
	}

	@Override
	public double calc(float summandA, float summandB) {
		return summandA + summandB;
	}

}
