package com.ess.edu.observers;

public class TstSingl {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Singl s = Singl.getInstance();
	}

}

class Singl {
	public static Singl inst = new Singl();
	
	private Singl() {
		//djedhe
	}
	
	static Singl instance = null;
	
	static Singl getInstance() {
		if (instance == null)
			instance = new Singl();
		return instance;
	}
	
}