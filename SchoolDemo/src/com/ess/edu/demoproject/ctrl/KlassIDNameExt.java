package com.ess.edu.demoproject.ctrl;

import com.ess.edu.demoproject.entities.Klass;

public class KlassIDNameExt extends KlassIDName {
	String managersName = "<no manager>";
	
	public KlassIDNameExt (Klass kls) {
		super (kls);
		if (kls.getManager() != null)
		this.managersName = kls.getManager().getFirstName().substring (0, 1) + ". " + kls.getManager().getLastName();
	}

	@Override
	public String getName() {
		return super.getName() + "(" + this.managersName + ")";
	}

	
}
