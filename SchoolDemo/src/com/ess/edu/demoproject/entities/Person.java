package com.ess.edu.demoproject.entities;

import java.io.Serializable;

import com.ess.edu.demoproject.util.IDGenerator;

public class Person implements Serializable {
	private int id;  // used as unique ID
	private String firstName, lastName;
	
	public Person (String firstName, String lastName){
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = IDGenerator.getNextId();
	}

	public Person () {
		this.firstName = null;
		this.lastName = null;
		this.id = IDGenerator.getNextId();
	}

	public int getId() {
		return this.id;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getName () {
		return this.getFirstName() + " " + this.getLastName();
	}

	@Override
	public String toString() {
		return "Person [id=" + this.id + ", firstName=" + this.firstName + ", lastName=" + this.lastName + "]";
	}
	
}

