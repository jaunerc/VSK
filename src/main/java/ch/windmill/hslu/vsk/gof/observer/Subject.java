package ch.windmill.hslu.vsk.gof.observer;

import java.util.ArrayList;

public class Subject {
	
	private ArrayList<Observer> registeredObservers;
	
	public void register(Observer observer) {
		registeredObservers.add(observer);
	}
	
	public void unRegister(Observer observer) {
		registeredObservers.remove(observer);
	}
	
	public void handleStateChange() {
		notifyObserver();
	}
	
	private void notifyObserver() {
		for(Observer observer : registeredObservers) {
			observer.refresh();
		}
	}
}
