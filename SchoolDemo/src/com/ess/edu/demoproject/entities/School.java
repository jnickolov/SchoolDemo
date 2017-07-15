package com.ess.edu.demoproject.entities;

import java.util.Collection;
import java.util.LinkedList;

public class School {
	private Collection<Klass> klasses;
	private Collection<Teacher> teachers;
	private String name;
	
	public School (String name) {
		this.setName (name);
		this.klasses = new LinkedList<>();
		this.teachers = new LinkedList<>();
	}
	
	public Collection<Klass> getKlasses() {
		return this.klasses;
	}
	public void setKlasses(Collection<Klass> classes) {
		this.klasses = classes;
	}
	public Collection<Teacher> getTeachers() {
		return this.teachers;
	}
	public void setTeachers(Collection<Teacher> teachers) {
		this.teachers = teachers;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Klass getKlassById (int id) {
		for (Klass k: this.klasses) {
			if (k.getId() == id)
				return k;
		}
		
		return null;
	}
}
