package com.ess.edu.demoproject.entities;

import java.util.Collection;
import java.util.LinkedList;

public class Teacher extends Person {
	private Collection<Subject> subjects = new LinkedList<>();
	
	public Teacher() {
		super();
	}

	public Teacher (String firstName, String lastName) {
		super (firstName, lastName);
	}
	
	public void addSubject (Subject sub) {
		this.subjects.add (sub);
	}
	
	public void removeSubject (Subject sub) {
		this.subjects.remove (sub);
	}
	
	public String getSubjects () {
		String s = "";
		for (Subject sub: this.subjects) {
			s += sub.getTitle() + " ";
		}
		return s;
	}
	
}
