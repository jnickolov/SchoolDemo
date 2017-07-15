package com.ess.edu.observers;

public interface Observee {
	void addObserver (Observer o);
	void removeObserver (Observer o);
	void notifyObservers();
}
