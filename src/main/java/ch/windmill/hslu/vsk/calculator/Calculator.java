package ch.windmill.hslu.vsk.calculator;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Calculator {
	
	private Calculation<?> calculation;
	private final ExecutorService executorService;
	private final ExecutorService resultCatchService;
	
	public Calculator() {
		this(null);
	}
	
	public Calculator(final Calculation<?> calculation) {
		this.calculation = calculation;
		executorService = Executors.newCachedThreadPool();
		resultCatchService = Executors.newCachedThreadPool();
	}
	
	public void solve() throws InterruptedException, ExecutionException {
		final Future<?> future = executorService.submit(calculation);
		resultCatchService.execute(new ResultCatcher(future));
	}
	
	private void showResultOnConsole( Object result) {
		System.out.println("\nResult is " + result);
	}

	public void setCalculation(Calculation<?> calculation) {
		this.calculation = calculation;
	}
	
	private class ResultCatcher implements Runnable {
		Future<?> future;
		
		public ResultCatcher(final Future<?> future) {
			this.future = future;
		}
		
		@Override
		public void run() {
			try {
				showResultOnConsole(future.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		
	}
}
