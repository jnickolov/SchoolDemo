package com.ess.edu.demoproject.util;

import java.io.Serializable;

public class Mark implements Serializable {
	private static final double MIN_VALUE = 2;
	private static final double MAX_VALUE = 6;

	private double value = 0;
	
	public Mark (double val) throws InvalidGradeException {
		this.setValue (val);
	}
	
	public static boolean isValidGrade (double gr) {
		return gr >= MIN_VALUE && gr <= MAX_VALUE;
	}

	public double getValue () {
		return this.value;
	}

	public void setValue (double val) throws InvalidGradeException {
		if (isValidGrade (val)) {
			this.value = val;
		} else { 
			throw new InvalidGradeException ("Error: " + val + " is not a valid value for a mark.");
		}
	}

	@Override
	public String toString() {
		return "" + this.value;
	}
	
}
