package com.ess.edu.demoproject.ui.view.impl.fx;

import com.ess.edu.demoproject.entities.Person;
import com.ess.edu.demoproject.entities.Teacher;

import javafx.scene.control.Label;

public class TeacherView extends PersonView {
	private String subjs = "";
	
	@Override
	protected void initUI() {
		// TODO Auto-generated method stub
		super.initUI();
		this.pane.add (new Label ("Subjects"), 0, 3);
		this.pane.add (new Label (this.subjs), 1, 3);

	}

	@Override
	public void displayModel (Person p) {
		// TODO Auto-generated method stub
		super.displayModel (p);
		Teacher t = (Teacher)p;
		this.subjs = t.getSubjects();
	}

}
