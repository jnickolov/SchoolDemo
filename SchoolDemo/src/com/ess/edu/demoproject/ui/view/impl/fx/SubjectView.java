package com.ess.edu.demoproject.ui.view.impl.fx;

import com.ess.edu.demoproject.entities.Subject;
import com.ess.edu.demoproject.ui.textfieldvalidation.IntegerTextFieldValidator;
import com.ess.edu.demoproject.ui.undoablefield.text.IntegerUndoableField;
import com.ess.edu.demoproject.ui.undoablefield.text.StringUndoableField;
import com.ess.mvc.view.fx.ObjectViewFX;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class SubjectView extends ObjectViewFX <Subject> {
	protected GridPane pane = null;
	private StringUndoableField tfTitle = null;
	private IntegerUndoableField tfHours = null;
	
	public SubjectView () {
		super();
		this.tfTitle = new StringUndoableField ();
		this.tfHours = new IntegerUndoableField ();
		this.initUI();
	}
	
	protected void initUI () {
		this.pane = new GridPane();
		this.pane.add (new Label ("Title"), 0, 0);
		this.pane.add (tfTitle, 1, 0);
		this.pane.add (new Label ("Week hours"), 0, 1);
		this.pane.add (tfHours, 1, 1);
		this.pane.setHgap (8);
		this.pane.setVgap (4);
		new IntegerTextFieldValidator (tfHours) {

			@Override
			public boolean customCheck (Integer val) {
				return val > 0;
			}
		}; 
	}

	@Override
	public Node getNode () {
		return this.pane;
	}
	
	@Override
	public void displayModel (Subject sub) {
		if (sub == null) {
			this.tfTitle.setValue ("");
			this.tfHours.setValue (0);
		} else {
			this.tfTitle.setValue (sub.getTitle());
			this.tfHours.setValue (sub.getWeekHours());
		}
	}
	
	@Override
	public void updateModel (Subject sub) {  
		//  Note: simulate transaction: all fields are set, or no field is changed
		if (sub != null) {
			try {
				Integer h = 0;
				
				// Danger zone: could throw exceptions
				h = Integer.parseInt (this.tfHours.getText());  // possibly throws InvalidFormatException
				sub.setTitle (this.formatInput (this.tfTitle.getText()));  // possibly throws InvalidSubjectNameException
				
				sub.setWeekHours (h);  // set field valueAFTER exception zone passed
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	
	private String formatInput (String s) {
		return (s == null) ? "" : s.trim();
	}
}
