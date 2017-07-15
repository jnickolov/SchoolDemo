package com.ess.edu.demoproject.util;

import com.ess.edu.demoproject.SchoolDemo;
import com.ess.edu.demoproject.entities.Klass;
import com.ess.edu.demoproject.entities.School;
import com.ess.edu.demoproject.entities.Student;

public class SchoolLoader {
	private static String lastLoadedPath = null;
	public static SchoolDemo application = null;
	
	public static School loadSchool (String path) {
		lastLoadedPath = path;
		return generateTestSchool();
	}
	
	public static void saveSchool () {
		
	}
	
	private static School generateTestSchool () {
		School sch = new School ("MG Geo Milev");
		
		Klass k = new Klass (11, 'A');
		k.addStudent (new Student ("Asen", "Asenov"));
		k.addStudent (new Student ("Angel", "Angelov"));
		k.addStudent (new Student ("Asparuh", "Asparuhov"));
		sch.getKlasses().add (k);

		k = new Klass (11, 'B');
		k.addStudent (new Student ("Boris", "Borisov"));
		k.addStudent (new Student ("Boyan", "Boyanov"));
		k.addStudent (new Student ("Biser", "Biserov"));
		sch.getKlasses().add (k);

		k = new Klass (11, 'V');
		k.addStudent (new Student ("Vasil", "Vasilev"));
		k.addStudent (new Student ("Vladimir", "Vladimirov"));
		k.addStudent (new Student ("Viktor", "Viktorov"));
		sch.getKlasses().add (k);

		return sch;
	}
}
