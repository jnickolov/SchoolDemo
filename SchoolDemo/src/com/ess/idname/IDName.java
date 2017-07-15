package com.ess.idname;

public class IDName {
	private int ID;
	private String name;
	
	public IDName(int iD, String name) {
		this.ID = iD;
		this.name = name;
	}
	
	public int getID() {
		return this.ID;
	}
	public void setID(int iD) {
		this.ID = iD;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
//		return this.ID + ": " + this.name;
		return this.name;
	}
	
}
