package com.ess.edu.observers.decorator;

public class CreameDecorator implements Cofee {
	private Cofee cofee;

	public CreameDecorator () {
		this (null);
	}
	public CreameDecorator (Cofee c) {
		if (c == null)
			c = new BlackCofee();
		this.cofee = c;
	}
	
	@Override
	public Cup prepare() {
		Cup cup = this.cofee.prepare();
		addCreamer (cup);
		return cup;
	}

	private void addCreamer(Cup cup) {
		// TODO Auto-generated method stub
		
	}

}
