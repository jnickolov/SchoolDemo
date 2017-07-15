package com.ess.edu.demoproject.entities;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.ess.edu.demoproject.util.InvalidGradeException;
import com.ess.edu.demoproject.util.Mark;

public class Student extends Person {
	private Map <String, Mark> marks = new HashMap<>();
	
	public Student (String firstName, String lastName) {
		super (firstName, lastName);
	}

	public Student () {
		super ("", "");
	}

	public double getMark (String subject) {
		Mark ans = this.marks.get (subject);
		return ans != null ? ans.getValue() : 0;
	}
	
	public void setMark (String subject, double mrk) throws InvalidGradeException {
		this.marks.put (subject, new Mark (mrk));
	}

	public double getAverageGrade () {
		if (this.marks == null || this.marks.size() == 0)
			return 0.0;
		
		double sum = 0.0;
		for (String sbj: this.marks.keySet()) {
			sum += this.marks.get (sbj).getValue();  // no need to check 
		}
		
		return sum / this.marks.size();
	}
	
	public Map <String, Double> getGrades () {
		Map <String,Double> res = new TreeMap<String, Double>();
		if (this.marks != null) {
			for (String key: this.marks.keySet()) {
				res.put (key, this.marks.get (key).getValue());
			}
		}
		return res;
	}
	
	@Override
	public String toString() {
		String ps = super.toString();
		if (this.marks != null) {
			ps += "\nGrades: \n";
			for (String key: marks.keySet()) {
				ps += "    " + key + ": " + marks.get (key) + "\n";
			}
		} else {
			ps += "\nGrades: NONE\n";
		}
		
		return ps;
	}

	public void copyMarks (Student other) {
		this.marks.clear();
		this.marks.putAll (other.marks);
	}
}

