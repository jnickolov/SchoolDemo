package com.ess.edu.observers.decorator;

public class BlackCofee implements Cofee {

	@Override
	public Cup prepare() {
		// TODO Auto-generated method stub
		return new Cup();
	}

}
