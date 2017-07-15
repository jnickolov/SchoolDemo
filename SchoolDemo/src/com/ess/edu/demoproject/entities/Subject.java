package com.ess.edu.demoproject.entities;

import java.io.Serializable;
import java.util.*;

import com.ess.edu.demoproject.util.InvalidSubjectNameException;

public class Subject implements Serializable {
	protected static Set<String> titles = new TreeSet<String>();
	
	private String title;
	private int weekHours;
	
	public Subject ()  {
		this.title = "";
		this.weekHours = 0;
	}

	public Subject (String title)  throws InvalidSubjectNameException{
		this (title, 0);
	}

	public Subject (String title, int weekHours) throws InvalidSubjectNameException {
		this.setTitle (title);
		this.setWeekHours (weekHours);
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle (String tit) throws InvalidSubjectNameException {
		checkTitleValidity (tit);

		if (! titles.contains (tit)) {
			throw new InvalidSubjectNameException ("Invalid subject name: " + tit);
		}
		
		this.title = tit.trim();
	}

	public int getWeekHours() {
		return this.weekHours;
	}

	public void setWeekHours (int weekHours) {
		// TODO: check argument: weekHours
		this.weekHours = weekHours;
	}

	public static Collection<String> listNames () {
		LinkedList<String> res = new LinkedList<>();
		res.addAll (titles);
		res.sort (null);
		return res;
	}
	
	public static void addTitle (String s) throws InvalidSubjectNameException {
		checkTitleValidity (s);
		titles.add (s);  // no check for duplicates: it is set
	}
	
	public static void checkTitleValidity (String s) throws InvalidSubjectNameException {
		if (s == null || s.length() == 0) {
			throw new InvalidSubjectNameException ("Invalid subject name: " + s);
		}
	}
	
	@Override
	public boolean equals (Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		Subject other = (Subject) obj;
		if (this.title == null) {
			if (other.title != null)
				return false;
		} else if (!this.title.equals(other.title))
			return false;
		return true;
	}

}
