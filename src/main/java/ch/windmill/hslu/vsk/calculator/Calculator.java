package ch.windmill.hslu.vsk.calculator;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Logger;

public class Calculator {

	private final static Logger LOG = Logger.getLogger(Calculator.class.getName());
	
	private Calculation<?> calculation;
	private final ExecutorService executorService;
	
	public Calculator() {
		this(null);
	}
	
	public Calculator(final Calculation<?> calculation) {
		this.calculation = calculation;
		executorService = Executors.newCachedThreadPool();
	}
	
	public void solve() throws InterruptedException, ExecutionException {
		final Future<?> future = executorService.submit(calculation);
		LOG.info("Result is " + future.get());
	}

	public void setCalculation(Calculation<?> calculation) {
		this.calculation = calculation;
	}
}
