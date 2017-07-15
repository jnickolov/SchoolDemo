package com.ess.edu.demoproject.ctrl;

import com.ess.edu.demoproject.entities.Klass;
import com.ess.idname.IDName;

public class KlassIDName extends IDName {

	public KlassIDName (Klass kls) {
		super (kls.getId(), kls.getName());
	}
}
