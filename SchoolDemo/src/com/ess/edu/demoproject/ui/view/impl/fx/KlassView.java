package com.ess.edu.demoproject.ui.view.impl.fx;

import com.ess.edu.demoproject.entities.Klass;
import com.ess.edu.demoproject.ui.undoablefield.text.UndoableTextField;
import com.ess.mvc.view.fx.ObjectViewFX;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class KlassView  extends ObjectViewFX <Klass> {
	private UndoableTextField<Integer> grade;
	private UndoableTextField<String> letter;

	public KlassView () {
		this.grade = new UndoableTextField<>(11);
		this.letter = new UndoableTextField<>();

		GridPane grd = new GridPane();
		grd.add (new Label ("Клас"), 0, 0);
		grd.add (this.grade, 1, 0);
		grd.add (new Label ("Паралелка"), 0, 1);
		grd.add (this.letter, 1, 1);
		grd.setHgap (8);
		grd.setVgap (4);
		this.node = grd;
	}
	
	@Override
	public void displayModel (Klass kls) {
		if (kls == null) {
			this.grade.setValue (11);
			this.letter.setValue ("А");
		} else {
			this.grade.setValue (kls.getGrade());
			this.letter.setValue ("" + kls.getLetter());
		}
	}

	@Override
	public void updateModel (Klass kls) {
		// TODO: check or add checkers
		if (kls != null) {
			kls.setGrade (Integer.parseInt (this.grade.getText()));
			kls.setLetter(this.letter.getText().trim().charAt(0));
		}
	}
}
