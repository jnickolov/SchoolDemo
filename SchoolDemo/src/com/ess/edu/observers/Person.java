package com.ess.edu.observers;

import java.util.Collection;
import java.util.LinkedList;

public class Person implements Observee {
	private String name;
	private int egn;
	
	Collection<Observer> obs = new LinkedList<>();

	public Person (PersonObserver o) {
		this.addObserver(o);
	}

	public Person() {
		//fdjh
	}
	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
		this.notifyObservers();
	}

	public int getEgn() {
		return this.egn;
	}

	public void setEgn(int egn) {
		this.egn = egn;
	}

	@Override
	public void addObserver(Observer o) {
		this.obs.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		this.obs.remove(o);
	}

	@Override
	public void notifyObservers() {
		for (Observer o: this.obs) {
			o.update (this);
		}
	}

}
