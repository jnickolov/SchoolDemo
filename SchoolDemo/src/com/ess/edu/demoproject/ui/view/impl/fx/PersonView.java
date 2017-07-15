package com.ess.edu.demoproject.ui.view.impl.fx;

import com.ess.edu.demoproject.entities.Person;
import com.ess.edu.demoproject.ui.undoablefield.text.StringUndoableField;
import com.ess.mvc.view.fx.ObjectViewFX;

import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class PersonView extends ObjectViewFX <Person> {
	protected GridPane pane = null;
	protected StringUndoableField tfFirstName = null, tfLastName = null;
	protected Label lblId = null;
	
	public PersonView () {
		super();
		this.tfFirstName = new StringUndoableField ();
		this.tfLastName = new StringUndoableField ();
		this.lblId = new Label ("");
		this.initUI();
	}
	
	protected void initUI () {
		GridPane grd = new GridPane();
		grd.add (this.lblId, 0, 0, 2, 1);
		grd.add (new Label ("First name"), 0, 1);
		grd.add (tfFirstName, 1, 1);
		grd.add (new Label ("Last name"), 0, 2);
		grd.add (tfLastName, 1, 2);
		grd.setHgap (8);
		grd.setVgap (4);
		lblId.setTextFill (Color.gray (0.7));
		GridPane.setHalignment (lblId, HPos.RIGHT);
		this.node = grd;
	}

	
	@Override
	public void displayModel (Person p) {
		if (p == null) {
			this.lblId.setText ("ID = 0");
			this.tfFirstName.setValue ("");
			this.tfLastName.setValue ("");
		} else {
			this.lblId.setText ("ID = " + p.getId());
			this.tfFirstName.setValue(p.getFirstName());
			this.tfLastName.setValue(p.getLastName());
		}
	}
	
	@Override
	public void updateModel (Person p) {  // Note: ID is not filled!
		if (p != null) {
			p.setFirstName (this.formatInput (this.tfFirstName.getText()));
			p.setLastName (this.formatInput (this.tfLastName.getText()));
		}
		System.out.println("Filling: " + p.toString());
	}
	
	private String formatInput (String s) {
		return (s == null) ? "" : s.trim();
	}
}
