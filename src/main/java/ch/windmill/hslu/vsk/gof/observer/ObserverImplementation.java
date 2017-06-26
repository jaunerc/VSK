package ch.windmill.hslu.vsk.gof.observer;

public class ObserverImplementation implements Observer {
	
	private boolean observerState;
	private SubjectImplementation subjectImplementation;
	
	public ObserverImplementation(final SubjectImplementation subjectImplementation) {
		this.subjectImplementation = subjectImplementation;
		observerState = false;
	}

	@Override
	public void refresh() {
		observerState = subjectImplementation.getState();
	}
}
