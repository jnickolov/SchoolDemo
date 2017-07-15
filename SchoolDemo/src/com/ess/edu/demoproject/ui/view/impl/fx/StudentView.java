package com.ess.edu.demoproject.ui.view.impl.fx;

import java.util.Map;

import com.ess.edu.demoproject.entities.Student;
import com.ess.edu.demoproject.util.InvalidGradeException;
import com.ess.mvc.view.fx.ObjectViewFX;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

public class StudentView extends ObjectViewFX <Student> {
	private PersonView pv = null;
	//private GridPane pane = null;
	private Map<String, Double> marks = null;
	
	protected String sGrades = "";
	protected Label  lblGrades = null;
	protected TextArea  taGrades = null;
	
	public StudentView () {
		this.pv = new PersonView();  // will call initUI
		//this.pane = this.pv.pane;
		this.initUI();
	}

	protected void initUI() {
		//this.pv.initUI();
		this.node = this.pv.getNode();
		//this.pane = pv.pane;
		
		this.lblGrades = new Label ("Marks");
		
		GridPane grd = (GridPane)this.node;
		grd.add (this.lblGrades, 0, 3);
		Button b = new Button ("Add");
		b.setOnAction (e -> {  });
		grd.add(b, 1, 3);
		this.taGrades = new TextArea ("");
		grd.add (this.taGrades, 0, 4, 2, 1);
	}


	@Override
	public void displayModel (Student model) {
		this.pv.displayModel (model);
		
		this.marks = model.getGrades();
		this.sGrades = "";
		for (String s: marks.keySet()) {
			this.sGrades += s + ": " + marks.get (s) + "\n";
		}
		this.taGrades.setText (sGrades);
		
	}

	@Override
	public void updateModel (Student model) {
		this.pv.updateModel (model);
		if (this.marks != null) {
			for (String s: this.marks.keySet()) {
				try {
					model.setMark (s, this.marks.get(s));
				} catch (InvalidGradeException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
