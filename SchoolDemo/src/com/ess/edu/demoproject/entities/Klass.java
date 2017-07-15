package com.ess.edu.demoproject.entities;

import java.util.LinkedList;
import java.util.List;

import com.ess.edu.demoproject.util.IDGenerator;

public class Klass {
	private int id;
	private char letter;
	private int grade;
	private Teacher manager;
	private List<Student> students;
	
	public Klass () {
		this (11, 'A');
	}
	
	public Klass (int grd, char let) {
		this.grade = grd;
		this.letter = let;
		this.id = IDGenerator.getNextId();
		this.manager = null;
		this.students = new LinkedList<>();
	}

	public int getId() {
		return this.id;
	}

	public String getName () {
		return this.grade + " " + this.letter;
	}
	
	public void addStudent (Student s) {
		if (s != null)
			this.students.add (s);
	}
	
	public void removeStudent (Student s) {
		this.students.remove (s);
	}
	
	public char getLetter() {
		return this.letter;
	}

	public void setLetter(char letter) {
		this.letter = letter;
	}

	public int getGrade() {
		return this.grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public Teacher getManager() {
		return this.manager;
	}

	public void setmanager (Teacher teacher) {
		this.manager = teacher;
	}

	public List<Student> getStudents() {
		return this.students;
	}

	public void setStudents (List<Student> students) {
		this.students = students;
	}
	
	public Student getStudentById (int id) {
		for (Student s: this.students) {
			if (s.getId() == id) {
				return s;
			}
		}
		return null;
	}
}
