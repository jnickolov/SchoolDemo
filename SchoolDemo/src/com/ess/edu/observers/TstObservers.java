package com.ess.edu.observers;

public class TstObservers {

	public static void main(String[] args) {
		System.out.println("Start");
		Person p1 = new Person();

		PersonObserver o1 = new PersonObserver();
		p1.addObserver(o1);
		
		p1.setName("Gogo");
		p1.setEgn(12345);
		

		Person p2 = new Person();
		//p2.addObserver(o1);
		p2.setName("Jojo");
		p2.setEgn(54321);
		
		
		p1.setName ("Gogo Gogov");
		System.out.println("end");

	}

}
