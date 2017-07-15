package com.ess.edu.observers;

public class PersonObserver implements Observer {

	@Override
	public void update (Observee obs) {
		
		Person p = (Person)obs;
		
		System.out.println("Updated: " + ((Person)obs).getName());
		//.getClass().System.out.println("Observed");
	}

}
