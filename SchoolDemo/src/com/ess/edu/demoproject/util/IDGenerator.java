package com.ess.edu.demoproject.util;

public final class IDGenerator {
	private static int UNDEFINED = -1;  
	private static int nextId = UNDEFINED;

	public static int getNextId () {
		if (nextId == UNDEFINED) {
			String sid = Configurator.getInstance().getProperty ("ID");
			if (sid == null) {  // not found
				nextId = 1;
			} else {
				nextId = Integer.parseInt (sid);
			}
		} else {
			++nextId;
		}
		
		Configurator.getInstance().setProperty ("ID", "' + nextId");
		return nextId;
	}
	
	private IDGenerator () {
		//  don't create instances!
	}
}
