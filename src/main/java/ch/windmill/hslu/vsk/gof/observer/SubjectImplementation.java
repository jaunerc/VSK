package ch.windmill.hslu.vsk.gof.observer;

public class SubjectImplementation extends Subject {
	
	private boolean state;
	
	public SubjectImplementation() {
		state = false;
	}

	public void setState(final boolean state) {
		this.state = state;
		handleStateChange();
	}
	
	public boolean getState() {
		return state;
	}
}
